/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Curso;
import entidades.Disciplina;
import entidades.Turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fernando_paladini
 */
public class TurmaDAO extends AbstractDAO{
    
    /** 
     * Retorna todas as turmas do banco de dados.
     * @return 
     */
    public ArrayList<Turma> select() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Turma order by nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Turma> listaTurmas = new ArrayList<Turma>();
            while (rs.next()) {
                Turma turma = new Turma();
                
                int id = rs.getInt("idTurma");
                String nome = rs.getString("nome");
                 /*
                 * O método selectCurso pesquisa na tabela "Curso" pelo curso com o mesmo ID que está no "Curo_idCurso"; 
                 * que é o curso associado à essa turma.
                 */
                Curso curso = this.selectCurso(rs.getInt("Curso_idCurso"));
                
                turma.setId(id);
                turma.setNome(nome);
                turma.setCurso(curso);
                
                listaTurmas.add(turma);
            }

            connection.close();
            return listaTurmas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Faz consulta no banco de dados e retorna apenas uma turma com esse nome.
     * @return 
     */
    public Turma selectTurma(int id){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Turma where idTurma = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, id);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova turma
            Turma turma = new Turma();
            
            // Verifica se existe algum resultado nesse result set
            if(rs.next()){
                // Pega os dados desse registro e guarda em variáveis
                int idNovo = rs.getInt("idTurma");
                String nome = rs.getString("nome");

                 /*
                  * O método selectCurso pesquisa na tabela "Curso" pelo curso com o mesmo ID que está no "Curo_idCurso"; 
                  * que é o curso associado à essa turma.
                 */
                Curso curso = this.selectCurso(rs.getInt("Curso_idCurso"));

                // Seta os dados na turma criada
                turma.setId(idNovo);
                turma.setNome(nome);
                turma.setCurso(curso);

                connection.close();
                return turma;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Faz consulta no banco de dados e retorna apenas uma turma com esse nome.
     * @return 
     */
    public Turma selectTurma(String nomeTurma){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Turma where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeTurma);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova turma
            Turma turma = new Turma();
            
            // Verifica se existe algum resultado nesse result set
            if(rs.next()){
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idTurma");
                String nome = rs.getString("nome");

                 /*
                  * O método selectCurso pesquisa na tabela "Curso" pelo curso com o mesmo ID que está no "Curo_idCurso"; 
                  * que é o curso associado à essa turma.
                 */
                Curso curso = this.selectCurso(rs.getInt("Curso_idCurso"));

                // Seta os dados na turma criada
                turma.setId(id);
                turma.setNome(nome);
                turma.setCurso(curso);

                connection.close();
                return turma;
            }
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
    public ArrayList<Turma> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Turma where nome like ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, termos + "%");
            ResultSet rs = prest.executeQuery();

            ArrayList<Turma> listaTurmas = new ArrayList<Turma>();
            while (rs.next()) {
                Turma turma = new Turma();
                int id = rs.getInt("idTurma");
                String nome = rs.getString("nome");
                
                /*
                 * O método selectCurso pesquisa na tabela "Curso" pelo curso com o mesmo ID que está no "Curo_idCurso"; 
                 * que é o curso associado à essa turma.
                 */
                Curso curso = this.selectCurso(rs.getInt("Curso_idCurso"));
                
                turma.setId(id);
                turma.setNome(nome);
                turma.setCurso(curso);
                
                listaTurmas.add(turma);
            }

            connection.close();
            return listaTurmas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Inserir turma com id do curso
     * @param turma Turma a ser inserida no banco
     */
    public void insert(Turma turma){
        String sql = "INSERT INTO Turma (nome, Curso_idCurso) VALUES (?,?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turma.getNome());
        params.add(turma.getCurso().getId());
        operacaoEscrita(sql, params);
    }
    
    public void update(Turma turmaNova) {
        String sql = "UPDATE Turma SET nome = ?, Curso_idCurso = ? WHERE id = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turmaNova.getNome());
        params.add(turmaNova.getCurso().getId());
        params.add(turmaNova.getId());
        operacaoEscrita(sql, params);
    }

    public void delete(Turma turmaAntiga) {
        String sql = "DELETE FROM Turma WHERE idTurma = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turmaAntiga.getId());
        operacaoEscrita(sql, params);
    }
    
    
    /*
     * mmm   mmm          
     * m m   m m  eeeeee  ttttttt
     * m  m m  m  e          t  
     * m   m   m  eee        t  
     * m       m  e          t
     * m       m  eeeeee     t
     */
    
    
    
    /**
     * Faz consulta no banco de dados e retorna apenas um curso (que é o associado à turma).
     * @return 
     */
    private Curso selectCurso(int idCurso){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM Turma where idCurso = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, idCurso);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova sala
            Curso curso = new Curso();
            
            // Pega o primeiro registro do retorno da consulta
            if(rs.next()){
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idCurso");
                String nome = rs.getString("nome");

                // Seta os dados na disciplina criada
                curso.setId(id);
                curso.setNome(nome);

                connection.close();
                return curso;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    @Deprecated
    public void update(Turma turmaNova, Turma turmaAntiga) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "UPDATE Turma SET nome = ?, Curso_idCurso = ? WHERE nome = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, turmaNova.getNome());
            
            /**
             * Se a turma nova tiver um novo curso, atualiza com esse novo curso.
             * Se não tiver, atualiza também mas deixa o ID do curso antigo.
             */
            if (turmaNova.getCurso() != null){
                prest.setInt(2, turmaNova.getCurso().getId());
            }else{
                prest.setInt(2, turmaAntiga.getCurso().getId());
            }
            prest.setString(3, turmaAntiga.getNome());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
