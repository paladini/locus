package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import control.ControleCurso;
import control.ControleEnsalamento;
import control.ControleProfessor;
import control.ControleSala;
import control.ControleTurma;
import entidades.Aula;
//import control.ControleEscola;
import entidades.Curso;
import entidades.Dia;
import entidades.Disciplina;
import entidades.Professor;
import entidades.Sala;
import entidades.Turma;
import entidades.Turno;

// Troquei de SessionScoped pra view Scoped não sei pq diabos.
@ManagedBean(name = "ensalamentoMBean")
@ViewScoped
public class EnsalamentoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControleEnsalamento controleEnsalamento = ControleEnsalamento
			.getInstance();
	private ScheduleModel eventModel;

	private String professor = "";
	private String turma = "";
	private String curso = "";
	private String sala = "";
	private ArrayList<Professor> listaProfessores;
	private ArrayList<Turma> listaTurmas;
	private ArrayList<Curso> listaCursos;
	private ArrayList<Sala> listaSalas;


	private SelectItem professorSelecionado = new SelectItem();
	private SelectItem turmaSelecionado = new SelectItem();
	private SelectItem cursoSelecionado = new SelectItem();
	private SelectItem salaSelecionado = new SelectItem();

	
	public EnsalamentoMBean() {
		eventModel = new DefaultScheduleModel();

		// Carregando os valores do ensalamento antigo na tabela
		ArrayList<Aula> ensalado = controleEnsalamento.Consultar();
		if (ensalado != null) {
			criarTabela(ensalado);
		}

		if (listaProfessores == null) {
			ControleProfessor cp = ControleProfessor.getInstance();
			listaProfessores = cp.consultar();
		}
		if (listaTurmas == null) {
			ControleTurma ct = ControleTurma.getInstance();
			listaTurmas = ct.consultar();
		}
		if (listaSalas == null) {
			ControleSala cs = ControleSala.getInstance();
			listaSalas = cs.consultar();
		}
		if (listaCursos == null) {
			ControleCurso cc = ControleCurso.getInstance();
			listaCursos = cc.consultar();
		}

		// Curso curso1 = new Curso("Tec. Inf.", 1, null, null);
		// Turma turma1 = new Turma(1, "V2", curso1, 1);
		// Sala sala1 = new Sala("G24", 1);
		// Disciplina disciplina1 = new Disciplina("POO2", 1,
		// new ArrayList<Professor>());
		// Professor professor1 = new Professor("Daniel",
		// new ArrayList<Disciplina>(), 1);
		// Turno turno = new Turno("Vesp.");
		// Dia dia = new Dia("segunda");
		// Aula aula = new Aula(professor1, turma1, sala1, turno, curso1,
		// disciplina1, dia, turno, 1);
		// adicionarEvento(aula.texto2(), GregorianCalendar.FRIDAY);
		//
		// adicionarEvento("teste \n teste2", GregorianCalendar.MONDAY);
		// adicionarAulas();
	}

	// TODO FALAR DANIEL M�TODO NAO EST� ADICIONANDO EVENTO
	private void adicionarEvento(String descricao,
			int diaSemanaGregorianCalendar) {
		GregorianCalendar calendarioInicio = (GregorianCalendar) GregorianCalendar
				.getInstance();
		calendarioInicio.set(GregorianCalendar.DAY_OF_WEEK,
				diaSemanaGregorianCalendar);
		calendarioInicio.set(GregorianCalendar.HOUR_OF_DAY, 14);
		calendarioInicio.set(GregorianCalendar.MINUTE, 0);

		GregorianCalendar calendarioFim = (GregorianCalendar) GregorianCalendar
				.getInstance();
		calendarioFim.set(GregorianCalendar.DAY_OF_WEEK,
				diaSemanaGregorianCalendar);
		calendarioFim.set(GregorianCalendar.HOUR_OF_DAY, 18);
		calendarioInicio.set(GregorianCalendar.MINUTE, 0);

		DefaultScheduleEvent evento = new DefaultScheduleEvent(descricao,
				calendarioInicio.getTime(), calendarioFim.getTime());

		// Criando o evento com corzinhas viadas
		if (descricao.contains("(A contratar)")) {
			evento.setStyleClass("fc-event-inner-contratar");
		}else{
			evento.setStyleClass("fc-event-inner-normal");
		}

		eventModel.addEvent(evento);
//		System.out.println(calendarioInicio.getTime());
//		System.out.println(calendarioFim.getTime());
	}

	public Date getInitialDate() {

		// adicionarEvento("PWEB - Daniel - TINF", GregorianCalendar.MONDAY);

		GregorianCalendar calendario = (GregorianCalendar) GregorianCalendar
				.getInstance();
		// calendario.set(GregorianCalendar.DAY_OF_WEEK,
		// GregorianCalendar.MONDAY);
		// calendario.set(GregorianCalendar.DAY_OF_MONTH, 18);
		// calendario.set(GregorianCalendar.MONTH, GregorianCalendar.NOVEMBER);
		// calendario.set(GregorianCalendar.YEAR, 2013);
		System.out.println(calendario.getTime());
		return calendario.getTime();
	}

	public void Ensalar() {
		controleEnsalamento.Ensalar();
		controleEnsalamento.Persistir();

		// Cria uma nova tabela com os novos valores
		ArrayList<Aula> ensalado = controleEnsalamento.Consultar();
		criarTabela(ensalado);

	}

	private void criarTabela(ArrayList<Aula> ensalado) {

		// Limpa a tabela
		eventModel.clear();

		for (Aula a : ensalado) {

//			System.out.println("Dia da aula: " + a.getDia().getId());
			switch (a.getDia().getId()) {
			case 1:
				adicionarEvento(a.textoFinalEnsalamento(),
						GregorianCalendar.MONDAY);
				break;

			case 2:
				adicionarEvento(a.textoFinalEnsalamento(),
						GregorianCalendar.TUESDAY);
				break;

			case 3:
				adicionarEvento(a.textoFinalEnsalamento(),
						GregorianCalendar.WEDNESDAY);
				break;

			case 4:
				adicionarEvento(a.textoFinalEnsalamento(),
						GregorianCalendar.THURSDAY);
				break;

			case 5:
				adicionarEvento(a.textoFinalEnsalamento(),
						GregorianCalendar.FRIDAY);
				break;

			case 6:
				adicionarEvento(a.textoFinalEnsalamento(),
						GregorianCalendar.SATURDAY);
				break;

			case 7:
				adicionarEvento(a.textoFinalEnsalamento(),
						GregorianCalendar.SUNDAY);
				break;

			default:
				break;
			}
		}
	}

	public ArrayList<Professor> getValuesComboBoxProfessor() {

		// SelectItem si = new SelectItem();
		ArrayList<Professor> professores = controleEnsalamento
				.ConsultarByProfessor();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Professor professor : professores) {
			listaComboBox.add(new SelectItem(professor.getId(), professor
					.getNome()));
		}
		// return listaComboBox;
		return professores;
	}

	public ArrayList<Turma>  getValuesComboBoxTurma() {

		// SelectItem si = new SelectItem();
		ArrayList<Turma> turmas = controleEnsalamento.ConsultarByTurma();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Turma turma : turmas) {
			listaComboBox.add(new SelectItem(turma.getId(), turma.getNome()));
		}
		return turmas;
	}

	public ArrayList<Sala> getValuesComboBoxSala() {

		// SelectItem si = new SelectItem();
		ArrayList<Sala> salas = controleEnsalamento.ConsultarBySala();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Sala sala : salas) {
			listaComboBox.add(new SelectItem(sala.getId(), sala.getNome()));
		}

		return salas;
	}

	public ArrayList<Curso> getValuesComboBoxCurso() {

		// SelectItem si = new SelectItem();
		ArrayList<Curso> cursos = controleEnsalamento.ConsultarByCurso();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Curso curso : cursos) {
			listaComboBox.add(new SelectItem(curso.getId(), curso.getNome()));
		}

		return cursos;
	}

	public void consultarByProfessor() {
		controleEnsalamento.ConsultarByProfessor();
	}

	public void consultarByProfessor(int id) {
		controleEnsalamento.ConsultarByProfessor(id);

	}


	private String var;
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public void valueChangeMethod(ValueChangeEvent e){
		System.out.println("ENTROU ASD");
	}
	
	public void consultarByProfessorSelectItem(ValueChangeEvent e) { // SelectItem
																		// professor

		System.out.println("ENTROU Professor");
		
		professor = e.getNewValue().toString();
		ControleProfessor cp = ControleProfessor.getInstance();
		Professor professorBanco = cp.consultar(professor);

		int id = professorBanco.getId();
		System.out.println("Id do professor no selectItem: " + id);
		ArrayList<Aula> ensalado = controleEnsalamento.ConsultarByProfessor(id);
		criarTabela(ensalado);

	}

	public void ConsultarByTurmaSelectItem(ValueChangeEvent e) {
		// turma

		System.out.println("ENTROU Turma");
		
		turma = e.getNewValue().toString();
		ControleTurma ct = ControleTurma.getInstance();
		Turma turmaBanco = ct.consultar(turma);

		int id = turmaBanco.getId();
		System.out.println("Id da turma no selectItem: " + id);
		ArrayList<Aula> ensalado = controleEnsalamento.ConsultarByTurma(id);
		criarTabela(ensalado);
}

	public void ConsultarBySalaSelectItem(ValueChangeEvent e) {
		// sala

		System.out.println("ENTROU Sala");
		
		sala = e.getNewValue().toString();
		ControleSala cs = ControleSala.getInstance();
		Sala salaBanco = cs.consultar(sala);

		int id = salaBanco.getId();
		System.out.println("Id da sala no selectItem: " + id);
		ArrayList<Aula> ensalado = controleEnsalamento.ConsultarBySala(id);
		criarTabela(ensalado);

	}

	public void ConsultarByCursoSelectItem(ValueChangeEvent e) {
		// curso

		System.out.println("ENTROU Curso");
		
		curso = e.getNewValue().toString();
		ControleCurso cc = ControleCurso.getInstance();
		Curso cursoBanco = cc.consultar(curso);

		int id = cursoBanco.getId();
		System.out.println("Id do curso no selectItem: " + id);
		ArrayList<Aula> ensalado = controleEnsalamento.ConsultarByCurso(id);
		criarTabela(ensalado);

	}

	public ArrayList<Aula> getEnsalamento() {
		return controleEnsalamento.getAulas();
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	@Deprecated
	public void adicionarAulas() {
		Ensalar();
		ArrayList<Aula> array = getEnsalamento();

		for (int i = 0; i < array.size(); i++) {
			String descricao = array.get(i).texto2();
			int diaSemanaGregorianCalendar = 0;
			if (array.get(i).getDia().getNome() == "seg") {
				diaSemanaGregorianCalendar = GregorianCalendar.MONDAY;
			}
			if (array.get(i).getDia().getNome() == "ter") {
				diaSemanaGregorianCalendar = GregorianCalendar.TUESDAY;
			}
			if (array.get(i).getDia().getNome() == "qua") {
				diaSemanaGregorianCalendar = GregorianCalendar.WEDNESDAY;
			}
			if (array.get(i).getDia().getNome() == "qui") {
				diaSemanaGregorianCalendar = GregorianCalendar.THURSDAY;
			}
			if (array.get(i).getDia().getNome() == "sex") {
				diaSemanaGregorianCalendar = GregorianCalendar.FRIDAY;
			}

			adicionarEvento(descricao, diaSemanaGregorianCalendar);
		}
	}

	@Deprecated
	public void adicionarAulasLista(ArrayList<Aula> arrayList) {

		for (int i = 0; i < arrayList.size(); i++) {
			String descricao = arrayList.get(i).texto2();
			int diaSemanaGregorianCalendar = 0;
			if (arrayList.get(i).getDia().getNome() == "seg") {
				diaSemanaGregorianCalendar = GregorianCalendar.MONDAY;
			}
			if (arrayList.get(i).getDia().getNome() == "ter") {
				diaSemanaGregorianCalendar = GregorianCalendar.TUESDAY;
			}
			if (arrayList.get(i).getDia().getNome() == "qua") {
				diaSemanaGregorianCalendar = GregorianCalendar.WEDNESDAY;
			}
			if (arrayList.get(i).getDia().getNome() == "qui") {
				diaSemanaGregorianCalendar = GregorianCalendar.THURSDAY;
			}
			if (arrayList.get(i).getDia().getNome() == "sex") {
				diaSemanaGregorianCalendar = GregorianCalendar.FRIDAY;
			}

			adicionarEvento(descricao, diaSemanaGregorianCalendar);
		}
	}

	public ControleEnsalamento getControleEnsalamento() {
		return controleEnsalamento;
	}

	public void setControleEnsalamento(ControleEnsalamento controleEnsalamento) {
		this.controleEnsalamento = controleEnsalamento;
	}

	public SelectItem getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(SelectItem professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public SelectItem getTurmaSelecionado() {
		return turmaSelecionado;
	}

	public void setTurmaSelecionado(SelectItem turmaSelecionado) {
		this.turmaSelecionado = turmaSelecionado;
	}

	public SelectItem getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(SelectItem cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public SelectItem getSalaSelecionado() {
		return salaSelecionado;
	}

	public void setSalaSelecionado(SelectItem salaSelecionado) {
		this.salaSelecionado = salaSelecionado;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public ArrayList<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(ArrayList<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public ArrayList<Turma> getListaTurmas() {
		return listaTurmas;
	}

	public void setListaTurmas(ArrayList<Turma> listaTurmas) {
		this.listaTurmas = listaTurmas;
	}

	public ArrayList<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(ArrayList<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public ArrayList<Sala> getListaSalas() {
		return listaSalas;
	}

	public void setListaSalas(ArrayList<Sala> listaSalas) {
		this.listaSalas = listaSalas;
	}

	
	
}