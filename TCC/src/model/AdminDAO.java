package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Admin;

public class AdminDAO extends AbstractDAO{
	
	/**
	 * Método que retorna um objeto Admin do banco de dados com todos os seus atributos.
	 * @return
	 */
	public Admin consultar() {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM admin;";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            if (rs.next()){
                
            	// Pegando os dados da tabela do Admin
            	Admin admin = new Admin();
                admin.setLogin(rs.getString("login"));
                admin.setSenha(rs.getString("senha"));
                admin.setUltimoacesso(rs.getDate("ultimo_acesso"));
                admin.setNomeEscola(rs.getString("nomeEscola"));
                return admin;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
	
	/**
	 * Atualiza no banco de dados o nome da instituição de ensino (nome da escola).
	 * @param novoNomeEscola
	 */
	public void atualizarNomeEscola(String novoNomeEscola){
		String sql = "UPDATE admin SET nomeEscola = ? WHERE login = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(novoNomeEscola);
        params.add("admin");
        operacaoEscrita(sql, params);
	}
	
	/**
	 * Atualiza o último acesso do usuário para o horário atual (método MYSQL 'now()' ).
	 */
	public void atualizarUltimoAcesso(){
		String sql = "UPDATE admin SET ultimo_acesso = now() WHERE login = ?;";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add("admin");
		operacaoEscrita(sql, params);
	}
	
	/**
	 * Atualiza a senha de administrador do programa.
	 * @param novaSenha
	 */
	public void atualizarSenha(String novaSenha){
		String sql = "UPDATE admin SET senha = md5(?) WHERE login = ?;";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(novaSenha);
		params.add("admin");
		operacaoEscrita(sql, params);
	}
	
	/**
	 * Método para autenticar o Login do usuário.
	 * @param tentativa
	 * @return
	 */
	public Admin autenticarLogin(Admin tentativa){
		Connection connection = Conexao.getConexao();
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM admin WHERE login = ? and senha = md5(?);";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setString(1, tentativa.getLogin());
			prest.setString(2, tentativa.getSenha());
			rs = prest.executeQuery();
			if (rs.next()) {
				
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				String nomeEscola = rs.getString("nomeEscola");
				Date ultimo_acesso = rs.getDate("ultimo_acesso");

				// Setando atributos ao objeto
				Admin admin = new Admin();
				admin.setLogin(login);
				admin.setSenha(senha);
				admin.setNomeEscola(nomeEscola);
				admin.setUltimoacesso(ultimo_acesso);

				return admin;
			} else {
				return null;
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	

}
