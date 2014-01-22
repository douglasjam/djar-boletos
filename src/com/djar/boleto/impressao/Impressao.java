package com.djar.boleto.impressao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

public class Impressao {

    public static int FULLPAGE = 1;
    public static int COMPACT = 2;
    public static int CARNE3PORPAG = 3;
    //
    public String identificacao;
    public String codigobancodv;
    public String linhadigitavel;
    public String cedente;
    public String agenciacodigo;
    public String especie;
    public String quantidade;
    public String nossonumero;
    public String numerodocumento;
    public String cpfcnpj;
    public String datavencimento;
    public String valor;
    public String sacado;
    public String demonstrativo;
    public String datadocumento;
    public String especiedoc;
    public String aceite;
    public String dataprocessamento;
    public String carteira;
    public String valorunitario;
    public String valorboleto;
    public String instrucoes;
    public String endereco1;
    public String endereco2;
    public Image codigobarras;
    public Image logoempresa;
    public Image logobanco;

    public Impressao() {
    }

    public String getTipoimpressao(int tipoimpressao) {
        if (tipoimpressao == FULLPAGE) {
            return "FULLPAGE";
        } else if (tipoimpressao == COMPACT) {
            return "COMPACT";
        } else if (tipoimpressao == CARNE3PORPAG) {
            return "CARNE3PORPAG";
        } else {
            return null;
        }
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getCodigobancodv() {
        return codigobancodv;
    }

    public void setCodigobancodv(String codigobancodv) {
        this.codigobancodv = codigobancodv;
    }

    public String getLinhadigitavel() {
        return linhadigitavel;
    }

    public void setLinhadigitavel(String linhadigitavel) {
        this.linhadigitavel = linhadigitavel;
    }

    public String getCedente() {
        return cedente;
    }

    public void setCedente(String cedente) {
        this.cedente = cedente;
    }

    public String getAgenciacodigo() {
        return agenciacodigo;
    }

    public void setAgenciacodigo(String agenciacodigo) {
        this.agenciacodigo = agenciacodigo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getNossonumero() {
        return nossonumero;
    }

    public void setNossonumero(String nossonumero) {
        this.nossonumero = nossonumero;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(String datavencimento) {
        this.datavencimento = datavencimento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getSacado() {
        return sacado;
    }

    public void setSacado(String sacado) {
        this.sacado = sacado;
    }

    public String getDemonstrativo() {
        return demonstrativo;
    }

    public void setDemonstrativo(String demonstrativo) {
        this.demonstrativo = demonstrativo;
    }

    public String getDatadocumento() {
        return datadocumento;
    }

    public void setDatadocumento(String datadocumento) {
        this.datadocumento = datadocumento;
    }

    public String getEspeciedoc() {
        return especiedoc;
    }

    public void setEspeciedoc(String especiedoc) {
        this.especiedoc = especiedoc;
    }

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public String getDataprocessamento() {
        return dataprocessamento;
    }

    public void setDataprocessamento(String dataprocessamento) {
        this.dataprocessamento = dataprocessamento;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public String getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(String valorunitario) {
        this.valorunitario = valorunitario;
    }

    public String getValorboleto() {
        return valorboleto;
    }

    public void setValorboleto(String valorboleto) {
        this.valorboleto = valorboleto;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getEndereco1() {
        return endereco1;
    }

    public void setEndereco1(String endereco1) {
        this.endereco1 = endereco1;
    }

    public String getEndereco2() {
        return endereco2;
    }

    public void setEndereco2(String endereco2) {
        this.endereco2 = endereco2;
    }

    public Image getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(Image codigobarras) {
        this.codigobarras = codigobarras;
    }

    public Image getLogoempresa() {
        return logoempresa;
    }

    public void setLogoempresa(Image logoempresa) {
        this.logoempresa = logoempresa;
    }

    public Image getLogobanco() {
        return logobanco;
    }

    public void setLogobanco(Image logobanco) {
        this.logobanco = logobanco;
    }
}
