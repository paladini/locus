/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

/**
 *
 * @author fernando_paladini
 */
public class Professor {
    
    // Atributos
    private int id;
    private String nome;
    private ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();

    // Construtores

    public Professor() {
    }
    
    public Professor(String nome) {
        this.nome = nome;
    }

    public Professor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Métodos
    
    /**
     * Método para adicionar disciplina ao professor
     * @param disciplina 
     */
    public void adicionarDisciplina(Disciplina disciplina){
        getListaDisciplinas().add(disciplina);
    }
    
    /**
     * Método para remover disciplina do professor
     * @param disciplina 
     */
    public void removerDisciplina(Disciplina disciplina){
        getListaDisciplinas().remove(disciplina);
    }
    
    // G & S
    public ArrayList<Disciplina> getListaDisciplinas() {
        return listaDisciplinas;
    }

    public void setListaDisciplinas(ArrayList<Disciplina> listaDisciplinas) {
        this.listaDisciplinas = listaDisciplinas;
    }
    
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
