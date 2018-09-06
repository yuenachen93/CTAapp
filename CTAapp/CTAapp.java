package CTAFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CTAapp {
	//Read and import file
	public static ArrayList<CTAStation> stationlist=new ArrayList<CTAStation>();
	public static void readfile(){
		File file=new File("CTAStops.csv");
		Scanner input=null;
		try{
			input=new Scanner(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		int count=0;
		while(input.hasNextLine()){
			if(count<2){
				String put=input.nextLine();
				count++;
			}else{
				String put=input.nextLine();
				String[] inArray=put.split(",");
				double[] line= new double[8];
				String name=inArray[0];
				double latitude=Double.parseDouble(inArray[1]);
				double longtitude=Double.parseDouble(inArray[2]);
				String location=inArray[3];
				boolean wheelchair=Boolean.parseBoolean(inArray[4]);
				line[0]=Double.parseDouble(inArray[5]);
				line[1]=Double.parseDouble(inArray[6]);
				line[2]=Double.parseDouble(inArray[7]);
				line[3]=Double.parseDouble(inArray[8]);
				line[4]=Double.parseDouble(inArray[9]);
				line[5]=Double.parseDouble(inArray[10]);
				line[6]=Double.parseDouble(inArray[11]);
				line[7]=Double.parseDouble(inArray[12]);
				stationlist.add(new CTAStation(name,latitude, longtitude,location,wheelchair,line));
			}
		}
		
	}
	
	//File output
	public static void printfile(ArrayList<CTAStation> list){
		try{
			FileWriter File=new FileWriter("output.txt",true);
			BufferedWriter out=new BufferedWriter(File);
			for(int i=0;i<list.size();i++){
				out.write(i+" "+list.get(i).toString());
			}
			out.newLine();
			out.close();
		}catch(Exception io){
			System.out.println("File");
		}
	}
    
	// Main method
	public static void main(String[] args){
		readfile();//import the data
		CTARoute powels=new CTARoute(stationlist);
		Scanner in=new Scanner(System.in);
	    // Start menu options
		boolean notDone=false;
		//start menu loop
        while(!notDone){
        	System.out.println("Which option do you choose:");
        	System.out.println("1.Check information for a station");
        	System.out.println("2.How to get to a station");
        	System.out.println("3.Add a new station");
        	System.out.println("4.Delete a route");
        	System.out.println("5.Display nearest station");
        	System.out.println("6.Exit");
        	double option=Double.parseDouble(in.nextLine());
        	//first choice
        	if(option==1){
        		System.out.println("Please enter the station name");
        		String name=in.nextLine();
        		ArrayList<CTAStation> resulttemp= powels.search(name);
        		if(resulttemp.size()>0){
        			for(CTAStation b:powels.search(name)){
        			System.out.println(b);
        			}
        		}
        	//second choice
        	}else if(option==2){
        	System.out.println("Please enter the start point station name: ");
        	String namebeg=in.nextLine();
        	System.out.println("Please enter the start point line color(red,green,blue,brown,etc)");
        	int linebeg=CTARoute.linecolortransfer(in.nextLine());
        	System.out.println("Please enter the end point station name: ");
        	String nameend=in.nextLine();
        	System.out.println("Please enter the end point line color(red,green,blue,brown,etc)");
        	int lineend=CTARoute.linecolortransfer(in.nextLine()); 
        	powels.SameRouteGenerater(linebeg,namebeg,lineend,nameend);
            //third choice
        	}else if(option==3){
        	System.out.println("Please enter station name: ");
        	String name=in.nextLine();
        	System.out.println("Please enter the latitude");
        	double la=Double.parseDouble(in.nextLine());
        	System.out.println("Please enter the longtitude");
        	double lo=Double.parseDouble(in.nextLine());
        	System.out.println("Where is the station, elevated, surface, subway or others");
        	String location=in.nextLine();
        	System.out.println("Do it has wheelchair access, enter true or false");
        	Boolean wh=Boolean.parseBoolean(in.nextLine());
        	System.out.println("Which color line do you want to add");
        	String color=in.nextLine();
        	printfile(powels.addStation(name,la,lo,location,wh,color));
        	//fourth choice
        	}else if(option==4){
            System.out.println("Please enter the name for the station you want to delete");
            String name=in.nextLine();
            int tempcount=0;
            //user can choose the which station they want to delete if there is multiple stations
            System.out.println("Please enter the station number you want to delete");
            for(CTAStation b:powels.search(name)){
            	tempcount++;
            	System.out.println(tempcount+"."+b);
            }
            	tempcount=Integer.parseInt(in.nextLine())-1;
            	printfile(powels.deleteStation(name,tempcount));
        	//fifth choice	
        	}else if(option==5){
        		try{
        	 System.out.println("Please enter your latitude");
        	 double la=Double.parseDouble(in.nextLine());
        	 System.out.println("Please enter your longtitude");
        	 double lo=Double.parseDouble(in.nextLine());
        	 System.out.println("The nearest station is: "+powels.nearestDistance(la,lo));
        		}catch(Exception e){
        			System.out.println("Please enter valid choice");
        		}
        	//sixth choice end loop
        	}else if(option==6){
        		notDone=true;
        	}else System.out.println("Please enter the valid choice");
        	
 
        }      
		in.close();
	}
}
