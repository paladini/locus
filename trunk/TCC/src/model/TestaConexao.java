/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel.s.oliveira
 */
public class TestaConexao {

    public static void main(String[] args) {
        
        Connection connection = Conexao.getConexao();

        try {

            // Cria o statement - Objeto de interação de dados  
            //Statement statement = connection.createStatement();
           
            //statement.execute("INSERT INTO cliente (nome) VALUES ('oliveira');");
            
            
            String sql = "INSERT INTO cliente (nome) VALUES (?);";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1,"oliveira");
           prest.execute();
            //rs.next();
            //System.out.println(rs.getString("nome"));
            //statement.execute("UPDATE");
            //JOptionPane.showMessageDialog(null, "atualizou");
            
            //statement.execute("DELETE");
            //JOptionPane.showMessageDialog(null, "deletou");
            
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
