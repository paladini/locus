/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fernando_paladini
 */
public class Conexao {
    
      public static Connection getConexao() {  
        try {  
            // Carregando o JDBC Driver padrão  
            String driverName = "com.mysql.jdbc.Driver";                          
            Class.forName(driverName);  
            // Configurando a nossa conexão com um banco de dados//  
            String serverName = "localhost";    //caminho do servidor do BD  
            String mydatabase ="locus";        //nome do seu banco de dados  
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;  
            String username = "root";        //nome de um usuário de seu BD        
            String password = "33410084";      //sua senha de acesso  
            Connection connection = DriverManager.getConnection(url, username, password);  
            return connection;  
        } catch (ClassNotFoundException e) {  //Driver não encontrado  
            System.out.println("O driver expecificado nao foi encontrado.");  
            return null;  
        } catch (SQLException e) {  
            //Não conseguindo se conectar ao banco  
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");  
            return null;  
        }
      } 
      
      /**
       * Verifica no banco de dados se a senha e o nome de usuário batem.
       * @param nome Nome de usuário
       * @param password Senha inserida pela usuário
       * @return 
       */
      public static ResultSet logar(String nome){
          Connection connection = Conexao.getConexao();
          ResultSet rs = null;
          try{ 
              Statement statement = connection.createStatement();
              String query = "SELECT login,senha FROM admin WHERE login='" +nome +"';";
              rs = statement.executeQuery(query);
          } catch (SQLException ex){
              System.out.println(ex.getMessage());
          }  
          return rs;
      }
    
}
