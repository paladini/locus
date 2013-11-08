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
 * @author daniel.s.oliveira
 */
public class Conexao {

    public static Connection getConexao() {
        try {
            // Carregando o JDBC Driver padrão  
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            // Configurando a nossa conexão com um banco de dados//  
            String serverName = "localhost";    //caminho do servidor do BD  
            String mydatabase = "locus";        //nome do seu banco de dados  
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";        //nome de um usuário de seu BD        
            String password = "sp95fccod2";      //sua senha de acesso  
            Connection connection = DriverManager.getConnection(url, username, password);
            //String url = "jdbc:mysql://10.3.60.249:3306/teste?user=root&password=root";
            //Connection connection = DriverManager.getConnection(url);
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
