package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import control.ControleCurso;
import control.ControleDisciplina;
import entidades.Curso;
import entidades.Disciplina;



/*
 * 
 * TODO Curso (ver arquivo) 
 *  BEAN:
 *  
 *  JSF: 
 * 
 * 
 * 
 */







@ManagedBean(name = "cursoMBean")
@SessionScoped
public class CursoMBean {

	private ControleCurso controleCurso;

	// Lista de todas as disciplinas
	private static ArrayList<Curso> lista;

	// Lista de disciplinas pesquisadas (fiz isso para evitar muitas consultas ao banco de dados)
	private static ArrayList<Curso> listaPesquisa;
	private Curso selecionado;

	private int id;
	private String nome;

	public CursoMBean() {

		if (lista == null) {
			lista = new ArrayList<Curso>();
		}

		if (this.getNome() == null) {
			this.setNome("");
		}

		controleCurso = ControleCurso.getInstance();

//		atualizarListagem();
	}
	
	/**
	 * Insere um objeto no banco de dados. 
	 * @return Retorna para a mesma página.
	 */
	public String cadastrar() {

		Curso curso = new Curso();
		
		if (!(this.getNome().isEmpty() || this.getNome() == " " || this
				.getNome() == "  ")) {
			
			// Atribuindo nome ao curso
			curso.setNome(this.getNome());
			
			// Adicionando curso ao banco de dados
			controleCurso.adicionar(curso);
			
			System.out.println("Inserido(a). " + curso.getClass().toString());
		} else {
			System.out.println("Erro: Não inserido(a). " + curso.getClass().toString());
		}

		limparCampos();
//		atualizarListagem();

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
	 * @return Retorna para a mesma página.
	 */
	public String editar() {

		if (this.getSelecionado() != null){
			// Pegando ID da disciplina selecionada e o nome editado pelo usuário (no edit.xhtml)
			String nome = this.getSelecionado().getNome();
			int id = this.getSelecionado().getId();
			
			// Verificando dados
			System.out.println("---------------------");
			System.out.println("nome editado: " + this.getSelecionado().getNome());
			System.out.println("---------------------");
			
			// Atualizando disciplina
//			Curso atualizar = new Curso(id, nome);
//			controleCurso.atualizar(atualizar);
			
			// Atualizando dados da disciplinaSelecionada e das listas
//			Disciplina disciplina = controleDisciplina.consultaDisciplina(id);
//			disciplinaSelecionada = disciplina;
			
//			atualizarListagem();
		}

		return null;
	}

	public String deletar() {
		
		// Prevenindo para ID da disciplina selecionada não ser null
//		if(this.getDisciplinaSelecionada() != null){
//			
//			int id = this.getDisciplinaSelecionada().getId();
//			
//			// Limpando a disciplina selecionada antes de excluir do banco/lista (se não dá erro)
//			setDisciplinaSelecionada(null);
//			
//			// Removando da banco/lista
//			controleDisciplina.remover(id);
//			System.out.println("Disciplina excluída!");
//
//			// Atualizando lista
//			atualizarListagem();
//		}

		 return null;
	}

	/**
	 * Atualizar a listagem de disciplinas (chamado do próprio Bean, ou seja, na
	 * primeira vez que executa) e a lista de disciplinasPesquisadas - agora listaDisciplinasPesquisadas 
	 * tem todas as disciplinas.
	 */
//	public void atualizarListagem() {
//		this.setListaDisciplinas(controleDisciplina.consulta());
//		listaDisciplinasPesquisadas = (ArrayList<Disciplina>) this.getListaDisciplinas().clone();
//	}

	/**
	 * Atualiza a listagem de disciplinas (chamado da View) TODO: Futuramente
	 * implementar de forma que não seja case-sensitive?
	 * 
	 * @param e
	 */
//	public void atualizarListagemPesquisa(ValueChangeEvent e) {
//		if (e.getNewValue().toString().length() > 0) {
//			this.pegarDisciplinaLista(e.getNewValue().toString());
//			System.out.println("Disciplina pesquisada com nome: "
//					+ e.getNewValue().toString());
//		} else {
//			atualizarListagem();
//			System.out.println("Deveria mostrar todas as disciplinas agora.");
//		}
//	}
	
	public void limparSelecionadosDataTable() {
		RequestContext.getCurrentInstance().execute(
				"resultadosDisciplina.unselectAllRows()");
	}

	/**
	 * Adiciona na listaDisciplinasPesquisadas as disciplinas que contém no seu
	 * nome o termo pesquisado pelo usuário. Criei este novo arrayList pq é
	 * melhor usar um ArrayList ao invés de ficar fazendo consultas toda hora no
	 * banco.
	 * 
	 * @param termo
	 */
//	private void pegarDisciplinaLista(String termo) {
//
//		for (int i = 0; i < this.getListaDisciplinas().size(); i++) {
//
//			Disciplina estaDisciplina = this.getListaDisciplinas().get(i);
//			boolean disciplinaTemEsseTermo = estaDisciplina.getNome().contains(
//					termo);
//
//			if (disciplinaTemEsseTermo) {
//				if (!containsListaDisciplina(estaDisciplina)) {
//					this.getListaDisciplinasPesquisadas().add(estaDisciplina);
//				} else {
//					// não faz nada, pois a disciplina já está na lista
//				}
//			} else { // disciplina não tem esse termo
//				if (!containsListaDisciplina(estaDisciplina)) {
//					// não faz nada, pois a disciplina sem o termo não está na
//					// lista
//				} else {
//					this.getListaDisciplinasPesquisadas()
//							.remove(estaDisciplina);
//				}
//			}
//		}
//	}

//	private boolean containsListaDisciplina(Disciplina estaDisciplina) {
//
//		for (Disciplina d : this.getListaDisciplinasPesquisadas())
//			if (d.getNome().equals(estaDisciplina.getNome()))
//				return true;
//
//		return false;
//	}

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

	public ControleCurso getControleCurso() {
		return controleCurso;
	}

	public void setControleCurso(ControleCurso controleCurso) {
		this.controleCurso = controleCurso;
	}

	public static ArrayList<Curso> getLista() {
		return lista;
	}

	public static void setLista(ArrayList<Curso> lista) {
		CursoMBean.lista = lista;
	}

	public static ArrayList<Curso> getListaPesquisa() {
		return listaPesquisa;
	}

	public static void setListaPesquisa(ArrayList<Curso> listaPesquisa) {
		CursoMBean.listaPesquisa = listaPesquisa;
	}

	public Curso getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Curso selecionado) {
		this.selecionado = selecionado;
	}

}