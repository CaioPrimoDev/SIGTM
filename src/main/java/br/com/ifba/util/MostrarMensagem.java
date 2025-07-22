/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class MostrarMensagem {
    public static void info(Component parent, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(parent, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void erro(Component parent, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(parent, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void alerta(Component parent, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(parent, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirmar(Component parent, String mensagem, String titulo) {
        int resposta = JOptionPane.showConfirmDialog(parent, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    } 
}
