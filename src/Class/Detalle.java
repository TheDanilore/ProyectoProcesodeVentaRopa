/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author windows2020
 */
public class Detalle {
    private int ID;
    private String codigo_producto;
    private int cantidad;
    private double precio;
    private int ID_Venta;

    public Detalle() {
    }

    public Detalle(int ID, String codigo_producto, int cantidad, double precio, int ID_Venta) {
        this.ID = ID;
        this.codigo_producto = codigo_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.ID_Venta = ID_Venta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getID_Venta() {
        return ID_Venta;
    }

    public void setID_Venta(int ID_Venta) {
        this.ID_Venta = ID_Venta;
    }

    
    
    
}
