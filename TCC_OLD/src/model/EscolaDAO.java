/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Escola;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fernando_paladini
 */
public class EscolaDAO extends AbstractDAO {

    public Escola consultar() {
        
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM admin;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            if (rs.next()){
                Escola escola = new Escola();
                escola.setNomeEscola(rs.getString("nome_escola"));
                return escola;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }

    /**
     * Método para mudar o nome da instituição.
     *
     * @param novoNome Novo nome da instituição.
     * @return Retorna true se operação foi completada, false caso não.
     */
    public boolean mudarInstituicao(String novoNome) {
        String sql = "update admin set nome_escola = ? where login = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(novoNome);
        params.add("admin");
        operacaoEscrita(sql, params);
        return true;
    }

 
    
    /**
     * Método para adicionar turnos ao banco de dados. Se o turno já existir no
     * banco de dados, ele insere de novo e omite os warnings).
     *
     * @param turno
     */
    public boolean adicionarTurno(String turno) {
        String sql = "INSERT IGNORE INTO Turno (descricao) values (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno);
        operacaoEscrita(sql, params);
        return true;
    }
    
    /**
     * Método para remover um turno do banco de dados.
     * @param turno
     * @return
     */
    public boolean removerTurno(String turno){
   	 String sql = "DELETE from Turno where descricao = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(turno);
        operacaoEscrita(sql, params);
        return true;
   }

    /**
     * Método para adicionar dias ao banco de dados. Se o dia já existir no
     * banco de dados, ele insere de novo e omite os warnings.
     *
     * @param dia
     * @return
     */
    public boolean adicionarDia(String dia) {
        String sql = "INSERT IGNORE INTO Dia (descricao) values (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(dia);
        operacaoEscrita(sql, params);
        return true;
    }
    
    /**
     * Método par remover um dia do banco de dados
     * @param dia
     * @return
     */
    public boolean removerDia(String dia){
    	 String sql = "DELETE from Dia where descricao = ?;";
         ArrayList<Object> params = new ArrayList<Object>();
         params.add(dia);
         operacaoEscrita(sql, params);
         return true;
    }
    
    public boolean removerDias(){
    	String sql = "DELETE from Dia where idDia != -1;";
        ArrayList<Object> params = new ArrayList<Object>();
        operacaoEscrita(sql, params);
        return true;
    }
    
    public boolean removerTurnos(){
    	 String sql = "DELETE from Turno where idTurno != -1;";
         ArrayList<Object> params = new ArrayList<Object>();
         operacaoEscrita(sql, params);
         return true;
    }
}
