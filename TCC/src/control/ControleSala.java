package control;

import java.util.ArrayList;

import model.DisciplinaDAO;
import model.SalaDAO;
import entidades.Disciplina;
import entidades.Sala;

public class ControleSala {
	
	// Padrão de projeto Singleton
	private static ControleSala singleton;
	SalaDAO modelo;
//	SalaDAOHibernate modeloHibernate;

	private ControleSala() {
		modelo = new SalaDAO();
//		modeloHibernate = new SalaDAOHibernate();
	}

	public static ControleSala getInstance() {
		if (singleton == null)
			singleton = new ControleSala();
		return singleton;
	}

	
	
	
	
	
	
	
	/**
	 * Método para atualizar sala no banco de dados.
	 * 
	 * @param sala
	 */
	public void atualizar(Sala sala) {
		 modelo.update(sala);
//		modeloHibernate.update(disciplina);
	}

	/**
	 * Método para remover sala no banco de dados
	 * 
	 * @param idSala ID da sala a ser removida.
	 */
	public void remover(int idSala) {
		Sala sala = new Sala();
		sala.setId(idSala);
		modelo.delete(sala);
//		modeloHibernate.delete(sala);
	}

	/**
	 * Método para adicionar uma sala ao banco de dados.
	 * 
	 * @param disciplinaAdicionar
	 */
	public void adicionar(Sala sala) {
		// Se não existir nenhum curso com esse nome, manda inserir o curso.
		 if (consultaSala(sala.getNome()) == null){
			 modelo.insert(sala);
		 }
//		modeloHibernate.insert(disciplina);
	}

	/**
	 * Método para consultar as salas do banco de dados
	 * 
	 * @return
	 */
	public ArrayList<Sala> consulta() {
		 return modelo.select();
//		return modeloHibernate.select();
	}

	/**
	 * Método para retornar somente uma sala por Id (para edição dela).
	 * 
	 * @param termo
	 * @return
	 */
	public Sala consultaSala(int id) {
		 return modelo.selectSala(id);
//		return modeloHibernate.select(id);
	}
	
	/**
	 * Método para retornar somente uma sala (para edição dela).
	 * 
	 * @param termo
	 * @return
	 */
	public Sala consultaSala(String termo) {
		return modelo.selectSala(termo);
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
