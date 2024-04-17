package it.unibas.banca.controllo;

import it.unibas.banca.Applicazione;
import it.unibas.banca.modello.Archivio;
import it.unibas.banca.modello.Costanti;
import it.unibas.banca.persistenza.DAOException;
import it.unibas.banca.persistenza.IDAOArchivioMock;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import lombok.Getter;

@Getter

public class ControlloFrame {
    
    private Action azioneEsci = new AzioneEsci();
    private Action azioneCarica = new AzioneCarica();
    
    public class AzioneCarica extends AbstractAction {
        
        public AzioneCarica() {
            this.putValue(NAME, "Carica");
            this.putValue(SHORT_DESCRIPTION, "Carica l'archivio mock");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IDAOArchivioMock daoArchivio = Applicazione.getInstance().getDaoArchivioMock();
            try {
                Archivio archivio = daoArchivio.carica("");
                Applicazione.getInstance().getModello().putBean(Costanti.ARCHIVIO, archivio);
                Applicazione.getInstance().getFrame().mostraMessaggio("Caricato l'archivio contenente " + archivio.getListaContoCorrenti().size() + " conto correnti");
                Applicazione.getInstance().getControlloPrincipale().getAzioneCerca().setEnabled(true);
            } catch(DAOException ex) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrore("Impossibile caricare l'applicazione");
            }
        }
        
    }
    
    public class AzioneEsci extends AbstractAction {
        
        public AzioneEsci() {
            this.putValue(NAME, "Esci");
            this.putValue(SHORT_DESCRIPTION, "Esci dall'applicazione");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    } 
}
