package com.tahsan.eventstorm.fragmentos;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tahsan.eventstorm.R;
import com.tahsan.eventstorm.pojo.LoginRequest;
import com.tahsan.eventstorm.pojo.LoginResponse;
import com.tahsan.eventstorm.utilerias.Utileria;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class RegistrarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    EditText et_usuario;
    EditText et_contrasena;
    ProgressBar loadingProgess;

    public RegistrarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_registrar, container, false);
        et_usuario = view.findViewById(R.id.userReg);
        et_contrasena = view.findViewById(R.id.userCorreo);
        Button button =  view.findViewById(R.id.btn_registrar);
        loadingProgess = view.findViewById(R.id.pb_registrar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registart();
            }
        });
        return view;
    }

    private void showProgressBar() {
        loadingProgess.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        loadingProgess.setVisibility(View.GONE);
    }

    private void registart(){
        String correo = et_usuario.getText().toString().trim();
        String password = et_contrasena.getText().toString().trim();

        if (correo == "" || correo == null) {
            Toast.makeText(getContext(), getActivity().getString(R.string.no_usuario), Toast.LENGTH_SHORT).show();
            return;
        }

        if(password == "" || password == null){
            Toast.makeText(getContext(), getActivity().getString(R.string.no_password), Toast.LENGTH_SHORT).show();
            return;
        }

        showProgressBar();
        String passD5 = Utileria.md5(password);

        final LoginRequest register = new LoginRequest(correo, passD5, "QWERTY" );

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utileria.urlRegistrar,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        Gson gson = new Gson();
                        LoginResponse registerResponse = gson.fromJson(response, LoginResponse.class);
                        if(registerResponse.exito){
                            et_usuario.setText("");
                            et_contrasena.setText("");
                            Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), registerResponse.error, Toast.LENGTH_LONG).show();
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
                params.put("correo", register.getCorreo());
                params.put("passwSistema", register.getPasswSistema());
                params.put("token", register.getToken());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}
