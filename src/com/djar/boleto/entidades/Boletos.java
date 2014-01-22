package com.djar.boleto.entidades;

import com.djar.boleto.instutuicoes.Caixa;
import com.djar.boleto.impressao.Impressao;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Boletos {

    Banco banco;
    Cedente cedente;
    Sacado sacado;
    ArrayList<Boleto> boletos;

    public Boletos() {
        boletos = new ArrayList<>();
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Cedente getCedente() {
        return cedente;
    }

    public void setCedente(Cedente cedente) {
        this.cedente = cedente;
    }

    public Sacado getSacado() {
        return sacado;
    }

    public void setSacado(Sacado sacado) {
        this.sacado = sacado;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(ArrayList<Boleto> boletos) {
        this.boletos = boletos;
    }

    public void addBoleto(Boleto boleto) {
        this.boletos.add(boleto);
    }

    public void imprimir(String arquivo, Integer metodoImpressao) {

        ArrayList<Impressao> impressoes = new ArrayList<>();

        for (Boleto boleto : boletos) {

            if (banco.codigo == Banco.CAIXA) {
                Caixa caixa = new Caixa(banco, cedente, sacado, boleto);
                impressoes.add(caixa.getImpressao());
            } else if (banco.codigo == Banco.SANTANDER) {
                System.out.println("não implementado");
            } else {
                System.out.println("erro");
            }
        }
        
        System.out.println("IMPRIMINDO BOLETOS, FORMATO: " + metodoImpressao);
        System.out.println("-----------------------------------");
        for(Impressao impressao : impressoes){
            System.out.println("nossonumero: " + impressao.getNossonumero());
            System.out.println("valor: " + impressao.getValor());
        }

        // gera o pdf do relatório
        InputStream relatorio = null;

        if (metodoImpressao == Impressao.FULLPAGE) {
            relatorio = getClass().getResourceAsStream("/com/djar/boleto/relatorios/fullpage.jasper");
            System.out.println("fullpage");
        } else if (metodoImpressao == Impressao.COMPACT) {
            relatorio = getClass().getResourceAsStream("/com/djar/boleto/relatorios/compact.jasper");
        } else if (metodoImpressao == Impressao.CARNE3PORPAG) {
            relatorio = getClass().getResourceAsStream("/com/djar/boleto/relatorios/carne3porpag.jasper");
        } else {
            System.out.println("Tipo do documento a imprimir não é válido!");
            System.exit(1);
        }

        String outFileName = arquivo;
        HashMap parametros = new HashMap();
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(impressoes);

        try {
            JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, datasource);
            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.exportReport();
            System.out.println("relatório criado: " + outFileName);
        } catch (JRException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
