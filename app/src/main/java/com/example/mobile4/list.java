package com.example.mobile4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    ArrayList<object> items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items=new ArrayList<>();
        if (savedInstanceState != null) {
            items = (ArrayList<object>) savedInstanceState.getSerializable("Ilist");
        }
        objectAdapter adapter = new objectAdapter(list.this, R.layout.notes,items);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(list.this,Details.class);
                intent.putExtra("note",items.get(i).desc);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int listItem, long l) {

                new AlertDialog.Builder(list.this)
                        .setTitle("are you sure you want to delete it ?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                items.remove(listItem);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();

                return true;
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mmenu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View popupView = inflater.inflate(R.layout.popup, null);

                EditText editText = popupView.findViewById(R.id.edit);
                Button addButton = popupView.findViewById(R.id.addbtn);

                builder.setView(popupView);
                AlertDialog dialog = builder.create();

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = editText.getText().toString();
                        // Add the text to the ListView here
                        items.add(new object("TAKHI Maria",text,R.drawable.profil));
                        objectAdapter adapter = new objectAdapter(list.this, R.layout.notes,items);
                        // Attach the adapter to a ListView
                        ListView listView = (ListView) findViewById(R.id.list_view);
                        listView.setAdapter(adapter);
                        listView.setClickable(true);
                        dialog.dismiss();



                    }


                });

                dialog.show();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Ilist",items);
    }

}



