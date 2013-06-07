/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fernando_paladini
 */
public class EscolaDAO {
    
    /**
       * Método para mudar o nome da instituição.
       * @param novoNome Novo nome da instituição.
       * @return Retorna true se operação foi completada, false caso não.
       */
      public static boolean mudarInstituicao(String novoNome){ 
          Connection connection = Conexao.getConexao();
          boolean sucesso = false;
          try{
              Statement statement = connection.createStatement();
              statement.execute("update admin set nome_instituicao = '" +novoNome +"' where login=\"admin\" ;");
              sucesso = true;
          } catch (SQLException ex){
              System.out.println(ex.getMessage());
          }
          return sucesso;
      }
      
      /**
       * Método para adicionar turnos ao banco de dados.
       * Se o turno já existir no banco de dados, ele insere de novo e omite os warnings).
       * @param turno 
       */
      public static void adicionarTurno(String turno){
          Connection connection = Conexao.getConexao();
          try{
              Statement statement = connection.createStatement();
              statement.execute("INSERT IGNORE INTO turno (descricao) values (\"" + turno +"\");");
          } catch (SQLException ex){
              System.out.println(ex.getMessage());
          }
      }
    
}
