/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Curso;
import model.CursoDAO;

/**
 *
 * @author silvio
 */
public class ControleCurso {
    
    // Instancia o modelo desse tipo de objeto
    CursoDAO modelo = new CursoDAO();
    
    public void adicionar(Curso cursoAdicionar){
        modelo.insert(cursoAdicionar);
    }
    
}
