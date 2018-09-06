package CTAFinal;

public class CTAStation extends GeoLocation {
	private String name;
	private String location;
	private boolean wheelchair;
	private double[] line;
	
	public 	CTAStation(){
		super();
		name="Station";
		location="surface";
		wheelchair=true;
		double[] line=new double[8];
	}
	public CTAStation(String name, double latitude, double longtitude, String location, boolean wheelchair, double[] line){
		super(latitude, longtitude);
		this.name=name;
		this.location=location;
		this.wheelchair=wheelchair;
		setLine(line);
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isWheelchair() {
		return wheelchair;
	}
	public void setWheelchair(boolean wheelchair) {
		this.wheelchair = wheelchair;
	}
	public double getLine(int a) {
		return line[a];
	}
	public void setLine(double[] line) {
		this.line = line;
	}
	
	public String toString(){
		return  name+" "+latitude+" "+longtitude+" "+location+" "+wheelchair+" "+line[0]+" "+line[1]+" "+line[2]+" "+line[3]+" "+line[4]+" "+line[5]+" "+line[6]+" "+line[7];
	}
	
	// Overridden Get latitude and longtitude for the station, and use calDistance method to get the distance
	//Yuena Chen 12/02/2016
		public double calcDistance(CTAStation ctastation){
			return calcDistance(ctastation.latitude, ctastation.longtitude);
		}
		

	//create calcDidtance method that can calculate distance between input location and station location
    //Yuena Chen 12/02/2016
		public double calcDistance(double latitude, double longtitude){
			double la=getLatitude();
			double lo=getLongtitude();
			return Math.sqrt((latitude-la)*(latitude-la)+(longtitude-lo)*(longtitude-lo));
	}
	//create equal method 
		public boolean equals(double latitude, double longtitude){
			double la=getLatitude();
			double lo=getLongtitude();
			boolean equal;
			if((la-latitude)==0&&(lo-longtitude)==0){
				equal=true;
			}else{
				equal=false;
			}
			return equal;
		}
	
	

}
