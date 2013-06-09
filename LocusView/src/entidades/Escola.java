/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author fernando_paladini
 */
public class Escola {
 
    // Atributos
    private String nomeEscola;
    private ArrayList<Turno> turnos = new ArrayList<Turno>();

    // Construtores
    public Escola() {
    }

    public Escola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
        
    }
 
    // Métodos
    public void adicionarTurno(Turno turno){
        getTurnos().add(turno);
    }
    
    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }
    
        public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }
    
}
