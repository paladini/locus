/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControleDisciplina;
import control.ControleSala;
import entidades.Disciplina;
import entidades.Sala;

/**
 *
 * @author silvio
 */
public class Editar extends javax.swing.JFrame {

    
    Disciplina disciplina;
    Disciplina disciplinaNova;
    Sala sala;
    Sala salaNova;
    int argumento = 0;
    
    /**
     * Cria um novo form "Editar".
     * 
     * Tipo = 0 - Disciplina
     * Tipo = 1 - Sala
     * 
     * @param tipo Tipo de dado (0 = Disciplina, 1 = Sala)
     * @param nome Nome a ser pesquisado para editar.
     */
    public Editar(int tipo, String nome) {
        
        // Inicializa todos os componentes
        initComponents();
        
        // Não fechar todas as janelas ao fechar esta janela.
        setDefaultCloseOperation(Editar.DISPOSE_ON_CLOSE);
        
        // O valor da variável global "argumento" é igual ao valor do dado enviado pelo usuário.
        argumento = tipo;
        
        if (argumento == 0){
            // Instancia um novo ControleDisciplina
            ControleDisciplina cd = new ControleDisciplina();

            // Armazena a disciplina a ser editada
            disciplina = cd.consultaDisciplina(nome);

            // Seta os valores para exibir ao usuário
            jTextField2.setText(disciplina.getNome());
        }else{
            if (argumento == 1){
                // Instancia um novo ControleDisciplina
                ControleSala cs = new ControleSala();

                // Armazena a disciplina a ser editada
                sala = cs.consultaDisciplina(nome);

                // Seta os valores para exibir ao usuário
                jTextField2.setText(sala.getNome());
            }
        }
        
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome de usuário:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Locus - Editar Disciplina");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nome antigo:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nome novo:");

        jTextField2.setEditable(false);
        jTextField2.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Disciplina");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salvar.fw.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excluir3.fw.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(246, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        if (argumento == 0){
            // Instanciando gerenciar disciplina
            ControleDisciplina cd = new ControleDisciplina();

            // Previne que um campo em branco seja salvo ou que o nome atual seja inserido como novo nome.
            // Verifica se o campo jTextField1 tem valor maior do que zero (não está em branco) e se o nome não igual ao anterior.
            //if (jTextField1.getText().length() != 0 && !(jTextField1.getText().equals(disciplina.getNome()))){
            String novaDisciplina = jTextField1.getText();
            disciplinaNova = new Disciplina();
            disciplinaNova.setNome(novaDisciplina);

                // Atualizando a disciplina
            cd.atualizar(disciplinaNova, disciplina);
            //}  

            // Fechando a janela
            dispose();
        }else{
            if (argumento == 1){
                // Instanciando gerenciar disciplina
                ControleSala cs = new ControleSala();

                // Previne que um campo em branco seja salvo ou que o nome atual seja inserido como novo nome.
                // Verifica se o campo jTextField1 tem valor maior do que zero (não está em branco) e se o nome não igual ao anterior.
                //if (jTextField1.getText().length() != 0 && !(jTextField1.getText().equals(disciplina.getNome()))){
                String novaSala = jTextField1.getText();
                salaNova = new Sala();
                salaNova.setNome(novaSala);

                    // Atualizando a disciplina
                cs.atualizar(salaNova, sala);
                //}  

                // Fechando a janela
                dispose();
            }
        }
        
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        
        if (argumento == 0){
           // Instanciando gerenciar disciplina
            ControleDisciplina cd = new ControleDisciplina();

            // Atualizando a disciplina
            cd.remover(disciplina);

            // Fechando a janela
            dispose();
        } else {
            if (argumento == 1){
                // Instanciando gerenciar disciplina
                ControleSala cs = new ControleSala();

                // Atualizando a disciplina
                cs.remover(sala);

                // Fechando a janela
                dispose();
            }
        }
        
    }//GEN-LAST:event_jLabel6MouseClicked

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplinaNova() {
        return disciplinaNova;
    }

    public void setDisciplinaNova(Disciplina disciplinaNova) {
        this.disciplinaNova = disciplinaNova;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Sala getSalaNova() {
        return salaNova;
    }

    public void setSalaNova(Sala salaNova) {
        this.salaNova = salaNova;
    }
    
    

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Editar().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}