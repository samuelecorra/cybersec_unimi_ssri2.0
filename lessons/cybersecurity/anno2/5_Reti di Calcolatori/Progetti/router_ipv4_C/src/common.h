/* =====================================================================
 * common.h  —  Definizioni condivise da tutti i moduli di crouter.
 *
 * Contiene tre gruppi di cose:
 *   1. le costanti globali (dimensioni dei buffer, EtherType);
 *   2. le STRUTTURE che descrivono gli header dei protocolli (Ethernet,
 *      ARP, IPv4, ICMP, UDP), mappate byte-per-byte sul contenuto reale
 *      dei pacchetti;
 *   3. le strutture di stato del router e alcune funzioni di utilità
 *      `static inline` (piccole, quindi definite qui nell'header).
 *
 * NOTA TRASVERSALE — "network byte order". In rete i campi multi-byte
 * viaggiano in big-endian (il byte più significativo per primo). Le CPU
 * x86/ARM su cui giriamo sono little-endian. Perciò ogni volta che si
 * legge/scrive un campo di header a 16 o 32 bit bisogna convertire con
 * ntohs/ntohl (network->host) o htons/htonl (host->network). I campi
 * marcati "network order" NON vanno mai confrontati/stampati senza
 * conversione.
 * ===================================================================== */
#ifndef CROUTER_COMMON_H          /* include guard: evita doppie inclusioni */
#define CROUTER_COMMON_H

/* --- Header di sistema e cosa ci serve da ciascuno --- */
#include <arpa/inet.h>   /* htons/htonl/ntohs/ntohl, inet_ntop, inet_pton   */
#include <net/if.h>      /* IF_NAMESIZE, struct ifreq (nomi di interfaccia)  */
#include <netinet/in.h>  /* struct in_addr, IPPROTO_*, INADDR_*, IN_MULTICAST */
#include <stdbool.h>     /* il tipo bool + true/false (C99)                  */
#include <stdint.h>      /* interi a larghezza fissa: uint8_t/uint16_t/...   */
#include <stdio.h>       /* snprintf (usata dalle utility qui sotto)         */
#include <string.h>      /* memcmp/memcpy (usate dalle utility qui sotto)    */
#include <time.h>        /* clock_gettime, CLOCK_MONOTONIC, struct timespec  */

#define MAX_IFACES   8     /* numero massimo di interfacce gestibili         */
#define FRAME_BUF_SZ 2048  /* buffer di un frame: > MTU 1500 + header, con margine */
#define ETH_HDR_LEN  14    /* dimensione dell'header Ethernet: 6+6+2 byte    */

/* EtherType: il campo a 16 bit dell'header Ethernet che dice "cosa c'è
 * dentro". Sono i due valori che a noi interessano; qui in host order,
 * si confrontano dopo aver fatto ntohs() sul campo del frame. */
#define ETHERTYPE_IPV4 0x0800
#define ETHERTYPE_ARP  0x0806

/* =====================================================================
 * Header di protocollo.
 *
 * __attribute__((packed)) è FONDAMENTALE: dice al compilatore di NON
 * inserire byte di allineamento fra i campi. Senza, il compilatore
 * potrebbe "spaziare" i membri per allinearli in memoria, e la struct
 * non combacerebbe più byte-per-byte con il pacchetto reale. Con packed,
 * fare `(struct ipv4_hdr *)puntatore_al_pacchetto` mappa esattamente i
 * campi sul contenuto del frame.
 *
 * Tutti i campi multi-byte sono in NETWORK ORDER (vedi nota in testa).
 * ===================================================================== */

/* Header Ethernet II (livello 2). */
struct eth_hdr {
    uint8_t  dst[6];      /* MAC di destinazione                              */
    uint8_t  src[6];      /* MAC sorgente                                     */
    uint16_t ethertype;   /* payload: 0x0800 IPv4, 0x0806 ARP (vedi macro)    */
} __attribute__((packed));

/* Pacchetto ARP (Address Resolution Protocol) su Ethernet/IPv4.
 * Serve a scoprire "che MAC ha questo IP?" nella LAN. */
struct arp_pkt {
    uint16_t htype;   /* hardware type: 1 = Ethernet                          */
    uint16_t ptype;   /* protocol type: 0x0800 = IPv4                         */
    uint8_t  hlen;    /* lunghezza indirizzo hardware: 6 (MAC)                */
    uint8_t  plen;    /* lunghezza indirizzo di protocollo: 4 (IPv4)          */
    uint16_t oper;    /* operazione: 1 = request, 2 = reply                   */
    uint8_t  sha[6];  /* Sender Hardware Address  (MAC del mittente)          */
    uint32_t spa;     /* Sender Protocol Address  (IP del mittente)           */
    uint8_t  tha[6];  /* Target Hardware Address  (MAC cercato; 0 in request) */
    uint32_t tpa;     /* Target Protocol Address  (IP di cui si cerca il MAC) */
} __attribute__((packed));

/* Header IPv4 (livello 3), forma minima da 20 byte (senza opzioni). */
struct ipv4_hdr {
    uint8_t  ver_ihl;   /* 4 bit "version" (=4) | 4 bit "IHL" = lunghezza     */
                        /*  header in parole da 32 bit (5 => 20 byte).        */
                        /*  Si estraggono con (ver_ihl>>4) e (ver_ihl&0x0F).  */
    uint8_t  tos;       /* Type of Service / DSCP (priorità del pacchetto)    */
    uint16_t tot_len;   /* lunghezza totale del datagramma (header+dati)      */
    uint16_t id;        /* identificativo, usato per la frammentazione        */
    uint16_t frag_off;  /* 3 bit flag (DF/MF) | 13 bit offset del frammento   */
    uint8_t  ttl;       /* Time To Live: decrementato a ogni hop (anti-loop)  */
    uint8_t  proto;     /* protocollo incapsulato: 1 ICMP, 6 TCP, 17 UDP      */
    uint16_t checksum;  /* checksum del solo header (Internet checksum)       */
    uint32_t saddr;     /* indirizzo IP sorgente     (network order)          */
    uint32_t daddr;     /* indirizzo IP destinazione (network order)          */
} __attribute__((packed));

/* Header ICMP (dentro un pacchetto IPv4, proto=1). I 4 byte "rest"
 * cambiano significato col tipo: per Echo sono identificatore+sequenza,
 * per Time Exceeded / Unreachable sono inutilizzati (zero). */
struct icmp_hdr {
    uint8_t  type;      /* 0 Echo Reply, 3 Dest Unreach, 8 Echo, 11 Time Exc. */
    uint8_t  code;      /* sotto-tipo (dipende da 'type')                     */
    uint16_t checksum;  /* Internet checksum di header ICMP + payload         */
    uint32_t rest;      /* "Rest of Header": dipende dal tipo                 */
} __attribute__((packed));

/* Header UDP (dentro IPv4, proto=17). RIP viaggia qui sopra. */
struct udp_hdr {
    uint16_t sport;     /* porta sorgente                                     */
    uint16_t dport;     /* porta destinazione (520 per RIP)                   */
    uint16_t len;       /* lunghezza di header UDP + dati                     */
    uint16_t checksum;  /* checksum su pseudo-header IPv4 + segmento UDP      */
} __attribute__((packed));

/* =====================================================================
 * Stato del router
 * ===================================================================== */

/* Una interfaccia di rete gestita da crouter. Gli indirizzi IP vivono
 * QUI (non nel kernel): è crouter a "possedere" questi IP e a rispondere
 * per essi ad ARP/ICMP/RIP. */
struct iface {
    char     name[IF_NAMESIZE];  /* nome, es. "eth1" (IF_NAMESIZE da net/if.h) */
    int      sys_ifindex;   /* indice numerico dell'interfaccia dato dal kernel */
    int      fd;            /* file descriptor del socket AF_PACKET su di essa  */
    uint8_t  mac[6];        /* MAC dell'interfaccia (letto dal kernel via ioctl)*/
    uint32_t ip;            /* IP dell'interfaccia            (network order)   */
    uint8_t  prefix_len;    /* lunghezza del prefisso, es. 24 per /24           */
    uint32_t mask;          /* netmask corrispondente         (network order)   */
    bool     rip;           /* true se RIP è attivo su questa interfaccia       */
};

/* Lo stato globale: l'insieme delle interfacce. */
struct router {
    struct iface ifaces[MAX_IFACES];
    int          n_ifaces;
};

/* =====================================================================
 * Utility inline (piccole, quindi definite qui e condivise via include)
 * ===================================================================== */

/* Converte una lunghezza di prefisso (0..32) nella netmask, in network
 * order. Esempio: 24 -> 0xFFFFFF00 (host) -> htonl -> byte 255.255.255.0
 *
 * INSIDIA C: `~0u << (32 - len)` con len==0 diventerebbe uno shift di 32
 * posizioni su un uint a 32 bit, che in C è "undefined behavior" (il
 * risultato non è garantito 0). Perciò trattiamo len==0 come caso a
 * parte, restituendo 0 (netmask vuota della rotta di default /0). */
static inline uint32_t prefix_to_mask(uint8_t len)
{
    return len == 0 ? 0 : htonl(~0u << (32 - len));
}

/* Restituisce "adesso" in secondi da un orologio MONOTONO. Perché non
 * time()? Perché CLOCK_MONOTONIC non torna mai indietro e non è toccato
 * da aggiustamenti dell'ora di sistema (NTP, cambio manuale): i timer di
 * RIP e ARP misurano intervalli, e devono essere immuni a salti d'orologio. */
static inline time_t mono_now(void)
{
    struct timespec ts;
    clock_gettime(CLOCK_MONOTONIC, &ts);
    return ts.tv_sec;
}

/* Formatta un IP (network order) in stringa "a.b.c.d".
 *
 * TRUCCO dei "buffer rotanti": usiamo 4 buffer statici a rotazione così
 * che una singola printf con più chiamate a ip_str() — es.
 *   printf("%s -> %s", ip_str(a), ip_str(b))
 * non veda i due risultati sovrascriversi a vicenda. Con un solo buffer
 * statico, b sovrascriverebbe a prima della stampa. Con 4 buffer se ne
 * possono avere fino a 4 vivi contemporaneamente.
 *
 * inet_ntop("network to presentation"): converte l'indirizzo binario in
 * testo; AF_INET seleziona IPv4. <arpa/inet.h>. */
static inline const char *ip_str(uint32_t ip_net)
{
    static char buf[4][INET_ADDRSTRLEN];   /* INET_ADDRSTRLEN = 16 ("255.255.255.255\0") */
    static int  i;
    struct in_addr a = { .s_addr = ip_net };
    i = (i + 1) & 3;                        /* i = (i+1) mod 4, ciclo sui 4 buffer */
    inet_ntop(AF_INET, &a, buf[i], sizeof buf[i]);
    return buf[i];
}

/* Formatta un MAC a 6 byte in "aa:bb:cc:dd:ee:ff". Due buffer rotanti,
 * per lo stesso motivo di ip_str (due MAC nella stessa printf). */
static inline const char *mac_str(const uint8_t *m)
{
    static char buf[2][18];   /* 17 caratteri + terminatore                    */
    static int  i;
    i ^= 1;                    /* alterna 0/1 con lo XOR                        */
    snprintf(buf[i], sizeof buf[i], "%02x:%02x:%02x:%02x:%02x:%02x",
             m[0], m[1], m[2], m[3], m[4], m[5]);
    return buf[i];
}

/* True se 'ip' (network order) è l'indirizzo di una nostra interfaccia:
 * serve a capire se un pacchetto è "destinato a noi" (da consegnare in
 * locale) oppure va inoltrato. */
static inline bool ip_is_ours(const struct router *rt, uint32_t ip)
{
    for (int i = 0; i < rt->n_ifaces; i++)
        if (rt->ifaces[i].ip == ip)
            return true;
    return false;
}

#endif /* CROUTER_COMMON_H */
