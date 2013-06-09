/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Disciplina;
import java.util.ArrayList;
import model.DisciplinaDAO;
/**
 *
 * @author silvio
 */
public class ControleDisciplina {
    
    // Instancia o modelo desse tipo de objeto
    DisciplinaDAO modelo = new DisciplinaDAO();
    
    
    /**
     * Método para atualizar disciplina no banco de dados.
     * @param antiga
     * @param nova 
     */
    public void atualizar(Disciplina nova, Disciplina antiga){
        modelo.update(nova, antiga);
    }
    
    
    /**
     * Método para remover disciplina no banco de dados
     * @param disciplina 
     */
    public void remover(Disciplina disciplina){
        modelo.delete(disciplina);
    }
    
    
    /**
     * Método para adicionar uma disciplina ao banco de dados.
     * @param disciplinaAdicionar 
     */
    public void adicionar(Disciplina disciplinaAdicionar){
        modelo.insert(disciplinaAdicionar);
    }
    
    /**
     * Método para consultar as disciplinas do banco de dados
     * @return 
     */
    public ArrayList<Disciplina> consulta(){
        return modelo.select();
    }
    
    /**
     * Método para consultar as disciplinas com nomes que começam com os termos digitados pelo usuário.
     * @return 
     */
    public ArrayList<Disciplina> consultaComTermos(String termo){
        return modelo.selectComTermos(termo);
    }
    
    /**
     * Método para retornar somente uma disciplina (para edição dela).
     * @param termo
     * @return 
     */
    public Disciplina consultaDisciplina(String termo){
        return modelo.selectDisciplina(termo);
    }
            
    
}
