package util;
import exceptions.ValidarException;

public class Validar {
    public static boolean validarCPF(String cpf) {
        // Removendo caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificando o comprimento do CPF
        if (cpf.length() != 11) {
            return false;
        }

        // Verificando se todos os dígitos são iguais (CPF inválido)
        boolean todosDigitosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
            return false;
        }

        // Verificando os dígitos verificadores
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }

        // Primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += digitos[i] * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;
        if (digitos[9] != digitoVerificador1) {
            return false;
        }

        // Segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += digitos[i] * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;
        if (digitos[10] != digitoVerificador2) {
            return false;
        }

        // CPF válido
        return true;
    }

    public static boolean validarCNPJ(String cnpj) {
        // Removendo caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verificando o comprimento do CNPJ
        if (cnpj.length() != 14) {
            return false;
        }

        // Verificando se todos os dígitos são iguais (CNPJ inválido)
        boolean todosDigitosIguais = true;
        for (int i = 1; i < 14; i++) {
            if (cnpj.charAt(i) != cnpj.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
            return false;
        }

        // Verificando os dígitos verificadores
        int[] digitos = new int[14];
        for (int i = 0; i < 14; i++) {
            digitos[i] = Character.getNumericValue(cnpj.charAt(i));
        }

        // Primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }
        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;
        if (digitos[12] != digitoVerificador1) {
            return false;
        }

        // Segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }
        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;
        if (digitos[13] != digitoVerificador2) {
            return false;
        }

        // CNPJ válido
        return true;
    }

    public static String validarRG(String rg) {
        try {
            if (rg == null) {
                throw new NullPointerException("O valor de entrada não pode ser vázio!");
            }

            rg = rg.replaceAll("[^0-9X]", "");

            if (rg.length() != 9) {
                throw new ValidarException("O valor de entrada não é válido!");
            }

            int result = 0;
            int[] pesos = {2, 3, 4, 5, 6, 7, 8, 9};

            for (int i = 0; i < pesos.length; i++) {
                result += pesos[i] * Integer.parseInt(String.valueOf(rg.charAt(i)));
            }

            result = (11 - (result % 11)) == 11 ? 0 : (11 - (result % 11));

            if (rg.contains("X")) {
                if (result == 10)
                    return rg;
                else
                    throw new ValidarException("O RG inserido não é válido!");
            }
            else {
                if (result == Integer.parseInt(String.valueOf(rg.charAt(8))))
                    return rg;
                else
                    throw new ValidarException("O RG inserido não é válido!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
