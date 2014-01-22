package com.djar.boleto.entidades;

public class Banco {

    public static int CAIXA = 104;
    public static int SANTANDER = 999;
    //
    public int codigo;
    public String apelido;
    public String nome;
    public String agencia;
    public String agenciadigito;
    public String conta;
    public String contadigito;
    public String convenio;
    public String carteira;
    public String carteiradescricao;

    public Banco(Integer codigo, String agencia, String agenciadigito, String conta, String contadigito, String convenio, String carteira, String carteiradescricao) {

        this.codigo = codigo;
        this.agencia = agencia;
        this.agenciadigito = agenciadigito;
        this.conta = conta;
        this.contadigito = contadigito;
        this.convenio = convenio;
        this.carteira = carteira;
        this.carteiradescricao = carteiradescricao;

        atualizaDadosInstituicao();
    }

    public final void atualizaDadosInstituicao() {

        if (this.codigo == Banco.CAIXA) {
            this.apelido = "CAIXA";
            this.nome = "CAIXA ECONÔMICA FEDERAL";
        } else if (this.codigo == Banco.SANTANDER) {
            this.apelido = "SANTANDER";
            this.nome = "SANTANDER BANESPA";
        }
    }
}
