package v107;

import java.util.Scanner;

public class _10707 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			
			int[][] grid1 = new int[n][m];
			int[][] grid2 = new int[n][m];
			
			for(int i=0;i<k;i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				grid1[x][y] = 1;
			}
			for(int i=0;i<k;i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				grid2[x][y] = 1;
			}
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<m;j++)
					System.out.print(grid1[i][j] + " ");
				System.out.println();
			}
			System.out.println("-------------------------------------------------");
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<m;j++)
					System.out.print(grid2[i][j] + " ");
				System.out.println();
			}
		}

	}

}
