package com.example.lenovo.android142;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //declaration
    ImageView imageview;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview=(ImageView)findViewById(R.id.imageView);
        button=(Button)findViewById(R.id.button);

        final ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        //setting the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting the image from the drawble res folder
                Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.user,null);
                //geeting into the bitmap
                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);
                // Find the SD Card path
                File filepath=new File(Environment.getExternalStorageDirectory()+"/Image File/");
                //applying the try n catch
                try {
                    Log.e("path", "path= "+ new File( Environment.getExternalStorageDirectory()
                            + "/SampleImage.png").getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    //creating the new file
                    filepath.createNewFile();
                    //creating the object of fileoutputstream
                    FileOutputStream fileOutputStream=new FileOutputStream(filepath);
                    //converting the data into byte
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    //closing the file
                    fileOutputStream.close();

            } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //showing toast
                Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();


            }
        });


    }
}
