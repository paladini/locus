/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Curso;
import entidades.Disciplina;
import entidades.Turma;

import java.util.ArrayList;

import model.CursoDAO;

/**
 *
 * @author fernando_paladini
 */
public class ControleCurso {

	/*
	 * Padrão de projeto Singleton.
	 * 
	 */
	private static ControleCurso singleton;
	private CursoDAO modelo;
	
	private ControleCurso(){
		modelo = new CursoDAO();
	}
	
	public static ControleCurso getInstance(){
		if (singleton == null)
			singleton = new ControleCurso();
		return singleton;
	}

    
	
	
	
	
	
	
	  /**
     * Retorna uma lista de Cursos do banco de dados.
     *
     * @return Retorna todos os cursos do banco de dados.
     */
    public ArrayList<Curso> consultar() {
        return modelo.consultar();
    }
    
    /**
     * Retorna um Curso do banco de dados, de acordo com o ID fornecido.
     * @param id Id do curso desejado. 
     * @return Retorna um único curso.
     */
    public Curso consultar(int id){
    	return modelo.consultar(id);
    }

    /**
     * Retorna um Curso do banco de dados, de acordo com o nome do curso fornecido.
     * @param termo
     * @return
     */
    public Curso consultar(String termo) {
        return modelo.consultar(termo);
    }
    
    /**
     * Retorna o ID de um curso, de acordo com o nome do curso fornecido.
     * @param termo
     * @return
     */
    public int consultarID(String termo){
    	Curso curso = modelo.consultar(termo);
    	return curso.getId();
    }
	
	/**
     * Atualiza um curso do banco de dados.
     *
     * @param curso
     */
    public void atualizar(Curso curso) {
        if (curso.getNome() == null || curso.getNome() == " " || curso.getNome() == ""){
            
        }else{
            modelo.atualizar(curso);
            this.adicionarDisciplina(curso);
        }
    }

    /**
     * Remove um curso do banco de dados.
     *
     * @param disciplina
     */
    public void remover(int idCurso) {
        modelo.deletar(idCurso);
    }

    /**
     * Método para adicionar um curso ao banco de dados.
     *
     * @param disciplinaAdicionar
     */
    public void inserir(Curso cursoAdicionar) {
        
        // Se não existir nenhum curso com esse nome, manda inserir o curso.
        if (consultar(cursoAdicionar.getNome()) == null){
            
        	modelo.inserir(cursoAdicionar);
        	
        	// O ID atualmente é 0, precisa atualizar com o ID que o banco deu no auto-increment.
        	int id = this.consultarID(cursoAdicionar.getNome());
        	cursoAdicionar.setId(id);
        	
        	// Caso tenha alguma disciplina associada, cria as associações no banco
        	if (cursoAdicionar.getDisciplinas().size() > 0){
        		this.adicionarDisciplina(cursoAdicionar);
        	}
        	
        }
        
    }

  
    
    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação curso-disciplina
     * 
     *    ======================================================================
     * 
     */
    
    /**
     * Retorna a lista de disciplinas associadas à esse curso.
     *
     * @param curso
     * @return Retorna a lista de disciplinas que estão associadas a esse curso.
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Curso curso) {
        return modelo.listaDisciplinasAssociadas(curso);
    }

    /**
     * Retorna a lista de disciplinas não associadas à esse curso.
     *
     * @param curso
     * @return Retorna a lista de disciplinas que não está associada a esse curso.
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Curso curso) {
        return modelo.listaDisciplinasNaoAssociadas(curso);
    }
    
    /**
     * Atualiza todas as disciplinas desse curso no banco de dados.
     * 1) Deleta todas as disciplinas antigas desse curso do banco.
     * 2) Adiciona todas as novas disciplinas (getDisciplina()) desse curso no banco.
     * @param curso Curso com uma lista de disciplinas não nula.
     */
    private void adicionarDisciplina(Curso curso){
    	
    	ArrayList<Disciplina> listaDisciplinasBanco = this.listaDisciplinasAssociadas(curso);
    	
    	// TODO: Essa forma de fazer isso está, no mínimo, ingênua.
    	// Deletando TODAS as disciplinas associadas ao curso.
    	for(int i = 0; i < listaDisciplinasBanco.size(); i++){
    		modelo.deleteCursoDisciplina(curso.getId(), listaDisciplinasBanco.get(i).getId());
    	}
    	
    	// Adicionando as novas disciplinas no banco
    	for(int i = 0; i < curso.getDisciplinas().size(); i++){
    		modelo.insertCursoDisciplina(curso.getId(), curso.getDisciplinas().get(i).getId());
    	}
    }
    
    
    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação curso-turma
     * 
     *    ======================================================================
     * 
     */
    /**
     * Retorna uma lista de turmas associadas ao Curso.
     * @param curso
     * @return
     */
    public ArrayList<Turma> listaTurmasAssociadas(Curso curso){
    	return modelo.listaTurmasAssociadas(curso);
    }
    
    
    
    
    
    
    
    
    
    
    
    /*
     * 
     *
     *                    MÉTODOS POSSIVELMENTE DEPRECIADOS
     * 
     * 
     */
    
    
    /**
     * Adiciona uma disciplina associada ao curso.
     *
     * @param idCurso
     * @param idDisciplina
     */
    @Deprecated
    public void adicionarDisciplina(int idCurso, int idDisciplina) {
        modelo.insertCursoDisciplina(idCurso, idDisciplina);
    }

    /**
     * Exclui disciplina associada ao curso.
     *
     * @param idCurso
     * @param idDisciplina
     */
    @Deprecated
    public void excluirDisciplina(int idCurso, int idDisciplina) {
        modelo.deleteCursoDisciplina(idCurso, idDisciplina);
    }
    

    /**
     * Método para consultar os cursos com nomes que começam com os termos
     * digitados pelo usuário.
     *
     * @return
     */
    @Deprecated
    public ArrayList<Curso> consultaComTermos(String termo) {
        return modelo.selectComTermos(termo);
    }

   
}
