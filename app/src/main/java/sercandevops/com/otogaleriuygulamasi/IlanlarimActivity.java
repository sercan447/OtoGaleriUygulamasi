package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Adapters.IlanlarimAdapter;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarimPojo;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class IlanlarimActivity extends Activity {

    ListView listView;
    IlanlarimAdapter ilanlarimAdapter;
    List<IlanlarimPojo> ilanlarimPojos;
    SharedPreferences sharedPreferences;
    String uye_id;

    AlertDialogClass alertDialogClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);

        listView = (ListView)findViewById(R.id.listview_ilanlar);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        uye_id = sharedPreferences.getString("uye_id",null);
        Log.i("hata",""+uye_id);

        ilanlarimGoruntuleme();

        alertDialogClass = new AlertDialogClass();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                alertDialogClass.IlanlarimAlertDialog(IlanlarimActivity.this,ilanlarimPojos.get(position).getIlanId());

            }
        });
    }

    public void ilanlarimGoruntuleme(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlarım");
        progressDialog.setMessage("İlanlarınız yükleniyor..Lütfen bekleyiniz..");
        progressDialog.setCancelable(false);
        progressDialog.show();


        ilanlarimPojos = new ArrayList<>();
        Call<List<IlanlarimPojo>> request = ManagerALL.getInstance().Ilanlarim(uye_id);

        request.enqueue(new Callback<List<IlanlarimPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarimPojo>> call, Response<List<IlanlarimPojo>> response) {
                
                if(response.body().get(0).isTf()) {
                    ilanlarimPojos = response.body();

                        ilanlarimAdapter = new IlanlarimAdapter(ilanlarimPojos, getApplicationContext(), IlanlarimActivity.this);
                        listView.setAdapter(ilanlarimAdapter);
                    progressDialog.cancel();
                }else{
                    progressDialog.cancel();
                    Intent intent = new Intent(IlanlarimActivity.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {
                Log.i("hata",t.toString());
                Log.i("hata",t.getMessage());
                Toast.makeText(getApplicationContext(),"Hata", Toast.LENGTH_LONG).show();

                progressDialog.cancel();
            }
        });

    }
}
