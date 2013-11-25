package control;

import java.util.ArrayList;

import model.AulaDao;
import model.ProfessorDAO;

import entidades.Aula;
import entidades.Curso;
import entidades.Dia;
import entidades.Disciplina;
import entidades.Instituicao;
import entidades.Professor;
import entidades.Sala;
import entidades.Turma;
import entidades.Turno;

public class ControleEnsalamento {
	
	private ArrayList<Aula> aulas;
	ArrayList<Aula> aulasFinal;
	Instituicao instituicao = Instituicao.getInstance();
	AulaDao aulaDao = new AulaDao();
	

	public ControleEnsalamento(){
		init();
	}
	

	private void init() {
		
		instituicao.setListAulas(new ArrayList<Aula>());
		aulas = instituicao.getListAulas();
		
	}


	public void Ensalar() {
		init();
		// turno
		for (int i = 0; i < 3; i++) {

			ArrayList<Curso> curso = instituicao.getListaCurso();
			int tamanhocurso = curso.size();

			// curso
			for (int j = 0; j < tamanhocurso; j++) {

				// verificacao turmas do turno
				ArrayList<Turma> turma = new ArrayList<Turma>();
				for (int k = 0; k < curso.get(j).getTurmas().size(); k++) {
					if (i == curso.get(j).getTurmas().get(k).getTurno()) {
						turma.add(curso.get(j).getTurmas().get(k));
					}
				}
				// ArrayList<Turma> turma = curso.get(j).getTurmas();
				int tamanhoturma = turma.size();
				// turma
				for (int k = 0; k < tamanhoturma; k++) {

					turma.get(k).setAulas(new ArrayList<Aula>());

					ArrayList<Disciplina> DisciplinasVerific = new ArrayList<Disciplina>();
					// aulas
					for (int k2 = 0; k2 < 5; k2++) {
						Aula a = new Aula("aula" + k2);
						// turma.get(k).getGrade().getAulas().add(a);
						// Aula aula =
						// turma.get(k).getGrade().getAulas().get(k2);
						Aula aula = a; // foi adicionada
						ArrayList<Sala> salas = instituicao.getListaSala();
						aula.setSala(salas.get(k));
						if (i == 0) {
							Turno turno = new Turno("manha", i);
							aula.setTurno(turno);
							aula.getSala().setUsar1(true);
						}
						if (i == 1) {
							Turno turno = new Turno("tarde", i);
							aula.setTurno(turno);
							aula.getSala().setUsar2(true);
						}
						if (i == 2) {
							Turno turno = new Turno("noite", i);
							aula.setTurno(turno);
							aula.getSala().setUsar3(true);
						}
						aula.setCurso(curso.get(j));
						aula.setTurma(turma.get(k));
						// ArrayList<Disciplina> disciplinas = instituicao
						// .getListaDisciplinas();
						//  Verificar semestre
						ArrayList<Disciplina> disciplinas = aula.getCurso()
								.getDisciplinas();
						if (k2 == disciplinas.size()) {
							// disciplina == null break
							break;
						}

						// aula.setDisciplina(disciplinas.get(k2));
						ArrayList<Professor> professors = instituicao
								.getListaProfessores();
						// ---------------------------------------------
						// //  alocar o professor somente se ele estiver
						// for (int l = 0; l < professors.size(); l++) {
						// for (int l2 = 0; l2 < professors.get(l)
						// .getDisciplina().size(); l2++) {
						//
						// if (disciplinas.get(k2) == professors.get(l)
						// .getDisciplina().get(l2)) {
						// aula.setProfessor(professors.get(l));
						// break;
						// }
						// }
						// }
						// --------------------------------------------------------------------
						//  array tem que estar fora do for de aulas
						if (k2 == 0) {

							for (int l = 0; l < disciplinas.size(); l++) {
								DisciplinasVerific.add(disciplinas.get(l));
							}
						}

						boolean v = false;
						int x = 0;
						int aumenta = 0;

						int limite = professors.size(); //  CALCULAR O
														// LIMITE DINAMICAMENTE
						ArrayList<Integer> conjuntoNumerosRandom = RandomControlado
								.retornaRandom(limite);

						x = conjuntoNumerosRandom.get(aumenta);
						sair: while (v == false) {
							if (professors.get(x).retornadisponibilidade(k2, i) == false) {

								for (int l = 0; l < DisciplinasVerific.size(); l++) {
									for (int l2 = 0; l2 < professors.get(x)
											.getDisciplina().size(); l2++) {
										Disciplina d = professors.get(x)
												.getDisciplina().get(l2);

										if (DisciplinasVerific.get(l).getId() == d
												.getId()) {
											aula.setDisciplina(d);
											aula.setProfessor(professors.get(x));
											professors.get(x).alocar(k2, i);
											v = true;
											DisciplinasVerific.remove(l);
											break sair;
											// System.out.println("k2: " + k2
											// +" i: " +i);

										}
									}

								}

							}

							aumenta = aumenta + 1;
							if (x >= conjuntoNumerosRandom.size())
								break;
							if (aumenta >= conjuntoNumerosRandom.size())
								break;
							x = conjuntoNumerosRandom.get(aumenta);

							if (x >= professors.size())
								break;
						}
						; // fim while

						// --------------------------------------------

						for (int l = 0; l < 5; l++) {
							Dia dia = new Dia("", l);
							instituicao.getListaDias().add(dia);
						}

						// imprimir
						for (int l = 0; l < 5; l++) {

							if (l < 5) {
								turma.get(k).getAulas().add(aula);
								if (k2 == 0)
									aula.setDia(new Dia("seg", k2));
								if (k2 == 1)
									aula.setDia(new Dia("ter", k2));
								if (k2 == 2)
									aula.setDia(new Dia("qua", k2));
								if (k2 == 3)
									aula.setDia(new Dia("qui", k2));
								if (k2 == 4)
									aula.setDia(new Dia("sex", k2));

								// System.out.println(aula.toString());
								aulas.add(aula);

							}
							break;

						}
						//  sysout método ensalar
						// System.out.println(aula.toString());
						// System.out.println();

					}// aulas

				}// turma

			} // curso

		} // for turno
		
		AddDisciplinasNull();
		
	} // fim ensalar

	
	public void AddDisciplinasNull() {
		ArrayList<Disciplina> disciplinasCurso = new ArrayList<Disciplina>();
		for (int q = 0; q < aulas.size(); q++) {

			// Copiando todas as disciplinas para disciplinasCurso
			disciplinasCurso.addAll(aulas.get(q).getCurso().getDisciplinas());
			
			//pegar as disciplinas já ensaladas da turma da aula do for Q, inclusive as disciplinas nulas
			ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
			int turma = aulas.get(q).getTurma().getId();
			for (int i = 0; i < aulas.size(); i++) {
				if (turma == aulas.get(i).getTurma().getId() ) {
					disciplinas.add( aulas.get(i).getDisciplina());
				}
				
			}
//			ArrayList<Disciplina> disciplinas2 = new ArrayList<Disciplina>();
//			int x = disciplinasCurso.size();
//			for (int i = 0; i < x; i++) {
//				for (int j = 0; j < disciplinas.size(); j++) {
//					if (disciplinasCurso.get(i)==disciplinas.get(j)) {
////						disciplinas2.add(disciplinasCurso.get(i));
//						disciplinasCurso.remove(i);
//						
//					}
//				}
//			}
//			
			
			//pegar as disciplinas que não foram ensaladas para a turma da aula do for Q
			ArrayList<Disciplina> disciplinasNaoEnsaladas = new ArrayList<Disciplina>();
			for (Disciplina disciplinaCurso : disciplinasCurso) {
				if (disciplinas.contains(disciplinaCurso))
					;
				else
					disciplinasNaoEnsaladas.add(disciplinaCurso);
			}
			
			
			
//			for (int i = 0; i < disciplinasNaoEnsaladas.size(); i++) {
//				if (aulas.get(q).getDisciplina() == null) {
//					aulas.get(q).setDisciplina(disciplinasNaoEnsaladas.get(i));
//				} else {
//					break;
//				}
//
//			}
			
			int indiceDisciplinasNaoEnsaladas = 0;
			for (int i = 0; i < aulas.size(); i++) {
				if (turma == aulas.get(i).getTurma().getId() ) {
					
					if (aulas.get(i).getDisciplina() == null) {
						aulas.get(i).setDisciplina(disciplinasNaoEnsaladas.get(indiceDisciplinasNaoEnsaladas));
						indiceDisciplinasNaoEnsaladas++;
					} else {
						;
					}
				}
				
			}

			
		}
		
	}// fim AddDisciplinaNull
	
	

	/**
	 * escolhe o melhor ensalamento entre os 1000
	 * 
	 * @param listAulas
	 */
	public void EscolherEntreMil() {
		
		
		int qtdnullFinal = 999999999;

		for (int i = 0; i < 1000; i++) {
			System.out.println("i"+i);
//			TODO ver qual a melhor maneira de fazer isto 			
			ControleEnsalamento pr = new ControleEnsalamento(); 
			pr.Ensalar();
			aulas = pr.getAulas();
			pr = null;
			
//			Ensalar();
//			aulas = getAulas();
			
			int qtdnull = 0;
			for (int j = 0; j < aulas.size(); j++) {
				if (aulas.get(j).getProfessor() == null)
					qtdnull = qtdnull + 1;
			}// for listAulas
			if (qtdnullFinal > qtdnull) {
//				qtdnullFinal = qtdnull;
//				aulasFinal = aulas;
				qtdnullFinal = qtdnull;
				armazenarEnsalamentoFinal();
				System.out.println(qtdnull);
				System.out.println(qtdnullFinal);
				System.out.println();
			}// if qtd

		}// for 1000
		
		aulas = aulasFinal;
		
//		int cont = 0;
//		for (int j = 0; j < aulasFinal.size(); j++) {
//			System.out.println(aulasFinal.get(j).toString());
//
//			cont = cont + 1;
//			if (cont == 5) {
//				cont = 0;
//				System.out.println();
//				System.out.println();
//			}
//
//		}// sysout aulasFInal
		
	}// fim EscolherEntreMil
	
	
	private void armazenarEnsalamentoFinal(){
		aulasFinal = new ArrayList<Aula>();
		for (Aula a : aulas) {
			aulasFinal.add(a);
		}
	}
	
//			TODO ver com o daniel segunda(18/11/2013)
//	public ArrayList<Aula> EnsalarCerto() {
//		ArrayList<Aula> aulasFinal = new ArrayList<Aula>();
//		int qtdnullFinal = 999999999;
//
//		for (int i = 0; i < 1000; i++) {
//			//			ProcessoRandom.this.Ensalar();
//			ProcessoRandom processarEnsalamento = new ProcessoRandom();
//			ArrayList<Aula> listAulas;
//			processarEnsalamento.Ensalar();
//			listAulas = processarEnsalamento.getAulas();
//			
//			int qtdnull = 0;
//			for (int j = 0; j < listAulas.size(); j++) {
//				if (listAulas.get(j).getProfessor() == null)
//					qtdnull = qtdnull + 1;
//			}// for listAulas
//			if (qtdnullFinal > qtdnull) {
//				qtdnullFinal = qtdnull;
//				aulasFinal = listAulas;
//			}// if qtd
//
//			processarEnsalamento.AddDisciplinasNull();
//			aulasFinal = processarEnsalamento.getAulas();
//		}// for 1000
//		return aulasFinal;
//
//	}// fim EscolherEntreMil

	public void Persistir(){
		for (int i = 0; i < aulas.size(); i++) {
			aulaDao.inserir(aulas.get(i));
		}
	}
	
	/*
	 * Pega todos os professores do BD
	 */
	public void ConsultarByProfessor() {
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.consultar();
	}
	/*
	 * Pega um professor especifico do BD
	 */
	public void ConsultarByProfessor(int id) {
		aulaDao.consultarByProfessor(id);
	}
	
	
	
	public ArrayList<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(ArrayList<Aula> aulas) {
		this.aulas = aulas;
	}
	
}

	
	
	
	

