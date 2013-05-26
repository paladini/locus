///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package model;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
// *
// * @author fernando_paladini
// */
//public class TesteConexao {
//    
//    public static void main(String[] args) {  
//      Connection connection = Conexao.getConexao();  
//      // Sql de inserção  
//       String sql = "INSERT INTO cliente (nome) VALUES ('GG');";  
//  
//      // Cria o statement - Objeto de interação de dados  
//      Statement statement;
//      try{ 
//        statement = connection.createStatement();  
//        
//        /*
//         * Comandos do Tipo Imperativo
//         */
//        // Insert 
//        int ok = 0;
//        int id = 1;
//        while(ok != 100){
//            statement.execute("INSERT INTO cliente (nome) values ('Guilherme"+id+"');"); 
//            id++;
//            ok++;
//        }
//        
//        // Update
//         statement.execute("UPDATE cliente set nome = 'Gabriel R.' where nome = 'Gabriel';");
//        
//        // Delete
//         statement.execute("DELETE from cliente where nome='Guilherme';");
//        
//        /*
//         * Comandos do Tipo Consulta
//         */
//        // Select
//        ResultSet rs = statement.executeQuery("SELECT * FROM cliente;");
//        
//        boolean temRegistro = true;
//        int id1;
//        String nome;
//        while(temRegistro!=false){
//            if (rs.next()){
//                id1 = rs.getInt("id");
//                nome = rs.getString("nome");
//                System.out.println(" ID   |     Nome");
//                System.out.println(id1 + "  |  " + nome + "\n");
//                
//            }else{
//                temRegistro = false;
//            }
//        }
//
//        connection.close(); 
//      } catch (SQLException ex){
//          System.out.println(ex.getMessage());
//      }
//   }  
//    
//}
