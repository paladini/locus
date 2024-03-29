/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControleDisciplina;
import control.ControleProfessor;
import control.ControleSala;
import control.ControleTurma;
import entidades.Disciplina;
import entidades.Professor;
import entidades.Sala;
import entidades.Turma;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import util.LineWrapCellRenderer;

/**
 *
 * @author silvio
 */
public class GerarEnsalamento extends javax.swing.JFrame {

    /**
     * Creates new form GerarEnsalamento
     */
    public GerarEnsalamento() {
        initComponents();

        // Setar como desativado o jLabel6 ("jLabel6")
        jLabel6.setText("Turmas:");

        // Cria um modelo para a Lista.
        final DefaultListModel lista1 = new DefaultListModel();

        // Seta o modelo na Lista
        jList1.setModel(lista1);

        // Carrega lista de turmas
        this.recarregarTurmas();

        /*
         * ===========================================
         *            TABELA DO ENSALAMENTO
         * ===========================================
         */

        // Cria as colunas
        Object colunas[] = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"};

        // Cria o modelo
        final DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            public Class getColumnClass(int columnIndex) {
                return String.class;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Seta o modelo na tabela e um novo tamanho para as linhas
        jTable1.setModel(modelo);

        jTable1.setRowHeight(jTable1.getRowHeight() + 20);
        jTable1.setDefaultRenderer(String.class, new LineWrapCellRenderer());

        // Mouse listener
        jList1.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {

                    int index = list.locationToIndex(evt.getPoint());

                    Object hue = lista1.getElementAt(index);

                    carregarDados(hue);

                }
            }
        });

    }

    private void carregarDados(Object tipo) {

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        // Técnico em Informática
        if (tipo instanceof Turma) {

            modelo.addRow(new String[]{"                                      ",
                "                                      ",
                "                                      ",
                "                                      ",
                "                                      "
            });

            modelo.addRow(new String[]{"Daniel \nDisciplina:POO \nSala:G23",
                "Andrenizia \nDisciplina:Projeto de Software \nSala:G24",
                "Artur \nDisciplina: BD II \nSala:G24",
                "Daniel \nDisciplina:POO \nSala:G23",
                ""
            });

            modelo.addRow(new String[]{"",
                "                                      ",
                "                                      ",
                "                                      ",
                "                                      "
            });
        } else {
            
            // G23
            if (tipo instanceof Sala) {

                modelo.addRow(new String[]{"                                      ",
                    "                                      ",
                    "                                      ",
                    "                                      ",
                    "                                      "
                });

                modelo.addRow(new String[]{"Daniel \nDisciplina: POO \nTécnico em Informática - 2012/01 - V2",
                    "",
                    "",
                    "Daniel \nDisciplina: POO \nTécnico em Informática - 2012/01 - V2",
                    ""
                });

                modelo.addRow(new String[]{"",
                    "                                      ",
                    "                                      ",
                    "                                      ",
                    "                                      "
                });

            } else {
                
                // Banco de Dados
                if (tipo instanceof Disciplina) {

                    modelo.addRow(new String[]{"                                      ",
                        "                                      ",
                        "                                      ",
                        "                                      ",
                        "                                      "
                    });

                    modelo.addRow(new String[]{"                                      ",
                        "                                      ",
                        "Artur \nSala: G23 \nTécnico em Informática - 2012/01 - V2",
                        "                                      ",
                        "                                      "
                    });

                    modelo.addRow(new String[]{"",
                        "                                      ",
                        "                                      ",
                        "                                      ",
                        "                                      "
                    });

                } else {
                    
                    // Daniel
                    if (tipo instanceof Professor) {

                        modelo.addRow(new String[]{"                                      ",
                            "                                      ",
                            "                                      ",
                            "                                      ",
                            "                                      "
                        });

                        modelo.addRow(new String[]{"Turma: Técnico em Informática - 2012/01 - V2 \nDisciplina: POO \nSala: G23",
                            "                                      ",
                            "                                      ",
                            "Turma: Técnico em Informática - 2012/01 - V2 \nDisciplina: POO \nSala: G23",
                            "                                      "
                        });

                        modelo.addRow(new String[]{"                                      ",
                            "                                      ",
                            "                                      ",
                            "                                      ",
                            "                                      "
                        });
                    }
                }
            }
        }



    }

    private void recarregarTurmas() {
        // Variável "modelo" é o "modelo" da jTable1
        DefaultListModel modelo = (DefaultListModel) jList1.getModel();

        // Remove todos os elementos da lista
        modelo.removeAllElements();

        // Instancia um novo ControleCurso
        ControleTurma ct = new ControleTurma();

        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Turma> consulta = ct.consulta();

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())) {
            for (Turma temp : consulta) {
                modelo.addElement(temp);
            }
        }
    }

    private void recarregarSalas() {
        // Variável "modelo" é o "modelo" da jTable1
        DefaultListModel modelo = (DefaultListModel) jList1.getModel();

        // Remove todos os elementos da lista
        modelo.removeAllElements();

        // Instancia um novo ControleCurso
        ControleSala cs = new ControleSala();

        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Sala> consulta = cs.consulta();

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())) {
            for (Sala temp : consulta) {
                modelo.addElement(temp);
            }
        }
    }

    private void recarregarDisciplinas() {
        // Variável "modelo" é o "modelo" da jTable1
        DefaultListModel modelo = (DefaultListModel) jList1.getModel();

        // Remove todos os elementos da lista
        modelo.removeAllElements();

        // Instancia um novo ControleCurso
        ControleDisciplina cd = new ControleDisciplina();

        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Disciplina> consulta = cd.consulta();

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())) {
            for (Disciplina temp : consulta) {
                modelo.addElement(temp);
            }
        }
    }

    private void recarregarProfessores() {
        // Variável "modelo" é o "modelo" da jTable1
        DefaultListModel modelo = (DefaultListModel) jList1.getModel();

        // Remove todos os elementos da lista
        modelo.removeAllElements();

        // Instancia um novo ControleCurso
        ControleProfessor cd = new ControleProfessor();

        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Professor> consulta = cd.consulta();

        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())) {
            for (Professor temp : consulta) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Locus - Relatório de Ensalamento");

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

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_sala.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-superior-ensalamento-selecionado.fw.png"))); // NOI18N

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Turma", "Sala", "Professor", "Disciplina" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Organizar por:");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Matutino");

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Vespertino");

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Noturno");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Turnos:");

        jScrollPane1.setViewportView(jList1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("jLabel6");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(10, 10, 10)
                                        .addComponent(jCheckBox2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckBox3))
                                    .addComponent(jLabel5))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        int indice = jComboBox1.getSelectedIndex();

        switch (indice) {
            case 0:
                jLabel6.setText("Turmas:");
                jLabel6.setVisible(true);
                this.recarregarTurmas();
                break;
            case 1:
                jLabel6.setText("Salas:");
                jLabel6.setVisible(true);
                this.recarregarSalas();
                break;
            case 2:
                jLabel6.setText("Professores:");
                jLabel6.setVisible(true);
                this.recarregarProfessores();
                break;
            case 3:
                jLabel6.setText("Disciplinas:");
                jLabel6.setVisible(true);
                this.recarregarDisciplinas();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // Caso o usuário passe o mouse em cima, exibe essa imagem.
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-superior-salas-selecionado.fw.png")));
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // Caso o usuário tire o mouse de cima, volta a exibir a imagem anterior.
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_sala.png")));
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // Ao clicar no botão Salas, cria janela "Gerenciar Salas"
        dispose();
        GerenciarSalas gerenciarSalas = new GerenciarSalas();
        gerenciarSalas.setVisible(true);
        gerenciarSalas.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel17MouseClicked

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
            java.util.logging.Logger.getLogger(GerarEnsalamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarEnsalamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarEnsalamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarEnsalamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerarEnsalamento().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
// Tentando fazer um negócio de várias linhas em uma única linha do modelo.
/**
 * Multiline Table Cell Renderer.
 */
