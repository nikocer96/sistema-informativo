package it.unibas.banca.controllo;

import it.unibas.banca.Applicazione;
import it.unibas.banca.modello.Archivio;
import it.unibas.banca.modello.ContoCorrente;
import it.unibas.banca.modello.Costanti;
import it.unibas.banca.modello.DatiMovimento;
import it.unibas.banca.modello.Movimento;
import it.unibas.banca.vista.VistaPrincipale;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import lombok.Getter;

@Getter

public class ControlloPrincipale {

    private Action azioneCerca = new AzioneCerca();
    private Action azioneSeleziona = new AzioneSeleziona();
    
    public class AzioneSeleziona extends AbstractAction {
        
        public AzioneSeleziona() {
            this.putValue(NAME, "Seleziona");
            this.putValue(SHORT_DESCRIPTION, "Seleziona un conto corrente");
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<ContoCorrente> listaFiltrataConti = (List<ContoCorrente>) Applicazione.getInstance().getModello().getBean(Costanti.LISTA_FILTRATA_CONTO);
            int selezionaRiga = Applicazione.getInstance().getVistaPrincipale().getSelezionaRiga();
            if (selezionaRiga == -1) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore("Selezionare prima una riga");
                return;
            }
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            ContoCorrente contoCorrenteSelezionato = listaFiltrataConti.get(selezionaRiga);
            Applicazione.getInstance().getModello().putBean(Costanti.CONTO_CORRENTE_SELEZIONATO, contoCorrenteSelezionato);
            List<Movimento> listaMovimentiFiltrata = contoCorrenteSelezionato.getListaMovimenti();
            List<DatiMovimento> listaDatiMovimento = archivio.cercaDatiMovimento(listaMovimentiFiltrata);
            Applicazione.getInstance().getModello().putBean(Costanti.LISTA_DATI_MOVIMENTO, listaDatiMovimento);
            Applicazione.getInstance().getVistaDettagliMovimento().visualizza();
        }
    }

    public class AzioneCerca extends AbstractAction {

        public AzioneCerca() {
            this.putValue(NAME, "Cerca");
            this.putValue(SHORT_DESCRIPTION, "Cerca i conto correnti");
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            VistaPrincipale vistaPrincipale = Applicazione.getInstance().getVistaPrincipale();
            String ordinamento = Costanti.DATA_CRESCENTE;
            if (vistaPrincipale.isOrdinamentoDataCrescente()) {
                ordinamento = Costanti.DATA_CRESCENTE;
            } else if (vistaPrincipale.isOrdinamentoNomeCrescente()) {
                ordinamento = Costanti.NOME_CRESCENTE;
            }
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            List<ContoCorrente> listaFiltrataConto = archivio.cercaContoCorrenti(ordinamento);
            Applicazione.getInstance().getModello().putBean(Costanti.LISTA_FILTRATA_CONTO, listaFiltrataConto);
            Applicazione.getInstance().getVistaPrincipale().aggiorna();
            Applicazione.getInstance().getControlloPrincipale().azioneSeleziona.setEnabled(true);
        }
    }
}
