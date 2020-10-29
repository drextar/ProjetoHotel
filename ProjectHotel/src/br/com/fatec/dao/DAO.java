/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.dao;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author drextar
 * @param <T>
 */
public interface DAO <T> {
    public boolean inserir(T dado) throws SQLException,
                                          ClassNotFoundException;
    public boolean alterar(T dado) throws SQLException,
                                          ClassNotFoundException;
    public boolean excluir(T dado) throws SQLException,
                                          ClassNotFoundException;
    public T buscar(T dado) throws SQLException,
                                   ClassNotFoundException;
    public Collection<T> listar(String criterio) 
                            throws SQLException,
                            ClassNotFoundException;
}
