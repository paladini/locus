/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import model.LoginDAO;
import entidades.Login;

/**
 *
 * @author silvio
 */
public class ControleLogin {
    
    ResultSet result; 
    LoginDAO modelo = new LoginDAO();
    
    
    /**
     * Método para realizar as mudanças na primeira entrada no sistema.
     * @param login 
     */
    public int primeiraEntrada(Login login){
        // Chama método para mudar a senha. Se for realizado com sucesso, continua e atualiza no banco o último acesso.
        if (modelo.mudarSenha(login.getSenha())){
            modelo.ultimoAcesso();
            return 0;
        }else{
            return 1;
        }
    }
    
    /**
     * Método que autentica usuário e permite login no sistema.
     * @param login
     * @param senha
     * @return 
     */
    public int login(String login, String senha){
        
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        
        // Criando objeto loginUsuario, de acordo com dados concedidos pelo usuario (na view deu problema). 
        Login loginUsuario = new Login();
        loginUsuario.setLogin(login);
        loginUsuario.setSenha(senha);
        
        // Criando objeto loginBanco, de acordo com os dados do banco.
        Login loginBanco = modelo.logar(loginUsuario.getLogin());
        
        /**
         * Se retorno = 0: acesso não permitido
         * Se retorno = 1: primeiro acesso, redirecionar para PrimeiraEntrada1;
         * Se retorno = 2: acesso normal, redirecionar para MenuPrincipal;
         */
        int retorno = 0;
        
        if (loginUsuario.getSenha().contentEquals(loginBanco.getSenha())){
             if (loginBanco.getUltimoacesso() == null){
                 retorno = 1;
             }else{
                 retorno = 2;
             }
        }
        return retorno;
    }
    
//    public void dadosBasicos(String nomeInstituicao, String novaSenha, String turnos ){
//        
//        // Trocando nome da instituição
//        Conexao.mudarInstituicao(nomeInstituicao);
//        
//        // Trocando senha
//        //Conexao.mudarSenha(novaSenha);
//        
//        /**
//         * Criar os turnos no banco de acordo com os checkboxes marcados.
//        */
//        
//        if (turnos.contains("?")){
//            // Esse [^/] é o que chamamos de expressão regular, se quiserem pesquisem no Google sobre
//            String vetorTurnos[] = turnos.split("[^/]"); 
//            for (int i = 0; i < vetorTurnos.length; i++){
//                Conexao.adicionarTurno(vetorTurnos[i]);
//            }
//        }else{
//             Conexao.adicionarTurno(turnos);
//        }
//    }
    
}
