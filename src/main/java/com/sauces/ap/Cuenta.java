/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.ap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author David Aparicio Sir
 */
public class Cuenta implements Comparable<Cuenta>{
    private String codigo;
    private String titular;
    private float saldo;
    private List<Movimiento> movimientos;
    /**
     *  Constructor de la clase Cuenta
     * @param codigo codigo IBAN de la cuenta
     * @param titular propietario titular de la cuenta
     * @param saldo Saldo inicial de la cuenta
     */
    public Cuenta(String codigo, String titular, float saldo) throws SaldoException {
        this.codigo = codigo;
        this.titular = titular;
        if(saldo<0){
            throw new SaldoException("Saldo menor a 0");
        }
        this.saldo = saldo;
        movimientos=new ArrayList<>();
        movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.INGRESO, saldo, saldo));
    }
    /**
     *  Devuelve el valor de la variable codigo
     * @return codigo String
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     *  Establece el codigo de la cuenta
     * @param codigo String
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /** 
     *  Devuelve el titular de la cuenta
     * @return titular String
     */
    public String getTitular() {
        return titular;
    }
    /**
     *  Establece el titular de la cuenta
     * @param titular String
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }
    /**
     *  Devuelve el saldo de la cuenta
     * @return saldo
     */
    public float getSaldo() {
        return saldo;
    }
    /**
     * Establece el saldo de la cuenta
     * @param saldo float
     */
    public void setSaldo(float saldo) throws SaldoException {
        if(saldo<0){
            throw new SaldoException("El saldo a introducir es menor a 0");
        }
        this.saldo = saldo;
    }
    /**
     *  Metodo que devuelve una lista de metodos
     * @return movimientos Lista de los movimientos de la cuenta
     */
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     *  Metodo que devuelve una lista de movimientos entre dos fechas
     * @param desde Fecha inicial desde la que hay que buscar los movimientos
     * @param hasta Fecha final hasta la que hay que buscar los movimientos
     * @return Lista de los movimientos desde la fecha desde hasta la fecha hasta
     */
    public List<Movimiento> getMovimientos(LocalDate desde,LocalDate hasta){
        List<Movimiento>salida=new ArrayList();
        Movimiento m;
        Iterator<Movimiento> iterador=movimientos.iterator();
        while(iterador.hasNext()){
            
            m=iterador.next();
            if (m.getFecha().isAfter(desde)&&m.getFecha().isBefore(hasta)){
                salida.add(m);
            }
        }
        return salida;
    }

    /**
     *  Establece los movimientos de una cuenta
     * @param movimientos 
     */
    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * Metodo que permite ingresar dinero en la cuenta
     * @param cantidad cantidad que se desea ingresar en la cuenta
     */
    public void ingresar(float cantidad){
        if(cantidad>0){
            saldo+=cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.INGRESO,cantidad,saldo));
        }
    }

    /**
     *  Metodo que permite retirar dinero de la cuenta
     * @param cantidad cantidad que se desea reintegrar en la cuenta
     */
    public void reintegrar(float cantidad){
        if(cantidad>saldo){
           throw new SaldoInsuficienteException("Saldo Insuficiente");
        }
    saldo-=cantidad;
    movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.REINTEGRO,-cantidad,saldo));
    }

    /**
     * Metodo que permite realizar una transferencia de una cuenta a otra del banco
     * @param destino Cuenta en la cual se añadirá la cantidad retirada
     * @param cantidad Cantidad que se transferirá a la cuenta señalada
     */
    public void realizarTransferencia(Cuenta destino,float cantidad){
        if(cantidad>0&&cantidad<=saldo&&!destino.equals(this)){
            saldo-=cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.TRANSFERENCIA,-cantidad,saldo));
            destino.saldo+=cantidad;
            destino.movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.INGRESO,cantidad,destino.saldo));
        }
    }

    /**
     * Metodo que devuelve la lista de movimientos de una cuenta
     * @return Devuelve los movimientos como una cadena
     */
    public String listarMovimientos(){
        StringBuilder sb=new StringBuilder();
        for(Movimiento m:movimientos){
            sb.append(m.toString()).append("\n");
            
        }
        return sb.toString();
    }
/**
 *  Devuelve la cuenta como una cadena
 * @return codigo,titular,saldo como cadena
 */
    @Override
    public String toString() {
        return  codigo + "," + titular + "," + saldo ;
    }
/**
 * Devuelve un valor de código hash del objeto.
 * @return hash 
 */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
/**
 *  Metodo que comprueba que el codigo de dos cuentas son iguales
 * @param obj Objeto a comparar
 * @return Si los objetos son iguales devuelve true si no false
 */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Cuenta c) {
        return this.codigo.compareTo(c.codigo);
    }

   
    
}
