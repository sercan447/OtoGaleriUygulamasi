package sercandevops.com.otogaleriuygulamasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Models.IlanSonucPojo;
import sercandevops.com.otogaleriuygulamasi.Models.IlanVerPojo;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class YakitActivity extends AppCompatActivity {

    EditText yakitTipiBilgiEditText,ortalamaYakitEditText,depoHacmiEditText;
    Button yakitTuketimBilgiButon,yakitTuketimBilgiButonGeri;

    ProgressBar mProgressBar;
    LinearLayout linearLayout;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);

        Tanimlamalar();
    }

    private void Tanimlamalar(){

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        linearLayout = (LinearLayout)findViewById(R.id.yakitInLinearlayout);

        yakitTipiBilgiEditText = (EditText)findViewById(R.id.yakitTipiBilgiEditText);
        ortalamaYakitEditText = (EditText)findViewById(R.id.ortalamaYakitEditText);
        depoHacmiEditText = (EditText)findViewById(R.id.depoHacmiEditText);
        yakitTuketimBilgiButon = findViewById(R.id.yakitTuketimBilgiButon);
        yakitTuketimBilgiButonGeri = findViewById(R.id.yakitTuketimBilgiButonGeri);

        yakitTipiBilgiEditText.setText(IlanVerPojo.getYakittipi());
        ortalamaYakitEditText.setText(IlanVerPojo.getOrtalamayakit());
        depoHacmiEditText.setText(IlanVerPojo.getDepohacmi());

        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        IlanVerPojo.setUye_id(sharedPreferences.getString("uye_id",null));

        yakitTuketimBilgiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgressBar.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);

                IlanVerPojo.setYakittipi(yakitTipiBilgiEditText.getText().toString().trim());
                IlanVerPojo.setOrtalamayakit(ortalamaYakitEditText.getText().toString().trim());
                IlanVerPojo.setDepohacmi(depoHacmiEditText.getText().toString().trim());

                if(!yakitTipiBilgiEditText.getText().toString().equals("")
                        && !ortalamaYakitEditText.getText().toString().trim().equals("")
                        && !depoHacmiEditText.getText().toString().trim().equals("")){
                    ilanYayinla(IlanVerPojo.getUye_id(),
                                IlanVerPojo.getSehir(),
                                IlanVerPojo.getIlce(),
                                IlanVerPojo.getMahalle(),
                                IlanVerPojo.getMarka(),
                                IlanVerPojo.getSeri(),
                                IlanVerPojo.getModel(),
                                IlanVerPojo.getYil(),
                                IlanVerPojo.getIlantipi(),
                                IlanVerPojo.getKimden(),
                                IlanVerPojo.getBaslik(),
                                IlanVerPojo.getMotortipi(),
                                IlanVerPojo.getMotorhacmi(),
                                IlanVerPojo.getSurat(),
                                IlanVerPojo.getYakittipi(),
                                IlanVerPojo.getOrtalamayakit(),
                                IlanVerPojo.getDepohacmi(),
                                IlanVerPojo.getUcret(),
                                IlanVerPojo.getKm(),
                                IlanVerPojo.getAciklama());

                }else{
                    Toast.makeText(getApplicationContext(),"bOŞ VAR",Toast.LENGTH_LONG).show();
                }
            }
        });

        yakitTuketimBilgiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YakitActivity.this,MotorPerformansActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
            }
        });

    }

    public void ilanYayinla(String uye_id, String sehir, String ilce, String mahalle, String marka, String seri,
                            String model, String yil, String ilantipi,String kimden, String baslik, String motortipi,
                            String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi,
                            String ucret,String km,String aciklama){

        Call<IlanSonucPojo> sonuc = ManagerALL.getInstance().IlanVer(uye_id,  sehir,  ilce,  mahalle,  marka,  seri, model,
                                                                      yil,  ilantipi, kimden,  baslik,  motortipi, motorhacmi,
                                                                        surat,  yakittipi,  ortalamayakit,  depohacmi,ucret,km,aciklama);


        sonuc.enqueue(new Callback<IlanSonucPojo>() {
            @Override
            public void onResponse(Call<IlanSonucPojo> call, Response<IlanSonucPojo> response) {

                if(response.body().isTf()){
                    mProgressBar.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE );

                    Toast.makeText(getApplicationContext(),"basarili",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(YakitActivity.this,IlanResimlerActivity.class);
                    intent.putExtra("ilan_id",response.body().getIlanId());
                    intent.putExtra("uye_id",String.valueOf(response.body().getUyeId()));
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"KAYIT YAPILAMADI",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IlanSonucPojo> call, Throwable t) {
                Log.i("HATA",t.getMessage());
                Log.i("HATA",t.toString());
              Toast.makeText(getApplicationContext(),"Hatalandı servis"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }//ILANYAYINLA
}
