package sercandevops.com.otogaleriuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sercandevops.com.otogaleriuygulamasi.Models.IlanVerPojo;

public class IlanBilgileriActivity extends AppCompatActivity {


    Button ilanBilgisiButton,ilanBilgisiButtonGeri;
    EditText ilanAciklamaEditText,ilanBaslikEditText,ilanUcretEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_bilgileri);

        Tanimlamalar();

    }

    public void Tanimlamalar(){

        ilanAciklamaEditText = (EditText)findViewById(R.id.ilanAciklamaEditText);
        ilanBaslikEditText = (EditText)findViewById(R.id.ilanBaslikEditText);
        ilanUcretEditText = (EditText)findViewById(R.id.ilanUcretEditText);

        ilanBilgisiButton = findViewById(R.id.ilanBilgisiButon);
        ilanBilgisiButtonGeri = findViewById(R.id.ilanBilgisiButonGeri);

        ilanBaslikEditText.setText(IlanVerPojo.getBaslik());
        ilanAciklamaEditText.setText(IlanVerPojo.getAciklama());
        ilanUcretEditText.setText(IlanVerPojo.getUcret());

        ilanBilgisiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

    ;
   ;


                if(!ilanBaslikEditText.getText().toString().trim().equals("") &&
                        !ilanAciklamaEditText.getText().toString().trim().equals("") &&
                        !ilanUcretEditText.getText().toString().trim().equals("")){

                    IlanVerPojo.setAciklama( ilanAciklamaEditText.getText().toString().trim());
                    IlanVerPojo.setBaslik( ilanBaslikEditText.getText().toString().trim());
                    IlanVerPojo.setUcret(ilanUcretEditText.getText().toString().trim());

                    Intent intent = new Intent(IlanBilgileriActivity.this,IlanTuruActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                }else{
                    Toast.makeText(getApplicationContext(),"BOSLAR",Toast.LENGTH_LONG).show();
                }


            }
        });

        ilanBilgisiButtonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IlanBilgileriActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
            }
        });

    }
}
