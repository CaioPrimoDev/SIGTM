package br.com.ifba.promocao.view;

import br.com.ifba.promocao.controller.TipoPromocaoIController;
import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.sessao.UsuarioSession;
import br.com.ifba.telainicial.view.TelaInicial;
import br.com.ifba.util.ButtonRenderer;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.BeansException;
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
    
    @Autowired
    private UsuarioSession userLogado;
    
    // Contexto do Spring para acessar outros componentes
    @Autowired
    private ApplicationContext context;
    
    // Lista que armazenará os tipos de promoção
    private List<TipoPromocao> listaDeTipos;

    @Autowired
    public TipoPromocaoList(TipoPromocaoIController controller, UsuarioSession userLogado) {
        this.controller = controller;
        this.userLogado = userLogado;
        initComponents();
        
        // Inicializações
        configurarTabela();
        carregarDados();
        adicionarListenerDeCliqueNaTabela();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
    }
    
    // Configura como a tabela vai exibir os dados
    private void configurarTabela() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID", 
                "TÍTULO", 
                "REGRAS", 
                "DESCRIÇÃO", 
                "USUÁRIO", 
                "PÚBLICO",
                "EDITAR", 
                "REMOVER"
            }
        );
        tblTipos.setModel(model);

        // Define renderizadores especiais para os botões
        tblTipos.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer()); // Editar
        tblTipos.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer()); // Remover
    }

    // Carrega os dados do banco para a tabela
    public void carregarDados() {
        try {
            this.listaDeTipos = controller.findAll();
            DefaultTableModel model = (DefaultTableModel) tblTipos.getModel();
            model.setRowCount(0); // Limpa a tabela

            for (TipoPromocao tipo : listaDeTipos) {
                model.addRow(new Object[]{
                    tipo.getId(),                                     // ID
                    tipo.getTitulo(),                                 // Título
                    tipo.getRegra(),                                  // Regras
                    tipo.getDescricao(),                              // Descrição
                    tipo.getUsuarioCadastro() != null ? tipo.getUsuarioCadastro().getPessoa().getNome() : "—", // Usuário
                    tipo.getPublicoAlvo() != null ? tipo.getPublicoAlvo().getDescricao() : "—",    // Público alvo
                    "Editar",                                         // Botão Editar
                    "Remover"                                         // Botão Remover
                });
            }
        } catch (Exception e) {
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
                int linha = tblTipos.rowAtPoint(evt.getPoint());
                int coluna = tblTipos.columnAtPoint(evt.getPoint());

                if (linha >= 0) {
                    TipoPromocao tipoSelecionado = listaDeTipos.get(linha);

                    if (coluna == 5) { // Editar
                        if(userLogado.isLogado()) {
                            try {
                                abrirTelaEdicao(tipoSelecionado);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(TipoPromocaoList.this, 
                                    "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            avisoLogin();
                        }
                    } else if (coluna == 6) { // Remover
                        if(userLogado.isLogado()) {
                            confirmarRemocao(tipoSelecionado);
                        } else {
                            avisoLogin();
                        }
                    }
                }
            }
        });
    }

    private void avisoLogin() {
        JOptionPane.showMessageDialog(this, 
            "Acesso negado. Nenhum usuário autenticado na sessão.", 
            "Login pendente", JOptionPane.INFORMATION_MESSAGE);
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
        if(userLogado.isLogado()) {
            try {
                TipoPromocaoSave telaCadastrar = context.getBean(TipoPromocaoSave.class);
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

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {     
        this.context = applicationContext;
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
