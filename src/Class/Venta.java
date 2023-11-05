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
public class Venta {
    private int ID;
    private String cliente;
    private String vendedor;
    private double total;
    private String fecha;

    public Venta() {
    }

    public Venta(int ID, String cliente, String vendedor, double total, String fecha) {
        this.ID = ID;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.total = total;
        this.fecha = fecha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
