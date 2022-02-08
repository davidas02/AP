/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.ap;

/**
 *
 * @author daw1
 */
public enum TipoMovimiento {
    INGRESO("I"),REINTEGRO("R"),TRANSFERENCIA("T");
    private final String codigo;
    private TipoMovimiento (String codigo){
    this.codigo=codigo;
    }

    public String getCodigo() {
        return codigo;
    }
    
}
