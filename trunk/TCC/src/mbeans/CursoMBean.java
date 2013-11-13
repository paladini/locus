package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;

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
	private ControleDisciplina controleDisciplina;

	// Lista de todas as disciplinas
	private static ArrayList<Curso> lista;

	// Lista de objetos pesquisados. Vai ser a lista apresentada para o usuário, já configurada para fazer filtro 
	// de acordo com termos inseridos pelo usuário.
	private static ArrayList<Curso> listaPesquisa;
	
	// Objeto selecionado na dataTable
	private Curso selecionado;
	
	// Lista de todas as disciplinas (que ainda não estão associadas ao curso)
	private static ArrayList<Disciplina> listaTodasDisciplinas;
	
	// Lista de disciplinas deste curso
	private static ArrayList<Disciplina> listaDisciplinasDoCurso;
	
	private int id;
	private String nome;

	public CursoMBean() {

		controleCurso = ControleCurso.getInstance();
		controleDisciplina = ControleDisciplina.getInstance();
		
		if (listaTodasDisciplinas == null){
			listaTodasDisciplinas = controleDisciplina.consulta();
		}
		
		if (listaDisciplinasDoCurso == null){
			listaDisciplinasDoCurso = new ArrayList<Disciplina>();
		}
		
		if (this.getNome() == null) {
			this.setNome("");
		}
		
		atualizarListagem();
	}
	
	/**
	 * Insere um objeto no banco de dados. 
	 * @return Retorna para a mesma página.
	 */
	public String cadastrar() {

		Curso curso = new Curso();
//		listaDisciplinasDoCurso = new ArrayList<Disciplina>();
//		listaTodasDisciplinas = controleDisciplina.consulta();
		
		System.out.println("\n----------------------");
		System.out.println("Chegando disciplinas do curso: " + listaDisciplinasDoCurso.size() +"\n");
		for (Disciplina d : listaDisciplinasDoCurso) {
			System.out.println(d.toString());
		}
		System.out.println("----------------------\n");
		
		System.out.println("\n----------------------");
		System.out.println("Chegando todas disciplinas: \n");
		for(Disciplina d : listaTodasDisciplinas){
			System.out.println(d.toString());
		}
		
		System.out.println("----------------------\n");
		
		if (!(this.getNome().isEmpty() || this.getNome() == " " || this
				.getNome() == "  ")) {
			
			// Atribuindo nome ao curso
			curso.setNome(this.getNome());
//			curso.setDisciplina(listaDisciplinasDoCurso);
			
			// Limpando a lista de disciplinas do curso
			listaDisciplinasDoCurso = new ArrayList<Disciplina>();
			
			// Adicionando curso ao banco de dados
			controleCurso.adicionar(curso);
			
			System.out.println("Inserido(a). " + curso.toString());
		} else {
			System.out.println("Erro: Não inserido(a). " + curso.toString());
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
			Curso atualizar = new Curso(id, nome);
			controleCurso.atualizar(atualizar);
			
			// Atualizando dados da disciplinaSelecionada e das listas
			Curso curso = controleCurso.consultaCurso(id);
			selecionado = curso;
			
			atualizarListagem();
		}

		return null;
	}

	public String deletar() {
		
		// Prevenindo para ID da disciplina selecionada não ser null
		if(this.getSelecionado() != null){
			
			int id = this.getSelecionado().getId();
			
			// Limpando a disciplina selecionada antes de excluir do banco/lista (se não dá erro)
			setSelecionado(null);
			
			// Removando da banco/lista
			controleCurso.remover(id);
			System.out.println("Curso excluído!");

			// Atualizando lista
			atualizarListagem();
		}

		 return null;
	}

	 public void onDrop(DragDropEvent ddEvent) {  
	        Disciplina disciplina = ((Disciplina) ddEvent.getData());  
	  
	        listaDisciplinasDoCurso.add(disciplina);  
	        listaTodasDisciplinas.remove(disciplina);  
	    }  
	
	/**
	 * Atualizar a listagem de cursos (chamado do próprio Bean, ou seja, na
	 * primeira vez que executa) e a lista de listaPesquisa. 
	 */
	public void atualizarListagem() {
		lista = controleCurso.consulta();
		listaPesquisa = (ArrayList<Curso>) getLista().clone();
	}

	/**
	 * Atualiza a listagem de disciplinas (chamado da View) TODO: Futuramente
	 * implementar de forma que não seja case-sensitive?
	 * 
	 * @param e
	 */
	public void atualizarListagemPesquisa(ValueChangeEvent e) {
		if (e.getNewValue().toString().length() > 0) {
			this.pegarDisciplinaLista(e.getNewValue().toString());
			System.out.println("Disciplina pesquisada com nome: "
					+ e.getNewValue().toString());
		} else {
			atualizarListagem();
			System.out.println("Deveria mostrar todas as disciplinas agora.");
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

			Curso esteCurso = getLista().get(i);
			boolean cursoTemEsseTermo = esteCurso.getNome().contains(
					termo);

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

	private boolean containsListaDisciplina(Curso esteCurso) {

		for (Curso c : getListaPesquisa()){
			if (c.getNome().equals(esteCurso.getNome())){
				return true;
			}
		}
		return false;
	}
	
	
	// Primeira etapa antes de abrir o modal, basicamente salva o nome do curso para exibir dentro do modal.
	public void gravarNome(){
		System.out.println("Cheguei com o nome: " + nome);
		setNome(nome);
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

	public ControleCurso getControleCurso() {
		return controleCurso;
	}

	public void setControleCurso(ControleCurso controleCurso) {
		this.controleCurso = controleCurso;
	}

	public ArrayList<Curso> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Curso> lista) {
		CursoMBean.lista = lista;
	}

	public ArrayList<Curso> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(ArrayList<Curso> listaPesquisa) {
		CursoMBean.listaPesquisa = listaPesquisa;
	}

	public Curso getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Curso selecionado) {
		this.selecionado = selecionado;
		if (selecionado != null){
			listaTodasDisciplinas = controleCurso.listaDisciplinasNaoAssociadas(selecionado);
			listaDisciplinasDoCurso = controleCurso.listaDisciplinasAssociadas(selecionado);
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
		CursoMBean.listaTodasDisciplinas = listaTodasDisciplinas;
	}

	public ArrayList<Disciplina> getListaDisciplinasDoCurso() {
		return listaDisciplinasDoCurso;
	}

	public void setListaDisciplinasDoCurso(
			ArrayList<Disciplina> listaDisciplinasDoCurso) {
		CursoMBean.listaDisciplinasDoCurso = listaDisciplinasDoCurso;
	}
	
	

}