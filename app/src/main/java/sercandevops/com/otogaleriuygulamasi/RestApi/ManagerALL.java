package sercandevops.com.otogaleriuygulamasi.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Query;
import sercandevops.com.otogaleriuygulamasi.Models.DogrulamaPojo;
import sercandevops.com.otogaleriuygulamasi.Models.FavoriIslem;
import sercandevops.com.otogaleriuygulamasi.Models.FavoriKontrol;
import sercandevops.com.otogaleriuygulamasi.Models.FavoriSliderPojo;
import sercandevops.com.otogaleriuygulamasi.Models.IlanDetayPojo;
import sercandevops.com.otogaleriuygulamasi.Models.IlanSonucPojo;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarPojo;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarimPojo;
import sercandevops.com.otogaleriuygulamasi.Models.IlanlarimSilPojo;
import sercandevops.com.otogaleriuygulamasi.Models.LoginPojo;
import sercandevops.com.otogaleriuygulamasi.Models.Register;
import sercandevops.com.otogaleriuygulamasi.Models.ResimEklePojo;
import sercandevops.com.otogaleriuygulamasi.Models.SliderPojo;
import sercandevops.com.otogaleriuygulamasi.Models.User;
import sercandevops.com.otogaleriuygulamasi.Models.UserUpdate;

public class ManagerALL extends BaseManager{


    private static ManagerALL ourInstance = new ManagerALL();

    public static synchronized ManagerALL getInstance(){return ourInstance;}

    public Call<LoginPojo> login(String ad, String sifre){

        Call<LoginPojo> x = getRestApi().control(ad,sifre);

        return x;
    }

    public Call<Register> register(String kadi, String sifre){

        Call<Register> x = getRestApi().kayitol(kadi,sifre);

        return x;
    }

    public Call<DogrulamaPojo> dogrulama(String kadi, String kod){

        Call<DogrulamaPojo> x = getRestApi().dogrulama(kadi,kod);

        return x;
    }
    public Call<IlanSonucPojo> IlanVer(String uye_id, String sehir, String ilce, String mahalle, String marka, String seri,
                                       String model, String yil, String ilantipi,String kimden, String baslik, String motortipi,
                                       String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi,
                                       String ucret,String km,String aciklama){

        Call<IlanSonucPojo> x = getRestApi().IlanVer(uye_id,  sehir,  ilce,  mahalle,  marka,  seri, model,  yil,  ilantipi,
                                        kimden,  baslik,  motortipi, motorhacmi,  surat,  yakittipi,  ortalamayakit,  depohacmi,ucret,
                                        km,aciklama);



        return x;
    }

    public Call<ResimEklePojo> ResimYukle(String uye_id,String ilan_id,String base64ResimYukle)
    {
        Call<ResimEklePojo> x = getRestApi().resimYukle(uye_id,ilan_id,base64ResimYukle);
        return x;
    }

    public Call<List<IlanlarimPojo>> Ilanlarim(String uyeid)
    {
        Call<List<IlanlarimPojo>> x = getRestApi().ilanlarim(uyeid);
        return x;
    }

    public Call<IlanlarimSilPojo> IlanSil(String ilan_id)
    {
        Call<IlanlarimSilPojo> x = getRestApi().IlanSil(ilan_id);
        return x;
    }

    public Call<List<IlanlarPojo>> Ilanlar()
    {
        Call<List<IlanlarPojo>> x = getRestApi().Ilanlar();
        return x;
    }

    public Call<IlanDetayPojo> IlanDetay(String ilanid)
    {
        Call<IlanDetayPojo> x = getRestApi().ilanDetay(ilanid);
        return x;
    }

    public Call<List<SliderPojo>> IlanResimleri(String ilanid)
    {
        Call<List<SliderPojo>> x = getRestApi().Ilanresimleri(ilanid);
        return x;
    }

    public Call<FavoriKontrol> FavoriKontrolTEXT(String uye_id,String ilan_id)
    {
        Call<FavoriKontrol> x = getRestApi().getButonText(uye_id,ilan_id);
        return x;
    }

    public Call<FavoriIslem> FavoriIslemYap(String uye_id, String ilan_id)
    {
        Call<FavoriIslem> x = getRestApi().FavoriIslem(uye_id,ilan_id);
        return x;
    }

    public Call<List<FavoriSliderPojo>> setSlider(String uye_id)
    {
        Call<List<FavoriSliderPojo>> x = getRestApi().FavoriSlider(uye_id);
        return x;
    }


    public Call<User> KisiBilgi(String uye_id)
    {
        Call<User> x = getRestApi().getInformation(uye_id);
        return x;
    }

    public Call<UserUpdate> bilgiDegistir(String uye_id, String user,String pass)
    {
        Call<UserUpdate> x = getRestApi().changeInformation(uye_id,user,pass);

        return x;
    }
}
