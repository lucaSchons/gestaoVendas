/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaovendas.modelo;

import gestaovendas.entidade.Produto;

/**
 *
 * @author lucas
 */
public class OperacaoItem {
    
    int cod;
    int codProduto;
    int quantidade;
    double valor_unitario;

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
     * @return the codProduto
     */
    public int getCodProduto() {
        return codProduto;
    }

    /**
     * @param codProduto the codProduto to set
     */
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valortotal
     */
    public double getValorUnitario() {
        return valor_unitario;
    }

    /**
     * @param valortotal the valortotal to set
     */
    public void setValorUnitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
    
}
