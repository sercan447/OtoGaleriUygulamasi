package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Models.ResimEklePojo;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class IlanResimlerActivity extends Activity {

    Button resimSecBtn,resimEkleButon,cikButon;
    ImageView secilenIlanResimImageView;
    Bitmap bitmap;
    String uye_id,ilan_id,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);

        Tanimlamalar();

        Bundle bundle = getIntent().getExtras();
        uye_id = bundle.getString("uye_id",null);
        ilan_id = bundle.getString("ilan_id",null);

    }

    public void Tanimlamalar(){

        resimSecBtn = findViewById(R.id.resimSecButon);
        resimEkleButon = findViewById(R.id.resimEkleButton);
        cikButon = findViewById(R.id.cikButon);
        secilenIlanResimImageView = (ImageView)findViewById(R.id.secilenIlanResimImageView);

        resimSecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimGoster();
            }
        });

        resimEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yukle();
            }
        });
    }

    public void resimGoster(){
       /* Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);
        */
        secilenIlanResimImageView.setImageResource(R.drawable.rest);
    }

    public void yukle(){
        image = imageToString();
        Call<ResimEklePojo> request = ManagerALL.getInstance().ResimYukle(uye_id,ilan_id,image);

        request.enqueue(new Callback<ResimEklePojo>() {
            @Override
            public void onResponse(Call<ResimEklePojo> call, Response<ResimEklePojo> response) {
                    if(response.body().isTf())
                    {
                        Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<ResimEklePojo> call, Throwable t) {

            }
        });
    }


    public String imageToString(){

        //RESMI BITMAP NESNESINE ATAMA
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.rest);

        //RESMI POST ISLEMI ILE YOLLAMAK ICIN YAPLAN ENCODE ISLEMI
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

            byte[] byt = byteArrayOutputStream.toByteArray();
            String imageToString = Base64.encodeToString(byt,Base64.DEFAULT);


        return imageToString;
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 777 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try{

                bitmap = BitmapFactory.decodeStream(getResources().getDrawable(R.drawable.rest,null));
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                secilenIlanResimImageView.setImageBitmap(bitmap);
                secilenIlanResimImageView.setVisibility(View.VISIBLE);
            }catch (IOException e){

            }
        }
    }*/




}
