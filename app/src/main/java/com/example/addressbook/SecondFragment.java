package com.example.addressbook;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.EditText;
import android.widget.TextView;


import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {
    Button btn ;
    AdressBookDb db ;
    EditText editName, editPhone, editEmail, editStreet, editCity, editState, editZip;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        db = new AdressBookDb(getActivity());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void Save(View view) {
        editName = (EditText) view.findViewById(R.id.name);
        String name =editName.getText().toString();
        if (TextUtils.isEmpty(name)) {


editEmail = (EditText) view.findViewById(R.id.email);
String email = editEmail.getText().toString();

editPhone = (EditText) view.findViewById(R.id.phone);
String phone = editPhone.getText().toString();

editStreet = (EditText) view.findViewById(R.id.street);
String street =  editStreet.getText().toString();

editCity = (EditText) view.findViewById(R.id.city);
String city = editCity.getText().toString();

editState  = (EditText) view.findViewById(R.id.state);
String state = editState.getText().toString();

editZip = (EditText) view.findViewById(R.id.zip);

String zip = editZip.getText().toString();

this.db.insertData(name,phone,email,street,city,state,zip);


        } else{

        }

    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn= (Button)  view.findViewById(R.id.button);
        btn.setEnabled(false);
editName.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        editName = (EditText) view.findViewById(R.id.name);
        String name =editName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            btn.setEnabled(false);
        }else {
            btn.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});

    }
}
