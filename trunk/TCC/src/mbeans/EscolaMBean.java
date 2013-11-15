package mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import control.ControleEscola;
import entidades.Escola;

@ManagedBean(name = "escolaMBean")
@SessionScoped
public class EscolaMBean {
	
	private ControleEscola modelo;
	private Escola escola;
	
	
	public EscolaMBean(){
		modelo = ControleEscola.getInstance();
	}
	
	public String cadastrar(){
		
		
		
		return "";
		
	}
	
	public String atualizarDados(){
		
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
	
}
