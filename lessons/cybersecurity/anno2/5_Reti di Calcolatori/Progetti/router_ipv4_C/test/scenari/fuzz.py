#!/usr/bin/env python3
"""Fuzzing del data plane di crouter.

Inietta migliaia di frame MALFORMATI verso il router e verifica che li
scarti senza andare in crash (fase 6 del design: robustezza). Non controlla
le risposte: l'obiettivo è stressare i parser (Ethernet/IP/ARP/UDP) con
input volutamente sbagliati e dimostrare che le validazioni difensive
tengono.

Da eseguire dentro il namespace pc1:
    ip netns exec pc1 python3 fuzz.py
"""
import os
import random
import socket
import struct

IFACE = "eth0"
ROUTER_MAC = bytes.fromhex("020000010001")   # MAC di r1 eth1 (destinatario)
SRC_MAC = bytes.fromhex("020000ff0001")      # MAC sorgente fasullo

# AF_PACKET/SOCK_RAW: come in netio.c, ci dà accesso ai frame Ethernet
# grezzi. bind((IFACE, 0)) lega il socket all'interfaccia eth0 di pc1.
s = socket.socket(socket.AF_PACKET, socket.SOCK_RAW)
s.bind((IFACE, 0))

random.seed(42)   # seme fisso -> esecuzioni riproducibili
sent = 0

def eth(dst=ROUTER_MAC, etype=0x0800):
    """Costruisce un header Ethernet (14 byte): dst + src + ethertype.
    struct.pack('!H', ...) impacchetta un intero a 16 bit in big-endian
    ('!' = network order, 'H' = unsigned short)."""
    return dst + SRC_MAC + struct.pack("!H", etype)

# 1. Byte completamente casuali, di lunghezza varia (anche sotto la
#    dimensione minima di un header): mette alla prova i controlli di
#    lunghezza a monte.
for _ in range(500):
    n = random.randint(1, 120)
    s.send(eth() + os.urandom(n))
    sent += 1

# 2. Header IPv4 "di base" ben formato, poi corrotto in UN campo a caso:
#    versione/IHL, lunghezza totale bugiarda, TTL 0, checksum errato,
#    protocollo casuale. struct.pack impacchetta i 20 byte dell'header
#    (B=byte, H=short, 4s=4 byte); inet_aton converte l'IP in 4 byte.
base = struct.pack("!BBHHHBBH4s4s", 0x45, 0, 84, 1, 0, 64, 1, 0,
                   socket.inet_aton("10.0.1.100"), socket.inet_aton("10.0.4.100"))
for i in range(500):
    pkt = bytearray(base + os.urandom(64))
    field = random.randint(0, 4)
    if field == 0:
        pkt[0] = random.choice([0x15, 0x60, 0x40, 0x4F])   # versione/IHL assurdi
    elif field == 1:
        pkt[2:4] = struct.pack("!H", random.choice([0, 5, 19, 65535]))  # tot_len
    elif field == 2:
        pkt[8] = 0                                          # TTL 0
    elif field == 3:
        pkt[10:12] = os.urandom(2)                          # checksum a caso
    else:
        pkt[9] = random.randint(0, 255)                     # protocollo a caso
    s.send(eth() + bytes(pkt))
    sent += 1

# 3. Frame ARP e UDP/520 (RIP) troncati o con lunghezze incoerenti: stressa
#    i parser di arp.c e ripd.c (che devono rifiutare pacchetti corti).
for _ in range(500):
    s.send(eth(etype=0x0806) + os.urandom(random.randint(1, 27)))   # ARP tronco
    sent += 1
for _ in range(500):
    ip = bytearray(base)
    ip[9] = 17                                              # proto = UDP
    udp = struct.pack("!HHHH", 520, 520, random.choice([0, 3, 65535]), 0)  # len assurde
    payload = os.urandom(random.randint(0, 40))
    s.send(eth() + bytes(ip) + udp + payload)
    sent += 1

# 4. EtherType arbitrari: verifica che il demux di handle_frame scarti
#    tutto ciò che non è ARP/IPv4.
for _ in range(200):
    s.send(eth(etype=random.randint(0, 0xFFFF)) + os.urandom(50))
    sent += 1

print(f"inviati {sent} frame malformati verso il router")
