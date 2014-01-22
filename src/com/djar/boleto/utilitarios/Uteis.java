package com.djar.boleto.utilitarios;

import com.lowagie.text.pdf.BarcodeInter25;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import sun.awt.image.ToolkitImage;

public class Uteis {

    /**
     * @author :Allan Tenorio
     * @since :10/07/2012
     * @see :Calculo do Modulo 10 para geracao do digito verificador de boletos
     * bancários.
     */
    //Módulo 10  
    //Conforme o esquema abaixo, cada dígito do número, começando da direita para a esquerda   
    //(menos significativo para o mais significativo) é multiplicado, na ordem, por 2, depois 1, depois 2, depois 1 e   
    //assim sucessivamente.  
    //Em vez de ser feito o somatório das multiplicações, será feito o somatório dos dígitos das multiplicações   
    //(se uma multiplicação der 12, por exemplo, será somado 1 + 2 = 3).  
    //O somatório será dividido por 10 e se o resto (módulo 10) for diferente de zero, o dígito será 10 menos este valor.  
    //Número exemplo: 261533-4  
    //  +---+---+---+---+---+---+   +---+  
    //  | 2 | 6 | 1 | 5 | 3 | 3 | - | 4 |  
    //  +---+---+---+---+---+---+   +---+  
    //    |   |   |   |   |   |  
    //   x1  x2  x1  x2  x1  x2  
    //    |   |   |   |   |   |  
    //   =2 =12  =1 =10  =3  =6  
    //    +---+---+---+---+---+-> = (16 / 10) = 1, resto 6 => DV = (10 - 6) = 4  
    public static int getMod10(String num) {

        //variáveis de instancia  
        int soma = 0;
        int resto = 0;
        int dv = 0;
        String[] numeros = new String[num.length() + 1];
        int multiplicador = 2;
        String aux;
        String aux2;
        String aux3;

        for (int i = num.length(); i > 0; i--) {
            //Multiplica da direita pra esquerda, alternando os algarismos 2 e 1  
            if (multiplicador % 2 == 0) {
                // pega cada numero isoladamente    
                numeros[i] = String.valueOf(Integer.valueOf(num.substring(i - 1, i)) * 2);
                multiplicador = 1;
            } else {
                numeros[i] = String.valueOf(Integer.valueOf(num.substring(i - 1, i)) * 1);
                multiplicador = 2;
            }
        }

        // Realiza a soma dos campos de acordo com a regra  
        for (int i = (numeros.length - 1); i > 0; i--) {
            aux = String.valueOf(Integer.valueOf(numeros[i]));

            if (aux.length() > 1) {
                aux2 = aux.substring(0, aux.length() - 1);
                aux3 = aux.substring(aux.length() - 1, aux.length());
                numeros[i] = String.valueOf(Integer.valueOf(aux2) + Integer.valueOf(aux3));
            } else {
                numeros[i] = aux;
            }
        }

        //Realiza a soma de todos os elementos do array e calcula o digito verificador  
        //na base 10 de acordo com a regra.       
        for (int i = numeros.length; i > 0; i--) {
            if (numeros[i - 1] != null) {
                soma += Integer.valueOf(numeros[i - 1]);
            }
        }
        resto = soma % 10;
        dv = 10 - resto;

        //retorna o digito verificador  
        return dv;
    }

    public static int getMod11(String num) {
        return Uteis.getMod11(num, 9, 0);
    }

    public static int getMod11(String num, int base, int r) {
        /**
         * Autor: Douglas Tybel <dtybel@yahoo.com.br>
         *
         * Função: Calculo do Modulo 11 para geracao do digito verificador de
         * boletos bancarios conforme documentos obtidos da Febraban -
         * www.febraban.org.br
         *
         * Entrada: $num: string numérica para a qual se deseja calcularo digito
         * verificador; $base: valor maximo de multiplicacao [2-$base] $r:
         * quando especificado um devolve somente o resto
         *
         * Saída: Retorna o Digito verificador.
         *
         * Observações: - Script desenvolvido sem nenhum reaproveitamento de
         * código existente. - Script original de Pablo Costa
         * <pablo@users.sourceforge.net>
         * - Transportado de php para java - Exemplo de uso:
         * getMod11(nossoNumero, 9,1) - 9 e 1 são fixos de acordo com a base -
         * Assume-se que a verificação do formato das variáveis de entrada é
         * feita antes da execução deste script.
         */
        int soma = 0;
        int fator = 2;
        String[] numeros, parcial;
        numeros = new String[num.length() + 1];
        parcial = new String[num.length() + 1];

        /* Separacao dos numeros */
        for (int i = num.length(); i > 0; i--) {
            // pega cada numero isoladamente  
            numeros[i] = num.substring(i - 1, i);
            // Efetua multiplicacao do numero pelo falor  
            parcial[i] = String.valueOf(Integer.parseInt(numeros[i]) * fator);
            // Soma dos digitos  
            soma += Integer.parseInt(parcial[i]);
            if (fator == base) {
                // restaura fator de multiplicacao para 2  
                fator = 1;
            }
            fator++;

        }

        /* Calculo do modulo 11 */
        if (r == 0) {
            soma *= 10;
            int digito = soma % 11;
            if (digito == 10) {
                digito = 0;
            }
            return digito;
        } else {
            int resto = soma % 11;
            return resto;
        }
    }

    public static Image geraCodigoBarras(String linhaDigitavel) {
        
        System.out.println(linhaDigitavel);
        
        // Montando o código de barras.
        BarcodeInter25 barCode = new BarcodeInter25();
        barCode.setCode(linhaDigitavel);

        barCode.setExtended(true);
        barCode.setBarHeight(35);
        barCode.setFont(null);
        barCode.setN(3);

        Image image = barCode.createAwtImage(Color.BLACK, Color.WHITE);
        return image;
    }
}
