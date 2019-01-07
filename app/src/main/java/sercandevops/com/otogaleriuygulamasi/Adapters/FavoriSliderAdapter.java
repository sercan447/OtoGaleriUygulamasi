package sercandevops.com.otogaleriuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import sercandevops.com.otogaleriuygulamasi.IlanDetayActivity;
import sercandevops.com.otogaleriuygulamasi.Models.FavoriSliderPojo;
import sercandevops.com.otogaleriuygulamasi.Models.SliderPojo;
import sercandevops.com.otogaleriuygulamasi.R;
import sercandevops.com.otogaleriuygulamasi.RestApi.BaseURL;

public class FavoriSliderAdapter extends PagerAdapter {

    List<FavoriSliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public FavoriSliderAdapter(List<FavoriSliderPojo> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout,container,false);

        ImageView img = (ImageView)view.findViewById(R.id.sliderImageView);

        Picasso.get().load(BaseURL.URL+""+list.get(position).getResimyolu()).into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //resme tıklandıgında detaya gitsin..

                if(list.get(position).getIlanid() != null)
                {
                    Intent intent = new Intent(activity, IlanDetayActivity.class);
                    intent.putExtra("ilanid", list.get(position).getIlanid());
                    activity.startActivity(intent);
                }else
                {

                }

            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);

        // container.removeView((View)object);
    }
}
