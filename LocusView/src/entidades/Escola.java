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
public class Escola {
 
    // Atributos
    private String nomeEscola;
    
    
    // Construtores
    public Escola() {
    }

    public Escola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
        
    }
 
    // Métodos
    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }
    
}
