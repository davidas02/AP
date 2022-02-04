/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.ap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author David Aparicio Sir
 */
public class Banco {

    private String nombre;
    private Set<Cuenta> cuentas;
    /**
     *  Constructor de la clase banco
     * @param nombre String
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas=new HashSet<>();
    }
    /**
     *  Este metodo devuelve el nombre del banco
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     *  Este metodo devuelve la lista de cuentas que hay en el banco
     * @return List<Cuenta> cuentas 
     */
    public List<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas);
    }
    /**
     *  Este metodo inicializa el nombre del banco al nombre seleccionado
     * @param nombre String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     *  Crea una nueva cuenta y la guarda en la lista de cuentas
     * @param codigo String
     * @param titular String
     * @param saldo float
     * @return  new Cuenta(codigo,titular,saldo)
     */
    public boolean abrirCuenta(String codigo, String titular, float saldo){
        return cuentas.add(new Cuenta(codigo, titular, saldo));
    }
    /**
     *  Busca una cuenta y la elimina de la lista de cuentas
     * @param codigo String
     * @return boolean borrado
     */
    public boolean cancelarCuenta(String codigo) {
        boolean borrado=false;
        Cuenta c=getCuenta(codigo);
        if(c!=null){
            borrado=cuentas.remove(c);
        }
        return borrado;
    }
    /**
     *  Acumula el total del saldo de las cuentas del banco
     * @return acum float
     */
    public float getTotalDepositos() {
        
        float acum = 0;
        for(Cuenta c:cuentas){
            acum+=c.getSaldo();
        }
        return acum;
    }
    /**
     *  Busca un codigo con el metodo privado buscarCuenta(String Codigo) 
     * y con la posicion que devuelve el anterior metodo buscamos en la lista
     * la cuenta con el codigo requerido
     * @param codigo String
     * @return Cuenta c
     */
    public Cuenta getCuenta(String codigo) {
        for(Cuenta cu:cuentas){
            if(cu.getCodigo().equals(codigo)){
                return cu;
            }
        }
        return null;
    }
    /**
     *  Devuelve el nombre del banco
     * @return nombre del banco 
     */
    public String toString() {
        return nombre;
    }
    
}
