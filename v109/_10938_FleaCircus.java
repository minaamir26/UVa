package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class _10938_FleaCircus {
	//4:17
	
	
	static ArrayList<Integer> adj[];
	static boolean[] vis;
	static int n , target;
	static ArrayList<Integer> road;
	static boolean dfs(int u)
	{
		vis[u] = true;
		if(u == target)
			return true;
		for(int v : adj[u])
			if(!vis[v])
				if(dfs(v))
				{
					road.add(v+1);
					return true;
				}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			n = sc.nextInt();
			if(n == 0)
				break;
			adj = new ArrayList[n];
			for(int i=0;i<n;i++)
				adj[i] = new ArrayList<>();
			
			for(int i=0;i<n-1;i++)
			{
				int a = sc.nextInt()-1;
				int b = sc.nextInt()-1;
				adj[a].add(b);
				adj[b].add(a);
			}
			int q = sc.nextInt();
			while(q-- > 0)
			{
				int a = sc.nextInt()-1;
				int b = sc.nextInt()-1;
				target = b;
				vis = new boolean[n];
				road = new ArrayList<>();
				dfs(a);
				road.add(a+1);
				if(road.size() %2 != 0)
					pw.printf("The fleas meet at %d.\n" ,road.get(road.size()/2));
				else
				{
					int min = Math.min(road.get(road.size()/2) , road.get(road.size()/2-1));
					int max = Math.max(road.get(road.size()/2) , road.get(road.size()/2-1));
					pw.printf("The fleas jump forever between %d and %d.\n", min ,max);	
				}
			}
		}
		pw.flush();
	}
	static class Scanner{
		StringTokenizer st;BufferedReader br;
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		public String next() throws IOException 
		{while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());return st.nextToken();}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		public long nextLong() throws IOException {return Long.parseLong(next());}
		public String nextLine() throws IOException {return br.readLine();}
		public double nextDouble() throws IOException {return Double.parseDouble(next());}
		public boolean ready() throws IOException {return br.ready();}
	}
}
