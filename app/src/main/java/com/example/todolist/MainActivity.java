package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemEdit;
    private Button btn;
    private ListView itemList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemEdit = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_button);
        itemList = findViewById(R.id.items_list);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);

        btn.setOnClickListener(this);
        itemList.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_button:
                String itemEntered = itemEdit.getText().toString();
                adapter.add(itemEntered);
                itemEdit.setText("");
                FileHelper.writeData(items, this);
                Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(items, this);
        Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show();
    }
}
