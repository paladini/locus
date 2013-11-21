/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Disciplina;
import entidades.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fernando_paladini
 */
public class ProfessorDAO extends AbstractDAO{
    
    /**
     * Retorna uma lista com todos os professores cadastrados no banco de dados.
     *
     * @return
     */
    public ArrayList<Professor> consultar() {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM professor;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Professor> listaProfessores = new ArrayList<Professor>();
            while (rs.next()) {
                Professor professor = new Professor();
                int id = rs.getInt("idprofessor");
                String nome = rs.getString("nome");
                professor.setId(id);
                professor.setNome(nome);
                listaProfessores.add(professor);
            }
            connection.close();
            return listaProfessores;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Retorna um professor do banco de dados, de acordo com o nome do professor fornecido.
     *
     * @return
     */
    public Professor consultar(String nomeProfessor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM professor where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeProfessor);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova disciplina
            Professor professor = new Professor();

            // Pega o primeiro registro do retorno da consulta
            rs.next();

            // Pega os dados desse registro e guarda em variáveis
            int id = rs.getInt("idprofessor");
            String nome = rs.getString("nome");

            // Seta os dados na disciplina criada
            professor.setId(id);
            professor.setNome(nome);

            connection.close();
            return professor;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Retorna um professor do banco de dados, de acordo com o ID fornecido.
     *
     * @return
     */
    public Professor consultar(int idProfessor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM professor where idprofessor = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, idProfessor);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova disciplina
            Professor professor = new Professor();

            // Pega o primeiro registro do retorno da consulta
            if (rs.next()){
            	 // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idprofessor");
                String nome = rs.getString("nome");

                // Seta os dados na disciplina criada
                professor.setId(id);
                professor.setNome(nome);

                connection.close();
                return professor;
            }
            connection.close();
            return professor;
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Insere um novo professor no banco de dados, de acordo com o Professor fornecido.
     * @param professor
     */
    public void inserir(Professor professor) {
        String sql = "INSERT INTO professor (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getNome());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Atualiza um professor no banco de dados, de acordo com o Professor fornecido.
     * @param professor
     */
    public void atualizar(Professor professor) {
        String sql = "UPDATE professor SET nome = ? WHERE idprofessor = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getNome());
        params.add(professor.getId());
        operacaoEscrita(sql, params);
    }

    /**
     * Deleta um professor do banco de dados, de acordo com o Professor fornecido.
     * @param professor
     */
    public void deletar(Professor professor) {

        // Chama um outro método para excluir as disciplinas associadas a esse curso.
        this.deletarAssociacoes(professor);

        String sql = "DELETE FROM professor WHERE idprofessor = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getId());
        operacaoEscrita(sql, params);
    }
    
    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação professor-disciplina
     * 
     *    ======================================================================
     * 
     */

    
    /**
     * Retorna a lista de todas as disciplinas associadas à esse professor
     *
     * @param professor 
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Professor professor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.iddisciplina, d.nome from disciplina_has_professor as cd inner join disciplina as d on "
                    + "d.iddisciplina = cd.disciplina_iddisciplina where cd.professor_idprofessor = ? order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, professor.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                int id = rs.getInt("iddisciplina");
                String nome = rs.getString("nome");
                disciplina.setId(id);
                disciplina.setNome(nome);
                listaDisciplinas.add(disciplina);
            }

            connection.close();
            return listaDisciplinas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Retorna a lista de todas as disciplinas não associadas à esse professor
     *
     * @param professor
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Professor professor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.iddisciplina, d.nome from disciplina d where d.iddisciplina not in "
                    + "(select disciplina_iddisciplina from disciplina_has_professor where professor_idprofessor = ?) order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, professor.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                int id = rs.getInt("iddisciplina");
                String nome = rs.getString("nome");
                disciplina.setId(id);
                disciplina.setNome(nome);
                listaDisciplinas.add(disciplina);
            }

            connection.close();
            return listaDisciplinas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Adiciona uma disciplina ao professor.
     *
     * @param curso
     */
    public void insertProfessorDisciplina(int idProfessor, int idDisciplina) {
        String sql = "INSERT INTO disciplina_has_professor (disciplina_iddisciplina, professor_idprofessor) VALUES (?, ?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idDisciplina);
        params.add(idProfessor);
        operacaoEscrita(sql, params);
    }

    /**
     * Deleta uma disciplina associada ao professor.
     *
     * @param idProfessor
     * @param idDisciplina
     */
    public void deleteProfessorDisciplina(int idProfessor, int idDisciplina) {
        String sql = "delete from disciplina_has_professor where disciplina_iddisciplina = ? and professor_idprofessor = ?;";

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idDisciplina);
        params.add(idProfessor);
        operacaoEscrita(sql, params);
    }

    /*
     * ============================================
     * 
     *                Métodos Privados
     * 
     * ============================================
     */
    
    /**
     * Deleta todas as associações entre professor e disciplina. 
     *
     * @param professor
     */
    private void deletarAssociacoes(Professor professor) {
        String sql = "delete from disciplina_has_professor where professor_idprofessor = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getId());
        operacaoEscrita(sql, params);
    }
    
    
    
    
    
    /*
     * ==============================================
     * 
     *             POSSIVELMENTE DEPRECIADOS
     *             
     * ==============================================
     */
    /**
     * Faz uma consulta no banco de dados pesquisando pelos termos digitados até
     * o momento.
     *
     * @param termos Termos digitados pelo usuário.
     * @return
     */
    @Deprecated
    public ArrayList<Professor> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Professor where nome like ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, termos + "%");
            ResultSet rs = prest.executeQuery();

            ArrayList<Professor> listaProfessores = new ArrayList<Professor>();
            while (rs.next()) {
                Professor professor = new Professor();
                int id = rs.getInt("idProfessor");
                String nome = rs.getString("nome");
                professor.setId(id);
                professor.setNome(nome);
                listaProfessores.add(professor);
            }

            connection.close();
            return listaProfessores;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
