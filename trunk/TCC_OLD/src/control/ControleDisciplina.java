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
//	DisciplinaDAOHibernate modeloHibernate;

	private ControleDisciplina() {
		modelo = new DisciplinaDAO();
//		modeloHibernate = new DisciplinaDAOHibernate();
	}

	public static ControleDisciplina getInstance() {
		if (singleton == null)
			singleton = new ControleDisciplina();
		return singleton;
	}

	/**
	 * Método para atualizar disciplina no banco de dados.
	 * 
	 * @param disciplina
	 */
	public void atualizar(Disciplina disciplina) {
		 modelo.update(disciplina);
//		modeloHibernate.update(disciplina);
	}

	/**
	 * Método para remover disciplina no banco de dados
	 * 
	 * @param disciplina
	 */
	public void remover(int idDisciplina) {
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		 modelo.delete(disciplina);
//		modeloHibernate.delete(disciplina);
	}

	/**
	 * Método para adicionar uma disciplina ao banco de dados.
	 * 
	 * @param disciplinaAdicionar
	 */
	public void adicionar(Disciplina disciplina) {
		// Se não existir nenhum curso com esse nome, manda inserir o curso.
		 if (consultaDisciplina(disciplina.getNome()) == null){
			 modelo.insert(disciplina);
		 }
//		modeloHibernate.insert(disciplina);
	}

	/**
	 * Método para consultar as disciplinas do banco de dados
	 * 
	 * @return
	 */
	public ArrayList<Disciplina> consulta() {
		 return modelo.select();
//		return modeloHibernate.select();
	}

	/**
	 * Método para retornar somente uma disciplina por Id (para edição dela).
	 * 
	 * @param termo
	 * @return
	 */
	public Disciplina consultaDisciplina(int id) {
		 return modelo.selectDisciplina(id);
//		return modeloHibernate.select(id);
	}
	
	/**
	 * Método para retornar somente uma disciplina (para edição dela).
	 * 
	 * @param termo
	 * @return
	 */
	public Disciplina consultaDisciplina(String termo) {
		return modelo.selectDisciplina(termo);
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
	public ArrayList<Disciplina> consultaComTermos(String termo) {
		return modelo.selectComTermos(termo);
	}



}
