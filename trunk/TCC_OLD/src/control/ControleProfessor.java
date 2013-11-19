/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Curso;
import entidades.Disciplina;
import entidades.Professor;

import java.util.ArrayList;

import model.ProfessorDAO;

/**
 *
 * @author fernando_paladini
 */
public class ControleProfessor {
    
    private ProfessorDAO modelo;
    private static ControleProfessor singleton;
    
    private ControleProfessor(){
    	modelo = new ProfessorDAO();
    }
    
    public static ControleProfessor getInstance(){
    	if(singleton == null){
    		singleton = new ControleProfessor();
    	}
    	return singleton;
    }
    
    
    
    
    /**
     * Método para atualizar Professor no banco de dados.
     *
     * @param curso
     */
    public void atualizar(Professor professor) {
        if (professor.getNome() == null || professor.getNome() == " " || professor.getNome() == ""){
            
        }else{
            modelo.update(professor);
            this.adicionarDisciplina(professor);
        }
    }

    /**
     * Método para remover Professor no banco de dados
     *
     * @param disciplina
     */
    public void remover(Professor professor) {
        modelo.delete(professor);
    }

    /**
     * Método para adicionar um professor ao banco de dados.
     *
     * @param disciplinaAdicionar
     */
public void adicionar(Professor professorAdicionar) {
        
        // Se não existir nenhum curso com esse nome, manda inserir o curso.
        if (consultaProfessor(professorAdicionar.getNome()) == null){
            
        	modelo.insert(professorAdicionar);
        	
        	// O ID atualmente é 0, precisa atualizar com o ID que o banco deu no auto-increment.
        	int id = this.consultaProfessorID(professorAdicionar.getNome());
        	professorAdicionar.setId(id);
        	
        	// Caso tenha alguma disciplina associada, cria as associações no banco
        	if (professorAdicionar.getListaDisciplinas().size() > 0){
        		this.adicionarDisciplina(professorAdicionar);
        	}
        	
        }
        
    }

    /**
     * Método para consultar os professores do banco de dados
     *
     * @return
     */
    public ArrayList<Professor> consulta() {
        return modelo.select();
    }

    /**
     * Método para retornar somente um professor (para tela "Editar").
     *
     * @param termo
     * @return
     */
    public Professor consultaProfessor(String termo) {
        return modelo.selectProfessor(termo);
    }
    
    /**
     * Método para retornar somente um professor baseado no ID do professor.
     * @param id
     * @return
     */
    public Professor consultaProfessor(int id){
    	return modelo.selectProfessor(id);
    }
    
    /**
     * Retorna apenas o ID do professor (necessário para a inserção).
     * @param termo
     * @return
     */
    public int consultaProfessorID(String termo){
    	Professor professor = modelo.selectProfessor(termo);
    	return professor.getId();
    }

    
    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação professor-disciplina
     * 
     *    ======================================================================
     * 
     */
    
    /**
     * Atualiza todas as disciplinas desse professor no banco de dados.
     * 1) Deleta todas as disciplinas antigas desse professor do banco.
     * 2) Adiciona todas as novas disciplinas (getDisciplina()) desse professor no banco.
     * @param professor Professor com uma lista de disciplinas não nula.
     */
    private void adicionarDisciplina(Professor professor){
    	
    	ArrayList<Disciplina> listaDisciplinasBanco = this.listaDisciplinasAssociadas(professor);
    	
    	// TODO: Essa forma de fazer isso está, no mínimo, ingênua.
    	// Deletando TODAS as disciplinas associadas ao curso.
    	for(int i = 0; i < listaDisciplinasBanco.size(); i++){
    		modelo.deleteProfessorDisciplina(professor.getId(), listaDisciplinasBanco.get(i).getId());
    	}
    	
    	// Adicionando as novas disciplinas no banco
    	for(int i = 0; i < professor.getListaDisciplinas().size(); i++){
    		modelo.insertProfessorDisciplina(professor.getId(), professor.getListaDisciplinas().get(i).getId());
    	}
    }
    
    /**
     * Retorna a lista de disciplinas associadas à esse professor.
     *
     * @param professor
     * @return
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Professor professor) {
        return modelo.listaDisciplinasAssociadas(professor);
    }

    /**
     * Retorna a lista de disciplinas não associadas à esse Professor.
     *
     * @param professor
     * @return
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Professor professor) {
        return modelo.listaDisciplinasNaoAssociadas(professor);
    }
    
    /*
     * 
     * 
     * 
     * 
     * 
     *                                 POSSIVELMENTE DEPRECIADOS
     * 
     * 
     * 
     * 
     * 
     */
    
    
    /**
     * Adiciona uma disciplina associada ao professor.
     *
     * @param idCurso
     * @param idDisciplina
     */
    @Deprecated
    public void adicionarDisciplina(int idProfessor, int idDisciplina) {
        modelo.insertProfessorDisciplina(idProfessor, idDisciplina);
    }

    /**
     * Exclui disciplina associada ao professor.
     *
     * @param idProfessor
     * @param idDisciplina
     */
    @Deprecated
    public void excluirDisciplina(int idProfessor, int idDisciplina) {
        modelo.deleteProfessorDisciplina(idProfessor, idDisciplina);
    }


    /**
     * Método para consultar os professores com nomes que começam com os termos
     * digitados pelo usuário.
     *
     * @return
     */
    @Deprecated
    public ArrayList<Professor> consultaComTermos(String termo) {
        return modelo.selectComTermos(termo);
    }
  
    
    
}
