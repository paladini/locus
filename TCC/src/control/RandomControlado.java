package control;
import java.util.ArrayList;
import java.util.Random;


public class RandomControlado {

	private static ArrayList<Integer> lista;
	
	public static void main(String[] args) {
		
		Random r = new Random();
		
		lista = new ArrayList<Integer>(5);
		
		for (int i=0; i<5; i++) {
			int numero = r.nextInt(5);
			if (jaExiste(numero) == false)
				lista.add( numero );
		}
		
		print(lista);
		
	}
	
	private static boolean jaExiste(Integer numero){
		
		for (Integer elemento : lista) {
			if (elemento.compareTo(numero) == 0)
				return true;
		}
		
		return false;
	}
	
	private static void print(ArrayList<Integer> lista){
		for (Integer elemento : lista) {
			System.out.println(elemento);
		}
	}
	
	
	
	public static ArrayList<Integer> retornaRandom(int limite){
		Random r = new Random();
		
		lista = new ArrayList<Integer>(limite);
		
		for (int i=0; i<limite; i++) {
			int numero = r.nextInt(limite);
			if (jaExiste(numero) == false)
				lista.add( numero );
		}
		
		return lista;
	}
	
}
