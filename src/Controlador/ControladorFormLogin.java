/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ConsultaLogin;
import Conexion.ConsultaUsuario;
import Conexion.ConsultaUsuarioPagos;
import FondosImg.ImagenFondoLogin;
import Modelo.Usuario;
import Vista.FormAdmSistema;
import Vista.FormEmpSistema;
import Vista.FormLogin;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author JEFFERSON
 */
public class ControladorFormLogin implements ActionListener, MouseListener {

    FormLogin log;
    Usuario us;
    ConsultaLogin cclg;
    //FormEmpSistema femp;
    //FormAdmSistema fadm;
    ImagenFondoLogin fondo = new ImagenFondoLogin();

    public ControladorFormLogin(Usuario us, ConsultaLogin cclg, FormLogin log) {
        this.us = us;
        this.cclg = cclg;
        this.log = log;
        //this.fadm=fadm;
        //this.femp=femp;
        //this.fadm.setVisible(false);
        //this.femp.setVisible(false);
        //this.log.setContentPane(fondo);
        this.log.btnLoginUsuario.addActionListener(this);
        this.log.btnSalirUsuario.addActionListener(this);
        this.log.jlbVer.addMouseListener(this);
        this.log.jlbOcultar.addMouseListener(this);
        this.log.setLocationRelativeTo(null);
        this.log.setResizable(false);
        this.log.setVisible(true);
        this.log.jlbOcultar.setVisible(false);
        this.log.setTitle("Inicio de Sessión");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == log.btnLoginUsuario) {
            if (log.txtUsLog.getText().equals("") || log.btnSalirUsuario.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Completa todo los campos");
            } else {
                us.setUsuario(log.txtUsLog.getText());
                us.setPass(String.valueOf(log.jtxPassUsuarioLog.getPassword()));
                cclg.IngresarLoginUsuario(us);
                if (us.getRol().equals("Administrador") && log.txtUsLog.getText().equals(us.getUsuario())
                        && String.valueOf(log.jtxPassUsuarioLog.getPassword()).equals(us.getPass())) {
                    //CONTROLADOR TABBED ADMINISTRADOR
                    FormAdmSistema fadm = new FormAdmSistema();
                    Usuario usu2 = new Usuario();
                    FormEmpSistema femp = new FormEmpSistema();
                    //FormEmpSistema emp=new FormEmpSistema();
                    ControladorTabbedPane controlTabbedPane = new ControladorTabbedPane(fadm,usu2);
                    fadm.txtNombreAdministrador.setText(us.getIdentificacion());
                    fadm.setVisible(true);
                    log.setVisible(false);
                } else if (us.getRol().equals("Empleado") && log.txtUsLog.getText().equals(us.getUsuario())
                        && String.valueOf(log.jtxPassUsuarioLog.getPassword()).equals(us.getPass())) {
                    //CONTROLADOR MENU EMPLEADO
                    FormEmpSistema femp = new FormEmpSistema();
                    ControladorMenuEmpleado controlMenuEmp = new ControladorMenuEmpleado(femp);
                    femp.mnVolverPanel.setEnabled(false);
                    femp.mnVolverPanel.setVisible(false);
                    femp.jtxNombreEmpleado.setText(us.getIdentificacion());
                    femp.setVisible(true);
                    log.setVisible(false);
                } else if (!us.getRol().equals("Nada")) {
                    JOptionPane.showMessageDialog(null, "Revisa las mayúsculas");
                }
            }
        }

        if (e.getSource() == log.btnSalirUsuario) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas salir?", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == log.jlbVer) {
            log.jlbVer.setVisible(false);
            log.jlbOcultar.setVisible(true);
            log.jtxPassUsuarioLog.setEchoChar((char) 0);
        }

        if (e.getSource() == log.jlbOcultar) {
            log.jlbVer.setVisible(true);
            log.jlbOcultar.setVisible(false);
            log.jtxPassUsuarioLog.setEchoChar('●');
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
