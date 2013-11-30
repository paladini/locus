package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Aula;

public class AulaDao extends AbstractDAO {

	ProfessorDAO professorDAO = new ProfessorDAO();
	TurmaDAO turmaDAO = new TurmaDAO();
	SalaDAO salaDAO = new SalaDAO();
	TurnoDAO turnoDAO = new TurnoDAO();
	CursoDAO cursoDAO = new CursoDAO();
	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	DiaDAO diaDAO = new DiaDAO();

	/**
	 * traz todas as aulas do banco de dados
	 * 
	 * @return
	 */
	public ArrayList<Aula> consultar() {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula;";
			PreparedStatement prest = connection.prepareStatement(sql);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos as aulaa que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais uma
			 * aulas, ele vai fazendo os comandos que estao ai dentro. Quando
			 * nao existir mais uma aula (rs.next() == false), ele sai do Loop,
			 * fecha a conexao e retorna o ArrayList com todas as aulas.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPECFICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idcurso");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public ArrayList<Aula> consultarByProfessor(int id) {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula where idprofessor =?;";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setInt(1, id);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos os cursos que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais um
			 * curso, ele vai fazendo os comandos que estão ai dentro. Quando
			 * não existir mais um curso (rs.next() == false), ele sai do Loop,
			 * fecha a conexão e retorna o ArrayList com todos os cursos.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPEC�FICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idturno");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public ArrayList<Aula> consultarByTurma(int id) {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula where idturma =?;";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setInt(1, id);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos os cursos que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais um
			 * curso, ele vai fazendo os comandos que estão ai dentro. Quando
			 * não existir mais um curso (rs.next() == false), ele sai do Loop,
			 * fecha a conexão e retorna o ArrayList com todos os cursos.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPEC�FICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idturno");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public ArrayList<Aula> consultarBySala(int id) {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula where idsala =?;";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setInt(1, id);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos os cursos que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais um
			 * curso, ele vai fazendo os comandos que estão ai dentro. Quando
			 * não existir mais um curso (rs.next() == false), ele sai do Loop,
			 * fecha a conexão e retorna o ArrayList com todos os cursos.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPEC�FICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idturno");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public ArrayList<Aula> consultarByTurno(int id) {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula where idturno =?;";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setInt(1, id);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos os cursos que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais um
			 * curso, ele vai fazendo os comandos que estão ai dentro. Quando
			 * não existir mais um curso (rs.next() == false), ele sai do Loop,
			 * fecha a conexão e retorna o ArrayList com todos os cursos.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPEC�FICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idturno");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public ArrayList<Aula> consultarByCurso(int id) {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula where idcurso =?;";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setInt(1, id);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos os cursos que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais um
			 * curso, ele vai fazendo os comandos que estão ai dentro. Quando
			 * não existir mais um curso (rs.next() == false), ele sai do Loop,
			 * fecha a conexão e retorna o ArrayList com todos os cursos.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPEC�FICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idturno");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public ArrayList<Aula> consultarByDisciplina(int id) {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula where iddisciplina =?;";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setInt(1, id);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos os cursos que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais um
			 * curso, ele vai fazendo os comandos que estão ai dentro. Quando
			 * não existir mais um curso (rs.next() == false), ele sai do Loop,
			 * fecha a conexão e retorna o ArrayList com todos os cursos.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPEC�FICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idturno");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public ArrayList<Aula> consultarByDia(int id) {

		Connection connection = Conexao.getConexao();
		try {

			// Faz a consulta ao banco de dados
			String sql = "SELECT * FROM aula where iddia =?;";
			PreparedStatement prest = connection.prepareStatement(sql);
			prest.setInt(1, id);
			ResultSet rs = prest.executeQuery();

			/*
			 * Cria um arraylist de cursos para armazenar todos os cursos que
			 * voltaram dessa consulta ao banco de dados.
			 */
			ArrayList<Aula> listaAula = new ArrayList<Aula>();

			/*
			 * Enquanto rs.next() for true, ou seja, enquanto existir mais um
			 * curso, ele vai fazendo os comandos que estão ai dentro. Quando
			 * não existir mais um curso (rs.next() == false), ele sai do Loop,
			 * fecha a conexão e retorna o ArrayList com todos os cursos.
			 */
			while (rs.next()) {

				/*
				 * Cria um curso.
				 * 
				 * Pega os dados DESSE RESULTADO ESPEC�FICO do banco de dados.
				 * Para isso: Cria variáveis de acordo com os atributos desse
				 * objeto (nesse caso, só tem ID e NOME), e para cada variável
				 * pega o campo/coluna correspondente no banco de dados
				 * (rs.getInt("idCurso").
				 */
				Aula a = new Aula();
				int idaula = rs.getInt("idaula");
				int idprofessor = rs.getInt("idprofessor");
				int idturma = rs.getInt("idturma");
				int idsala = rs.getInt("idsala");
				int idturno = rs.getInt("idturno");
				int idcurso = rs.getInt("idturno");
				int iddisciplina = rs.getInt("iddiscilpina");
				String nome = rs.getString("nome");
				int iddia = rs.getInt("iddia");

				/*
				 * Depois que criou as variáveis e pegou os dados do banco,
				 * coloca esses dados dentro do objeto criado logo acima.
				 * Porque? Porque você vai adicionar Objetos no ArrayList, e
				 * depois vamos retornar esse arraylist.
				 * 
				 * Se os dados nao fossem colocados em um Objeto, onde iriamos
				 * colocar os dados de cada linha que retornou no banco de
				 * dados? Objetos é o jeito certo de se fazer, só para
				 * contextualizar pq isso é necessário.
				 */

				a.setId(idaula);
				a.setProfessor(professorDAO.consultar(idprofessor));
				a.setTurma(turmaDAO.consultar(idturma));
				a.setSala(salaDAO.consultar(idsala));
				a.setTurno(turnoDAO.consultar(idturno));
				a.setCurso(cursoDAO.consultar(idcurso));
				a.setDisciplina(disciplinaDAO.consultar(iddisciplina));
				a.setNome(nome);
				a.setDia(diaDAO.consultar(iddia));
				listaAula.add(a);
			}

			return listaAula;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/**
	 * Exclui TODASSSS as aulas do banco de dados.
	 */
	public void deletar() {
		String sql = "DELETE FROM aula where idaula != 0;";
		ArrayList<Object> params = new ArrayList<Object>();
		operacaoEscrita(sql, params);
	}

	public void inserir(Aula aula) {
		// String sql =
		// "INSERT INTO aula (idaula, idprofessor, idturma, idsala, idturno, idcurso, iddisciplina, nome, iddia) VALUES (?,?,?,?,?,?,?,?,?);";
		String sql = "INSERT INTO aula (idprofessor, idturma, idsala, idturno, idcurso, iddiscilpina, nome, iddia) VALUES (?,?,?,?,?,?,?,?);";
		ArrayList<Object> params = new ArrayList<Object>();
		// System.out.println("Aula ID:" + aula.getId());
		// System.out.println("Professor: " + aula.getProfessor().getNome());
		// params.add(aula.getId());
		params.add(aula.getProfessor().getId());
		params.add(aula.getTurma().getId());
		params.add(aula.getSala().getId());
		params.add(aula.getTurno().getId());
		params.add(aula.getCurso().getId());
		params.add(aula.getDisciplina().getId());
		params.add(aula.getNome());
		params.add(aula.getDia().getId());
		operacaoEscrita(sql, params);
	}

}
