/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.ap;

/**
 *
 * @author daw1
 * @since 1.2
 * 
 */
public class CuentaCredito extends Cuenta {

    private float limiteCredito;

    /**
     *  Constructor de la clase CuentaCredito
     * @param codigo Codigo de la cuenta
     * @param titular titular de la cuenta
     * @param saldo saldo de la cuenta
     * @param limiteCredito Limite del credito de la cuenta
     * @throws SaldoException
     */
    public CuentaCredito(String codigo, String titular, float saldo, float limiteCredito) throws SaldoException {
        super(codigo, titular, saldo);
        this.limiteCredito = limiteCredito;
    }

    /**
     *  Devuelve el limite de credito de la cuenta
     * @return El limite de credito de la cuenta
     */
    public float getLimiteCredito() {
        return limiteCredito;
    }

    /**
     * Establece el limite de credito de la cuenta
     * @param limiteCredito 
     */
    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void reintegrar(float cantidad) {
        float nuevoSaldo;
        if (cantidad > 0) {
            nuevoSaldo = getSaldo() - cantidad;
            if (nuevoSaldo >= limiteCredito) {
                try {
                    setSaldo(nuevoSaldo);
                } catch (SaldoException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "," + limiteCredito;
    }

}
