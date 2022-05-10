/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class CuentaDaoJdbc implements CuentaDao {

    private ConexionBD conexion;

    public CuentaDaoJdbc(ConexionBD conexion) {
        this.conexion = conexion;
    }

    public ConexionBD getConexionBD() {
        return conexion;
    }

    public void setConexionBD(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public int insertar(Cuenta c) throws DaoException {
        int n = 0;
        String sentencia = "insert into cuenta values(?,?,?)";
        try (Connection con = conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sentencia);) {
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getTitular());
            ps.setFloat(3, c.getSaldo());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return n;
    }

    @Override
    public Cuenta buscar(String codigo) throws DaoException {
        Cuenta c = null;
        String sentencia = "select * from cuenta where codigo=?";
        try (Connection con = conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sentencia);) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cuenta(rs.getString("codigo"), rs.getString("titular"), rs.getFloat("saldo"));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return c;
    }

    @Override
    public int borrar(String codigo) throws DaoException {
        int n = 0;
        String sentencia = "delete from cuenta where codigo=?";
        try (Connection con = conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sentencia);) {
            ps.setString(1, codigo);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return n;
    }

    @Override
    public int modificar(Cuenta c) throws DaoException {
        int n=0; 
        String sentencia="update cuenta set saldo=? where codigo=?";
        try(Connection con=conexion.getConexion();
            PreparedStatement ps=con.prepareStatement(sentencia);){
            ps.setString(2,c.getCodigo());
            ps.setFloat(1,c.getSaldo());
            n=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    @Override
    public List<Cuenta> listar() throws DaoException {
        List<Cuenta> listado = null;
        String sentencia = "select * from cuenta";
        try (Connection con = conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sentencia);
                ResultSet rs = ps.executeQuery();) {
            listado = new ArrayList<>();
            while (rs.next()) {
                listado.add(new Cuenta(rs.getString("codigo"), rs.getString("titular"), rs.getFloat("saldo")));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return listado;
    }

}
