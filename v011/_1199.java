package v011;

import java.io.PrintWriter;
import java.util.Scanner;

public class _1199 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			int[] arr = new int[n];
			for(int i=0;i<n;i++)
				arr[i] = sc.nextInt();
			
			int t = (n-1) * 10 + (arr[n-1] - 1) * 4;
			int t0 = 0;
			boolean[] leave = new boolean[n];
			for(int i=0;i<n-1;i++)
			{
				if(arr[i] - 1 + t0 + 10 < t - 10)
				{
					leave[i] = true;
					t-=10;
					
				}
			}
		}
	}

}
