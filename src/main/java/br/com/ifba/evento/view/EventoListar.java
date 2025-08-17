/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.evento.view;

import br.com.ifba.endereco.controller.EnderecoIController;
import br.com.ifba.endereco.entity.Endereco;
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
import org.springframework.stereotype.Component;

/**
 *
 * @author Casa
 */
@Component
public class EventoListar extends javax.swing.JFrame {

    /**
     * Creates new form EventoListar
     */
    @Autowired
    public EventoListar(EventoIController eventoController,ParceiroIController parceiroController,EnderecoIController enderecoController) {
        this.eventoController = eventoController;
        this.parceiroController = parceiroController;
        
        initComponents();
        tableModel = (DefaultTableModel) tblEventos.getModel();
        
    }

    @Autowired
    EventoIController eventoController;
    
    @Autowired
    ParceiroIController parceiroController;
    
    @Autowired
    EnderecoIController enderecoController;
    
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
   
     
  public int nivelAcessibilidade(javax.swing.JComboBox<String> comboBox) {
    String selected = (String) comboBox.getSelectedItem();
    return Integer.parseInt(selected);
}
      
  public LocalDateTime formatarHora(String hora){
   
   LocalTime horarioInicio = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));//formatando o tempo
   LocalDateTime horarioFormatado = LocalDateTime.of(LocalDate.now(), horarioInicio);
        
        return horarioFormatado;
   }   
     
  public LocalDate formatarData(String data){
   
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");//formatando data
   LocalDate dataFormatada = LocalDate.parse(data, formatter);
        
        return dataFormatada;
   }
   
   public Parceiro procurarParceirocnpj(String cnpj){
   
    Parceiro parceiro = parceiroController.findByCnpj(cnpj)
                           .orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
   
    return parceiro;
   }
  
     
  public void preencherTabelaEventos() {
    tableModel.setRowCount(0); // Limpa a tabela antes de preencher
    
    for (Evento evento : listaEventos) {
        tableModel.addRow(new Object[]{
            evento.getParceiro().getNomeEmpresa(),
            evento.getNome(),
            evento.getHora().toLocalTime(),            
            evento.getHora().plusHours(2).toLocalTime(),
            evento.getData(),
            evento.getPublicoAlvo(),
            evento.getNivelAcessibilidade(),
            evento.getEndereco().getBairro(),
            evento.getEndereco().getCidade(),
            evento.getEndereco().getEstado(),
            evento.getEndereco().getNumero(),
            evento.getEndereco().getRua()
        });
    }
}
public void adicionarEvento(Parceiro parceiro, Evento evento, Endereco endereco) {
   if (parceiro == null || evento == null) {
        System.out.println("Parceiro ou Evento está nulo");
        return;
    }

    evento.setParceiro(parceiro);
    evento.setEndereco(endereco);

    eventoController.save(evento);
    
    
    listaEventos.add(evento);
    tableModel.addRow(new Object[]{
            evento.getParceiro().getNomeEmpresa(),
            evento.getNome(),
            evento.getHora().toLocalTime(),            
            evento.getHora().plusHours(2).toLocalTime(),
            evento.getData(),
            evento.getPublicoAlvo(),
            evento.getNivelAcessibilidade(),
            evento.getEndereco().getBairro(),
            evento.getEndereco().getCidade(),
            evento.getEndereco().getEstado(),
            evento.getEndereco().getNumero(),
            evento.getEndereco().getRua()
        });
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
    tableModel.setValueAt(eventoEditado.getHora().toLocalTime(), itemSelecionado, 2);
    tableModel.setValueAt(
        eventoEditado.getHora().plusHours(2).toLocalTime(), // Assumindo 2h de duração
        itemSelecionado, 
        3
    );
    tableModel.setValueAt(eventoEditado.getData(), itemSelecionado, 4);
    tableModel.setValueAt(eventoEditado.getPublicoAlvo(), itemSelecionado, 5);
    tableModel.setValueAt(eventoEditado.getNivelAcessibilidade(), itemSelecionado, 6);
    tableModel.setValueAt(eventoEditado.getEndereco().getBairro(), itemSelecionado, 7);
    tableModel.setValueAt(eventoEditado.getEndereco().getCidade(), itemSelecionado, 8);
    tableModel.setValueAt(eventoEditado.getEndereco().getEstado(), itemSelecionado, 9);
    tableModel.setValueAt(eventoEditado.getEndereco().getNumero(), itemSelecionado, 10);
    tableModel.setValueAt(eventoEditado.getEndereco().getRua(), itemSelecionado, 11);
    
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jadicionar = new javax.swing.JFrame();
        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnumeroRua = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxAcessibilidade = new javax.swing.JComboBox<>();
        txthoraInicio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtpublicoAlvo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        txtParceiro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btncriarEvento = new javax.swing.JButton();
        txtProgramacao = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEventos = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("nome");

        jLabel2.setText("Descrição");

        jLabel3.setText("Estado");

        jLabel4.setText("Bairro");

        jLabel5.setText("Rua");

        jLabel6.setText("Numero da rua");

        jLabel8.setText("Data (DD/MM/AAAA)");

        jLabel9.setText("Cidade");

        cbxAcessibilidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", " " }));

        jLabel10.setText("Hora de inicio(HH/MM)");

        jLabel11.setText("Publico alvo");

        jLabel12.setText("Categoria");

        txtParceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtParceiroActionPerformed(evt);
            }
        });

        jLabel13.setText("Nível de acessibilidade");

        jLabel14.setText("Parceiro responsável (CNPJ)");

        btncriarEvento.setText("Criar evento");
        btncriarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncriarEventoActionPerformed(evt);
            }
        });

        txtProgramacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProgramacaoActionPerformed(evt);
            }
        });

        jLabel15.setText("Programação (X depois y e por fim Z)");

        javax.swing.GroupLayout jadicionarLayout = new javax.swing.GroupLayout(jadicionar.getContentPane());
        jadicionar.getContentPane().setLayout(jadicionarLayout);
        jadicionarLayout.setHorizontalGroup(
            jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jadicionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jadicionarLayout.createSequentialGroup()
                        .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txthoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpublicoAlvo, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnumeroRua, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadicionarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btncriarEvento)
                        .addGap(371, 371, 371))))
            .addGroup(jadicionarLayout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadicionarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadicionarLayout.createSequentialGroup()
                        .addComponent(cbxAcessibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(384, 384, 384))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jadicionarLayout.createSequentialGroup()
                        .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtProgramacao, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jadicionarLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(99, 99, 99)))
                        .addGap(223, 223, 223))))
        );
        jadicionarLayout.setVerticalGroup(
            jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jadicionarLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jadicionarLayout.createSequentialGroup()
                        .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jadicionarLayout.createSequentialGroup()
                                .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jadicionarLayout.createSequentialGroup()
                                        .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jadicionarLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jadicionarLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtnumeroRua, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txthoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jadicionarLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jadicionarLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtpublicoAlvo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(15, 15, 15)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jadicionarLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jadicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jadicionarLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jadicionarLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProgramacao, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(cbxAcessibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncriarEvento)
                .addGap(46, 46, 46))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
        jScrollPane1.setViewportView(tblEventos);

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jButton2.setText("Remover");

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton3)
                                .addComponent(jButton2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnAdicionar)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtParceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtParceiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtParceiroActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        jadicionar.setVisible(true);
        jadicionar.setSize(843,805);
        jadicionar.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btncriarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncriarEventoActionPerformed
          if(stringUtil.isNullOrEmpty(txtParceiro.getText()) || stringUtil.isCnpjValido(txtParceiro.getText())){
       JOptionPane.showMessageDialog(null, "o campo cnpjo deve conter um cnpj válido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtNome.getText())){
       JOptionPane.showMessageDialog(null, "o campo nome do evento deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txthoraInicio.getText())){
       JOptionPane.showMessageDialog(null, "o campo horário de inicio deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtpublicoAlvo.getText())){
       JOptionPane.showMessageDialog(null, "o campo publico alvo deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtData.getText())){
       JOptionPane.showMessageDialog(null, "o campo data deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtBairro.getText())){
       JOptionPane.showMessageDialog(null, "o campo Localizacao deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtEstado.getText())){
       JOptionPane.showMessageDialog(null, "o campo Localizacao deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtCidade.getText())){
       JOptionPane.showMessageDialog(null, "o campo Localizacao deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtRua.getText())){
       JOptionPane.showMessageDialog(null, "o campo Localizacao deve ser preenchido");
       return;
       }
       
       if(stringUtil.isNullOrEmpty(txtnumeroRua.getText())){
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

       //adicionando objeto capsula
       Evento eventoCapsula = new Evento();
       //procurando o parceiro responsavel pelo evento
       Parceiro parceiro = procurarParceirocnpj(txtParceiro.getText());

       Endereco endereco = new Endereco();
       
       eventoCapsula.setNome(txtNome.getText());
       eventoCapsula.setDescricao(txtDescricao.getText());
       endereco.setBairro(txtBairro.getText());
       endereco.setEstado(txtEstado.getText());
       endereco.setEstado(txtEstado.getText());
       endereco.setNumero(txtnumeroRua.getText());
       endereco.setRua(txtRua.getText());
       endereco.setCidade(txtCidade.getText());
       eventoCapsula.setNivelAcessibilidade(nivelAcessibilidade(cbxAcessibilidade));
       eventoCapsula.setHora(formatarHora(txthoraInicio.getText()));
       eventoCapsula.setData(formatarData(txtData.getText()));
       eventoCapsula.setPublicoAlvo(txtpublicoAlvo.getText());
       eventoCapsula.setProgramacao(txtParceiro.getText());
       eventoCapsula.setCategoria(txtCategoria.getText()); 
       eventoCapsula.setParceiro(parceiro);
       
       
       
        adicionarEvento(parceiro, eventoCapsula,endereco );
    }//GEN-LAST:event_btncriarEventoActionPerformed

    private void txtProgramacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProgramacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProgramacaoActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btncriarEvento;
    private javax.swing.JComboBox<String> cbxAcessibilidade;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFrame jadicionar;
    private javax.swing.JTable tblEventos;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtParceiro;
    private javax.swing.JTextField txtProgramacao;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txthoraInicio;
    private javax.swing.JTextField txtnumeroRua;
    private javax.swing.JTextField txtpublicoAlvo;
    // End of variables declaration//GEN-END:variables
}
