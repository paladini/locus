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
    
    // Instancia o modelo desse tipo de objeto
    CursoDAO modelo = new CursoDAO();
    
    /**
     * Método para atualizar curso no banco de dados.
     * @param curso 
     */
    public void atualizar(Curso curso){
        modelo.update(curso);
    }
    
    
    /**
     * Método para remover curso no banco de dados
     * @param disciplina 
     */
    public void remover(Curso curso){
        modelo.delete(curso);
    }
    
    
    /**
     * Método para adicionar um curso ao banco de dados.
     * @param disciplinaAdicionar 
     */
    public void adicionar(Curso cursoAdicionar){
        modelo.insert(cursoAdicionar);
    }
    
    /**
     * Método para consultar os cursos do banco de dados
     * @return 
     */
    public ArrayList<Curso> consulta(){
        return modelo.select();
    }
    
    /**
     * Método para consultar os cursos com nomes que começam com os termos digitados pelo usuário.
     * @return 
     */
    public ArrayList<Curso> consultaComTermos(String termo){
        return modelo.selectComTermos(termo);
    }
    
    /**
     * Método para retornar somente um curso (para tela "Editar").
     * @param termo
     * @return 
     */
    public Curso consultaCurso(String termo){
        return modelo.selectCurso(termo);
    }
    
}
