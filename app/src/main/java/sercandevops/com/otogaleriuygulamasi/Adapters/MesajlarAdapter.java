package sercandevops.com.otogaleriuygulamasi.Adapters;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.otogaleriuygulamasi.Models.User;
import sercandevops.com.otogaleriuygulamasi.R;
import sercandevops.com.otogaleriuygulamasi.RestApi.ManagerALL;

public class MesajlarAdapter extends BaseAdapter {

    List<String> otherIdList;
    String userId;
    Context context;

    public MesajlarAdapter(List<String> otherIdList, String userId, Context context) {
        this.otherIdList = otherIdList;
        this.userId = userId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return otherIdList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherIdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.other,parent,false);
        TextView tv = v.findViewById(R.id.tv_usertext);

        istekAt(otherIdList.get(position).toString(),tv);

        return v;
    }

    public void istekAt(String uye_id, final TextView textView)
    {
        final retrofit2.Call<User> request = ManagerALL.getInstance().KisiBilgi(uye_id);
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                {
                    textView.setText(response.body().getKadi());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {

            }
        });
    }
}
