/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.controlador;

import com.sauces.modelo.Banco;
import com.sauces.modelo.Cuenta;
import com.sauces.modelo.CuentaDao;
import com.sauces.modelo.DaoException;
import com.sauces.modelo.SaldoException;
import com.sauces.vista.Ventana;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author daw1
 */
public class Controlador {

    private Ventana vista;
    private CuentaDao modelo;

    public Controlador(Ventana vista, CuentaDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void abrirCuenta() {
        String codigo = vista.getCodigo();
        String titular = vista.getTitular();
        Float saldo = vista.getSaldo();
        Cuenta c = new Cuenta(codigo, titular, saldo);
        try {
            if (modelo.insertar(c) > 0) {
                vista.mostrarMensaje("Cuenta Abierta");
                vista.mostrarCuentas(modelo.listar());
            } else {
                vista.mostrarMensaje("No se ha podido abrir la cuenta");
            }
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void operarConCuenta() {
        Cuenta c1 = null;
        try {
            c1 = modelo.buscar((String) JOptionPane.showInputDialog("Introduce cuenta con la que trabajar"));
        } catch (DaoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        float cantidad;
        String operacion;
        if (c1 != null) {

            cantidad = vista.getCantidad();
            try {
                c1.ingresar(cantidad);
                modelo.modificar(c1);
                vista.mostrarSaldo(c1.getSaldo());
                vista.mostrarCuentas(modelo.listar());
            } catch (IllegalArgumentException | DaoException ex) {
                vista.mostrarMensaje(ex.getMessage());
            }

        } else {
            vista.mostrarMensaje("La cuenta especificada no existe");
        }
    }

    public void cancelarCuenta() {
        String codigo = (String) JOptionPane.showInputDialog("Introduce cuenta con la que trabajar");

        try {
            if (modelo.borrar(codigo) > 0) {
                vista.mostrarCuentas(modelo.listar());
                vista.limpiarCampos();
                vista.mostrarMensaje("Cuenta cancelada");
            } else {
                vista.mostrarMensaje("La cuenta seleccionada no se ha podido cancelar");
            }
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }

    }

    public void listarCuentas() {
        try {
            vista.mostrarCuentas(modelo.listar());
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void guardarCuentas() {
        String archivo = vista.getArchivo();
        Path path = Paths.get(archivo);
        List<Cuenta> listado = new ArrayList<>();
        String codigo, titular, linea;
        float saldo;
        Cuenta cuenta = null;
        String[] tokens;
        try ( BufferedReader entrada = Files.newBufferedReader(path)) {
            linea = entrada.readLine();
            while (linea != null) {
                tokens = linea.split(",");
                codigo = tokens[0];
                titular = tokens[1];
                saldo = Float.parseFloat(tokens[2]);
                cuenta = new Cuenta(codigo, titular, saldo);

                listado.add(cuenta);
                linea = entrada.readLine();
            }
        } catch (IOException me) {
            vista.mostrarMensaje(me.getMessage());
        } catch (Exception ex) {
            vista.mostrarMensaje(ex.toString());
        }
        listarCuentas();
    }

    public void cargarCuentas() {
        List<Cuenta> cuentas = null;
        try {
            cuentas = modelo.listar();
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
        int n = 0;
        if (cuentas != null) {
            String archivo = vista.getArchivo();
            Path path = Paths.get(archivo);
            String linea;
            try ( BufferedWriter salida = Files.newBufferedWriter(path)) {
                for (Cuenta c : cuentas) {
                    linea = c.toString();
                    salida.write(linea);
                    salida.newLine();
                    n++;
                }
            } catch (IOException ex) {
                vista.mostrarMensaje(ex.getMessage());
            }
        }
    }

    public void iniciar() {
        vista.mostrar();
    }
}
