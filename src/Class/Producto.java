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
public class Producto {
    
    private int ID;
    private String codigo;
    private String nombre;
    private String proveedor;
    private int stock;
    private double precio;

    public Producto() {
    }

    public Producto(int ID, String codigo, String nombre, String proveedor, int stock, double precio) {
        this.ID = ID;
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.stock = stock;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
