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
    public int insertar(Cuenta c) throws DaoException;
    public Cuenta buscar(String codigo) throws DaoException;
    public int borrar(String codigo) throws DaoException;
    public int modificar(Cuenta c) throws DaoException;
    public List<Cuenta> listar() throws DaoException;
}
