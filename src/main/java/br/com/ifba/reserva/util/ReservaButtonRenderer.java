/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.reserva.util;

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

public class ReservaButtonRenderer extends JButton implements TableCellRenderer {

    private ImageIcon deleteIcon;
    private ImageIcon infoIcon;

    public ReservaButtonRenderer() {
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
            deleteIcon = ButtonRenderer.scaleImage("/imagens/delete.png", iconSize);
            infoIcon = ButtonRenderer.scaleImage("/imagens/info.png", iconSize);
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

        if (column == 2) { // Coluna Info
            setIcon(infoIcon);
        }
        else if (column == 3) { // Coluna Remover
            setIcon(deleteIcon);
        }
        
        // Remove o texto para exibir apenas o ícone
        setText("");

        return this;
    }
}