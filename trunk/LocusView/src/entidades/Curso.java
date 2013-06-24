package entidades;

import java.util.ArrayList;

public class Curso {

    // Variáveis
    private String nome;
    private int id;
    private ArrayList<Disciplina> disciplina;
    private ArrayList<Turma> turmas; 
    // porra velho, esse ArrayList tá quebrando toda a arquitetura que fizemos. 
    // Uma turma tem um curso e não o contrário. 

    // Contrutores
    public Curso() {
        super();
    }
    
    public Curso(String nome, int id, ArrayList<Turma> turmas) {
        super();
        this.nome = nome;
        this.id = id;
        this.turmas = turmas;
    }
    
    // Métodos
    public void adicionarDisciplina(Disciplina disciplina){
        getDisciplina().add(disciplina);
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
    
    // ToString
    @Override
    public String toString() {
        return nome;
    }
    
}
