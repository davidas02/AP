/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Aparicio Sir
 */
public class Banco {

    private String nombre;
    private Map<String,Cuenta> cuentas;
    CuentaDao cuentaDao;
    /**
     *  Constructor de la clase banco
     * @param nombre String
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas=new HashMap<>();
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
        return new ArrayList<>(cuentas.values());
    }
    /**
     *  Este metodo inicializa el nombre del banco al nombre seleccionado
     * @param nombre String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CuentaDao getCuentaDao() {
        return cuentaDao;
    }

    public void setCuentaDao(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }
    
    /**
     *  Crea una nueva cuenta y la guarda en la lista de cuentas
     * @param codigo String
     * @param titular String
     * @param saldo float
     * @return  new Cuenta(codigo,titular,saldo)
     */
    public boolean abrirCuenta(String codigo, String titular, float saldo){
        boolean salida=false;
        if(!cuentas.containsKey(codigo)){
            cuentas.put(codigo, new Cuenta(codigo, titular, saldo));
            salida=true;
        }
        return salida;
    }
    /**
     *  Busca una cuenta y la elimina de la lista de cuentas
     * @param codigo String
     * @return boolean borrado
     */
    public boolean cancelarCuenta(String codigo) {
        boolean borrado=false;
        if(cuentas.remove(codigo)!=null){
            borrado=true;
        }
        return borrado;
    }
    /**
     *  Acumula el total del saldo de las cuentas del banco
     * @return acum float
     */
    public float getTotalDepositos() {
        float acum = 0;
        for(Cuenta c:cuentas.values()){
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
        return cuentas.get(codigo);
    }
    /**
     *  Devuelve el nombre del banco
     * @return nombre del banco 
     */
    public String toString() {
        return nombre;
    }
    public int guardarCuentas() throws DaoException {
        int n = 0;
        if (cuentaDao == null) {
            throw new DaoException("No se ha podido encontrar la extension del fichero");
        }
        n = cuentaDao.insertar(new ArrayList<>(cuentas.values()));
        return n;
    }

    public int cargarCuentas() throws DaoException {
        if (cuentaDao == null) {
            throw new DaoException("No se ha pudido encontrar la extension del fichero");
        }
        int n = 0;
        List<Cuenta> listado = cuentaDao.listar();
        String codigo,titular;
        float saldo;
        for (Cuenta c : listado) {
            codigo=c.getCodigo();
            titular=c.getTitular();
            saldo=c.getSaldo();
            if (abrirCuenta(codigo, titular, saldo)) {
                n++;
            }
        }
        return n;
    }
}
