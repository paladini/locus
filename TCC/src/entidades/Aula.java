package entidades;

public class Aula {

	// variaveis
	private Professor professor;
	private Turma turma;
	private Sala sala;
	private Turno turno;
	private Curso curso;
	private Disciplina disciplina;
	private String nome;
	private Dia dia;
	private int id;

	// dia ->turnos

	// contrutores
	public Aula(Professor professor, Turma turma, Sala sala, Turno periodo,
			Curso curso, Disciplina disciplina, Dia dia, Turno turno, int id) {
		this.professor = professor;
		this.turma = turma;
		this.sala = sala;
		this.turno = periodo;
		this.curso = curso;
		this.disciplina = disciplina;
		this.dia = dia;
		this.turno = turno;
		this.id = id;

		// Instituicao.addListAulas(this);
	}

	public Aula() {
		super();
		// Instituicao.addListAulas(this);
	}

	public Aula(String nome) {
		this.nome = nome;
	}

	// codigos
	public void adicionarAulaParaLista() {
		Instituicao i = Instituicao.getInstance();
		i.addListAulas(this);
	}

	
//	public String texto() {
//		return "Aula [professor=" + professor + ", turma=" + turma + ", sala="
//				+ sala + ", periodo=" + turno + ", curso=" + curso
//				+ ", disciplina=" + disciplina + ", nome=" + nome + ", Dia = "
//				+ dia + ",Turno =  " + turno + ", Id=  "+ id + "]";
//	}
	
	
	public String texto() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Aula [professor=" );
		sb.append( professor != null ? professor.texto() : "( A contratar )" );
		sb.append( ", turma=" );
		sb.append( turma.texto() );
		sb.append( ", sala="  );
		sb.append( sala.texto() );
		sb.append( ", disciplina=" );
		sb.append( disciplina.texto() );
		sb.append( ", nome=" );
		sb.append( nome );
		sb.append(", Dia = " );
		sb.append(dia.texto()  );
		sb.append( ",Turno =  " );
		sb.append( turno.texto() );
		sb.append( ", Id=  " );
		sb.append( id );
		sb.append( "]" );
//		return "Aula [professor=" + professor.texto() + ", turma="
//				+ turma.texto() + ", sala=" + sala.texto() + ", periodo="
//				+ turno.texto() + ", curso=" + curso.texto() + ", disciplina="
//				+ disciplina.texto() + ", nome=" + nome + ", Dia = "
//				+ dia.texto() + ",Turno =  " + turno.texto() + ", Id=  " + id
//				+ "]";
		return sb.toString();
	}

	// gets e sets
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Turno getPeriodo() {
		return turno;
	}

	public void setPeriodo(Turno periodo) {
		this.turno = periodo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
