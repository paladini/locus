/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

import javax.persistence.Entity;

/**
 * Classe que define entidade Turma.
 * @author fernando_paladini
 */
@Entity
public class Turma {
    
    // Atributos
    private int id;
    private String nome;
    private Curso curso;
    private ArrayList<Aula> aulas;
    private int turno;
    
    // Construtores
    public Turma() {
    	super();
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

    public Turma(int id, String nome, Curso curso, int turno) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.turno = turno;
	}

	public Turma(int id, String nome, Curso curso, ArrayList<Aula> aulas,
			int turno) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.aulas = aulas;
		this.turno = turno;
	}

	public Turma(String nome, Curso idCurso) {
        this.nome = nome;
        this.curso = idCurso;
    }
    
    // Métodos
    public String texto() {
		if (nome==null) {
			 return "null";
		}
    	return nome;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso idCurso) {
        this.curso = idCurso;
    }

	public ArrayList<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(ArrayList<Aula> aulas) {
		this.aulas = aulas;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

}
