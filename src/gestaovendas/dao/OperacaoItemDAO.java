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
public class OperacaoItemDAO {
    ResultSet resultadoQ;
     
     
    public void inserir(OperacaoItem operacaoItem, int cod_operacao) throws Exception {
        try{
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into operacaoitem values"
                    + "(default,"
                    + " '" + cod_operacao+ "',"
                    + " '" + operacaoItem.getCodProduto() + "',"
                    + " '" + operacaoItem.getQuantidade() + "',"
                    + " '" + operacaoItem.getValorUnitario()+"');";
                    
            System.out.println("SQL: " + sql);
            
            stm.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao salvar o produto: " + e);
            throw e;
        }
    }
}
