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
            String serverName = "10.3.60.83";    //caminho do servidor do BD  
            // Configurando a nossa conexão com um banco de dados//  
            String mydatabase ="locus";        //nome do seu banco de dados  
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;  
            String username = "usuarioremoto";        //nome de um usuário de seu BD        
            String password = "123";      //sua senha de acesso  
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
      public static Boolean mudarSenha(String novaSenha){
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
