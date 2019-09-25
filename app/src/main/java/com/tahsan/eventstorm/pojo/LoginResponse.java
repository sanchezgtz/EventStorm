package com.tahsan.eventstorm.pojo;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("error")
    public String error;

    @SerializedName("exito")
    public boolean exito;

    @SerializedName("idUsuario")
    public int idUsuario;

    @SerializedName("correo")
    public String correo;
}
