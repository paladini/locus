/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Disciplina;

/**
 *
 * @author silvio
 */
public class DisciplinaDAO extends AbstractDAO {

    /**
     * Retorna todas as disciplinas do banco de dados.
     *
     * @return
     */
    public ArrayList<Disciplina> consultar() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM disciplina order by nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaClientes = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina c = new Disciplina();
                int id = rs.getInt("iddisciplina");
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
     * Returna uma disciplina do banco de dados, de acordo com o nome da disciplina fornecido.
     * @return
     */
    public Disciplina consultar(String nomeDisciplina) {
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
            int id = rs.getInt("iddisciplina");
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
     * Retorna uma Disciplina do banco de dados, de acordo com o ID fornecido.
     * @return
     */
    public Disciplina consultar(int idDisciplina) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM disciplina where iddisciplina = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, idDisciplina);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova disciplina
            Disciplina disciplina = new Disciplina();

            // Pega o primeiro registro do retorno da consulta
            rs.next();

            // Pega os dados desse registro e guarda em variáveis
            int id = rs.getInt("iddisciplina");
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
     * Insere no banco de dados a disciplina fornecida.
     * @param disciplina Disciplina a ser inserida.
     */
    public void inserir(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(disciplina.getNome());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Atualiza no banco de dados a disciplina fornecida. 
     * @param disciplinaNova Disciplina a ser atualizada.
     */
    public void atualizar(Disciplina disciplinaNova) {
        String sql = "UPDATE disciplina SET nome = ? WHERE iddisciplina = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(disciplinaNova.getNome());
        params.add(disciplinaNova.getId());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Deleta do banco de dados a Disciplina que foi fornecida (através do ID).
     * @param disciplinaAntiga Disciplina a ser deletada.
     */
    public void deletar(Disciplina disciplinaAntiga) {
        deletarAssociacoes(disciplinaAntiga);
        String sql = "DELETE FROM disciplina WHERE iddisciplina = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(disciplinaAntiga.getId());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Deleta todas as associações da disciplina com cursos.
     * @param disciplina
     */
     private void deletarAssociacoes(Disciplina disciplina) {
        String sql = "delete from curso_has_disciplina where disciplina_iddiscilpina = ?;"
        		+ "delete from disciplina_has_professor where disciplina_iddisciplina = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(disciplina.getId());
        params.add(disciplina.getId());
        operacaoEscrita(sql, params);
    }
     
     /*
      *=====================================================================================
      *
      *                             POSSIVELMENTE DEPRECIADOS
      *
      *=====================================================================================
      */
     /**
      * Faz uma consulta no banco de dados pesquisando pelos termos digitados até
      * o momento.
      *
      * @param termos Termos digitados pelo usuário.
      * @return
      */
     @Deprecated
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
                 int id = rs.getInt("iddisciplina");
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
}
