package com.tahsan.eventstorm.fragmentos;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tahsan.eventstorm.MainActivity;
import com.tahsan.eventstorm.R;
import com.tahsan.eventstorm.RetrofitClient;
import com.tahsan.eventstorm.pojo.LoginRequest;
import com.tahsan.eventstorm.pojo.LoginResponse;
import com.tahsan.eventstorm.utilerias.Utileria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    EditText et_usuario;
    EditText et_contrasena;
    Button btn_registrar;
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
        showProgressBar();
        String passD5 = Utileria.md5(et_contrasena.getText().toString());

        LoginRequest login = new LoginRequest(et_usuario.getText().toString(), passD5, "QWERTY" );

        RetrofitClient.getServiceClass().registrarUsuario(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful())
                {
                    LoginResponse reponseR = response.body();
                    if(reponseR.exito){
                        Toast.makeText(getContext(), getActivity().getString(R.string.registro_exitoso), Toast.LENGTH_SHORT).show();
                        hideProgressBar();
                    }
                    else{
                        hideProgressBar();
                        Toast.makeText(getContext(), reponseR.error, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    hideProgressBar();
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
