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


import androidx.navigation.Navigation;

public class AddContact extends Fragment {

    Button saveButton;
    AddressBookDb db;
    EditText editName, editPhone, editEmail, editStreet, editCity, editState, editZip;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        db = new AddressBookDb(getActivity());

        return inflater.inflate(R.layout.fragment_add_contact, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveButton = (Button) view.findViewById(R.id.button);
        saveButton.setEnabled(false);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save(v);
                Navigation.findNavController(v).navigate(R.id.action_AddContact_to_ContactsList);
            }
        });

        editName = (EditText) view.findViewById(R.id.name);
        editEmail = (EditText) view.findViewById(R.id.email);
        editPhone = (EditText) view.findViewById(R.id.phone);
        editStreet = (EditText) view.findViewById(R.id.street);
        editCity = (EditText) view.findViewById(R.id.city);
        editState  = (EditText) view.findViewById(R.id.state);
        editZip = (EditText) view.findViewById(R.id.zip);

        editName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editName = (EditText) view.findViewById(R.id.name);
                String name = editName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    saveButton.setEnabled(false);
                }else {
                    saveButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void Save(View view) {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String phone = editPhone.getText().toString();
        String street =  editStreet.getText().toString();
        String city = editCity.getText().toString();
        String state = editState.getText().toString();
        String zip = editZip.getText().toString();

        this.db.insertData(name,phone,email,street,city,state,zip);
    }
}
