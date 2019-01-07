package sercandevops.com.otogaleriuygulamasi;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Models.User;
import sercandevops.com.otogaleriuygulamasi.Models.UserUpdate;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class BilgiActivity extends AppCompatActivity {

    EditText userbilgi_username,userbilgi_pass;
    Button userbilgi_buton;
    SharedPreferences sharedPreferences;
    String uye_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi);

        Tanimlama();

        sharedPreferences = this.getSharedPreferences("giris",0);
        uye_id = sharedPreferences.getString("uye_id",null);

        istekAt(uye_id);

        userbilgi_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change(uye_id,userbilgi_username.getText().toString(),userbilgi_pass.getText().toString());
            }
        });
    }

    public void Tanimlama()
    {
        userbilgi_username = (EditText)findViewById(R.id.userbilgi_username);
        userbilgi_pass = (EditText)findViewById(R.id.userbilgi_pass);
        userbilgi_buton = (Button)findViewById(R.id.userbilgi_btn);


    }

    public void istekAt(String uye_id)
    {
        Call<User> request = ManagerALL.getInstance().KisiBilgi(uye_id);
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful())
                {
                    userbilgi_username.setText(response.body().getKadi());
                    userbilgi_pass.setText(response.body().getSifre());
                    Log.i("TAG","GİRDİ");
                }
                Log.i("TAG"," IF DISI GİRDİ");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("TAG","hata : "+t.toString());
            }
        });
    }


    public void change(String userid,String user,String pass)
    {
        Call<UserUpdate> request = ManagerALL.getInstance().bilgiDegistir(userid,user, pass);
        request.enqueue(new Callback<UserUpdate>() {
            @Override
            public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {

                if(response.body().isTf())
                {
                    finish();
                }
                Toast.makeText(getApplicationContext(),"oldu",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserUpdate> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"hata"+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
