package Tsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Tsp {
	//임의의 시작점을 처음 좌표로 지정함
	static int[]Xdot=new int[5000000];
	static int[]Ydot=new int[5000000];
	
	static int N;//전체 점의 수
	static double Sum=0;
	static double length=0;
	
	
	
	static int []tour=new int[100];
	static int []tour_min=new int[100];
	static double min=10000000;
	public static void main(String[] args) {
		String fileName = "input00.txt";
		read(fileName);
		
		
		 for (int i = 0; i < N; i++)
			 tour[i] = i;

		perm(0);
		System.out.println(min);
		System.out.print("경로 : [");
		for(int i=0;i<N-1;i++)
			System.out.print(tour_min[i]+",");
		System.out.print(tour_min[N-1]+"]");
	}
	
	
	
	
	static void read(String f) {
		try {
			Scanner sc=new Scanner(new FileReader(f));
			
			N=sc.nextInt();
			for(int i=0;i<N;i++) {
				Xdot[i]=sc.nextInt();
				Ydot[i]=sc.nextInt();
				
			}
			sc.close();
		}catch (FileNotFoundException e) { 
		System.out.println("파일없음");
		}catch (IOException e) {
		System.out.println("예외");
	}

	}
	
	
	
	static double betweenDistance(int x,int y,int x1,int y1) {
		return Math.sqrt(Math.pow(Math.abs(x1-x), 2)+Math.pow(Math.abs(y1-y), 2));
		
	}
	
	
	
	static void swap(int x[],int y[],int i,int j) {
		int tmp_X=x[i];
		int tmp_Y=y[i];
		x[i]=x[j];
		y[i]=y[j];
		x[j]=tmp_X;
		y[j]=tmp_Y;
		int tmpi=tour[j];
		tour[j]=tour[i];
		tour[i]=tmpi;
	}
	
	
	
	static void perm(int k) {
		
		if(k==N) {
			for (int i = 0; i < k; i++) {

		         Sum+=betweenDistance(Xdot[i], Ydot[i], Xdot[i+1], Ydot[i+1]);
		         }
			Sum+=betweenDistance(Xdot[0], Ydot[0], Xdot[N-1], Ydot[N-1]);
			if(min>Sum) {
				min = Sum;
		         for (int i = 0; i < N; i++)
		            tour_min[i] = tour[i];
			}
			Sum = 0; 
			
		    return;
		}
		 for (int i = k; i < N; i++) {
		      swap(Xdot, Ydot, k, i);
		      perm(k + 1);
		      swap(Xdot, Ydot, k, i);
		   }
			
	}
	
	
}