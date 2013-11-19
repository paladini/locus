package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import control.ControleDia;
import control.ControleEscola;
import control.ControleLogin;
import control.ControleTurno;
import entidades.Dia;
import entidades.Escola;
import entidades.Login;
import entidades.Turno;

@ManagedBean(name = "escolaMBean")
@ViewScoped
public class EscolaMBean {

	private ControleEscola modelo;
	private Escola escola;
	private Login login;
	
	// TODO: Cuidar do primeiro acesso.
	
	// Alguns controles necessários
	ControleDia controleDia;
	ControleTurno controleTurno;
	ControleLogin controleLogin;

	// Lista de turnos e dias
	private ArrayList<Dia> listaTodosDias;
	private ArrayList<Turno> listaTodosTurnos;
	
	// Vetores dos dias e turnos selecionados
	private String[] vetorTurnos; 
	private String[] vetorDias;

	// Atributos para o primeira entrada
	private String novaSenha;
	private String nomeEscola;

	public EscolaMBean() {
		modelo = ControleEscola.getInstance();

		if (escola == null) {
			escola = modelo.consultar();
		}

		// Inicializando os controles necessários
		if (controleDia == null) {
			controleDia = ControleDia.getInstance();
		}

		if (controleTurno == null) {
			controleTurno = ControleTurno.getInstance();
		}

		if (controleLogin == null) {
			controleLogin = ControleLogin.getInstance();
		}

		// Instanciando Login
		if (login == null){
			login = controleLogin.consultar();
		}
		
		// Populando lista com todos os dias da semana
		if (listaTodosDias == null) {
			listaTodosDias = new ArrayList<Dia>();

			Dia segunda = new Dia("Segunda-feira");
			Dia terca = new Dia("Terca-feira");
			Dia quarta = new Dia("Quarta-feira");
			Dia quinta = new Dia("Quinta-feira");
			Dia sexta = new Dia("Sexta-feira");
			Dia sabado = new Dia("Sabado");

			listaTodosDias.add(segunda);
			listaTodosDias.add(terca);
			listaTodosDias.add(quarta);
			listaTodosDias.add(quinta);
			listaTodosDias.add(sexta);
			listaTodosDias.add(sabado);
		}

		// Populando lista com todos os turnos
		if (listaTodosTurnos == null) {
			listaTodosTurnos = new ArrayList<Turno>();

			Turno matutino = new Turno("Matutino");
			Turno vespertino = new Turno("Vespertino");
			Turno noturno = new Turno("Noturno");

			listaTodosTurnos.add(matutino);
			listaTodosTurnos.add(vespertino);
			listaTodosTurnos.add(noturno);
		}

		novaSenha = "";
		nomeEscola = "";
		
		// Setando dias
		getEscola().setDias(controleDia.consulta());
		vetorDias = new String[getEscola().getDias().size()];
		for(int i = 0; i < getEscola().getDias().size(); i++){
			vetorDias[i] = getEscola().getDias().get(i).getNome();
		}
		
		// Setando turnos
		getEscola().setTurnos(controleTurno.consulta());
		vetorTurnos = new String[getEscola().getTurnos().size()];
		for(int i = 0; i < getEscola().getTurnos().size(); i++){
			vetorTurnos[i] = getEscola().getTurnos().get(i).getNome();
		}

	}

	/**
	 * Método exclusivo para o primeira entrada.
	 * @return
	 */
	public String cadastrarPrimeiraEntrada() {

		// Criando nome para Escola
		Escola escolaPrimeiraEntrada = new Escola(nomeEscola);
		
		if (modelo.mudarNome(escolaPrimeiraEntrada) == 0) {

			// Adicionando todos os horários
			if (modelo.adicionarHorario(escola) == 0) {

				controleLogin.modificarSenha(novaSenha);
//				controleLogin.primeiroAcesso(); // Define o últimoAcesso como agora.
				System.out
						.println("Aparentemente tudo certo com o Dados Escola.");
				return "2-tela-disciplinas.xhtml?faces-redirect=true";
			}

		}

		return "";

	}
	
	/**
	 * Método a ser editado para CRUD genérico de Escola.
	 * @return
	 */
	public String atualizarDados() {
		
		// Pegando os checkboxes selecionados
		// Dias
		ArrayList<Dia> diasSelecionados = new ArrayList<Dia>();
		for(int i = 0; i < vetorDias.length; i++){
			String termo = vetorDias[i];
			Dia dia = new Dia(termo);
			diasSelecionados.add(dia);
		}
		
		// Turnos
		ArrayList<Turno> turnosSelecionados = new ArrayList<Turno>();
		for(int i = 0; i < vetorTurnos.length; i++){
			String termo = vetorTurnos[i];
			Turno turno = new Turno(termo);
			turnosSelecionados.add(turno);
		}
		
		getEscola().setDias(diasSelecionados);
		getEscola().setTurnos(turnosSelecionados);
		
		// Mudando nome da escola;
		if (modelo.mudarNome(escola) == 0) {

			// Adicionando todos os horários
			if (modelo.adicionarHorario(escola) == 0) {

				controleLogin.modificarSenha(login.getSenha());
				
				System.out
						.println("Aparentemente tudo certo com o Dados Escola.");
				return null;
			}

		}
		return null;
	}

	public ControleEscola getModelo() {
		return modelo;
	}

	public void setModelo(ControleEscola modelo) {
		this.modelo = modelo;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
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

	public ControleLogin getControleLogin() {
		return controleLogin;
	}

	public void setControleLogin(ControleLogin controleLogin) {
		this.controleLogin = controleLogin;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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
	
	
	
}
