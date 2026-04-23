package E2_gestionali.GestionaleDischeria.Classi;

import java.util.*;

public class Discheria {
    private final List<Disco> catalogoDischi;
    private final List<Cliente> clienti;

    public Discheria() {
        this.catalogoDischi = new ArrayList<>();
        this.clienti = new ArrayList<>();
    }

    public void aggiungiDisco(Disco disco) {
        catalogoDischi.add(disco);
    }

    public void registraCliente(Cliente cliente) {
        clienti.add(cliente);
    }

    public boolean prendiDisco(int idDisco, int idCliente) {
        Disco disco = null;
        for (Disco d : catalogoDischi) {
            if (d.getId() == idDisco) {
                disco = d;
                break;
            }
        }
        if (disco == null) return false;
        Cliente cliente = null;
        for (Cliente c : clienti) {
            if (c.getId() == idCliente) {
                cliente = c;
                break;
            }
        }
        if (cliente == null) return false;
        cliente.prendiDisco(disco);
        return true;
    }

    public boolean restituisciDisco(int idDisco, int idCliente) {
        Cliente cliente = null;
        for (Cliente c : clienti) {
            if (c.getId() == idCliente) {
                cliente = c;
                break;
            }
        }
        if (cliente == null) return false;
        return cliente.restituisciDisco(idDisco);
    }

}