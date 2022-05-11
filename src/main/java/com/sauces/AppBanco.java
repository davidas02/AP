package com.sauces;

import com.sauces.controlador.Controlador;
import com.sauces.modelo.Banco;
import com.sauces.modelo.ConexionBD;
import com.sauces.modelo.CuentaDao;
import com.sauces.modelo.CuentaDaoJdbc;
import com.sauces.vista.Ventana;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author daw1
 */
public class AppBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url, usuario, password;
        Controlador controlador;
        CuentaDao modelo;
        Ventana vista;
        Properties propiedades;

        vista = new Ventana();
        propiedades = new Properties();

        try (InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("conexion.properties");) {
            propiedades.load(is);
            url = propiedades.getProperty("url");
            usuario = propiedades.getProperty("usuario");
            password = propiedades.getProperty("password");

            ConexionBD conexion = new ConexionBD(url, usuario, password);
            modelo = new CuentaDaoJdbc(conexion);

            controlador = new Controlador(vista, modelo);
            vista.setControlador(controlador);
            controlador.iniciar();
        } catch (IOException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

}
