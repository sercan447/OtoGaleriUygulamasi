package sercandevops.com.otogaleriuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sercandevops.com.otogaleriuygulamasi.Models.IlanlarPojo;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarimPojo;
import sercandevops.com.otogaleriuygulamasi.R;
import sercandevops.com.otogaleriuygulamasi.RestApi.BaseURL;

public class IlanlarAdapter extends BaseAdapter {

    List<IlanlarPojo> list;
    Context context;
    Activity activity;


    public IlanlarAdapter(List<IlanlarPojo> list, Context context, Activity activity) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlarlayout,parent,false);
        ImageView resim;
        TextView baslik,fiyat,adres;

        resim = (ImageView)convertView.findViewById(R.id.ilanlarIlanResim);
        baslik = (TextView)convertView.findViewById(R.id.ilanlarIlanBaslik);
        adres = (TextView)convertView.findViewById(R.id.ilanlarIlanAdres);
        fiyat = convertView.findViewById(R.id.ilanlarIlanFiyat);

        baslik.setText(list.get(position).getBaslik()+"-"+list.get(position).getIlanid());
        fiyat.setText(list.get(position).getFiyat()+"\n"+list.get(position).getResim());
        adres.setText(list.get(position).getIl()+" / "+list.get(position).getIlce()+" - "+list.get(position).getMahalle());

        Picasso.get().load(BaseURL.URL+"" +list.get(position).getResim()).into(resim);

        return convertView;
    }


}
