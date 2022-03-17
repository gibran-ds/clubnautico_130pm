package clubnautico_130pm;

import entidades.Socio;
import guis.SociosForm;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.ISociosDAO;
import persistencia.SociosDAO;

public class Principal {

    public static void main(String[] args) {
        IConexionBD conexion = new ConexionBD();
        ISociosDAO sociosDAO = new SociosDAO(conexion);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SociosForm(sociosDAO).setVisible(true);
            }
        });
    }
    
}
