/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControleSala;
import entidades.Sala;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luiz_malaquias
 */
public class GerenciarSalas extends javax.swing.JFrame {

    /**
     * Creates new form GerenciarSalas
     */
    public GerenciarSalas() {

        initComponents();
        jLabel3.setVisible(false);

        // Cria uma coluna para a tabela
        Object colunas[] = {"Salas"};

        // Cria um modelo e diz que ele tem uma coluna (e depois sobreescrevendo um método para não ser possível editar a table)
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        // Seta o modelo na tabela, seta uma nova fonte e aumenta o tamanho das linhas.
        jTable1.setModel(modelo);
        jTable1.setFont(new Font("Helvetica", Font.PLAIN, 18));
        jTable1.setRowHeight(jTable1.getRowHeight() + 10);

        // Atualiza as disciplinas exibidas
        this.recarregarSalas();

        // "Listener", para "escutar" um duplo clique nas linhas dentro da tabela.
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    // Pega o nome da disciplina nessa posição
                    String nomeSala = (String) jTable1.getValueAt(row, column);

                    // Instancia nova "view" chamada "editar". Em seguida exibe ela para o usuário centralizada.
                    Editar editar = new Editar(1, nomeSala);
                    editar.setVisible(true);
                    editar.setLocationRelativeTo(null);

                    // "Listener" para recarregar as disciplinas quando fechar a janela de "editar disciplinas". 
                    editar.addWindowListener(new WindowAdapter() {
                        public void windowClosed(WindowEvent evt) {
                            recarregarSalas();
                        }
                    });
                }
            }
        });
    }

    /**
     * Faz uma nova consulta no banco de dados, atualizando todas as salas na
     * lista de disciplinas.
     */
    private void recarregarSalas() {

        // Variável "modelo" é o "modelo" da jTable1
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        // Limpa todas as linhas do modelo (para não simplesmente adicionar os mesmos resultados já existentes na lista.
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        // Instancia um novo ControleDisciplina
        ControleSala cs = new ControleSala();

        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Sala> consulta = cs.consulta();

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())) {
            for (Sala temp : consulta) {
                modelo.addRow(new String[]{temp.getNome()});
            }
        }

    }

    /**
     * Faz uma nova consulta no banco de dados com os termos digitados,
     * atualizando as disciplinas na lista de disciplinas. Difere do método
     * anterior pois leva em consideração os termos digitados pelo usuário.
     */
    private void recarregarSalas(ArrayList<Sala> consulta) {

        // Variável "modelo" é o "modelo" da jTable1
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        // Limpa todas as linhas do modelo (para não simplesmente adicionar os mesmos resultados já existentes na lista.
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        // Instancia um novo ControleDisciplina
        ControleSala cs = new ControleSala();

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        for (Sala temp : consulta) {
            modelo.addRow(new String[]{temp.getNome()});
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Locus - Salas");
        setResizable(false);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jTextField1.setBounds(0, 0, 650, 32);
        jLayeredPane1.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adicionar-geral.fw.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jLabel5.setBounds(650, 0, 150, 32);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Salas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 0));
        jLabel3.setText("Sala adicionada!");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo5.fw.png"))); // NOI18N
        jLabel1.setMinimumSize(new java.awt.Dimension(50, 70));
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 70));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_prof.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_turma.png"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-superior-salas-selecionado.fw.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_escola.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_ensalamento.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // Instancia disciplina (entidade) e controleDisciplina(controller)
        Sala sala = new Sala();
        ControleSala cs = new ControleSala();

        // Pega nome do campo "jTextField1" e seta nome na disciplina
        String nome = jTextField1.getText();
        sala.setNome(nome);

        // Chama método do controle para adicionar disciplina ao banco de dados
        cs.adicionar(sala);

        // Limpa campo de nome da disciplina (deixa em branco)
        jTextField1.setText(null);

        // Cria texto dizendo que disciplina foi adicionada
        jLabel3.setVisible(true);

        // Atualiza a lista de disciplinas
        this.recarregarSalas();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // Se existir algum texto no "jLabel3" (confirmação de que Disciplina foi inserida), vai limpar esse campo;
        if (jLabel3.getText() != null) {
            jLabel3.setText(null);
        }

        // Se existir algum texto, atualiza a lista de disciplinas de acordo com os termos digitados
        String texto = jTextField1.getText();
        if (texto.length() != 0) {
            // Instancia novo gerenciar disciplina
            ControleSala cs = new ControleSala();

            // Recarrega as disciplinas de acordo com o 
            this.recarregarSalas(cs.consultaComTermos(texto));
        } else {
            // Se não, exibe todas as disciplinas
            this.recarregarSalas();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked


        // Ao clicar no botão Escola, cria janela "Gerenciar Escola"
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked

        // Ao clicar no botão Professor, cria janela "Gerenciar Professor"
        dispose();
        GerenciarProfessores gerenciarProfessores = new GerenciarProfessores();
        gerenciarProfessores.setVisible(true);
        gerenciarProfessores.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered

        // Caso o usuário passe o mouse em cima, exibe essa imagem.
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-superior-professor-selecionado.fw.png")));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited

        // Caso o usuário tire o mouse de cima, volta a exibir a imagem anterior.
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_prof.png")));
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked

        // Ao clicar no botão Turmas, cria janela "Gerenciar Turmas"
        dispose();
        GerenciarTurmas gerenciarTurmas = new GerenciarTurmas();
        gerenciarTurmas.setVisible(true);
        gerenciarTurmas.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        // Caso o usuário passe o mouse em cima, exibe essa imagem.
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-superior-turmas0selecionado-1.fw.png")));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        // Caso o usuário tire o mouse de cima, volta a exibir a imagem anterior.
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_turma.png")));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

        // Ao clicar no botão Escola, cria janela "Gerenciar Escola"
        dispose();
        GerenciarEscola gerenciarEscola = new GerenciarEscola();
        gerenciarEscola.setVisible(true);
        gerenciarEscola.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        // Caso o usuário passe o mouse em cima, exibe essa imagem.
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-superior-escola-selecionado.fw.png")));
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // Caso o usuário tire o mouse de cima, volta a exibir a imagem anterior.
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_escola.png")));
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // Caso o usuário passe o mouse em cima, exibe essa imagem.
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-superior-ensalamento-selecionado.fw.png")));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // Caso o usuário tire o mouse de cima, volta a exibir a imagem anterior.
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_ensalamento.png")));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // Ao clicar no botão Escola, cria janela "Gerenciar Escola"
        dispose();
        GerarEnsalamento gerarEnsalamento = new GerarEnsalamento();
        gerarEnsalamento.setVisible(true);
        gerarEnsalamento.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciarSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarSalas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
