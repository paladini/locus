package control;

import model.AdminDAO;
import entidades.Admin;
import entidades.Dia;
import entidades.Turno;

public class ControleAdmin {

	private static ControleAdmin singleton;
	private AdminDAO modelo;

	private ControleAdmin() {
		modelo = new AdminDAO();
	}

	public static ControleAdmin getInstance() {
		if (singleton == null) {
			singleton = new ControleAdmin();
		}
		return singleton;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Método para retornar um objeto Admin.
	 * @return
	 */
	public Admin consultar() {
		return modelo.consultar();
	}

	/**
	 * Método para mudar o nome da escola.
	 * 
	 * @param escola
	 */
	public int atualizarNomeEscola(Admin admin) {
		if (admin.getNomeEscola().length() > 0
				|| !(admin.getNomeEscola().isEmpty())) {
			modelo.atualizarNomeEscola(admin.getNomeEscola());
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * Método para atualizar o último acesso do usuário para a hora atual.
	 */
	public void atualizarUltimoAcesso(){
		modelo.atualizarUltimoAcesso();
	}
	
	/**
	 * Método para atualizar senha do administrador do sistema.
	 * @param novaSenha
	 * @return
	 */
	public int atualizarSenha(String novaSenha){
		
		// Caso a senha tenha algum dígito, retorna 0 e muda a senha.
		if(!(novaSenha.isEmpty() || novaSenha.length() == 0)){
			modelo.atualizarSenha(novaSenha);
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * Verifica se o Login é valido e sob quais condiçoes
	 * 
	 * @param loginTentativa
	 *            Recebe a tentativa de login com os dados que o usuário
	 *            inseriu.
	 * @return 0 = Pula direto para o menu principal; 1 = Pula para o
	 *         "Primeiro Acesso"; 2 = Login ou senha errados
	 */
	public int autenticarLogin(Admin loginTentativa) {

		Admin loginOriginal = modelo.autenticarLogin(loginTentativa);

		if (loginOriginal != null) {
			if (loginOriginal.getUltimoacesso() == null) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 2;
		}
	}
	

	/**
	 * Método resumido para adicionar turnos e dias para uma escola. TODO:
	 * Santana vai me bater, arrumar isso.
	 * 
	 * @param escola
	 * @return
	 */
	// public int adicionarHorario(Escola escola){
	// int resultado = 0;
	//
	// // Removendo todos os dias e turnos
	// // TODO: Arrumar essa zueira
	// modelo.removerTurnos();
	// modelo.removerDias();
	//
	// // Percorre o ArrayList de Turnos, chamando cada elemento de "temp".
	// for(Turno temp : escola.getTurnos()){
	// System.out.println("Controle Turno: " + temp.getNome());
	// if (modelo.adicionarTurno(temp.getNome())){
	//
	// }else{
	// resultado = 1;
	// }
	// }
	//
	// // Verifica se na etapa anterior teve algum erro.
	// // Se não teve, percorre o ArrayList de Dias, chamando cada elemento de
	// "temp".
	// if (resultado == 0){
	//
	// for(Dia temp : escola.getDias()){
	// System.out.println("Controle Dia: " + temp.getNome());
	// if (modelo.adicionarDia(temp.getNome())){
	//
	// }else{
	// resultado = 1;
	// }
	// }
	// }
	//
	// return resultado;
	// }

	

}
