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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author windows2020
 */
public class Proveedor1 {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProveedor(Proveedor proveedor){
        String sql="INSERT INTO proveedor (ruc, nombre, telefono,direccion, razon) VALUES (?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setInt(1, proveedor.getRuc());
            ps.setString(2, proveedor.getNombre());
            ps.setInt(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getRazon());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarProveedor(){
        List<Proveedor> listaproveedor = new ArrayList();
        String sql ="SELECT * FROM proveedor";
        try{
            con=cn.getConnection();
            ps=  con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Proveedor proveedor= new Proveedor();
                proveedor.setID(rs.getInt("ID"));
                proveedor.setRuc(rs.getInt("ruc"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getInt("telefono"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setRazon(rs.getString("razon"));
                listaproveedor.add(proveedor);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return listaproveedor;
    }
    
    public boolean EliminarProveedor(int ID){
        String sql="DELETE FROM proveedor WHERE ID=? ";
        try{
            con=cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setInt(1, ID);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor proveedor){
        String sql="UPDATE proveedor SET ruc=?,nombre=?, telefono=?,direccion=?,razon=? WHERE ID=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, proveedor.getRuc());
            ps.setString(2, proveedor.getNombre());
            ps.setInt(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getRazon());
            ps.setInt(6, proveedor.getID());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
}
