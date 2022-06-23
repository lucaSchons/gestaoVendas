/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaovendas.dao;

import gestaovendas.apoio.ConexaoBD;
import gestaovendas.apoio.IDAOT;
import gestaovendas.entidade.Produto;
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
public class ProdutoDAO implements IDAOT<Produto> {

    ResultSet resultadoQ;
    
    @Override
    public String salvar(Produto o) {
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "Insert into produto values"
                    + "(default,"
                    + " '" + o.getDescricao()+ "',"
                    + " '" + o.getReferencia() + "',"
                    + " '" + o.getCategoria() + "',"
                    + " '" + o.getCor() + "',"
                    + " '" + o.getMarca() + "',"
                    + " '" + o.getTamanho() + "',"
                    + " '" + o.getPrecoCusto() + "',"
                    + " '" + o.getPrecoVenda() + "');";
                    
            System.out.println("SQL: " + sql);
            
            int resultado = stm.executeUpdate(sql);
            
            return null;
            
        } catch (Exception e) {
            System.out.println("Erro ao salvar o produto: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Produto o) {
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update produto "
                    + "set descricao = '" + o.getDescricao()+ "', "
                    + "referencia = '" + o.getReferencia()+ "', "
                    + "cor = '" + o.getCor()+ "' "
                    + "tamanho = '" + o.getTamanho()+ "' "
                    + "marca = '" + o.getMarca()+ "' "
                    + "categoria = '" + o.getCategoria()+ "' "
                    + "precocusto = '" + o.getPrecoCusto()+ "' "
                    + "precovenda = '" + o.getPrecoVenda()+ "' "
                    + "where id = '"+ o.getId() + "';";
            
            System.out.println("SQL: " + sql);
            
            int resultado = stm.executeUpdate(sql);
            
            return null;
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o produto: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "delete from produto "
                    + "where id = " + id + ";";
            
            System.out.println("SQL: " + sql);
            
            int resultado = stm.executeUpdate(sql);
            
            return null;
            
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Produto> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Produto> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produto consultarId(int id) {
        Produto produto = null;
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select * "
                    + "from produto "
                    + "where "
                    + "id = '" + id + "';";
            
            System.out.println("SQL: "+ sql);
            
            ResultSet retorno = stm.executeQuery(sql);
            
            if (retorno.next()){
                produto = new Produto();
                produto.setId(retorno.getInt("id"));
                produto.setDescricao(retorno.getString("descricao"));
                produto.setReferencia(retorno.getString("referencia"));
                produto.setCategoria(retorno.getInt("categoria"));
                produto.setCor(retorno.getInt("cor"));
                produto.setMarca(retorno.getInt("marca"));
                produto.setTamanho(retorno.getString("tamanho"));
                produto.setPrecoCusto(retorno.getDouble("preco_custo"));
                produto.setPrecoVenda(retorno.getDouble("preco_venda"));
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar marca: " + e);
        }
        
        return produto;
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Tamanho";
        cabecalho[3] = "Preço de venda";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM produto "
                    + "WHERE descricao ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][5];

        } catch (Exception e) {
            System.out.println("Erro ao consultar a item: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT id, descricao, tamanho, preco_venda FROM produto "
                    + "WHERE descricao ILIKE '%" + criterio + "%' "
                    + "ORDER BY descricao");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");
                dadosTabela[lin][2] = resultadoQ.getString("tamanho");
                dadosTabela[lin][3] = resultadoQ.getDouble("preco_venda");

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
    
    public boolean verificaLetras (String palavra) {
        
        palavra = palavra.replaceAll(" ", "");
        
        if (palavra.trim().matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
            return true;
        } else {
            return false;
        }
    }
    //"^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$"
    //"^(?x)[0-9]*$"
    public boolean verificaNumeros (String palavra) {
        
        palavra = palavra.replaceAll(" ", "");
        
        if (palavra.trim().matches("^[0-9,.]*$")) {
            System.out.println("Número: " + palavra);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean verificaCampos (String a, String b) {
            
            if (a.trim().length() > 0 &&
                b.trim().length() > 0) {
                
                return true;
            } else {
                return false;
            }
    }
}
