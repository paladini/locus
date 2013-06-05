/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fernando_paladini
 */
public class LoginDAO {
    
    /**
       * Verifica no banco de dados se a senha e o nome de usuário batem.
       * @param nome Nome de usuário
       * @param password Senha inserida pela usuário
       * @return 
       */
      public Boolean logar(Login login){
          Connection connection = Conexao.getConexao();
          ResultSet rs = null;
          try{ 
              Statement statement = connection.createStatement();
              String query = "SELECT login,senha,ultimo_acesso FROM admin WHERE login='" +nome +"';";
              rs = statement.executeQuery(query);
          } catch (SQLException ex){
              System.out.println(ex.getMessage());
          }  
          return rs;
      }
      
      /**
       * Método para modificar a senha do administrador da Instituição
       * @param novaSenha Nova senha de acesso
       * @return 
       */
      public Boolean mudarSenha(String novaSenha){
          Connection connection = Conexao.getConexao();
          boolean sucesso = false;
          try{    
              Statement statement = connection.createStatement();
              statement.execute("update admin set senha = '" +novaSenha +"' where login=\"admin\" ;");
              sucesso = true;
          } catch (SQLException ex){
              System.out.println("ex.getMessage()");
          }
          return sucesso;
 
      }
    
}
