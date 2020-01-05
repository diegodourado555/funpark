package br.com.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";
    
    public static final String CONTA_CORRENTE = "ROLE_CONTA_CORRENTE";
    public static final String CONTA_CORRENTE_VIEW = "ROLE_CONTA_CORRENTE_VIEW";
    public static final String CONTA_CORRENTE_NEW = "ROLE_CONTA_CORRENTE_NEW";
    public static final String CONTA_CORRENTE_EDIT = "ROLE_CONTA_CORRENTE_EDIT";
    public static final String CONTA_CORRENTE_DELETE = "ROLE_CONTA_CORRENTE_DELETE";
    
    public static final String GRUPO_MAQUINA = "ROLE_GRUPO_MAQUINA";
    public static final String GRUPO_MAQUINA_VIEW = "ROLE_GRUPO_MAQUINA_VIEW";
    public static final String GRUPO_MAQUINA_NEW = "ROLE_GRUPO_MAQUINA_NEW";
    public static final String GRUPO_MAQUINA_EDIT = "ROLE_GRUPO_MAQUINA_EDIT";
    public static final String GRUPO_MAQUINA_DELETE = "ROLE_GRUPO_MAQUINA_DELETE";
    
    public static final String MAQUINA = "ROLE_MAQUINA";
    public static final String MAQUINA_VIEW = "ROLE_MAQUINA_VIEW";
    public static final String MAQUINA_NEW = "ROLE_MAQUINA_NEW";
    public static final String MAQUINA_EDIT = "ROLE_MAQUINA_EDIT";
    public static final String MAQUINA_DELETE = "ROLE_MAQUINA_DELETE";
    
    public static final String RECEITAS = "ROLE_RECEITAS";
    public static final String RECEITAS_VIEW = "ROLE_RECEITAS_VIEW";
    public static final String RECEITAS_NEW = "ROLE_RECEITAS_NEW";
    public static final String RECEITAS_EDIT = "ROLE_RECEITAS_EDIT";
    public static final String RECEITAS_DELETE = "ROLE_RECEITAS_DELETE";
    
    public static final String DESPESAS = "ROLE_DESPESAS";
    public static final String DESPESAS_VIEW = "ROLE_DESPESAS_VIEW";
    public static final String DESPESAS_NEW = "ROLE_DESPESAS_NEW";
    public static final String DESPESAS_EDIT = "ROLE_DESPESAS_EDIT";
    public static final String DESPESAS_DELETE = "ROLE_DESPESAS_DELETE";
    
    public static final String LOJA = "ROLE_LOJA";
    public static final String LOJA_VIEW = "ROLE_LOJA_VIEW";
    public static final String LOJA_NEW = "ROLE_LOJA_NEW";
    public static final String LOJA_EDIT = "ROLE_LOJA_EDIT";
    public static final String LOJA_DELETE = "ROLE_LOJA_DELETE";
    
    public static final String LOJA_MAQUINA = "ROLE_LOJA_MAQUINA";
    public static final String LOJA_MAQUINA_VIEW = "ROLE_LOJA_MAQUINA_VIEW";
    public static final String LOJA_MAQUINA_NEW = "ROLE_LOJA_MAQUINA_NEW";
    public static final String LOJA_MAQUINA_EDIT = "ROLE_LOJA_MAQUINA_EDIT";
    public static final String LOJA_MAQUINA_DELETE = "ROLE_LOJA_MAQUINA_DELETE";
    
    public static final String OPERADOR_CAIXA = "ROLE_OPERADOR_CAIXA";
    public static final String OPERADOR_CAIXA_VIEW = "ROLE_OPERADOR_CAIXA_VIEW";
    public static final String OPERADOR_CAIXA_NEW = "ROLE_OPERADOR_CAIXA_NEW";
    public static final String OPERADOR_CAIXA_EDIT = "ROLE_OPERADOR_CAIXA_EDIT";
    public static final String OPERADOR_CAIXA_DELETE = "ROLE_OPERADOR_CAIXA_DELETE";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
