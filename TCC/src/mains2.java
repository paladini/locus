import java.util.ArrayList;

import control.ControleEnsalamento;
import entidades.Aula;


public class mains2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ControleEnsalamento processarEnsalamento = ControleEnsalamento.getInstance();
		ArrayList<Aula> aulas;
//		processarEnsalamento.Ensalar();
		processarEnsalamento.Ensalar();
		aulas = processarEnsalamento.getAulas();
		printGradeHorario(aulas);
		processarEnsalamento.Persistir();

		
		System.out.println("====================================================================");
		
		
		
		
	}
	
	private static void printGradeHorario(ArrayList<Aula> aulas){
		int cont = 0;
		
		for (Aula aula : aulas) {
			System.out.println(aula.texto());
			cont++;
			if (cont == 5) {
				System.out.println();
				System.out.println();
				cont = 0;
			}
		}
		
	}

}
