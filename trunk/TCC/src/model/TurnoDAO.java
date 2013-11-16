/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author silvio
 */
public class TurnoDAO extends AbstractDAO {
    
	/**
	 * Faz consulta no banco de dados e retorna todos os turnos.
	 * @return
	 */
    public ArrayList<Turno> select(){
    	Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Turno";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            
            ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
            while (rs.next()) {
                Turno turno = new Turno();
                int id = rs.getInt("idTurno");
                String nome = rs.getString("descricao");

                turno.setId(id);
                turno.setNome(nome);
                
                listaTurnos.add(turno);
               
            }
            connection.close();
            return listaTurnos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
	
	/**
     * Faz consulta no banco de dados e retorna apenas um dia com esse nome.
     *
     * @return
     */
    public Turno selectTurno(String nomeTurno) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM turno where descricao = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeTurno);
            ResultSet rs = prest.executeQuery();

            // Pega o primeiro registro do retorno da consulta
            if (rs.next()) {
                // Cria uma nova disciplina
                Turno turno = new Turno();
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idTurno");
                String nome = rs.getString("descricao");

                // Seta os dados na disciplina criada
                turno.setId(id);
                turno.setNome(nome);

                connection.close();
                return turno;
            }else{
                connection.close();
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void insert(Turno turno) {
        String sql = "INSERT INTO Turno (descricao) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getNome());
        operacaoEscrita(sql, params);
    }

    public void delete(Turno turno) {
        String sql = "DELETE FROM Turno WHERE descricao = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getNome());
        operacaoEscrita(sql, params);
    }
    
}
