/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author silvio
 */
public class DisciplinaDAO {
   
   
    /** 
     * Retorna todas as disciplinas do banco de dados.
     * @return 
     */
    public ArrayList<Disciplina> select() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM disciplina order by nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaClientes = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina c = new Disciplina();
                int id = rs.getInt("idDisciplina");
                String nome = rs.getString("nome");
                c.setId(id);
                c.setNome(nome);
                listaClientes.add(c);
            }

            connection.close();
            return listaClientes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Faz consulta no banco de dados e retorna apenas uma disciplina com esse nome.
     * @return 
     */
    public Disciplina selectDisciplina(String nomeDisciplina){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM disciplina where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeDisciplina);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova disciplina
            Disciplina disciplina = new Disciplina();
            
            // Pega o primeiro registro do retorno da consulta
            rs.next();
            
            // Pega os dados desse registro e guarda em variáveis
            int id = rs.getInt("idDisciplina");
            String nome = rs.getString("nome");
            
            // Seta os dados na disciplina criada
            disciplina.setId(id);
            disciplina.setNome(nome);

            connection.close();
            return disciplina;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    
    /**
     * Faz uma consulta no banco de dados pesquisando pelos termos digitados até o momento.
     * @param termos Termos digitados pelo usuário.
     * @return 
     */
    public ArrayList<Disciplina> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM disciplina where nome like ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, termos + "%");
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaClientes = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina c = new Disciplina();
                int id = rs.getInt("idDisciplina");
                String nome = rs.getString("nome");
                c.setId(id);
                c.setNome(nome);
                listaClientes.add(c);
            }

            connection.close();
            return listaClientes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void insert(Disciplina disciplina) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "INSERT INTO disciplina (nome) VALUES (?);";
            PreparedStatement prest = connection.prepareStatement(sql);
           
            prest.setString(1,disciplina.getNome() );
            
            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Disciplina disciplinaNova, Disciplina disciplinaAntiga) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "UPDATE disciplina SET nome = ? WHERE nome like ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, disciplinaNova.getNome());
            prest.setString(2, disciplinaAntiga.getNome());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Disciplina disciplinaAntiga) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "DELETE FROM disciplina WHERE nome = ?";
            //String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, disciplinaAntiga.getNome());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
