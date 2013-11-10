/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe que define entidade Turma.
 * @author fernando_paladini
 */
@Entity
public class Turma {
    
    // Atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CENTRO")
	@Column(name="idTurma")
    private int id;
    private String nome;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idCurso")
    private Curso curso;
    
    // Construtores
    public Turma() {
        
    }
    
    public Turma(int id, String nome){
        this.id = id;
        this.nome = nome;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso idCurso) {
        this.curso = idCurso;
    }

    // ToString - NÃO ALTERAR UMA VIRGULA!
    @Override
    public String toString() {
        return nome;
    }  
}
