package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import control.ControleDia;
import control.ControleEscola;
import control.ControleLogin;
import control.ControleTurno;
import entidades.Dia;
import entidades.Escola;
import entidades.Turno;

@ManagedBean(name = "escolaMBean")
@SessionScoped
public class EscolaMBean {

	private ControleEscola modelo;
	private Escola escola;
	
	// TODO: Cuidar do primeiro acesso.
	
	// Alguns controles necessários
	ControleDia controleDia;
	ControleTurno controleTurno;
	ControleLogin controleLogin;

	// Lista de turnos e dias
	private ArrayList<Dia> listaTodosDias;
	private ArrayList<Turno> listaTodosTurnos;

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

		// Populando lista com todos os dias da semana
		if (listaTodosDias == null) {
			listaTodosDias = new ArrayList<Dia>();

			Dia segunda = new Dia("Segunda-feira");
			Dia terca = new Dia("Terça-feira");
			Dia quarta = new Dia("Quarta-feira");
			Dia quinta = new Dia("Quinta-feira");
			Dia sexta = new Dia("Sexta-feira");
			Dia sabado = new Dia("Sábado");

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

		getEscola().setDias(controleDia.consulta());
		getEscola().setTurnos(controleTurno.consulta());

		for (Turno t : getEscola().getTurnos()) {
			System.out.println("Turno selecionado anteriormente: "
					+ t.getNome());
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

		// Mudando nome da escola;
		if (modelo.mudarNome(escola) == 0) {

			// Adicionando todos os horários
			if (modelo.adicionarHorario(escola) == 0) {

				controleLogin.modificarSenha(novaSenha);
				System.out
						.println("Aparentemente tudo certo com o Dados Escola.");
				return "2-tela-disciplinas.jsf";
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

}
