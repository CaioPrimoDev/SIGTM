/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.pontoturistico.view;

import br.com.ifba.pontoturistico.controller.PontoTuristicoIController;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.util.StringUtil;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author juant
 */
@Component
@Scope("prototype") // Garante que cada clique em "Adicionar" crie uma janela nova
public class PontoTuristicoSave extends javax.swing.JFrame {

    @Autowired
    private PontoTuristicoIController pontoTuristicoController;
    private PontoTuristicoList pontoTuristicoList;
    
    /**
     * Creates new form PontoTuristicoSave
     */
    public PontoTuristicoSave() {
        initComponents();
        
        setLocationRelativeTo(null); // inicializa o jframe no meio da tela
        // Impede que o usuário redimensione a janela
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
    }
    
    // Ele será chamado pela PontoTuristicoList_OLD para "entregar" a referência dela mesma.
    public void setPontoTuristicoList(PontoTuristicoList pontoTuristicoList) {
        this.pontoTuristicoList = pontoTuristicoList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pontoTuristico1 = new br.com.ifba.pontoturistico.entity.PontoTuristico();
        txtNome4 = new javax.swing.JTextField();
        lblLocalizacao1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblHorarioAber = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        txtDescricao = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        lblDescricao = new javax.swing.JLabel();
        txtHorarioAber = new javax.swing.JTextField();
        txtHorarioFecha = new javax.swing.JTextField();
        lblHorarioFecha = new javax.swing.JLabel();
        lblInfoHorario = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtLocalizacao = new javax.swing.JTextField();
        lblLocalizacao = new javax.swing.JLabel();
        lblLocalizacao2 = new javax.swing.JLabel();
        sliNivelAcess = new javax.swing.JSlider();

        txtNome4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblLocalizacao1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLocalizacao1.setText("LOCALIZAÇÃO: (RUA - BAIRRO) OU (PONTO DE REFERÊNCIA)");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNome.setText("NOME:");

        lblHorarioAber.setText("HORARIO DE ABERTURA");

        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrar.setText("CADASTRAR");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInfo.setText("CADASTRO DE PONTOS TURISTICOS");

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtDescricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDescricao.setText("DESCRIÇÃO:");

        txtHorarioAber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHorarioFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblHorarioFecha.setText("HORARIO DE FECHAMENTO");

        lblInfoHorario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblInfoHorario.setText("HORÁRIO DE FUNCIONAMENTO: (hh:mm)");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtLocalizacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblLocalizacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLocalizacao.setText("LOCALIZAÇÃO: (RUA - BAIRRO) OU (PONTO DE REFERÊNCIA)");

        lblLocalizacao2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLocalizacao2.setText("NIVEL DE ACESSIBILIDADE: (0 A 5)");

        sliNivelAcess.setMaximum(5);
        sliNivelAcess.setValue(2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome)
                    .addComponent(lblDescricao)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLocalizacao)
                    .addComponent(lblLocalizacao2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtHorarioAber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblHorarioAber))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHorarioFecha)
                                .addComponent(txtHorarioFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblInfoHorario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sliNivelAcess, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLocalizacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)))
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(btnCadastrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(lblInfo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblInfo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLocalizacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblLocalizacao2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sliNivelAcess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblInfoHorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHorarioAber)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHorarioAber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHorarioFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHorarioFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadastrar)
                    .addComponent(btnCancelar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        // TODO add your handling code here:
        try {
            if (txtNome.getText().equals("") || 
                        txtDescricao.getText().equals("") || 
                        txtLocalizacao.getText().equals("") ||
                        txtHorarioAber.getText().equals("") ||
                        txtHorarioFecha.getText().equals("")) {
                JOptionPane.showMessageDialog(
                    null, "Existem campos vazios ou invalido(s), preencha todos e tente novamente!",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            }
            // Valida o formato do horário de abertura
            if (!StringUtil.isValidHorario(txtHorarioAber.getText())) {
                 JOptionPane.showMessageDialog(this, "Formato do horário de abertura inválido. Use HH:mm (ex: 08:00).", 
                                               "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                 return; // Para a execução
            }
            
            // Valida o formato do horário de fechamento
            if (!StringUtil.isValidHorario(txtHorarioFecha.getText())) {
                 JOptionPane.showMessageDialog(this, "Formato do horário de fechamento inválido. Use HH:mm (ex: 18:00).", 
                                               "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            else{
                // Cria o objeto Curso com os dados da tela
                PontoTuristico novoPonto = new PontoTuristico();
                novoPonto.setNome(txtNome.getText());
                novoPonto.setDescricao(txtDescricao.getText());
                novoPonto.setLocalizacao(txtLocalizacao.getText());
                novoPonto.setNivelAcessibilidade(sliNivelAcess.getValue());
                novoPonto.setHorarioAbertura(txtHorarioAber.getText());
                novoPonto.setHorarioFechamento(txtHorarioFecha.getText());

                // salva o objeto no banco
                pontoTuristicoController.save(novoPonto);

                // Exibe uma mensagem de sucesso para o usuário
                JOptionPane.showMessageDialog(this, "Ponto Turistico cadastrado com sucesso!",
                                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                // possibilita a atualização instantanea da tabela da tela inicial
                this.pontoTuristicoList.carregarDados();
                
                // FECHA A TELA ATUAL (A TELA DE CADASTRO)
                this.dispose(); // <--- É esta linha que fecha a janela!
            }
        }
        catch (Exception e){
            // Em caso de erro, exibe uma mensagem e NÃO fecha a tela
            JOptionPane.showMessageDialog(this,
                "Ocorreu um erro ao cadastrar: " +
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        // FECHA A TELA ATUAL (A TELA DE CADASTRO)
        this.dispose(); // <--- É esta linha que fecha a janela!
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblHorarioAber;
    private javax.swing.JLabel lblHorarioFecha;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblInfoHorario;
    private javax.swing.JLabel lblLocalizacao;
    private javax.swing.JLabel lblLocalizacao1;
    private javax.swing.JLabel lblLocalizacao2;
    private javax.swing.JLabel lblNome;
    private br.com.ifba.pontoturistico.entity.PontoTuristico pontoTuristico1;
    private javax.swing.JSlider sliNivelAcess;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtHorarioAber;
    private javax.swing.JTextField txtHorarioFecha;
    private javax.swing.JTextField txtLocalizacao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNome4;
    // End of variables declaration//GEN-END:variables
}
