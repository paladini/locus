/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.Disciplina;
import entidades.Professor;
import java.util.ArrayList;
import model.ProfessorDAO;

/**
 *
 * @author fernando_paladini
 */
public class ControleProfessor {
    
    ProfessorDAO modelo = new ProfessorDAO();
    
    /**
     * Método para atualizar Professor no banco de dados.
     *
     * @param curso
     */
    public void atualizar(Professor professor) {
        if (professor.getNome() == null || professor.getNome() == " " || professor.getNome() == ""){
            
        }else{
            modelo.update(professor);
        }
    }

    /**
     * Método para remover Professor no banco de dados
     *
     * @param disciplina
     */
    public void remover(Professor professor) {
        modelo.delete(professor);
    }

    /**
     * Método para adicionar um professor ao banco de dados.
     *
     * @param disciplinaAdicionar
     */
    public void adicionar(Professor professor) {
        modelo.insert(professor);
    }

    /**
     * Método para consultar os professores do banco de dados
     *
     * @return
     */
    public ArrayList<Professor> consulta() {
        return modelo.select();
    }

    /**
     * Método para consultar os cursos com nomes que começam com os termos
     * digitados pelo usuário.
     *
     * @return
     */
    public ArrayList<Professor> consultaComTermos(String termo) {
        return modelo.selectComTermos(termo);
    }

    /**
     * Método para retornar somente um curso (para tela "Editar").
     *
     * @param termo
     * @return
     */
    public Professor consultaProfessor(String termo) {
        return modelo.selectProfessor(termo);
    }

    
    /*
     * 
     *    ======================================================================
     * 
     *                   Métodos de interação professor-disciplina
     * 
     *    ======================================================================
     * 
     */
    
    
    /**
     * Adiciona uma disciplina associada ao professor.
     *
     * @param idCurso
     * @param idDisciplina
     */
    public void adicionarDisciplina(int idProfessor, int idDisciplina) {
        modelo.insertProfessorDisciplina(idProfessor, idDisciplina);
    }

    /**
     * Exclui disciplina associada ao professor.
     *
     * @param idProfessor
     * @param idDisciplina
     */
    public void excluirDisciplina(int idProfessor, int idDisciplina) {
        modelo.deleteProfessorDisciplina(idProfessor, idDisciplina);
    }

    /**
     * Retorna a lista de disciplinas associadas à esse professor.
     *
     * @param professor
     * @return
     */
    public ArrayList<Disciplina> listaDisciplinasAssociadas(Professor professor) {
        return modelo.listaDisciplinasAssociadas(professor);
    }

    /**
     * Retorna a lista de disciplinas não associadas à esse Professor.
     *
     * @param professor
     * @return
     */
    public ArrayList<Disciplina> listaDisciplinasNaoAssociadas(Professor professor) {
        return modelo.listaDisciplinasNaoAssociadas(professor);
    }
    
    
}
