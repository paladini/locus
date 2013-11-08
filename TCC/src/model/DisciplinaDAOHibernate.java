//package model;
//
//import java.util.ArrayList;
//
//import dao.LocusDAO;
//import entidades.Disciplina;
//
//public class DisciplinaDAOHibernate extends LocusDAO{
//	
//	
//	
//	
//	
//	/*
//	 * 
//	 * 
//	 *  ESTÁ COM PROBLEMA POIS NÃO CONSEGUE IMPORTAR O "dao.LocusDAO" do projeto Arq. Ele já foi adicionado ao projeto,
//	 *  mas ainda não está funcionando. Ver se consegue arrumar. 
//	 * 
//	 * 
//	 */
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/*
//	 * ========================================
//	 *              CONSULTAS
//	 * ========================================
//	 */
//	public ArrayList<Disciplina> select(){
//		ArrayList<Disciplina> listaDisciplinas = (ArrayList<Disciplina>) findAll(Disciplina.class);
//		
//		return listaDisciplinas;
//	}
//	
//	public Disciplina select(int id){
//		Disciplina disciplina = (Disciplina) findByPrimaryKey(Disciplina.class, id);
//		return disciplina;
//	}
//	
//	/*
//	 * =======================================
//	 *       MODIFICAÇÃO DE DADOS
//	 * =======================================
//	 */
//	public void insert(Disciplina disciplina){
//		create(disciplina);
//	}
//	
//	public void update(Disciplina disciplina){
//		update(disciplina);
//	}
//	
//	public void delete(Disciplina disciplina){
//		delete(disciplina);
//	}
//	
//}
