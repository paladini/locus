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

import entidades.Dia;
import entidades.Turno;

/**
 *
 * @author fernando_paladini
 */
public class DiaDAO extends AbstractDAO {

	/**
	 * Retorna uma lista com todos os Dias cadastrados no banco de dados.
	 * @return
	 */
    public ArrayList<Dia> consultar(){
    	
    	 Connection connection = Conexao.getConexao();
         try {

             String sql = "SELECT * FROM dia;";
             PreparedStatement prest = connection.prepareStatement(sql);
             ResultSet rs = prest.executeQuery();
             
             
             ArrayList<Dia> listaDias = new ArrayList<Dia>();
             while (rs.next()) {
                 Dia dia = new Dia();
                 int id = rs.getInt("iddia");
                 String nome = rs.getString("nome");

                 dia.setId(id);
                 dia.setNome(nome);

                 listaDias.add(preparaTurnosDoDia(dia));
                 
             }
             connection.close();
             return listaDias;
             
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return null;
    	
    }
	
	/**
     * Retorna um Dia do banco de dados, de acordo com o nome do dia fornecido.
     *
     * @return
     */
    public Dia consultar(String nomeDia) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM dia where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeDia);
            ResultSet rs = prest.executeQuery();

            // Pega o primeiro registro do retorno da consulta
            if (rs.next()) {
                // Cria uma nova disciplina
                Dia dia = new Dia();
                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("iddia");
                String nome = rs.getString("nome");

                // Seta os dados na disciplina criada
                dia.setId(id);
                dia.setNome(nome);

                connection.close();
                return preparaTurnosDoDia(dia);
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
     * Retorna um Dia do banco de dados, de acordo com o ID fornecido.
     * @param idDia
     * @return
     */
    public Dia consultar(int idDia){
    	 Connection connection = Conexao.getConexao();
         try {

             String sql = "SELECT * FROM dia where iddia = ?;";
             PreparedStatement prest = connection.prepareStatement(sql);
             prest.setInt(1, idDia);
             ResultSet rs = prest.executeQuery();

             // Pega o primeiro registro do retorno da consulta
             if (rs.next()) {
                 // Cria uma nova disciplina
                 Dia dia = new Dia();
                 // Pega os dados desse registro e guarda em variáveis
                 int id = rs.getInt("iddia");
                 String nome = rs.getString("nome");

                 // Seta os dados na disciplina criada
                 dia.setId(id);
                 dia.setNome(nome);

                 connection.close();
                 return preparaTurnosDoDia(dia);
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
     * Insere um Dia no banco de dados.
     * @param dia
     */
    public void inserir(Dia dia) {
        String sql = "INSERT INTO dia (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(dia.getNome());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Atualiza um Dia no banco de dados.
     * @param dia
     */
    public void atualizar(Dia dia) {
        String sql = "UPDATE dia SET nome = ? where iddia = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(dia.getNome());
        params.add(dia.getId());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Deleta um Dia do banco de dados.
     * @param dia
     */
    public void deletar(Dia dia) {
        this.deletarAssociacoes(dia);
    	String sql = "DELETE FROM dia WHERE iddia = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(dia.getId());
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
     * Retorna uma lista de todas os Turnos associados ao Dia fornecido.
     * @param dia
     * @return
     */
    public ArrayList<Turno> listaTurnosAssociados(Dia dia) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select * from turno_has_dia as cd inner join turno as d on "
                    + "d.idturno = cd.turno_idturno where cd.dia_iddia = ? order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, dia.getId());
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
     * Retorna uma lista de todos os Turnos não associados ao Dia fornecido.
     *
     * @param professor
     */
    public ArrayList<Turno> listaTurnosNaoAssociados(Dia dia) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select * from turno d where d.idturno not in "
                    + "(select turno_idturno from turno_has_dia where dia_iddia = ?) order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, dia.getId());
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
     * Adiciona um turno ao dia.
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
     * Deleta um turno que está associada a um dia.
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
    private void deletarAssociacoes(Dia dia) {
        String sql = "delete from turno_has_dia where dia_iddia = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(dia.getId());
        operacaoEscrita(sql, params);
    }
    
    /**
     * Como Dia só tem 3 turnos, e não um arraylist de turnos, fiz esse método para converter o ArrayList<Turno>
     * que vem do banco, para os 3 turnos que o dia tem.
     * @param dia
     * @return
     */
    private Dia preparaTurnosDoDia(Dia dia){
    	
    	ArrayList<Turno> listaTurnos = this.listaTurnosAssociados(dia);
    	if(listaTurnos.size() > 0){
    		
    		for(Turno t : listaTurnos){
    			
    			if(t.getNome() == "Matutino"){
    				dia.setT1(t);
    			}
    			
    			if(t.getNome() == "Vespertino"){
    				dia.setT2(t);
    			}
    			
    			if(t.getNome() == "Noturno"){
    				dia.setT3(t);
    			}
    			
    		}
    		
    	}
    	return dia;
    	
    }
}
