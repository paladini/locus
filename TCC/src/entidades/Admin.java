package entidades;

import java.util.Date;

public class Admin {
	
	// Atributos
	private String nomeEscola;
	private String login;
    private String senha;
    private Date ultimoacesso;
    
    // Construtores
    public Admin(){
    	
    }
    
    public Admin(String nomeEscola) {
		super();
		this.nomeEscola = nomeEscola;
	}

	public Admin(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Admin(String nomeEscola, String login, String senha) {
		super();
		this.nomeEscola = nomeEscola;
		this.login = login;
		this.senha = senha;
	}

	public Admin(String nomeEscola, String login, String senha,
			Date ultimoacesso) {
		super();
		this.nomeEscola = nomeEscola;
		this.login = login;
		this.senha = senha;
		this.ultimoacesso = ultimoacesso;
	}
	
	

	// Getters & Setters
	public String getNomeEscola() {
		return nomeEscola;
	}
	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getUltimoacesso() {
		return ultimoacesso;
	}
	public void setUltimoacesso(Date ultimoacesso) {
		this.ultimoacesso = ultimoacesso;
	}
    
    

}
