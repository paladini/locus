package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import control.ControleTurma;
import entidades.Turma;



/*
 * 
 * TODO Turma (ver arquivo) 
 *  BEAN:
 *   Revisar, arrumar e deletar métodos desnecessários/ruins/inúteis.
 *   
 *  JSF: 
 *   Implementar avisos (ao deletar) e validações dos dados. 
 * 
 * 
 */







@ManagedBean(name = "turmaMBean")
@SessionScoped
public class TurmaMBean {

	private ControleTurma controleTurma;

	// Lista de todas as turmas
	private ArrayList<Turma> lista;

	// Lista de Turmas pesquisadas (fiz isso para evitar muitas consultas
	// ao banco de dados)
	private ArrayList<Turma> listaPesquisa;

	private Turma selecionado;

	private int id;
	private String nome;

	public TurmaMBean() {

		if (lista == null) {
			lista = new ArrayList<Turma>();
		}

		if (this.getNome() == null) {
			this.setNome("");
		}

		controleTurma = ControleTurma.getInstance();

		atualizarListagem();
	}

	public String cadastrar() {

		System.out.println("metodo cadastrar Ok");

		if (!(this.getNome().isEmpty() || this.getNome() == " " || this
				.getNome() == "  ")) {
			
			Turma turma = new Turma();
			turma.setNome(this.getNome());

			controleTurma.adicionar(turma);
		} else {
			System.out.println("Turma não inserida.");
		}

		limparCampos();
		atualizarListagem();

		return null;
	}

	public String editar() {

		if (this.getSelecionado() != null){
			// Pegando ID da Turma selecionada e o nome editado pelo usuário (no edit.xhtml)
			String nome = this.getSelecionado().getNome();
			int id = this.getSelecionado().getId();
			
			// Verificando dados
			System.out.println("---------------------");
			System.out.println("nome editado: " + this.getSelecionado().getNome());
			System.out.println("---------------------");
			
			// Atualizando turma
			Turma turmaAtualizar = new Turma(id, nome);
			controleTurma.atualizar(turmaAtualizar);
			
			// Atualizando dados do selecionado e das listas
			Turma turma = controleTurma.consultaTurma(id);
			selecionado = turma;
			
			atualizarListagem();
		}

		return null;
	}

	public String deletar() {
		
		// Prevenindo para ID da disciplina selecionada não ser null
		if(this.getSelecionado() != null){
			
			int id = this.getSelecionado().getId();
			System.out.println("Cheguei aqui com ID: " + id);
			
			// Limpando a disciplina selecionada antes de excluir do banco/lista (se não dá erro)
			setSelecionado(null);
			
			// Removando da banco/lista
			Turma turma = new Turma(id, "");
			controleTurma.remover(turma);
			System.out.println("Sala excluída!");

			// Atualizando lista
			atualizarListagem();
		}

		 return null;
	}

	/**
	 * Atualizar a listagem de salas (chamado do próprio Bean, ou seja, na
	 * primeira vez que executa) e a listaPesquisadas
	 */
	public void atualizarListagem() {
		this.setLista(controleTurma.consulta());
		listaPesquisa = (ArrayList<Turma>) this.getLista().clone();
	}

	/**
	 * Atualiza a listagem de salas (chamado da View) TODO: Futuramente
	 * implementar de forma que não seja case-sensitive?
	 * 
	 * @param e
	 */
	public void atualizarListagemPesquisa(ValueChangeEvent e) {
		if (e.getNewValue().toString().length() > 0) {
			this.pegarDisciplinaLista(e.getNewValue().toString());
			System.out.println("Sala pesquisada com nome: "
					+ e.getNewValue().toString());
		} else {
			atualizarListagem();
			System.out.println("Deveria mostrar todas as salas agora.");
		}
	}
	
	/**
	 * Limpa todos os objetos selecionados na tabela.
	 */
	public void limparSelecionadosDataTable() {
		RequestContext.getCurrentInstance().execute(
				"resultados.unselectAllRows()");
	}
	
	/**
	 * Limpa o campo de Input.
	 */
	private void limparCampos() {
		this.setNome("");
	}

	/**
	 * Adiciona na listaPesquisa as salas que contém no seu
	 * nome o termo pesquisado pelo usuário. Criei este novo arrayList pq é
	 * melhor usar um ArrayList ao invés de ficar fazendo consultas toda hora no
	 * banco.
	 * 
	 * @param termo
	 */
	private void pegarDisciplinaLista(String termo) {

		for (int i = 0; i < this.getLista().size(); i++) {

			Turma estaTurma = this.getLista().get(i);
			boolean disciplinaTemEsseTermo = estaTurma.getNome().contains(
					termo);

			if (disciplinaTemEsseTermo) {
				if (!contains(estaTurma)) {
					this.getListaPesquisa().add(estaTurma);
				} else {
					// não faz nada, pois a sala já está na lista
				}
			} else { // sala não tem esse termo
				if (!contains(estaTurma)) {
					// não faz nada, pois a sala sem o termo não está na
					// lista
				} else {
					this.getListaPesquisa()
							.remove(estaTurma);
				}
			}
		}
	}

	private boolean contains(Turma estaTurma) {

		for (Turma d : this.getListaPesquisa())
			if (d.getNome().equals(estaTurma.getNome()))
				return true;

		return false;
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

	public ControleTurma getControleTurma() {
		return controleTurma;
	}

	public void setControleTurma(ControleTurma controleTurma) {
		this.controleTurma = controleTurma;
	}

	public Turma getSelecionado() {
		System.out.println("Get: " + selecionado);
		
		return selecionado;
	}

	public void setSelecionado(Turma selecionado) {
		this.selecionado = selecionado;
		System.out.println("Set: " + selecionado);
	}

	public ArrayList<Turma> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Turma> listaTurmas) {
		this.lista = listaTurmas;
	}

	public ArrayList<Turma> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(ArrayList<Turma> lista) {
		this.listaPesquisa = lista;
	}
}