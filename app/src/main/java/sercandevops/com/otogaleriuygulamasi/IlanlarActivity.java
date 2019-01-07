package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Adapters.IlanlarAdapter;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarPojo;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class IlanlarActivity extends AppCompatActivity {


    ListView listView;
    IlanlarAdapter adapter;
    List<IlanlarPojo> ilanlarPojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.ilanlarListView);

        IlanlarGoruntule();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(IlanlarActivity.this,IlanDetayActivity.class);
                intent.putExtra("ilanid",""+ilanlarPojoList.get(position).getIlanid());
                startActivity(intent);
            }
        });
    }

    private void IlanlarGoruntule(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ilanlar");
        progressDialog.setMessage("Ilanlar Listeleniyor., Lütfen Bekleyiniz..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        retrofit2.Call<List<IlanlarPojo>> request = ManagerALL.getInstance().Ilanlar();
        request.enqueue(new Callback<List<IlanlarPojo>>() {
            @Override
            public void onResponse(retrofit2.Call<List<IlanlarPojo>> call, Response<List<IlanlarPojo>> response) {
                 ilanlarPojoList = response.body();
                adapter = new IlanlarAdapter(ilanlarPojoList,getApplicationContext(),IlanlarActivity.this);

                listView.setAdapter(adapter);
                progressDialog.cancel();
            }

            @Override
            public void onFailure(retrofit2.Call<List<IlanlarPojo>> call, Throwable t) {
                Log.i("TTT",t.getMessage());
                progressDialog.cancel();
            }
        });

    }//FUNC

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
