package com.djar.boleto.instutuicoes;

import org.joda.time.DateTime;

public class Instituicao {
    
    public DateTime dataBaseFatorVencimento;

    public Instituicao() {
        // vence em 21/02/2025
        dataBaseFatorVencimento = new DateTime(1987, 10, 7, 0, 0);
    }
    
    
    
}
