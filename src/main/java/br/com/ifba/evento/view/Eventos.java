/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.evento.view;

import br.com.ifba.evento.controller.EventoIController;
import br.com.ifba.evento.entity.Evento;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Casa
 */

//AREA DE VIZUALIZAÇÃO DO PARCEIRO
@Component
public class Eventos extends javax.swing.JFrame {

    /**
     * Creates new form Evento
     */
    @Autowired
    public Eventos(EventoIController eventoController) {
        this.eventoController = eventoController;
        tableModel = (DefaultTableModel) tblEvenetos.getModel();
        initComponents();
    }


    @Autowired
    EventoIController eventoController;
    
   final DefaultTableModel tableModel;
   List<Evento> listaEventos = new ArrayList();
   
   int itemSelecionado = -1;
   
   
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
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEvenetos = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEvenetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Empresa responsável", "Evento", "Hora de início", "Hora de término", "Data", "Publico alvo", "Nivel de acessibilidade"
            }
        ));
        jScrollPane1.setViewportView(tblEvenetos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblEvenetos;
    // End of variables declaration//GEN-END:variables
}
