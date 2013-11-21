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
	private ArrayList<Dia> listaDiasSelecionados;
	private ArrayList<Turno> listaTodosTurnos;
	private ArrayList<Turno> listaTurnosSelecionados;

	// Vetores dos dias e turnos selecionados
	private String[] vetorTurnos;
	private String[] vetorDias;

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

		// Setando dias
		listaDiasSelecionados = controleDia.consultarAtivos();
		vetorDias = new String[listaDiasSelecionados.size()];
		for (int i = 0; i < listaDiasSelecionados.size(); i++) {
			vetorDias[i] = listaDiasSelecionados.get(i).getNome();
		}

		// Setando turnos
		listaTurnosSelecionados = controleTurno.consultarAtivos();
		vetorTurnos = new String[listaTurnosSelecionados.size()];
		for (int i = 0; i < listaTurnosSelecionados.size(); i++) {
			vetorTurnos[i] = listaTurnosSelecionados.get(i).getNome();
		}

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

		if (listaDiasSelecionados.size() == 0
				|| listaTurnosSelecionados.size() == 0) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Campo obrigatório",
					"Pelo menos um turno e dia são obrigatórios."));
			erro = true;
		}

		if (erro == true) {
			return null;
		}

		// Setando os dias selecionados para ativo = true
		for(Dia d : listaDiasSelecionados){
			d.setAtivo(true);
		}
		
		// Setando os turnos selecionados para ativo = true
		for(Turno t : listaTurnosSelecionados){
			t.setAtivo(true);
		}

		// Continua o método normal
		Admin escolaPrimeiraEntrada = new Admin();
		escolaPrimeiraEntrada.setNomeEscola(nomeEscola);
		escolaPrimeiraEntrada.setSenha(novaSenha);

		if (modelo.atualizarNomeEscola(escolaPrimeiraEntrada) == 0) {
			if (modelo.atualizarSenha(escolaPrimeiraEntrada.getSenha()) == 0) {

				controleDia.atualizarDias(listaDiasSelecionados);
				controleTurno.atualizarTurnos(listaTurnosSelecionados);
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
		if (admin.getNomeEscola().length() == 0 || admin.getNomeEscola().isEmpty()) {
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

		if (listaDiasSelecionados.size() == 0
				|| listaTurnosSelecionados.size() == 0) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Campo obrigatório",
					"Pelo menos um turno e dia são obrigatórios."));
			erro = true;
		}

		if (erro == true) {
			return null;
		}

		// Pegando os checkboxes selecionados
		// Dias
		ArrayList<Dia> diasSelecionados = new ArrayList<Dia>();
		for (int i = 0; i < vetorDias.length; i++) {
			String termo = vetorDias[i];
			Dia dia = new Dia(termo);
			dia.setAtivo(true);
			diasSelecionados.add(dia);
		}

		// Turnos
		ArrayList<Turno> turnosSelecionados = new ArrayList<Turno>();
		for (int i = 0; i < vetorTurnos.length; i++) {
			String termo = vetorTurnos[i];
			Turno turno = new Turno(termo);
			turno.setAtivo(true);
			turnosSelecionados.add(turno);
		}

		listaDiasSelecionados = diasSelecionados;
		listaTurnosSelecionados = turnosSelecionados;

		// Atualizando dados do Admin e de Turnos/Dias
		Admin escolaPrimeiraEntrada = new Admin();
		escolaPrimeiraEntrada.setNomeEscola(admin.getSenha());
		escolaPrimeiraEntrada.setSenha(admin.getSenha());

		if (modelo.atualizarNomeEscola(escolaPrimeiraEntrada) == 0) {
			if (modelo.atualizarSenha(escolaPrimeiraEntrada.getSenha()) == 0) {

				controleDia.atualizarDias(listaDiasSelecionados);
				controleTurno.atualizarTurnos(listaTurnosSelecionados);
				// modelo.atualizarUltimoAcesso();
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

	public String[] getVetorTurnos() {
		return vetorTurnos;
	}

	public void setVetorTurnos(String[] vetorTurnos) {
		this.vetorTurnos = vetorTurnos;
	}

	public String[] getVetorDias() {
		return vetorDias;
	}

	public void setVetorDias(String[] vetorDias) {
		this.vetorDias = vetorDias;
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

	public ArrayList<Dia> getListaDiasSelecionados() {
		return listaDiasSelecionados;
	}

	public void setListaDiasSelecionados(ArrayList<Dia> listaDiasSelecionados) {
		this.listaDiasSelecionados = listaDiasSelecionados;
	}

	public ArrayList<Turno> getListaTurnosSelecionados() {
		return listaTurnosSelecionados;
	}

	public void setListaTurnosSelecionados(
			ArrayList<Turno> listaTurnosSelecionados) {
		this.listaTurnosSelecionados = listaTurnosSelecionados;
	}

}
