package mbeans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import control.ControleLogin;
import entidades.Login;

@ManagedBean(name = "loginMBean")
@SessionScoped
public class LoginMBean {
	
	private ControleLogin controle;
//	private Login login;
	
	private String login;
	private String password;
	
	public LoginMBean(){
		
//		if (login == null){
//			login = new Login();
//		}
		
		controle = ControleLogin.getInstance();
		
	}
	
	public void entrar() throws IOException{
		
		System.out.println("Login: " + this.getLogin() + " Senha: " + this.getPassword());
		
		// Criando o objeto loginTentativa com os dados inseridos pelo usuário
		Login loginTentativa = new Login(login, password);
		
		// Validando o Login
		int resultadoValidacao = controle.validaLogin(loginTentativa);
		System.out.println(resultadoValidacao);
		// Direcionando de acordo com a validação
		switch (resultadoValidacao) {
		case 0:
			System.out.println("Usuário autenticado. Primeira Entrada.");
			FacesContext.getCurrentInstance().getExternalContext().dispatch("first/1-dados-escola.xhtml");
		case 1:
			System.out.println("Usuário autenticado. Menu Principal.");
			FacesContext.getCurrentInstance().getExternalContext().dispatch("/user/principal.xhtml");
			
		default:
			System.out.println("Usuário não autenticado.");
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Login getLogin() {
//		return login;
//	}
//
//	public void setLogin(Login login) {
//		this.login = login;
//	}

}
