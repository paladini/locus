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
 * @author silvio
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
        }
        
    }

    /**
     * Método para consultar os cursos do banco de dados
     *
     * @return
     */
    public ArrayList<Curso> consulta() {
        return modelo.select();
    }

    /**
     * Método para consultar os cursos com nomes que começam com os termos
     * digitados pelo usuário.
     *
     * @return
     */
    public ArrayList<Curso> consultaComTermos(String termo) {
        return modelo.selectComTermos(termo);
    }

    /**
     * Método para retornar somente um curso (para tela "Editar").
     *
     * @param termo
     * @return
     */
    public Curso consultaCurso(String termo) {
        return modelo.selectCurso(termo);
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
     * Adiciona uma disciplina associada ao curso.
     *
     * @param idCurso
     * @param idDisciplina
     */
    public void adicionarDisciplina(int idCurso, int idDisciplina) {
        modelo.insertCursoDisciplina(idCurso, idDisciplina);
    }

    /**
     * Exclui disciplina associada ao curso.
     *
     * @param idCurso
     * @param idDisciplina
     */
    public void excluirDisciplina(int idCurso, int idDisciplina) {
        modelo.deleteCursoDisciplina(idCurso, idDisciplina);
    }

    /**
     * Retorna a lista de disciplinas associadas à esse curso.
     *
     * @param curso
     * @return
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Curso curso) {
        return modelo.listaDisciplinasAssociadas(curso);
    }

    /**
     * Retorna a lista de disciplinas não associadas à esse curso.
     *
     * @param curso
     * @return
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Curso curso) {
        return modelo.listaDisciplinasNaoAssociadas(curso);
    }
}
