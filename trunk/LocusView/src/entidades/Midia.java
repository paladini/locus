/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Daniel
 */
public class Midia {
    
    private int id;
    private String titulo;
    private boolean lancamento;
    private int genero;

    public Midia() {
    }

    public Midia(int id, String titulo, boolean lancamento, int genero) {
        this.id = id;
        this.titulo = titulo;
        this.lancamento = lancamento;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Midia{" + "id=" + id + ", titulo=" + titulo + ", lancamento=" + lancamento + ", genero=" + genero + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isLancamento() {
        return lancamento;
    }

    public void setLancamento(boolean lancamento) {
        this.lancamento = lancamento;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
    
    
    
    
         
    
}
