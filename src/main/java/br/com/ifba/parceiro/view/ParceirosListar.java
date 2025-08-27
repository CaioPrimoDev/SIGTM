/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.parceiro.view;

import br.com.ifba.Solicitacao.controller.SolicitacaoIController;
import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.usuario.controller.UsuarioIController;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.parceiro.controller.ParceiroIController;
import br.com.ifba.parceiro.entity.Parceiro;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Casa
 */
@Component
public class ParceirosListar extends javax.swing.JFrame {
    
    @Autowired
    private UsuarioIController usuarioController;
    
    @Autowired
    ParceiroIController parceiroController;

    @Autowired
    SolicitacaoIController solicitacaoController;

    @Autowired
    public ParceirosListar(ParceiroIController parceiroController, UsuarioIController usuarioController, SolicitacaoIController solicitacaoController) {//mantive o nome de parceiroController apenas para fins organiizacionais   
        this.parceiroController = parceiroController;
        this.usuarioController = usuarioController;
        this.solicitacaoController = solicitacaoController;
        
        initComponents();

        tableModel = (DefaultTableModel) tblParceiros.getModel();//setando padrão para o default table model

        //TELA DE SOLICITAÇÕE//////S
        tableModelS = (DefaultTableModel) tblSolicitantes.getModel();

        carregarDadosParceiros();
        carregardadosSolicitantes();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
    }

    List<Parceiro> listaParceiros = new ArrayList(); //lista para ajudar a popular a tabela

    final DefaultTableModel tableModel;//tabela dos parceiros
    final DefaultTableModel tableModelS;//tabela dos solicitantes

    int itemSelecionado = -1;
    
    // MÉTODOS ESPECÍFICOS
    public void carregarDadosParceiros() {

        tableModel.setRowCount(0);// zerar todas as linhas

        listaParceiros.clear(); // limpar a lista 

        List<Parceiro> listaCapsula = parceiroController.findAll();//acessa todos os parceiros

        for (Parceiro parceiros : listaCapsula) {

            listaParceiros.add(parceiros);
        }

        preencherTabelaParceiros();
    }

    public Parceiro procurarParceirocnpj(String cnpj){
   
    Parceiro parceiro = parceiroController.findByCnpj(cnpj)
                           .orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
   
    return parceiro;
   }
    
    public void preencherTabelaParceiros() {
        // PEDRO
        // um metodo para buscar os dados de usuario pelo parceiro é:
        // usuario.findByNomeContainingIgnoreCase(parceiro.getNome());
        //método para povoamento da tabela
        
        Usuario user;
        for (Parceiro parceiro : listaParceiros) {
            user = usuarioController.findByPessoaId(parceiro.getId());
            tableModel.addRow(new Object[]{
                parceiro.getNome(),
                parceiro.getCnpj(),
                parceiro.getNomeEmpresa(),
                user.getEmail(),
                parceiro.getTelefone()
            });

        }

    }

    public void adicionarParceiro(Usuario usuario, String cnpj, String nomeEmpresa) {

        if (usuario == null) {

            System.out.println("Parceiro esta nulo");

            return;
        }

        Parceiro parceiroCapsula = parceiroController.tornarParceiro(usuario, cnpj, nomeEmpresa);
        
        listaParceiros.add(parceiroCapsula);

            Usuario user = usuarioController.findByPessoaId(parceiroCapsula.getId());
            tableModel.addRow(new Object[]{
                parceiroCapsula.getNome(),
                parceiroCapsula.getCnpj(),
                parceiroCapsula.getNomeEmpresa(),
                user.getEmail(),
                parceiroCapsula.getTelefone()
            });

           JOptionPane.showMessageDialog(null, "Seu novo login é: "+user.getEmail());
         
    }

    public void editarParceiro(Parceiro parceiro) {

        if (parceiro == null) {
            System.out.println("Parceiro esta nulo");

            return;
        }

        if (itemSelecionado == -1) {

            System.out.println("Nenhum item selecionado");
            return;
        }
        
        parceiroController.save(parceiro);//atualizar db

        listaParceiros.set(itemSelecionado, parceiro);

        Usuario user = usuarioController.findByPessoaId(parceiro.getId());
        tableModel.setValueAt(parceiro.getNome(), itemSelecionado, 0);
        tableModel.setValueAt(parceiro.getCnpj(), itemSelecionado, 1);
        tableModel.setValueAt(parceiro.getNomeEmpresa(), itemSelecionado, 2);
        tableModel.setValueAt(user.getEmail(), itemSelecionado, 3);
        tableModel.setValueAt(parceiro.getTelefone(), itemSelecionado, 4);

    }

    public void resetarSelecao() {

        itemSelecionado = -1;

    }

   public void atualizarSolicitantes(){
   
   tableModelS.setRowCount(0);
   carregardadosSolicitantes();
   
   }
    
   public void atualizarParceiros(){
    tableModel.setRowCount(0);
   carregarDadosParceiros();
   }
   
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SolicitacoesParceria = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSolicitantes = new javax.swing.JTable();
        btnAceitar = new javax.swing.JButton();
        btnNegar = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        txtpesquisarSolicitantes = new javax.swing.JTextField();
        EditarParceiro = new javax.swing.JFrame();
        txtnovoNome = new javax.swing.JTextField();
        lblMudarnome = new javax.swing.JLabel();
        txtnovoSegmentoempresarial = new javax.swing.JTextField();
        lblMudarseguimentoempresarial = new javax.swing.JLabel();
        btnconfirmarMudancas = new javax.swing.JButton();
        txtnovoTelefone = new javax.swing.JTextField();
        lblMudartelefone = new javax.swing.JLabel();
        txtnovoEmail = new javax.swing.JTextField();
        lblmudarEmail = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblParceiros = new javax.swing.JTable();
        btnverSolicitacoes = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        txtbarradePesquisa = new javax.swing.JTextField();
        btnatualizarTabela = new javax.swing.JButton();

        tblSolicitantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Parceiro", "Telefone", "EMAIL", "CNPJ", "Nome da empresa"
            }
        ));
        tblSolicitantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSolicitantesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSolicitantes);

        btnAceitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/aceitar.png"))); // NOI18N
        btnAceitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceitarActionPerformed(evt);
            }
        });

        btnNegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recusar.png"))); // NOI18N
        btnNegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegarActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atualizar.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        txtpesquisarSolicitantes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpesquisarSolicitantesKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout SolicitacoesParceriaLayout = new javax.swing.GroupLayout(SolicitacoesParceria.getContentPane());
        SolicitacoesParceria.getContentPane().setLayout(SolicitacoesParceriaLayout);
        SolicitacoesParceriaLayout.setHorizontalGroup(
            SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceitar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNegar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addComponent(txtpesquisarSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        SolicitacoesParceriaLayout.setVerticalGroup(
            SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SolicitacoesParceriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAceitar)
                .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(txtpesquisarSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRefresh)
                            .addComponent(btnNegar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblMudarnome.setText("Mudar nome do titular");

        lblMudarseguimentoempresarial.setText("Mudar nome da empresa");

        btnconfirmarMudancas.setText("CONFIRMAR");
        btnconfirmarMudancas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfirmarMudancasActionPerformed(evt);
            }
        });

        lblMudartelefone.setText("Mudar telefone");

        lblmudarEmail.setText("Mudar email");

        javax.swing.GroupLayout EditarParceiroLayout = new javax.swing.GroupLayout(EditarParceiro.getContentPane());
        EditarParceiro.getContentPane().setLayout(EditarParceiroLayout);
        EditarParceiroLayout.setHorizontalGroup(
            EditarParceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditarParceiroLayout.createSequentialGroup()
                .addGroup(EditarParceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditarParceiroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(EditarParceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnovoNome, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(lblMudarnome)
                            .addComponent(lblMudarseguimentoempresarial)
                            .addComponent(txtnovoSegmentoempresarial)
                            .addComponent(txtnovoTelefone)
                            .addComponent(txtnovoEmail)))
                    .addGroup(EditarParceiroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMudartelefone))
                    .addGroup(EditarParceiroLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btnconfirmarMudancas))
                    .addGroup(EditarParceiroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblmudarEmail)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        EditarParceiroLayout.setVerticalGroup(
            EditarParceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditarParceiroLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblMudarnome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnovoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMudarseguimentoempresarial)
                .addGap(12, 12, 12)
                .addComponent(txtnovoSegmentoempresarial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lblMudartelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnovoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(lblmudarEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnovoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnconfirmarMudancas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblParceiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Razão Social", "CNPJ", "Segmento Empresarial", "Email", "Telefone"
            }
        ));
        tblParceiros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblParceirosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblParceiros);
        if (tblParceiros.getColumnModel().getColumnCount() > 0) {
            tblParceiros.getColumnModel().getColumn(0).setResizable(false);
        }

        btnverSolicitacoes.setText("Solicitações");
        btnverSolicitacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverSolicitacoesActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        txtbarradePesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtbarradePesquisaMouseReleased(evt);
            }
        });
        txtbarradePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbarradePesquisaKeyReleased(evt);
            }
        });

        btnatualizarTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atualizar.png"))); // NOI18N
        btnatualizarTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatualizarTabelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnatualizarTabela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnverSolicitacoes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnatualizarTabela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnverSolicitacoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (listaParceiros.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nenhum parceiro cadastrado");
            return;
        }

        if (itemSelecionado == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum parceiro foi selecionado");
            return;
        }

        Parceiro parceriaEncerrada = listaParceiros.get(itemSelecionado);

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente encerrar a parceria com:\n" + parceriaEncerrada.getNome() + "?",
                "Confirmação de exclusão",
                JOptionPane.YES_NO_OPTION
        );
        //0 = sim | 1 = não | -1 = nada
        if (resposta == JOptionPane.YES_OPTION) {
            parceiroController.removerParceiria(parceriaEncerrada);
            listaParceiros.remove(itemSelecionado);
            tableModel.removeRow(itemSelecionado);
            resetarSelecao();
        }
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        if (listaParceiros.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nenhum parceiro cadastrado");
            return;
        }

        if (itemSelecionado == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum parceiro foi selecionado");
            return;
        }

        EditarParceiro.setVisible(true);
        EditarParceiro.setSize(343, 515);
        EditarParceiro.setLocationRelativeTo(null);

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnverSolicitacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverSolicitacoesActionPerformed
        SolicitacoesParceria.setVisible(true);
        SolicitacoesParceria.setSize(882, 546);
        SolicitacoesParceria.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnverSolicitacoesActionPerformed

    private void btnconfirmarMudancasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarMudancasActionPerformed

        if (listaParceiros.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nenhum parceiro cadastrado");
            return;
        }

        if (itemSelecionado == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum parceiro foi selecionado");
            return;
        }

        Parceiro parceiroEditar = listaParceiros.get(itemSelecionado);//selecionar o parceiro pela lista
        Usuario user = usuarioController.findByPessoaId(parceiroEditar.getId());

        if (!txtnovoNome.getText().isEmpty()) {

            parceiroEditar.setNome(txtnovoNome.getText());//caso o campo esteja vazio deixar o objeto como ele está

        }

        if (!txtnovoSegmentoempresarial.getText().isEmpty()) {

            parceiroEditar.setNomeEmpresa(txtnovoSegmentoempresarial.getText());

        }

        if (!txtnovoEmail.getText().isEmpty()) {

            user.setEmail(txtnovoEmail.getText());
        }

        if (!txtnovoTelefone.getText().isEmpty()) {

            parceiroEditar.setTelefone(txtnovoTelefone.getText());
        }

        editarParceiro(parceiroEditar);

        resetarSelecao();
        
    }//GEN-LAST:event_btnconfirmarMudancasActionPerformed

    private void txtbarradePesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbarradePesquisaKeyReleased
        String termo = txtbarradePesquisa.getText().trim();

        // Limpar tabela de forma segura na EDT
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0); // Usar APENAS um modelo (tableModel)
        });

        if (termo.isEmpty()) {
            preencherTabelaParceiros();
            return;
        }

        //Uso de regex para verificação se é númerico
        if (termo.matches("\\d+")) {
            try {
                Parceiro parceiro = procurarParceirocnpj(termo);
                SwingUtilities.invokeLater(() -> {
                    if (parceiro != null) {
                        tableModel.addRow(new Object[]{
                            parceiro.getNome(),
                            parceiro.getCnpj(),
                            parceiro.getNomeEmpresa()
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum parceiro encontrado com esse CNPJ.");
                        preencherTabelaParceiros();
                    }
                });
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar CNPJ: " + e.getMessage());
            }
        } 
        // Busca por Nome (não numérico)
        else {
            SwingUtilities.invokeLater(() -> {
                List<Parceiro> resultados = parceiroController.findByNomeContainingIgnoreCase(termo);

                if (resultados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum parceiro encontrado com esse nome.");
                    txtbarradePesquisa.setText("");
                    preencherTabelaParceiros();
                } else {
                    for (Parceiro p : resultados) {
                        tableModel.addRow(new Object[]{
                            p.getNome(),
                            p.getCnpj(),
                            p.getNomeEmpresa()
                        });
                    }



                }
            });
        }

    }//GEN-LAST:event_txtbarradePesquisaKeyReleased

    private void txtbarradePesquisaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbarradePesquisaMouseReleased

    }//GEN-LAST:event_txtbarradePesquisaMouseReleased

    //tela de solicitações
    public void carregardadosSolicitantes() {

        tableModelS.setRowCount(0);// zerar todas as linhas

        listaSolicitantes.clear(); // limpar a lista
        
        List<Solicitacao> solicitacoes = solicitacaoController.findBySolicitouParceriaTrue();
        List<Usuario> listaCapsula = solicitacoes.stream()
                                     .map(Solicitacao::getUsuario)
                                     .toList();
        for (Usuario solicitantes : listaCapsula) {

            listaSolicitantes.add(solicitantes);
        }

        preenchertabelaSolicitantes();

    }

    public void preenchertabelaSolicitantes() {

        Solicitacao slc;
        for (Usuario solicitantes : listaSolicitantes) {
            slc = solicitacaoController.findByUsuario(solicitantes).get();
            tableModelS.addRow(new Object[]{
                solicitantes.getPessoa().getNome(),
                solicitantes.getPessoa().getTelefone(),
                solicitantes.getEmail(),
                slc.getCnpj(),
                slc.getNomeEmpresa()
            });
        }
    }

    List<Usuario> listaSolicitantes = new ArrayList();// if (usuario.getSolicitacao ==  true) listaSolicitantes.add(usuario)

    private void btnAceitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceitarActionPerformed
        if (listaSolicitantes.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nenhuma solicitação requerida");
            return;

        }

        Usuario novoParceiroCapsula = listaSolicitantes.get(itemSelecionado);
        Optional<Solicitacao> optSlc = solicitacaoController.findByUsuario(novoParceiroCapsula);

        if (optSlc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma solicitação encontrada para este usuário");
            return;
        }

        Solicitacao slc = optSlc.get(); // agora é seguro


       /* String cnpj = novoParceiroCapsula.getSolicitacao().getCnpj();
        String nomeEmpresa = novoParceiroCapsula.getSolicitacao().getNomeEmpresa(); // coloquei nesse formato para melhorar a leitura de código */ 
       
        adicionarParceiro(novoParceiroCapsula, slc.getCnpj(), slc.getNomeEmpresa());

        listaSolicitantes.remove(itemSelecionado);//após a solicitação ser aceita o usuario vira parceiro, logo pode sair

        JOptionPane.showMessageDialog(null, "O " + novoParceiroCapsula.getPessoa().getNome() + " foi adicionado como parceiro");
        
        atualizarSolicitantes();
    }//GEN-LAST:event_btnAceitarActionPerformed

    private void btnNegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegarActionPerformed

        Usuario parceiroNegado = listaSolicitantes.get(itemSelecionado);
        Solicitacao slc = solicitacaoController.findByUsuario(parceiroNegado).get();

        if (parceiroNegado == null) {

            JOptionPane.showMessageDialog(null, "Não foi possível adicionar a parceria");
            return;

        }

        //solicitacao = false e vai sair da lista de solicitantes
        slc.setSolicitouParceria(false); 
        
        solicitacaoController.save(slc);
        usuarioController.save(parceiroNegado);
        
        listaSolicitantes.remove(parceiroNegado);
        tableModelS.removeRow(itemSelecionado);
    }//GEN-LAST:event_btnNegarActionPerformed

    private void txtpesquisarSolicitantesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpesquisarSolicitantesKeyReleased
        String termo = txtpesquisarSolicitantes.getText().trim();

        // Limpar tabela de forma segura na EDT
        SwingUtilities.invokeLater(() -> tableModelS.setRowCount(0));

        if (termo.isEmpty()) {
            preencherTabelaParceiros(); // Ou preencherTabelaSolicitantes() se for diferente
            return;
        }

        boolean isNumerico = true;
        for (char c : termo.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumerico = false;
                break;
            }
        }

        if (isNumerico) {
            // Busca por ID
            SwingUtilities.invokeLater(() -> {
                try {
                    Long id = Long.valueOf(termo);
                    Usuario solicitante = usuarioController.findById(id);

                    if (solicitante != null) {
                        tableModelS.addRow(new Object[]{
                            solicitante.getPessoa().getNome(),
                            solicitante.getPessoa().getTelefone(),
                            solicitante.getEmail()
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum solicitante encontrado com esse ID.");
                        preencherTabelaParceiros(); 
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar ID: " + e.getMessage());
                    preencherTabelaParceiros(); 
                }
            });
        } else {
            // Busca por nome
            SwingUtilities.invokeLater(() -> {

                List<Solicitacao> solicitacoes = solicitacaoController.findByNomeUsuarioComSolicitacaoAtiva(termo);
                List<Usuario> resultados = solicitacoes.stream()
                                             .map(Solicitacao::getUsuario)
                                             .toList();        
                if (resultados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum solicitante encontrado com esse nome.");
                    txtpesquisarSolicitantes.setText("");
                    preencherTabelaParceiros(); // Ou preencherTabelaSolicitantes()
                } else {
                    for (Usuario solicitante : resultados) {
                        tableModelS.addRow(new Object[]{
                            solicitante.getPessoa().getNome(),
                            solicitante.getPessoa().getTelefone(),
                            solicitante.getEmail()
                        });
                    }

                }
            });
        }
    }//GEN-LAST:event_txtpesquisarSolicitantesKeyReleased

    private void tblParceirosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParceirosMouseClicked
         itemSelecionado = tblParceiros.getSelectedRow();
    }//GEN-LAST:event_tblParceirosMouseClicked

    private void tblSolicitantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSolicitantesMouseClicked
       itemSelecionado = tblSolicitantes.getSelectedRow();
    }//GEN-LAST:event_tblSolicitantesMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        atualizarSolicitantes();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnatualizarTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatualizarTabelaActionPerformed
        atualizarParceiros();
    }//GEN-LAST:event_btnatualizarTabelaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame EditarParceiro;
    private javax.swing.JFrame SolicitacoesParceria;
    private javax.swing.JButton btnAceitar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNegar;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnatualizarTabela;
    private javax.swing.JButton btnconfirmarMudancas;
    private javax.swing.JButton btnverSolicitacoes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMudarnome;
    private javax.swing.JLabel lblMudarseguimentoempresarial;
    private javax.swing.JLabel lblMudartelefone;
    private javax.swing.JLabel lblmudarEmail;
    private javax.swing.JTable tblParceiros;
    private javax.swing.JTable tblSolicitantes;
    private javax.swing.JTextField txtbarradePesquisa;
    private javax.swing.JTextField txtnovoEmail;
    private javax.swing.JTextField txtnovoNome;
    private javax.swing.JTextField txtnovoSegmentoempresarial;
    private javax.swing.JTextField txtnovoTelefone;
    private javax.swing.JTextField txtpesquisarSolicitantes;
    // End of variables declaration//GEN-END:variables
}
