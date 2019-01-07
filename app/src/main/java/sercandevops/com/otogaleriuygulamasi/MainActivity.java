package sercandevops.com.otogaleriuygulamasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Adapters.FavoriSliderAdapter;
import sercandevops.com.otogaleriuygulamasi.Models.FavoriSliderPojo;
import sercandevops.com.otogaleriuygulamasi.Models.MesajModel;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    String navHeaderText;
    TextView tvNavHeaderText;
    SharedPreferences.Editor editor;

    Button ilanVerBtn,ilanlarimMenuButon,ilanlarMenuButon,iletisimMenuButton,btnMesajlarGor;

    ViewPager MainilanDetaySlider;
    CircleIndicator MainsliderNokta;
    String uyeId;
    FavoriSliderAdapter favoriSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sharedPreferences = getSharedPreferences("giris",0);
        navHeaderText = sharedPreferences.getString("uye_kullaniciAdi",null);
        uyeId = sharedPreferences.getString("uye_id",null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

    //nav_header_main içerisindeki TextView componentine erişmek için yapıldı. aşağıdaki işlem
        View v = navigationView.getHeaderView(0);
        tvNavHeaderText = v.findViewById(R.id.navHeaderText);
        tvNavHeaderText.setText(navHeaderText);

        navigationView.setNavigationItemSelectedListener(this);

        Tanimlamalar();
        getSlider();

    }

    public void  Tanimlamalar(){
        ilanVerBtn = findViewById(R.id.ilanVerButton);
        ilanlarimMenuButon = findViewById(R.id.ilanlarimMenuButon);
        ilanlarMenuButon = findViewById(R.id.ilanlarMenuButon);
        iletisimMenuButton = (Button)findViewById(R.id.iletisimMenuButton);

        MainilanDetaySlider = (ViewPager)findViewById(R.id.MainilanDetaySlider);
        MainsliderNokta = (CircleIndicator)findViewById(R.id.MainsliderNokta);

        btnMesajlarGor = (Button)findViewById(R.id.btnMesajlarGor);


        ilanVerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
            }
        });

        ilanlarimMenuButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IlanlarimActivity.class);
                startActivity(intent);
            }
        });

        ilanlarMenuButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IlanlarActivity.class);
                startActivity(intent);
            }
        });

        iletisimMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BilgiActivity.class);
                startActivity(intent);
            }
        });
        btnMesajlarGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MesajlarActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public  void getSlider()
    {
        final Call<List<FavoriSliderPojo>> request = ManagerALL.getInstance().setSlider(uyeId);
        request.enqueue(new Callback<List<FavoriSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderPojo>> call, Response<List<FavoriSliderPojo>> response) {

                if(response.body().size() > 0)
                {
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(),getApplicationContext(),MainActivity.this);

                    MainilanDetaySlider.setAdapter(favoriSliderAdapter);
                    MainsliderNokta.setViewPager(MainilanDetaySlider);
                    MainsliderNokta.bringToFront();
                }
            }

            @Override
            public void onFailure(Call<List<FavoriSliderPojo>> call, Throwable t) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_cikis) {

            editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
