package com.djar.boleto;

import com.djar.boleto.impressao.Impressao;
import com.djar.boleto.entidades.Boleto;
import com.djar.boleto.entidades.Boletos;
import com.djar.boleto.entidades.Banco;
import com.djar.boleto.entidades.Cedente;
import com.djar.boleto.entidades.Sacado;
import com.djar.boleto.resources.Resource;
import javax.swing.ImageIcon;
import org.joda.time.DateTime;

public class Teste {

    public static void main(String[] args) {

        // simulação boleto 137
        Boletos boletos = new Boletos();
        boletos.setBanco(new Banco(Banco.CAIXA, "3308", "0", "738", "0", "391483", "SR", "SEM REGISTRO"));
        boletos.setCedente(new Cedente(new ImageIcon(Resource.getInstance().IMAGE_MULTICAR).getImage(), "MULTICAR ASSISTENCIA VEICULAR LTDA", "10.797.176/0001-70", "Av. Antônio Olimpio de Morais", "545", "Centro", "Divinópolis", "Minas Gerais", "35680-005"));
        boletos.setSacado(new Sacado("MOISES NUNES DA SILVA", "", "Av. Expedito Garcia", "123", "Campo Grande", "Cariacica", "Espírito Santo", "29146-201"));

        for (int i = 1; i <= 3; i++) {

            Boleto boleto = new Boleto();
            //
            boleto.setRegistro(137);
            boleto.setVencimento(new DateTime(2013, 12, 10, 0, 0));
            boleto.setValor(99.5);
            boleto.setInstrucoes("SR CAIXA, <br /><br />NÃO RECEBER APÓS O VENCIMENTO");
            boleto.setDemonstrativo("BOLETO REFERENTE A MENSALIDADE DO MÊS 11/2013<BR />R$ 99,50 MENSALIDADE<BR />APÓS O VENCIMENTO, PROCURAR NOSSA SEDE");
            //
            boletos.addBoleto(boleto);

        }

        boletos.imprimir("teste.pdf", Impressao.FULLPAGE);

    }
}
