/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Login;
import model.LoginDAO;

/**
 *
 * @author daniel.s.oliveira
 */
public class ControleLogin {
	
	private static ControleLogin singleton;
	private LoginDAO modelo;
    
	private ControleLogin(){
		modelo = new LoginDAO();
	}
	
	public static ControleLogin getInstance(){
		if (singleton == null){
			singleton = new ControleLogin();
		}
		return singleton;
	}
	
    public static String processamentoApae(String email, String senha){
        String resultado = email + " " + senha;
        return resultado;
    }
    
    /**
     * Verifica se o Login é valido e sob quais condiçoes
     * @param loginTentativa Recebe a tentativa de login com os dados que o usuário inseriu.
     * @return 0 = Pula direto para o menu principal; 1 = Pula para o "Primeiro Acesso"; 2 = Login ou senha errados
     */
    public int validaLogin(Login loginTentativa){
    	
    	Login loginOriginal = modelo.logar(loginTentativa.getLogin());
    	
    	if(loginOriginal != null && loginTentativa.getSenha().contentEquals(loginOriginal.getSenha())){
    		if(loginOriginal.getUltimoacesso() == null){
    			return 0;
    		}else{
    			return 1;
    		}
    	}else{
        	return 2;
    	}
    }
    
    public void modificarSenha(String novaSenha){
    	modelo.mudarSenha(novaSenha);
    }
    
    
    
}
