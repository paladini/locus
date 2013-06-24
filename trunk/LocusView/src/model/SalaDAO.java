/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author silvio
 */
public class SalaDAO {
    
    /** 
     * Retorna todas as salas do banco de dados.
     * @return 
     */
    public ArrayList<Sala> select() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Sala order by nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Sala> listaSalas = new ArrayList<Sala>();
            while (rs.next()) {
                Sala s = new Sala();
                int id = rs.getInt("idSala");
                String nome = rs.getString("nome");
                s.setId(id);
                s.setNome(nome);
                listaSalas.add(s);
            }

            connection.close();
            return listaSalas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Faz consulta no banco de dados e retorna apenas uma sala com esse nome.
     * @return 
     */
    public Sala selectDisciplina(String nomeSala){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Sala where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeSala);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova sala
            Sala sala = new Sala();
            
            // Pega o primeiro registro do retorno da consulta
            rs.next();
            
            // Pega os dados desse registro e guarda em variáveis
            int id = rs.getInt("idSala");
            String nome = rs.getString("nome");
            
            // Seta os dados na disciplina criada
            sala.setId(id);
            sala.setNome(nome);

            connection.close();
            return sala;
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
    public ArrayList<Sala> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Sala where nome like ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, termos + "%");
            ResultSet rs = prest.executeQuery();

            ArrayList<Sala> listaSalas = new ArrayList<Sala>();
            while (rs.next()) {
                Sala s = new Sala();
                int id = rs.getInt("idSala");
                String nome = rs.getString("nome");
                s.setId(id);
                s.setNome(nome);
                listaSalas.add(s);
            }

            connection.close();
            return listaSalas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void insert(Sala sala) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "INSERT INTO sala (nome) VALUES (?);";
            PreparedStatement prest = connection.prepareStatement(sql);
           
            prest.setString(1,sala.getNome() );
            
            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Sala salaNova, Sala salaAntiga) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "UPDATE sala SET nome = ? WHERE nome = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, salaNova.getNome());
            prest.setString(2, salaAntiga.getNome());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Sala salaAntiga) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "DELETE FROM sala WHERE nome = ?";
            //String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, salaAntiga.getNome());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
