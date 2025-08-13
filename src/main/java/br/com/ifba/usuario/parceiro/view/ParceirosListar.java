/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.usuario.parceiro.view;

import br.com.ifba.Solicitacao.controller.SolicitacaoIController;
import br.com.ifba.usuario.comum.controller.UsuarioIController;
import br.com.ifba.usuario.comum.entity.Usuario;
import br.com.ifba.usuario.parceiro.controller.ParceiroIController;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
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

    }

    List<Parceiro> listaParceiros = new ArrayList(); //lista para ajudar a popular a tabela

    final DefaultTableModel tableModel;//tabela dos parceiros
    final DefaultTableModel tableModelS;//tabela dos solicitantes

    int itemSelecionado = -1;

    @Autowired
    ParceiroIController parceiroController;

    @Autowired
    SolicitacaoIController solicitacaoController;
    
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
        for (Parceiro parceiro : listaParceiros) {
            tableModel.addRow(new Object[]{
                parceiro.getNome(),
                parceiro.getCnpj(),
                parceiro.getNomeEmpresa(),
                parceiro.getEmail(),
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

        
            tableModel.addRow(new Object[]{
                parceiroCapsula.getNome(),
                parceiroCapsula.getCnpj(),
                parceiroCapsula.getNomeEmpresa(),
                parceiroCapsula.getEmail(),
                parceiroCapsula.getTelefone()
            });

           
      
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

        tableModel.setValueAt(parceiro.getNome(), itemSelecionado, 0);
        tableModel.setValueAt(parceiro.getCnpj(), itemSelecionado, 1);
        tableModel.setValueAt(parceiro.getNomeEmpresa(), itemSelecionado, 2);
        tableModel.setValueAt(parceiro.getEmail(), itemSelecionado, 3);
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
        jButton1 = new javax.swing.JButton();
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
                "Parceiro", "CNPJ", "Segmento empresarial", "CNPJ", "Nome da empresa"
            }
        ));
        tblSolicitantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSolicitantesMouseClicked(evt);
            }
        });
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

        btnRefresh.setText("VOLTAR");
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

        jButton1.setText("Ver Eventos");

        javax.swing.GroupLayout SolicitacoesParceriaLayout = new javax.swing.GroupLayout(SolicitacoesParceria.getContentPane());
        SolicitacoesParceria.getContentPane().setLayout(SolicitacoesParceriaLayout);
        SolicitacoesParceriaLayout.setHorizontalGroup(
            SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh)
                    .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                        .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAceitar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(btnNegar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(txtpesquisarSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SolicitacoesParceriaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        SolicitacoesParceriaLayout.setVerticalGroup(
            SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SolicitacoesParceriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceitar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(SolicitacoesParceriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNegar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpesquisarSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnatualizarTabela.setText("autalizar tabela");
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnverSolicitacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(btnatualizarTabela)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnverSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnatualizarTabela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        if (!txtnovoNome.getText().isEmpty()) {

            parceiroEditar.setNome(txtnovoNome.getText());//caso o campo esteja vazio deixar o objeto como ele está

        }

        if (!txtnovoSegmentoempresarial.getText().isEmpty()) {

            parceiroEditar.setNomeEmpresa(txtnovoSegmentoempresarial.getText());

        }

        if (!txtnovoEmail.getText().isEmpty()) {

            parceiroEditar.setEmail(txtnovoEmail.getText());
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

        List<Usuario> listaCapsula = usuarioController.findBySolicitacaoSolicitouParceriaTrue();

        for (Usuario solicitantes : listaCapsula) {

            listaSolicitantes.add(solicitantes);
        }

        preenchertabelaSolicitantes();

    }

    public void preenchertabelaSolicitantes() {

        for (Usuario solicitantes : listaSolicitantes) {
            tableModelS.addRow(new Object[]{
                solicitantes.getNome(),
                solicitantes.getTelefone(),
                solicitantes.getEmail(),
                solicitantes.getSolicitacao().getCnpj(),
                solicitantes.getSolicitacao().getNomeEmpresa()
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

       /* String cnpj = novoParceiroCapsula.getSolicitacao().getCnpj();
        String nomeEmpresa = novoParceiroCapsula.getSolicitacao().getNomeEmpresa(); // coloquei nesse formato para melhorar a leitura de código */ 
       
        adicionarParceiro(novoParceiroCapsula, novoParceiroCapsula.getSolicitacao().getCnpj(), novoParceiroCapsula.getSolicitacao().getNomeEmpresa());

        listaSolicitantes.remove(itemSelecionado);//após a solicitação ser aceita o usuario vira parceiro, logo pode sair

        JOptionPane.showMessageDialog(null, "O " + novoParceiroCapsula.getNome() + " foi adicionado como parceiro");
        
        atualizarSolicitantes();
    }//GEN-LAST:event_btnAceitarActionPerformed

    private void btnNegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegarActionPerformed

        Usuario parceiroNegado = listaSolicitantes.get(itemSelecionado);

        if (parceiroNegado == null) {

            JOptionPane.showMessageDialog(null, "Não foi possível adicionar a parceria");
            return;

        }

        //solicitacao = false e vai sair da lista de solicitantes
        parceiroNegado.getSolicitacao().setSolicitouParceria(false); 
        
        solicitacaoController.save(parceiroNegado.getSolicitacao());
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
                    solicitante.getNome(),
                    solicitante.getTelefone(),
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
        List<Usuario> resultados = usuarioController.findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(termo);
        
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum solicitante encontrado com esse nome.");
            txtpesquisarSolicitantes.setText("");
            preencherTabelaParceiros(); // Ou preencherTabelaSolicitantes()
        } else {
            for (Usuario solicitante : resultados) {
                tableModelS.addRow(new Object[]{
                    solicitante.getNome(),
                    solicitante.getTelefone(),
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
    private javax.swing.JButton jButton1;
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
