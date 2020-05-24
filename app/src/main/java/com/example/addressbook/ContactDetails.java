package com.example.addressbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContactDetails extends Fragment {

    public ContactDetails() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact_details, container, false);
    }

    public void saveDetails(View view){

    }

    public void deleteContact(View view){

    }
}
