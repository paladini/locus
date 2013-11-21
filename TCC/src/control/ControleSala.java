package control;

import java.util.ArrayList;

import model.SalaDAO;
import entidades.Sala;

public class ControleSala {
	
	// Padrão de projeto Singleton
	private static ControleSala singleton;
	SalaDAO modelo;

	private ControleSala() {
		modelo = new SalaDAO();
	}

	public static ControleSala getInstance() {
		if (singleton == null)
			singleton = new ControleSala();
		return singleton;
	}

	
	
	/**
	 * Retorna uma lista com todas Salas do banco de dados.
	 * 
	 * @return
	 */
	public ArrayList<Sala> consultar() {
		 return modelo.consultar();
	}

	/**
	 * Retorna uma Sala do banco de dados, de acordo com o ID fornecido.
	 * 
	 * @param termo
	 * @return
	 */
	public Sala consultar(int id) {
		 return modelo.consultar(id);
	}
	
	/**
	 * Retorna uma Sala do banco de dados, de acordo com o nome da sala fornecido.
	 * 
	 * @param termo
	 * @return
	 */
	public Sala consultar(String termo) {
		return modelo.consultar(termo);
	}
	
	/**
	 * Atualiza uma Sala no banco de dados.
	 * 
	 * @param sala
	 */
	public void atualizar(Sala sala) {
		 modelo.atualizar(sala);
	}

	/**
	 * Remove uma Sala do banco de dados.
	 * 
	 * @param idSala ID da sala a ser removida.
	 */
	public void remover(int idSala) {
		Sala sala = new Sala();
		sala.setId(idSala);
		modelo.deletar(sala);
	}

	/**
	 * Insere uma Sala no banco de dados.
	 * 
	 * @param disciplinaAdicionar
	 */
	public void inserir(Sala sala) {
		// Se não existir nenhum curso com esse nome, manda inserir o curso.
		 if (consultar(sala.getNome()) == null){
			 modelo.inserir(sala);
		 }
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 
	 *                 POSSIVELMENTE DEPRECIADOS (DELETAR FUTURAMENTE, SE NÃO FOREM NECESSÁRIOS DE FATO)
	 * 
	 * 
	 */
	/**
	 * Método para consultar as disciplinas com nomes que começam com os termos
	 * digitados pelo usuário.
	 * 
	 * @return
	 */
	@Deprecated
	public ArrayList<Sala> consultaComTermos(String termo) {
		return modelo.selectComTermos(termo);
	}


}
