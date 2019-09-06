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

import com.tahsan.eventstorm.MainActivity;
import com.tahsan.eventstorm.R;
import com.tahsan.eventstorm.utilerias.Utileria;

public class LoginFragment extends Fragment {

    SharedPreferences sharedpreferences;
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
        if(pass != "")
            et_contrasena.setText(pass);

        check_Recordar.setChecked( pass == "" ? false : true);

        usuario.setText(Utileria.getPreference_String(getContext(),  getString(R.string.preference_username)));

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
        String usr = et_usuario.getText().toString(), password = et_contrasena.getText().toString();
        if (usr == "") {
            Toast.makeText(getContext(), getActivity().getString(R.string.no_usuario), Toast.LENGTH_SHORT).show();
            return;
        }

        if (password == "") {
            Toast.makeText(getContext(), getActivity().getString(R.string.no_password), Toast.LENGTH_SHORT).show();
            return;
        }

        if(check_Recordar.isChecked())
            Utileria.savePreference_String(getContext(), getString(R.string.recordar_password), et_contrasena.getText().toString());

        String passMD5 = Utileria.md5(password);
        showProgressBar();

        if (usr.equals("ricardo.sanchezgtz@gmail.com") && passMD5.equals("34f85ca8ec353d352b8a2d3973a0c5")) {
            Utileria.savePreference_String(getContext(), getString(R.string.preference_username), usr);
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("UsuarioID", 1);
            startActivity(intent);
            hideProgressBar();
            getActivity().finish();
        }
        else{
            hideProgressBar();
            Toast.makeText(getContext(), getString(R.string.error_login), Toast.LENGTH_SHORT).show();
         }
     }
}