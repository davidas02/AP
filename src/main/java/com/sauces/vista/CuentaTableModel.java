/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.vista;

import com.sauces.modelo.Cuenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author daw1
 */
public class CuentaTableModel extends AbstractTableModel{
    private List <Cuenta> listado;
    private String[] columnas;

    public CuentaTableModel() {
        this.columnas=new String[]{"CODIGO","TITULAR","SALDO"};
        this.listado=new ArrayList<>();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public List<Cuenta> getCuentas(){
        return listado;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.listado = cuentas;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return listado.size();
    }

    @Override
    public int getColumnCount() {
       return columnas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Cuenta c=listado.get(row);
        Object o=null;
        switch(col){
            case 0: o=c.getCodigo();
                break;
            case 1: o=c.getTitular();
                break;
            case 2: o=c.getSaldo();
                break;
        }
        return o;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        Class<?> clase=null;
        switch(col){
            case 0: clase=String.class;
            break;
            case 1: clase=String.class;
            break;
            case 2: clase=Float.class;
            break;
        }
        return clase;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
