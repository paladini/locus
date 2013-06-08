/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author silvio
 */
public class ControleGenerico {
    
    public int procura(Object[] vetor, Object elementoProcurado) {
     for (int i = 0; i < vetor.length; i++)
         if (vetor[i].equals(elementoProcurado))
             return i;
     return -1; // Não achou, retorna -1
    }
    
}
