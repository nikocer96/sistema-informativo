package it.unibas.banca.modello;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter

public class Movimento {
    
    private LocalDateTime dataMovimento;
    private int importo;
    private String tipologia;

    public Movimento(LocalDateTime dataMovimento, int importo, String tipologia) {
        this.dataMovimento = dataMovimento;
        this.importo = importo;
        this.tipologia = tipologia;
    }
}
