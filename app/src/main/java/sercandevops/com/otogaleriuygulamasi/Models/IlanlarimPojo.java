package sercandevops.com.otogaleriuygulamasi.Models;

public class IlanlarimPojo{

	private String result;
	private String resim;
	private boolean tf;
	private String aciklama;
	private String uyeId;
	private String ilanId;
	private String fiyat;
	private String baslik;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResim() {
		return resim;
	}

	public void setResim(String resim) {
		this.resim= resim;
	}

	public boolean isTf() {
		return tf;
	}

	public void setTf(boolean tf) {
		this.tf = tf;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getUyeId() {
		return uyeId;
	}

	public void setUyeId(String uyeId) {
		this.uyeId = uyeId;
	}

	public String getIlanId() {
		return ilanId;
	}

	public void setIlanId(String ilanId) {
		this.ilanId = ilanId;
	}

	public String getFiyat() {
		return fiyat;
	}

	public void setFiyat(String fiyat) {
		this.fiyat = fiyat;
	}

	public String getBaslik() {
		return baslik;
	}

	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
}
