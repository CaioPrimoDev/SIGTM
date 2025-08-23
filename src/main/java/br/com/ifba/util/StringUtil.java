/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

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
    
    // Verifica se um e-mail é válido
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
    
    // Verifica se o CPF é válido
    public static boolean isCpfValido(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}") || cpf.chars().distinct().count() == 1)
            return false;

        try {
            int d1 = 0, d2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                d1 += digito * (10 - i);
                d2 += digito * (11 - i);
            }

            int check1 = 11 - (d1 % 11);
            check1 = (check1 > 9) ? 0 : check1;
            d2 += check1 * 2;
            int check2 = 11 - (d2 % 11);
            check2 = (check2 > 9) ? 0 : check2;

            return check1 == Character.getNumericValue(cpf.charAt(9)) &&
                   check2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
    
    // Verifica se o CNPJ é válido
    public static boolean isCnpjValido(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}") || cnpj.chars().distinct().count() == 1)
            return false;

        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma1 = 0;
            for (int i = 0; i < 12; i++) {
                soma1 += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
            }
            int check1 = 11 - (soma1 % 11);
            check1 = (check1 > 9) ? 0 : check1;

            int soma2 = 0;
            for (int i = 0; i < 13; i++) {
                soma2 += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
            }
            int check2 = 11 - (soma2 % 11);
            check2 = (check2 > 9) ? 0 : check2;

            return check1 == Character.getNumericValue(cnpj.charAt(12)) &&
                   check2 == Character.getNumericValue(cnpj.charAt(13));
        } catch (Exception e) {
            return false;
        }
    }
    
    // Chama as validações de CPF e CNPJ
    public static boolean isCpfOuCnpjValido(String valor) {
        if (valor == null) return false;
        valor = valor.replaceAll("[^\\d]", ""); // remove pontos e traços
        return valor.length() == 11 ? isCpfValido(valor)
             : valor.length() == 14 ? isCnpjValido(valor)
             : false;
    }
    
    public static boolean isValidHorario(String horario) {
        if (horario == null) {
            return false;
        }

        // Define o formato estrito "HH:mm" (horas 00-23, minutos 00-59)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
                                                     .withResolverStyle(ResolverStyle.STRICT);

        try {
            // Se o parse funciona, o formato e os valores são válidos.
            LocalTime.parse(horario, formatter);
            return true;
        } catch (DateTimeParseException e) {
            // A string não corresponde ao formato esperado.
            return false;
        }
    }
    
    /*
        Valida se uma string está no formato de data "dd/MM/yyyy" e se a data é válida.
        Ex: "29/02/2024" é válido, mas "29/02/2025" (não bissexto) não é.
        Também rejeita datas como "31/04/2025".
    */
    public static boolean isValidData(String data) {
        if (data == null) {
            return false;
        }

        // Define o formato estrito "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                                     .withResolverStyle(ResolverStyle.STRICT);

        try {
            // Tenta fazer o parse. Se o comando for executado com sucesso, a data é válida.
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            // Se der erro no parse, significa que o formato ou a data são inválidos.
            return false;
        }
    }
}
