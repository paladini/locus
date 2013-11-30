package mbeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import control.ControleEnsalamento;
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
public class EnsalamentoMBean {

	private ControleEnsalamento controleEnsalamento = ControleEnsalamento
			.getInstance();
	private ScheduleModel eventModel;

	private SelectItem professorSelecionado = new SelectItem();
	private SelectItem turmaSelecionado = new SelectItem();
	private SelectItem cursoSelecionado = new SelectItem();
	private SelectItem salaSelecionado = new SelectItem();

	public EnsalamentoMBean() {
		eventModel = new DefaultScheduleModel();

		// Carregando os valores do ensalamento antigo na tabela
		ArrayList<Aula> ensalado = controleEnsalamento.Consultar();
		if(ensalado != null){
			criarTabela(ensalado);
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
		eventModel.addEvent(new DefaultScheduleEvent(descricao,
				calendarioInicio.getTime(), calendarioFim.getTime()));
		System.out.println(calendarioInicio.getTime());
		System.out.println(calendarioFim.getTime());
	}

	public Date getInitialDate() {

//		adicionarEvento("PWEB - Daniel - TINF", GregorianCalendar.MONDAY);

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

			System.out.println("Dia da aula: " + a.getDia().getId());
			switch (a.getDia().getId()) {
			case 1:
				adicionarEvento(a.textoFinalEnsalamento(), GregorianCalendar.MONDAY);
				break;

			case 2:
				adicionarEvento(a.textoFinalEnsalamento(), GregorianCalendar.TUESDAY);
				break;

			case 3:
				adicionarEvento(a.textoFinalEnsalamento(), GregorianCalendar.WEDNESDAY);
				break;

			case 4:
				adicionarEvento(a.textoFinalEnsalamento(), GregorianCalendar.THURSDAY);
				break;

			case 5:
				adicionarEvento(a.textoFinalEnsalamento(), GregorianCalendar.FRIDAY);
				break;

			case 6:
				adicionarEvento(a.textoFinalEnsalamento(), GregorianCalendar.SATURDAY);
				break;

			case 7:
				adicionarEvento(a.textoFinalEnsalamento(), GregorianCalendar.SUNDAY);
				break;

			default:
				break;
			}
		}
	}

	public Collection<SelectItem> getValuesComboBoxProfessor() {

		// SelectItem si = new SelectItem();
		ArrayList<Professor> professores = controleEnsalamento
				.ConsultarByProfessor();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Professor professor : professores) {
			listaComboBox.add(new SelectItem(professor.getId(), professor
					.getNome()));
		}
		return listaComboBox;

	}

	public Collection<SelectItem> getValuesComboBoxTurma() {

		// SelectItem si = new SelectItem();
		ArrayList<Turma> turmas = controleEnsalamento.ConsultarByTurma();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Turma turma : turmas) {
			listaComboBox.add(new SelectItem(turma.getId(), turma.getNome()));
		}
		return listaComboBox;
	}

	public Collection<SelectItem> getValuesComboBoxSala() {

		// SelectItem si = new SelectItem();
		ArrayList<Sala> salas = controleEnsalamento.ConsultarBySala();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Sala sala : salas) {
			listaComboBox.add(new SelectItem(sala.getId(), sala.getNome()));
		}

		return listaComboBox;
	}

	public Collection<SelectItem> getValuesComboBoxCurso() {

		// SelectItem si = new SelectItem();
		ArrayList<Curso> cursos = controleEnsalamento.ConsultarByCurso();
		Collection<SelectItem> listaComboBox = new ArrayList<SelectItem>();

		for (Curso curso : cursos) {
			listaComboBox.add(new SelectItem(curso.getId(), curso.getNome()));
		}

		return listaComboBox;
	}

	public void consultarByProfessor() {
		controleEnsalamento.ConsultarByProfessor();
	}

	public void consultarByProfessor(int id) {
		controleEnsalamento.ConsultarByProfessor(id);
	}

	public void ConsultarByProfessorSelectItem(SelectItem professor) {
		int id = (Integer) professor.getValue();
		adicionarAulasLista(controleEnsalamento.ConsultarByProfessor(id));
	}

	public void ConsultarByTurmaSelectItem(SelectItem turma) {
		int id = (Integer) turma.getValue();
		adicionarAulasLista(controleEnsalamento.ConsultarByTurma(id));
	}

	public void ConsultarBySalaSelectItem(SelectItem sala) {
		int id = (Integer) sala.getValue();
		adicionarAulasLista(controleEnsalamento.ConsultarBySala(id));
	}

	public void ConsultarByCursoSelectItem(SelectItem curso) {
		int id = (Integer) curso.getValue();
		adicionarAulasLista(controleEnsalamento.ConsultarByCurso(id));
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

}