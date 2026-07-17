/* =====================================================================
 * parse.c  —  Checksum Internet e validazione IPv4.
 *
 * Il "checksum Internet" (RFC 1071) usato da IPv4/ICMP/UDP/TCP è la somma
 * in complemento a uno, a parole di 16 bit, di tutto il blocco, poi
 * complementata. Le due proprietà che sfruttiamo:
 *   - per CALCOLARLO: si azzera il campo checksum, si somma tutto, si
 *     complementa e si scrive il risultato;
 *   - per VERIFICARLO: si somma tutto (campo checksum incluso) e il
 *     risultato deve venire 0.
 * ===================================================================== */
#include "parse.h"

/* Somma "grezza" a 16 bit di 'len' byte in un accumulatore a 32 bit (che
 * raccoglie i riporti, ripiegati poi da fold()). I byte sono trattati a
 * coppie big-endian: p[0] è la metà alta, p[1] la bassa. Se 'len' è
 * dispari, l'ultimo byte va nella metà alta con la bassa a 0 (padding). */
static uint32_t sum_bytes(uint32_t sum, const uint8_t *p, size_t len)
{
    while (len > 1) {
        sum += ((uint32_t)p[0] << 8) | p[1];
        p += 2;
        len -= 2;
    }
    if (len)
        sum += (uint32_t)p[0] << 8;
    return sum;
}

/* "Ripiega" i riporti oltre il 16° bit dentro i 16 bit bassi (somma in
 * complemento a uno) finché non ne restano, poi complementa (~). Il
 * ciclo gira al più due volte. */
static uint16_t fold(uint32_t sum)
{
    while (sum >> 16)
        sum = (sum & 0xFFFF) + (sum >> 16);
    return (uint16_t)~sum;
}

uint16_t inet_cksum(const void *data, size_t len)
{
    /* fold() lavora in host order; htons riporta il risultato in network
     * order per scriverlo nel campo del pacchetto. */
    return htons(fold(sum_bytes(0, data, len)));
}

uint16_t udp_cksum(uint32_t saddr, uint32_t daddr, const void *seg, size_t seg_len)
{
    /* RFC 768: il checksum UDP copre uno "pseudo-header" fittizio
     * (IP sorgente, IP destinazione, un byte zero, il protocollo=17 e la
     * lunghezza UDP) seguito dal segmento vero. Serve a legare il segmento
     * alla coppia di indirizzi IP, così un pacchetto dirottato non passa
     * per valido. */
    uint32_t sum = 0;
    sum  = sum_bytes(sum, (const uint8_t *)&saddr, 4);
    sum  = sum_bytes(sum, (const uint8_t *)&daddr, 4);
    sum += IPPROTO_UDP + (uint32_t)seg_len;   /* pseudo: proto(17) + lunghezza */
    sum  = sum_bytes(sum, seg, seg_len);
    uint16_t r = fold(sum);
    /* Caso speciale RFC 768: se il checksum calcolato è 0, si trasmette
     * come 0xFFFF, perché il valore 0 nel campo significa "checksum
     * assente" (i due valori sono equivalenti in complemento a uno). */
    if (r == 0)
        r = 0xFFFF;
    return htons(r);
}

bool ipv4_valid(const uint8_t *p, size_t avail)
{
    /* 1) ci devono essere almeno 20 byte per l'header minimo */
    if (avail < sizeof(struct ipv4_hdr))
        return false;
    const struct ipv4_hdr *ih = (const struct ipv4_hdr *)p;
    /* 2) il nibble alto di ver_ihl deve valere 4 (IPv4) */
    if ((ih->ver_ihl >> 4) != 4)
        return false;
    /* 3) IHL (nibble basso) è in parole da 32 bit: *4 dà i byte. Deve
     *    essere >= 20 (header minimo) e non oltre i byte disponibili. */
    size_t ihl = (size_t)(ih->ver_ihl & 0x0F) * 4;
    if (ihl < 20 || ihl > avail)
        return false;
    /* 4) tot_len (header+dati) coerente: non minore dell'header, non
     *    maggiore dei byte realmente ricevuti */
    size_t tot = ntohs(ih->tot_len);
    if (tot < ihl || tot > avail)
        return false;
    /* 5) checksum dell'header: deve verificare a 0 (vedi commento in testa) */
    if (inet_cksum(p, ihl) != 0)
        return false;
    return true;
}

void ipv4_ttl_dec(struct ipv4_hdr *ih)
{
    /* Aggiornamento INCREMENTALE del checksum (RFC 1624, eq. 3):
     *   HC' = ~(~HC + ~m + m')
     * dove HC è il vecchio checksum, m la parola a 16 bit modificata e m'
     * il suo nuovo valore. Qui la parola modificata è quella che contiene
     * TTL (byte alto) e protocollo (byte basso): cambia solo il TTL, ma la
     * formula lavora sull'intera parola a 16 bit. Vantaggio: due addizioni
     * invece di risommare tutto l'header a ogni hop. */
    uint16_t m = ((uint16_t)ih->ttl << 8) | ih->proto;   /* m  = TTL|proto (prima) */
    ih->ttl--;
    uint16_t m1 = ((uint16_t)ih->ttl << 8) | ih->proto;  /* m' = TTL|proto (dopo)  */

    uint32_t sum = (uint16_t)~ntohs(ih->checksum);  /* ~HC in host order         */
    sum += (uint16_t)~m;                            /* + ~m                       */
    sum += m1;                                      /* + m'                       */
    sum = (sum & 0xFFFF) + (sum >> 16);             /* ripiega i riporti...       */
    sum = (sum & 0xFFFF) + (sum >> 16);             /* ...(due giri bastano)      */
    ih->checksum = htons((uint16_t)~sum);           /* complementa e riscrivi     */
}
