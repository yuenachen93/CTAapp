package CTAFinal;

public class GeoLocation {
	protected double latitude;
	protected double longtitude;
	
	public GeoLocation(){
		latitude=0.0;
		longtitude=0.0;
	}
	
	public GeoLocation(double latitude, double longtitude){
		setLatitude(latitude);
		setLongtitude(longtitude);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		if(latitude<90&&latitude>-90){
		this.latitude = latitude;
		}else System.out.println("the latitude is out of range");
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		if(longtitude<180 && longtitude>-180){
		this.longtitude = longtitude;
		}else System.out.println("the longtitude is out of range");
	}
	
	public double calcDistance(GeoLocation a){
		double result=calcDistance(a.getLatitude(),a.getLongtitude());
		return result;
	}
	//Calculate distance based on latitude and longtitude
	//Yuena Chen 12/02/2016
	public double calcDistance(double la,double lo){
		double result=Math.sqrt((la-latitude)*(la-latitude)+(lo-longtitude)*(lo-longtitude));
		return result;
	}
   public boolean equals(double la, double lo){
	   if(Math.abs(la-latitude)<0.000001&&Math.abs(lo-longtitude)<0.00000001){
		   return true;
	   }
	   return false;
   }
   

}
