package it.unibas.banca.modello;

import java.util.Comparator;

public class ComparatoreOrdinamentoOperazioniDecrescente implements Comparator<DatiMovimento> {

    @Override
    public int compare(DatiMovimento o1, DatiMovimento o2) {
        return o2.getNumeroOperazioni() - o1.getNumeroOperazioni();
    } 
}
