/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.evento.view;

import br.com.ifba.evento.controller.EventoIController;
import br.com.ifba.evento.entity.Evento;
import br.com.ifba.usuario.parceiro.controller.ParceiroIController;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
import br.com.ifba.util.StringUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Casa
 */

//AREA DE VIZUALIZAÇÃO DO PARCEIRO
//@Component
public class Eventos extends javax.swing.JFrame {

    /**
     * Creates new form Evento
     * @param eventoController
     * @param parceiroController
     */
    @Autowired
    public Eventos(EventoIController eventoController,ParceiroIController parceiroController) {
        this.eventoController = eventoController;
        this.parceiroController = parceiroController;
        
        tableModel = (DefaultTableModel) tblEvenetos.getModel();
        initComponents();
    }


    @Autowired
    EventoIController eventoController;
    @Autowired
    ParceiroIController parceiroController;
    
   final DefaultTableModel tableModel;
   List<Evento> listaEventos = new ArrayList();
   
   int itemSelecionado = -1;
   
   StringUtil stringUtil = new StringUtil();
   
   public void carregarDadosEventos() {  // Nome atualizado para refletir a funcionalidade

    tableModel.setRowCount(0); // zerar todas as linhas

    listaEventos.clear(); // substitui listaParceiros por listaEventos

    List<Evento> listaCapsula = eventoController.findAll(); // substitui parceiroController por eventoController

    for (Evento evento : listaCapsula) { // substitui Parceiro por Evento no loop
        listaEventos.add(evento);
    }

    preencherTabelaEventos(); // atualizado para preencher tabela de eventos
}
   
  public void preencherTabelaEventos() {
    tableModel.setRowCount(0); // Limpa a tabela antes de preencher
    
    for (Evento evento : listaEventos) {
        tableModel.addRow(new Object[]{
            evento.getParceiro().getNomeEmpresa(),
            evento.getNome(),
            evento.getDataHora().toLocalTime(),            
            evento.getDataHora().plusHours(2).toLocalTime(),
            evento.getDataHora().toLocalDate(),
            evento.getPublicoAlvo(),
            evento.getNivelAcessibilidade()
        });
    }
}
  
    public void adicionarEvento(Parceiro parceiro, Evento evento) {
    if (parceiro == null || evento == null) {
        System.out.println("Parceiro ou Evento está nulo");
        return;
    }
    evento.setParceiro(parceiro);

    eventoController.save(evento);

    listaEventos.add(evento);
    preencherTabelaEventos();
}

   
   public void editarEvento(Evento eventoEditado) {
    if (eventoEditado == null) {
        System.out.println("Evento está nulo");
        return;
    }

    if (itemSelecionado == -1) {
        System.out.println("Nenhum item selecionado");
        return;
    }

    // Atualiza no banco de dados
    eventoController.save(eventoEditado);

    // Atualiza na lista
    listaEventos.set(itemSelecionado, eventoEditado);

    // Atualiza a tabela (considerando a estrutura mostrada na imagem)
    tableModel.setValueAt(
        eventoEditado.getParceiro() != null ? eventoEditado.getParceiro().getNomeEmpresa() : "N/D",
        itemSelecionado, 
        0
    );
    tableModel.setValueAt(eventoEditado.getNome(), itemSelecionado, 1);
    tableModel.setValueAt(eventoEditado.getDataHora().toLocalTime(), itemSelecionado, 2);
    tableModel.setValueAt(
        eventoEditado.getDataHora().plusHours(2).toLocalTime(), // Assumindo 2h de duração
        itemSelecionado, 
        3
    );
    tableModel.setValueAt(eventoEditado.getDataHora().toLocalDate(), itemSelecionado, 4);
    tableModel.setValueAt(eventoEditado.getPublicoAlvo(), itemSelecionado, 5);
    tableModel.setValueAt(eventoEditado.getNivelAcessibilidade(), itemSelecionado, 6);
    tableModel.setValueAt(eventoEditado.getLocalizacao(), itemSelecionado, 7);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jAdicionar = new javax.swing.JFrame();
        txtcnpj = new javax.swing.JTextField();
        txtnomeEvento = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        txthorarioInicio = new javax.swing.JTextField();
        txtPublicoAlvo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxnivelAcessibilidade = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnConfirmarEvento = new javax.swing.JButton();
        txtLocalizacao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProgramacao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEvenetos = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        jLabel1.setText("CNPJ do parceiro");

        jLabel2.setText("Nome do evento");

        jLabel3.setText("Data (DD/MM/AAAA)");

        jLabel4.setText("Horário de início");

        jLabel5.setText("Público alvo");

        cbxnivelAcessibilidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel6.setText("Nível de acessibilidade");

        btnConfirmarEvento.setText("CONFIRMAR");
        btnConfirmarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarEventoActionPerformed(evt);
            }
        });

        txtLocalizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalizacaoActionPerformed(evt);
            }
        });

        jLabel7.setText("Localização");

        txtProgramacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProgramacaoActionPerformed(evt);
            }
        });

        jLabel8.setText("Programacap");

        txtCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoriaActionPerformed(evt);
            }
        });

        jLabel9.setText("Categoria");

        javax.swing.GroupLayout jAdicionarLayout = new javax.swing.GroupLayout(jAdicionar.getContentPane());
        jAdicionar.getContentPane().setLayout(jAdicionarLayout);
        jAdicionarLayout.setHorizontalGroup(
            jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdicionarLayout.createSequentialGroup()
                .addGroup(jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jAdicionarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtLocalizacao))
                    .addGroup(jAdicionarLayout.createSequentialGroup()
                        .addGroup(jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jAdicionarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1)
                                        .addComponent(txtcnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtnomeEvento)
                                        .addComponent(txtData)
                                        .addComponent(txthorarioInicio)
                                        .addComponent(txtPublicoAlvo))
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(cbxnivelAcessibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)))
                            .addGroup(jAdicionarLayout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(btnConfirmarEvento)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtProgramacao))
                .addGap(52, 52, 52))
            .addGroup(jAdicionarLayout.createSequentialGroup()
                .addGroup(jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jAdicionarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jAdicionarLayout.setVerticalGroup(
            jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdicionarLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnomeEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addComponent(txtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProgramacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txthorarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPublicoAlvo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxnivelAcessibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnConfirmarEvento)
                .addContainerGap())
        );

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEvenetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Empresa responsável", "Evento", "Hora de início", "Hora de término", "Data", "Publico alvo", "Nivel de acessibilidade", "Localizaçao"
            }
        ));
        jScrollPane1.setViewportView(tblEvenetos);

        btnAdicionar.setText("ADICIONAR");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setText("REMOVER");

        btnEditar.setText("EDITAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnEditar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemover)
                        .addGap(162, 162, 162)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(372, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        jAdicionar.setVisible(true);
         jAdicionar.setSize(343, 515);
         jAdicionar.setLocationRelativeTo(null);

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnConfirmarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarEventoActionPerformed
       if(stringUtil.isNullOrEmpty(txtcnpj.getText()) || stringUtil.isCnpjValido(txtcnpj.getText())){
       JOptionPane.showMessageDialog(null, "o campo cnpjo deve conter um cnpj válido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtnomeEvento.getText())){
       JOptionPane.showMessageDialog(null, "o campo nome do evento deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txthorarioInicio.getText())){
       JOptionPane.showMessageDialog(null, "o campo horário de inicio deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtPublicoAlvo.getText())){
       JOptionPane.showMessageDialog(null, "o campo publico alvo deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtData.getText())){
       JOptionPane.showMessageDialog(null, "o campo data deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtLocalizacao.getText())){
       JOptionPane.showMessageDialog(null, "o campo Localizacao deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtProgramacao.getText())){
       JOptionPane.showMessageDialog(null, "o campo programacao deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtCategoria.getText())){
       JOptionPane.showMessageDialog(null, "o campo categoria deve ser preenchido");
       return;
       }
       
        LocalTime horarioInicio = LocalTime.parse(txthorarioInicio.getText(), DateTimeFormatter.ofPattern("HH:mm"));
    
      LocalDateTime horarioFormatado = LocalDateTime.of(LocalDate.now(), horarioInicio);
       
      Parceiro parceiro = parceiroController.findByCnpj(txtcnpj.getText())
                           .orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
      
       
       Evento eventoCapsula = new Evento(horarioFormatado,txtPublicoAlvo.getText(),txtProgramacao.getText(),txtCategoria.getText(),parceiro);
       
        adicionarEvento(parceiro, eventoCapsula);
       
    }//GEN-LAST:event_btnConfirmarEventoActionPerformed

    private void txtLocalizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalizacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizacaoActionPerformed

    private void txtProgramacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProgramacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProgramacaoActionPerformed

    private void txtCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoriaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnConfirmarEvento;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cbxnivelAcessibilidade;
    private javax.swing.JFrame jAdicionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblEvenetos;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtLocalizacao;
    private javax.swing.JTextField txtProgramacao;
    private javax.swing.JTextField txtPublicoAlvo;
    private javax.swing.JTextField txtcnpj;
    private javax.swing.JTextField txthorarioInicio;
    private javax.swing.JTextField txtnomeEvento;
    // End of variables declaration//GEN-END:variables
}
