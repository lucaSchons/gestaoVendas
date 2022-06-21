/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaovendas.apoio;

import java.util.ArrayList;

/**
 *
 * @author pretto
 */
public interface IDAOT <T> {

    public String salvar(T o);
 
    public String atualizar(T o);

    public String excluir(int id);

    public ArrayList<T> consultarTodos();

    public ArrayList<T> consultar(String criterio);

    public T consultarId(int id);
}
