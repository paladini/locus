/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Disciplina;
import entidades.Sala;
import java.util.ArrayList;
import model.SalaDAO;

/**
 *
 * @author silvio
 */
public class ControleSala {
    
    // Instancia o modelo desse tipo de objeto
    SalaDAO modelo = new SalaDAO();
    
    
    /**
     * Método para atualizar sala no banco de dados.
     * @param sala 
     */
    public void atualizar(Sala sala){
        modelo.update(sala);
    }
    
    
    /**
     * Método para remover sala no banco de dados
     * @param sala 
     */
    public void remover(Sala sala){
        modelo.delete(sala);
    }
    
    
    /**
     * Método para adicionar uma sala ao banco de dados.
     * @param salaAdicionar 
     */
    public void adicionar(Sala salaAdicionar){
        modelo.insert(salaAdicionar);
    }
    
    /**
     * Método para consultar as salas do banco de dados
     * @return 
     */
    public ArrayList<Sala> consulta(){
        return modelo.select();
    }
    
    /**
     * Método para consultar as salas com nomes que começam com os termos digitados pelo usuário.
     * @return 
     */
    public ArrayList<Sala> consultaComTermos(String termo){
        return modelo.selectComTermos(termo);
    }
    
    /**
     * Método para retornar somente uma sala (para edição dela).
     * @param termo
     * @return 
     */
    public Sala consultaSala(String termo){
        return modelo.selectSala(termo);
    }
    
}
