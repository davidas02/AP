
import com.sauces.controlador.Controlador;
import com.sauces.modelo.Banco;
import com.sauces.modelo.ConexionBD;
import com.sauces.modelo.Cuenta;
import com.sauces.modelo.CuentaDao;
import com.sauces.modelo.CuentaDaoJdbc;
import com.sauces.modelo.DaoException;
import com.sauces.vista.Ventana;
import java.util.List;
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
        // TODO code application logic here
        Banco modelo=new Banco("Banco Sauces");
        Ventana vista=new Ventana();
        Properties propiedades=new Properties();
        ConexionBD conexion=new ConexionBD(propiedades.getProperty("url"), propiedades.getProperty("usuario"), propiedades.getProperty("password"));
        CuentaDao cd=new CuentaDaoJdbc(conexion);
        Controlador controlador=new Controlador(vista, cd);
        vista.setControlador(controlador);
        controlador.iniciar();
    }
    
}
