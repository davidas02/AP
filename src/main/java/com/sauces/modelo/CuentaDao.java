/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import java.util.List;

/**
 *
 * @author daw1
 */
public interface CuentaDao {
    public List<Cuenta> listar() throws DaoException;
    public int insertar(List<Cuenta> cuentas) throws DaoException;
}
