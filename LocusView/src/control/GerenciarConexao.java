/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
//import org.jasypt.util.text.BasicPasswordEncryptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexao;

/**
 *
 * @author silvio
 */
public class GerenciarConexao {
    
    public boolean Login(String nome, String senha){
        
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        ResultSet result = Conexao.logar(nome);
        Boolean retorno = false;
        
        try {
            if (result != null){
                if (result.getString("senha").contentEquals(senha)){
                    retorno = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
}
