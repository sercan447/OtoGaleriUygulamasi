package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sercandevops.com.otogaleriuygulamasi.Adapters.MesajAdapter;
import sercandevops.com.otogaleriuygulamasi.Models.MesajModel;
import sercandevops.com.otogaleriuygulamasi.Models.OtherId;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String userId,otherId,userTable,otherTable,key;
    SharedPreferences   sharedPreferences;

    FirebaseDatabase database;
    DatabaseReference reference;

    EditText ed_mesaj;
    Button btnSendMesajButn;

    List<MesajModel> list;
    MesajAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        Tanimlama();
        Action();
        load();

    }

    public void Tanimlama()
    {
        otherId = OtherId.getOtherId();
        sharedPreferences = this.getSharedPreferences("giris",0);
        userId = sharedPreferences.getString("uye_id",null);

        
        list = new ArrayList<>();
        adapter = new MesajAdapter(list,getApplicationContext(),userId);

        recyclerView = (RecyclerView)findViewById(R.id.recylerviewMesaj);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        ed_mesaj = (EditText)findViewById(R.id.ed_mesajEdittext);
        btnSendMesajButn = findViewById(R.id.btnSendMesajButn);

    }

    public void Action()
    {
        btnSendMesajButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(ed_mesaj.getText().toString(),userId,otherId);
            }
        });
    }

    public void load()
    {
        reference.child("messages").child(userId).child(otherId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.i("TAGALANDIK",dataSnapshot.toString());

                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
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

    public void sendMessage(String mesajbody,String uid,String oId)
    {
        userTable = "messages/"+userId+"/"+otherId;
        otherTable = "messages/"+otherId+"/"+userId;

        String key = reference.child("messages").child(userTable).child(otherTable).push().getKey();

        Map map = send(mesajbody,uid,oId);
        Map map2 = new HashMap();
        map2.put(userTable+"/"+key,map);
        map2.put(otherTable+"/"+key,map);

        reference.updateChildren(map2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

            }
        });
    }

    private Map send(String mesajbody, String id,String otherId) {

        Map msj = new HashMap();
        msj.put("mesaj",mesajbody);
        msj.put("from",userId);
        msj.put("to",otherId);
        return msj;
    }
}
