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
 * @author fernando_paladini
 */
public class SalaDAO extends AbstractDAO{
    
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
    public Sala selectSala(String nomeSala){
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
     * Faz consulta no banco de dados e retorna apenas uma sala com esse id.
     * @return Retorna uma sala única com esse ID.
     */
    public Sala selectSala(int id){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Sala where idSala = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, id);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova sala
            Sala sala = new Sala();
            
            if (rs.next()){
            	// Pega o primeiro registro do retorno da consulta
                rs.next();
                
                // Pega os dados desse registro e guarda em variáveis
                int idSala = rs.getInt("idSala");
                String nome = rs.getString("nome");
                
                // Seta os dados na disciplina criada
                sala.setId(id);
                sala.setNome(nome);

                connection.close();
                return sala;

            }else{
            	return null;
            }
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
    @Deprecated
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
        String sql = "INSERT INTO Sala (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(sala.getNome());
        operacaoEscrita(sql, params);
    }

    public void update(Sala salaNova) {
        String sql = "UPDATE Sala SET nome = ? WHERE idSala = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(salaNova.getNome());
        params.add(salaNova.getId());
        operacaoEscrita(sql, params);
    }

    public void delete(Sala salaAntiga) {
        String sql = "DELETE FROM Sala WHERE idSala = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(salaAntiga.getId());
        operacaoEscrita(sql, params);
    }
    
}
