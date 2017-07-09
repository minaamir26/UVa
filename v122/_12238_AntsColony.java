package v122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _12238_AntsColony {
	//3 arrays --> E (Euler) , L (Level) , H (first occ of index i in E)
	static ArrayList<Pair> adj[];
	static int n;
	static boolean[] vis;
	static long dist[];
	static int[] E,L,H;
	static int k , nn;
	static int lvl;
	static void dfs(int u, long dis)
	{
		vis[u] = true;
		if(H[u] == -1)
			H[u] = k;
		E[k] = u;
		L[k++] = lvl;
		dist[u] = dis;
		for(Pair v : adj[u])
			if(!vis[v.to])
			{
				lvl++;
				dfs(v.to , dis+v.dis);
				lvl--;
				if(H[u] == -1)
					H[u] = k;
				E[k] = u;
				L[k++] = lvl;
			}
	}

	@SuppressWarnings("unchecked")
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
			for(int i=1;i<=n-1;i++)
			{
				int to = sc.nextInt();
				int dis = sc.nextInt();
				adj[i].add(new Pair(to, dis));
				adj[to].add(new Pair(i, dis));
			}
			dist = new long[n];
			k = 0;
			nn = n;
			vis = new boolean[n];
			E = new int[n<<1];
			L = new int[n<<1];
			H = new int[n];
			Arrays.fill(H, -1);
			lvl = 0;
			dfs(0,0);
			spt = new int[2*n][(int)(Math.log(2*n)/Math.log(2))+1];
			build();
			int q = sc.nextInt();
			while(q-->0)
			{
				int u = sc.nextInt();
				int v = sc.nextInt();
				int k = query(Math.min(H[u],H[v]), Math.max(H[u],H[v]));
				k = E[k];
				pw.print(dist[u]+dist[v]-2*dist[k]);
				if(q==0)
					pw.println();
				else
					pw.print(" ");
			}
		}
		pw.flush();
	}
	static class Pair{
		int to;
		long dis;
		public Pair(int a,long b)
		{
			to=a;
			dis=b;
		}
	}
	static int[][] spt;
	static void build()
	{
		int n = nn*2;
		for(int i=0;i<n;i++)
			spt[i][0] = i;
		for(int j=1;(1<<j) <= n;j++)
			for(int i=0;i + (1<<j)-1<n;i++)
				if(L[spt[i][j-1]] > L[spt[i+(1<<(j-1))][j-1]])
					spt[i][j] = spt[i+(1<<(j-1))][j-1];
				else
					spt[i][j] = spt[i][j-1];
	}
	
	static int query(int i , int j)
	{
		int k = (int)(Math.log(j-i+1)/Math.log(2));
		if(L[spt[i][k]] < L[spt[j-(1<<k)+1][k]])
			return spt[i][k];
		else
			return spt[j-(1<<k)+1][k];
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
