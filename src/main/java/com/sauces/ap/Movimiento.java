/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.ap;

import java.time.LocalDate;

/**
 *
 * @author daw1
 */
public class Movimiento {
     private LocalDate fecha;
    private char tipo;
    private float cantidad;
    private float saldo;

    /**
     *Crea un nuevo movimiento
     * @param fecha  fecha del movimiento
     * @param tipo  tipo del movimeinto
     * @param cantidad Cantidad del movimiento
     * @param saldo saldo de la cuenta del movimiento
     */
    public Movimiento(LocalDate fecha, char tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }

    /**
     *  Devuelve la fecha del movimiento
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *  Devuelve el tipo del movimiento 
     * I=Ingreso
     * R=Retirada de efectivo (reintegrar)
     * T=Transferencia
     * @return tipo tipo del movimiento
     */
    public char getTipo() {
        return tipo;
    }

    /**
     *  Devuelve la cantidad del movimiento
     * @return cantidad Cantidad a sumar o restar a la cuenta del movimiento
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     *  Devuelve el Saldo de la cuenta
     * @return saldo Saldo de la cuenta despues del movimiento
     */
    public float getSaldo() {
        return saldo;
    }

   
    public String toString() {
        return fecha + "," + tipo + "," + cantidad + "," + saldo;
    }
    
}
