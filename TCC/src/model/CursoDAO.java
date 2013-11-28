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

import entidades.Curso;
import entidades.Disciplina;
import entidades.Turma;

/**
 *
 * @author fernando_paladini
 */
public class CursoDAO extends AbstractDAO {

    /**
     * Retorna uma lista dos Cursos cadastrados no banco de dados.
     *
     * @return
     */
    public ArrayList<Curso> consultar() {

        Connection connection = Conexao.getConexao();
        try {

            // Faz a consulta ao banco de dados
            String sql = "SELECT * FROM curso";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            /*
             * Cria um arraylist de cursos para armazenar todos os cursos
             * que voltaram dessa consulta ao banco de dados.
             */
            ArrayList<Curso> listaCurso = new ArrayList<Curso>();
            
            /* Enquanto rs.next() for true, ou seja, enquanto existir mais um curso,
             * e retorna o ArrayList com todos os cursos.
             */
            while (rs.next()) {
                
                /*
                 * Cria um curso.
                 * 
                 * correspondente no banco de dados (rs.getInt("idCurso"). 
                 * 
                 */
                Curso c = new Curso();
                int id = rs.getInt("idcurso");
                String nome = rs.getString("nome");
                
                c.setId(id);
                c.setNome(nome);
                listaCurso.add(c);
            }

            // 
            return listaCurso;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        return null;
    }

    /**
     * Retorna um Curso do banco de dados, de acordo com o nome do curso fornecido.
     *
     * @return
     */
    public Curso consultar(String nomeCurso) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM curso where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeCurso);
            ResultSet rs = prest.executeQuery();

            // Pega o primeiro registro do retorno da consulta, se existir
            if (rs.next()) {
                
                // Cria uma nova disciplina
                Curso curso = new Curso();

                int id = rs.getInt("idcurso");
                String nome = rs.getString("nome");

                // Seta os dados na disciplina criada
                curso.setId(id);
                curso.setNome(nome);

//                 
                return curso;
            }else{
//                 
                return null;
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

   
    /**
     * Retorna um Curso do banco de dados, de acordo com o ID fornecido.
     * @param id
     * @return
     */
    public Curso consultar(int id){
    	 Connection connection = Conexao.getConexao();
         try {

             String sql = "SELECT * FROM curso where idCurso = ?;";
             PreparedStatement prest = connection.prepareStatement(sql);
             prest.setInt(1, id);
             ResultSet rs = prest.executeQuery();

             if (rs.next()) {
                 Curso c = new Curso();
                 int idConsulta = rs.getInt("idcurso");
                 String nome = rs.getString("nome");
                 c.setId(idConsulta);
                 c.setNome(nome);
                 return c;
             }

//              
             return null;
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return null;
    }
    
    /**
     * Inser um Curso no banco de dados, de acordo com o Curso fornecido.
     * @param curso
     */
    public void inserir(Curso curso) {
        String sql = "INSERT INTO curso (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(curso.getNome());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Atualiza um Curso do banco de dados, de acordo com o Curso fornecido.
     * @param cursoNovo
     */
    public void atualizar(Curso cursoNovo) {
        String sql = "UPDATE curso SET nome = ? WHERE idcurso = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(cursoNovo.getNome());
        params.add(cursoNovo.getId());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Deleta um Curso do banco de dados, de acordo com o ID fornecido.
     * @param idCurso
     */
    public void deletar(int idCurso) {

        this.deletarAssociacoes(idCurso);

        String sql = "DELETE FROM curso WHERE idcurso = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        operacaoEscrita(sql, params);
    }

    /*
     * 
     *    ======================================================================
     * 
     * 
     *    ======================================================================
     * 
     */
    /**
     * Retorna a lista de todas as disciplinas associadas à esse curso
     *
     * @param curso
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Curso curso) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.iddisciplina, d.nome from curso_has_disciplina as cd inner join disciplina as d on "
                    + "d.iddisciplina = cd.disciplina_iddiscilpina where cd.curso_idcurso = ? order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, curso.getId());
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

             
            return listaDisciplinas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @param curso
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Curso curso) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.iddisciplina, d.nome from disciplina d where d.iddisciplina not in "
                    + "(select disciplina_iddiscilpina from curso_has_disciplina where curso_idcurso = ?) order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, curso.getId());
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

             
            return listaDisciplinas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Adiciona uma disciplina ao curso.
     *
     * @param curso
     */
    public void insertCursoDisciplina(int idCurso, int idDisciplina) {
        String sql = "INSERT INTO curso_has_disciplina (curso_idcurso, disciplina_iddiscilpina) VALUES (?, ?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        params.add(idDisciplina);
        operacaoEscrita(sql, params);
    }

    /**
     * Deleta uma disciplina associada ao curso.
     *
     * @param idCurso
     * @param idDisciplina
     */
    public void deleteCursoDisciplina(int idCurso, int idDisciplina) {
        String sql = "delete from curso_has_disciplina where curso_idcurso = ? and disciplina_iddiscilpina = ?;";

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        params.add(idDisciplina);
        operacaoEscrita(sql, params);
    }
    
    /*
     * 
     *    ======================================================================
     * 
     * 
     *    ======================================================================
     * 
     */
    /**
     * Retorna a lista de todas as disciplinas associadas à esse curso
     *
     * @param curso
     */
    public ArrayList<Turma> listaTurmasAssociadas(Curso curso) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select * from turma where idcurso = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, curso.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Turma> listaTurmas = new ArrayList<Turma>();
            while (rs.next()) {
                Turma turma = new Turma();
                int id = rs.getInt("idturma");
                String nome = rs.getString("nome");
                
                // Pegando turno
//                int idTurno = rs.getInt("idturno");
//                ControleTurno ct = ControleTurno.getInstance();
                // Turno turno = ct.consultar(idTurno);
                // turma.setTurno(turno);
                
                turma.setId(id);
                turma.setNome(nome);
                turma.setCurso(curso);
                listaTurmas.add(turma);
            }

             
            return listaTurmas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    

    /*
     * ============================================
     * 
     * 
     * ============================================
     */
    /**
     *
     * @param curso
     */
    private void deletarAssociacoes(int idCurso) {
        String sql = "delete from curso_has_disciplina where curso_idcurso = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        operacaoEscrita(sql, params);
    }
    
    
    
    
    /*
     * ============================================
     * 
     *         POSSIVELMENTE DEPRECIADOS
     *         
     * ============================================
     */
    /**
     * o momento.
     * @return
     */
    @Deprecated
    public ArrayList<Curso> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Curso where nome like ?;";
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

             
            return listaCursos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
