/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author JEFFERSON
 */
public class Servicios extends TallerAutomotriz{

    private double precio;
    
    public Servicios() {
    }

    public Servicios(double precio, int id, String cod, String nombre, String descripcion, String fecha, 
            String hora,String ver,String ver2) {
        super(id, cod, nombre, descripcion, fecha, hora,ver,ver2);
        this.precio = precio;
    }

 
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public Object[] Registro() {
      Object[] fila = {super.getId(), super.getNombre(), super.getDescripcion(),getPrecio(),super.getHora(),
            super.getFecha()};
        return fila; //ID,NOMBRE,DESCRIPCION,PRECIO,FECHA,HORA
    }

    
}
