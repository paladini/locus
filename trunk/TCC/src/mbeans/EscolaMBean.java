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
import entidades.Login;
import entidades.Turno;

@ManagedBean(name = "escolaMBean")
@SessionScoped
public class EscolaMBean {
	
	private ControleEscola modelo;
	private Escola escola;
	
	// Alguns controles necessários
	ControleDia controleDia;
	ControleTurno controleTurno;
	ControleLogin controleLogin;
	
	// Lista de turnos e dias
	private ArrayList<Dia> listaTodosDias;
	private ArrayList<Turno> listaTodosTurnos;
	
	// Nova senha definida pela usuário
	private String novaSenha;
	
	
	public EscolaMBean(){
		modelo = ControleEscola.getInstance();
		
		if (escola == null){
			escola = new Escola();
		}
		
		// Inicializando os controles necessários
		if (controleDia == null){
			controleDia = new ControleDia(); //TODO: Fazer singleton.
		}
		
		if (controleTurno == null){
			controleTurno = new ControleTurno(); //TODO: Fazer singleton
		}
		
		// Populando lista com todos os dias da semana
		if (listaTodosDias == null){
			listaTodosDias = new ArrayList<Dia>();
			listaTodosDias.add(new Dia("Segunda-feira"));
			listaTodosDias.add(new Dia("Terça-feira"));
			listaTodosDias.add(new Dia("Quarta-feira"));
			listaTodosDias.add(new Dia("Quinta-feira"));
			listaTodosDias.add(new Dia("Sexta-feira"));
			listaTodosDias.add(new Dia("Sábado"));
		}
		
		// Populando lista com todos os turnos
		if (listaTodosTurnos == null){
			listaTodosTurnos = new ArrayList<Turno>();
			listaTodosTurnos.add(new Turno("Matutino"));
			listaTodosTurnos.add(new Turno("Vespertino"));
			listaTodosTurnos.add(new Turno("Noturno"));
		}
		
		novaSenha = "";
		

		
		if (controleLogin == null){
			controleLogin = ControleLogin.getInstance();
		}
		
	}
	
	public String cadastrar(){
		
		
		
		return "";
		
	}
	
	public String atualizarDados(){
		
		System.out.println("Cheguei");
		
		// Mudando nome da escola;
		if (modelo.mudarNome(escola) == 0){
			
			// Adicionando todos os horários
			if (modelo.adicionarHorario(escola) == 0){
				
				controleLogin.modificarSenha(novaSenha);
				System.out.println("Aparentemente tudo certo com o Login.");
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
	
	
	
	
	
}
