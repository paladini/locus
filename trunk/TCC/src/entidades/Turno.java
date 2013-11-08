/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author silvio
 */
public class Turno {
    
    // Atributos
    private int id;
    private String nome;

    // Construtores
    public Turno() {
    }
    
    public Turno(String nome) {
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
