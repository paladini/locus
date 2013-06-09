/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import model.LoginDAO;
import entidades.Login;

/**
 *
 * @author silvio
 */
public class ControleLogin {
    
    ResultSet result; 
    LoginDAO modelo = new LoginDAO();
    
    public int login(String login, String senha){
        
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        
        // Criando objeto loginUsuario, de acordo com dados concedidos pelo usuario (na view deu problema). 
        Login loginUsuario = new Login();
        loginUsuario.setLogin(login);
        loginUsuario.setSenha(senha);
        
        // Criando objeto loginBanco, de acordo com os dados do banco.
        Login loginBanco = modelo.logar(loginUsuario.getLogin());
        
        /**
         * Se retorno = 0: acesso não permitido
         * Se retorno = 1: primeiro acesso, redirecionar para PrimeiraEntrada1;
         * Se retorno = 2: acesso normal, redirecionar para MenuPrincipal;
         */
        int retorno = 0;
        
        if (loginUsuario.getSenha().contentEquals(loginBanco.getSenha())){
             if (loginBanco.getUltimoacesso() == null){
                 retorno = 1;
             }else{
                 retorno = 2;
             }
        }
        return retorno;
    }
    
}
