package sercandevops.com.otogaleriuygulamasi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import sercandevops.com.otogaleriuygulamasi.Models.SliderPojo;
import sercandevops.com.otogaleriuygulamasi.R;
import sercandevops.com.otogaleriuygulamasi.RestApi.BaseURL;

public class SliderAdapter extends PagerAdapter {

    List<SliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderPojo> list, Context context) {
        this.list = list;
        this.context = context;
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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout,container,false);

        ImageView img = (ImageView)view.findViewById(R.id.sliderImageView);

        Picasso.get().load(BaseURL.URL+""+list.get(position).getResim()).into(img);

        container.addView(view);
        return view;
    }
}
