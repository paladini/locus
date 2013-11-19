package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import control.ControleDisciplina;
import control.ControleProfessor;
import entidades.Disciplina;
import entidades.Professor;

/*
 * 
 * TODO Professor (ver arquivo) 
 *  BEAN:
 *  
 *  JSF: 
 * 
 * 
 * 
 */

@ManagedBean(name = "professorMBean")
@ViewScoped
public class ProfessorMBean {

	private ControleProfessor controleProfessor;
	private ControleDisciplina controleDisciplina;

	// Lista de todas as disciplinas
	private ArrayList<Professor> lista;

	// Lista de objetos pesquisados. Vai ser a lista apresentada para o usuário,
	// já configurada para fazer filtro
	// de acordo com termos inseridos pelo usuário.
	private ArrayList<Professor> listaPesquisa;

	// Objeto selecionado na dataTable
	private Professor selecionado;

	// Lista de todas as disciplinas (que ainda não estão associadas ao curso)
	private ArrayList<Disciplina> listaTodasDisciplinas;

	// Lista de disciplinas deste curso
	private ArrayList<Disciplina> listaDisciplinasDoProfessor;
	
	private String[] vetorDisciplinasDoProfessor;

	private int id;
	private String nome;

	public ProfessorMBean() {

		controleProfessor = ControleProfessor.getInstance();
		controleDisciplina = ControleDisciplina.getInstance();

		if (listaTodasDisciplinas == null) {
			listaTodasDisciplinas = controleDisciplina.consulta();
		}

		if (listaDisciplinasDoProfessor == null) {
			listaDisciplinasDoProfessor = new ArrayList<Disciplina>();
		}

		if (this.getNome() == null) {
			this.setNome("");
		}

		atualizarListagem();
	}

	/**
	 * Insere um objeto no banco de dados.
	 * 
	 * @return Retorna para a mesma página.
	 */
	public String cadastrar() {

		Professor professor = new Professor();

		if (!(this.getNome().isEmpty() || this.getNome() == " " || this
				.getNome() == "  ")) {

			// Atribuindo nome ao professor
			professor.setNome(this.getNome());
			professor.setListaDisciplinas(listaDisciplinasDoProfessor);

			// Adicionando professor ao banco de dados
			controleProfessor.adicionar(professor);

			// Limpando a lista de disciplinas do professor
			listaDisciplinasDoProfessor = new ArrayList<Disciplina>();

			System.out.println("Inserido(a). " + professor.toString());
		} else {
			System.out.println("Erro: Não inserido(a). " + professor.toString());
		}

		limparCampos();
		atualizarListagem();

		return null;
	}

	/**
	 * Deixa o campo de input vazio. Usado após inserir disciplina.
	 */
	private void limparCampos() {
		this.setNome("");
	}

	/**
	 * Edita / Atualiza um objeto no banco de dados.
	 * 
	 * @return Retorna para a mesma página.
	 */
	public String editar() {

		if (this.getSelecionado() != null) {

			// Pegando ID da disciplina selecionada e o nome editado pelo
			// usuário (no edit.xhtml)
			String nome = this.getSelecionado().getNome();
			int id = this.getSelecionado().getId();

			// Pegando disciplinas
			ArrayList<Disciplina> novasDisciplinas = new ArrayList<Disciplina>();
			for(int i = 0; i < vetorDisciplinasDoProfessor.length; i++){
				String nomeDisciplina = vetorDisciplinasDoProfessor[i];
				Disciplina d = controleDisciplina.consultaDisciplina(nomeDisciplina);
				novasDisciplinas.add(d);
			}
			
			// Atualizando disciplina
			Professor novoProfessor = new Professor(id, nome);
			novoProfessor.setListaDisciplinas(novasDisciplinas);
			controleProfessor.atualizar(novoProfessor);

			// Atualizando dados da disciplinaSelecionada e das listas
			Professor professor = controleProfessor.consultaProfessor(id);
			selecionado = professor;

			atualizarListagem();
		}

		return null;
	}

	public String deletar() {

		// Prevenindo para ID da disciplina selecionada não ser null
		if (this.getSelecionado() != null) {

			int id = this.getSelecionado().getId();

			// Limpando a disciplina selecionada antes de excluir do banco/lista
			// (se não dá erro)
			setSelecionado(null);

			// Removando da banco/lista
			Professor p = new Professor(id, "");
			controleProfessor.remover(p);
			System.out.println("Professor excluído!");

			// Atualizando lista
			atualizarListagem();
		}

		return null;
	}

	/**
	 * Atualizar a listagem de professores (chamado do próprio Bean, ou seja, na
	 * primeira vez que executa) e a lista de listaPesquisa.
	 */
	public void atualizarListagem() {
		lista = controleProfessor.consulta();
		listaPesquisa = (ArrayList<Professor>) getLista().clone();
	}

	/**
	 * Atualiza a listagem de professores (chamado da View) TODO: Futuramente
	 * implementar de forma que não seja case-sensitive?
	 * 
	 * @param e
	 */
	public void atualizarListagemPesquisa(ValueChangeEvent e) {
		if (e.getNewValue().toString().length() > 0) {
			this.pegarDisciplinaLista(e.getNewValue().toString());
			System.out.println("Professor pesquisado com nome: "
					+ e.getNewValue().toString());
		} else {
			atualizarListagem();
			System.out.println("Deveria mostrar todos os professores agora.");
		}
	}

	public void limparSelecionadosDataTable() {
		RequestContext.getCurrentInstance().execute(
				"resultados.unselectAllRows()");
	}

	/**
	 * Adiciona na listaDisciplinasPesquisadas as disciplinas que contém no seu
	 * nome o termo pesquisado pelo usuário. Criei este novo arrayList pq é
	 * melhor usar um ArrayList ao invés de ficar fazendo consultas toda hora no
	 * banco.
	 * 
	 * @param termo
	 */
	private void pegarDisciplinaLista(String termo) {

		for (int i = 0; i < getLista().size(); i++) {

			Professor esteCurso = getLista().get(i);
			boolean cursoTemEsseTermo = esteCurso.getNome().contains(termo);

			if (cursoTemEsseTermo) {
				if (!containsListaDisciplina(esteCurso)) {
					getListaPesquisa().add(esteCurso);
				} else {
					// não faz nada, pois a disciplina já está na lista
				}
			} else { // disciplina não tem esse termo
				if (!containsListaDisciplina(esteCurso)) {
					// não faz nada, pois a disciplina sem o termo não está na
					// lista
				} else {
					getListaPesquisa().remove(esteCurso);
				}
			}
		}
	}

	private boolean containsListaDisciplina(Professor esteCurso) {
		for (Professor p : getListaPesquisa()) {
			if (p.getNome().equals(esteCurso.getNome())) {
				return true;
			}
		}
		return false;
	}
	
	

	public ArrayList<Professor> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Professor> lista) {
		this.lista = lista;
	}

	public ArrayList<Professor> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(ArrayList<Professor> listaPesquisa) {
		this.listaPesquisa = listaPesquisa;
	}

	public Professor getSelecionado() {
		return selecionado;
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

	public ControleProfessor getControleProfessor() {
		return controleProfessor;
	}

	public void setControleProfessor(ControleProfessor controleProfessor) {
		this.controleProfessor = controleProfessor;
	}

	public ArrayList<Disciplina> getListaDisciplinasDoProfessor() {
		return listaDisciplinasDoProfessor;
	}

	public void setListaDisciplinasDoProfessor(
			ArrayList<Disciplina> listaDisciplinasDoProfessor) {
		this.listaDisciplinasDoProfessor = listaDisciplinasDoProfessor;
	}

	public String[] getVetorDisciplinasDoProfessor() {
		return vetorDisciplinasDoProfessor;
	}

	public void setVetorDisciplinasDoProfessor(String[] vetorDisciplinasDoProfessor) {
		this.vetorDisciplinasDoProfessor = vetorDisciplinasDoProfessor;
	}

	public void setSelecionado(Professor selecionado) {
		this.selecionado = selecionado;
		
		if (selecionado != null) {
			listaTodasDisciplinas = controleDisciplina.consulta();
			listaDisciplinasDoProfessor = controleProfessor.listaDisciplinasAssociadas(selecionado);
			
			vetorDisciplinasDoProfessor = new String[listaDisciplinasDoProfessor.size()];
			for(int i = 0; i < listaDisciplinasDoProfessor.size(); i++){
				vetorDisciplinasDoProfessor[i] = listaDisciplinasDoProfessor.get(i).getNome();
			}
		}
	}

	public ControleDisciplina getControleDisciplina() {
		return controleDisciplina;
	}

	public void setControleDisciplina(ControleDisciplina controleDisciplina) {
		this.controleDisciplina = controleDisciplina;
	}

	public ArrayList<Disciplina> getListaTodasDisciplinas() {
		return listaTodasDisciplinas;
	}

	public void setListaTodasDisciplinas(
			ArrayList<Disciplina> listaTodasDisciplinas) {
		this.listaTodasDisciplinas = listaTodasDisciplinas;
	}

	public ArrayList<Disciplina> getListaDisciplinasDoCurso() {
		return listaDisciplinasDoProfessor;
	}

	public void setListaDisciplinasDoCurso(
			ArrayList<Disciplina> listaDisciplinasDoCurso) {
		this.listaDisciplinasDoProfessor = listaDisciplinasDoCurso;
	}

	public String[] getVetorDisciplinasDoCurso() {
		return vetorDisciplinasDoProfessor;
	}

	public void setVetorDisciplinasDoCurso(String[] vetorDisciplinasDoCurso) {
		this.vetorDisciplinasDoProfessor = vetorDisciplinasDoCurso;
	}
	
	
	
}