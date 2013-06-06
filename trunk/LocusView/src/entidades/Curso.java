package entidades;

import java.util.ArrayList;

public class Curso {

	//variaveis
	private String nome;
	private int id;
	private ArrayList<Turma> turmas;
	
	//contrutores
	public Curso(String nome, int id, ArrayList<Turma> turmas) {
		super();
		this.nome = nome;
		this.id = id;
		this.turmas = turmas;
	}

	public Curso() {
		super();
	}

    @Override
    public String toString() {
        return "Curso{" + "nome=" + nome + ", id=" + id + ", turmas=" + turmas + '}';
    }

	//codigos
	
	void adicionarTurma(Turma turma){
	turmas.add(turma);
	}
	
	
	
	//gets e sets
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
