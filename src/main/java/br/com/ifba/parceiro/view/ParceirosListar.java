/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.parceiro.view;

import br.com.ifba.parceiro.controller.ParceiroIController;
import br.com.ifba.parceiro.entity.Parceiro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Component;

import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Casa
 */
@Component
public class ParceirosListar extends javax.swing.JFrame {

    @Autowired
    public ParceirosListar(ParceiroIController parceiroController) {
        this.parceiroController = parceiroController;
        initComponents();
 
        tableModel = (DefaultTableModel) tblParceiros.getModel();//setando padrão para o default table model
        
        List<Parceiro> listaParceiros = new ArrayList(); //lista para ajudar a popular a tabela
    }

    List<Parceiro> listaParceiros = new ArrayList(); //lista para ajudar a popular a tabela
    
     final DefaultTableModel tableModel;
     
     int itemSelecionado = -1;
     
     @Autowired
     ParceiroIController parceiroController;
    
   // MÉTODOS ESPECÍFICOS
    
    public void carregarDados(){
    
        tableModel.setRowCount(0);// zerar todas as linhas
    
        listaParceiros.clear(); // limpar a lista 
        
        List <Parceiro> listaCapsula = parceiroController.findAll();
        
        for(Parceiro parceiros: listaCapsula){
        
            listaParceiros.add(parceiros);
        }
        
        preencherTabela();
    }
    
    public void preencherTabela(){
    //método para povoamento da tabela
        for(Parceiro parceiro: listaParceiros){
        tableModel.addRow(new Object []{
                parceiro.getNome(),
                parceiro.getCpf_cnpj(),
                parceiro.getSegmento_empresarial(),
                parceiro.getHorario_abertura(),
                parceiro.getHorario_fechamento()});
        
        }
    
    }
    
    public void adicionarParceiro(Parceiro parceiro){
    
       if(parceiro == null){
       
           System.out.println("Parceiro esta nulo");
           
       return;
       } 
        
     parceiroController.save(parceiro);
     
     listaParceiros.add(parceiro);
        
     tableModel.addRow(new Object []{
                parceiro.getNome(),
                parceiro.getCpf_cnpj(),
                parceiro.getSegmento_empresarial(),
                parceiro.getHorario_abertura(),
                parceiro.getHorario_fechamento()});
        
    }
    
    public void editarParceiro(Parceiro parceiro){
        
    if(parceiro == null){
        System.out.println("Parceiro esta nulo");   
    
    return;
    }
        
    if(itemSelecionado == -1){
    
        System.out.println("Nenhum item selecionado");
        return;
    }
    parceiroController.save(parceiro);//atualizar db
    
    listaParceiros.set(itemSelecionado, parceiro);
    
    tableModel.setValueAt(parceiro.getNome(),itemSelecionado,0);
    tableModel.setValueAt(parceiro.getCpf_cnpj(),itemSelecionado,1);
    tableModel.setValueAt(parceiro.getSegmento_empresarial(),itemSelecionado,2);
    tableModel.setValueAt(parceiro.getHorario_abertura(),itemSelecionado,3);
    tableModel.setValueAt(parceiro.getHorario_fechamento(),itemSelecionado,4);
   
    }
    
    public void resetarSelecao(){
    
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
        txtPesquisar = new javax.swing.JTextField();
        EditarParceiro = new javax.swing.JFrame();
        txtnovoNome = new javax.swing.JTextField();
        lblMudarnome = new javax.swing.JLabel();
        txtnovoSegmentoempresarial = new javax.swing.JTextField();
        lblMudarseguimentoempresarial = new javax.swing.JLabel();
        lblMudarhorariodeabertura = new javax.swing.JLabel();
        txtnovoHorariodeabertura = new javax.swing.JTextField();
        lblMudarHorariodefechamento = new javax.swing.JLabel();
        txtnovoHorariodefechamento = new javax.swing.JTextField();
        btnconfirmarMudancas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblParceiros = new javax.swing.JTable();
        btnverSolicitacoes = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        txtbarradePesquisa = new javax.swing.JTextField();

        tblSolicitantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Razão social", "CNPJ", "Segmento empresarial", "Horário de abertura", "Horário de fechamento", "Status da solicitação"
            }
        ));
        jScrollPane2.setViewportView(tblSolicitantes);

        btnAceitar.setText("ACEITAR");

        btnNegar.setText("NEGAR");

        btnRetornar.setText("VOLTAR");

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
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRetornar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblMudarnome.setText("Mudar nome");

        lblMudarseguimentoempresarial.setText("Mudar seguimento empresarial");

        lblMudarhorariodeabertura.setText("Mudar horário de abertura");

        lblMudarHorariodefechamento.setText("Mudar horário de fechamento");

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
                            .addComponent(txtnovoNome)
                            .addComponent(lblMudarnome)
                            .addComponent(lblMudarseguimentoempresarial)
                            .addComponent(txtnovoSegmentoempresarial)
                            .addComponent(lblMudarhorariodeabertura)
                            .addComponent(txtnovoHorariodeabertura)
                            .addComponent(lblMudarHorariodefechamento)
                            .addComponent(txtnovoHorariodefechamento, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))
                    .addGroup(EditarParceiroLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMudarhorariodeabertura)
                .addGap(18, 18, 18)
                .addComponent(txtnovoHorariodeabertura, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMudarHorariodefechamento)
                .addGap(18, 18, 18)
                .addComponent(txtnovoHorariodefechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnconfirmarMudancas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
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
                "Razão Social", "CNPJ", "Segmento Empresarial", "Horário de abertura", "Horário de fechamento"
            }
        ));
        jScrollPane1.setViewportView(tblParceiros);

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

        txtbarradePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbarradePesquisaKeyTyped(evt);
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
                        .addGap(74, 74, 74)
                        .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtbarradePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if(listaParceiros.isEmpty()){
        
            JOptionPane.showMessageDialog(null, "Nenhum parceiro cadastrado");
            return;
        }
  
        if(itemSelecionado == -1){
            JOptionPane.showMessageDialog(null, "Nenhum parceiro foi selecionado");
        return;
        }
        
        Parceiro parceiroExcluir = listaParceiros.get(itemSelecionado);
        
        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir o curso:\n" + parceiroExcluir.getNome() + "?",
                "Confirmação de exclusão",
                JOptionPane.YES_NO_OPTION
        );
        //0 = sim | 1 = não | -1 = nada
        if (resposta == JOptionPane.YES_OPTION) {
            listaParceiros.remove(itemSelecionado);
            tableModel.removeRow(itemSelecionado);
            parceiroController.delete(parceiroExcluir.getId());

            resetarSelecao();
        }
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
        if(listaParceiros.isEmpty()){
        
            JOptionPane.showMessageDialog(null, "Nenhum parceiro cadastrado");
            return;
        }
  
        if(itemSelecionado == -1){
            JOptionPane.showMessageDialog(null, "Nenhum parceiro foi selecionado");
        return;
        }
        
        EditarParceiro.setVisible(true);
        EditarParceiro.setSize(343,515);
        EditarParceiro.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnverSolicitacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverSolicitacoesActionPerformed
       SolicitacoesParceria.setVisible(true);
       SolicitacoesParceria.setSize(882,546);
       SolicitacoesParceria.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnverSolicitacoesActionPerformed

    private void txtbarradePesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbarradePesquisaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbarradePesquisaKeyTyped

    private void btnconfirmarMudancasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarMudancasActionPerformed
        
        if(listaParceiros.isEmpty()){
        
            JOptionPane.showMessageDialog(null, "Nenhum parceiro cadastrado");
            return;
        }
  
        if(itemSelecionado == -1){
            JOptionPane.showMessageDialog(null, "Nenhum parceiro foi selecionado");
        return;
        }
        
        Parceiro parceiroEditar = listaParceiros.get(itemSelecionado);//selecionar o parceiro pela lista
        
        if(txtnovoNome.getText().isEmpty()){
        
            parceiroEditar.setNome(parceiroEditar.getNome());//caso o campo esteja vazio deixar o objeto como ele está
        
        }
        
        if(txtnovoSegmentoempresarial.getText().isEmpty()){
        
            parceiroEditar.setSegmento_empresarial(parceiroEditar.getSegmento_empresarial());//caso o campo esteja vazio deixar o objeto como ele está
        
        }
      //PROCURAR MELHORES FORMAS DE MUDAR O TEMPO
      
        if(txtnovoHorariodeabertura.getText().isEmpty()){
        
            txtnovoHorariodeabertura.setText(String.valueOf(parceiroEditar.getHorario_abertura()));
        
        }
        
         if(txtnovoHorariodefechamento.getText().isEmpty()){
        
            txtnovoHorariodefechamento.setText(String.valueOf(parceiroEditar.getHorario_fechamento()));
        
        }
         
         editarParceiro(parceiroEditar);
         
         resetarSelecao();
         
//procruar melhores metodos para edição de tempo
        
    }//GEN-LAST:event_btnconfirmarMudancasActionPerformed

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
    private javax.swing.JLabel lblMudarHorariodefechamento;
    private javax.swing.JLabel lblMudarhorariodeabertura;
    private javax.swing.JLabel lblMudarnome;
    private javax.swing.JLabel lblMudarseguimentoempresarial;
    private javax.swing.JTable tblParceiros;
    private javax.swing.JTable tblSolicitantes;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtbarradePesquisa;
    private javax.swing.JTextField txtnovoHorariodeabertura;
    private javax.swing.JTextField txtnovoHorariodefechamento;
    private javax.swing.JTextField txtnovoNome;
    private javax.swing.JTextField txtnovoSegmentoempresarial;
    // End of variables declaration//GEN-END:variables
}
