package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarimSilPojo;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class AlertDialogClass {

    public void IlanlarimAlertDialog(final Activity activity, final String ilan_id)
    {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertlayout,null);

        Button btnSil = view.findViewById(R.id.btnSil);
        Button btnCik = view.findViewById(R.id.btncikis);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(ilan_id,activity);
                dialog.cancel();
            }
        });
        btnCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void sil(String ilanId, final Activity activity)
    {
        Call<IlanlarimSilPojo> request = ManagerALL.getInstance().IlanSil(ilanId);
        request.enqueue(new Callback<IlanlarimSilPojo>() {
            @Override
            public void onResponse(Call<IlanlarimSilPojo> call, Response<IlanlarimSilPojo> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(activity,"silindi"+response.body().getResult(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IlanlarimSilPojo> call, Throwable t) {
                Toast.makeText(activity,"HTA"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }//sil

}
