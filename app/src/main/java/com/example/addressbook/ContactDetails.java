package com.example.addressbook;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ContactDetails extends Fragment {

    String id;
    TextInputEditText name;
    TextInputEditText phone;
    TextInputEditText email;
    TextInputEditText zip;
    TextInputEditText city;
    TextInputEditText street;
    TextInputEditText state;

    ImageButton deleteButton;
    ImageButton updateButton;
    AddressBookDb db;

    public ContactDetails() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new AddressBookDb(getActivity());

        return inflater.inflate(R.layout.fragment_contact_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name= (TextInputEditText) view.findViewById(R.id.text_edit_name);
        phone= (TextInputEditText) view.findViewById(R.id.text_edit_phone);
        email= (TextInputEditText) view.findViewById(R.id.text_edit_email);
        zip= (TextInputEditText) view.findViewById(R.id.text_edit_zip);
        city= (TextInputEditText) view.findViewById(R.id.text_edit_city);
        state= (TextInputEditText) view.findViewById(R.id.text_edit_state);
        street= (TextInputEditText) view.findViewById(R.id.text_edit_street);

        deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
        updateButton = (ImageButton) view.findViewById(R.id.saveButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                saveDetails();
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(id);
                Navigation.findNavController(v).navigate(R.id.action_ContactDetails_to_ContactsList);
            }
        });

        id = getArguments().getString("id");

        getDetails();
    }

    public void saveDetails(){
        db.updateData(id, name.getText().toString(),phone.getText().toString(),email.getText().toString(),
                street.getText().toString(),city.getText().toString(),
                state.getText().toString(),zip.getText().toString());

        Toast message = Toast.makeText(getContext(),"Updated", Toast.LENGTH_SHORT);
        message.show();
    }


    public void getDetails(){
        Cursor  contactData = db.getById(id);
        contactData.moveToFirst();

        name.setText(contactData.getString(contactData.getColumnIndex("NAME")));
        phone.setText(contactData.getString(contactData.getColumnIndex("Phone")));
        email.setText(contactData.getString(contactData.getColumnIndex("Email")));
        city.setText(contactData.getString(contactData.getColumnIndex("City")));
        state.setText(contactData.getString(contactData.getColumnIndex("State")));
        street.setText(contactData.getString(contactData.getColumnIndex("Street")));
        zip.setText(contactData.getString(contactData.getColumnIndex("Zip")));

        contactData.close();
    }
}
