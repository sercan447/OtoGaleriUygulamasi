package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sercandevops.com.otogaleriuygulamasi.Models.IlanVerPojo;

public class AracBilgieriActivity extends Activity {


    Button aracBilgisiButn,aracBilgisiButnGeri;
    EditText markaBilgiEditText,seriBilgiEditText,modelBilgiEditText,yilBilgiEditText,kmBilgiEditText ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgieri);

        Tanimlamalar();
    }

    public void Tanimlamalar(){

        markaBilgiEditText = (EditText)findViewById(R.id.markaBilgiEditText);
        seriBilgiEditText = (EditText)findViewById(R.id.seriBilgiEditText);
        modelBilgiEditText = (EditText)findViewById(R.id.modelBilgiEditText);
        yilBilgiEditText = (EditText)findViewById(R.id.yilBilgiEditText);
        kmBilgiEditText = (EditText)findViewById(R.id.kmBilgiEditText);
        aracBilgisiButn = findViewById(R.id.aracBilgisiButn);
        aracBilgisiButnGeri = findViewById(R.id.aracBilgisiButnGeri);

        markaBilgiEditText.setText(IlanVerPojo.getMarka());
        seriBilgiEditText.setText(IlanVerPojo.getSeri());
        modelBilgiEditText.setText(IlanVerPojo.getModel());
        yilBilgiEditText.setText(IlanVerPojo.getYil());
        kmBilgiEditText.setText(IlanVerPojo.getKm());



        aracBilgisiButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!markaBilgiEditText.getText().toString().trim().equals("") &&
                        !seriBilgiEditText.getText().toString().trim().equals("") &&
                        !modelBilgiEditText.getText().toString().trim().equals("") &&
                        !yilBilgiEditText.getText().toString().trim().equals("")
                        && !kmBilgiEditText.getText().toString().trim().equals("")){

                    IlanVerPojo.setMarka(markaBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setSeri(seriBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setModel(modelBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setYil(yilBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setKm(kmBilgiEditText.getText().toString().trim());


                    Intent intent = new Intent(AracBilgieriActivity.this,MotorPerformansActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"BOŞ VAR", Toast.LENGTH_LONG).show();;
                }


            }
        });

        aracBilgisiButnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG"," aracbilgi TIK");
                Intent intent = new Intent(AracBilgieriActivity.this,AdresBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
            }
        });
    }
}
