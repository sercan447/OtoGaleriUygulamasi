package sercandevops.com.otogaleriuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sercandevops.com.otogaleriuygulamasi.AlertDialogClass;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarimPojo;
import sercandevops.com.otogaleriuygulamasi.R;
import sercandevops.com.otogaleriuygulamasi.RestApi.BaseURL;

public class IlanlarimAdapter extends BaseAdapter {

    List<IlanlarimPojo> list;
    Context context;
    Activity activity;
  //  AlertDialogClass alertDialogClass;

    String ilan_id,uye_id;

    public IlanlarimAdapter(List<IlanlarimPojo> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlarimlayout,parent,false);
        ImageView resim;
        TextView baslik,fiyat;
      //  LinearLayout ilanLİnearLayout;

       // ilanLİnearLayout = (LinearLayout)convertView.findViewById(R.id.ilanLİnearLayout);
        resim = (ImageView)convertView.findViewById(R.id.ilanlarimIlanResim);
        baslik = (TextView)convertView.findViewById(R.id.ilanlarimIlanBaslik);
        fiyat = convertView.findViewById(R.id.ilanlarimIlanFiyat);

        ilan_id = list.get(position).getIlanId();
        uye_id = list.get(position).getUyeId();

        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat()+"\n"+list.get(position).getResim());

        Picasso.get().load(BaseURL.URL+"" +list.get(position).getResim()).into(resim);

        //BURADAKI YORUM SATIRLARINI KAPATTIK YERINE ILANLARIMACITVITY DEN TIKLAMA İLE KAYIT SİLME ISLEMI YAPILAACAKTIR.
        /*ilanLİnearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogClass = new AlertDialogClass();
                alertDialogClass.IlanlarimAlertDialog(activity,ilan_id);
            }
        });
        */
        return convertView;
    }


}
