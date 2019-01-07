package sercandevops.com.otogaleriuygulamasi.Models;

public class Register{
	private String result;
	private boolean tf;
	private String dogrulamakodu;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setDogrulamakodu(String dogrulamakodu){
		this.dogrulamakodu = dogrulamakodu;
	}

	public String getDogrulamakodu(){
		return dogrulamakodu;
	}

	@Override
 	public String toString(){
		return 
			"Register{" + 
			"result = '" + result + '\'' + 
			",tf = '" + tf + '\'' + 
			",dogrulamakodu = '" + dogrulamakodu + '\'' + 
			"}";
		}
}
