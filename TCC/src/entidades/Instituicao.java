package entidades;

import java.util.ArrayList;
import java.util.List;

import model.CursoDAO;
import model.DiaDAO;
import model.DisciplinaDAO;
import model.ProfessorDAO;
import model.SalaDAO;
import model.TurmaDAO;
import model.TurnoDAO;

public class Instituicao {

	// variaveis
	
	
	private static Instituicao singleton;
	private String nome;
	private int id;
	private ArrayList<Sala> listaSala;
	private ArrayList<Curso> listaCurso;
	private ArrayList<Disciplina> listaDisciplinas;
	private ArrayList<Professor> listaProfessores;
	private ArrayList<Turno> listaPeriodos;
	private ArrayList<Dia> listaDias;
	private ArrayList<Turma> listaTurmas;
	private ArrayList<Aula> listAulas;
	SalaDAO salaDAO = new SalaDAO();
	CursoDAO cursoDAO = new CursoDAO();
	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	ProfessorDAO professorDAO = new  ProfessorDAO();
	TurnoDAO turnoDAO = new TurnoDAO();
	DiaDAO diaDAO = new DiaDAO();
	TurmaDAO turmaDAO = new TurmaDAO();
	

	// contrutores
	private Instituicao(){
		init();
	}
	
	public void init(){
		//TODO alterar a inicialização para puxar os dados do banco
		listaSala =salaDAO.consultar();
		listaCurso =cursoDAO.consultar();
		listaDisciplinas = disciplinaDAO.consultar();
		listaProfessores = professorDAO.consultar();
		listaPeriodos = turnoDAO.consultar();
		listaDias = diaDAO.consultar();
		listaTurmas = turmaDAO.consultar();
		listAulas = new ArrayList<Aula>();
		
	}
	
	public static Instituicao getInstance(){
		if (singleton == null){
			singleton = new Instituicao();
		}
		return singleton;
	}

	public String texto() {
		if (nome==null) {
			 return "null";
		}
    	return nome;
	}	
	public Instituicao(String nome, ArrayList<Sala> listaSala,
			ArrayList<Curso> listaCurso,
			ArrayList<Disciplina> listaDisciplinas,
			ArrayList<Professor> listaProfessores,
			ArrayList<Turno> listaPeriodos, ArrayList<Dia> listaDias,
			ArrayList<Turma> listaTurmas, int id) {
		super();
		this.nome = nome;
		this.listaSala = listaSala;
		this.listaCurso = listaCurso;
		this.listaDisciplinas = listaDisciplinas;
		this.listaProfessores = listaProfessores;
		this.listaPeriodos = listaPeriodos;
		this.listaDias = listaDias;
		this.listaTurmas = listaTurmas;
		this.id = id;
	}

	// codigo

	// adicionar

	public void addListaSala(Sala Sala) {
		listaSala.add(Sala);

	}

	public void addListaCurso(Curso Curso) {
		listaCurso.add(Curso);
	}

	public void addListaDisciplinas(Disciplina Disciplina) {
		listaDisciplinas.add(Disciplina);
	}

	public void addListaProfessores(Professor Professor) {
		listaProfessores.add(Professor);
	}

	public void addListaTurmas(Turma Turma) {
		listaTurmas.add(Turma);
	}

	public void addListAulas(Aula Aula) {
		listAulas.add(Aula);
	}

	public void addListaPeriodos(Turno Periodo) {
		listaPeriodos.add(Periodo);
	}

	public void addListaDias(Dia dia) {
		listaDias.add(dia);
	}

	// gets e sets
	public String getNome() {
		return nome;

	}

	public void setNome(String nome1) {
		nome = nome1;
	}

	public ArrayList<Sala> getListaSala() {
		return listaSala;
	}

	public void setListaSala(ArrayList<Sala> listaSala1) {
		listaSala = listaSala1;
	}

	public ArrayList<Curso> getListaCurso() {
		return listaCurso;
	}

	public void setListaCurso(ArrayList<Curso> listaCurso1) {
		listaCurso = listaCurso1;
	}

	public ArrayList<Disciplina> getListaDisciplinas() {
		return listaDisciplinas;
	}

	public void setListaDisciplinas(ArrayList<Disciplina> listaDisciplinas1) {
		listaDisciplinas = listaDisciplinas1;
	}

	public ArrayList<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(ArrayList<Professor> listaProfessores1) {
		listaProfessores = listaProfessores1;
	}

	public ArrayList<Turno> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(ArrayList<Turno> listaPeriodos1) {
		listaPeriodos = listaPeriodos1;
	}

	public ArrayList<Dia> getListaDias() {
		return listaDias;
	}

	public void setListaDias(ArrayList<Dia> listaDias1) {
		listaDias = listaDias1;
	}

	public ArrayList<Aula> getListAulas() {
		return listAulas;
	}

	public void setListAulas(ArrayList<Aula> listAulas1) {
		listAulas = listAulas1;
	}

	public ArrayList<Turma> getListaTurmas() {
		if (listaTurmas != null) {
			return listaTurmas;
		} else {
			return new ArrayList<Turma>();
		}
	}

	public void setListaTurmas(ArrayList<Turma> listaTurmas1) {
		listaTurmas = listaTurmas1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
