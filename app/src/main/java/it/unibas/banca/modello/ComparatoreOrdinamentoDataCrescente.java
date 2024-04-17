package it.unibas.banca.modello;

import java.util.Comparator;

public class ComparatoreOrdinamentoDataCrescente implements Comparator<ContoCorrente> {

    @Override
    public int compare(ContoCorrente o1, ContoCorrente o2) {
        return o1.getDataConto().compareTo(o2.getDataConto());
    } 
}
