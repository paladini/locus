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
import java.util.Date;

/**
 *
 * @author silvio
 */
public class LoginDAO {
    
    /**
       * Verifica no banco de dados se a senha e o nome de usuário batem.
       * @param nome Nome de usuário
       * @param password Senha inserida pela usuário
       * @return 
       */
      public Login logar(String usuario){
          Connection connection = Conexao.getConexao();
          ResultSet rs = null;
          try{ 
              Statement statement = connection.createStatement();
              String query = "SELECT login,senha,ultimo_acesso FROM admin WHERE login='" +usuario +"';";
              rs = statement.executeQuery(query);
              rs.next();
              
              // Pegando os dados
              String admin = rs.getString("login");
              String senha = rs.getString("senha");
              Date ultimo_acesso = rs.getDate("ultimo_acesso");
              
              // Setando atributos ao objeto
              Login login = new Login();
              login.setLogin(admin);
              login.setSenha(senha);
              login.setUltimoacesso(ultimo_acesso);
              
              return login;

          } catch (SQLException ex){
              System.out.println(ex.getMessage());
          }  
          return null;
      }
    
}
