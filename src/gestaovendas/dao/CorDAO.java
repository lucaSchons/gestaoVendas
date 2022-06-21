/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaovendas.dao;

import gestaovendas.apoio.ConexaoBD;
import gestaovendas.apoio.IDAOT;
import gestaovendas.entidade.Cor;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ariel Yuri
 */
public class CorDAO implements IDAOT<Cor> {

    ResultSet resultadoQ;
    
    @Override
    public String salvar(Cor o) {
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "Insert into cor values"
                    + "(default,"
                    + " '" + o.getDescricao()+ "');";
            
            System.out.println("SQL: " + sql);
            
            int resultado = stm.executeUpdate(sql);
            
            return null;
            
        } catch (Exception e) {
            System.out.println("Erro ao salvar cor: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Cor o) {
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update cor "
                    + "set descricao = '" + o.getDescricao()+ "' "
                    + "where id = '"+ o.getId() + "';";;
            
            System.out.println("SQL: " + sql);
            
            int resultado = stm.executeUpdate(sql);
            
            return null;
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cor: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "delete from cor "
                    + "where id = " + id + ";";
            
            System.out.println("SQL: " + sql);
            
            int resultado = stm.executeUpdate(sql);
            
            return null;
            
        } catch (Exception e) {
            System.out.println("Erro ao excluir cor: " + e);
            return e.toString();
        }
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "Código";
        cabecalho[1] = "Cor";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM cor "
                    + "WHERE descricao ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][2];

        } catch (Exception e) {
            System.out.println("Erro ao consultar o cor: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT id, descricao FROM cor "
                    + "WHERE descricao ILIKE '%" + criterio + "%' "
                    + "ORDER BY descricao");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
                    //return ImageIcon.class;
                    //return Boolean.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(80);
                    column.setMaxWidth(80);
                    column.setMinWidth(80);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                }
                return this;
            }
        });
    }

    @Override
    public ArrayList<Cor> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Cor> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cor consultarId(int id) {
        Cor cor = null;
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select * "
                    + "from cor "
                    + "where "
                    + "id = '" + id + "';";
            
            System.out.println("SQL: "+ sql);
            
            ResultSet retorno = stm.executeQuery(sql);
            
            if (retorno.next()){
                cor = new Cor();
                cor.setId(retorno.getInt("id"));
                cor.setDescricao(retorno.getString("descricao"));
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar cor: " + e);
        }
        
        return cor;
    }
    
    public boolean verificaLetras (String palavra) {
        
        palavra = palavra.replaceAll(" ", "");
        
        if (palavra.trim().matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean verificaCampos (String a) {
            
            if (a.trim().length() > 0) {
                
                return true;
                
            } else {
                return false;
            }
    }
}
