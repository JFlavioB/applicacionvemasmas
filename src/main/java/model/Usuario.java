package model;


public class Usuario {
    String nombre;
    String correo;
    String rfc;
    String password;
    String direccion;
    String telefono;
    String web;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String rfc, String password, String direccion, String telefono, String web) {
        this.nombre = nombre;
        this.correo = correo;
        this.rfc = rfc;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.web = web;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", correo=" + correo + ", rfc=" + rfc + ", password=" + password + ", direccion=" + direccion + ", telefono=" + telefono + ", web=" + web + '}';
    }

  
    
    
}

