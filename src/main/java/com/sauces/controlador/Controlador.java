/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.controlador;

import com.sauces.modelo.Banco;
import com.sauces.modelo.Cuenta;
import com.sauces.modelo.CuentaDao;
import com.sauces.modelo.SaldoException;
import com.sauces.vista.Ventana;
import javax.swing.JOptionPane;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana vista;
    private CuentaDao modelo;

    public Controlador(Ventana vista, CuentaDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    public void abrirCuenta(){
        String codigo=vista.getCodigo();
        String titular=vista.getTitular();
        Float saldo=vista.getSaldo();
        Cuenta c=new Cuenta(codigo, titular, saldo);
        if(modelo.insertar(c)>0){
            vista.mostrarMensaje("Cuenta Abierta");
            vista.mostrarCuentas(modelo.listar());
        }else{
            vista.mostrarMensaje("No se ha podido abrir la cuenta");
        }
    }
    public void operarConCuenta(){
        Cuenta c1= modelo.getCuenta((String)JOptionPane.showInputDialog("Introduce cuenta con la que trabajar"));
        float cantidad;
        String operacion;
        if(c1!=null){
            operacion=vista.getOperacion();
            if(operacion!=null){
            switch(operacion){
                case "INGRESAR":
                    cantidad=vista.getCantidad();
                    try{
                    c1.ingresar(cantidad);
                    vista.mostrarSaldo(c1.getSaldo());
                    }catch(IllegalArgumentException ex){
                        vista.mostrarMensaje(ex.getMessage());
                    }
                  break;  
                case "REINTEGRAR":
                    cantidad=vista.getCantidad();
                    try {
                        c1.reintegrar(cantidad);
                        vista.mostrarSaldo(c1.getSaldo());
                    } catch (SaldoException ex) {
                        vista.mostrarMensaje(ex.getMessage());
                    }catch(IllegalArgumentException ex){
                        vista.mostrarMensaje(ex.getMessage());
                    }
            }
            }
            vista.mostrarCuentas(modelo.getCuentas());
        }else{
            vista.mostrarMensaje("La cuenta especificada no existe");
        }
    }
    public void cancelarCuenta(){
       String codigo=(String)JOptionPane.showInputDialog("Introduce cuenta con la que trabajar");
       
        if(modelo.cancelarCuenta(codigo)){
            vista.mostrarCuentas(modelo.getCuentas());
            vista.limpiarCampos();
            vista.mostrarMensaje("Cuenta cancelada");
        }else{
            vista.mostrarMensaje("La cuenta seleccionada no se ha podido cancelar");
        }
        
    }
    public void listarCuentas(){
        vista.mostrarCuentas(modelo.getCuentas());
    }
    /*public void guardarCuentas(){
        String archivo= vista.getArchivo();
        try {
            modelo.setCuentaDao(new CuentaDaoCsv(archivo));
           int i= modelo.guardarCuentas();
            vista.mostrarMensaje("Cuentas guardadas");
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
        listarCuentas();
    }
    
    public void cargarCuentas(){
       String archivo= vista.getArchivo();
        try {
            modelo.setCuentaDao(new CuentaDaoCsv(archivo));
            modelo.cargarCuentas();
            vista.mostrarMensaje("Cuentas cargadas");
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
        listarCuentas();
    }*/
    public void iniciar(){
        vista.mostrar();
    }
}
