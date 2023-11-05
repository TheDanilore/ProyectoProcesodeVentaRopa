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
import javax.swing.JOptionPane;

/**
 *
 * @author windows2020
 */
public class Cliente1 {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarCliente(Cliente cliente){
        String sql = "INSERT INTO clientes (DNI,nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cliente.getDNI());
            ps.setString(2, cliente.getNombre());
            ps.setInt(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getRazon());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarCliente(){
        List<Cliente> listacliente= new ArrayList();
        String sql="SELECT * FROM clientes";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setID(rs.getInt("ID"));
                cliente.setDNI(rs.getInt("DNI"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getInt("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setRazon(rs.getString("razon"));
                listacliente.add(cliente);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return listacliente;
    }
    
    public boolean EliminarCliente(int ID){
        String sql ="DELETE FROM clientes WHERE ID = ?";
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
    
    public boolean ModificarCliente(Cliente cliente){
        String sql="UPDATE clientes SET DNI=?, nombre=?, telefono=?, direccion=?,razon=? WHERE ID=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, cliente.getDNI());
            ps.setString(2, cliente.getNombre());
            ps.setInt(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getRazon());
            ps.setInt(6, cliente.getID());
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
    
    public Cliente BuscarCliente(int DNI){
        Cliente cliente = new Cliente();
        String sql ="SELECT * FROM clientes WHERE DNI=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, DNI);
            rs=ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getInt("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setRazon(rs.getString("razon"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return cliente;
    }
}
