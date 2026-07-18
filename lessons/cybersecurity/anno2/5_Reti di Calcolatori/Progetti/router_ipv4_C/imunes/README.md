# Demo su IMUNES

Porting della topologia del progetto sull'emulatore **IMUNES** (documentato
nell'Appendice A della relazione). La stessa topologia della demo a network
namespaces gira in IMUNES: `crouter` nel nodo R1, FRR nei router R2/R3/R4.

```
 pc1 ──(10.0.1.0/24)── R1[crouter] ══(10.0.12.0/30)══ R2[FRR] ══(10.0.23.0/30)══ R3[FRR] ──(10.0.4.0/24)── pc2
                            ║                                                     ║
                            ╚════════(10.0.14.0/30)══ R4[FRR] ══(10.0.34.0/30)═══╝
```

## Prerequisiti (una volta sola, su Ubuntu con Docker)

```sh
sudo apt-get install -y openvswitch-switch tk tcllib xterm ethtool
git clone https://github.com/imunes/imunes.git ~/imunes
cd ~/imunes && sudo make install
sudo imunes -p          # scarica l'immagine Docker dei nodi (imunes/template, con FRR)
```

## Esecuzione della demo

1. Compila crouter: dalla cartella del progetto, `make` (produce `build/crouter`).
2. Avvia IMUNES con la topologia:
   ```sh
   sudo imunes imunes/topo.imn
   ```
3. Nella GUI, premi **Execute** (▶) per istanziare l'esperimento.
4. Deploya crouter nel nodo R1 e imposta le rotte:
   ```sh
   bash imunes/deploy_crouter.sh
   ```
5. Doppio clic sul nodo **R1-crouter** → nel suo terminale lancia (una sola volta):
   ```sh
   /crouter -c /r1.conf -v
   ```
   crouter converge (compaiono le rotte `[R]` apprese via RIP).
6. Doppio clic su **pc1** → verifica la connettività attraverso crouter:
   ```sh
   ping -c 4 10.0.4.100
   traceroute -n -I 10.0.4.100
   ```

## Note

- R1 è un nodo `pc`: le sue interfacce **non hanno IP a livello kernel**, così è
  crouter (in userspace) a possederli — esattamente come nell'ambiente a namespaces.
- Avviare crouter **una sola volta**: istanze multiple inoltrano lo stesso pacchetto
  più volte (repliche `DUP!` nel ping).
- Il `traceroute` in modalità ICMP (`-I`) raggiunge pc2 come ultimo hop; quello UDP
  classico mostra la destinazione come `*` perché i nodi `pc` di IMUNES non rispondono
  ai probe UDP.
