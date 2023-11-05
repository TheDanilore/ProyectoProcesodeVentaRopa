/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author windows2020
 */
public class Venta1 {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int IDVenta(){
        int ID=0;
        String sql="SELECT MAX(ID) FROM ventas";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                ID=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ID;
    }
    public int RegistrarVenta(Venta venta){
        String sql="INSERT INTO ventas (cliente, vendedor, total, fecha ) VALUES (?,?,?,?)";
        
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, venta.getCliente());
            ps.setString(2, venta.getVendedor());
            ps.setDouble(3, venta.getTotal());
            ps.setString(4, venta.getFecha());
            ps.execute();
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public int RegistrarDetalle(Detalle detalle_venta){
        String sql="INSERT INTO detalle (codigo_producto, cantidad, precio, ID_Venta) VALUES (?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, detalle_venta.getCodigo_producto());
            ps.setInt(2, detalle_venta.getCantidad());
            ps.setDouble(3, detalle_venta.getPrecio());
            ps.setInt(4, detalle_venta.getID_Venta());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public boolean ActualizarStock(int cantidad, String codigo){
        String sql="UPDATE productos SET stock=? WHERE codigo=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setString(2, codigo);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List ListarVentas(){
        List<Venta> listaventa = new ArrayList();
        String sql ="SELECT * FROM ventas";
        try{
            con=cn.getConnection();
            ps=  con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Venta venta= new Venta();
                venta.setID(rs.getInt("ID"));
                venta.setCliente(rs.getString("cliente"));
                venta.setVendedor(rs.getString("vendedor"));
                venta.setTotal(rs.getDouble("total"));
                listaventa.add(venta);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return listaventa;
    }
}
