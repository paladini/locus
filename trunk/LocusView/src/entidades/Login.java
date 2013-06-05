/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author fernando_paladini
 */
public class Login {
    
    // Atributos
    private String nomeEscola;
    private String login;
    private String senha;
    private Date ultimoacesso;
    
    // Construtores
    public Login() {
    }

    public Login(String nomeEscola, String login, String senha, Date ultimoacesso) {
        this.nomeEscola = nomeEscola;
        this.login = login;
        this.senha = senha;
        this.ultimoacesso = ultimoacesso;
    }
 
    // Métodos
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
