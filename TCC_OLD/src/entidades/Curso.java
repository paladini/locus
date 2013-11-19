package entidades;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


/**
 * Classe que define o objeto Curso.
 * @author fernando_paladini
 */
@Entity
public class Curso {

    // Variáveis
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_DEPARTAMENTO")
    @Column(name="idCurso")
    private int id;
    
    private String nome;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="Curso_has_Disciplina",
    		joinColumns = @JoinColumn(name="Curso_idCurso"),
    		inverseJoinColumns = @JoinColumn(name="Disciplina_idDisciplina")
    		)
    private ArrayList<Disciplina> disciplina = new ArrayList<Disciplina>();
    
    // TODO: Arrumar isso
    @OneToMany(mappedBy="centro", cascade=CascadeType.ALL)
    private ArrayList<Turma> turmas;

    // Contrutores
    public Curso() {
        super();
    }
    
    public Curso(int id, String nome){
    	super();
        this.nome = nome;
        this.id = id;
    }
    
    public Curso(int id, String nome, ArrayList<Turma> turmas) {
        super();
        this.nome = nome;
        this.id = id;
        this.turmas = turmas;
    }
    
    // Métodos
    public void adicionarDisciplina(Disciplina disciplina){
        getDisciplina().add(disciplina);
    }
    
    public void removerDisciplina(Disciplina disciplina){
        getDisciplina().remove(disciplina);
    }
    
    void adicionarTurma(Turma turma) {
        turmas.add(turma);
    }

    public ArrayList<Disciplina> getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(ArrayList<Disciplina> disciplina) {
        this.disciplina = disciplina;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(ArrayList<Turma> turmas) {
        this.turmas = turmas;
    }
//    
    // ToString
//    @Override
//    public String toString() {
//        return nome;
//    }
    
}
