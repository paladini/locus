/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControleCurso;
import entidades.Curso;
import entidades.Disciplina;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author silvio
 */
public class CadastrarCurso extends javax.swing.JFrame {

    // Variáveis
    Curso curso;

    /**
     * Cria um novo form "EditarCurso"
     *
     * @param nomeDisciplina Nome da curso a ser pesquisada no banco de dados.
     */
    public CadastrarCurso(String nomeCurso) {

        // Inicializa todos os componentes
        initComponents();
        
        // Não fechar todas as janelas ao fechar esta janela.
        setDefaultCloseOperation(CadastrarCurso.DISPOSE_ON_CLOSE);

        // Instancia um novo ControleCurso
        ControleCurso cc = new ControleCurso();

        // Armazena a curso a ser editado
        curso = cc.consultaCurso(nomeCurso);

        // Seta os valores para exibir ao usuário
        jTextField1.setText(curso.getNome());

        // Cria um modelo para as jList
        // jList1 = Lista de todas as disciplinas
        // jList2 = Lista de todas as disciplinas já relacionadas ao curso
        final DefaultListModel lista1 = new DefaultListModel();
        final DefaultListModel lista2 = new DefaultListModel();

        // Seta os modelos na jList e o "renderer customizado" para poder adicionar um objeto, e exibir um texto. 
        // Explicação no último método desse código (que na verdade é uma classe).
        jList1.setModel(lista1);
        jList2.setModel(lista2);

        // Carrega os dados nas jList
        this.recarregarDisciplina();
        this.recarregarDisciplinaCurso();
        
        // Listener para um duplo clique nos itens da jList1
        // Lista de todas as disciplinas
        jList1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());

                    // Pegando disciplina do index atual (onde o usuario clicou duas vezes)
                    Disciplina disciplina = (Disciplina) lista1.getElementAt(index);

                    // Adicionando disciplina ao curso
                    curso.adicionarDisciplina(disciplina);

                    // Adicionando disciplina ao curso (no banco de dados)
                    ControleCurso cc = new ControleCurso();
                    cc.adicionarDisciplina(curso.getId(), disciplina.getId());

                    // Removendo elemento dessa lista
                    lista1.remove(index);

                    // Adicionando elemento à outra lista
                    lista2.addElement(disciplina);
                }
            }
        });

        // Listener para duplo clique nos itens da jList2 
        // Lista de disciplinas adicionadas ao curso
        jList2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());

                    // Pegando disciplina do index atual (onde o usuario clicou duas vezes)
                    Disciplina disciplina = (Disciplina) lista2.getElementAt(index);

                    // Adicionando disciplina ao curso
                    curso.removerDisciplina(disciplina);

                    // Excluir disciplina no banco de dados
                    ControleCurso cc = new ControleCurso();
                    cc.excluirDisciplina(curso.getId(), disciplina.getId());

                    // Removendo elemento dessa lista
                    lista2.remove(index);

                    // Adicionando elemento à outra lista
                    lista1.addElement(disciplina);
                    
                }
            }
        });

    }

    /**
     * Método para recarregar a jList de todas as disciplinas.
     */
    private void recarregarDisciplina() {
        // Variável "modelo" é o "modelo" da jTable1
        DefaultListModel modelo = (DefaultListModel) jList1.getModel();

        // Remove todos os elementos da lista
        modelo.removeAllElements();

        // Instancia um novo ControleCurso
        ControleCurso cc = new ControleCurso();

        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Disciplina> consulta = cc.listaDisciplinasNaoAssociadas(curso);

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())) {
            for (Disciplina temp : consulta) {
                modelo.addElement(temp);
            }
        }
    }

    /**
     * Método para recarregar a jList de disciplinas já adicionadas ao curso.
     */
    private void recarregarDisciplinaCurso() {
        // Variável "modelo" é igual ao "modelo" da jTable1
        DefaultListModel modelo = (DefaultListModel) jList2.getModel();

        // Remove todos os elementos da lista
        modelo.removeAllElements();

        // Instancia um novo ControleCurso
        ControleCurso cc = new ControleCurso();

        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Disciplina> consulta = cc.listaDisciplinasAssociadas(curso);

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())) {
            for (Disciplina temp : consulta) {
                modelo.addElement(temp);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome de usuário:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Locus - Cadastrar Curso");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nome: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Curso");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adicionar-curso.fw.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item1", "Item2", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel7.setText("Disciplinas:");

        jScrollPane2.setViewportView(jList2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator2)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        // Instanciando gerenciar curso
        ControleCurso cc = new ControleCurso();

        // Fazendo algumas verificações de segurança
        if (jTextField1.getText().length() > 0) {
            if (jTextField1.getText() != " ") {
                String novoCurso = jTextField1.getText();
                curso.setNome(novoCurso);
            }
        }

        // Atualizando a curso
        cc.atualizar(curso);

        // Fechando a janela
        dispose();


    }//GEN-LAST:event_jLabel1MouseClicked

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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
//            java.util.logging.Logger.getLogger(EditarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EditarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EditarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EditarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EditarDisciplina().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
