package it.unibas.banca.modello;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class ContoCorrente {
    
    private String iban;
    private String nomeCognome;
    private LocalDate dataConto;
    private List<Movimento> listaMovimenti = new ArrayList<>();

    public ContoCorrente(String iban, String nomeCognome, LocalDate dataConto) {
        this.iban = iban;
        this.nomeCognome = nomeCognome;
        this.dataConto = dataConto;
    }
    
    public void addMovimento(Movimento movimento) {
        this.listaMovimenti.add(movimento);
    }
}
