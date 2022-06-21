
package gestaovendas;

import gestaovendas.telas.JFrameTela_Inicial;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class GestaoVendas {

    public static Connection conexao;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (abrirConexao()){
            System.out.println("Conexão realizada com sucesso...");
            new JFrameTela_Inicial().setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Erooooou");
        }
    }
    
    private static boolean abrirConexao(){
        try {
            String dbdriver = "org.postgresql.Driver";
            String dburl = "jdbc:postgresql://localhost:5432/gestaoVendas";
            String dbuser = "postgres";
            String dbsenha = "postgres";

            // Carrega Driver do Banco de Dados
            Class.forName(dbdriver);

            if (dbuser.length() != 0) // conexão COM usuário e senha
            {
                conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
            } else // conexão SEM usuário e senha
            {
                conexao = DriverManager.getConnection(dburl);
            }

            return true;

        } catch (Exception e) {
            System.err.println("Erro ao tentar conectar: " + e);
            return false;
        }
    }
    
}
