/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Dia;
import entidades.Escola;
import entidades.Turno;
import model.EscolaDAO;

/**
 *
 * @author silvio
 */
public class ControleEscola {
    
    EscolaDAO modelo = new EscolaDAO();
    
    public Escola consultar(){
        return modelo.consultar();
    } 
    
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
    * Método resumido para adicionar turnos e dias para uma escola.
    * @param escola
    * @return 
    */
   public int adicionarHorario(Escola escola){
       int resultado = 0;
       
       // Percorre o ArrayList de Turnos, chamando cada elemento de "temp".
       for(Turno temp : escola.getTurnos()){
            if (modelo.adicionarTurno(temp.getNome())){
                
            }else{
                resultado = 1;
            }
        }
       
       // Verifica se na etapa anterior teve algum erro.
       // Se não teve, percorre o ArrayList de Dias, chamando cada elemento de "temp".
       if (resultado == 0){
           for(Dia temp : escola.getDias()){
                if (modelo.adicionarDia(temp.getNome())){
                    
                }else{
                     resultado = 1;
                }
            }
       }
       
       return resultado;
   }
   
   
   
   
   
    
}
