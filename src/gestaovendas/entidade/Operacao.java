package gestaovendas.modelo;

import gestaovendas.entidade.Cliente;
import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class Operacao {
    int cod;
    String tipo;
    int codCliente;
    Date data;
    double valor_total;
    ArrayList<OperacaoItem> listaOperacaoItem = new ArrayList<>();

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the codCliente
     */
    public int getCodCliente() {
        return codCliente;
    }

    /**
     * @param codCliente the codCliente to set
     */
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the listaOperacaoItem
     */
    public ArrayList<OperacaoItem> getListaOperacaoItem() {
        return listaOperacaoItem;
    }

    /**
     * @param listaOperacaoItem the listaOperacaoItem to set
     */
    public void setListaOperacaoItem(ArrayList<OperacaoItem> listaOperacaoItem) {
        this.listaOperacaoItem = listaOperacaoItem;
    }
    
    //Metodo para adiconar item por item na operação
    public void adicionaOperacaoItem(OperacaoItem operacaoItem){
        listaOperacaoItem.add(operacaoItem);
    }

    /**
     * @return the valor_total
     */
    public double getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }
    
    
}
