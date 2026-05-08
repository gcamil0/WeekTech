package com.unicesumar.techweek.util;

import java.util.regex.Pattern;

/**
 * Classe utilitária com validações reutilizáveis em todo o app.
 * Centraliza RegEx e lógica de negócio de validação.
 */
public class Validators {

    // RA: 8 dígitos numéricos (padrão UniCesumar)
    private static final Pattern RA_PATTERN = Pattern.compile("^\\d{8}$");

    // Código de check-in: formato TW<ano>-<nn>
    private static final Pattern CHECKIN_PATTERN = Pattern.compile("^TW\\d{4}-\\d{2}$");

    public static boolean raValido(String ra) {
        if (ra == null || ra.trim().isEmpty()) return false;
        return RA_PATTERN.matcher(ra.trim()).matches();
    }

    public static boolean nomeValido(String nome) {
        return nome != null && nome.trim().length() >= 3;
    }

    public static boolean codigoCheckinValido(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) return false;
        return CHECKIN_PATTERN.matcher(codigo.trim().toUpperCase()).matches();
    }

    public static boolean campoVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }
}
