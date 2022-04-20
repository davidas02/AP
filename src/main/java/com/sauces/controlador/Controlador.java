/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.controlador;

import com.sauces.modelo.Banco;
import com.sauces.vista.Ventana;

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
