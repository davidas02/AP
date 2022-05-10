/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daw1
 */
public class ConexionBD {

    private final String url;
    private final String usuario;
    private final String password;

    public ConexionBD(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    public Connection getConexion() throws DaoException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, url, password);
        } catch (SQLException sqle) {
            throw new DaoException("Error: " + sqle.getMessage());
        }
        return con;
    }
}
