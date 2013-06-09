/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Escola;
import entidades.Turno;
import model.EscolaDAO;

/**
 *
 * @author silvio
 */
public class ControleEscola {
    
    EscolaDAO modelo = new EscolaDAO();
    
    /**
     * Método para mudar o nome da escola.
     * @param escola 
     */
   public void mudarNome(Escola escola){
       modelo.mudarInstituicao(escola.getNomeEscola()); 
   }
   
   /**
    * Método para adicionar turnos na escola.
    */
    public void adicionarTurnos(Escola escola) {
        for(Turno temp : escola.getTurnos()){
            modelo.adicionarTurno(temp.getNome());
        }
    }
   
   
    
}
