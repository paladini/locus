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
	
	public String atualizarDados(){
		
		return null;
		
	}
	
	
	
}
