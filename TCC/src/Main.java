 

import java.util.ArrayList;

import model.CursoDAO;
import model.DisciplinaDAO;
import model.ProfessorDAO;
import model.SalaDAO;
import model.TurmaDAO;
import model.TurnoDAO;

import control.ControleEnsalamento;
import entidades.Aula;
import entidades.Curso;
import entidades.Disciplina;
import entidades.Instituicao;
import entidades.Professor;
import entidades.Sala;
import entidades.Turma;
import entidades.Turno;

public class Main {

	public static void main(String[] args) {
		
		Instituicao i = Instituicao.getInstance();
		i.setListaCurso(new ArrayList<Curso>());
		i.setListaProfessores(new ArrayList<Professor>());
		i.setListaDisciplinas(new ArrayList<Disciplina>());
		i.setListaSala(new ArrayList<Sala>());

		// cursos
		Curso curso1 = new Curso("inf1", 1, null, null);
		// Curso curso2 = new Curso("Inf2",2,null,null);
		// Curso curso3 = new Curso("Inf3",3,null,null);
		// Curso curso4 = new Curso("Inf4",4,null,null);
		// Curso curso5 = new Curso("Inf5",5,null,null);
		
		//turno
		Turno turno1 = new Turno("Matutino",0);
		Turno turno2 = new Turno("Vespertino",1);
		Turno turno3 = new Turno("Noturno",2);
		

		// turmas
		Turma turma1 = new Turma(1, "t1", curso1, 1);
		Turma turma2 = new Turma(2, "t2", curso1, 1);
		Turma turma3 = new Turma(3, "t3", curso1, 1);
		Turma turma4 = new Turma(4, "t4", curso1, 1);
		Turma turma5 = new Turma(5, "t5", curso1, 1);
		
		

		// Instituicao.addListaTurmas(new Turma("t1", curso1, 1));
		// Instituicao.addListaTurmas(new Turma("t2", curso1, 1));
		// Instituicao.addListaTurmas(new Turma("t3", curso1, 1));
		// Instituicao.addListaTurmas(new Turma("t4", curso1, 1));
		// Instituicao.addListaTurmas(new Turma("t5", curso1, 1));

		// Instituicao.addListaTurmas(Turma);

		// salas
		Sala sala1 = new Sala("sala1", 1);
		Sala sala2 = new Sala("sala2", 2);
		Sala sala3 = new Sala("sala3", 3);
		Sala sala4 = new Sala("sala4", 4);
		Sala sala5 = new Sala("sala5", 5);
		Sala sala6 = new Sala("sala6", 6);
		Sala sala7 = new Sala("sala7", 7);
		Sala sala8 = new Sala("sala8", 8);
		Sala sala9 = new Sala("sala9", 9);
		Sala sala10 = new Sala("sala10", 10);

		// disciplina
		Disciplina disciplina1 = new Disciplina("POO2", 1,
				new ArrayList<Professor>());
		Disciplina disciplina2 = new Disciplina("BD", 2,
				new ArrayList<Professor>());
		Disciplina disciplina3 = new Disciplina("Infra-net", 3,
				new ArrayList<Professor>());
		Disciplina disciplina4 = new Disciplina("projet-sof2", 4,
				new ArrayList<Professor>());
		Disciplina disciplina5 = new Disciplina("d5", 5,
				new ArrayList<Professor>());

		// professor
		Professor professor1 = new Professor("Daniel",
				new ArrayList<Disciplina>(), 1);
		Professor professor2 = new Professor("Arthur",
				new ArrayList<Disciplina>(), 2);
		Professor professor3 = new Professor("Estevao",
				new ArrayList<Disciplina>(), 3);
		Professor professor4 = new Professor("andrenizia",
				new ArrayList<Disciplina>(), 4);
		Professor professor5 = new Professor("prof5",
				new ArrayList<Disciplina>(), 5);

		// add professor a dicipplina
		// Instituicao.getListaDisciplinas().get(1).addProfessores(professor1);

		disciplina1.addProfessores(professor1);
		disciplina2.addProfessores(professor2);
		disciplina3.addProfessores(professor3);
		disciplina4.addProfessores(professor4);
		disciplina5.addProfessores(professor5);

		// add diciplina a professores
		professor1.addDisciplina(disciplina1);
		professor2.addDisciplina(disciplina2);
		professor3.addDisciplina(disciplina3);
		professor4.addDisciplina(disciplina4);
		professor5.addDisciplina(disciplina5);

		// add diciplina a curso
		curso1.addDicilpina(disciplina1);
		curso1.addDicilpina(disciplina2);
		curso1.addDicilpina(disciplina3);
		curso1.addDicilpina(disciplina4);
		curso1.addDicilpina(disciplina5);

		// add turma ao curso
		curso1.addTurma(turma1);
		curso1.addTurma(turma2);
		curso1.addTurma(turma3);
		curso1.addTurma(turma4);
		curso1.addTurma(turma5);

		// add na Instituicao
		// curso
//		Instituicao.addListaCurso(curso1);
//		Instituicao.addListaTurmas(turma1);
//		Instituicao.addListaTurmas(turma2);
//		Instituicao.addListaTurmas(turma3);
//		Instituicao.addListaTurmas(turma4);
//		Instituicao.addListaTurmas(turma5);
		// sala
		
		i.init();
		
//		i.addListaSala(sala1);
//		i.addListaSala(sala2);
//		i.addListaSala(sala3);
//		i.addListaSala(sala4);
//		i.addListaSala(sala5);
//		i.addListaSala(sala6);
//		i.addListaSala(sala7);
//		i.addListaSala(sala8);
//		i.addListaSala(sala9);
//		i.addListaSala(sala10);
//		// disciplina
//		i.addListaDisciplinas(disciplina1);
//		i.addListaDisciplinas(disciplina2);
//		i.addListaDisciplinas(disciplina3);
//		i.addListaDisciplinas(disciplina4);
//		i.addListaDisciplinas(disciplina5);
//		// professor
//		i.addListaProfessores(professor1);
//		i.addListaProfessores(professor2);
//		i.addListaProfessores(professor3);
//		i.addListaProfessores(professor4);
//		i.addListaProfessores(professor5);

		CursoDAO cursoDAO = new CursoDAO();
		cursoDAO.inserir(curso1);
		
//		TurnoDAO turnoDAO = new TurnoDAO();
//		turnoDAO.inserir(turno1);
//		turnoDAO.inserir(turno2);
//		turnoDAO.inserir(turno3);
		
			
		TurmaDAO turmaDao = new TurmaDAO();
		turmaDao.inserir(turma1);
		turmaDao.inserir(turma2);
		turmaDao.inserir(turma3);
		turmaDao.inserir(turma4);
		turmaDao.inserir(turma5);
		
		SalaDAO salaDAO = new SalaDAO();
		salaDAO.inserir(sala1);
		salaDAO.inserir(sala2);
		salaDAO.inserir(sala3);
		salaDAO.inserir(sala4);
		salaDAO.inserir(sala5);
		salaDAO.inserir(sala6);
		salaDAO.inserir(sala7);
		salaDAO.inserir(sala8);
		salaDAO.inserir(sala9);
		salaDAO.inserir(sala10);
		
		DisciplinaDAO disciplinaDAO = new  DisciplinaDAO();
		disciplinaDAO.inserir(disciplina1);
		disciplinaDAO.inserir(disciplina2);
		disciplinaDAO.inserir(disciplina3);
		disciplinaDAO.inserir(disciplina4);
		disciplinaDAO.inserir(disciplina5);
		
		ProfessorDAO professorDAO = new  ProfessorDAO();
		professorDAO.inserir(professor1);
		professorDAO.inserir(professor2);
		professorDAO.inserir(professor3);
		professorDAO.inserir(professor4);
		professorDAO.inserir(professor5);
		
		
		
		
		
		
//---------------------------------------------------------------------
		
		ControleEnsalamento processarEnsalamento = ControleEnsalamento.getInstance();
		ArrayList<Aula> aulas;
//		processarEnsalamento.Ensalar();
		processarEnsalamento.Ensalar();
		aulas = processarEnsalamento.getAulas(); 
		printGradeHorario(aulas);

		
		System.out.println("====================================================================");
		
//		processarEnsalamento.EscolherEntreMil();
//		aulas = processarEnsalamento.getAulas(); 
//		printGradeHorario(aulas);
		
		
// -------------------------------------------------------------------------------------	
//		printGradeHorario(processarEnsalamento.EnsalarCerto());
		
		
//		for (int i = 0; i < Instituicao.getListaTurmas().size(); i++) {
//			System.out.println(Instituicao.getListaTurmas().get(i).getNome());
//		}
		
	}
	
	
	private static void printGradeHorario(ArrayList<Aula> aulas){
		int cont = 0;
		
		for (Aula aula : aulas) {
			System.out.println(aula.texto());
			cont++;
			if (cont == 5) {
				System.out.println();
				System.out.println();
				cont = 0;
			}
		}
		
	}

}
