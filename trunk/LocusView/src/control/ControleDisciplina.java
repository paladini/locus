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
    public String consulta(){
        ArrayList<Disciplina> disciplinas = modelo.select();
        
        String nomesDisciplinas = "";
        for (Disciplina temp : disciplinas){
            nomesDisciplinas += temp.getNome() + "\n";
        }
        return nomesDisciplinas;
    }
            
    
}
