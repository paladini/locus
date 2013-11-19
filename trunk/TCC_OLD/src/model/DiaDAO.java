/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Dia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author silvio
 */
public class DiaDAO extends AbstractDAO {

    public ArrayList<Dia> consulta(){
    	
    	 Connection connection = Conexao.getConexao();
         try {

             String sql = "SELECT * FROM Dia;";
             PreparedStatement prest = connection.prepareStatement(sql);
             ResultSet rs = prest.executeQuery();
             
             
             ArrayList<Dia> listaDias = new ArrayList<Dia>();
             while (rs.next()) {
                 Dia dia = new Dia();
                 int id = rs.getInt("idDia");
                 String nome = rs.getString("descricao");

                 dia.setId(id);
                 dia.setNome(nome);

                 listaDias.add(dia);
                 
             }
             connection.close();
             return listaDias;
             
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
    public Dia selectDia(String nomeDia) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM dia where descricao = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeDia);
            ResultSet rs = prest.executeQuery();

            // Pega o primeiro registro do retorno da consulta
            if (rs.next()) {
                // Cria uma nova disciplina
                Dia dia = new Dia();
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idDia");
                String nome = rs.getString("descricao");

                // Seta os dados na disciplina criada
                dia.setId(id);
                dia.setNome(nome);

                connection.close();
                return dia;
            }else{
                connection.close();
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void insert(Dia dia) {
        String sql = "INSERT INTO dia (descricao) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(dia.getNome());
        operacaoEscrita(sql, params);
    }

    public void delete(Dia dia) {
        String sql = "DELETE FROM dia WHERE descricao = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(dia.getNome());
        operacaoEscrita(sql, params);
    }
}
