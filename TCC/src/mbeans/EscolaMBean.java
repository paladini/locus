package mbeans;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import control.ControleAdmin;
import control.ControleDia;
import control.ControleTurno;
import entidades.Admin;
import entidades.Dia;
import entidades.Turno;

@ManagedBean(name = "escolaMBean")
@ViewScoped
public class EscolaMBean {

	private ControleAdmin modelo;
	private Admin admin;

	// TODO: Cuidar do primeiro acesso.
	// Alguns controles necessários
	ControleDia controleDia;
	ControleTurno controleTurno;

	// Lista de turnos e dias
	private ArrayList<Dia> listaTodosDias;
	private ArrayList<Turno> listaTodosTurnos;
	private String[] turnosSelecionadosOrdenados;
	
	// Atributos para o primeira entrada
	private String novaSenha;
	private String nomeEscola;

	public EscolaMBean() {
		modelo = ControleAdmin.getInstance();

		if (admin == null) {
			admin = modelo.consultar();
		}

		// Inicializando os controles necessários
		if (controleDia == null) {
			controleDia = ControleDia.getInstance();
		}

		if (controleTurno == null) {
			controleTurno = ControleTurno.getInstance();
		}

		// Populando lista com todos os dias da semana
		if (listaTodosDias == null) {
			listaTodosDias = controleDia.consulta();
		}

		// Populando lista com todos os turnos
		if (listaTodosTurnos == null) {
			listaTodosTurnos = controleTurno.consultar();
		}

		novaSenha = "";
		nomeEscola = "";

	}

	/**
	 * Método exclusivo para o primeira entrada.
	 * 
	 * @return
	 */
	public String cadastrarPrimeiraEntrada() {

		// Validações da página
		FacesContext context = FacesContext.getCurrentInstance();
		boolean erro = false;
		if (nomeEscola.length() == 0 || nomeEscola.isEmpty()) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Campo obrigatório",
					"Nome da instituição é de preenchimento obrigatório"));
			erro = true;
		}

		if (novaSenha.length() == 0 || novaSenha.isEmpty()) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Campo obrigatório",
					"Senha é de preenchimento obrigatório"));
			erro = true;
		}

		if (erro == true) {
			return null;
		}

		// Continua o método normal
		Admin escolaPrimeiraEntrada = new Admin();
		escolaPrimeiraEntrada.setNomeEscola(nomeEscola);
		escolaPrimeiraEntrada.setSenha(novaSenha);

		if (modelo.atualizarNomeEscola(escolaPrimeiraEntrada) == 0) {
			if (modelo.atualizarSenha(escolaPrimeiraEntrada.getSenha()) == 0) {

				this.cadastrarDiasETurnos();
				// modelo.atualizarUltimoAcesso();
				return "2-tela-disciplinas.xhtml?faces-redirect=true";

			} else {
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Algo deu errado",
								"Por favor, verifique a validade de todos os campos antes de prosseguir."));
			}
		} else {
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Algo deu errado",
							"Por favor, verifique a validade de todos os campos antes de prosseguir."));
		}

		return null;

	}

	/**
	 * Método a ser editado para CRUD genérico de Escola.
	 * 
	 * @return
	 */
	public String atualizarDados() {

		// Validações da página
		FacesContext context = FacesContext.getCurrentInstance();
		boolean erro = false;
		if (admin.getNomeEscola().length() == 0
				|| admin.getNomeEscola().isEmpty()) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Campo obrigatório",
					"Nome da instituição é de preenchimento obrigatório"));
			erro = true;
		}

		if (admin.getSenha().length() == 0 || admin.getSenha().isEmpty()) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Campo obrigatório",
					"Senha é de preenchimento obrigatório"));
			erro = true;
		}

		if (erro == true) {
			return null;
		}

		// Atualizando dados do Admin e de Turnos/Dias
		Admin escolaPrimeiraEntrada = new Admin();
		escolaPrimeiraEntrada.setNomeEscola(admin.getNomeEscola());
		escolaPrimeiraEntrada.setSenha(admin.getSenha());

		if (modelo.atualizarNomeEscola(escolaPrimeiraEntrada) == 0) {
			if (modelo.atualizarSenha(escolaPrimeiraEntrada.getSenha()) == 0) {

				this.cadastrarDiasETurnos();
				// modelo.atualizarUltimoAcesso();
				
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Salvo com sucesso!",
								"Suas modificações foram salvas com sucesso."));
				return null;

			} else {
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Algo deu errado",
								"Por favor, verifique a validade de todos os campos antes de prosseguir."));
			}
		} else {
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Algo deu errado",
							"Por favor, verifique a validade de todos os campos antes de prosseguir."));
		}
		return null;
	}

	private void cadastrarDiasETurnos() {
		
		controleDia.deletarTodosTurnoDia();
		
		for (Dia d : listaTodosDias) {
			
			d.instanciarTurnosSelecionados(listaTodosTurnos.size());
			int i = 0;

			for (Turno item : listaTodosTurnos) {
			    String name = item.getNome();
			    d.getVetorTurnosSelecionados()[i++] = d.getListaTurnosSelecionados().contains(name) ? name : null;
			}
			
			controleDia.atualizarDias(d);
		}
	}
	
	
	public ArrayList<Dia> getListaTodosDias() {
		return listaTodosDias;
	}

	public void setListaTodosDias(ArrayList<Dia> listaTodosDias) {
		this.listaTodosDias = listaTodosDias;
	}

	public ArrayList<Turno> getListaTodosTurnos() {
		return listaTodosTurnos;
	}

	public void setListaTodosTurnos(ArrayList<Turno> listaTodosTurnos) {
		this.listaTodosTurnos = listaTodosTurnos;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public ControleDia getControleDia() {
		return controleDia;
	}

	public void setControleDia(ControleDia controleDia) {
		this.controleDia = controleDia;
	}

	public ControleTurno getControleTurno() {
		return controleTurno;
	}

	public void setControleTurno(ControleTurno controleTurno) {
		this.controleTurno = controleTurno;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public ControleAdmin getModelo() {
		return modelo;
	}

	public void setModelo(ControleAdmin modelo) {
		this.modelo = modelo;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String[] getTurnosSelecionadosOrdenados() {
		return turnosSelecionadosOrdenados;
	}

	public void setTurnosSelecionadosOrdenados(String[] turnosSelecionadosOrdenados) {
		this.turnosSelecionadosOrdenados = turnosSelecionadosOrdenados;
	}

	
	
//	public ArrayList<Dia> getListaDiasSelecionados() {
//		return listaDiasSelecionados;
//	}
//
//	public void setListaDiasSelecionados(ArrayList<Dia> listaDiasSelecionados) {
//		this.listaDiasSelecionados = listaDiasSelecionados;
//	}

}
