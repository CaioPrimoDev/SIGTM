/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.usuario.parceiro.view;

import br.com.ifba.usuario.comum.controller.UsuarioIController;
import br.com.ifba.usuario.comum.entity.Usuario;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
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
    public ParceirosListar(UsuarioIController parceiroController, UsuarioIController usuarioController) {//mantive o nome de parceiroController apenas para fins organiizacionais   
        this.parceiroController = parceiroController;
        this.usuarioController = usuarioController;
        
        initComponents();

        tableModel = (DefaultTableModel) tblParceiros.getModel();//setando padrão para o default table model

        //TELA DE SOLICITAÇÕES
        tableModelS = (DefaultTableModel) tblSolicitantes.getModel();

        carregarDadosParceiros();
        carregardadosSolicitantes();

    }

    List<Usuario> listaParceiros = new ArrayList(); //lista para ajudar a popular a tabela

    final DefaultTableModel tableModel;//tabela dos parceiros
    final DefaultTableModel tableModelS;//tabela dos solicitantes

    int itemSelecionado = -1;

    @Autowired
    UsuarioIController parceiroController;

    // MÉTODOS ESPECÍFICOS
    public void carregarDadosParceiros() {

        tableModel.setRowCount(0);// zerar todas as linhas

        listaParceiros.clear(); // limpar a lista 

        List<Usuario> listaCapsula = parceiroController.findByTipoNomeIgnoreCase("PARCEIRO");//acessa todos os usuarios onde tipo.getNome() = PARCEIRO

        for (Usuario parceiros : listaCapsula) {

            listaParceiros.add(parceiros);
        }

        preencherTabelaParceiros();
    }

    public void preencherTabelaParceiros() {
        // PEDRO
        // um metodo para buscar os dados de usuario pelo parceiro é:
        // usuario.findByNomeContainingIgnoreCase(parceiro.getNome());
        //método para povoamento da tabela
        for (Usuario parceiro : listaParceiros) {
            tableModel.addRow(new Object[]{
                parceiro.getNome(),
                parceiro.getCnpj(),
                parceiro.getNomeEmpresa()
            });

        }

    }

    public void adicionarParceiro(Usuario usuario) {

        if (usuario == null) {

            System.out.println("Parceiro esta nulo");

            return;
        }

        usuario.getTipo().setNome("PARCEIRO");// definindo o tipo do usuário

        usuario.setSolicitacao(false);// como ele foi adicionado como parceiro a solicitação não é mais necessária
       
        listaParceiros.add(usuario);

        tableModel.addRow(new Object[]{
            usuario.getNome(),
            usuario.getCnpj(),
            usuario.getNomeEmpresa()});

    }

    public void editarParceiro(Usuario parceiro) {

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

        tableModel.setValueAt(parceiro.getNome(), itemSelecionado, 0);
        tableModel.setValueAt(parceiro.getCnpj(), itemSelecionado, 1);
        tableModel.setValueAt(parceiro.getNomeEmpresa(), itemSelecionado, 2);

    }

    public void resetarSelecao() {

        itemSelecionado = -1;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SolicitacoesParceria = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSolicitantes = new javax.swing.JTable();
        btnAceitar = new javax.swing.JButton();
        btnNegar = new javax.swing.JButton();
        btnRetornar = new javax.swing.JButton();
        txtpesquisarSolicitantes = new javax.swing.JTextField();
        EditarParceiro = new javax.swing.JFrame();
        txtnovoNome = new javax.swing.JTextField();
        lblMudarnome = new javax.swing.JLabel();
        txtnovoSegmentoempresarial = new javax.swing.JTextField();
        lblMudarseguimentoempresarial = new javax.swing.JLabel();
        btnconfirmarMudancas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblParceiros = new javax.swing.JTable();
        btnverSolicitacoes = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        txtbarradePesquisa = new javax.swing.JTextField();

        tblSolicitantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Razão social", "CNPJ", "Segmento empresarial"
            }
        ));
        jScrollPane2.setViewportView(tblSolicitantes);

        btnAceitar.setText("ACEITAR");
        btnAceitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceitarActionPerformed(evt);
            }
        });

        btnNegar.setText("NEGAR");
        btnNegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegarActionPerformed(evt);
            }
        });

        btnRetornar.setText("VOLTAR");

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
                    .addComponent(btnRetornar)
                    .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                        .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAceitar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnNegar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(94, 94, 94)
                        .addComponent(txtpesquisarSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        SolicitacoesParceriaLayout.setVerticalGroup(
            SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SolicitacoesParceriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAceitar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNegar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpesquisarSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRetornar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
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
                            .addComponent(txtnovoSegmentoempresarial)))
                    .addGroup(EditarParceiroLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btnconfirmarMudancas)))
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
                .addGap(48, 48, 48)
                .addComponent(btnconfirmarMudancas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblParceiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Razão Social", "CNPJ", "Segmento Empresarial"
            }
        ));
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

        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setText("EDITAR");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnverSolicitacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(166, 166, 166)
                        .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnverSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
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

        Usuario parceriaEncerrada = listaParceiros.get(itemSelecionado);

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente encerrar a parceria com:\n" + parceriaEncerrada.getNome() + "?",
                "Confirmação de exclusão",
                JOptionPane.YES_NO_OPTION
        );
        //0 = sim | 1 = não | -1 = nada
        if (resposta == JOptionPane.YES_OPTION) {
            listaParceiros.remove(itemSelecionado);
            tableModel.removeRow(itemSelecionado);
            parceriaEncerrada.getTipo().setNome("USUARIOCOMUM");
            //relação com a parte de caio na próxima sprint
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

        Usuario parceiroEditar = listaParceiros.get(itemSelecionado);//selecionar o parceiro pela lista

        if (txtnovoNome.getText().isEmpty()) {

            parceiroEditar.setNome(parceiroEditar.getNome());//caso o campo esteja vazio deixar o objeto como ele está

        }

        if (txtnovoSegmentoempresarial.getText().isEmpty()) {

            parceiroEditar.setNomeEmpresa(parceiroEditar.getNomeEmpresa());//caso o campo esteja vazio deixar o objeto como ele está

        }

        editarParceiro(parceiroEditar);

        resetarSelecao();

//procruar melhores metodos para edição de tempo

    }//GEN-LAST:event_btnconfirmarMudancasActionPerformed

    private void txtbarradePesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbarradePesquisaKeyReleased
 String termo = txtbarradePesquisa.getText().trim();

        //limpar tabela de forma segura na EDT
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0);
        });

        if (termo.isEmpty()) {
            preencherTabelaParceiros();
            return;
        }

        try {
            Long id = Long.valueOf(termo);

            //usar invokeLater para garantir execução na thread correta
            SwingUtilities.invokeLater(() -> {
                try {
                    Usuario parceiro= parceiroController.findById(id);
                    //primeiro ver se é numérico
                    if (parceiro != null) {
                        tableModelS.addRow(new Object[]{
                            parceiro.getNome(),
                            parceiro.getCnpj(),
                            parceiro.getNomeEmpresa(),});
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum parceiro encontrado com esse ID.");
                        preencherTabelaParceiros();
                    }
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar parceiro: " + e.getMessage());
                }
            });

        } catch (NumberFormatException e) {
            //se não for numérico procurar por nome
            SwingUtilities.invokeLater(() -> {
                List<Usuario> resultados = parceiroController.findByTipoNomeAndNomeContainingIgnoreCase("PARCEIRO", termo);//procura por usuários do tipo PARCEIRO e com o nome termo

                if (resultados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum parceiro encontrado com esse nome.");
                    preencherTabelaParceiros();
                } else {
                    for (Usuario solicitante : resultados) {
                        tableModel.addRow(new Object[]{
                           solicitante.getNome(),
                            solicitante.getCnpj(),
                            solicitante.getNomeEmpresa()
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

        List<Usuario> listaCapsula = usuarioController.findBySolicitacaoTrue();

        for (Usuario solicitantes : listaCapsula) {

            listaSolicitantes.add(solicitantes);
        }

        preenchertabelaSolicitantes();

    }

    public void preenchertabelaSolicitantes() {

        for (Usuario solicitantes : listaSolicitantes) {
            tableModel.addRow(new Object[]{
                solicitantes.getNome(),
                solicitantes.getCnpj(),
                solicitantes.getNomeEmpresa()
            });
        }
    }

    List<Usuario> listaSolicitantes = new ArrayList();// if (usuario.getSolicitacao ==  true) listaSolicitantes.add(usuario)

    @Autowired
    UsuarioIController usuarioController;

    private void btnAceitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceitarActionPerformed
        if (listaSolicitantes.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nenhuma solicitação requerida");
            return;

        }

        Usuario novoParceiroCapsula = listaSolicitantes.get(itemSelecionado);

        if (novoParceiroCapsula == null) {

            JOptionPane.showMessageDialog(null, "Não foi possível adicionar a parceria");
            return;

        }
        adicionarParceiro(novoParceiroCapsula);

        listaSolicitantes.remove(itemSelecionado);//após a solicitação ser aceita o usuario vira parceiro, logo pode sair

        JOptionPane.showMessageDialog(null, "O " + novoParceiroCapsula.getNome() + " foi adicionado como parceiro");
    }//GEN-LAST:event_btnAceitarActionPerformed

    private void btnNegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegarActionPerformed
        if (listaSolicitantes.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nenhuma solicitação requerida");
            return;

        }

        Usuario novoParceiroCapsula = listaSolicitantes.get(itemSelecionado);

        if (novoParceiroCapsula == null) {

            JOptionPane.showMessageDialog(null, "Não foi possível adicionar a parceria");
            return;

        }

        //solicitacao = false e vai sair da lista de solicitantes
        novoParceiroCapsula.setSolicitacao(false);//como ele foi recusado vai sair da lista de solicitÇão
        listaSolicitantes.remove(novoParceiroCapsula);
    }//GEN-LAST:event_btnNegarActionPerformed

    private void txtpesquisarSolicitantesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpesquisarSolicitantesKeyReleased
          String termo = txtpesquisarSolicitantes.getText().trim();

        //limpar tabela de forma segura na EDT
        SwingUtilities.invokeLater(() -> {
            tableModelS.setRowCount(0);
        });

        if (termo.isEmpty()) {
            preencherTabelaParceiros();
            return;
        }//

        try {
            Long id = Long.valueOf(termo);

            //usar invokeLater para garantir execução na thread correta
            SwingUtilities.invokeLater(() -> {
                try {
                    Usuario solicitante= usuarioController.findById(id);
                    //primeiro ver se é numérico
                    if (solicitante != null) {
                        tableModelS.addRow(new Object[]{
                            solicitante.getNome(),
                            solicitante.getCnpj(),
                            solicitante.getNomeEmpresa(),});
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum solicitante encontrado com esse ID.");
                        preencherTabelaParceiros();
                    }
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar solicitante: " + e.getMessage());
                }
            });

        } catch (NumberFormatException e) {
            //se não for numérico procurar por nome
            SwingUtilities.invokeLater(() -> {
                List<Usuario> resultados = parceiroController.findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(termo);

                if (resultados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum solicitante encontrado com esse nome.");
                    preencherTabelaParceiros();
                } else {
                    for (Usuario solicitante : resultados) {
                        tableModelS.addRow(new Object[]{
                           solicitante.getNome(),
                            solicitante.getCnpj(),
                            solicitante.getNomeEmpresa()
                        });
                    }
                }
            });
        }

    }//GEN-LAST:event_txtpesquisarSolicitantesKeyReleased

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
    private javax.swing.JButton btnRetornar;
    private javax.swing.JButton btnconfirmarMudancas;
    private javax.swing.JButton btnverSolicitacoes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMudarnome;
    private javax.swing.JLabel lblMudarseguimentoempresarial;
    private javax.swing.JTable tblParceiros;
    private javax.swing.JTable tblSolicitantes;
    private javax.swing.JTextField txtbarradePesquisa;
    private javax.swing.JTextField txtnovoNome;
    private javax.swing.JTextField txtnovoSegmentoempresarial;
    private javax.swing.JTextField txtpesquisarSolicitantes;
    // End of variables declaration//GEN-END:variables
}