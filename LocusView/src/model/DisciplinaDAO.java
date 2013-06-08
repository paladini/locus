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
   
    public ArrayList<Disciplina> select() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM disciplina order by nome";
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
