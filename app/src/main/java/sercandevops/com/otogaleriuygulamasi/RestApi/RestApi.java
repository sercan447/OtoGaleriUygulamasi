package sercandevops.com.otogaleriuygulamasi.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

public interface RestApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginPojo> control(@Field("kad") String kad, @Field("sifre")String sifre);


    @FormUrlEncoded
    @POST("register.php")
    Call<Register> kayitol(@Field("kadi")String kadi, @Field("sifre")String sifre);

    @FormUrlEncoded
    @POST("dogrulama.php")
    Call<DogrulamaPojo> dogrulama(@Field("kadi")String kadi, @Field("kod")String kod);

    @FormUrlEncoded
    @POST("ilanver.php")
    Call<IlanSonucPojo> IlanVer(@Field("uye_id")String uye_id, @Field("sehir")String sehir,
                                @Field("ilce")String ilce, @Field("mahalle")String mahalle,
                                @Field("marka")String marka, @Field("seri")String seri,
                                @Field("model")String model, @Field("yil")String yil,
                                @Field("ilantipi")String ilantipi, @Field("kimden")String kimden,
                                @Field("baslik")String baslik, @Field("motortipi")String motortipi,
                                @Field("motorhacmi")String motorhacmi, @Field("surat")String surat,
                                @Field("yakittipi")String yakittipi, @Field("ortalamayakit")String ortalamayakit,
                                @Field("depohacmi")String depohacmi,@Field("km")String km,
                                @Field("ucret")String ucret,@Field("aciklama")String aciklama);


    @POST("ilanresmiekle.php")
    @FormUrlEncoded
    Call<ResimEklePojo> resimYukle(@Field("uye_id")String uye_id,@Field("ilan_id")String ilan_id,@Field("resim")String base64ResimYukle);

    @GET("ilanlarim.php")
    Call<List<IlanlarimPojo>> ilanlarim(@Query("uyeid") String uyeid);

    @GET("ilanlarimdansil.php")
    Call<IlanlarimSilPojo> IlanSil(@Query("ilan_id") String ilan_id);

    @GET("ilanlar.php")
    Call<List<IlanlarPojo>> Ilanlar();

    @GET("ilandetay.php")
    Call<IlanDetayPojo> ilanDetay(@Query("ilanid") String ilan_id);

    @GET("ilanresimleri.php")
    Call<List<SliderPojo>> Ilanresimleri(@Query("ilanid") String ilan_id);

    @GET("favorilanlar.php")
    Call<FavoriKontrol> getButonText(@Query("uye_id")String uye_id,@Query("ilan_id")String ilan_id);

    @GET("favorislem.php")
    Call<FavoriIslem> FavoriIslem(@Query("uye_id")String uye_id, @Query("ilan_id")String ilan_id);

    @GET("favorilanslider.php")
    Call<List<FavoriSliderPojo>> FavoriSlider(@Query("uye_id")String uye_id);


    @GET("bilgiler.php")
    Call<User> getInformation(@Query("uye_id")String uye_id);


    @GET("bilgiguncelle.php")
    Call<UserUpdate> changeInformation(@Query("uyeid")String uye_id,@Query("user")String user,@Query("pass")String pass);


}
