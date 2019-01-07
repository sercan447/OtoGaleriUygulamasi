package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sercandevops.com.otogaleriuygulamasi.Models.IlanVerPojo;

public class AdresBilgileriActivity extends Activity {

        Button adresBilgiButn,adresBilgiButnGeri;
        EditText sehirBilgiEditText,ilceBilgiEditText,mahalleBİlgiEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);


        Tanimlamalar();
    }

    public void Tanimlamalar(){

        sehirBilgiEditText = (EditText) findViewById(R.id.sehirBilgiEditText);
        ilceBilgiEditText = (EditText)findViewById(R.id.ilceBilgiEditText);
        mahalleBİlgiEditText = (EditText)findViewById(R.id.mahalleBilgiEditText);
        adresBilgiButn = findViewById(R.id.adresBilgisiBtn);
        adresBilgiButnGeri = findViewById(R.id.adresBilgisiBtnGeri);

        sehirBilgiEditText.setText(IlanVerPojo.getSeri());
        ilceBilgiEditText.setText(IlanVerPojo.getIlce());
        mahalleBİlgiEditText.setText(IlanVerPojo.getMahalle());

        adresBilgiButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!sehirBilgiEditText.getText().toString().trim().equals("") && !ilceBilgiEditText.getText().toString().trim().equals("")&&
                        !mahalleBİlgiEditText.getText().toString().trim().equals("")){

                    IlanVerPojo.setSehir(sehirBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setIlce(ilceBilgiEditText.getText().toString().trim());
                    IlanVerPojo.setMahalle(mahalleBİlgiEditText.getText().toString().trim());


                    Intent intent = new Intent(AdresBilgileriActivity.this,AracBilgieriActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();

                }

            }
        });

        adresBilgiButnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdresBilgileriActivity.this,IlanTuruActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);

            }
        });

    }
}
