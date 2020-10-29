/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.dao;

import br.com.fatec.model.Quarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

/**
 *
 * @author drextar
 */
public class QuartoDAO implements DAO <Quarto>{
    //Variaveis auxiliares
    private Quarto quarto;
    //para conexao
    private Connection conexao;
    //para comandos DML não preparados
    private Statement st;
    //para comandos DML preparados
    private PreparedStatement pst;
    //Repsenta os dados da tabela
    private ResultSet rs;
    //referencia para o banco de dados

    @Override
    public boolean inserir(Quarto dado) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO QUARTO (NUMQUARTO, TIPOQUARTO, "
                + "DESCRICAO, VALORDIARIA, STATUSQUARTO) values (?,?,?,?,?)";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getNumQuarto());
        pst.setString(2,dado.getTipoQuarto());
        pst.setString(3, dado.getDescricao());
        pst.setFloat(4, dado.getValorDiaria());
        pst.setString(5, dado.getStatus());
        //executar o comando
        if(pst.executeUpdate() > 0) {
            //fecha a conexao
            Banco.fecharConexao();
            //retorna o resultado
            return true;
        }
        else {
            Banco.fecharConexao();
            return false;
        }
    }

    @Override
    public boolean alterar(Quarto dado) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE QUARTO SET NUMQUARTO=?,TIPOQUARTO=?,DESCRICAO=?,VALORDIARIA=?,STATUSQUARTO=? WHERE NUMQUARTO=?";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getNumQuarto());
        pst.setString(2,dado.getTipoQuarto());
        pst.setString(3, dado.getDescricao());
        pst.setFloat(4, dado.getValorDiaria());
        pst.setString(5, dado.getStatus());
        pst.setString(6, dado.getNumQuarto());
        //executar o comando
        if(pst.executeUpdate() > 0) {
            //fecha a conexao
            Banco.fecharConexao();
            //retorna o resultado
            return true;
        }
        else {
            Banco.fecharConexao();
            return false;
        }
    }

    @Override
    public boolean excluir(Quarto dado) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quarto buscar(Quarto dado) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM QUARTO "
                + "WHERE NUMQUARTO=?";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getNumQuarto());
        //executar o comando
        rs = pst.executeQuery();
        quarto = null;
        //Verifica se achou alguem
        if(rs.next()) { //achou
            //transfere todas as informações da tabela para 
            // o Objeto filme
            quarto = new Quarto();
            quarto.setNumQuarto(rs.getString("NUMQUARTO"));
            quarto.setTipoQuarto(rs.getString("TIPOQUARTO"));
            quarto.setDescricao(rs.getString("DESCRICAO"));
            quarto.setValorDiaria(rs.getFloat("VALORDIARIA"));
            quarto.setStatus(rs.getString("STATUSQUARTO"));
        }
        //fecha o resultado
        rs.close();
        //fecha o banco
        Banco.fecharConexao();
        //retorna o que foi encontrado, NULL se nada foi achado
        return quarto;
    }

    @Override
    public Collection<Quarto> listar(String criterio) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
