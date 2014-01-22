/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.djar.boleto;

import java.util.Vector;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Douglas
 */
public class novoTeste {
    
    public static void main(String[] args) {
        //new java.util.Collection.JRBeanCollectionDataSource 
        Vector teste = new Vector();
        teste.add(2);
        teste.add(3);
        
        
    JRBeanCollectionDataSource collect = new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(teste);
        System.out.println(collect.toString());
    }
    
}
