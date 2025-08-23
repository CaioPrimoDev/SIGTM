/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.reserva.view;

import br.com.ifba.evento.view.EventoListar;
import br.com.ifba.pontoturistico.view.PontoTuristicoList;
import br.com.ifba.reserva.controller.ReservaIController;
import br.com.ifba.reserva.entity.Reserva;
import br.com.ifba.reserva.util.ReservaButtonRenderer;
import br.com.ifba.sessao.UsuarioSession;
import br.com.ifba.telainicial.view.TelaInicial;
import br.com.ifba.util.ButtonRenderer;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author juant
 */
@Component
public class ReservaList extends javax.swing.JFrame implements ApplicationContextAware{

    // Adiciona atributos para as classes de buscas
    private List<Reserva> listaDeReservas; // guarda a lista de reservas carregada
    private UsuarioSession userLogado;
    
    @Autowired
    private ReservaIController reservaController;
    
    private ApplicationContext applicationContext; // VARIÁVEL PARA O CONTEXTO SPRING
 
    /**
     * Creates new form PontoTuristicoList
     */
    @Autowired
    public ReservaList(ReservaIController reservaController, UsuarioSession userLogado) {
        // inicializa o controller como parametro recebido
        this.reservaController = reservaController;
        this.userLogado = userLogado;
        initComponents();
        
        carregarDados(); // carrega os dados na tabela
        // Define um tamanho fixo 
        this.setSize(1000, 400); 

        // Impede que o usuário redimensione a janela
        this.setResizable(false);
        setLocationRelativeTo(null); // inicializa o jframe no meio da tela
        configurarTabela(); // Novo método para organizar
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
        
        try {
            // Define o tamanho desejado para o ícone da lupa
            int iconSize = 20; // Ajuste o tamanho conforme o botão

            // Usa um método para carregar e redimensionar a imagem dos botões
            ImageIcon refreshIcon = ButtonRenderer.scaleImage("/imagens/refresh.png", iconSize);
            ImageIcon addIcon = ButtonRenderer.scaleImage("/imagens/add.png", iconSize);

            // Aplica o ícone ao botão
            btnRefresh.setIcon(refreshIcon);
            btnAdiciona.setIcon(addIcon);

            // remove o texto do botão para exibir apenas o ícone
            btnRefresh.setText("");
            btnAdiciona.setText("");

        } 
        catch (Exception e) {
            // Tratamento de erro caso a imagem não seja encontrada
            System.err.println("Erro ao carregar ícone de pesquisa: " + e.getMessage());
            btnRefresh.setText("Buscar"); // Garante que o botão tenha um texto se o ícone falhar
            btnAdiciona.setText("Adicionar"); // Garante que o botão tenha um texto se o ícone falhar
        }
        
        adicionarListenerDeCliqueNaTabela();
    }
    
    private void adicionarListenerDeCliqueNaTabela() {
        // Adiciona um "ouvinte" de eventos de mouse à tabela
        this.tblReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Pega a linha e a coluna exatas onde o clique do mouse ocorreu
                int linha = tblReservas.rowAtPoint(evt.getPoint());
                int coluna = tblReservas.columnAtPoint(evt.getPoint());

                // Garante que o clique foi em uma linha válida (e não no cabeçalho, por exemplo)
                if (linha >= 0) {
                                       
                    // AÇÃO DE MOSTRAR MAIS INFORMAÇÕES (TODAS)
                    if (coluna == 2) { // Verifica se o clique foi na 3ª coluna ("Mais informações")  
                        // Pega o objeto Reserva da linha clicada
                        Reserva reservaSelecionada = listaDeReservas.get(linha);

                        // exibe todas as informações da reserva
                        JOptionPane.showMessageDialog(ReservaList.this, 
                                reservaSelecionada, 
                                "Detalhes da reserva", JOptionPane.INFORMATION_MESSAGE);
                    }
                    // AÇÃO DE DESMARCAR
                    else if (coluna == 3) { // Verifica se o clique foi na 3ª coluna ("Remover")                       
                        Reserva reservaParaRemover = listaDeReservas.get(linha);
                        // Pede confirmação ao usuário antes de desmarcar (remover)
                        int resposta = JOptionPane.showConfirmDialog(ReservaList.this, // Parent component
                                "Deseja realmente desmarcar a reserva de token: \"" + reservaParaRemover.getToken() + "\"?", 
                                "Confirmar Remoção", 
                                JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_NO_OPTION) {
                            try {
                                // Usa o ID do objeto identificado corretamente
                                reservaController.delete(reservaParaRemover);
                                
                                // Mostra a mensagem de sucesso
                                JOptionPane.showMessageDialog(ReservaList.this, "Reserva desmarcada com sucesso!");

                                // ATUALIZA A TABELA DE FORMA SIMPLES E SEGURA
                                carregarDados(); 

                            } 
                            catch (Exception e) {
                                JOptionPane.showMessageDialog(ReservaList.this, "Erro ao desmarcar reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
    }
    
    // carrega os dados do banco de dados
    public void carregarDados() {
        // Verifica se um usuário está realmente logado
        if (!userLogado.isLogado()) {
            return; // Interrompe a execução do método
        }

        try {
            this.listaDeReservas = this.reservaController.findAll(); 
            
            // Pega o modelo da tabela
            DefaultTableModel model = (DefaultTableModel) tblReservas.getModel();
            model.setRowCount(0); // Limpa a tabela para evitar duplicatas
            
            // Cria um formatador para a data ficar mais legível (ex: 22/08/2025)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // loop que adiciona cada reserva como uma nova linha
            for (int i = 0; i < listaDeReservas.size(); i++) {
    
                // Pega a reserva que está na posição 'i' da lista
                Reserva reserva = listaDeReservas.get(i);

                // adiciona linha 
                model.addRow(new Object[]{
                    reserva.getToken(),
                    reserva.getDataReserva().format(formatter),
                    "Mais informações",
                    "Desmarcar"
                });
            }
        } 
        catch (Exception e) {
            // Mostra uma mensagem de erro se a conexão com o banco falhar
            JOptionPane.showMessageDialog(this, 
                "Erro ao conectar ao banco de dados para listar as reservas.\n" +
                "Verifique sua conexão e as configurações de firewall.\n\n" +
                "Detalhes do erro: " + e.getMessage(),
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Usando o índice da coluna!
    private void configurarTabela() {
        // Define a altura da linha para 40 pixels. Ajuste conforme necessário.
        tblReservas.setRowHeight(32);

        // Pega a coluna na posição 2 (a terceira coluna)
        tblReservas.getColumnModel().getColumn(2).setCellRenderer(new ReservaButtonRenderer());
                // Pega a coluna na posição 3 (a quarta coluna)
        tblReservas.getColumnModel().getColumn(3).setCellRenderer(new ReservaButtonRenderer());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisar = new javax.swing.JTextField();
        btnAdiciona = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnTelaInicial = new javax.swing.JButton();
        btnEventos = new javax.swing.JButton();
        btnPontosTuristicos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPesquisar.setToolTipText("Pesquisar...");
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        btnAdiciona.setText("adc");
        btnAdiciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionaActionPerformed(evt);
            }
        });

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Data", "Mais informações", "Desmarcar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReservas.setToolTipText("");
        jScrollPane1.setViewportView(tblReservas);

        btnRefresh.setText("ref");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnTelaInicial.setText("Tela Inicial");
        btnTelaInicial.setMaximumSize(new java.awt.Dimension(76, 27));
        btnTelaInicial.setMinimumSize(new java.awt.Dimension(76, 27));
        btnTelaInicial.setPreferredSize(new java.awt.Dimension(76, 27));
        btnTelaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTelaInicialActionPerformed(evt);
            }
        });

        btnEventos.setText("Eventos");
        btnEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEventosActionPerformed(evt);
            }
        });

        btnPontosTuristicos.setText("Pontos Turisticos");
        btnPontosTuristicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPontosTuristicosActionPerformed(evt);
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
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh)
                        .addGap(18, 18, 18)
                        .addComponent(btnTelaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btnEventos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPontosTuristicos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdiciona))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTelaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh)
                            .addComponent(btnEventos)
                            .addComponent(btnPontosTuristicos)
                            .addComponent(btnAdiciona))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
        // Pega o texto que o usuário digitou no campo de pesquisa
        String tokenPesquisa = txtPesquisar.getText();

        // Verifica se o campo de pesquisa está vazio
        if (tokenPesquisa.isBlank()) {
            // Se estiver vazio, simplesmente recarrega todos os dados da tabela
            this.carregarDados();
            return;
        }

        try {
            // Usa o buscadorNome para consultar o banco com o texto digitado
            this.listaDeReservas = reservaController.findByTokenContainingIgnoreCase(tokenPesquisa);

            // Pega o modelo da tabela
            DefaultTableModel model = (DefaultTableModel) tblReservas.getModel();
            model.setRowCount(0); // Limpa a tabela para exibir apenas os resultados da pesquisa

            // Se a busca não retornar resultados, exibe uma mensagem
            if (listaDeReservas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada com o token informado.",
                    "Pesquisa",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                // Preenche a tabela com os cursos encontrados na pesquisa
                for (int i = 0; i < listaDeReservas.size(); i++) {

                    // Pega o ponto turistico que está na posição 'i' da lista
                    Reserva reserva = listaDeReservas.get(i);
                    
                    // Cria um formatador para a data ficar mais legível (ex: 22/08/2025)
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    // adiciona linha
                    model.addRow(new Object[]{
                        reserva.getToken(),
                        reserva.getDataReserva().format(formatter),
                        "Mais informações",
                        "Desmarcar"
                    });
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Ocorreu um erro ao realizar a busca.\nDetalhes: " + e.getMessage(),
                "Erro de Pesquisa",
                JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_txtPesquisarKeyReleased
    
    
    private void btnAdicionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionaActionPerformed
        // TODO add your handling code here:       
        // Verifica se existe um usuário logado na sessão
        if (userLogado.isLogado()) {
            try {
                // abre a tela de cadastro de reserva
                ReservaSave telaReservaSave = applicationContext.getBean(ReservaSave.class);

                // Passa a referência desta tela para a tela de salvar,
                // para que a tabela possa ser atualizada após o salvamento.
                telaReservaSave.setReservaList(this);

                telaReservaSave.setVisible(true);

            } catch (Exception e) {
                // Mostra uma mensagem de erro genérica caso algo dê errado ao abrir a tela
                JOptionPane.showMessageDialog(this, 
                    "Ocorreu um erro inesperado. Por favor, tente novamente.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // 4. Se não estiver logado, exibe um aviso
            JOptionPane.showMessageDialog(this, 
                "Você precisa estar logado para fazer uma reserva.", 
                "Acesso Negado", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    
    }//GEN-LAST:event_btnAdicionaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        carregarDados();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnTelaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTelaInicialActionPerformed
        // TODO add your handling code here:
        TelaInicial telaInicial = applicationContext.getBean(TelaInicial.class);
        telaInicial.setVisible(true);
        this.dispose(); // <--- É esta linha que fecha a janela!
    }//GEN-LAST:event_btnTelaInicialActionPerformed

    private void btnPontosTuristicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPontosTuristicosActionPerformed
        // TODO add your handling code here:
        PontoTuristicoList telaPontoTuristicoList = applicationContext.getBean(PontoTuristicoList.class);
        telaPontoTuristicoList.setVisible(true);
    }//GEN-LAST:event_btnPontosTuristicosActionPerformed

    private void btnEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEventosActionPerformed
        // TODO add your handling code here:
        EventoListar crudEventos = applicationContext.getBean(EventoListar.class);
        crudEventos.setVisible(true);
    }//GEN-LAST:event_btnEventosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdiciona;
    private javax.swing.JButton btnEventos;
    private javax.swing.JButton btnPontosTuristicos;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTelaInicial;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReservas;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
