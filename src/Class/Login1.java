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

/**
 *
 * @author windows2020
 */
public class Login1 {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion cn= new Conexion();
    
    public login log(String correo, String pass){
        login login= new login();
        String sql="SELECT * FROM usuarios WHERE correo=? AND pass=?";
        
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs= ps.executeQuery();
            if(rs.next()){
                login.setID(rs.getInt("ID"));
                login.setNombre(rs.getString("nombre"));
                login.setCorreo(rs.getString("correo"));
                login.setPass(rs.getString("pass"));
                login.setRol(rs.getString("rol"));
                
            }
        }catch(SQLException e){
                System.out.println(e.toString());
        }
        return login;
    }
    public boolean Registrar (login reg ) {
        String sql  = "insert into usuarios (nombre, correo, pass, rol) VALUES (?,?,?,?)";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());
            ps.execute();
            return true;
        } catch (SQLException e){
            System.out.print(e.toString());
            return false;
        }
    }
}
