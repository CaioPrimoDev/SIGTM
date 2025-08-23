/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pontoturistico.util;

import br.com.ifba.util.ButtonRenderer;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author juant
 */

public class PontoButtonRenderer extends JButton implements TableCellRenderer {

    private ImageIcon editIcon;
    private ImageIcon deleteIcon;
    private ImageIcon infoIcon;
    private ImageIcon comentIcon;

    public PontoButtonRenderer() {
        setOpaque(true);
        
        // --- CÓDIGO PARA DEIXAR O BOTÃO TRANSPARENTE ---
        setContentAreaFilled(false); // 1. Não pinta a área de conteúdo (o fundo)
        setBorderPainted(false);     // 2. Não pinta a borda
        setFocusPainted(false);      // 3. (Opcional) Não pinta o efeito de foco ao clicar


        // Define o tamanho desejado para os ícones (ex: 32x32 pixels)
        // Deve ser um pouco menor que a altura da linha para ter uma margem.
        int iconSize = 18;

        try {
            // Carrega e redimensiona as imagens usando um método auxiliar
            editIcon = ButtonRenderer.scaleImage("/imagens/update.png", iconSize);
            deleteIcon = ButtonRenderer.scaleImage("/imagens/delete.png", iconSize);
            infoIcon = ButtonRenderer.scaleImage("/imagens/info.png", iconSize);
            comentIcon = ButtonRenderer.scaleImage("/imagens/comentIcon.png", iconSize);
        } catch (Exception e) {
            System.err.println("Erro ao carregar ou redimensionar ícones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        // Usa a cor de fundo padrão da tabela ou a de seleção
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } 
        else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }

        if (column == 4) { // Coluna Comentarios
            setIcon(comentIcon);
        }
        else if (column == 5) { // Coluna Info
            setIcon(infoIcon);
        }
        else if (column == 6) { // Coluna Editar
            setIcon(editIcon);
        } 
        else if (column == 7) { // Coluna Remover
            setIcon(deleteIcon);
        }
        
        // Remove o texto para exibir apenas o ícone
        setText("");

        return this;
    }
}