/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Turma;
import java.util.ArrayList;
import model.TurmaDAO;

/**
 *
 * @author fernando_paladini
 */
public class ControleTurma {
    
    // Instancia o modelo desse tipo de objeto
    private static ControleTurma singleton;
	TurmaDAO modelo;
    
    private ControleTurma(){
    	modelo = new TurmaDAO();
    }
    
    public static ControleTurma getInstance(){
    	if (singleton == null){
    		singleton = new ControleTurma();
    	}
    	return singleton;
    }
    
    
    
    
    
    
    
    /**
     * Método para atualizar turma no banco de dados.
     * @param antiga
     * @param nova 
     */
    public void atualizar(Turma nova){
        modelo.update(nova);
    }
    
    
    /**
     * Método para remover turma no banco de dados
     * @param turma 
     */
    public void remover(Turma turma){
        modelo.delete(turma);
    }
    
    
    /**
     * Método para adicionar uma turma ao banco de dados.
     * @param turmaAdicionar 
     */
    public void adicionar(Turma turmaAdicionar){
        // Se não existir nenhum curso com esse nome, manda inserir o curso.
        if (consultaTurma(turmaAdicionar.getNome()) == null){
            modelo.insert(turmaAdicionar);
        }
    }
    
    
    /**
     * Método para consultar as turmas do banco de dados
     * @return 
     */
    public ArrayList<Turma> consulta(){
        return modelo.select();
    }
    
    /**
     * Método para retornar somente uma turma (para edição dela).
     * @param termo
     * @return 
     */
    public Turma consultaTurma(String termo){
        return modelo.selectTurma(termo);
    }
    
   /**
    * Retorna uma turma de acordo com o ID fornecido.
    * @param id
    * @return
    */
    public Turma consultaTurma(int id){
    	return modelo.selectTurma(id);
    }
    
    /*
     * 
     * 
     * 
     *  
     *                         POSSIVELMENTE DEPRECIADOS
     * 
     * 
     * 
     * 
     * 
     */
    
    /**
     * Método para consultar as turmas com nomes que começam com os termos digitados pelo usuário.
     * @return 
     */
    @Deprecated
    public ArrayList<Turma> consultaComTermos(String termo){
        return modelo.selectComTermos(termo);
    }
    
}
