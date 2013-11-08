package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import control.ControleCurso;
import control.ControleDisciplina;
import entidades.Curso;
import entidades.Disciplina;


@ManagedBean(name="cursoMBean")
@SessionScoped
public class CursoMBean{
	
	private ControleCurso controleCurso;
	private static ArrayList<Curso> listaCursos;
	
	private int id;
	private String nome;
	
	public CursoMBean(){
		
		if (listaCursos==null)
			listaCursos = new ArrayList<Curso>();
		
		controleCurso = ControleCurso.getInstance();
		
		atualizarListagem();
	}
		
	
	public String cadastrar(){
		
		System.out.println("metodo cadastrar Ok");
		
		if (id == 0){
		
			Curso curso = new Curso();
			curso.setNome(nome);
			
			controleCurso.adicionar(curso);
		}else{
			Curso curso = new Curso();
			curso.setId(id);
			curso.setNome(nome);
			
			controleCurso.atualizar(curso);
		}
		
		limparCampos();
		
		atualizarListagem();
		
		return null;
	}
	
	private void limparCampos(){
		this.setId(0);
		this.setNome("");
	}
	
	public String entrarEditar(){
		String param1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCurso");
		String param2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nomeCurso");
		
		int idCurso = Integer.parseInt(param1);
		String nomeCurso = param2;
		
		this.setId(idCurso);
		this.setNome(nomeCurso);
		
		return null;
	}
	
	public String editar(){
		
	    
	    
//	    Cliente c = new Cliente(idCliente, nomeCliente, emailCliente);
//	    controleCliente.editar(c);
//	    atualizarListagem();
	    
		return null;
	}
	
	public String deletar(){
		String param1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDisciplina");
		System.out.println(param1);
		
		int idDisciplina = Integer.parseInt(param1);
	    
		controleCurso.remover(idDisciplina);
		
		atualizarListagem();
		
		return "";
	}
	
	
	private void atualizarListagem(){
		
		this.setListaCursos(controleCurso.consulta());
		
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


	public static ArrayList<Curso> getListaCursos() {
		return listaCursos;
	}


	public static void setListaCursos(ArrayList<Curso> listaCursos) {
		CursoMBean.listaCursos = listaCursos;
	}
	
	
	
	
	
}