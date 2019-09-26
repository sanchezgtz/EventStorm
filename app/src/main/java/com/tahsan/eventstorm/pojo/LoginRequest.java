package com.tahsan.eventstorm.pojo;

public class LoginRequest {

    final String correo;
    final String passwSistema;
    final String token;

    public String getCorreo() {
        return correo;
    }

    public String getPasswSistema() {
        return passwSistema;
    }

    public String getToken() {
        return token;
    }

    public LoginRequest(String correo, String passwSistema, String token) {
        this.correo = correo;
        this.passwSistema = passwSistema;
        this.token = token;
    }


}
