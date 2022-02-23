/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.ap;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author daw1
 */
public class AP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Banco banco = new Banco("Banco Sauces");
        Scanner teclado = new Scanner(System.in);
        int opcion=0, opcion2 = 0;
        String codigo, titular;
        float saldo;
        List<Cuenta> listado;
        boolean correcto;
        do {
            System.out.println("1: -Abrir cuenta");
            System.out.println("2: -Operar con cuenta");
            System.out.println("3: -Cancelar cuenta");
            System.out.println("4: -Listar Cuentas");
            System.out.println("5: -Consultar total depositos");
            System.out.println("0: -Salir");
            do {
                try {
                    correcto = true;
                    opcion = teclado.nextInt();

                } catch (InputMismatchException ime) {
                    correcto = false;
                } finally {
                    teclado.nextLine();
                }
            } while (!correcto);
            switch (opcion) {
                case 1:
                    System.out.println("Abrir Cuentas");
                    System.out.println("Introduce codigo de cuenta: ");
                    codigo = teclado.nextLine();
                    System.out.println("Introduce el nombre del titular: ");
                    titular = teclado.nextLine();
                    System.out.println("Introduce el saldo inicial de la cuenta: ");
                    saldo = teclado.nextFloat();
                    teclado.nextLine();
                     {
                        try {
                            if (banco.abrirCuenta(codigo, titular, saldo)) {
                                System.out.println("Cuenta Abierta con exito");
                            } else {
                                System.out.println("No se ha podido crear la cuenta");
                            }
                        } catch (SaldoException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                case 2:
                    System.out.println("Operar con cuenta");
                    System.out.println("Introduce el codigo de cuenta con el que se va a operar: ");
                    codigo = teclado.nextLine();
                    Cuenta c1,
                     c2;
                    float cantidad;
                    c1 = banco.getCuenta(codigo);
                    if (c1 != null) {
                        do {
                            System.out.println("1:- Ingresar dinero");
                            System.out.println("2:- Retirar dinero");
                            System.out.println("3:- Consultar saldo");
                            System.out.println("4:- Realizar Transferencia");
                            System.out.println("5:- Consultar movimientos");
                            System.out.println("0:- Salir");
                            do {
                                try {
                                    correcto = true;
                                    opcion2 = teclado.nextInt();

                                } catch (InputMismatchException ime) {
                                    correcto = false;
                                } finally {
                                    teclado.nextLine();
                                }
                            } while (!correcto);
                            teclado.nextLine();
                            switch (opcion2) {
                                case 1:
                                    System.out.println("Ingeresar Dinero");
                                    System.out.println("Introduzca cantidad a ingresar: ");
                                    cantidad = teclado.nextFloat();
                                    c1.ingresar(cantidad);
                                    System.out.println("Su saldo es de: " + c1.getSaldo() + "€");
                                    break;
                                case 2:
                                    System.out.println("Retirar Dinero");
                                    System.out.println("Introduzca cantidad a retirar: ");
                                    cantidad = teclado.nextFloat();
                                    try {
                                        c1.reintegrar(cantidad);
                                    } catch (SaldoException sie) {
                                        System.out.println("No se ha podido retirar la cantidad deseada");
                                    }
                                    System.out.println("Su saldo es de: " + c1.getSaldo() + "€");
                                    break;
                                case 3:
                                    System.out.println("Consultar saldo");
                                    System.out.println(c1.getSaldo());
                                    break;
                                case 4:
                                    System.out.println("Realizar Transferencia");
                                    System.out.println("Introduzca codigo de cuenta a transferir: ");
                                    codigo = teclado.nextLine();
                                    c2 = banco.getCuenta(codigo);

                                    System.out.println("Introduzca cantidad a transferir: ");
                                    cantidad = teclado.nextFloat();
                                    teclado.nextLine();
                                    try {
                                        c1.realizarTransferencia(c2, cantidad);
                                    } catch (SaldoException se) {
                                        System.out.println(se.getMessage());
                                    } catch (IllegalArgumentException iae) {
                                        System.out.println(iae.getMessage());
                                    } catch (NullPointerException npe) {
                                        System.out.println(npe.getMessage());
                                    }
                                    System.out.println("Su saldo es de: " + c1.getSaldo() + "€");
                                    break;
                                case 5:
                                    System.out.println("Consultar Movimientos");
                                    System.out.println(c1.listarMovimientos());
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Opcion Incorrecta");
                            }
                        } while (opcion2 != 0);
                    } else {
                        System.out.println("No existe una cuenta con ese codigo.");
                    }
                    break;
                case 3:
                    System.out.println("Cancelar Cuenta");
                    System.out.println("Introduce el codigo de cuenta a borrar: ");
                    codigo = teclado.nextLine();
                    if (banco.cancelarCuenta(codigo)) {
                        System.out.println("Cuenta cancelada con exito");
                    } else {
                        System.out.println("No se ha podido cancelar la cuenta");
                    }
                    break;
                case 4:
                    System.out.println("Listar Cuentas");
                    listado = banco.getCuentas();
                    for (Cuenta c : listado) {
                        System.out.println(c.toString());
                    }
                    break;
                case 5:
                    System.out.println("consultar Total Depositos");
                    System.out.println("Total depositos: " + banco.getTotalDepositos());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion Incorrecta");
            }
        } while (opcion != 0);
        System.out.println("Hasta Luego");
    }

}
