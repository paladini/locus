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
     * Retorna uma lista com todas as Turmas do banco de dados.
     * @return 
     */
    public ArrayList<Turma> consultar(){
        return modelo.consultar();
    }
    
    /**
     * Retorna uma Turma do banco de dados, de acordo com o nome da turma informado.
     * @param termo
     * @return 
     */
    public Turma consultar(String termo){
        return modelo.consultar(termo);
    }
    
   /**
    * Retorna uma Turma do banco de dados, de acordo com o ID fornecido.
    * @param id
    * @return
    */
    public Turma consultar(int id){
    	return modelo.consultar(id);
    }
    
    
    
    
    
    
    
    /**
     * Atualiza uma Turma no banco de dados.
     * @param antiga
     * @param nova 
     */
    public void atualizar(Turma nova){
        modelo.atualizar(nova);
    }
    
    
    /**
     * Remove uma Turma do banco de dados
     * @param turma 
     */
    public void remover(Turma turma){
        modelo.deletar(turma);
    }
    
    
    /**
     * Insere uma nova Turma no banco de dados.
     * @param turmaAdicionar 
     */
    public void inserir(Turma turmaAdicionar){
        // Se não existir nenhum curso com esse nome, manda inserir o curso.
        if (consultar(turmaAdicionar.getNome()) == null){
            modelo.inserir(turmaAdicionar);
        }
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
