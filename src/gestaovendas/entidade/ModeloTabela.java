/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaovendas.entidade;

import gestaovendas.modelo.Operacao;
import gestaovendas.modelo.OperacaoItem;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lucas
 */
public class ModeloTabela extends AbstractTableModel {
    ArrayList<OperacaoItem> dados = new ArrayList<>();
    
    String[] colunas = {"codProduto", "quantidade", "valor"};
    
    @Override
    public int getRowCount() {
        return dados.size();
    }
    
    public String getColumnName(int column){
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return dados.get(linha).getCodProduto();
            case 1:
                return dados.get(linha).getQuantidade();
            case 2:
                return dados.get(linha).getValortotal();
                
        }
        
        return null;
    }
    
    public void addRow(OperacaoItem item){
        this.dados.add(item);
        this.fireTableDataChanged();
    }

	
}