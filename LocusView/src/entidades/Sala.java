/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author silvio
 */
public class Sala {
    
    // Atributos
    private int id;
    private String nome;
    
    // Construtores
    public Sala() {
    
    }
    
    public Sala(String nome){
        this.nome = nome;
    }
    
    public Sala(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // G & S
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

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
