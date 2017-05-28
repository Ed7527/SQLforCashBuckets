package com.edwardtorpy.sqlforcashbuckets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase sqldatabase = this.openOrCreateDatabase("Database", MODE_PRIVATE, null);

            sqldatabase.execSQL("CREATE TABLE IF NOT EXISTS bucketTable (id INTEGER PRIMARY KEY, maxCash INTEGER(8), currentCash INTEGER(8), date DATETIME)");

            Cursor cursor = sqldatabase.rawQuery("SELECT * FROM bucketTable", null);

            int idIndex = cursor.getColumnIndex("id");
            int maxCashIndex = cursor.getColumnIndex("maxCash");
            int currentCashIndex = cursor.getColumnIndex("currentCash");

            if (cursor.moveToFirst()) {
                System.out.println("Something");

            } else {
                System.out.println("null");

                for (int bucketID = 1; bucketID <17; bucketID++){
                    sqldatabase.execSQL("INSERT INTO bucketTable (maxCash, currentCash) VALUES (69,3)");
                    System.out.println("Inserted id: " + bucketID);
                }
            }

            cursor.moveToFirst();
            while (cursor != null) {
                System.out.println("id: " + cursor.getInt(idIndex) + "max: " + cursor.getInt(maxCashIndex));
                cursor.moveToNext();
            }

            cursor.close();

            Toast.makeText(this, "id column: " + idIndex + "\nmax column: " + maxCashIndex + "\ncurrent column: " + currentCashIndex, Toast.LENGTH_LONG).show();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "NOT WORKING", Toast.LENGTH_SHORT).show();
        }
    }
}
