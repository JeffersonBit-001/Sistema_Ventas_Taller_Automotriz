/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Conexion.ConsultaLogin;
import Controlador.ControladorFormLogin;
import Modelo.Usuario;
import Vista.FormLogin;

/**
 *
 * @author JEFFERSON
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FormLogin log = new FormLogin();
        

        Usuario usu = new Usuario();
        //Cliente cli = new Cliente();

        ConsultaLogin consultalogin = new ConsultaLogin();
        
        ControladorFormLogin controlLogin = new ControladorFormLogin(usu, consultalogin, log);
    }
    
}
