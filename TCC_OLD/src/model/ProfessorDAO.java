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
     * Faz consulta no banco de dados e retorna todos os professores.
     *
     * @return
     */
    public ArrayList<Professor> select() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Professor";
            PreparedStatement prest = connection.prepareStatement(sql);
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

    /**
     * Faz consulta no banco de dados e retorna apenas um professor com esse nome.
     *
     * @return
     */
    public Professor selectProfessor(String nomeProfessor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Professor where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeProfessor);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova disciplina
            Professor professor = new Professor();

            // Pega o primeiro registro do retorno da consulta
            rs.next();

            // Pega os dados desse registro e guarda em variáveis
            int id = rs.getInt("idProfessor");
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
     * Faz consulta no banco de dados e retorna apenas um professor com esse nome.
     *
     * @return
     */
    public Professor selectProfessor(int idProfessor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Professor where idProfessor = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, idProfessor);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova disciplina
            Professor professor = new Professor();

            // Pega o primeiro registro do retorno da consulta
            if (rs.next()){
            	 // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idProfessor");
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
     * Faz uma consulta no banco de dados pesquisando pelos termos digitados até
     * o momento.
     *
     * @param termos Termos digitados pelo usuário.
     * @return
     */
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

    public void insert(Professor professor) {
        String sql = "INSERT INTO Professor (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getNome());
        operacaoEscrita(sql, params);
    }

    public void update(Professor professor) {
        String sql = "UPDATE Professor SET nome = ? WHERE idProfessor = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getNome());
        params.add(professor.getId());
        operacaoEscrita(sql, params);
    }

    public void delete(Professor professor) {

        // Chama um outro método para excluir as disciplinas associadas a esse curso.
        this.deleteAssociacoes(professor);

        String sql = "DELETE FROM Professor WHERE idProfessor = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getId());
        operacaoEscrita(sql, params);
    }
    
    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação curso-disciplina
     * 
     *    ======================================================================
     * 
     */

    
    /**
     * Retorna a lista de todas as disciplinas associadas à esse curso
     *
     * @param curso
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Professor professor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.idDisciplina, d.nome from Disciplina_has_Professor as cd inner join Disciplina as d on "
                    + "d.idDisciplina = cd.Disciplina_idDisciplina where cd.Professor_idProfessor = ? order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, professor.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                int id = rs.getInt("idDisciplina");
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
     * Retorna a lista de todas as disciplinas não associadas à esse curso
     *
     * @param professor
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Professor professor) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.idDisciplina, d.nome from Disciplina d where d.idDisciplina not in "
                    + "(select Disciplina_idDisciplina from Disciplina_has_Professor where Professor_idProfessor = ?) order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, professor.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                int id = rs.getInt("idDisciplina");
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
        String sql = "INSERT INTO Disciplina_has_Professor (Disciplina_idDisciplina, Professor_idProfessor) VALUES (?, ?);";
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
        String sql = "delete from Disciplina_has_Professor where Disciplina_idDisciplina = ? and Professor_idProfessor = ?;";

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
     * (Necessário para poder excluir o professor sem deixar dependências).
     *
     * @param professor
     */
    private void deleteAssociacoes(Professor professor) {
        String sql = "delete from Disciplina_has_Professor where Professor_idProfessor = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(professor.getId());
        operacaoEscrita(sql, params);
    }
    
}
