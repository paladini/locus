/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.Curso;
import entidades.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luiz_malaquias
 */
public class CursoDAO extends AbstractDAO {

    /**
     * Faz consulta no banco de dados e retorna todos os cursos.
     *
     * @return
     */
    public ArrayList<Curso> select() {

        Connection connection = Conexao.getConexao();
        try {

            // Faz a consulta ao banco de dados
            String sql = "SELECT * FROM curso";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            /*
             * Cria um arraylist de cursos para armazenar todos os cursos
             * que voltaram dessa consulta ao banco de dados.
             */
            ArrayList<Curso> listaCurso = new ArrayList<Curso>();
            
            /* Enquanto rs.next() for true, ou seja, enquanto existir mais um curso,
             * ele vai fazendo os comandos que estão ai dentro. Quando não existir
             * mais um curso (rs.next() == false), ele sai do Loop, fecha a conexão
             * e retorna o ArrayList com todos os cursos.
             */
            while (rs.next()) {
                
                /*
                 * Cria um curso.
                 * 
                 * Pega os dados DESSE RESULTADO ESPECÍFICO do banco de dados. Para isso:
                 * Cria variáveis de acordo com os atributos desse objeto
                 * (nesse caso, só tem ID e NOME), e para cada variável pega o campo/coluna
                 * correspondente no banco de dados (rs.getInt("idCurso"). 
                 * 
                 */
                Curso c = new Curso();
                int id = rs.getInt("idCurso");
                String nome = rs.getString("nome");
                
                /* Depois que criou as variáveis e pegou os dados do banco,
                 * coloca esses dados dentro do objeto criado logo acima.
                 * Porque? Porque você vai adicionar Objetos no ArrayList, e depois 
                 * vamos retornar esse arraylist. 
                 * 
                 * Se os dados nao fossem colocados em um Objeto, onde iriamos colocar
                 * os dados de cada linha que retornou no banco de dados? Objetos é o jeito
                 * certo de se fazer, só para contextualizar pq isso é necessário. 
                 */ 
                c.setId(id);
                c.setNome(nome);
                listaCurso.add(c);
            }

            connection.close();
            return listaCurso;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Faz consulta no banco de dados e retorna apenas um curso com esse nome.
     *
     * @return
     */
    public Curso selectCurso(String nomeCurso) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM curso where nome = ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, nomeCurso);
            ResultSet rs = prest.executeQuery();

            // Pega o primeiro registro do retorno da consulta, se existir
            if (rs.next()) {
                
                // Cria uma nova disciplina
                Curso curso = new Curso();

                // Pega os dados desse registro e guarda em variáveis
                int id = rs.getInt("idCurso");
                String nome = rs.getString("nome");

                // Seta os dados na disciplina criada
                curso.setId(id);
                curso.setNome(nome);

                connection.close();
                return curso;
            }else{
                connection.close();
                return null;
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Faz uma consulta no banco de dados pesquisando pelos termos digitados até
     * o momento.
     *
     * @param termos Termos digitados pelo usuário.
     * @return
     */
    public ArrayList<Curso> selectComTermos(String termos) {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM curso where nome like ?;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, termos + "%");
            ResultSet rs = prest.executeQuery();

            ArrayList<Curso> listaCursos = new ArrayList<Curso>();
            while (rs.next()) {
                Curso c = new Curso();
                int id = rs.getInt("idCurso");
                String nome = rs.getString("nome");
                c.setId(id);
                c.setNome(nome);
                listaCursos.add(c);
            }

            connection.close();
            return listaCursos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void insert(Curso curso) {
        String sql = "INSERT INTO curso (nome) VALUES (?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(curso.getNome());
        operacaoEscrita(sql, params);
    }

    public void update(Curso cursoNovo) {
        String sql = "UPDATE curso SET nome = ? WHERE idCurso = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(cursoNovo.getNome());
        params.add(cursoNovo.getId());
        operacaoEscrita(sql, params);
    }

    public void delete(int idCurso) {

        // Chama um outro método para excluir as disciplinas associadas a esse curso.
        this.deleteAssociacoes(idCurso);

        String sql = "DELETE FROM curso WHERE idCurso = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        operacaoEscrita(sql, params);
    }

    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação curso-disciplina
     * 
     *    ======================================================================
     * 
     */
    /**
     * Retorna a lista de todas as disciplinas associadas à esse curso
     *
     * @param curso
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Curso curso) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.idDisciplina, d.nome from Curso_has_Disciplina as cd inner join disciplina as d on "
                    + "d.idDisciplina = cd.Disciplina_idDisciplina where cd.Curso_idCurso = ? order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, curso.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                int id = rs.getInt("idDisciplina");
                String nome = rs.getString("nome");
                disciplina.setId(id);
                disciplina.setNome(nome);
                listaDisciplinas.add(disciplina);
            }

            connection.close();
            return listaDisciplinas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Retorna a lista de todas as disciplinas não associadas à esse curso
     *
     * @param curso
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Curso curso) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "select d.idDisciplina, d.nome from disciplina d where d.idDisciplina not in "
                    + "(select Disciplina_idDisciplina from Curso_has_Disciplina where Curso_idCurso = ?) order by d.nome;";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, curso.getId());
            ResultSet rs = prest.executeQuery();

            ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                int id = rs.getInt("idDisciplina");
                String nome = rs.getString("nome");
                disciplina.setId(id);
                disciplina.setNome(nome);
                listaDisciplinas.add(disciplina);
            }

            connection.close();
            return listaDisciplinas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Adiciona uma disciplina ao curso.
     *
     * @param curso
     */
    public void insertCursoDisciplina(int idCurso, int idDisciplina) {
        String sql = "INSERT INTO `locus`.`Curso_has_Disciplina` (`Curso_idCurso`, `Disciplina_idDisciplina`) VALUES (?, ?);";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        params.add(idDisciplina);
        operacaoEscrita(sql, params);
    }

    /**
     * Deleta uma disciplina associada ao curso.
     *
     * @param idCurso
     * @param idDisciplina
     */
    public void deleteCursoDisciplina(int idCurso, int idDisciplina) {
        String sql = "delete from Curso_has_Disciplina where Curso_idCurso = ? and Disciplina_idDisciplina = ?;";

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        params.add(idDisciplina);
        operacaoEscrita(sql, params);
    }

    /*
     * ============================================
     * 
     *                Métodos Privados
     * 
     * ============================================
     */
    /**
     * Deleta todas as associações entre curso e disciplina. (Necessário para
     * poder excluir o curso sem deixar dependências).
     *
     * @param curso
     */
    private void deleteAssociacoes(int idCurso) {
        String sql = "delete from Curso_has_Disciplina where Curso_idCurso = ?;";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(idCurso);
        operacaoEscrita(sql, params);
    }
}
