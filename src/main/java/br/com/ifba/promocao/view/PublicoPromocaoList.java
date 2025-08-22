/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.promocao.view;

import br.com.ifba.promocao.controller.PublicoPromocaoController;
import br.com.ifba.promocao.entity.PublicoPromocao;
import br.com.ifba.sessao.UsuarioSession;
import br.com.ifba.telainicial.view.TelaInicial;
import br.com.ifba.util.ButtonRenderer;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Joice
 */
@Component
public class PublicoPromocaoList extends javax.swing.JFrame {
    
    @Autowired
    private PublicoPromocaoController controller;

    @Autowired
    private UsuarioSession userLogado;

    @Autowired
    private ApplicationContext context;

    private List<PublicoPromocao> listaPublicos;
    /**
     * Creates new form PublicoPromocaoList
     */
    @Autowired
    public PublicoPromocaoList(PublicoPromocaoController controller, UsuarioSession userLogado) {
        this.controller = controller;
        this.userLogado = userLogado;
        initComponents();
        configurarTabela();
        carregarDados();
        adicionarListenerTabela();
    }


    private void configurarTabela() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "NOME", "DESCRIÇÃO", "FAIXA ETÁRIA", "INTERESSE", "USUÁRIO", "EDITAR", "REMOVER"}
        );
        tblPublicos.setModel(model);

        tblPublicos.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tblPublicos.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
    }

    public void carregarDados() {
        try {
            listaPublicos = controller.findAll();
            DefaultTableModel model = (DefaultTableModel) tblPublicos.getModel();
            model.setRowCount(0);

            for (PublicoPromocao publico : listaPublicos) {
                model.addRow(new Object[]{
                    publico.getId(),
                    publico.getNome(),
                    publico.getDescricao(),
                    publico.getFaixaEtaria(),
                    publico.getInteresse(),
                    publico.getUsuarioCadastro() != null ? publico.getUsuarioCadastro().getNome() : "—",
                    "Editar",
                    "Remover"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar públicos: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarListenerTabela() {
        tblPublicos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tblPublicos.rowAtPoint(evt.getPoint());
                int coluna = tblPublicos.columnAtPoint(evt.getPoint());

                if (linha >= 0) {
                    PublicoPromocao publicoSelecionado = listaPublicos.get(linha);

                    if (coluna == 6) { // Editar
                        if (userLogado.isLogado()) {
                            abrirTelaEdicao(publicoSelecionado);
                        } else {
                            avisoLogin();
                        }
                    } else if (coluna == 7) { // Remover
                        if (userLogado.isLogado()) {
                            confirmarRemocao(publicoSelecionado);
                        } else {
                            avisoLogin();
                        }
                    }
                }
            }
        });
    }

    private void abrirTelaEdicao(PublicoPromocao publico) {
        try {
            PublicoPromocaoUpdate telaUpdate = context.getBean(PublicoPromocaoUpdate.class);
            telaUpdate.carregarPublico(publico); // Preenche os campos e armazena o objeto
            telaUpdate.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao abrir tela de edição: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarRemocao(PublicoPromocao publico) {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente remover o público: \"" + publico.getNome() + "\"?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            try {
                controller.delete(Long.MIN_VALUE);
                JOptionPane.showMessageDialog(this, "Público removido com sucesso!");
                carregarDados();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao remover público: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void avisoLogin() {
        JOptionPane.showMessageDialog(this,
                "Acesso negado. Nenhum usuário autenticado na sessão.",
                "Login pendente",
                JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblTituloTela = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        scpnTabelaPromocao = new javax.swing.JScrollPane();
        tblPublicos = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPrincipal.setBackground(new java.awt.Color(102, 139, 195));

        lblTituloTela.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTituloTela.setForeground(new java.awt.Color(0, 0, 51));
        lblTituloTela.setText("VISUALIZAÇÃO DE TIPOS DE PROMOÇÕES");

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(0, 0, 51));
        lblNome.setText("NOME");

        tblPublicos.setModel(new javax.swing.table.DefaultTableModel(
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
        scpnTabelaPromocao.setViewportView(tblPublicos);

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

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloTela, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addGap(488, 488, 488)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(btnCadastrar))
                    .addComponent(scpnTabelaPromocao, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTituloTela)
                .addGap(8, 8, 8)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome))
                .addGap(10, 10, 10)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if (userLogado.isLogado()) {
            try {
                PublicoPromocaoSave telaCadastrar = context.getBean(PublicoPromocaoSave.class);
                telaCadastrar.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        } else {
            avisoLogin();
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnHome;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTituloTela;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JScrollPane scpnTabelaPromocao;
    private javax.swing.JTable tblPublicos;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
