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
 * Classe que define o objeto Professor.
 * @author fernando_paladini
 */
@Entity
public class Professor {
    
    private int id;
    private String nome;
    private ArrayList<Disciplina> disciplina;
    private boolean[][] disponibilidade;
    Instituicao i = Instituicao.getInstance();
    
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
    
    public Professor(String nome, ArrayList<Disciplina> disciplina, int id) {
		super();
		this.nome = nome;
		this.disciplina = disciplina;
		this.id = id;
		inicializarDisponibilidade(5, 3);
		i.addListaProfessores(this);
	}
    
    // Métodos
    public String texto() {
		if (nome==null) {
			 return "null";
		}
    	return nome;
	}
    
    public void professorAdicionarAula(Aula aula) {
		disponibilidade[aula.getDia().getId()][aula.getPeriodo().getId()] = false;
	}

	public void addDisciplina(Disciplina disciplina) {
		this.disciplina.add(disciplina);
	}
	
	public boolean retornadisponibilidade(int dia, int turno) {
		return disponibilidade[dia][turno];
	}
    
    private void inicializarDisponibilidade() {
    	disponibilidade = new boolean[i.getListaDias().size()][i
				.getListaPeriodos().size()];
	}

	private void inicializarDisponibilidade(int qtdDias, int qtdTurnos) {
		disponibilidade = new boolean[qtdDias][qtdTurnos];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				disponibilidade[i][j] = false;
			}
		}
	}
	public void alocar(int qtdDias, int qtdTurnos){
		disponibilidade[qtdDias][qtdTurnos]=true;
	}
    
    
    /**
     * Método para adicionar disciplina ao professor
     * @param disciplina 
     */
    public void adicionarDisciplina(Disciplina disciplina){
        getDisciplina().add(disciplina);
    }
    
    /**
     * Método para remover disciplina do professor
     * @param disciplina 
     */
    public void removerDisciplina(Disciplina disciplina){
        getDisciplina().remove(disciplina);
    }
    
    // Métodos
    
    
    public int getId() {
        return id;
    }

    public ArrayList<Disciplina> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(ArrayList<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}

	public boolean[][] getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean[][] disponibilidade) {
		this.disponibilidade = disponibilidade;
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
