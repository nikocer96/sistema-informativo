package it.unibas.banca.controllo;

import it.unibas.banca.Applicazione;
import it.unibas.banca.modello.Archivio;
import it.unibas.banca.modello.ContoCorrente;
import it.unibas.banca.modello.Costanti;
import it.unibas.banca.modello.DatiMovimento;
import it.unibas.banca.modello.Movimento;
import it.unibas.banca.vista.VistaDettagliMovimento;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.zip.DataFormatException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import lombok.Getter;

@Getter

public class ControlloDettagliMovimento {

    private Action azioneAggiungi = new AzioneAggiungi();

    public class AzioneAggiungi extends AbstractAction {

        public AzioneAggiungi() {
            this.putValue(NAME, "Aggiungi");
            this.putValue(SHORT_DESCRIPTION, "Aggiungi nuovo movimento");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaDettagliMovimento vistaDettagliMovimento = Applicazione.getInstance().getVistaDettagliMovimento();
            String tipologia = vistaDettagliMovimento.getTipologia();
            String importo = vistaDettagliMovimento.getImporto();
            String anno = vistaDettagliMovimento.getAnno();
            String mese = vistaDettagliMovimento.getMese();
            String giorno = vistaDettagliMovimento.getGiorno();
            String ora = vistaDettagliMovimento.getOra();
            String minuti = vistaDettagliMovimento.getMinuti();
            String errori = convalida(tipologia, importo, anno, mese, giorno, ora, minuti);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore(errori);
                return;
            }
            int interoAnno = Integer.parseInt(anno);
            int interoMese = Integer.parseInt(mese);
            int interoGiorno = Integer.parseInt(giorno);
            int interoOra = Integer.parseInt(ora);
            int interoMinuti = Integer.parseInt(minuti);
            int interoImporto = Integer.parseInt(importo);
            LocalDateTime data = LocalDateTime.of(interoAnno, interoMese, interoGiorno, interoOra, interoMinuti);
            String erroriData = convalidaErroriData(data);
            if (!erroriData.isEmpty()) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore(erroriData);
                return;
            }
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            ContoCorrente contoCorrenteSelezionato = (ContoCorrente) Applicazione.getInstance().getModello().getBean(Costanti.CONTO_CORRENTE_SELEZIONATO);
            Movimento nuovoMovimento = new Movimento(data, interoImporto, tipologia);
            contoCorrenteSelezionato.addMovimento(nuovoMovimento);
            //TODO IMPORTANTE METTERLO IN QUESTO PUNTO L'AGGIUNTA DEL NUOVO MOVIMENTO AL CONTO CORRENTE SELEZIONATO
            List<DatiMovimento> listaDatiMovimento = archivio.cercaDatiMovimento(contoCorrenteSelezionato.getListaMovimenti());
            Applicazione.getInstance().getModello().getReplace(Costanti.LISTA_DATI_MOVIMENTO, listaDatiMovimento);
            vistaDettagliMovimento.aggiornaDati();
        }
        
        public String convalidaErroriData(LocalDateTime data) {
            StringBuilder erroriData = new StringBuilder();
            if (data.getDayOfWeek().getValue() == 6 || data.getDayOfWeek().getValue() == 7) {
                erroriData.append("La banca e' chiusa di sabato e di domenica \n");
            }
            if (data.getHour() < 8) {
                erroriData.append("La banca apre alle 8 e 30 \n");
            }
            if (data.getHour() == 8 && data.getMinute() < 30) {
                erroriData.append("La banca apre alle 8 e 30 \n");
            }
            if (data.getHour() > 20) {
                erroriData.append("La banca chiude alle 20 e 30 \n");
            }
            if (data.getHour() == 20 && data.getMinute() > 30) {
                erroriData.append("La banca chiude alle 20 e 30 \n");
            }
            return erroriData.toString();
        }

        public String convalida(String tipologia, String importo, String anno, String mese, String giorno, String ora, String minuti) {
            StringBuilder errori = new StringBuilder();
            if (tipologia.trim().isEmpty()) {
                errori.append("Il campo tipologia e' obbligatorio \n");
            }
            if (importo.trim().isEmpty()) {
                errori.append("Il campo importo e' obbligatorio \n");
            } else {
                try {
                    int interoImporto = Integer.parseInt(importo);
                } catch (NumberFormatException ex) {
                    errori.append("L'importo deve essere un intero \n");
                }
            }
            if (anno.trim().isEmpty()) {
                errori.append("Il campo anno e' obbligatorio \n");
            }
            if (mese.trim().isEmpty()) {
                errori.append("Il campo mese e' obbligatorio \n");
            }
            if (giorno.trim().isEmpty()) {
                errori.append("Il campo giorno e' obbligatorio \n");
            }
            if (ora.trim().isEmpty()) {
                errori.append("Il campo ora e' obbligatorio \n");
            }
            if (minuti.trim().isEmpty()) {
                errori.append("Il campo minuti e' obbligatorio \n");
            }
            try {
                int interoAnno = Integer.parseInt(anno);
                int initeroMese = Integer.parseInt(mese);
                int interoGiorno = Integer.parseInt(giorno);
                int interoOra = Integer.parseInt(ora);
                int interoMinuti = Integer.parseInt(minuti);
            } catch (NumberFormatException ex) {
                errori.append("La data non e' corretta \n");
            }
            return errori.toString();
        }
    }
}
