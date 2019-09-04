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

import com.tahsan.eventstorm.R;
import com.tahsan.eventstorm.utilerias.Utileria;

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
        String userMD5 = Utileria.md5(et_usuario.getText().toString());
        Toast.makeText(getContext(), getActivity().getString(R.string.registro_exitoso), Toast.LENGTH_SHORT).show();
        hideProgressBar();
    }

}
