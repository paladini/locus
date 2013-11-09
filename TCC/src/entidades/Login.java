/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author silvio
 */
public class Login {
    
    // Atributos
    private String login;
    private String senha;
    private Date ultimoacesso;

    // Construtores
    public Login() {
    
    }
    
    public Login(String login, String senha){
    	this.login = login;
    	this.senha = senha;
    }
    
    public Login(String login, String senha, Date ultimoacesso) {
        this.login = login;
        this.senha = senha;
        this.ultimoacesso = ultimoacesso;
    }
    
    // Métodos
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
