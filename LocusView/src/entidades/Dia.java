/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author silvio
 */
public class Dia {
    
    // Variáveis
    private int id;
    private String nome;

    // Construtores
    public Dia() {
        
    }
    
    public Dia(String nome) {
        this.nome = nome;
    }
    
    public Dia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Métodos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
