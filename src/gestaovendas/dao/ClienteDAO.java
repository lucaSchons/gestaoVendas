/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaovendas.dao;

import gestaovendas.apoio.ConexaoBD;
import gestaovendas.apoio.IDAOT;
import gestaovendas.entidade.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Will
 */
public class ClienteDAO implements IDAOT<Cliente> {
    
    
    private PreparedStatement pstm;
    private Connection conn = ConexaoBD.getInstance().getConnection();
    private Statement st;
    private ResultSet rs = null;
    private ArrayList<Cliente> lista = new ArrayList<>();
   
    
    @Override
    public String salvar(Cliente cliente) {
        
        String sql = "INSERT INTO cliente (nome, data_nascimento, endereco, telefone, email, rg, cpf, sexo ) VALUES (?,?,?,?,?, ?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setDate(2, retornaDateSql(cliente.getDataNascimento()));
            pstm.setString(3, cliente.getEndereco());
            pstm.setString(4, cliente.getTelefone());
            pstm.setString(5, cliente.getEmail());
            pstm.setString(6, cliente.getRg());
            pstm.setString(7, cliente.getCpf());
            pstm.setString(8,String.valueOf(cliente.getSexo()));
            
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        finally {
           try {
               pstm.close();
           } catch (SQLException ex) {
               Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return null;
    }

    @Override
    public String atualizar(Cliente cliente) {
        String retorno = "erro";
        try {
            String sql = "UPDATE cliente SET "   +
                    "nome     =  '"          + cliente.getNome()             + "' , "  +
                    "data_nascimento =  '"   + cliente.getDataNascimento()   + "' , "  +
                    "endereco   =  '"        + cliente.getEndereco()         + "' , "  +
                    "telefone   =  '"        + cliente.getTelefone()         + "' , "  +
                     "email   =  '"          + cliente.getEmail()            + "' , "  +
                     "rg      =  '"          + cliente.getRg()               + "' , "  +
                    "cpf      =  '"          + cliente.getCpf()              + "' , "  +
                    "sexo =  '"              + cliente.getSexo()             + "'  "   + 
                    " WHERE id =  "          + cliente.getId();
           
             st = conn.createStatement();
            
            if (st.executeUpdate(sql) == -1) {
                retorno = null;
            } else { 
                return "erro";
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @Override
    public String excluir(int id) {
        String retorno = "erro";
        String sql = "DELETE FROM cliente WHERE id = " + id;
        try {    
            pstm = conn.prepareStatement(sql);
            if (pstm.execute(sql)) {
                retorno = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  retorno;
    }

    @Override
    public ArrayList<Cliente> consultarTodos() {
        String sql = "SELECT * FROM cliente ORDER BY id";
        try {
            pstm = conn.prepareStatement(sql);
            rs  = pstm.executeQuery();
            while(rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDataNascimento(rs.getDate("data_nascimento"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setRg(rs.getString("rg"));
                c.setCpf(rs.getString("cpf"));
                c.setSexo(rs.getString("sexo").charAt(0));
                
                lista.add(c);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
       
        return lista;
    }
    

    @Override
    public ArrayList<Cliente> consultar(String criterio) {
            String sql = "SELECT * FROM cliente WHERE nome LIKE '%" + criterio + "%'";
        try {
            pstm = conn.prepareStatement(sql);
            rs  = pstm.executeQuery();
            while(rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDataNascimento(rs.getDate("data_nascimento"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setRg(rs.getString("rg"));
                c.setCpf(rs.getString("cpf"));
                c.setSexo(rs.getString("sexo").charAt(0));
                
                lista.add(c);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
       
        return lista;
    }

    @Override
    public Cliente consultarId(int id) {
       Cliente c = new Cliente();
        String sql = "SELECT * FROM cliente WHERE id = " + id;
        try {
            pstm = conn.prepareStatement(sql);
            rs  = pstm.executeQuery();
            while(rs.next()) {
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("e_mail"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
       
        return c;
    }
    
    private java.sql.Date retornaDateSql(java.util.Date utilStartDate) {
       java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
       return sqlStartDate;
    }
}
