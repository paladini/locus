/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;

import entidades.Curso;
import entidades.Dia;
import entidades.Turno;
import model.DiaDAO;

/**
 * 
 * @author fernando_paladini
 */
public class ControleDia {

	// Criando um modelo
	private static ControleDia controleDia;
	private DiaDAO modelo;

	private ControleDia() {
		modelo = new DiaDAO();
	}

	public static ControleDia getInstance() {
		if (controleDia == null) {
			controleDia = new ControleDia();
		}
		return controleDia;
	}

	
	
	
	public void atualizarDias(Dia dia){
		this.adicionarTurno(dia);
	}
	
	/**
	 * Retorna uma lista de todos os Dias cadastrados no banco de dados.
	 * 
	 * @return
	 */
	public ArrayList<Dia> consulta() {
		ArrayList<Dia> listaDias = modelo.consultar();
		return listaDias;
	}

	/**
	 * Retorna um Dia do banco de dados, de acordo com o nome do dia fornecido.
	 * 
	 * @param dia
	 * @return
	 */
	public Dia consultar(String dia) {
		return modelo.consultar(dia);
	}

	/**
	 * Retorna um Dia do banco de dados, de acordo com o ID fornecido.
	 * 
	 * @param dia
	 * @return
	 */
	public Dia consultar(int dia) {
		return modelo.consultar(dia);
	}

	/**
	 * Retorna um ID de dia do banco de dados, de acordo com o nome do dia
	 * fornecido.
	 * 
	 * @param nomeDia
	 * @return
	 */
	public int consultarID(String nomeDia) {
		Dia d = this.consultar(nomeDia);
		return d.getId();
	}

	/**
	 * Atualiza um dia do banco de dados.
	 * 
	 * @param curso
	 */
	public void atualizar(Dia dia) {
		if (dia.getNome() == null || dia.getNome() == " "
				|| dia.getNome() == "") {

		} else {
			modelo.atualizar(dia);
			this.adicionarTurno(dia);
		}
	}

	/**
	 * Insere um Dia no banco de dados - SOMENTE SE ESSE DIA AINDA NÃO EXISTIR
	 * 
	 * @param dia
	 */
	public void inserir(Dia dia) {

		// Faz uma query no banco de dados buscando algum Dia com o mesmo nome
		// do que está prestes a ser inserido.
		Dia diaBanco = modelo.consultar(dia.getNome());

		/*
		 * Se o diaBanco for igual a null ou diferente do nome do "dia", insere
		 * no banco de dados.
		 */
		if (diaBanco == null) {
			modelo.inserir(dia);

			// O ID atualmente é 0, precisa atualizar com o ID que o banco deu
			// no auto-increment.
			int id = this.consultarID(dia.getNome());
			dia.setId(id);

			// Caso tenha alguma disciplina associada, cria as associações no
			// banco
			if (dia.getT1() != null | dia.getT2() != null | dia.getT3() != null) {
				this.adicionarTurno(dia);
			}
		}

	}

	/**
	 * Remove um Dia do banco de dados - SOMENTE SE ESSE DIA JÁ EXISTIR
	 * 
	 * @param dia
	 */
	public void deletar(Dia dia) {
		// Faz uma query no banco de dados buscando algum Dia com o mesmo nome
		// do que está prestes a ser inserido.
		Dia diaBanco = modelo.consultar(dia.getNome());

		/*
		 * Se o diaBanco for igual a null ou diferente do nome do "dia", insere
		 * no banco de dados.
		 */
		if (diaBanco != null) {
			modelo.deletar(dia);
		}
	}

	/**
	 * Faz uma consulta no banco e checa se esse dia já existe
	 */
	public Boolean checarDia(Dia dia) {
		if (modelo.consultar(dia.getNome()) != null) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 
	 * ======================================================================
	 * 
	 * Métodos de interação turno-dia
	 * 
	 * ======================================================================
	 */

	/**
	 * Atualiza todos os turnos desse dia no banco de dados. 1) Deleta todos os
	 * turnos antigos desse dia do banco. 2) Adiciona todos os novos turnos
	 * (getListaDias()) desse turno no banco.
	 * 
	 * @param dia
	 *            Turno com uma lista de dias não nula.
	 */
	private void adicionarTurno(Dia dia) {
		
		
		
		for(int i = 0; i < dia.getVetorTurnosSelecionados().length; i++){
			
			if(dia.getVetorTurnosSelecionados()[i] != null ){
				
				ControleTurno ct = ControleTurno.getInstance();
				Turno t = ct.consultar(dia.getVetorTurnosSelecionados()[i]);
				modelo.inserirTurnoDia(dia.getId(), t.getId());
			}
			
		}
	}
	
	public void deletarTodosTurnoDia(){
		modelo.deletarTodosTurnoDia();
	}

	/**
	 * Retorna a lista de turnos associadas à esse Dia.
	 * 
	 * @param professor
	 * @return
	 */
	public ArrayList<Turno> listaTurnosAssociados(Dia dia) {
		return modelo.listaTurnosAssociados(dia);
	}

	/**
	 * Retorna a lista de turnos não associadas à esse Dia.
	 * 
	 * @param professor
	 * @return
	 */
	public ArrayList<Turno> listaTurnosNaoAssociados(Dia dia) {
		return modelo.listaTurnosNaoAssociados(dia);
	}

}
