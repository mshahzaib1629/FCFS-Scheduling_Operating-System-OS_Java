package application;
import java.util.ArrayList;

import application.ResultSet;

public class FCFS {

	int n;
	String pid[];   // process id
	int ar[];    // arrival times
	int bt[];     // burst or execution times
	int ct[];    // completion times
	int ta[];     // turn around times
	int wt[];     // waiting times 
	int temp;
	String tempP;
	float waitSum=0,turnSum=0;
	
	public void applyFCFS(String[] pid, int[] ar, int[] bt, int size){
		this.pid = new String[size];
		this.ar = new int[size];
		this.bt = new int[size];
		this.ct = new int[size];
		this.ta = new int[size];
		this.wt = new int[size];
		n = size;
		//sorting according to arrival times
				for(int i = 0 ; i <n; i++)
				{
					for(int  j=0;  j < n-(i+1) ; j++)
					{
						if( ar[j] > ar[j+1] )
						{
							temp = ar[j];
							ar[j] = ar[j+1];
							ar[j+1] = temp;
							temp = bt[j];
							bt[j] = bt[j+1];
							bt[j+1] = temp;
							tempP = pid[j];
							pid[j] = pid[j+1];
							pid[j+1] = tempP;
						}
					}
				}
				//Turn around & Waiting Time
				for(int i =0 ; i< n ; i++) {
					int temp1 = 0 ;
					for(int j=0 ; j<i ; j++ ) {		
						temp1 = bt[j] + temp1;		//get the burst time sum of all the processes that have elapsed
													//this burst time sum is the current time
					}
					wt[i] = temp1 - ar[i] ;			//minus the arrival time of current process from the bt sum
					ta[i] = wt[i] + bt[i] ;
					temp1 = 0 ;
					waitSum += wt[i];
					turnSum += ta[i] ;
				}
				
				//Completion Time
				for(int k=0 ; k<n ; k++) {
					int temp3 = 0 ;
					for(int m = 0 ; m <= k ; m++) {
						 temp3 = bt[m] + temp3 ;
					}
					ct[k] = temp3 ;
					temp3 = 0 ;
				}
				
				for(int i=0; i<n; i++){
					this.pid[i] = pid[i];
				}
	}

	public ArrayList<ResultSet> getResult() {

		ArrayList<ResultSet> result = new ArrayList<>();

		for (int i = 0; i <n; i++) {

			result.add(new ResultSet(pid[i], ct[i], ta[i], wt[i]));

		}
		return result;

	}
	
	public float getAvgWait(){
		
		return (float) waitSum/n;
	}
	
	public float getAvgTurnAround(){
		
		return (float) turnSum/n;
	}
	
	public String getGantChart(){
		
		String gant = new String();
		
		for(int i=0; i<n; i++){
			gant = gant + pid[i] + " | ";
		}
		return gant;
	}
	
	public void reset(){
		waitSum = 0;
		turnSum = 0;
		
	}
	
	
	
	
	
	public void testing(){
		
		//sorting according to arrival times
		for(int i = 0 ; i <n; i++)
		{
			for(int  j=0;  j < n-(i+1) ; j++)
			{
				if( ar[j] > ar[j+1] )
				{
					temp = ar[j];
					ar[j] = ar[j+1];
					ar[j+1] = temp;
					temp = bt[j];
					bt[j] = bt[j+1];
					bt[j+1] = temp;
					tempP = pid[j];
					pid[j] = pid[j+1];
					pid[j+1] = tempP;
				}
			}
		}
		//Turn around & Waiting Time
		for(int i =0 ; i< n ; i++) {
			int temp1 = 0 ;
			for(int j=0 ; j<i ; j++ ) {		
				temp1 = bt[j] + temp1;		//get the burst time sum of all the processes that have elapsed
											//this burst time sum is the current time
			}
			wt[i] = temp1 - ar[i] ;			//minus the arrival time of current process from the bt sum
			ta[i] = wt[i] + bt[i] ;
			temp1 = 0 ;
			waitSum += wt[i];
			turnSum += ta[i] ;
		}
		
		//Completion Time
		for(int k=0 ; k<n ; k++) {
			int temp3 = 0 ;
			for(int m = 0 ; m <= k ; m++) {
				 temp3 = bt[m] + temp3 ;
			}
			ct[k] = temp3 ;
			temp3 = 0 ;
		}
		System.out.println("\npid		arrivalTime 		brustTime  		completeTime "
				+ "		turnAroundTime 			waitingTime");
		for(int  i = 0 ; i< n;  i++)
		{
			if(wt[i] < 0)
				wt[i] = 0;
			
			System.out.println(pid[i] + "\t"+"\t"+"\t" + ar[i] + "\t"+"\t"+"\t" + bt[i] + "\t"+"\t"+"\t" + ct[i] + 
					"\t"+"\t"+"\t" + ta[i] + "\t"+"\t"+"\t"  + wt[i] ) ;
		}
		System.out.println("\naverage waiting time: "+ (waitSum/n));
		System.out.println("average turnaround time:"+(turnSum/n));
	}

	
}
