/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 *  Esta classe é responsável APENAS pela conexão com o banco de dados. 
 *  Precisa configurar de acordo com a sua máquina, abrir o MySQL Workbench,etc.
 *  O campo "password" normalmente é vazio ou nulo, não se esqueçam de alterar
 *  esse campo, normalmente é necessário, pois depende da sua configuração no Workbench.
 * @author fernando_paladini
 */
public class Conexao { 
    
      public static Connection getConexao() {  
        try {  
            // Carregando o JDBC Driver padrão  
            String driverName = "com.mysql.jdbc.Driver";                          
            Class.forName(driverName);  
            String serverName = "localhost:3306";    //caminho do servidor do BD  
            // Configurando a nossa conexão com um banco de dados//  
            String mydatabase ="locus";        //nome do seu banco de dados  
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;  
            String username = "root";        // nome de um usuário de seu BD        
            String password = "33410084";      // sua senha de acesso  
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
}
