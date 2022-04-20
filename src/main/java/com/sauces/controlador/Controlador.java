/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.controlador;

import com.sauces.modelo.Banco;
import com.sauces.modelo.Cuenta;
import com.sauces.vista.Ventana;
import javax.swing.JOptionPane;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana vista;
    private Banco modelo;

    public Controlador(Ventana vista, Banco modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    public void abrirCuenta(){
        String codigo=vista.getCodigo();
        String titular=vista.getTitular();
        Float saldo=vista.getSaldo();
        if(modelo.abrirCuenta(codigo, titular, saldo)){
            vista.mostrarMensaje("Cuenta Abierta");
            vista.mostrarCuentas(modelo.getCuentas());
        }else{
            vista.mostrarMensaje("No se ha podido abrir la cuenta");
        }
    }
    public void operarConCuenta(){
        Cuenta c1= modelo.getCuenta((String)JOptionPane.showInputDialog("Introduce cuenta con la que trabajar"));
    
        if(vista.getOperacion().equals("INTEGRAR")){
            float cantidad=vista.getCantidad();
            c1.ingresar(cantidad);
                }else{
            float cantidad=vista.getCantidad();
            c1.reintegrar(cantidad);
        }
        vista.mostrarCuentas(modelo.getCuentas());
    }
    public void cancelarCuenta(){
    modelo.cancelarCuenta(vista.getCodigo());
    }
    public void listarCuentas(){
        
    }
    public void guardarCuentas(){
    
    }
    public void cargarCuentas(){
    
    }
    public void iniciar(){
        vista.mostrar();
    }
}
