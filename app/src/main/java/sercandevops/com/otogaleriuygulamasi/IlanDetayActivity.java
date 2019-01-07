package sercandevops.com.otogaleriuygulamasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Adapters.SliderAdapter;
import sercandevops.com.otogaleriuygulamasi.Models.FavoriIslem;
import sercandevops.com.otogaleriuygulamasi.Models.FavoriKontrol;
import sercandevops.com.otogaleriuygulamasi.Models.IlanDetayPojo;
import sercandevops.com.otogaleriuygulamasi.Models.OtherId;
import sercandevops.com.otogaleriuygulamasi.Models.SliderPojo;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class IlanDetayActivity extends AppCompatActivity {

    ViewPager ilanDetaySlider;
    TextView ilanDetaySehir,ilanDetayIlce,ilanDetayMahalle,ilanDetayMarka,ilanDetaySeri,ilanDetayModel,ilanDetayYil,
            ilanDetayIlanTipi,ilanDetayKimden,ilanDetayBaslik,ilanDetayMotorTipi,ilanDetayMotorHacmi,ilanDetaySurat,
            ilanDetayYakitTipi,ilanDetayOrtYakit,ilanDetayDepHacmi,ilanDetayKm,ilanDetayUcret;
    Button btnMesajGonder,btnDetayFavoriAl;

    List<SliderPojo> list;
    SliderAdapter sliderAdapter;
    String ilanId;
    CircleIndicator circleIndicator;

    SharedPreferences sharedPreferences;
    String uyeId,otherId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ilan_detay);

        sharedPreferences = this.getSharedPreferences("giris",0);
        uyeId = sharedPreferences.getString("uye_id",null);

        Bundle bundle = getIntent().getExtras();
        ilanId = bundle.getString("ilanid",null);
        Log.i("KTY",""+ilanId);
        Tanimlamalar();
        ilanDetayGoruntule(ilanId);

        getResim();
        FavoriBtnText();
            this.FavoriBtnAksiyon();

    }

    public void Tanimlamalar(){

        ilanDetaySlider = (ViewPager)findViewById(R.id.ilanDetaySlider);

        ilanDetaySehir = (TextView)findViewById(R.id.ilanDetaySehir);
        ilanDetayIlce = (TextView)findViewById(R.id.ilanDetayIlce);
        ilanDetayMahalle = (TextView)findViewById(R.id.ilanDetayMahalle);
        ilanDetayMarka = (TextView)findViewById(R.id.ilanDetayMarka);
        ilanDetaySeri = (TextView)findViewById(R.id.ilanDetaySeri);
        ilanDetayModel = (TextView)findViewById(R.id.ilanDetayModel);
        ilanDetayYil = (TextView)findViewById(R.id.ilanDetayYil);
        ilanDetayIlanTipi = (TextView)findViewById(R.id.ilanDetayIlanTipi);
        ilanDetayKimden = (TextView)findViewById(R.id.ilanDetayKimden);
        ilanDetayBaslik = (TextView)findViewById(R.id.ilanDetayBaslik);
        ilanDetayMotorTipi = (TextView)findViewById(R.id.ilanDetayMotorTipi);
        ilanDetayMotorHacmi = (TextView)findViewById(R.id.ilanDetayMotorHacmi);
        ilanDetaySurat = (TextView)findViewById(R.id.ilanDetaySurat);
        ilanDetayYakitTipi = (TextView)findViewById(R.id.ilanDetayYakitTipi);
        ilanDetayOrtYakit = (TextView)findViewById(R.id.ilanDetayOrtYakit);
        ilanDetayDepHacmi = (TextView)findViewById(R.id.ilanDetayDepHacmi);
        ilanDetayKm = (TextView)findViewById(R.id.ilanDetayKm);
        ilanDetayUcret = (TextView)findViewById(R.id.ilanDetayUcret);

        btnMesajGonder = (Button)findViewById(R.id.btnMesajGonder);
        btnDetayFavoriAl = (Button)findViewById(R.id.btnDetayFavoriAl);

        circleIndicator = (CircleIndicator)findViewById(R.id.sliderNokta);



    }

    public void ilanDetayGoruntule(String ilanid){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ilanlar");
        progressDialog.setMessage("Ilan Detay Getiriliyor.. LÜtfen bekle.");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final Call<IlanDetayPojo> request = ManagerALL.getInstance().IlanDetay(ilanid);
        request.enqueue(new Callback<IlanDetayPojo>() {
            @Override
            public void onResponse(Call<IlanDetayPojo> call, Response<IlanDetayPojo> response) {
                progressDialog.cancel();

                if(response.isSuccessful()){
                    ilanDetaySehir.setText(response.body().getSehir());
                    ilanDetayIlce.setText(response.body().getIlce());
                    ilanDetayMahalle.setText(response.body().getMahalle());
                    ilanDetayMarka.setText(response.body().getMarka());
                    ilanDetaySeri.setText(response.body().getSehir());
                    ilanDetayModel.setText(response.body().getModel());
                    ilanDetayYil.setText(response.body().getYil());
                    ilanDetayIlanTipi.setText(response.body().getIlantipi());
                    ilanDetayKimden.setText(response.body().getKimden());
                    ilanDetayBaslik.setText(response.body().getBaslik());
                    ilanDetayMotorTipi.setText(response.body().getMotortipi());
                    ilanDetayMotorHacmi .setText(response.body().getMotorhacmi());
                    ilanDetaySurat .setText(response.body().getSurat());
                    ilanDetayYakitTipi.setText(response.body().getYakittipi());
                    ilanDetayOrtYakit.setText(response.body().getOrtalamayakit());
                    ilanDetayDepHacmi.setText(response.body().getDepohacmi());
                    ilanDetayKm .setText(response.body().getKm());
                    ilanDetayUcret .setText(response.body().getUcret());
                    otherId = response.body().getUyeId();

                }
            }

            @Override
            public void onFailure(Call<IlanDetayPojo> call, Throwable t) {

                progressDialog.cancel();
                Toast.makeText(getApplicationContext(),"HATA",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getResim(){
        Call<List<SliderPojo>> request = ManagerALL.getInstance().IlanResimleri(ilanId);
        request.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {
                Log.i("TT","BASARILI");
                list = response.body();
                sliderAdapter = new SliderAdapter(list,getApplicationContext());
                ilanDetaySlider.setAdapter(sliderAdapter);

                circleIndicator.setViewPager(ilanDetaySlider);
                circleIndicator.bringToFront();

            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {
                Log.i("TT","problem");
            }
        });
    }

    public void FavoriBtnText()
    {
        Call<FavoriKontrol> request = ManagerALL.getInstance().FavoriKontrolTEXT(uyeId,ilanId);
        request.enqueue(new Callback<FavoriKontrol>() {
            @Override
            public void onResponse(Call<FavoriKontrol> call, Response<FavoriKontrol> response) {

                if(response.body().isTf())
                {
                 btnDetayFavoriAl.setText(response.body().getText());
                }else {
                    btnDetayFavoriAl.setText(response.body().getText());
                }
            }

            @Override
            public void onFailure(Call<FavoriKontrol> call, Throwable t) {

            }
        });

    }//FAVORİ BUTN TEXT

    public void FavoriBtnAksiyon()
    {
            btnDetayFavoriAl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Call<FavoriIslem> request = ManagerALL.getInstance().FavoriIslemYap(uyeId,ilanId);
                    request.enqueue(new Callback<FavoriIslem>() {
                        @Override
                        public void onResponse(Call<FavoriIslem> call, Response<FavoriIslem> response) {

                            if(response.body().isTf())
                            {
                                Toast.makeText(getApplicationContext(),"ye",Toast.LENGTH_LONG).show();
                                FavoriBtnText();
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_LONG).show();
                                FavoriBtnText();
                            }
                        }

                        @Override
                        public void onFailure(Call<FavoriIslem> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"HATALANDI FAVORI TIKLANMA OLAYI",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });


            btnMesajGonder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),ChatActivity.class);

                    OtherId.setOtherId(otherId);

                    startActivity(intent);
                }
            });
    }//BTN FUNC
}
