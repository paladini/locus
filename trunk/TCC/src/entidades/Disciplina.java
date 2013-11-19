/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Classe que define o objeto Disciplina.
 * @author fernando_paladini
 */
@Entity
public class Disciplina {
    
    // Variáveis
	
	// TODO: Verificar todo o código do Hibernate.
	// TODO: Dúvidas:
	// 			- Para relações muitos-para-muitos preciso criar um arraylist nas duas classes envolvidas?
	//	        - A relação ManyToMany que estou fazendo está certa?
	//			- Precisa criar a entidade "Aula"? 
	//			
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDisciplina")
    private int id;

	private String nome;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="Curso_has_Disciplina",
    		joinColumns = @JoinColumn(name="Disciplina_idDisciplina"),
    		inverseJoinColumns = @JoinColumn(name="Curso_idCurso")
    		)
    private ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
    		name="Disciplina_has_Professor",
    		joinColumns = @JoinColumn(name="Disciplina_idDisciplina"),
    		inverseJoinColumns = @JoinColumn(name="Professor_idProfessor")
    		)
	private ArrayList<Professor> professores = new ArrayList<Professor>();

	// Construtores
    public Disciplina(int id, String nome, ArrayList<Curso> cursos){
    	this.id = id;
        this.nome = nome;
        this.cursos = cursos;
    }
	
	public Disciplina(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Disciplina() {
    }
    
    // Métodos
    public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
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
        if (!(nome.isEmpty() || nome == " " || nome == "  ")){
        	this.nome = nome;
        }
    }
    
    
    public ArrayList<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}

    // ToString - NÃO ALTERAR UMA VIRGULA!
//    @Override
//    public String toString() {
//        return nome;
//    }
    
}
