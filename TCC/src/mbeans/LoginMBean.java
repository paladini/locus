package mbeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import control.ControleLogin;
import entidades.Login;

@ManagedBean(name = "loginMBean")
@ViewScoped
public class LoginMBean {
	
	private ControleLogin controle;
//	private Login login;
	
	private String login;
	private String password;
	
	public LoginMBean(){
		
		controle = ControleLogin.getInstance();
		
	}
	
	public String entrar() throws IOException{
		
		// Instanciando FacesContext
		FacesContext context = FacesContext.getCurrentInstance(); 
		
		// Criando o objeto loginTentativa com os dados inseridos pelo usuário
		Login loginTentativa = new Login(login, password);
		String redirecionamento = "";
		
		// Validando o Login
		int resultadoValidacao = controle.validaLogin(loginTentativa);
		
		// Direcionando de acordo com a validação
		switch (resultadoValidacao) {
		case 0:
			System.out.println("Usuário autenticado. Primeira Entrada.");
			redirecionamento = "first/1-dados-escola.xhtml?faces-redirect=true";
			break;
		case 1:
			System.out.println("Usuário autenticado. Menu Principal.");
			redirecionamento = "user/principal.xhtml?faces-redirect=true";
			break;
		default:
			System.out.println("Usuário não autenticado.");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Dados inválidos", "Por favor, insira seus dados novamente."));  
		}
		
		return redirecionamento;
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

}
