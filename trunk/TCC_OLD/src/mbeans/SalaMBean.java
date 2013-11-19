package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import control.ControleSala;
import entidades.Sala;



/*
 * 
 * TODO Disciplina (ver arquivo) 
 *  BEAN:
 *   Revisar, arrumar e deletar métodos desnecessários/ruins/inúteis.
 *   
 *  JSF: 
 *   Implementar avisos (ao deletar) e validações dos dados. 
 * 
 * 
 */







@ManagedBean(name = "salaMBean")
@SessionScoped
public class SalaMBean {

	private ControleSala controleSala;

	// Lista de todas as disciplinas
	private static ArrayList<Sala> lista;

	// Lista de disciplinas pesquisadas (fiz isso para evitar muitas consultas
	// ao banco de dados)
	private static ArrayList<Sala> listaPesquisa;
//	private Sala selecionado;
	private Sala selecionado;

	private int id;
	private String nome;

	public SalaMBean() {

		if (lista == null) {
			lista = new ArrayList<Sala>();
		}

		if (this.getNome() == null) {
			this.setNome("");
		}

		controleSala = ControleSala.getInstance();

		atualizarListagem();
	}

	public String cadastrar() {

		System.out.println("metodo cadastrar Ok");

		if (!(this.getNome().isEmpty() || this.getNome() == " " || this
				.getNome() == "  ")) {
			
			Sala sala = new Sala();
			sala.setNome(this.getNome());

			controleSala.adicionar(sala);
		} else {
			System.out.println("Sala não inserida.");
		}

		limparCampos();
		atualizarListagem();

		return null;
	}

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
			Sala salaAtualizar = new Sala(id, nome);
			controleSala.atualizar(salaAtualizar);
			
			// Atualizando dados da disciplinaSelecionada e das listas
			Sala sala = controleSala.consultaSala(id);
			selecionado = sala;
			
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
			controleSala.remover(id);
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
		this.setLista(controleSala.consulta());
		listaPesquisa = (ArrayList<Sala>) this.getLista().clone();
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

			Sala estaSala = this.getLista().get(i);
			boolean disciplinaTemEsseTermo = estaSala.getNome().contains(
					termo);

			if (disciplinaTemEsseTermo) {
				if (!contains(estaSala)) {
					this.getListaPesquisa().add(estaSala);
				} else {
					// não faz nada, pois a sala já está na lista
				}
			} else { // sala não tem esse termo
				if (!contains(estaSala)) {
					// não faz nada, pois a sala sem o termo não está na
					// lista
				} else {
					this.getListaPesquisa()
							.remove(estaSala);
				}
			}
		}
	}

	private boolean contains(Sala estaSala) {

		for (Sala d : this.getListaPesquisa())
			if (d.getNome().equals(estaSala.getNome()))
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

	public ControleSala getControleSala() {
		return controleSala;
	}

	public void setControleSala(ControleSala controleSala) {
		this.controleSala = controleSala;
	}

	public Sala getSelecionado() {
		System.out.println("Get: " + selecionado);
		
		return selecionado;
	}

	public void setSelecionado(Sala selecionado) {
		this.selecionado = selecionado;
		System.out.println("Set: " + selecionado);
	}

	public ArrayList<Sala> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Sala> listaSalas) {
		SalaMBean.lista = listaSalas;
	}

	public ArrayList<Sala> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(ArrayList<Sala> lista) {
		SalaMBean.listaPesquisa = lista;
	}
}