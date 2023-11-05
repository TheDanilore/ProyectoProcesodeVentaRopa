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
public class login {
    private int ID;
    private String nombre;
    private String correo;
    private String pass;
    private String rol;

    public login() {
    }

    public login(int ID, String nombre, String correo, String pass, String rol) {
        this.ID = ID;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
