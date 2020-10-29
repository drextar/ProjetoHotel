/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.dao;

import br.com.fatec.model.Servicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author drextar
 */
public class ServicoDAO implements DAO <Servicos> {
    //Variaveis auxiliares
    private Servicos servico;
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
    public boolean inserir(Servicos dado) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO SERVICOS (IDSERVICO, NOMESERVICO, TIPOSERVICO, VALORSERVICO) VALUES (NULL,?,?,?)";
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getNomeServico());
        pst.setString(2, dado.getTipoServico());
        pst.setFloat(3, dado.getValorServico());
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
    public boolean alterar(Servicos dado) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE SERVICOS SET IDSERVICO=?, NOMESERVICO=?, TIPOSERVICO=?, VALORSERVICO=? WHERE IDSERVICO=?";
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setInt(1, dado.getIdServico());
        pst.setString(2, dado.getNomeServico());
        pst.setString(3, dado.getTipoServico());
        pst.setFloat(4, dado.getValorServico());
        pst.setInt(5, dado.getIdServico());
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
    public boolean excluir(Servicos dado) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM SERVICOS "
                + "WHERE IDSERVICO=?";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setInt(1, dado.getIdServico());
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
    public Servicos buscar(Servicos dado) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM SERVICOS "
                + "WHERE IDSERVICO=?";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setInt(1, dado.getIdServico());
        //executar o comando
        rs = pst.executeQuery();
        servico = null;
        //Verifica se achou alguem
        if(rs.next()) { //achou
            //transfere todas as informações da tabela para 
            // o Objeto filme
            servico = new Servicos();
            servico.setIdServico(rs.getInt("IDSERVICO"));
            servico.setNomeServico(rs.getString("NOMESERVICO"));
            servico.setTipoServico(rs.getString("TIPOSERVICO"));
            servico.setValorServico(rs.getFloat("VALORSERVICO"));
        }
        //fecha o resultado
        rs.close();
        //fecha o banco
        Banco.fecharConexao();
        //retorna o que foi encontrado, NULL se nada foi achado
        return servico;
    }

    @Override
    public Collection<Servicos> listar(String criterio) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM SERVICOS ";

        if (criterio.length() != 0) {
            sql += "WHERE " + criterio;
        }

        //abre o banco
        Banco.abreConexao();
        pst = Banco.conexao.prepareStatement(sql);
        //executar comando SQL
        rs = pst.executeQuery();
        Collection<Servicos> servicos = new ArrayList<>();
        while (rs.next()) { //percorre todos os registros
            servico = new Servicos();
            servico.setIdServico(rs.getInt("IDSERVICO"));
            servico.setNomeServico(rs.getString("NOMESERVICO"));
            servico.setTipoServico(rs.getString("TIPOSERVICO"));
            servico.setValorServico(rs.getFloat("VALORSERVICO"));
                        
            servicos.add(servico);
        } 
        rs.close(); //fecha o resultSet
        Banco.fecharConexao();
        return servicos;
    }
    
}
