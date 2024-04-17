package it.unibas.banca.modello;

import lombok.Getter;

@Getter

public class DatiMovimento {
    
    private String tipologia;
    private int numeroOperazioni;

    public DatiMovimento(String tipologia, int numeroOperazioni) {
        this.tipologia = tipologia;
        this.numeroOperazioni = numeroOperazioni;
    }
}
