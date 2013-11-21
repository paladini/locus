package mbeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import control.ControleAdmin;
import entidades.Admin;

@ManagedBean(name = "adminMBean")
@ViewScoped
public class AdminMBean {
	
	// Atributos
	private ControleAdmin controle;
	private Admin admin;
	
	public AdminMBean(){
		if (controle == null){
			controle = ControleAdmin.getInstance();
		}
		
		if (admin == null){
			admin = new Admin("","","",null);
		}
	}
	
	public String autenticarLogin() throws IOException{
		
		// Instanciando FacesContext
		FacesContext context = FacesContext.getCurrentInstance(); 
		
		// Criando o objeto loginTentativa com os dados inseridos pelo usuário
		Admin loginTentativa = new Admin(admin.getLogin(), admin.getSenha());
		
		String redirecionamento = "";
		
		// Validando o Login
		int resultadoValidacao = controle.autenticarLogin(loginTentativa);
		
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

	public ControleAdmin getControle() {
		return controle;
	}

	public void setControle(ControleAdmin controle) {
		this.controle = controle;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
