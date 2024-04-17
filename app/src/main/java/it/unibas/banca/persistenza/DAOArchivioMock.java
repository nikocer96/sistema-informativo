package it.unibas.banca.persistenza;

import it.unibas.banca.modello.Archivio;
import it.unibas.banca.modello.ContoCorrente;
import it.unibas.banca.modello.Costanti;
import it.unibas.banca.modello.Movimento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class DAOArchivioMock implements IDAOArchivioMock {
    
    @Override
    public Archivio carica(String nomeFile) throws DAOException {
        Archivio archivio = new Archivio();
        
        ContoCorrente c1 = new ContoCorrente("OKLPTF", "Rocco Rossi", LocalDate.of(2021, Month.MARCH, 12));
        ContoCorrente c2 = new ContoCorrente("BHYTFG", "Martina Neri", LocalDate.of(2022, Month.AUGUST, 3));
        
        c1.addMovimento(new Movimento(LocalDateTime.of(2021, Month.MARCH, 12, 21, 33), 345, Costanti.BONIFICO));
        c2.addMovimento(new Movimento(LocalDateTime.of(2019, Month.JUNE, 23, 12, 33), 1345, Costanti.BONIFICO));
        c1.addMovimento(new Movimento(LocalDateTime.of(2021, Month.APRIL, 15, 9, 33), 567, Costanti.PAGAMENTO_POS));
        c1.addMovimento(new Movimento(LocalDateTime.of(2018, Month.NOVEMBER, 28, 10, 33), 980, Costanti.BONIFICO));
        
        archivio.addContoCorrente(c1);
        archivio.addContoCorrente(c2);
        
        return archivio;
    }
}
