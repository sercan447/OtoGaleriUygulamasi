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

public class MotorPerformansActivity extends Activity {

    EditText motorTipiBilgiEditText,motorHacmiBilgiEditText,azamiSuratBilgiEditText;
    Button motorBilgisiButon,motorBilgisiButonGeri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_performans);

        Tanimlamalar();
    }

    public void Tanimlamalar(){

        motorTipiBilgiEditText = (EditText)findViewById(R.id.motorTİpiBilgiEditText);
        motorHacmiBilgiEditText = (EditText)findViewById(R.id.motorHacmiBilgiEditText);
        azamiSuratBilgiEditText = (EditText)findViewById(R.id.azamiSuratBilgiEditText);
        motorBilgisiButon = findViewById(R.id.motorBilgisiButon);
        motorBilgisiButonGeri = findViewById(R.id.motorBilgisiButonGeri);

        motorTipiBilgiEditText.setText(IlanVerPojo.getMotortipi());
        motorHacmiBilgiEditText.setText(IlanVerPojo.getMotorhacmi());
        azamiSuratBilgiEditText.setText(IlanVerPojo.getSurat());


        motorBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!motorTipiBilgiEditText.getText().toString().trim().equals("") &&
                        !motorHacmiBilgiEditText.getText().toString().trim().equals("") &&
                        !azamiSuratBilgiEditText.getText().toString().trim().equals("")){

                    IlanVerPojo.setMotortipi(motorTipiBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setMotorhacmi(motorHacmiBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setSurat(azamiSuratBilgiEditText.getText().toString().trim());

                    Intent intent = new Intent(MotorPerformansActivity.this,YakitActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Boşv ar",Toast.LENGTH_LONG).show();
                }

            }
        });

        motorBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","motor perfor TIK");
                Intent intent = new Intent(MotorPerformansActivity.this,AracBilgieriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
            }
        });



    }
}
