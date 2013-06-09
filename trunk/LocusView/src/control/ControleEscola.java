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
   public int mudarNome(Escola escola){
       if (modelo.mudarInstituicao(escola.getNomeEscola())){
           return 0;
       }else{
           return 1;
       }
   }
   
   /**
    * Método para adicionar turnos na escola.
    */
    public int adicionarTurnos(Escola escola) {
        int resultado = 0;
        for(Turno temp : escola.getTurnos()){
            if (modelo.adicionarTurno(temp.getNome())){
                
            }else{
                resultado = 1;
            }
        }
        return resultado;
    }
   
   
    
}
