
package model;

import entidades.Cliente;
import entidades.Midia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MidiaDAO {

    
    public ArrayList<Cliente> select() {

        Connection connection = Conexao.getConexao();
        try {

            String sql = "SELECT * FROM cliente";
            PreparedStatement prest = connection.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();

            ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
            while (rs.next()) {
                Cliente c = new Cliente();
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                c.setId(id);
                c.setNome(nome);
                listaClientes.add(c);
            }

            connection.close();
            return listaClientes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public void insert(Midia midia) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "INSERT INTO midia (titulo, lancamento, idgenero) VALUES (?, ?, ?);";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, midia.getTitulo());
            prest.setBoolean(2, midia.isLancamento());
            prest.setInt(3, midia.getGenero());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Cliente clienteNovo, Cliente clienteAntigo) {
        Connection connection = Conexao.getConexao();
        try {

            String sql = "UPDATE cliente SET nome = ? WHERE nome like ?";
            //String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setString(1, clienteNovo.getNome());
            prest.setString(2, clienteAntigo.getNome());
            //prest.setInt(2,clienteAntigo.getId());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Cliente clienteAntigo) {
        Connection connection = Conexao.getConexao();
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            //String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
            PreparedStatement prest = connection.prepareStatement(sql);
            prest.setInt(1, clienteAntigo.getId());

            prest.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
