package it.unibas.banca.modello;

import java.util.Comparator;

public class ComparatoreOrdinamentoNomeCrescente implements Comparator <ContoCorrente> {

    @Override
    public int compare(ContoCorrente o1, ContoCorrente o2) {
        return o1.getNomeCognome().compareTo(o2.getNomeCognome());
    }
}
