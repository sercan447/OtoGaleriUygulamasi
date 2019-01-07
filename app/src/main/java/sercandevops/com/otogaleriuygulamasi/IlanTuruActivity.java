package sercandevops.com.otogaleriuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import sercandevops.com.otogaleriuygulamasi.Models.IlanVerPojo;

public class IlanTuruActivity extends Activity {


    Spinner ilanturuSpinner,kimdenSpinner;
    Button ilanTuruButon,ilanTuruButonGeri;
    List<String> turList;
    List<String> sahipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_turu);

        ListeDoldur();
        Tanimlamalar();
    }
public void ListeDoldur(){

        turList = new ArrayList<>();
        sahipList = new ArrayList<>();

        turList.add("Satılık");
        turList.add("Kiralık");

        sahipList.add("Sahibinden");
        sahipList.add("Galeriden");


}
    public void Tanimlamalar(){
        ilanturuSpinner = (Spinner)findViewById(R.id.ilanTuruSpinner);
        kimdenSpinner = (Spinner)findViewById(R.id.kimdenSpinner);
        ilanTuruButon = findViewById(R.id.ilanTuruButton);
        ilanTuruButonGeri = findViewById(R.id.ilanTuruButtonGeri);


        ArrayAdapter<String> turListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,turList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanturuSpinner.setAdapter(turListAdapter);

        ArrayAdapter<String> sahipListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,sahipList);
        sahipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kimdenSpinner.setAdapter(sahipListAdapter);


        ilanTuruButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IlanVerPojo.setKimden(kimdenSpinner.getSelectedItem().toString());
                IlanVerPojo.setIlantipi(ilanturuSpinner.getSelectedItem().toString());

                Intent intent = new Intent(IlanTuruActivity.this,AdresBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });

        ilanTuruButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IlanTuruActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
            }
        });
    }
}
