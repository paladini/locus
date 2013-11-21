package entidades;

import java.util.ArrayList;

/**
 * Classe que define o objeto Curso.
 * 
 * @author fernando_paladini
 */
public class Curso {

	private int id;
	private String nome;

	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Turma> turmas;

	// Contrutores
	public String texto() {
		if (nome==null) {
			 return "null";
		}
    	return nome;
	}	
	public Curso() {
		super();
		disciplinas = new ArrayList<Disciplina>();

		Instituicao i = Instituicao.getInstance();
		i.addListaCurso(this);
	}

	public Curso(int id, String nome) {
		super();
		this.nome = nome;
		this.id = id;
	}

	public Curso(int id, String nome, ArrayList<Turma> turmas) {
		super();
		this.nome = nome;
		this.id = id;
		this.turmas = turmas;
	}

	public Curso(String nome, int id, ArrayList<Turma> turmas,
			ArrayList<Disciplina> disciplinas) {
		super();
		this.nome = nome;
		this.id = id;
		this.turmas = new ArrayList<Turma>();
		this.disciplinas = new ArrayList<Disciplina>();
		Instituicao i = Instituicao.getInstance();
		i.addListaCurso(this);

	}

	// Métodos
	public void addTurma(Turma turma) {
		turmas.add(turma);
	}

	public void addDicilpina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public void removerDisciplina(Disciplina disciplina) {
		getDisciplinas().remove(disciplina);
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

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
