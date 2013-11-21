package control;

import java.util.ArrayList;

import entidades.Dia;
import entidades.Disciplina;
import entidades.Professor;
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
     * Retorna todos os Turnos ativos no banco de dados.
     * @return
     */
    public ArrayList<Turno> consultarAtivos(){
    	
    	ArrayList<Turno> listaTodosTurnos = modelo.consultar();
    	ArrayList<Turno> listaTurnosAtivos = new ArrayList<Turno>();
    	
    	for(Turno t : listaTodosTurnos){
    		if(t.isAtivo()){
    			listaTurnosAtivos.add(t);
    		}
    	}
    	
    	return listaTurnosAtivos;
    }
	
	
	/**
	 * Atualiza os turnos no banco de dados
	 * @param listaTurnos
	 */
	public void atualizarTurnos(ArrayList<Turno> listaTurnos){
		
		// Pegando ID correto do banco
		for(Turno t : listaTurnos){
			t.setId(this.consultarID(t.getNome()));
		}
		
		// Salvando o banco pois vou deixar todo o banco como "inativo"
		ArrayList<Turno> banco = modelo.consultar();
		
		// Deixando o banco como inativo
		modelo.tudoInativo();
		
		for(Turno t : listaTurnos){
			
			// Verificando se está ativo ou não
			boolean estaAtivo = false;
			for(Turno temp : banco){
				if(temp.getId() == t.getId()){
					if(temp.isAtivo()){
						estaAtivo = true;
					}
				}
			}
			
			// Se o turno que está vindo da View está ativo
			if(t.isAtivo()){
				
				// Se no banco estiver ativo  não faz nada
				if(estaAtivo){
					
				}else{ // caso o turno do banco não esteja ativo, como o turno vindo da View está ativo, precisa ativar isso
					modelo.atualizar(t);
				}
				
			}else{ // se o turno que está na view não está ativo
				
				// Se no banco estiver ativo, desativa
				if(estaAtivo){
					modelo.atualizar(t);
				}else{ // se não está ativo, não faz nada.
					
				}
			}
		}
	}
	
	/**
	 * Verifica se o dado turno está ativo no banco de dados.
	 * @param turno
	 * @return
	 */
	@Deprecated
	private boolean estaAtivo(Turno turno){
		Turno banco = modelo.consultar(turno.getId());
		return banco.isAtivo();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * =================================
	 * 
	 *   POSSIVELMENTE DESNECESSÁRIOS
	 * 
	 * =================================
	 */
	
	/**
	 * Retorna uma lista com todos os Turnos cadastrados no banco de dados.
	 * @return
	 */
	public ArrayList<Turno> consultar(){
		return modelo.consultar();
	}
	
	/**
	 * Retorna um Turno do banco de dados, de acordo com o nome do Turno fornecido.
	 * @param termo
	 * @return
	 */
	public Turno consultar(String termo){
		return modelo.consultar(termo);
	}
	
	/**
	 * Retorna um Turno do banco de dados, de acordo com o ID fornecido.
	 * @param idTurno
	 * @return
	 */
	public Turno consultar(int idTurno){
		return modelo.consultar(idTurno);
	}
	
	/**
	 * Retorna o ID de um Turno, de acordo com o nome do turno fornecido.
	 * @param termo
	 * @return
	 */
	public int consultarID(String termo){
		Turno t = modelo.consultar(termo);
		return t.getId();
	}
	
	
	/**
	 * Insere um Turno no banco de dados.
	 * @param turno
	 */
	public void inserir(Turno turno){
		modelo.inserir(turno);
	}
	
	/**
	 * Atualiza um Turno no banco de dados.
	 * @param turno
	 */
	public void atualizar(Turno turno){
		modelo.atualizar(turno);
	}
	
	/**
	 * Remove um Turno do banco de dados.
	 * @param turno
	 */
	public void remover(Turno turno){
		modelo.deletar(turno);
	}
	
	/*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação turno-dia
     * 
     *    ======================================================================
     * 
     */
    
    /**
     * Atualiza todos dias desse turno no banco de dados.
     * 1) Deleta todos os dias antigos desse professor do banco.
     * 2) Adiciona todos os novos dias (getListaDias()) desse turno no banco.
     * @param turno Turno com uma lista de dias não nula.
     */
    private void adicionarDia(Turno turno){
    	
    	ArrayList<Dia> listaDiasBanco = this.listaDiasAssociados(turno);
    	
    	// TODO: Essa forma de fazer isso está, no mínimo, ingênua.
    	// Deletando TODOS os dias associadas ao turno.
    	for(int i = 0; i < listaDiasBanco.size(); i++){
    		modelo.deletarTurnoDia(turno.getId(), listaDiasBanco.get(i).getId());
    	}
    	
    	// Adicionando os novos dias ao banco.
    	for(int i = 0; i < turno.getListaDias().size(); i++){
    		modelo.inserirTurnoDia(turno.getId(), turno.getListaDias().get(i).getId());
    	}
    }
    
    /**
     * Retorna a lista de dias associadas à esse Turno.
     *
     * @param professor
     * @return
     */
    public ArrayList<Dia> listaDiasAssociados(Turno turno) {
        return modelo.listaDiasAssociados(turno);
    }

    /**
     * Retorna a lista de dias não associadas à esse Turno.
     *
     * @param professor
     * @return
     */
    public ArrayList<Dia> listaDiasNaoAssociados(Turno turno) {
        return modelo.listaDiasNaoAssociados(turno);
    }
	

}
