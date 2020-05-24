package com.example.addressbook;

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

public class ContactDetails extends Fragment {

    ImageButton deleteButton;
    AddressBookDb db;

    public ContactDetails() {
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

        deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.deleteData();
                Navigation.findNavController(v).navigate(R.id.action_ContactDetails_to_ContactsList);
            }
        });
    }

    public void saveDetails(View view){

    }

    public void deleteContact(View view){

    }
}
