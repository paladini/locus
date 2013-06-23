/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luiz_malaquias
 */
public class CursoDAO {
   
    /**
     * ALTERAR ESSE MÉTODO E DEIXÁ-LO FUNCIONAL!
     * @param curso 
     */
    public void adicionarDisciplina(Curso curso){
        Connection connection = Conexao.getConexao();
        try {
            String sql = "INSERT INTO Curso_has_Disciplina (nome) VALUES (?);";
            PreparedStatement prest = connection.prepareStatement(sql);
           
            prest.setString(1,curso.getNome() );
            

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Faz consulta no banco de dados e retorna todos os cursos.
     * @return 
     */
    public ArrayList<Curso> select() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM curso";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Curso> listaClientes = new ArrayList<Curso>();
            while (rs.next()) {
                Curso c = new Curso();
                int id = rs.getInt("idCurso");
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
     * Faz consulta no banco de dados e retorna apenas um curso com esse nome.
     * @return 
     */
    public Curso selectCurso(String nomeCurso){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM curso where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeCurso);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova disciplina
            Curso curso = new Curso();
            
            // Pega o primeiro registro do retorno da consulta
            rs.next();
            
            // Pega os dados desse registro e guarda em variáveis
            int id = rs.getInt("idCurso");
            String nome = rs.getString("nome");
            
            // Seta os dados na disciplina criada
            curso.setId(id);
            curso.setNome(nome);

            connection.close();
            return curso;
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
    public ArrayList<Curso> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM curso where nome like ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, termos + "%");
            ResultSet rs = prest.executeQuery();

            ArrayList<Curso> listaCursos = new ArrayList<Curso>();
            while (rs.next()) {
                Curso c = new Curso();
                int id = rs.getInt("idCurso");
                String nome = rs.getString("nome");
                c.setId(id);
                c.setNome(nome);
                listaCursos.add(c);
            }

            connection.close();
            return listaCursos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public void insert(Curso curso) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "INSERT INTO curso (nome) VALUES (?);";
            PreparedStatement prest = connection.prepareStatement(sql);
           
            prest.setString(1,curso.getNome() );
            

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Curso cursoNovo, Curso cursoAntigo) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "UPDATE curso SET nome = ? WHERE nome like ?";
            //String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, cursoNovo.getNome());
            prest.setString(2, cursoAntigo.getNome());
            //prest.setInt(2,clienteAntigo.getId());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Curso cursoAntigo) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "DELETE FROM curso WHERE nome = ?";
            //String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, cursoAntigo.getNome());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
   
    
}