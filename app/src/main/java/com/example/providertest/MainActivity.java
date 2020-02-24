package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加数据
        Button button_add = (Button) findViewById(R.id.add_data);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "zlwzlw");
                values.put("aothor", "zlwzlwzlwzlw");
                values.put("pages", "1024");
                values.put("price", 22.12);
                Uri uri1 = getContentResolver().insert(uri, values);
                Log.d("zlwzlw", uri1.toString() + "");
                newId = uri1.getPathSegments().get(1);
            }
        });

        Button button_query = (Button) findViewById(R.id.query_data);
        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Log.d("MainActivity", "name: " + cursor.getString(cursor.getColumnIndex("name")));
                        Log.d("MainActivity", "anthor " + cursor.getString(cursor.getColumnIndex("aothor")));
                        Log.d("MainActivity", "pages " + cursor.getInt(cursor.getColumnIndex("pages")));
                        Log.d("MainActivity", "price " + cursor.getDouble(cursor.getColumnIndex("price")));
                    }
                }
                if (cursor != null)
                    cursor.close();
            }
        });

        Button button_delete = (Button) findViewById(R.id.delete_data);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                getContentResolver().delete(uri, null, null);
            }
        });

        Button button_update = (Button) findViewById(R.id.update_data);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "wywy");
                values.put("price", 1212.21);
                values.put("pages", 111);
                getContentResolver().update(uri, values, null, null);
            }
        });
    }
}
