/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Curso;
import java.util.ArrayList;
import model.CursoDAO;

/**
 *
 * @author luiz_malaquias
 */
public class TestaCursoDAO {
    public static void main(String[] args) {
        
        
    String nome = "TecInf";
    String nome1 = "TecInf1";
    
    Curso c = new Curso();
    c.setNome(nome);
    Curso c1 = new Curso();
    c1.setNome(nome1);
    
    CursoDAO cDao = new CursoDAO();
    
    //insert
    cDao.insert(c);
    cDao.insert(c1);
    
    //delete
    cDao.delete(c1);
    
    //uptade
    cDao.update(c1, c);
    
    //select
   ArrayList<Curso> alc = cDao.select();
        
        for(Curso curso : alc){
            System.out.println(c);
        }
        
            
            
            
            
            
        
    }
    
    
    
}
