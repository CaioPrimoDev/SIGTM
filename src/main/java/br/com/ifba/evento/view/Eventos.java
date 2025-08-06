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
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
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
            evento.getHora().toLocalTime(),            
            evento.getHora().plusHours(2).toLocalTime(),
            evento.getData(),
            evento.getPublicoAlvo(),
            evento.getNivelAcessibilidade(),
            evento.getLocalizacao()
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
    tableModel.setValueAt(eventoEditado.getHora().toLocalTime(), itemSelecionado, 2);
    tableModel.setValueAt(
        eventoEditado.getHora().plusHours(2).toLocalTime(), // Assumindo 2h de duração
        itemSelecionado, 
        3
    );
    tableModel.setValueAt(eventoEditado.getData(), itemSelecionado, 4);
    tableModel.setValueAt(eventoEditado.getPublicoAlvo(), itemSelecionado, 5);
    tableModel.setValueAt(eventoEditado.getNivelAcessibilidade(), itemSelecionado, 6);
    tableModel.setValueAt(eventoEditado.getLocalizacao(), itemSelecionado, 7);
}

   public int nivelAcessibilidade (javax.swing.JComboBox<String> comboBox){
   
      int nivelAcessibilidade = (int) comboBox.getSelectedItem(); 
 
   return nivelAcessibilidade;
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
   
   public void resetarSelecao(){
   itemSelecionado = -1;
   
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
        lblParceiro = new javax.swing.JLabel();
        lblparceiroNome = new javax.swing.JLabel();
        lblDatar = new javax.swing.JLabel();
        lblhorarioInicio = new javax.swing.JLabel();
        lblpublcioAlvo = new javax.swing.JLabel();
        cbxnivelAcessibilidade = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnConfirmarEvento = new javax.swing.JButton();
        txtLocalizacao = new javax.swing.JTextField();
        lblLocalizacao = new javax.swing.JLabel();
        txtProgramacao = new javax.swing.JTextField();
        lblProgramacao = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblDescricao = new javax.swing.JLabel();
        jEdit = new javax.swing.JFrame();
        txtnovoParceiro = new javax.swing.JTextField();
        txtnovoEvento = new javax.swing.JTextField();
        txtnovaCategoria = new javax.swing.JTextField();
        txtnovaLocalizacao = new javax.swing.JTextField();
        novaProgramacao = new javax.swing.JTextField();
        txtnovaData = new javax.swing.JTextField();
        txtnovoHorario = new javax.swing.JTextField();
        txtnovopublicoAlvo = new javax.swing.JTextField();
        cbxnovaAcessibilidade = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnconfirmarAlteracoes = new javax.swing.JButton();
        txtnovaDescricao = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEvenetos = new javax.swing.JTable();
        txtpesquisarEvento = new javax.swing.JTextField();
        btnadicionarEvento = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        txthorarioInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthorarioInicioActionPerformed(evt);
            }
        });

        lblParceiro.setText("CNPJ do parceiro");

        lblparceiroNome.setText("Nome do evento");

        lblDatar.setText("Data (DD/MM/AAAA)");

        lblhorarioInicio.setText("Horário de início");

        lblpublcioAlvo.setText("Público alvo");

        cbxnivelAcessibilidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cbxnivelAcessibilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxnivelAcessibilidadeActionPerformed(evt);
            }
        });

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

        lblLocalizacao.setText("Localização");

        txtProgramacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProgramacaoActionPerformed(evt);
            }
        });

        lblProgramacao.setText("Programação");

        txtCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoriaActionPerformed(evt);
            }
        });

        lblCategoria.setText("Categoria");

        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });

        lblDescricao.setText("Descrição do evento");

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
                                        .addComponent(lblParceiro)
                                        .addComponent(txtcnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                                        .addComponent(lblparceiroNome)
                                        .addComponent(txtnomeEvento)
                                        .addComponent(txtData)
                                        .addComponent(txthorarioInicio)
                                        .addComponent(txtPublicoAlvo))
                                    .addComponent(lblDatar)
                                    .addComponent(lblhorarioInicio)
                                    .addComponent(lblpublcioAlvo)
                                    .addComponent(cbxnivelAcessibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(lblLocalizacao)))
                            .addGroup(jAdicionarLayout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(btnConfirmarEvento)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtProgramacao))
                .addGap(52, 52, 52))
            .addGroup(jAdicionarLayout.createSequentialGroup()
                .addGroup(jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCategoria)
                    .addGroup(jAdicionarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProgramacao)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescricao))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jAdicionarLayout.setVerticalGroup(
            jAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdicionarLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblParceiro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lblparceiroNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnomeEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProgramacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProgramacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDatar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLocalizacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblhorarioInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txthorarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpublcioAlvo)
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

        txtnovoParceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovoParceiroActionPerformed(evt);
            }
        });

        txtnovoEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovoEventoActionPerformed(evt);
            }
        });

        txtnovaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovaCategoriaActionPerformed(evt);
            }
        });

        txtnovaLocalizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovaLocalizacaoActionPerformed(evt);
            }
        });

        novaProgramacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novaProgramacaoActionPerformed(evt);
            }
        });

        txtnovaData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovaDataActionPerformed(evt);
            }
        });

        txtnovoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovoHorarioActionPerformed(evt);
            }
        });

        txtnovopublicoAlvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovopublicoAlvoActionPerformed(evt);
            }
        });

        cbxnovaAcessibilidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel12.setText("Novo parceiro por CNPJ");

        jLabel14.setText("Novo nome do evento");

        jLabel16.setText("Nova categoria");

        jLabel17.setText("Nova localização");

        jLabel18.setText("Nova programação");

        jLabel19.setText("Nova data(DD/MM/AAAA)");

        jLabel21.setText("Público alvo");

        jLabel22.setText("Novo horário");

        jLabel23.setText("Novo nível de acessibilidade");

        btnconfirmarAlteracoes.setText("jButton1");
        btnconfirmarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfirmarAlteracoesActionPerformed(evt);
            }
        });

        txtnovaDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnovaDescricaoActionPerformed(evt);
            }
        });

        jLabel20.setText("Nova descrição");

        javax.swing.GroupLayout jEditLayout = new javax.swing.GroupLayout(jEdit.getContentPane());
        jEdit.getContentPane().setLayout(jEditLayout);
        jEditLayout.setHorizontalGroup(
            jEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEditLayout.createSequentialGroup()
                .addGroup(jEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEditLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnovoParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnovoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnovaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnovaLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(novaProgramacao, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnovaData, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnovoHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnovopublicoAlvo, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxnovaAcessibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(txtnovaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addGroup(jEditLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(btnconfirmarAlteracoes)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jEditLayout.setVerticalGroup(
            jEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEditLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnovoParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(2, 2, 2)
                .addComponent(txtnovoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addGap(2, 2, 2)
                .addComponent(txtnovaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnovaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(8, 8, 8)
                .addComponent(txtnovaLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(novaProgramacao, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(8, 8, 8)
                .addComponent(txtnovaData, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addGap(8, 8, 8)
                .addComponent(txtnovoHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnovopublicoAlvo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addGap(7, 7, 7)
                .addComponent(cbxnovaAcessibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnconfirmarAlteracoes)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jLabel11.setText("jLabel11");

        jLabel13.setText("jLabel13");

        jLabel15.setText("jLabel15");

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

        txtpesquisarEvento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpesquisarEventoKeyReleased(evt);
            }
        });

        btnadicionarEvento.setText("ADICIONAR");
        btnadicionarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadicionarEventoActionPerformed(evt);
            }
        });

        btnRemover.setText("REMOVER");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnadicionarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnEditar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemover)
                        .addGap(162, 162, 162)
                        .addComponent(txtpesquisarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(372, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadicionarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpesquisarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnadicionarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadicionarEventoActionPerformed
        jAdicionar.setVisible(true);
         jAdicionar.setSize(343, 515);
         jAdicionar.setLocationRelativeTo(null);

    }//GEN-LAST:event_btnadicionarEventoActionPerformed

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

       //adicionando objeto capsula
       Evento eventoCapsula = new Evento();
       
       Parceiro parceiro = procurarParceirocnpj(txtcnpj.getText());
       
       eventoCapsula.setNome(txtnomeEvento.getText());
       eventoCapsula.setDescricao(txtDescricao.getText());
       eventoCapsula.setLocalizacao(txtLocalizacao.getText());
       eventoCapsula.setNivelAcessibilidade(nivelAcessibilidade(cbxnivelAcessibilidade));
       eventoCapsula.setHora(formatarHora(txthorarioInicio.getText()));
       eventoCapsula.setData(formatarData(txtData.getText()));
       eventoCapsula.setPublicoAlvo(txtPublicoAlvo.getText());
       eventoCapsula.setProgramacao(txtProgramacao.getText());
       eventoCapsula.setCategoria(txtCategoria.getText()); 
       eventoCapsula.setParceiro(parceiro);
       
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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void txthorarioInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthorarioInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthorarioInicioActionPerformed

    private void txtnovoParceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovoParceiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovoParceiroActionPerformed

    private void txtnovoEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovoEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovoEventoActionPerformed

    private void txtnovaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovaCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovaCategoriaActionPerformed

    private void txtnovaLocalizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovaLocalizacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovaLocalizacaoActionPerformed

    private void txtnovaDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovaDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovaDataActionPerformed

    private void txtnovoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovoHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovoHorarioActionPerformed

    private void txtnovopublicoAlvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovopublicoAlvoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovopublicoAlvoActionPerformed

    private void cbxnivelAcessibilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxnivelAcessibilidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxnivelAcessibilidadeActionPerformed

    private void novaProgramacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novaProgramacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_novaProgramacaoActionPerformed

    private void btnconfirmarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarAlteracoesActionPerformed
        if (listaEventos.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Nenhum evento cadastrado");
    return;
}

if (itemSelecionado == -1) {
    JOptionPane.showMessageDialog(null, "Nenhum evento foi selecionado");
    return;
}

Evento eventoEditar = listaEventos.get(itemSelecionado);

// Atualiza apenas campos preenchidos
if (!txtnomeEvento.getText().isEmpty()) {
    eventoEditar.setNome(txtnomeEvento.getText());
}

if (!txtDescricao.getText().isEmpty()) {
    eventoEditar.setDescricao(txtDescricao.getText());
}

if (!txtLocalizacao.getText().isEmpty()) {
    eventoEditar.setLocalizacao(txtLocalizacao.getText());
}

// O combobox sempre terá um valor selecionado
eventoEditar.setNivelAcessibilidade(nivelAcessibilidade(cbxnivelAcessibilidade));

// Atualiza hora se preenchida
if (!txthorarioInicio.getText().isEmpty()) {
    eventoEditar.setHora(formatarHora(txthorarioInicio.getText()));
}

// Atualiza data se preenchida
if (!txtData.getText().isEmpty()) {
    eventoEditar.setData(formatarData(txtData.getText()));
}

if (!txtPublicoAlvo.getText().isEmpty()) {
    eventoEditar.setPublicoAlvo(txtPublicoAlvo.getText());
}

if (!txtProgramacao.getText().isEmpty()) {
    eventoEditar.setProgramacao(txtProgramacao.getText());
}

if (!txtCategoria.getText().isEmpty()) {
    eventoEditar.setCategoria(txtCategoria.getText());
}

Parceiro novoParceiro = procurarParceirocnpj(txtnovoParceiro.getText());

if(novoParceiro != null){

 eventoEditar.setParceiro(novoParceiro);
}

editarEvento(eventoEditar);
resetarSelecao();

    }//GEN-LAST:event_btnconfirmarAlteracoesActionPerformed

    private void txtnovaDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnovaDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnovaDescricaoActionPerformed

    private void txtpesquisarEventoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpesquisarEventoKeyReleased
       String termo = txtpesquisarEvento.getText().trim(); 

// Limpar tabela de forma segura na EDT
SwingUtilities.invokeLater(() -> {
    tableModel.setRowCount(0); // Supondo que o modelo da tabela de eventos é tableModelEventos
});

if (termo.isEmpty()) {
    preencherTabelaEventos(); // Método que preenche a tabela com todos os eventos
    return;
}

// Pesquisar apenas por nome (não precisa da parte numérica)
SwingUtilities.invokeLater(() -> {
    try {
        List<Evento> resultados = eventoController.findByNomeContainingIgnoreCase(termo);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum evento encontrado com esse nome.");
            preencherTabelaEventos(); // Preenche com todos os eventos novamente
        } else {
             for (Evento evento : resultados) {
        tableModel.addRow(new Object[]{
            evento.getParceiro().getNomeEmpresa(),
            evento.getNome(),
            evento.getHora().toLocalTime(),            
            evento.getHora().plusHours(2).toLocalTime(),
            evento.getData(),
            evento.getPublicoAlvo(),
            evento.getNivelAcessibilidade(),
            evento.getLocalizacao()
        });
    }
        }
    } catch (HeadlessException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar eventos: " + e.getMessage());
    }
});
    }//GEN-LAST:event_txtpesquisarEventoKeyReleased

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (itemSelecionado == -1) {
    JOptionPane.showMessageDialog(null, "Nenhum evento selecionado. Tente novamente.");
    return;
}

Evento eventoExcluir = listaEventos.get(itemSelecionado);

int resposta = JOptionPane.showConfirmDialog(
        this,
        "Deseja realmente excluir o evento:\n" + eventoExcluir.getNome() + "?",
        "Confirmação de exclusão",
        JOptionPane.YES_NO_OPTION
);

// 0 = sim | 1 = não | -1 = nada
if (resposta == JOptionPane.YES_OPTION) {
    try {
        // Remove da lista
        listaEventos.remove(itemSelecionado);
        
        // Remove da tabela
        tableModel.removeRow(itemSelecionado);
        
        // Exclui do banco de dados
        eventoController.delete(eventoExcluir.getId());
        
        resetarSelecao();
        
        JOptionPane.showMessageDialog(null, "Evento excluído com sucesso!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir evento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    
    }
}
    }//GEN-LAST:event_btnRemoverActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmarEvento;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnadicionarEvento;
    private javax.swing.JButton btnconfirmarAlteracoes;
    private javax.swing.JComboBox<String> cbxnivelAcessibilidade;
    private javax.swing.JComboBox<String> cbxnovaAcessibilidade;
    private javax.swing.JFrame jAdicionar;
    private javax.swing.JFrame jEdit;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDatar;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblLocalizacao;
    private javax.swing.JLabel lblParceiro;
    private javax.swing.JLabel lblProgramacao;
    private javax.swing.JLabel lblhorarioInicio;
    private javax.swing.JLabel lblparceiroNome;
    private javax.swing.JLabel lblpublcioAlvo;
    private javax.swing.JTextField novaProgramacao;
    private javax.swing.JTable tblEvenetos;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtLocalizacao;
    private javax.swing.JTextField txtProgramacao;
    private javax.swing.JTextField txtPublicoAlvo;
    private javax.swing.JTextField txtcnpj;
    private javax.swing.JTextField txthorarioInicio;
    private javax.swing.JTextField txtnomeEvento;
    private javax.swing.JTextField txtnovaCategoria;
    private javax.swing.JTextField txtnovaData;
    private javax.swing.JTextField txtnovaDescricao;
    private javax.swing.JTextField txtnovaLocalizacao;
    private javax.swing.JTextField txtnovoEvento;
    private javax.swing.JTextField txtnovoHorario;
    private javax.swing.JTextField txtnovoParceiro;
    private javax.swing.JTextField txtnovopublicoAlvo;
    private javax.swing.JTextField txtpesquisarEvento;
    // End of variables declaration//GEN-END:variables
}
