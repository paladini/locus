/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Curso;
import entidades.Disciplina;
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
	CursoDAO modelo;
	
	private ControleCurso(){
		modelo = new CursoDAO();
	}
	
	public static ControleCurso getInstance(){
		if (singleton == null)
			singleton = new ControleCurso();
		return singleton;
	}

    /**
     * Método para atualizar curso no banco de dados.
     *
     * @param curso
     */
    public void atualizar(Curso curso) {
        if (curso.getNome() == null || curso.getNome() == " " || curso.getNome() == ""){
            
        }else{
            modelo.update(curso);
            this.adicionarDisciplina(curso);
        }
    }

    /**
     * Método para remover curso no banco de dados
     *
     * @param disciplina
     */
    public void remover(int idCurso) {
        modelo.delete(idCurso);
    }

    /**
     * Método para adicionar um curso ao banco de dados.
     *
     * @param disciplinaAdicionar
     */
    public void adicionar(Curso cursoAdicionar) {
        
        // Se não existir nenhum curso com esse nome, manda inserir o curso.
        if (consultaCurso(cursoAdicionar.getNome()) == null){
            
        	modelo.insert(cursoAdicionar);
        	
        	// O ID atualmente é 0, precisa atualizar com o ID que o banco deu no auto-increment.
        	int id = this.consultaCursoID(cursoAdicionar.getNome());
        	cursoAdicionar.setId(id);
        	
        	// Caso tenha alguma disciplina associada, cria as associações no banco
        	if (cursoAdicionar.getDisciplina().size() > 0){
        		this.adicionarDisciplina(cursoAdicionar);
        	}
        	
        }
        
    }

    /**
     * Método para consultar os cursos do banco de dados
     *
     * @return Retorna todos os cursos do banco de dados.
     */
    public ArrayList<Curso> consulta() {
        return modelo.select();
    }
    
    /**
     * Método para pegar o curso pelo seu ID. 
     * @param id Id do curso desejado. 
     * @return Retorna um único curso.
     */
    public Curso consultaCurso(int id){
    	return modelo.select(id);
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
    	for(int i = 0; i < curso.getDisciplina().size(); i++){
    		modelo.insertCursoDisciplina(curso.getId(), curso.getDisciplina().get(i).getId());
    	}
    }
    
    /**
     * Método para retornar somente um curso (para tela "Editar").
     * Embora pareça desnecessário, é bom para verificar se um curso com o mesmo já existe, para validar no banco.
     * @param termo
     * @return
     */
    public Curso consultaCurso(String termo) {
        return modelo.selectCurso(termo);
    }
    
    /**
     * Retorna apenas o ID do curso (necessário para a inserção).
     * @param termo
     * @return
     */
    public int consultaCursoID(String termo){
    	Curso curso = modelo.selectCurso(termo);
    	return curso.getId();
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
