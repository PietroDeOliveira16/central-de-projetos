package com.sesi.projetos.util;

public class ValidadorDeCpf {
    private static int sequenciaMultiplicativa = 0;

    /**
     * Valida uma string de um cpf contendo apenas números
     * @param cpf String
     * @return true se o cpf for válido - false se o cpf for inválido
     */
    public static boolean validarCpf(String cpf){
        cpf = cpf.replaceAll("[.-]", "");

        boolean isCaracteresRepetidos = cpf.chars().distinct().count() == 1;
        if(isCaracteresRepetidos){
            return false;
        }

        if (cpf.length() != 11){
            return false;
        }

        // Converte o cpf para números
        int[] digitosAntes = new int[9];
        int[] ultimosDigitos = new int[2];
        for (int i = 0; i < cpf.length(); i++) {
            if (i <= 8){
                digitosAntes[i] = Character.getNumericValue(cpf.charAt(i));
            } else {
                ultimosDigitos[i - 9] = Character.getNumericValue(cpf.charAt(i));
            }
        }

        // Validação do primeiro digito após o '-'
        sequenciaMultiplicativa = 10;
        int resultadoDigitosAntes = multiplicarNumerosPorSequencia(digitosAntes);
        int restoDivisaoDigitosAntes = 0;

        resultadoDigitosAntes *= 10;
        restoDivisaoDigitosAntes = resultadoDigitosAntes % 11;
        if(restoDivisaoDigitosAntes == 10){
            restoDivisaoDigitosAntes = 0;
        }

        // Validação do segundo digito após o '-'
        sequenciaMultiplicativa = 11;
        int resultadosUltimosDigitos = multiplicarNumerosPorSequencia(digitosAntes);
        resultadosUltimosDigitos += ultimosDigitos[0] * sequenciaMultiplicativa;

        resultadosUltimosDigitos *= 10;
        int restoDivisaoUltimosDigitos = 0;
        restoDivisaoUltimosDigitos = resultadosUltimosDigitos % 11;
        if(restoDivisaoUltimosDigitos == 10){
            restoDivisaoUltimosDigitos = 0;
        }

        if (ultimosDigitos[0] == restoDivisaoDigitosAntes && ultimosDigitos[1] == restoDivisaoUltimosDigitos){
             return true;
        }

        return false;
    }

    private static int multiplicarNumerosPorSequencia(int[] digitos){
        int resultado = 0;
        for(int digito : digitos){
            resultado += digito * sequenciaMultiplicativa;
            sequenciaMultiplicativa--;
        }
        return resultado;
    }
}
