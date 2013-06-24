/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author daniel.s.oliveira
 */
public abstract class AbstractDAO {
    
    
    protected void operacaoEscrita(String sql, ArrayList<Object> lista) {
        Connection connection = Conexao.getConexao();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            preparacao(ps, lista);
            
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    private void preparacao(PreparedStatement ps, ArrayList<Object> lista) {
        try {
            int contador = 0;
            for (Object o : lista) {
                if (o instanceof java.util.Date) {
                    java.sql.Date dataSql = new java.sql.Date(((java.util.Date)o).getTime());
                    ps.setDate(++contador, (dataSql));
                } else {
                    ps.setObject(++contador, o);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
}
