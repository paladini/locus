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
            
    
}
