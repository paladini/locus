/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Disciplina;
import model.DisciplinaDAO;



/**
 *
 * @author silvio
 */
public class ControleDisciplina {
    
    // Instanciando modelo do ControleCurso
    // Instancia o modelo desse tipo de objeto
    DisciplinaDAO modelo = new DisciplinaDAO();
    
    public void adicionar(Disciplina disciplinaAdicionar){
        modelo.insert(disciplinaAdicionar);
    }
    
}
