package com.example.savetoword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView i;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i=(ImageView) findViewById(R.id.image1);





    }

    public void submit(View view) {

        try {




            //Word save path


            ContentValues values = new ContentValues();
            String folder="/"+"SaveToWordApp"+"/";
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, "SaveWordFile");       //file name
            values.put(MediaStore.MediaColumns.MIME_TYPE, "application/msword");        //file extension, will automatically add to file
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + folder);     //end "/" is not mandatory
            Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);      //important!

            //word save path end

            OutputStream outputStream = getContentResolver().openOutputStream(uri);

            //image
            Drawable drawable=i.getDrawable();
            bitmap = ((BitmapDrawable)drawable).getBitmap();
         
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,outputStream);

            //writing stream
            outputStream.write(("Hello writing in word file....").getBytes());


            //closing
            outputStream.close();

            //end

            Toast.makeText(MainActivity.this, "File created successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Fail to create file", Toast.LENGTH_SHORT).show();
        }



    }
}