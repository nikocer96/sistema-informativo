package it.unibas.banca.vista;

import it.unibas.banca.modello.DatiMovimento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ModelloTabellaMovimento extends AbstractTableModel {
    
    private List<DatiMovimento> listaDatiMovimento = new ArrayList<>();

    @Override
    public int getRowCount() {
        return this.listaDatiMovimento.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DatiMovimento datiMovimento = this.listaDatiMovimento.get(rowIndex);
        if (columnIndex == 0) {
            return datiMovimento.getTipologia();
        }
        if (columnIndex == 1) {
            return datiMovimento.getNumeroOperazioni();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Tipologia";
        }
        if (column == 1) {
            return "Numero Operazioni";
        }
        return "";
    }
    
    public void aggiornaTabella() {
        this.fireTableDataChanged();
    }
}
