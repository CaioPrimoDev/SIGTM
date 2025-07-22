/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.util;

/**
 *
 * @author User
 */
public class StringUtil {
    // Verifica se uma string está nula ou vazia
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Valida se uma string contém apenas letras
    public static boolean isOnlyLetters(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    // Valida se uma string contém apenas números
    public static boolean isOnlyNumbers(String str) {
        return str != null && str.matches("\\d+");
    }

    // Valida se uma string contém apenas letras e números
    public static boolean isAlphanumeric(String str) {
        return str != null && str.matches("[a-zA-Z0-9]+");
    }

    // Verifica se uma string está dentro de um tamanho mínimo e máximo
    public static boolean hasValidLength(String str, int min, int max) {
        if (str == null) return false;
        int length = str.length();
        return length >= min && length <= max;
    }

    // Remove espaços extras de uma string
    public static String trimExtraSpaces(String str) {
        if (str == null) return null;
        return str.trim().replaceAll("\\s+", " ");
    }

    // Verifica se uma string contém pelo menos um número
    public static boolean containsNumber(String str) {
        return str != null && str.matches(".*\\d.*");
    }

    // Verifica se uma string contém pelo menos um caractere especial
    public static boolean containsSpecialCharacter(String str) {
        return str != null && str.matches(".*[^a-zA-Z0-9].*");
    }

}
