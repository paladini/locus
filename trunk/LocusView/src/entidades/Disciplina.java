/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author silvio
 */
public class Disciplina {
    
    // Variáveis
    private int id;
    private String nome;

    // Construtores
    public Disciplina(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Disciplina() {
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

    // ToString - NÃO ALTERAR UMA VIRGULA!
    @Override
    public String toString() {
        return nome;
    }
    
}
