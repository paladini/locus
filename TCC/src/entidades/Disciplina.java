/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

/**
 * Classe que define o objeto Disciplina.
 * @author fernando_paladini
 */
public class Disciplina {
    
    private int id;
	private String nome;
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	private ArrayList<Curso> cursos = new ArrayList<Curso>();

//	 Construtores
    public Disciplina(int id, String nome, ArrayList<Curso> cursos){
    	this.id = id;
        this.nome = nome;
        this.cursos = cursos;
    }
	
	public Disciplina(int id, String nome) {
        super();
		this.id = id;
        this.nome = nome;
    }
	public String texto() {
		if (nome==null) {
			 return "null";
		}
    	return nome;
	}
	
	public Disciplina() {
		super();
	}
    
    public Disciplina(String nome, int id, ArrayList<Professor> professores) {
		super();
		this.nome = nome;
		this.id = id;
		this.professores = professores;
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
        if (!(nome.isEmpty() || nome == " " || nome == "  ")){
        	this.nome = nome;
        }
    }
    
    public void addProfessores(Professor professor) {
		professores.add(professor);
	}
    
    public ArrayList<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
    
}
