
import com.sauces.controlador.Controlador;
import com.sauces.modelo.Banco;
import com.sauces.vista.Ventana;

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
        // TODO code application logic here
        Banco modelo=new Banco("Banco Sauces");
        Ventana vista=new Ventana();
        Controlador controlador=new Controlador(vista, modelo);
        vista.setControlador(controlador);
        controlador.iniciar();
    }
    
}
