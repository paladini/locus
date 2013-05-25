/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author fernando_paladini
 */
public class Processamento {
    
    public static String concatenarTexto(String texto1, String texto2){
        return texto2 + " " + texto1; 
    }
    
    public static boolean login(String login, String senha){
        System.out.println(login + " " + senha);
        if (login.equals("pessoa")){
            if (senha.equals("senha")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
        
    }
    
}
