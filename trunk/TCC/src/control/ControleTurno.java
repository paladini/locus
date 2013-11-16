package control;

import java.util.ArrayList;

import entidades.Turno;
import model.TurnoDAO;

public class ControleTurno {
	
	private static ControleTurno singleton;
	private TurnoDAO modelo;
	
	private ControleTurno(){
		modelo = new TurnoDAO();
	}
	
	public static ControleTurno getInstance(){
		if (singleton == null){
			singleton = new ControleTurno();
		}
		return singleton;
	}
	
	
	/**
	 * Método que retorna todos os turnos do banco
	 * @return
	 */
	public ArrayList<Turno> consulta(){
		return modelo.select();
	}
	

}
