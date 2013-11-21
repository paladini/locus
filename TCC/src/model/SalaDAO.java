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
     * Retorna uma lista com todas as Salas cadastradas no banco de dados.
     * @return 
     */
    public ArrayList<Sala> consultar() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM sala order by nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Sala> listaSalas = new ArrayList<Sala>();
            while (rs.next()) {
            	Sala sala = new Sala();
            	
            	// Pega os dados desse registro e guarda em variáveis
                int idSala = rs.getInt("idsala");
                String nome = rs.getString("nome");
                boolean usar1 = rs.getBoolean("usar1");
                boolean usar2 = rs.getBoolean("usar2");
                boolean usar3 = rs.getBoolean("usar3");
                
                // Seta os dados na disciplina criada
                sala.setId(idSala);
                sala.setNome(nome);
                sala.setUsar1(usar1);
                sala.setUsar2(usar2);
                sala.setUsar3(usar3);
                
                listaSalas.add(sala);
            }

            connection.close();
            return listaSalas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Retorna uma Sala do banco de dados, de acordo com o nome da sala fornecido.
     * @return 
     */
    public Sala consultar(String nomeSala){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM sala where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeSala);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova sala
            Sala sala = new Sala();
            
            // Pega o primeiro registro do retorno da consulta
            if(rs.next()){
            	// Pega os dados desse registro e guarda em variáveis
                int idSala = rs.getInt("idsala");
                String nome = rs.getString("nome");
                boolean usar1 = rs.getBoolean("usar1");
                boolean usar2 = rs.getBoolean("usar2");
                boolean usar3 = rs.getBoolean("usar3");
                
                // Seta os dados na disciplina criada
                sala.setId(idSala);
                sala.setNome(nome);
                sala.setUsar1(usar1);
                sala.setUsar2(usar2);
                sala.setUsar3(usar3);
                
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
     * Retorna uma Sala do banco de dados, de acordo com o ID fornecido.
     * @return Retorna uma sala única com esse ID.
     */
    public Sala consultar(int id){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM sala where idsala = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, id);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova sala
            Sala sala = new Sala();
            
            if (rs.next()){
            	// Pega o primeiro registro do retorno da consulta
                rs.next();
                
                // Pega os dados desse registro e guarda em variáveis
                int idSala = rs.getInt("idsala");
                String nome = rs.getString("nome");
                boolean usar1 = rs.getBoolean("usar1");
                boolean usar2 = rs.getBoolean("usar2");
                boolean usar3 = rs.getBoolean("usar3");
                
                // Seta os dados na disciplina criada
                sala.setId(idSala);
                sala.setNome(nome);
                sala.setUsar1(usar1);
                sala.setUsar2(usar2);
                sala.setUsar3(usar3);

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
     * Insere uma Sala no banco de dados, de acordo com a Sala fornecida.
     * @param sala
     */
    public void inserir(Sala sala) {
        String sql = "INSERT INTO sala (nome, usar1, usar2, usar3) VALUES (?,?,?,?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(sala.getNome());
        params.add(sala.isUsar1());
        params.add(sala.isUsar2());
        params.add(sala.isUsar3());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Atualiza uma Sala no banco de dados, de acordo com a Sala fornecida.
     * @param salaNova
     */
    public void atualizar(Sala salaNova) {
        String sql = "UPDATE sala SET nome = ?, usar1 = ?, usar2 = ?, usar3 = ? WHERE idsala = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(salaNova.getNome());
        params.add(salaNova.isUsar1());
        params.add(salaNova.isUsar2());
        params.add(salaNova.isUsar3());
        params.add(salaNova.getId());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Deleta a Sala no banco de dados, de acordo com a Sala fornecida.
     * @param salaAntiga
     */
    public void deletar(Sala salaAntiga) {
        String sql = "DELETE FROM sala WHERE idsala = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(salaAntiga.getId());
        operacaoEscrita(sql, params);
    }
    
    /*
     * ================================================
     * 
     *            POSSIVELMENTE DEPRECIADOS
     * 
     * ================================================ 
     */
    /**
     * Faz uma consulta no banco de dados pesquisando pelos termos digitados até o momento.
     * @param termos Termos digitados pelo usuário.
     * @return 
     */
    @Deprecated
    public ArrayList<Sala> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM sala where nome like ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, termos + "%");
            ResultSet rs = prest.executeQuery();

            ArrayList<Sala> listaSalas = new ArrayList<Sala>();
            while (rs.next()) {
                Sala sala = new Sala();
                
                // Pega os dados desse registro e guarda em variáveis
                int idSala = rs.getInt("idsala");
                String nome = rs.getString("nome");
                boolean usar1 = rs.getBoolean("usar1");
                boolean usar2 = rs.getBoolean("usar2");
                boolean usar3 = rs.getBoolean("usar3");
                
                // Seta os dados na disciplina criada
                sala.setId(idSala);
                sala.setNome(nome);
                sala.setUsar1(usar1);
                sala.setUsar2(usar2);
                sala.setUsar3(usar3);
                
                listaSalas.add(sala);
            }

            connection.close();
            return listaSalas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
