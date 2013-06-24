/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Escola;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author fernando_paladini
 */
public class EscolaDAO extends AbstractDAO {

    /**
       * Método para mudar o nome da instituição.
       * @param novoNome Novo nome da instituição.
       * @return Retorna true se operação foi completada, false caso não.
       */
      public boolean mudarInstituicao(String novoNome){ 
        String sql = "update admin set nome_escola = ? where login = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(novoNome);
        params.add("admin");
        operacaoEscrita(sql, params);
        return true;
      }
      
      /**
       * Método para adicionar turnos ao banco de dados.
       * Se o turno já existir no banco de dados, ele insere de novo e omite os warnings).
       * @param turno 
       */
      public boolean adicionarTurno(String turno){
            String sql = "INSERT IGNORE INTO turno (descricao) values ?;";
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(turno);
            operacaoEscrita(sql, params);
            return true;
      }
      
      /**
       * Método para adicionar dias ao banco de dados.
       * Se o dia já existir no banco de dados, ele insere de novo e omite os warnings. 
       * @param dia 
       * @return 
       */
      public boolean adicionarDia(String dia){
          String sql = "INSERT IGNORE INTO dia (descricao) values ?;";
          ArrayList<Object> params = new ArrayList<Object>();
          params.add(dia);
          operacaoEscrita(sql, params);
          return true; 
      }
    
}
