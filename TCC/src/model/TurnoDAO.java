/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Dia;
import entidades.Disciplina;
import entidades.Professor;
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
public class TurnoDAO extends AbstractDAO {
    
	/**
	 * Retorna uma lista de todos os Turnos cadastrados no banco de dados.
	 * @return
	 */
    public ArrayList<Turno> consultar(){
    	Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM turno";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            
            ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
            while (rs.next()) {
                Turno turno = new Turno();
                int id = rs.getInt("idturno");
                String nome = rs.getString("nome");

                turno.setId(id);
                turno.setNome(nome);
                
                listaTurnos.add(turno);
               
            }
            connection.close();
            return listaTurnos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
	
	/**
     * Retorna um Turno do banco de dados, de acordo com o nome do turno fornecido.
     *
     * @return
     */
    public Turno consultar(String nomeTurno) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM turno where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeTurno);
            ResultSet rs = prest.executeQuery();

            // Pega o primeiro registro do retorno da consulta
            if (rs.next()) {
                // Cria uma nova disciplina
                Turno turno = new Turno();
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idturno");
                String nome = rs.getString("nome");

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
    
    /**
     * Retorna um Turno do banco de dados, de acordo com o ID fornecido.
     *
     * @return
     */
    public Turno consultar(int idTurno) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM turno where idturno = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, idTurno);
            ResultSet rs = prest.executeQuery();

            // Pega o primeiro registro do retorno da consulta
            if (rs.next()) {
                // Cria uma nova disciplina
                Turno turno = new Turno();
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idturno");
                String nome = rs.getString("nome");

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
    
    /**
     * Insere um Turno no banco de dados, de acordo com o Turno fornecido.
     * @param turno
     */
    public void inserir(Turno turno) {
        String sql = "INSERT INTO turno (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getNome());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Atualiza um Turno no banco de dados, de acordo com o Turno fornecido.
     * @param turno
     */
    public void atualizar(Turno turno) {
        String sql = "UPDATE turno SET nome = ? where idturno = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getNome());
        params.add(turno.getId());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Deleta um Turno no banco de dados, de acordo com o Turno fornecido.
     * @param turno
     */
    public void deletar(Turno turno) {
    	
    	// Deletando todas as associações desse turno com dias.
    	this.deletarAssociacoes(turno);
    	
    	String sql = "DELETE FROM turno WHERE idturno = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getId());
        operacaoEscrita(sql, params);
    }
    
    
    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação turno-dia
     * 
     *    ======================================================================
     * 
     */
    /**
     * Retorna uma lista de todas os Dias associados ao Turno fornecido.
     * @param turno
     * @return
     */
    public ArrayList<Dia> listaDiasAssociados(Turno turno) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select * from turno_has_dia as cd inner join dia as d on "
                    + "d.iddia = cd.dia_iddia where cd.turno_idturno = ? order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, turno.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Dia> listaDias = new ArrayList<Dia>();
            while (rs.next()) {
                Dia dia = new Dia();
                int id = rs.getInt("iddia");
                String nome = rs.getString("nome");
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
     * Retorna uma lista de todos os Dias não associados ao Turno fornecido.
     *
     * @param professor
     */
    public ArrayList<Dia> listaDiasNaoAssociados(Turno turno) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select * from dia d where d.iddia not in "
                    + "(select dia_iddia from turno_has_dia where turno_idturno = ?) order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, turno.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Dia> listaDias = new ArrayList<Dia>();
            while (rs.next()) {
            	Dia dia = new Dia();
                int id = rs.getInt("iddia");
                String nome = rs.getString("nome");
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
     * Adiciona um dia ao turno.
     *
     * @param curso
     */
    public void inserirTurnoDia(int idTurno, int idDia) {
        String sql = "INSERT INTO turno_has_dia (dia_iddia, turno_idturno) VALUES (?, ?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idDia);
        params.add(idTurno);
        operacaoEscrita(sql, params);
    }

    /**
     * Deleta um dia que está associada a um turno.
     *
     * @param idTurno
     * @param idDia
     */
    public void deletarTurnoDia(int idTurno, int idDia) {
        String sql = "delete from turno_has_dia where dia_iddia = ? and turno_idturno = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idDia);
        params.add(idTurno);
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
     * Deleta todas as associações entre turno e dia. 
     *
     * @param professor
     */
    private void deletarAssociacoes(Turno turno) {
        String sql = "delete from turno_has_dia where turno_idturno = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno.getId());
        operacaoEscrita(sql, params);
    }
    
    
}
