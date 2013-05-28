/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
//import org.jasypt.util.text.BasicPasswordEncryptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexao;

/**
 *
 * @author silvio
 */
public class GerenciarConexao {
    
    ResultSet result; 
    
    public int login(String nome, String senha){
        
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        result = Conexao.logar(nome);
        
        /**
         * Se retorno = 0: acesso não permitido
         * Se retorno = 1: primeiro acesso, redirecionar para PrimeiraEntrada1;
         * Se retorno = 2: acesso normal, redirecionar para MenuPrincipal;
         */
        int retorno = 0;
        
        try {
            result.next();
            if (result != null){
                if (result.getString("senha").contentEquals(senha)){
                    if (result.getDate("ultimo_acesso") == null){
                        retorno = 1;
                    }else{
                        retorno = 2;
                    }       
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
    public void dadosBasicos(String nomeInstituicao, String novaSenha, String turnos ){
        
        // Trocando nome da instituição
        Conexao.mudarInstituicao(nomeInstituicao);
        
        // Trocando senha
        Conexao.mudarSenha(novaSenha);
        
        /**
         * Criar os turnos no banco de acordo com os checkboxes marcados.
        */
        
        if (turnos.contains("?")){
            // Esse [^/] é o que chamamos de expressão regular, se quiserem pesquisem no Google sobre
            String vetorTurnos[] = turnos.split("[^/]"); 
            for (int i = 0; i < vetorTurnos.length; i++){
                Conexao.adicionarTurno(vetorTurnos[i]);
            }
        }else{
             Conexao.adicionarTurno(turnos);
        }
        
        
        
    }
    
}
