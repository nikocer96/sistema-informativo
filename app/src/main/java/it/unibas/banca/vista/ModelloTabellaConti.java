package it.unibas.banca.vista;

import it.unibas.banca.modello.ContoCorrente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ModelloTabellaConti extends AbstractTableModel {
    
    private List<ContoCorrente> listaContoCorrenti = new ArrayList<>();

    @Override
    public int getRowCount() {
        return this.listaContoCorrenti.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ContoCorrente contoCorrente = this.listaContoCorrenti.get(rowIndex);
        if (columnIndex == 0) {
            return contoCorrente.getIban();
        }
        if (columnIndex == 1) {
            return contoCorrente.getNomeCognome();
        }
        if (columnIndex == 2) {
            return contoCorrente.getDataConto();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Iban";
        }
        if (column == 1) {
            return "Nome Cognome";
        }
        if (column == 2) {
            return "Data sottoscrizione";
        }
        return "";
    }
    
    public void aggiornaTabella() {
        this.fireTableDataChanged();
    }
}
