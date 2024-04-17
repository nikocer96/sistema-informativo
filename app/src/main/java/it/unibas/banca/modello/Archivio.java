package it.unibas.banca.modello;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter

public class Archivio {
    
    private List<ContoCorrente> listaContoCorrenti = new ArrayList<>();
    
    public void addContoCorrente(ContoCorrente contoCorrente) {
        this.listaContoCorrenti.add(contoCorrente);
    }
    
    public List<ContoCorrente> cercaContoCorrenti(String ordinamento) {
        List<ContoCorrente> listaFiltrataConto = new ArrayList<>();
        LocalDate dataOdierna = LocalDate.now();
        for (ContoCorrente contoCorrente : listaContoCorrenti) {
            if (contoCorrente.getDataConto().isBefore(dataOdierna)) {
                listaFiltrataConto.add(contoCorrente);
            }
        }
        if (ordinamento.equalsIgnoreCase(Costanti.DATA_CRESCENTE)) {
            Collections.sort(listaFiltrataConto, new ComparatoreOrdinamentoDataCrescente());
        } else if (ordinamento.equalsIgnoreCase(Costanti.NOME_CRESCENTE)) {
            Collections.sort(listaFiltrataConto, new ComparatoreOrdinamentoNomeCrescente());
        }
        return listaFiltrataConto;
    }
    
    public List<DatiMovimento> cercaDatiMovimento(List<Movimento> listaMovimentiFiltrata) {
        List<DatiMovimento> listaDatiMovimento = new ArrayList<>();
        Map<String, Integer> mappaPerTipologia = new HashMap<>();
        for (Movimento movimento : listaMovimentiFiltrata) {
            String tipologia = movimento.getTipologia();
            Integer occorrenze = mappaPerTipologia.get(tipologia);
            if (occorrenze == null) {
                mappaPerTipologia.put(tipologia, 1);
            } else {
                mappaPerTipologia.put(tipologia, occorrenze + 1);
            }
        }
        for (String tipologia : mappaPerTipologia.keySet()) {
            Integer numeroOccorrenze = mappaPerTipologia.get(tipologia);
            DatiMovimento datiMovimento = new DatiMovimento(tipologia, numeroOccorrenze);
            listaDatiMovimento.add(datiMovimento);
        }
        Collections.sort(listaDatiMovimento, new ComparatoreOrdinamentoOperazioniDecrescente());
        return listaDatiMovimento;
    }
}
