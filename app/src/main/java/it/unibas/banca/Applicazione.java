package it.unibas.banca;

import it.unibas.banca.controllo.ControlloDettagliMovimento;
import it.unibas.banca.controllo.ControlloFrame;
import it.unibas.banca.controllo.ControlloPrincipale;
import it.unibas.banca.modello.Modello;
import it.unibas.banca.persistenza.DAOArchivioMock;
import it.unibas.banca.persistenza.IDAOArchivioMock;
import it.unibas.banca.vista.Frame;
import it.unibas.banca.vista.VistaDettagliMovimento;
import it.unibas.banca.vista.VistaPrincipale;
import javax.swing.SwingUtilities;
import lombok.Getter;

@Getter

public class Applicazione {
    
    private static Applicazione singleton = new Applicazione();
    
    public static Applicazione getInstance() {
        return singleton;
    }

    private Applicazione() {
    }
    
    private Modello modello;
    private ControlloPrincipale controlloPrincipale;
    private ControlloDettagliMovimento controlloDettagliMovimento;
    private ControlloFrame controlloFrame;
    private IDAOArchivioMock daoArchivioMock;
    private VistaPrincipale vistaPrincipale;
    private VistaDettagliMovimento vistaDettagliMovimento;
    private Frame frame;
    
    public void inizializza() {
        this.modello = new Modello();
        this.controlloPrincipale = new ControlloPrincipale();
        this.controlloDettagliMovimento = new ControlloDettagliMovimento();
        this.controlloFrame = new ControlloFrame();
        this.daoArchivioMock = new DAOArchivioMock();
        this.vistaPrincipale = new VistaPrincipale();
        this.vistaDettagliMovimento = new VistaDettagliMovimento(frame, true);
        this.frame = new Frame();
        this.vistaPrincipale.inizializza();
        this.vistaDettagliMovimento.inizializza();
        this.frame.inizializza();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Applicazione.getInstance().inizializza();
            }
        });
    }
}
