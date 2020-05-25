package com.example.addressbook;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactsList extends Fragment {

    AddressBookDb db;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        db = new AddressBookDb(getActivity());

        return inflater.inflate(R.layout.fragment_contacts_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Floating Action Button

        final FloatingActionButton fab = view.findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_ContactsList_to_AddContact);
            }
        });

        Cursor cursor = db.getSortedByName();

        // Contacts ListView
        final ListView list = view.findViewById(R.id.list);
        ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<String> arrayId = new ArrayList<>();


        if (cursor.moveToFirst() && cursor.getCount() > 0){
            do {
                arrayList.add(cursor.getString(cursor.getColumnIndex("NAME")));
                arrayId.add(cursor.getString(cursor.getColumnIndex("ID")));
            }while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("id", arrayId.get(position));
                Navigation.findNavController(view).navigate(R.id.action_ContactsList_to_ContactDetails, bundle);
            }
        });
    }
}
