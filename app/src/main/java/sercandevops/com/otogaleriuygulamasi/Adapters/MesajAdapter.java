package sercandevops.com.otogaleriuygulamasi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sercandevops.com.otogaleriuygulamasi.Models.MesajModel;
import sercandevops.com.otogaleriuygulamasi.R;

public class MesajAdapter extends RecyclerView.Adapter<MesajAdapter.ViewHolder> {

    List<MesajModel> list;
    boolean state = false;
    static final int user=5,other=8;
    Context context;
    String userId;


    public MesajAdapter(List<MesajModel> list, Context context,String userId) {
        this.list = list;
        this.context = context;
        this.userId = userId;
    }

    @NonNull
    @Override
    public MesajAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view;

        if(viewType == user)
        {
            view = LayoutInflater.from(context).inflate(R.layout.user,viewGroup,false);
            return new ViewHolder(view);
        }else
        {
            view = LayoutInflater.from(context).inflate(R.layout.other,viewGroup,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MesajAdapter.ViewHolder viewHolder, int i) {

        MesajModel s = list.get(i);
        switch (viewHolder.getItemViewType())
        {
            case user:
                ((ViewHolder)viewHolder).setle(s);
                break;
            case other:
                ((ViewHolder)viewHolder).setle(s);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getFrom().equals(userId))
        {
            state = true;
            return user;
        }else{
            state = false;
            return other;
        }
       // return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mesajBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            if(state)
            {
                mesajBody = (TextView)itemView.findViewById(R.id.tv_usertext);

            }else{
                mesajBody = (TextView)itemView.findViewById(R.id.tv_usertext);
            }
        }

        public void setle(MesajModel msj)
        {
            mesajBody.setText(msj.getMesaj());
        }
    }//CLASS
}
