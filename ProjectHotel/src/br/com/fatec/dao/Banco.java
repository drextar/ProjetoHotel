/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.dao;

import java.sql.*;

/**
 *
 * @author drextar
 */
public class Banco {
    public static String servidor, nomeBanco, 
                         usuario, senha;
    public static int porta;
    public static Connection conexao=null;
    
    //rotina executada quando a classe é ativada
    static {
        servidor = "localhost";
        nomeBanco = "hotel";
        usuario = "root";
        senha = "";
        porta = 3306;
    }
    
    //metodos 
    public static void abreConexao() throws SQLException,
                                            ClassNotFoundException {

        String url = "jdbc:mysql://" + servidor +
                     ":" + porta +
                     "/" + nomeBanco;

        //Carrega o Driver
        Class.forName("com.mysql.jdbc.Driver");
        conexao = DriverManager.getConnection(url, 
                            usuario, senha);
    }
    
    public static void fecharConexao() throws SQLException {
        conexao.close();
    }
}
