package CTAFinal;

import java.util.ArrayList;

public class CTARoute extends CTAStation {
       private ArrayList<CTAStation> stationlist;
       public CTARoute(){
    	   stationlist=new ArrayList<CTAStation>();
       }     
       public CTARoute(int cap){
    	   stationlist=new ArrayList<CTAStation>(cap);
       }    
       public CTARoute(ArrayList<CTAStation> ctastation){
   		stationlist=new ArrayList<CTAStation>(ctastation.size());
   		for(CTAStation b:ctastation){
   			stationlist.add(b);
   		}
   	}
    // transfer the linename(red, green, blue,etc) to integer, so it would be easier to code later
    //Yuena Chen 12/02/2016
       public static int linecolortransfer(String a){
    	   int result=0;
    	   if(a.equalsIgnoreCase("red")){
    		  result=0;
    	   }else if(a.equalsIgnoreCase("green")){
    		   result=1;
    	   }else if(a.equalsIgnoreCase("blue")){
    		   result=2;
    	   }else if(a.equalsIgnoreCase("brown")){
    		   result=3;
    	   }else if(a.equalsIgnoreCase("purple")){
    		   result=4;
    	   }else if(a.equalsIgnoreCase("pink")){
    		   result=5;
    	   }else if(a.equalsIgnoreCase("orange")){
    		   result=6;
    	   }else if(a.equalsIgnoreCase("yellow")){
    		   result=7;
    	   }
    	  return result; 
       }
       
     //Transfer integer to line name
     //Yuena Chen 12/02/2016
       public String lineinttransfer(int a){
    	   String result=" ";
    	   if(a==0){
    		  result="Redline";
    	   }else if(a==1){
    		   result="Greenline";
    	   }else if(a==2){
    		   result="Blueline";
    	   }else if(a==3){
    		   result="Brownline";
    	   }else if(a==4){
    		   result="Purpleline";
    	   }else if(a==5){
    		   result="Pinkline";
    	   }else if(a==6){
    		   result="Orangeline";
    	   }else if(a==7){
    		   result="YellowLine";
    	   }
    	 return result;
       }
    //This method can return an arraylist that contains the stations that in a specific line
    //Yuena Chen 12/02/2016
       public ArrayList<CTAStation>generateLine(int line){
    	   ArrayList<CTAStation> result=new ArrayList<CTAStation>();
    	   for(CTAStation b:stationlist){
    		   if(b.getLine(line)!=-1){
    			   result.add(b);
    		   }
    	   }
    	   //bubble sort the line 	   
    	   boolean flag=true;
    	   ArrayList<CTAStation> temp=new ArrayList<CTAStation>();
    	   while(flag){
    		   flag=false;
    		   for(int i=0;i<result.size()-1;i++){
    			   if(result.get(i).getLine(line)>result.get(i+1).getLine(line)){
    				   temp.add(result.get(i));
    				   result.set(i, result.get(i+1));
    				   result.set(i+1, temp.get(0));
    				   temp.clear();
    				   flag=true;
    			   }
    		   }
    	   }
    	   return result;
       }  
     //This is the little method I created for SameRouteGenerater method. This method can find the position of the station in the ArrayList
     //Yuena Chen 12/02/2016
       public int searchname(String name){
    	 int count=0; 
    	 for(int i=0;i<stationlist.size();i++){
    			 count++;
    		 if(stationlist.get(i).getName().equalsIgnoreCase(name)){
    			 i=i+200;	 
    		 }   	 
     }
    	 return count-1;
     }     
   //Option 1: Search a station by entering the name
   //Yuena Chen 12/02/2016
       
       public ArrayList<CTAStation> search(String name){
    	   ArrayList <CTAStation> temp=new ArrayList<CTAStation>();
    	   for(CTAStation b:stationlist){
    		   if(b.getName().equalsIgnoreCase(name)){
    			   temp.add(b);
    		   }
    	   } 
    	    if(temp.size()<1){
    		   System.out.println("Not found");
    	   }
    	   return temp;
       }
         
    //Option 2: Generate router from one station to another (Although I call it SameRouteGenerater, it actually just the Route Generater).
    //Yuena Chen 12/02
       public void SameRouteGenerater(int line1, String name1, int line2, String name2){
    	   
    	   CTARoute powels=new CTARoute(stationlist);
    	   ArrayList<CTAStation> result=new ArrayList<CTAStation>();
    	   ArrayList<CTAStation> resultline2=new ArrayList<CTAStation>();
    	   result=powels.generateLine(line1);//generate a arraylist for start station line: line1(user input line)
    	   resultline2=powels.generateLine(line2);//generate a arraylist for end station line
		   powels=new CTARoute(result);
		   int beg=powels.searchname(name1);//find the start position and end position in line1 arraylist
		   int end=powels.searchname(name2);
		   String temp=" ";
		   int count=0;
		   //check if two station that user plug in need to transfer once or twice
		   for(int i=0;i<result.size();i++){
			   if(result.get(i).getLine(line2)!=-1){
				  temp=result.get(i).getName();
				  count++;
			   }
		   }
    	  //Base condition 1
    	   if(line1==line2 && beg<end){ 
    		   for(int i=beg;i<=end;i++){
    			   System.out.println("--"+result.get(i).getName());
    		   } 
          //Base condition 2 if the start station is after the destination
    	   }else if(line1==line2 && beg>end){
    		   for(int i=beg;i>=end;i--){
    			   System.out.println("--"+result.get(i).getName());
    		   } 
    	  //Base condition 3, if you only need to transfer once (it uses Base condition 1 & 2)
    	   }  else if(line1!=line2 && count>=1) {
    		   
    		   for(int i=0;i<result.size();i++){
    			   if(result.get(i).getLine(line2)!=-1){
    				  temp=result.get(i).getName();
    			   }  
    		   }
    		   SameRouteGenerater(line1, name1, line1, temp);
			   System.out.println(" "+"Transfer to"+" "+lineinttransfer(line2)+" "+"at "+temp);
			   SameRouteGenerater(line2, temp, line2, name2); 
		//If you need to transfer twice, since only redline and yellowlinew would have this situation under certain circumstance, so i designed method specific for these circumstance
    		   }else if(line1==0 && line2==5) {
    			   //situation from redline to pinkline (transfer in roosevelt)
    			   SameRouteGenerater(line1,name1,line1,"Roosevelt");
    			   System.out.println(" "+"<Take green line in 'Roosevelt' station>");
    			   SameRouteGenerater(1,"Roosevelt",line2,name2);
    			   //situation from pinkline to redline
    		   }else if(line2==0 && line1==5){
    			   SameRouteGenerater(line1,name1,1,"Roosevelt");
    			   System.out.println(" "+"<Take green line in 'Roosevelt' station>");
    			   SameRouteGenerater(line2,"Roosevelt",line2,name2);
    			   //situation from yellowline to anyline that is not redline or purpleline
    		   }else if(line1==7&& (line2!=0 || line2!=4)){
    			   SameRouteGenerater(line1,name1,line1,"Howard");
    			   System.out.println(" "+"Transfer to"+" "+lineinttransfer(4)+" "+"at "+"Howard");
    			   SameRouteGenerater(4,"Howard",line2,name2);
    			   //situation from anyline that is not redline or purpleline to yellowline
    		   }else if(line2==7&&(line1!=0||line1!=4)){
    			   SameRouteGenerater(line1,name1,4,"Howard");
    			   System.out.println(" "+"Transfer to"+" "+lineinttransfer(7)+" "+"at "+"Howard");
    			   SameRouteGenerater(line2,"Howard",line2,name2);
    		   }else System.out.println("Please enter the valid value");
       }   
       //Option 3: Add a new station
     //Yuena Chen 12/02/2016
       public ArrayList<CTAStation> addStation(String name,double la, double lo, String location, boolean wh,String color){
    	   double[] temp=new double[8];
    	   CTARoute powels=new CTARoute(stationlist);
    	   int count=powels.generateLine(linecolortransfer(color)).size();
    	   for(int i=0;i<8;i++){
    		   if(i==linecolortransfer(color)){
    			   temp[i]=count+1;
    		   }else temp[i]=-1;
    	   }
    	   CTAStation result=new CTAStation(name,la,lo,location,wh,temp);
    	   stationlist.add(result);
    	   System.out.println(result.toString()+" "+"has been succesfully added");
    	   return stationlist;
       }
       //Option 4: Delete a station (user can choose which one to delete if it has multiple choice)
       //Yuena Chen 12/02/2016
      public ArrayList<CTAStation> deleteStation(String name, int count){
    	  ArrayList <CTAStation> temp=new ArrayList<CTAStation>();
    	  temp=search(name);
    	  stationlist.remove(temp.get(count));
    	  System.out.println(temp.get(count).toString()+" has been removed");
    	  return stationlist;
      }
      //Option 5: Nearest station by using Find the Min Value method
      //Yuena Chen 12/02/2016
      public String nearestDistance(double latitude, double longtitude){
		  double distance;
		  double near=Integer.MAX_VALUE;
		  String closestation=" ";
	      for(int i=0;i<stationlist.size();i++){
			double la=stationlist.get(i).getLatitude();
			double lo=stationlist.get(i).getLongtitude();
			distance= Math.sqrt((latitude-la)*(latitude-la)+(longtitude-lo)*(longtitude-lo));
			if(distance<near){
				near=distance;
				closestation=stationlist.get(i).getName();
			}
	}
	      return closestation;
		}
	@Override
	public String toString() {
		return "CTARoute [stationlist=" + stationlist + "]";
	}
	
	
       
      
}
