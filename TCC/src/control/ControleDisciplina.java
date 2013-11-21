/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Disciplina;

import java.util.ArrayList;

import model.DisciplinaDAO;

//import model.DisciplinaDAOHibernate;

/**
 * 
 * @author fernando_paladini
 */
public class ControleDisciplina {

	/*
	 * Padrão de projeto Singleton.
	 */
	private static ControleDisciplina singleton;
	DisciplinaDAO modelo;

	private ControleDisciplina() {
		modelo = new DisciplinaDAO();
	}

	public static ControleDisciplina getInstance() {
		if (singleton == null)
			singleton = new ControleDisciplina();
		return singleton;
	}

	
	/**
	 * Retorna uma lista com todas as disciplinas cadastradas no banco de dados.
	 * 
	 * @return
	 */
	public ArrayList<Disciplina> consultar() {
		return modelo.consultar();
	}

	/**
	 * Retorna uma disciplina do banco de dados, de acordo com o ID fornecido.
	 * 
	 * @param termo
	 * @return
	 */
	public Disciplina consultar(int id) {
		return modelo.consultar(id);
	}

	/**
	 * Retorna uma disciplina do banco de dados, de acordo com o nome fornecido.
	 * 
	 * @param termo
	 * @return
	 */
	public Disciplina consultar(String termo) {
		return modelo.consultar(termo);
	}

	
	
	
	/**
	 * Atualiza no banco de dados a disciplina fornecida.
	 * 
	 * @param disciplina
	 */
	public void atualizar(Disciplina disciplina) {
		modelo.atualizar(disciplina);
	}

	/**
	 * Remova do banco de dados a Disciplina, de acordo com o ID fornecido.
	 * 
	 * @param disciplina
	 */
	public void remover(int idDisciplina) {
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		modelo.deletar(disciplina);
	}

	/**
	 * Insere uma disciplina no banco de dados.
	 * 
	 * @param disciplinaAdicionar
	 */
	public void inserir(Disciplina disciplina) {
		// Se não existir nenhum curso com esse nome, manda inserir o curso.
		if (consultar(disciplina.getNome()) == null) {
			modelo.inserir(disciplina);
		}
	}


	/*
	 * 
	 * POSSIVELMENTE DEPRECIADOS (DELETAR FUTURAMENTE, SE NÃO FOREM NECESSÁRIOS
	 * DE FATO)
	 */
	/**
	 * Método para consultar as disciplinas com nomes que começam com os termos
	 * digitados pelo usuário.
	 * 
	 * @return
	 */
	@Deprecated
	public ArrayList<Disciplina> consultaComTermos(String termo) {
		return modelo.selectComTermos(termo);
	}

}
