/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Curso;
import entidades.Turma;
import java.util.ArrayList;
import model.TurmaDAO;

/**
 *
 * @author silvio
 */
public class ControleTurma {
    
    // Instancia o modelo desse tipo de objeto
    TurmaDAO modelo = new TurmaDAO();
    
    
    /**
     * Método para atualizar turma no banco de dados.
     * @param antiga
     * @param nova 
     */
    public void atualizar(Turma nova, Turma antiga){
        modelo.update(nova, antiga);
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
        modelo.insert(turmaAdicionar);
    }
    
    
    /**
     * Método para consultar as turmas do banco de dados
     * @return 
     */
    public ArrayList<Turma> consulta(){
        return modelo.select();
    }
    
    /**
     * Método para consultar as turmas com nomes que começam com os termos digitados pelo usuário.
     * @return 
     */
    public ArrayList<Turma> consultaComTermos(String termo){
        return modelo.selectComTermos(termo);
    }
    
    /**
     * Método para retornar somente uma turma (para edição dela).
     * @param termo
     * @return 
     */
    public Turma consultaTurma(String termo){
        return modelo.selectTurma(termo);
    }
    
}
