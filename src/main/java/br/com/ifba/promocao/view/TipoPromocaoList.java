package br.com.ifba.promocao.view;

import br.com.ifba.promocao.controller.TipoPromocaoIController;
import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.telainicial.view.TelaInicial;
import br.com.ifba.util.ButtonRenderer;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Joice
 */
@Component
public class TipoPromocaoList extends javax.swing.JFrame {
    @Autowired
    private TipoPromocaoIController controller;
    
    // Contexto do Spring para acessar outros componentes
    @Autowired
    private ApplicationContext context;
    
    // Lista que armazenará os tipos de promoção
    private List<TipoPromocao> listaDeTipos;

    /**
     * Creates new form TipoList
     */
    public TipoPromocaoList() {
        initComponents();
    }
    
    // Método chamado após a construção do objeto
    @PostConstruct
    private void init() {
        configurarTabela();  // Prepara a tabela
        carregarDados();     // Carrega os dados do banco
        adicionarListenerDeCliqueNaTabela(); // Adiciona os ouvintes de clique
    }
    
    // Configura como a tabela vai exibir os dados
    private void configurarTabela() {
        // Cria um modelo de tabela com colunas ID, NOME, EDITAR, REMOVER
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "NOME", "EDITAR", "REMOVER"}
        );
        tblTipos.setModel(model);
        // Define renderizadores especiais para os botões
        tblTipos.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        tblTipos.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
    }
    
    // Carrega os dados do banco para a tabela
    public void carregarDados() {
        try {
            // Pega todos os tipos de promoção do banco
            this.listaDeTipos = controller.findAll();
            DefaultTableModel model = (DefaultTableModel) tblTipos.getModel();
            model.setRowCount(0); // Limpa a tabela

            // Adiciona cada tipo como uma linha na tabela
            for (TipoPromocao tipo : listaDeTipos) {
                model.addRow(new Object[]{
                    tipo.getId(),      // Coluna ID
                    tipo.getTitulo(), // Coluna NOME
                    "Editar",         // Botão Editar
                    "Remover"         // Botão Remover
                });
            }
        } catch (Exception e) {
            // Se der erro, mostra mensagem para o usuário
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar tipos de promoção: " + e.getMessage(),
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    // Adiciona ação quando clica nos botões da tabela
    private void adicionarListenerDeCliqueNaTabela() {
        this.tblTipos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Pega a linha e coluna clicadas
                int linha = tblTipos.rowAtPoint(evt.getPoint());
                int coluna = tblTipos.columnAtPoint(evt.getPoint());

                if (linha >= 0) { // Se clicou em uma linha válida
                    TipoPromocao tipoSelecionado = listaDeTipos.get(linha);

                    if (coluna == 2) { // Se clicou em "Editar"
                        abrirTelaEdicao(tipoSelecionado);
                    } else if (coluna == 3) { // Se clicou em "Remover"
                        confirmarRemocao(tipoSelecionado);
                    }
                }
            }
        });
    }
    // Abre a tela de edição com os dados do tipo selecionado
    private void abrirTelaEdicao(TipoPromocao tipo) {
        try {
            // Pega a tela de edição do Spring
            TipoPromocaoUpdate telaUpdate = context.getBean(TipoPromocaoUpdate.class);
            telaUpdate.carregarTipo(tipo); // Preenche com os dados
            telaUpdate.setVisible(true);   // Mostra a tela
            this.setVisible(false);        // Esconde esta tela
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao abrir tela de edição: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Pede confirmação antes de remover
    private void confirmarRemocao(TipoPromocao tipo) {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente remover este tipo: \"" + tipo.getTitulo() + "\"?", 
                "Confirmar Remoção", 
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            try {
                controller.delete(tipo); // Remove do banco
                JOptionPane.showMessageDialog(this, "Tipo removido com sucesso!");
                carregarDados(); // Atualiza a tabela
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao remover tipo: " + e.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
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

        pnlPricipal = new javax.swing.JPanel();
        lblTituloTela = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        scpnTabelaPromocao = new javax.swing.JScrollPane();
        tblTipos = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPricipal.setBackground(new java.awt.Color(102, 139, 195));

        lblTituloTela.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTituloTela.setForeground(new java.awt.Color(0, 0, 51));
        lblTituloTela.setText("VISUALIZAÇÃO DE TIPOS DE PROMOÇÕES");

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(0, 0, 51));
        lblNome.setText("NOME");

        tblTipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {"", null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "TITULO", "REGRAS", "DESCRIÇÃO"
            }
        ));
        scpnTabelaPromocao.setViewportView(tblTipos);

        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(0, 0, 51));
        btnCadastrar.setText("CADASTRAR");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(0, 0, 51));
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPricipalLayout = new javax.swing.GroupLayout(pnlPricipal);
        pnlPricipal.setLayout(pnlPricipalLayout);
        pnlPricipalLayout.setHorizontalGroup(
            pnlPricipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPricipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloTela, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addGroup(pnlPricipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlPricipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPricipalLayout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addGap(488, 488, 488)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPricipalLayout.createSequentialGroup()
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(btnCadastrar))
                    .addComponent(scpnTabelaPromocao, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlPricipalLayout.setVerticalGroup(
            pnlPricipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPricipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTituloTela)
                .addGap(8, 8, 8)
                .addGroup(pnlPricipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome))
                .addGap(10, 10, 10)
                .addGroup(pnlPricipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(scpnTabelaPromocao, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPricipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPricipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        TipoPromocaoSave telaCadastrar = context.getBean(TipoPromocaoSave.class);
        telaCadastrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

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
            java.util.logging.Logger.getLogger(TipoPromocaoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TipoPromocaoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TipoPromocaoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TipoPromocaoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TipoPromocaoList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnHome;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTituloTela;
    private javax.swing.JPanel pnlPricipal;
    private javax.swing.JScrollPane scpnTabelaPromocao;
    private javax.swing.JTable tblTipos;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
