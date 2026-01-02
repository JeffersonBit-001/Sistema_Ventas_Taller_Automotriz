/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author JEFFERSON
 */
public class Autos extends TallerAutomotriz {

    private int dni_cliente;
    private String color;
    private int id2;

    

    public Autos() {
    }

    public Autos(int dni_cliente, String color, int id, String cod,
            String nombre, String descripcion, String fecha, String hora, int id2, String ver,String ver2) {
        super(id, cod, nombre, descripcion, fecha, hora,ver,ver2);
        this.dni_cliente = dni_cliente;
        this.color = color;
        this.id2 = id2;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(int dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }



    @Override
    public Object[] Registro() {
        Object[] fila = {super.getId(), super.getCod(), super.getDescripcion(), getColor(), getDni_cliente(), super.getNombre(), super.getHora(),
            super.getFecha()};
        return fila; //ID,PLACA,MODELO,COLOR,DNI,NOMBRECLIENTE,FECHA,,HORA
    }

}
