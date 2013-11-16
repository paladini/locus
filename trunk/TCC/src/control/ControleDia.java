/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;

import entidades.Dia;
import model.DiaDAO;

/**
 *
 * @author silvio
 */
public class ControleDia {
    
    // Criando um modelo
	private static ControleDia controleDia;
    private DiaDAO modelo;
    
    private ControleDia(){
    	modelo = new DiaDAO();
    }
    
    public static ControleDia getInstance(){
    	if (controleDia == null){
    		controleDia = new ControleDia();
    	}
    	return controleDia;
    }
    
    public ArrayList<Dia> consulta(){
    	return modelo.consulta();
    }
    
    
    
    /**
     * Faz uma consulta no banco e checa se esse dia já existe
     */
    public Boolean checarDia(Dia dia){
        if (modelo.selectDia(dia.getNome()) != null){
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * Adiciona dia na tabela "Dia" somente se esse dia não existir ainda
     * @param dia 
     */
    public void adicionarDia(Dia dia){
        
        // Faz uma query no banco de dados buscando algum Dia com o mesmo nome do que está prestes a ser inserido.
        Dia diaBanco = modelo.selectDia(dia.getNome());
        
        /*
         * Se o diaBanco for igual a null ou diferente do nome do "dia", insere no banco de dados.
         */
        if (diaBanco == null){
            modelo.insert(dia);
        }
        
    }
    
    /**
     * Remove dia na tabela "Dia" somente se esse dia já existir
     * @param dia 
     */
    public void removerDia(Dia dia){
        // Faz uma query no banco de dados buscando algum Dia com o mesmo nome do que está prestes a ser inserido.
        Dia diaBanco = modelo.selectDia(dia.getNome());
        
        /*
         * Se o diaBanco for igual a null ou diferente do nome do "dia", insere no banco de dados.
         */
        if (diaBanco != null){
            modelo.delete(dia);
        }
    }
    
    
    
}
