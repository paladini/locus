/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Turno;
import model.TurnoDAO;

/**
 *
 * @author silvio
 */
public class ControleTurno {
    
    // Criando um modelo
    TurnoDAO modelo = new TurnoDAO();
    
    /**
     * Faz uma consulta no banco e checa se esse turno já existe
     */
    public Boolean checarDia(Turno turno){
        if (modelo.selectTurno(turno.getNome()) != null){
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * Adiciona turno na tabela "turno" somente se esse turno não existir ainda
     * @param turno 
     */
    public void adicionarDia(Turno turno){
        
        // Faz uma query no banco de dados buscando algum turno com o mesmo nome do que está prestes a ser inserido.
        Turno turnoBanco = modelo.selectTurno(turno.getNome());
        
        /*
         * Se o turnoBanco for igual a null ou diferente do nome do "turno", insere no banco de dados.
         */
        if (turnoBanco == null){
            modelo.insert(turno);
        }
        
    }
    
    /**
     * Remove turno na tabela "turno" somente se esse turno já existir
     * @param turno 
     */
    public void removerDia(Turno turno){
        
        // Faz uma query no banco de dados buscando algum turno com o mesmo nome do que está prestes a ser inserido.
        Turno turnoBanco = modelo.selectTurno(turno.getNome());
        
        /*
         * Se o turnoBanco for igual a null ou diferente do nome do "turno", insere no banco de dados.
         */
        if (turnoBanco != null){
            modelo.delete(turno);
        }
    }
    
}
