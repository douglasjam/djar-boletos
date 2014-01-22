package com.djar.boleto.instutuicoes;

import com.djar.boleto.impressao.Impressao;
import com.djar.boleto.entidades.Banco;
import com.djar.boleto.entidades.Boleto;
import com.djar.boleto.utilitarios.Uteis;
import com.djar.boleto.entidades.Cedente;
import com.djar.boleto.entidades.Sacado;
import com.djar.boleto.resources.Resource;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class Caixa extends Instituicao {

    // dados utilizados no retorno e geração
    Impressao impressao;
    Banco banco;
    Cedente cedente;
    Sacado sacado;
    Boleto boleto;

    public Caixa(Banco banco, Cedente cedente, Sacado sacado, Boleto boleto) {

        this.impressao = new Impressao();
        this.banco = banco;
        this.cedente = cedente;
        this.sacado = sacado;
        this.boleto = boleto;
        //
        this.calculaImpressao();
    }

    public final void calculaImpressao() {


        // dados utilizados para calculos e geração da impressao
        String codigoBanco;
        String codigoBancoDV;
        String nummoeda;
        String fatorvencimento;
        String valor;
        String agencia;
        String conta;
        String contadv;
        String carteira;
        String convenio;
        String conveniodv;
        String campolivre;
        String nossonumero1;
        String nossonumero1const;
        String nossonumero2;
        String nossonumero2const;
        String nossonumero3;
        String dvcampolivre;
        String campolivrecomdv;
        String nnum;
        String nossonumero;
        String dv;
        String linha;
        String agenciacodigo;
        String linhaDigitavel;

        codigoBanco = String.valueOf(banco.codigo);
        codigoBancoDV = geraCodigoBanco(codigoBanco);
        nummoeda = "9";
        fatorvencimento = String.valueOf(fatorVencimento(boleto.vencimento));
        //
        valor = formataString(doubleToDecimal(boleto.valor), 10, "0");
        agencia = formataString(banco.agencia, 4, "0");
        conta = formataString(banco.conta, 5, "0");
        contadv = formataString(banco.contadigito, 1, "0");
        carteira = banco.carteira;
        //
        convenio = formataString(banco.convenio, 6, "0");
        conveniodv = String.valueOf(digitoVerificadorConvenio(convenio));
        //
        nossonumero1 = "000";
        nossonumero1const = "2"; //constanto 1 , 1=registrada , 2=sem registro
        nossonumero2 = "000";
        nossonumero2const = "4"; //constanto 2 , 4=emitido pelo proprio cliente
        nossonumero3 = String.valueOf(boleto.registro); // nossonumero ...
        //
        //campo livre (sem dv) é 24 digitos
        campolivre = convenio
                + conveniodv + formataString(nossonumero1, 3, "0")
                + formataString(nossonumero1const, 1, "0")
                + formataString(nossonumero2, 3, "0")
                + formataString(nossonumero2const, 1, "0")
                + formataString(nossonumero3, 9, "0");

        //dv do campo livre
        dvcampolivre = String.valueOf(digitoVerificadorNossoNumero(campolivre));
        campolivrecomdv = campolivre + dvcampolivre;

        //nosso número (sem dv) é 17 digitos
        nnum = formataString(nossonumero1const, 1, "0")
                + formataString(nossonumero2const, 1, "0")
                + formataString(nossonumero1, 3, "0")
                + formataString(nossonumero2, 3, "0")
                + formataString(nossonumero3, 9, "0");

        //nosso número completo (com dv) com 18 digitos
        nossonumero = nnum + digitoVerificadorNossoNumero(nnum);

        // 43 numeros para o calculo do digito verificador do codigo de barras
        dv = String.valueOf(digitoVerificadorCodigoBarras(codigoBanco + nummoeda + fatorvencimento + valor + campolivrecomdv));

        // Numero para o codigo de barras com 44 digitos
        linha = codigoBanco + nummoeda + dv + fatorvencimento + valor + campolivrecomdv;
        agenciacodigo = agencia + "/" + convenio + "-" + conveniodv;
        linhaDigitavel = montaLinhaDigitavel(linha);

        // repassa para o objeto impressão

        impressao.setLogoempresa(cedente.getLogo());
        impressao.setLinhadigitavel(linhaDigitavel);
        impressao.setValor(String.valueOf(boleto.valor));
//        identificacao
//        codigobancodv
//        linhadigitavel
        impressao.setCedente(cedente.nome);
        impressao.setAgenciacodigo(agenciacodigo);
//        especie
//        quantidade
        impressao.setNossonumero(nossonumero);
//        numerodocumento
//        cpfcnpj
//        datavencimento
//        valor
        impressao.setSacado(sacado.nome);
        impressao.setDemonstrativo(boleto.demonstrativo);
//        datadocumento
//        especiedoc
//        aceite
//        dataprocessamento
//        carteira
        impressao.setValor(String.valueOf(boleto.valor));
//        valorunitario
        impressao.setInstrucoes(boleto.instrucoes);
//        endereco1
//        endereco2


        impressao.setCodigobarras(Uteis.geraCodigoBarras(linha));
        impressao.setLogobanco(new ImageIcon(Resource.getInstance().IMAGE_CAIXA).getImage());
    }

    public Impressao getImpressao() {
        return impressao;
    }

    public String geraCodigoBanco(String numero) {
        String parte1 = numero.substring(0, 3);
        String parte2 = String.valueOf(Uteis.getMod11(parte1));
        return parte1 + "-" + parte2;
    }

    public Integer fatorVencimento(DateTime vencimento) {
        return Days.daysBetween(new DateTime(1997, 10, 7, 0, 0), vencimento.toDateMidnight()).getDays();
    }

    public String doubleToDecimal(Double valor) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(valor);

    }

    public Integer digitoVerificadorConvenio(String valor) {

        int resto = Uteis.getMod11(valor, 9, 1);
        int digito = 11 - resto;

        if (digito == 10 || digito == 11) {
            digito = 0;
        }
        return digito;
    }

    public String formataString(String palavra, Integer tamanho, String caracter) {

        String retorno = palavra.replace(".", "").replace(",", "");

        while (retorno.length() < tamanho) {
            retorno = caracter + retorno;
        }

        return retorno;
    }

    public Integer digitoVerificadorNossoNumero(String nossoNumero) {

        Integer resto = Uteis.getMod11(nossoNumero);
        Integer digito = 11 - resto;
        Integer retorno;

        if (digito == 10 || digito == 11) {
            retorno = 0;
        } else {
            retorno = digito;
        }

        return retorno;
    }

    public Integer digitoVerificadorCodigoBarras(String numero) {
        Integer resto = Uteis.getMod11(numero, 9, 1);
        Integer retorno;
        if (resto == 0 || resto == 1 || resto == 10) {
            retorno = 1;
        } else {
            retorno = 11 - resto;
        }

        return retorno;
    }

    public String montaLinhaDigitavel(String linha) {

        // Posição 	Conteúdo
        // 1 a 3    Número do banco
        // 4        Código da Moeda - 9 para Real
        // 5        Digito verificador do Código de Barras
        // 6 a 9   Fator de Vencimento
        // 10 a 19 Valor (8 inteiros e 2 decimais)
        // 20 a 44 Campo Livre definido por cada banco (25 caracteres)
        // 1. Campo - composto pelo código do banco, código da moéda, as cinco primeiras posições
        // do campo livre e DV (modulo10) deste campo

        String p1, p2, p3, p4, p5, p6, campo1, campo2, campo3, campo4, campo5;

        p1 = linha.substring(0, 4);
        p2 = linha.substring(19, 24);
        p3 = String.valueOf(Uteis.getMod10(p1 + p2));
        p4 = p1 + p2 + p3;
        p5 = p4.substring(0, 5);
        p6 = p4.substring(5);
        campo1 = p5 + "." + p6;

        // 2. Campo - composto pelas posiçoes 6 a 15 do campo livre
        // e livre e DV (modulo10) deste campo
        p1 = linha.substring(24, 34);
        p2 = String.valueOf(Uteis.getMod10(p1));
        p3 = p1 + p2;
        p4 = p3.substring(0, 5);
        p5 = p3.substring(5);
        campo2 = p4 + "." + p5;

        // 3. Campo composto pelas posicoes 16 a 25 do campo livre
        // e livre e DV (modulo10) deste campo
        p1 = linha.substring(34, 44);
        p2 = String.valueOf(Uteis.getMod10(p1));
        p3 = p1 + p2;
        p4 = p3.substring(0, 5);
        p5 = p3.substring(5);
        campo3 = p4 + "." + p5;

        // 4. Campo - digito verificador do codigo de barras
        campo4 = linha.substring(4, 5);

        // 5. Campo composto pelo fator vencimento e valor nominal do documento, sem
        // indicacao de zeros a esquerda e sem edicao (sem ponto e virgula). Quando se
        // tratar de valor zerado, a representacao deve ser 000 (tres zeros).
        p1 = linha.substring(5, 9);
        p2 = linha.substring(9, 19);
        campo5 = p1 + p2;

        return campo1 + " " + campo2 + " " + campo3 + " " + campo4 + " " + campo5;
    }
}
