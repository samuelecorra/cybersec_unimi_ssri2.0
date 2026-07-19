package E2_gestionali.GestionaleDischeria.Classi;

import java.util.*;

public class Discheria {
    private final List<Disco> catalogoDischi;
    private final List<Cliente> clienti;

    public Discheria() {
        this.catalogoDischi = new ArrayList<>();
        this.clienti = new ArrayList<>();
    }

    public boolean aggiungiDisco(Disco disco) {
        if (disco == null || discoRegistrato(disco.getId())) {
            return false;
        }
        catalogoDischi.add(disco);
        return true;
    }

    public boolean registraCliente(Cliente cliente) {
        if (cliente == null || trovaCliente(cliente.getId()) != null) {
            return false;
        }
        clienti.add(cliente);
        return true;
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
        catalogoDischi.remove(disco);
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
        Disco restituito = cliente.restituisciDisco(idDisco);
        if (restituito == null) return false;
        catalogoDischi.add(restituito);
        return true;
    }

    private Cliente trovaCliente(int idCliente) {
        for (Cliente cliente : clienti) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    private boolean discoRegistrato(int idDisco) {
        for (Disco disco : catalogoDischi) {
            if (disco.getId() == idDisco) {
                return true;
            }
        }
        return clienti.stream().anyMatch(cliente -> cliente.haDisco(idDisco));
    }
}
