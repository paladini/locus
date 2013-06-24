/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author luiz_malaquias
 */
public class Turma {
    
    // Atributos
    private int id;
    private String nome;
    private Curso curso;
    
    // Construtores
    public Turma() {
        
    }
    
    public Turma(int id, String nome, Curso idCurso) {
        this.id = id;
        this.nome = nome;
        this.curso = idCurso;
    }

    public Turma(String nome, Curso idCurso) {
        this.nome = nome;
        this.curso = idCurso;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso idCurso) {
        this.curso = idCurso;
    }
    
    
}
