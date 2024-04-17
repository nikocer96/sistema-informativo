package it.unibas.banca.persistenza;

import it.unibas.banca.modello.Archivio;

public interface IDAOArchivioMock {

    Archivio carica(String nomeFile) throws DAOException;
    
}
