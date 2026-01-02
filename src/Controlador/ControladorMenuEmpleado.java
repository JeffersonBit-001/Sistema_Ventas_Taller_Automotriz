/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ConsultaAuto;
import Conexion.ConsultaBoletaIndividual;
import Conexion.ConsultaCliente;
import Conexion.ConsultaPagos;
import Conexion.ConsultaProductos;
import Conexion.ConsultaReporteCliente;
import Conexion.ConsultaServicios;
import Conexion.ConsultaUsuario;
import Conexion.ConsultaUsuarioPagos;
import FondosImg.ImagenFondoDeskot;
import Modelo.Autos;
import Modelo.Cliente;
import Modelo.Productos;
import Modelo.Servicios;
import Modelo.Usuario;
import Vista.FormAdmSistema;
import Vista.FormEmpSistema;
import Vista.InterBoletaIndividual;
import Vista.InterPagos;
import Vista.InterGestionarCliente;
import Vista.InterProductos;
import Vista.InterReporteCliente;
import Vista.InterServicioNuevo;
import Vista.InterVehiculoCliente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author JEFFERSON
 */
public class ControladorMenuEmpleado implements ActionListener {
    FormEmpSistema emp;
    ImagenFondoDeskot img=new ImagenFondoDeskot();

    public ControladorMenuEmpleado(FormEmpSistema emp) {
        this.emp = emp;
        this.emp.dskPaneSistema.setBorder(img);
        this.emp.mnGestionarCliente.addActionListener(this);
        this.emp.mnGestionVehiculo.addActionListener(this);
        this.emp.mnGestionServicios.addActionListener(this);
        this.emp.mnGestionProducto.addActionListener(this);
        this.emp.mnGestionarFactura.addActionListener(this);
        this.emp.mnSalirPanel.addActionListener(this);
        this.emp.mnVolverPanel.addActionListener(this);
        this.emp.mnReporteClienteVentas.addActionListener(this);
        this.emp.setTitle("Panel Empleado");
        this.emp.setVisible(false);
        //this.emp.setSize(new Dimension(1400, 900));
        this.emp.jtxNombreEmpleado.setForeground(Color.BLACK);
        this.emp.setExtendedState(emp.MAXIMIZED_BOTH);
        this.emp.setLocationRelativeTo(null);
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == emp.mnGestionarCliente) {
            Cliente cli = new Cliente();
            ConsultaCliente consultacliente = new ConsultaCliente();
            InterGestionarCliente gestioncli = new InterGestionarCliente();
            //CONTROLADOR CLIENTE
            ControladorCliente controlCliente=new ControladorCliente(gestioncli,cli,consultacliente);
            emp.dskPaneSistema.add(gestioncli);
            gestioncli.setVisible(true);
            gestioncli.setTitle("Gestionar Cliente");
        }
        
        if (e.getSource() == emp.mnGestionVehiculo) {
            Autos aut=new Autos();
            ConsultaAuto consultaaut=new ConsultaAuto();
            InterVehiculoCliente gestionvehi = new InterVehiculoCliente();
            //CONTROLADOR AUTO
            ControladorAuto controlAuto=new ControladorAuto(gestionvehi,aut,consultaaut);
            emp.dskPaneSistema.add(gestionvehi);
            gestionvehi.setVisible(true);
            gestionvehi.setTitle("Gestionar Vehiculo");
        }
        
        if (e.getSource() == emp.mnGestionServicios) {
            Servicios ser=new Servicios();
            ConsultaServicios csser=new ConsultaServicios();
            InterServicioNuevo gestionserv = new InterServicioNuevo();
            //CONTROLADOR SERVICIOS
            ControladorServicios controlservicio=new ControladorServicios(gestionserv,ser,csser);
            emp.dskPaneSistema.add(gestionserv);
            gestionserv.setVisible(true);
            gestionserv.setTitle("Gestionar Servicio");
        }
        
        if (e.getSource() == emp.mnGestionProducto){
            Productos prod=new Productos();
            InterProductos interProd=new InterProductos();
            ConsultaProductos consultaprod=new ConsultaProductos();
            emp.dskPaneSistema.add(interProd);
            //CONTROLADOR PRODUCTOS
            ControladorProductos controlservicio=new ControladorProductos(interProd,prod,consultaprod);
            interProd.setVisible(true);
            interProd.setTitle("Gestionar Producto");
        }
        
        if (e.getSource() == emp.mnGestionarFactura) {
            Cliente cl = new Cliente();
            Autos aut=new Autos();
            Servicios ser=new Servicios();
            Productos prod=new Productos();
            InterPagos detalleFact = new InterPagos();
            ConsultaPagos consultaFac=new ConsultaPagos();
            ConsultaBoletaIndividual csbl = new ConsultaBoletaIndividual();
            emp.dskPaneSistema.add(detalleFact);
             //CONTROLADOR PRODUCTOS
            ControladorPagos controlFact=new ControladorPagos(cl,ser,prod,aut,detalleFact,consultaFac,emp,csbl);
            detalleFact.setVisible(true);
            detalleFact.setTitle("Gestionar Factura");
        }
        
        if(e.getSource()==emp.mnReporteClienteVentas){
            Cliente cl=new Cliente();
            ConsultaReporteCliente csncl=new ConsultaReporteCliente();
            InterReporteCliente interReport=new InterReporteCliente();
            emp.dskPaneSistema.add(interReport);
             //CONTROLADOR PRODUCTOS
            ControladorReporteCliente controlReport=new ControladorReporteCliente(interReport, cl, csncl,emp);
            interReport.setVisible(true);
            interReport.setTitle("Reporte del Cliente");
            
        }
        
        if(e.getSource() == emp.mnSalirPanel){
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas salir?", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                System.exit(0);
            }
        }
        
        if (e.getSource()== emp.mnVolverPanel){
            
            
                FormAdmSistema fadm=new FormAdmSistema();
                Usuario usu = new Usuario();
                
                ControladorTabbedPane controlTabbedPane = new ControladorTabbedPane(fadm,usu);
                fadm.txtNombreAdministrador.setText(emp.jtxNombreEmpleado.getText());
                //fadm.txtNombreAdministrador.setForeground(Color.BLACK);
                fadm.setVisible(true);
                emp.setVisible(false);
            }
    }
}
