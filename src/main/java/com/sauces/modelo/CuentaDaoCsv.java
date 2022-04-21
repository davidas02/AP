/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw1
 */
public class CuentaDaoCsv implements CuentaDao {

    Path path;

    public CuentaDaoCsv(String path) {
        this.path = Paths.get(path);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public List<Cuenta> listar() throws DaoException {
        List<Cuenta> listado = new ArrayList<>();
        String codigo, titular, linea;
        float saldo;
        Cuenta cuenta = null;
        String[] tokens;
        try (BufferedReader entrada = Files.newBufferedReader(path)) {
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
            throw new DaoException(me.getMessage());
        } catch (Exception ex) {
            throw new DaoException("Error de formato");
        }
        return listado;
    }

    @Override
    public int insertar(List<Cuenta> cuentas) throws DaoException {
        int n=0;
        String linea;
        try(BufferedWriter salida=Files.newBufferedWriter(path)){
            for(Cuenta c:cuentas){
                linea=c.toString();
                salida.write(linea);
                salida.newLine();
                n++;
            }
        }catch(IOException ex){
            throw new DaoException(ex.toString());
        }  
        return n;
    }

}
