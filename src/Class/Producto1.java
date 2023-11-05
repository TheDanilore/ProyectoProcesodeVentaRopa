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
import javax.swing.JComboBox;


/**
 *
 * @author windows2020
 */
public class Producto1 {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProducto(Producto producto){
        String sql="INSERT INTO productos (codigo,nombre,proveedor,stock,precio) VALUES (?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getProveedor());
            ps.setInt(4, producto.getStock());
            ps.setDouble(5, producto.getPrecio());
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
    
    public void ConsultarProveedor(JComboBox proveedor){
        String sql="SELECT nombre FROM proveedor";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                proveedor.addItem(rs.getString("nombre"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
    }
    
    public List ListarProducto(){
        List<Producto> listaproducto = new ArrayList();
        String sql ="SELECT * FROM productos";
        try{
            con=cn.getConnection();
            ps=  con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto producto= new Producto();
                producto.setID(rs.getInt("ID"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setProveedor(rs.getString("proveedor"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecio(rs.getDouble("precio"));
                listaproducto.add(producto);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return listaproducto;
    }
    
    public boolean EliminarProducto(int ID){
        String sql ="DELETE FROM productos WHERE ID = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ModificarProducto(Producto producto){
        String sql="UPDATE productos SET codigo=?, nombre=?, proveedor=?, stock=?,precio=? WHERE ID=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getProveedor());
            ps.setInt(4, producto.getStock());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getID());
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
    
    public Producto BuscarProductos(String codigo){
        Producto producto = new Producto();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try{
            con= cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs=ps.executeQuery();
            if(rs.next()){
                producto.setNombre(rs.getNString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return producto;
    }
    
    public Configuracion BuscarDatos(){
        Configuracion configuracion = new Configuracion();
        String sql = "SELECT * FROM configuracion";
        try{
            con= cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                configuracion.setID(rs.getInt("ID"));
                configuracion.setRuc(rs.getInt("ruc"));
                configuracion.setNombre(rs.getString("nombre"));
                configuracion.setTelefono(rs.getInt("telefono"));
                configuracion.setDireccion(rs.getString("direccion"));
                configuracion.setRazon(rs.getString("razon"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return configuracion;
    }
    
    public boolean ModificarDatos(Configuracion configuracion){
        String sql="UPDATE configuracion SET ruc=?, nombre=?, telefono=?, direccion=?,razon=? WHERE ID=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, configuracion.getRuc());
            ps.setString(2, configuracion.getNombre());
            ps.setInt(3, configuracion.getTelefono());
            ps.setString(4, configuracion.getDireccion());
            ps.setString(5, configuracion.getRazon());
            ps.setInt(6, configuracion.getID());
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
