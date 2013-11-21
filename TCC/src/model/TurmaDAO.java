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

import control.ControleTurno;
import entidades.Curso;
import entidades.Turma;
import entidades.Turno;

/**
 *
 * @author fernando_paladini
 */
public class TurmaDAO extends AbstractDAO{
    
    /** 
     * Retorna todas as turmas do banco de dados.
     * @return 
     */
    public ArrayList<Turma> consultar() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM turma order by nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Turma> listaTurmas = new ArrayList<Turma>();
            while (rs.next()) {
                Turma turma = new Turma();
                
                int id = rs.getInt("idturma");
                String nome = rs.getString("nome");
                int idTurno = rs.getInt("idturno");
                 /*
                 * O método selectCurso pesquisa na tabela "Curso" pelo curso com o mesmo ID que está no "Curo_idCurso"; 
                 * que é o curso associado à essa turma.
                 */
                Curso curso = this.selectCurso(rs.getInt("idcurso"));
                
                turma.setId(id);
                turma.setNome(nome);
                turma.setCurso(curso);
                this.preparaTurnoConsulta(turma, idTurno);
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
     * Retorna uma Turma do banco de dados, de acordo com o ID fornecido.
     * @return 
     */
    public Turma consultar(int idTurma){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM turma where idturma = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, idTurma);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova turma
            Turma turma = new Turma();
            
            // Verifica se existe algum resultado nesse result set
            if(rs.next()){
            	
            	int id = rs.getInt("idturma");
                String nome = rs.getString("nome");
                int idTurno = rs.getInt("idturno");
                 /*
                 * O método selectCurso pesquisa na tabela "Curso" pelo curso com o mesmo ID que está no "Curo_idCurso"; 
                 * que é o curso associado à essa turma.
                 */
                Curso curso = this.selectCurso(rs.getInt("idcurso"));
                
                turma.setId(id);
                turma.setNome(nome);
                turma.setCurso(curso);
                this.preparaTurnoConsulta(turma, idTurno);

                connection.close();
                return turma;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Retorna uma Turma do banco de dados, de acordo com o nome da turma fornecido.
     * @return 
     */
    public Turma consultar(String nomeTurma){
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM turma where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeTurma);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova turma
            Turma turma = new Turma();
            
            // Verifica se existe algum resultado nesse result set
            if(rs.next()){
            	
            	int id = rs.getInt("idturma");
                String nome = rs.getString("nome");
                int idTurno = rs.getInt("idturno");
                 /*
                 * O método selectCurso pesquisa na tabela "Curso" pelo curso com o mesmo ID que está no "Curo_idCurso"; 
                 * que é o curso associado à essa turma.
                 */
                Curso curso = this.selectCurso(rs.getInt("idcurso"));
                
                turma.setId(id);
                turma.setNome(nome);
                turma.setCurso(curso);
                this.preparaTurnoConsulta(turma, idTurno);

                connection.close();
                return turma;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    
    
    /**
     * Insere uma nova Turma no banco de dados.
     * @param turma Turma a ser inserida no banco
     */
    public void inserir(Turma turma){
        String sql = "INSERT INTO turma (nome, idcurso, idturno) VALUES (?,?,?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turma.getNome());
        params.add(turma.getCurso().getId());
        
        // Pegando o ID REAL do turno
        int idTurno = preparaTurnoResto(turma.getTurno());
        params.add(idTurno);
        
        operacaoEscrita(sql, params);
    }
    
    /**
     * Atualiza uma turma no banco de dados.
     * @param turmaNova
     */
    public void atualizar(Turma turmaNova) {
        String sql = "UPDATE turma SET nome = ?, idcurso = ?, idturno = ? WHERE idturma = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turmaNova.getNome());
        params.add(turmaNova.getCurso().getId());
        
        // Pegando o ID REAL do turno
        int idTurno = preparaTurnoResto(turmaNova.getTurno());
        params.add(idTurno);
        params.add(turmaNova.getId());
        
        operacaoEscrita(sql, params);
    }
    
    /**
     * Deleta uma turma do banco de dados.
     * @param turmaAntiga
     */
    public void deletar(Turma turmaAntiga) {
        String sql = "DELETE FROM turma WHERE idturma = ?;";
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

            String sql = "SELECT * FROM curso where idcurso = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, idCurso);
            ResultSet rs = prest.executeQuery();

            // Cria uma nova sala
            Curso curso = new Curso();
            
            // Pega o primeiro registro do retorno da consulta
            if(rs.next()){
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idcurso");
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
    
    
    
    
    
    
    /**
     * Turma tem um atributo "Turno" do tipo Int, pois isso facilita o desenvolvimento do ensalamento. 
     * No banco não temos um inteiro que identifica qual turno é qual, o que identifica é uma String. 
     * 
     * Ou seja, esse método faz uma conversão de String (que teria um valor "Matutino", "Vespertino" ou "Noturno")
     * para inteiro: 0, 1 e 2 respectivamente. No nosso banco o ID do turno "Matutino", "Vespertino" e "Noturno"
     * é alterado a todo momento, por isso não podemos verificar pelo ID que Matutino = 0, pois as vezes 
     * "Matutino" pode ser igual a 100. 
     * 
     *  Estou partindo do pressuposto que turno Matutino = 0, Vespertino = 1, Noturno = 2.
     * @param turma
     * @param idTurno
     */
    private void preparaTurnoConsulta(Turma turma, int idTurno){
    	
    	ControleTurno ct = ControleTurno.getInstance();
    	Turno turno = ct.consultar(idTurno);
    	
    	if(turno.getNome().equals("Matutino")){
    		turma.setTurno(0);
    	}
    	
    	if(turno.getNome().equals("Vespertino")){
    		turma.setTurno(1);
    	}
    	
    	if(turno.getNome().equals("Noturno")){
    		turma.setTurno(2);
    	}
    	
    }
    
    /**
     * Turma tem um atributo "Turno" do tipo Int, pois isso facilita o desenvolvimento do ensalamento. 
     * No banco não temos um inteiro que identifica qual turno é qual, o que identifica é uma String. 
     * 
     * Esse método faz uma conversão de int, que representa a ordem do turno, para String, que vai ser o nome
     * do Turno, só para então fazer uma consulta e descobrir o id real do turno selecionado.
     * 
     *  Estou partindo do pressuposto que turno Matutino = 0, Vespertino = 1, Noturno = 2.
     * @param turma
     * @param idTurno
     */
    private int preparaTurnoResto(int idTurno){
    	
    	// Pegando o ID certo do Turno
        ControleTurno ct = ControleTurno.getInstance();
        String termo = "";
        
        if(idTurno == 0){
        	termo = "Matutino";
        }else{
        	if(idTurno == 1){
            	termo = "Vespertino";
            }else{
            	if(idTurno == 2){
                	termo = "Noturno";
                }else{
                	termo = "Algo de errado!";
                }
            }
        }
        System.out.println("Termo é: " + termo);
        Turno turno = ct.consultar(termo);
        return turno.getId();
    	
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
     * ========================================
     *   
     *        POSSIVELMENTE DEPRECIADOS
     * 
     * ========================================
     */
    /**
     * Faz uma consulta no banco de dados pesquisando pelos termos digitados até o momento.
     * @param termos Termos digitados pelo usuário.
     * @return 
     */
    @Deprecated
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
