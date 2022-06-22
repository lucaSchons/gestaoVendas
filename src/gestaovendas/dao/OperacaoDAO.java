
package gestaovendas.dao;

import gestaovendas.apoio.ConexaoBD;
import gestaovendas.modelo.Operacao;
import gestaovendas.modelo.OperacaoItem;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lucas
 */
public class OperacaoDAO {
    ResultSet resultadoQ;
     
     
    public String inserir(Operacao o) {
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
           
            
            String sql = "Insert into operacao values"
                    + "(default,"
                    + " '" + o.getTipo()+ "',"
                    + " '" + o.getCodCliente() + "',"
                    + " '" + new java.sql.Date(o.getData().getTime()) +"',"
                    + " '" + o.getValor_total() + "');";
                    
            System.out.println("SQL: " + sql);
            
            stm.executeUpdate(sql);

            OperacaoItemDAO operacaoItemDAO = new OperacaoItemDAO();
            for(OperacaoItem item : o.getListaOperacaoItem()){
                operacaoItemDAO.inserir(item, getUltimoCodigo());
            }
            String resul = "deu certo";
            System.out.println("entrouuuu");
            
            return resul;
            
        } catch (Exception e) {
            System.out.println("Erro ao salvar o produto: " + e);
            return e.toString();
            
        }
    }
    
    
    public int getUltimoCodigo() throws Exception {
        Integer codigo = null;
        
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select max(id) as id from operacao";

            System.out.println("SQL: " + sql);

            resultadoQ = stm.executeQuery(sql);

            while(resultadoQ.next()){
                codigo = resultadoQ.getInt("id");
            }
            
            stm.close();
            
            return codigo;
        
        } catch (Exception e) {
            System.out.println("Erro ao salvar o produto: " + e);
            throw e;
        }
    }
}
