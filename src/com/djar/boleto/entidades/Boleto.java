package com.djar.boleto.entidades;

import org.joda.time.DateTime;

public class Boleto {

    public Integer registro;
    public Double valor;
    public DateTime processamento;
    public DateTime vencimento;
    public String instrucoes;
    public String demonstrativo;

    public Integer getRegistro() {
        return registro;
    }

    public void setRegistro(Integer registro) {
        this.registro = registro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public DateTime getProcessamento() {
        return processamento;
    }

    public void setProcessamento(DateTime processamento) {
        this.processamento = processamento;
    }

    public DateTime getVencimento() {
        return vencimento;
    }

    public void setVencimento(DateTime vencimento) {
        this.vencimento = vencimento;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getDemonstrativo() {
        return demonstrativo;
    }

    public void setDemonstrativo(String demonstrativo) {
        this.demonstrativo = demonstrativo;
    }
}
