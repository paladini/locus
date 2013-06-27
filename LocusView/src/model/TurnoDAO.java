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
 * @author fernando_paladini
 */
public class TurnoDAO extends AbstractDAO{
    
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
        String sql = "INSERT INTO turno (descricao) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getNome());
        operacaoEscrita(sql, params);
    }

    public void delete(Turno turno) {
        String sql = "DELETE FROM turno WHERE descricao = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getNome());
        operacaoEscrita(sql, params);
    }
    
}
