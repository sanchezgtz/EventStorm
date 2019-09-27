package com.tahsan.eventstorm.fragmentos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tahsan.eventstorm.MainActivity;
import com.tahsan.eventstorm.R;
import com.tahsan.eventstorm.pojo.LoginRequest;
import com.tahsan.eventstorm.pojo.LoginResponse;
import com.tahsan.eventstorm.utilerias.Utileria;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {

    EditText et_usuario;
    EditText et_contrasena;
    Button btn_entrar;
    ProgressBar loadingProgess;
    CheckBox check_Recordar;

    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        EditText usuario = view.findViewById(R.id.et_usr_login);
        final EditText et_contrasena = view.findViewById(R.id.et_usr_pass);
        loadingProgess = view.findViewById(R.id.pb_login);

        check_Recordar = view.findViewById(R.id.checkBox_remember);

        String pass = Utileria.getPreference_String(getContext(),  getString(R.string.recordar_password));

        if(pass != "")  et_contrasena.setText(pass);

        check_Recordar.setChecked( pass == "" ? false : true);
        String usuarioString = Utileria.getPreference_String(getContext(),  getString(R.string.preference_username));

        if(usuarioString != ""){
            check_Recordar.setEnabled(true);
            usuario.setText(usuarioString);
        }
        else{

            check_Recordar.setEnabled(false);
        }


        Button button =  view.findViewById(R.id.btn_entrar);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                entrar();
            }
        });
        check_Recordar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(et_contrasena.getText().toString()!=""){
                    if(view.getId() == R.id.checkBox_remember){
                        if (checked)
                        {
                            Utileria.savePreference_String(getContext(), getString(R.string.recordar_password), et_contrasena.getText().toString());
                        }
                        else{
                            Utileria.deletePreference_String(getContext(), getString(R.string.recordar_password));
                        }
                    }
                }
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        et_usuario = getView().findViewById(R.id.et_usr_login);
        et_contrasena = getView().findViewById(R.id.et_usr_pass);
        btn_entrar = getView().findViewById(R.id.btn_entrar);
    }

    private void showProgressBar() {
        loadingProgess.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        loadingProgess.setVisibility(View.GONE);
    }

    private void entrar() {
        String usr = et_usuario.getText().toString().trim();
        String password = et_contrasena.getText().toString().trim();
        if (usr == "" || usr == null) {
            Toast.makeText(getContext(), getActivity().getString(R.string.no_usuario), Toast.LENGTH_SHORT).show();
            return;
        }

        if (password == "" || password == null) {
            Toast.makeText(getContext(), getActivity().getString(R.string.no_password), Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Utileria.isValidEmail(usr)){
            Toast.makeText(getContext(), getActivity().getString(R.string.correo_incorrecto), Toast.LENGTH_SHORT).show();
            return;
        }

        if(check_Recordar.isChecked()){
            Utileria.savePreference_String(getContext(), getString(R.string.recordar_password), et_contrasena.getText().toString());
        }


        String passMD5 = Utileria.md5(password);
        showProgressBar();

        final LoginRequest loginR = new LoginRequest(usr, passMD5,"QWERTY" );

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utileria.urlLogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        Gson gson = new Gson();
                        LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                        if(loginResponse.exito){
                            Utileria.savePreference_String(getContext(), getString(R.string.preference_username), loginResponse.correo);
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.putExtra("UsuarioID", loginResponse.idUsuario);
                            startActivity(intent);
                            hideProgressBar();
                            getActivity().finish();
                        }
                        else{
                            Toast.makeText(getContext(), loginResponse.error, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressBar();
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("correo", loginR.getCorreo());
                params.put("passwSistema", loginR.getPasswSistema());
                params.put("token", loginR.getToken());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
     }
}