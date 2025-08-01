/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.pontoturistico.view;

import br.com.ifba.pontoturistico.controller.PontoTuristicoIController;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.util.ButtonRenderer;
import java.awt.Image;
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
public class PontoTuristicoList extends javax.swing.JFrame implements ApplicationContextAware{

    // Adiciona atributos para as classes de buscas
    private List<PontoTuristico> listaDePontos; // guarda a lista de pontos carregada
    @Autowired
    private PontoTuristicoIController pontoTuristicoController;
    private ApplicationContext applicationContext; // VARIÁVEL PARA O CONTEXTO SPRING

    
    /**
     * Creates new form PontoTuristicoList
     */
    @Autowired
    public PontoTuristicoList(PontoTuristicoIController pontoTuristicoController) {
        // inicializa o controller como parametro recebido
        this.pontoTuristicoController = pontoTuristicoController;
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
            ImageIcon refreshIcon = scaleImage("/imagens/refresh.png", iconSize);
            ImageIcon addIcon = scaleImage("/imagens/add.png", iconSize);

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
    
    private ImageIcon scaleImage(String path, int size) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
    
    private void adicionarListenerDeCliqueNaTabela() {
        // Adiciona um "ouvinte" de eventos de mouse à nossa tabela
        this.tblPontosTuristicos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Pega a linha e a coluna exatas onde o clique do mouse ocorreu
                int linha = tblPontosTuristicos.rowAtPoint(evt.getPoint());
                int coluna = tblPontosTuristicos.columnAtPoint(evt.getPoint());

                // Garante que o clique foi em uma linha válida (e não no cabeçalho, por exemplo)
                if (linha >= 0) {

                    // AÇÃO DE EDITAR
                    if (coluna == 5) { // Verifica se o clique foi na 6ª coluna ("Editar")
                        PontoTuristico pontoParaEditar = listaDePontos.get(linha);

                        PontoTuristicoUpdate telaAtualizacao = applicationContext.getBean(PontoTuristicoUpdate.class);
                        telaAtualizacao.setDadosParaEdicao(pontoParaEditar, PontoTuristicoList.this);
                        telaAtualizacao.setVisible(true);
                    } 
                    // AÇÃO DE REMOVER
                    else if (coluna == 6) { // Verifica se o clique foi na 7ª coluna ("Remover")
                        PontoTuristico pontoParaRemover = listaDePontos.get(linha);
                        // Pede confirmação ao usuário antes de remover
                        int resposta = JOptionPane.showConfirmDialog(PontoTuristicoList.this, // Parent component
                                "Deseja realmente remover este ponto turistico: \"" + pontoParaRemover.getNome() + "\"?", 
                                "Confirmar Remoção", 
                                JOptionPane.YES_NO_OPTION);

                        if (resposta == JOptionPane.YES_NO_OPTION) {
                            try {
                                // Usa o ID do objeto identificado corretamente
                                pontoTuristicoController.delete(pontoParaRemover);

                                // Mostra a mensagem de sucesso
                                JOptionPane.showMessageDialog(PontoTuristicoList.this, "Ponto Turistico removido com sucesso!");

                                // ATUALIZA A TABELA DE FORMA SIMPLES E SEGURA
                                carregarDados(); 

                            } 
                            catch (Exception e) {
                                JOptionPane.showMessageDialog(PontoTuristicoList.this, "Erro ao remover curso: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }
    
    // carrega os dados do banco de dados
    public void carregarDados() {
        try {
            // Usa o buscador para obter a lista de pontos turisticos do banco
            this.listaDePontos = pontoTuristicoController.findAll();

            // Pega o modelo da tabela
            DefaultTableModel model = (DefaultTableModel) tblPontosTuristicos.getModel();
            model.setRowCount(0); // Limpa a tabela para evitar duplicatas

            // loop que adiciona cada ponto turistico como uma nova linha
            for (int i = 0; i < listaDePontos.size(); i++) {
    
                // Pega o ponto turistico que está na posição 'i' da lista
                PontoTuristico pontoTuristico = listaDePontos.get(i);
                String horarioFuncionamento = pontoTuristico.getHorarioAbertura() +
                                                    " - " + pontoTuristico.getHorarioFechamento();

                // adiciona linha 
                model.addRow(new Object[]{
                    pontoTuristico.getNome(),
                    pontoTuristico.getDescricao(),
                    pontoTuristico.getLocalizacao(), 
                    horarioFuncionamento,
                    pontoTuristico.getNivelAcessibilidade(),  
                    "Editar",
                    "Remover"
                });
            }
        } 
        catch (Exception e) {
            // Mostra uma mensagem de erro se a conexão com o banco falhar
            JOptionPane.showMessageDialog(this, 
                "Erro ao conectar ao banco de dados para listar os pontos turisticos.\n" +
                "Verifique sua conexão e as configurações de firewall.\n\n" +
                "Detalhes do erro: " + e.getMessage(),
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Imprime o erro detalhado no console para depuração
        }
    }
    
    // Usando o índice da coluna!
    private void configurarTabela() {
        // Define a altura da linha para 40 pixels. Ajuste conforme necessário.
        tblPontosTuristicos.setRowHeight(32);
        
        // Pega a coluna na posição 5 (a sexta coluna)
        tblPontosTuristicos.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        // Pega a coluna na posição 6 (a setima coluna)
        tblPontosTuristicos.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
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
        tblPontosTuristicos = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();

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

        tblPontosTuristicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Descrição", "Localização", "Horario de Funcionamento", "Nivel de Acessibilidade", "Editar", "Remover"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPontosTuristicos.setToolTipText("");
        jScrollPane1.setViewportView(tblPontosTuristicos);

        btnRefresh.setText("ref");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdiciona))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdiciona)
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addGap(13, 13, 13)
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
        String nomePesquisa = txtPesquisar.getText();

        // Verifica se o campo de pesquisa está vazio
        if (nomePesquisa.isBlank()) {
            // Se estiver vazio, simplesmente recarrega todos os dados da tabela
            this.carregarDados();
            return;
        }

        try {
            // Usa o buscadorNome para consultar o banco com o texto digitado
            this.listaDePontos = pontoTuristicoController.findByNomeStartingWithIgnoreCase(nomePesquisa);

            // Pega o modelo da tabela
            DefaultTableModel model = (DefaultTableModel) tblPontosTuristicos.getModel();
            model.setRowCount(0); // Limpa a tabela para exibir apenas os resultados da pesquisa

            // Se a busca não retornar resultados, exibe uma mensagem
            if (listaDePontos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum curso encontrado com o nome informado.",
                    "Pesquisa",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                // Preenche a tabela com os cursos encontrados na pesquisa
                for (int i = 0; i < listaDePontos.size(); i++) {

                    // Pega o ponto turistico que está na posição 'i' da lista
                    PontoTuristico pontoTuristico = listaDePontos.get(i);
                    String horarioFuncionamento = pontoTuristico.getHorarioAbertura() +
                                                    " - " + pontoTuristico.getHorarioFechamento();

                    // adiciona linha
                    model.addRow(new Object[]{
                        pontoTuristico.getNome(),
                        pontoTuristico.getDescricao(),
                        pontoTuristico.getLocalizacao(),  
                        horarioFuncionamento,
                        pontoTuristico.getNivelAcessibilidade(),
                        "Editar",
                        "Remover"
                    });
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Ocorreu um erro ao realizar a busca.\nDetalhes: " + e.getMessage(),
                "Erro de Pesquisa",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void btnAdicionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionaActionPerformed
        // TODO add your handling code here:
        PontoTuristicoSave telaPontoTuristicoSave = applicationContext.getBean(PontoTuristicoSave.class);
        telaPontoTuristicoSave.setPontoTuristicoList(this); // Configura a referência para poder atualizar a tabela
        telaPontoTuristicoSave.setVisible(true);
    }//GEN-LAST:event_btnAdicionaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        carregarDados();
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdiciona;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPontosTuristicos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
