package sercandevops.com.otogaleriuygulamasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import sercandevops.com.otogaleriuygulamasi.Adapters.MesajlarAdapter;
import sercandevops.com.otogaleriuygulamasi.Models.OtherId;

public class MesajlarActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    List<String> otherIdList;
    String userID;
    SharedPreferences sharedPreferences;

    MesajlarAdapter adapter;
    ListView listviewMesajlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlar);

        listviewMesajlar = (ListView)findViewById(R.id.listviewMesajlarr);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        otherIdList = new ArrayList<>();
        sharedPreferences = this.getSharedPreferences("giris",0);
        userID = sharedPreferences.getString("uye_id",null);
        adapter = new MesajlarAdapter(otherIdList,userID,getApplicationContext());


        listviewMesajlar.setAdapter(adapter);

        listviewMesajlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                OtherId.setOtherId(otherIdList.get(position).toString());
                startActivity(intent);
            }
        });
        listele();
    }

    public void listele()
    {
        reference.child("messages").child("18").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.i("MESJLARIM",dataSnapshot.getKey());
                otherIdList.add(dataSnapshot.getKey().toString());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
