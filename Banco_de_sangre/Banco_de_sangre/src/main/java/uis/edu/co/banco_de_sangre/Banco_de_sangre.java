package uis.edu.co.banco_de_sangre;

import uis.edu.co.banco_de_sangre.controlador.Conexion;
import uis.edu.co.banco_de_sangre.controlador.ControladorPantallas;
import uis.edu.co.banco_de_sangre.vista.VDonante;


public class Banco_de_sangre {

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        conexion.conectar();
        ControladorPantallas.abrirpantallaMenu(); 
        
    }
}

// Esta es la carpeta de github ...SOTO