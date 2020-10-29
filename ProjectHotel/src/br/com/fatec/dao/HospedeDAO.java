/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.dao;
import br.com.fatec.model.Hospede;
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
public class HospedeDAO implements DAO <Hospede> {
    //Variaveis auxiliares
    private Hospede hospede;
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
    public boolean inserir(Hospede dado) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO HOSPEDE (CPF, RG, "
                + "NOME, EMAIL, SEXO, DATANASC, TELEFONE) values (?,?,?,?,?,?,?)";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getCpf());
        pst.setString(2,dado.getRg());
        pst.setString(3, dado.getNome());
        pst.setString(4, dado.getEmail());
        pst.setString(5, dado.getSexo());
        pst.setString(6, dado.getDataNasc());
        pst.setString(7, dado.getTel());
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
    public boolean alterar(Hospede dado) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE HOSPEDE SET CPF=?, "
                + "RG=?, NOME=?, EMAIL=?, "
                + "SEXO=?, DATANASC=?, TELEFONE=? "
                + "WHERE CPF=?";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getCpf());
        pst.setString(2,dado.getRg());
        pst.setString(3, dado.getNome());
        pst.setString(4, dado.getEmail());
        pst.setString(5, dado.getSexo());
        pst.setString(6, dado.getDataNasc());
        pst.setString(7, dado.getTel());
        pst.setString(8, dado.getCpf());
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
    public boolean excluir(Hospede dado) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM HOSPEDE "
                + "WHERE CPF=?";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getCpf());
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
    public Hospede buscar(Hospede dado) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM HOSPEDE "
                + "WHERE CPF=?";
        //abre a conexao com o banco
        Banco.abreConexao();
        //busca a conexao do banco
        conexao = Banco.conexao;
        //cria o comando preparado
        pst = conexao.prepareStatement(sql);
        //atribui os dados para o comando
        pst.setString(1, dado.getCpf());
        //executar o comando
        rs = pst.executeQuery();
        hospede = null;
        //Verifica se achou alguem
        if(rs.next()) { //achou
            //transfere todas as informações da tabela para 
            // o Objeto filme
            hospede = new Hospede();
            hospede.setCpf(rs.getString("CPF"));
            hospede.setRg(rs.getString("RG"));
            hospede.setNome(rs.getString("NOME"));
            hospede.setEmail(rs.getString("EMAIL"));
            hospede.setSexo(rs.getString("SEXO"));
            hospede.setDataNasc(rs.getString("DATANASC"));
            hospede.setTel(rs.getString("TELEFONE"));
        }
        //fecha o resultado
        rs.close();
        //fecha o banco
        Banco.fecharConexao();
        //retorna o que foi encontrado, NULL se nada foi achado
        return hospede;
    }

    @Override
    public Collection<Hospede> listar(String criterio) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM HOSPEDE ";

        if (criterio.length() != 0) {
            sql += "WHERE " + criterio;
        }

        //abre o banco
        Banco.abreConexao();
        pst = Banco.conexao.prepareStatement(sql);
        //executar comando SQL
        rs = pst.executeQuery();
        Collection<Hospede> hospedes = new ArrayList<>();
        while (rs.next()) { //percorre todos os registros
            hospede = new Hospede();
            hospede.setCpf(rs.getString("CPF"));
            hospede.setRg(rs.getString("RG"));
            hospede.setNome(rs.getString("NOME"));
            hospede.setEmail(rs.getString("EMAIL"));
            hospede.setSexo(rs.getString("SEXO"));
            hospede.setDataNasc(rs.getString("DATANASC"));
            hospede.setTel(rs.getString("TELEFONE"));
                        
            hospedes.add(hospede);
        } 
        rs.close(); //fecha o resultSet
        Banco.fecharConexao();
        return hospedes;        
    }
    
}
