
package uis.edu.co.banco_de_sangre.controlador;

import uis.edu.co.banco_de_sangre.vista.VAnalisisDeSangre;
import uis.edu.co.banco_de_sangre.vista.VDistribucion;
import uis.edu.co.banco_de_sangre.vista.VDonacion;
import uis.edu.co.banco_de_sangre.vista.VDonante;
import uis.edu.co.banco_de_sangre.vista.VMenuPrincipal;
import uis.edu.co.banco_de_sangre.vista.VUnidadesDeSangre;

public class ControladorPantallas {
    private static VDonante pantallaDonante;
    private static VMenuPrincipal PantallaMenu; 
    private static VUnidadesDeSangre pantallaUnidadesSangre;
    private static VDistribucion pantallaDistribucion;
    private static VDonacion pantallaDonacion;
    private static VAnalisisDeSangre pantallaAnalisisDeSangre;

    public static void abrirPantallaDonante() {
        pantallaDonante = new VDonante();
        pantallaDonante.setVisible(true);
        pantallaDonante.setLocationRelativeTo(null);
    }

    public static void cerrarPantallaDonante() {
        pantallaDonante.dispose();
        pantallaDonante = null;
    }

    public static void abrirpantallaMenu() {
        PantallaMenu = new VMenuPrincipal();
        PantallaMenu.setVisible(true);
        PantallaMenu.setLocationRelativeTo(null);
    }

    public static void cerrarPantallaMenu() {
        PantallaMenu.dispose();
        PantallaMenu = null;
    }

    public static void abrirPantallaUnidadesSangre() {
        pantallaUnidadesSangre = new VUnidadesDeSangre();
        pantallaUnidadesSangre.setVisible(true);
        pantallaUnidadesSangre.setLocationRelativeTo(null);
    }

    public static void cerrarPantallaUnidadesSangre() {
        pantallaUnidadesSangre.dispose();
        pantallaUnidadesSangre = null;
    }
    
        public static void abrirPantallaDistribucion() {
        pantallaDistribucion = new VDistribucion();
        pantallaDistribucion.setVisible(true);
        pantallaDistribucion.setLocationRelativeTo(null);
    }

    public static void cerrarPantallaDistribucion() {
        pantallaDistribucion.dispose();
        pantallaDistribucion = null;
    }
   
    public static void abrirPantallaDonacion() {
        pantallaDonacion = new VDonacion(); 
        pantallaDonacion.setVisible(true);
        pantallaDonacion.setLocationRelativeTo(null);
    }

    public static void cerrarPantallaDonacion() {
        pantallaDonacion.dispose();
        pantallaDonacion = null;
    }
    public static void abrirPantallaAnalisisDeSangre() {
    pantallaAnalisisDeSangre = new VAnalisisDeSangre();
    pantallaAnalisisDeSangre.setVisible(true);
    pantallaAnalisisDeSangre.setLocationRelativeTo(null);
}

    public static void cerrarPantallaAnalisisDeSangre() {
    pantallaAnalisisDeSangre.dispose();
    pantallaAnalisisDeSangre = null;
}
}
