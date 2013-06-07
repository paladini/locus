/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Escola;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EscolaDAO;

/**
 *
 * @author silvio
 */
public class ControleEscola {
    
    ResultSet result; 
    EscolaDAO modelo = new EscolaDAO();
    
    public int login(Escola escola){
        
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        result = modelo.logar(escola);
        
        /**
         * Se retorno = 0: acesso não permitido
         * Se retorno = 1: primeiro acesso, redirecionar para PrimeiraEntrada1;
         * Se retorno = 2: acesso normal, redirecionar para MenuPrincipal;
         */
        int retorno = 0;
        
        try {
            result.next();
            if (result != null){
                if (result.getString("senha").contentEquals(escola.getSenha())){
                    if (result.getDate("ultimo_acesso") == null){
                        retorno = 1;
                    }else{
                        retorno = 2;
                    }       
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
}
