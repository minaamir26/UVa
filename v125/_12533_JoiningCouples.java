package v125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class _12533_JoiningCouples {
	//12 + from 1:15 to 2:30
	static int arr[];
	static int n;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		while(true)
		{
			n = sc.nextInt();
			arr = new int[n];
			UnionFind uf = new UnionFind(n);
			adj= new ArrayList[n];
			for(int i=0;i<n;i++)
				adj[i] = new ArrayList<>();
			for(int i=0;i<n;i++)
			{
				int x = sc.nextInt()-1;
				arr[i] = x;
				adj[i].add(x);
//				adj[x].add(i);
				uf.unionSet(i, x);
			}
			ArrayList<Integer> sets[] = new ArrayList[uf.numSets];
			for(int i=0;i<sets.length;i++)
				sets[i] = new ArrayList<>();
			TreeMap<Integer, Integer> map = new TreeMap<>();
			int kk = 0;
			for(int i=0;i<n;i++)
			{
				int cur = uf.findSet(i);
				int idx = 0;
				if(map.get(cur) == null)
				{
					idx = kk++;
					map.put(cur, idx);
				}
				else
					idx = map.get(cur);
				sets[idx].add(i);
			}
			LCA[] lcas = new LCA[uf.numSets];
			for(int i=0;i<lcas.length;i++)
			{
				lcas[i] = new LCA(sets[i]);
			}
			int q = sc.nextInt();
			while(q-->0)
			{
				int u = sc.nextInt()-1;
				int v = sc.nextInt()-1;
				//				System.out.println(uf.findSet(v));
				int idx = map.get(uf.findSet(v));
				if(uf.isSameSet(u, v))
				{
					LCA curLCA = lcas[idx];
					int lca = (curLCA.query(u, v));
					int uu = curLCA.hash.get(u);
					int vv = curLCA.hash.get(v);
					System.out.println("LCA is" + lca);
					System.out.println(lcas[idx].dist[uu] + lcas[idx].dist[vv]  - 2 * lcas[idx].dist[lca] );
				}
				else
					System.out.println(-1);
			}
			System.out.println();
		}
	}
	static ArrayList<Integer> adj[];
	static boolean[] vis;
	static class LCA
	{
		int[] E,L,H;
		int k,nn;
		static int lvl;
		int[][] spt;
		int root;
		int[] dist;
		ArrayList<Integer> adjList[];
		TreeMap<Integer, Integer> hash;
		public LCA(ArrayList<Integer> list)
		{
			//			System.out.println(list);
			hash = new TreeMap<>();
			nn = list.size();
			adjList = new ArrayList[list.size()];
			for(int i=0;i<adjList.length;i++)
				adjList[i] = new ArrayList<>();
			for(int i=0;i<nn;i++)
				hash.put(list.get(i), i);

			for(int i=0;i<list.size();i++)
				for(Integer x : adj[list.get(i)])
					if(hash.get(x) != null)
						adjList[hash.get(list.get(i))].add(hash.get(x));
			
//						System.out.println(Arrays.toString(adjList));
//						System.out.println(Arrays.toString(adj));
			int[] inDeg = new int[nn];
			for(int i=0;i<adjList.length;i++)
				for(int x : adjList[i])
					inDeg[x]++;
			for(int i=0;i<nn;i++)
				if(inDeg[i] == 0)
				{
					root = i;
					break;
				}
			System.out.println("rooot is   " + root);
			build();
		}
		void dfs(int u , int d)
		{
			vis[u] = true;
			dist[u] = d;
			if(H[u] == -1)
				H[u] = k;
			E[k] = u;
			L[k++] = lvl;
			for(int v : adjList[u])
				if(!vis[v])
				{
					lvl++;
					dfs(v,d + 1);
					lvl--;
					if(H[u] == -1)
						H[u] = k;
					E[k] = u;
					L[k++] = lvl;
				}
		}
		
		void bfs()
		{
			Queue<Integer> q = new LinkedList<>();
			Arrays.fill(dist, -1);
			q.add(root);
			dist[root] = 0;
			while(!q.isEmpty())
			{
				int cur = q.poll();
				for(int x : adjList[cur])
					if(dist[x] == -1)
					{
						dist[x] = 1 + dist[cur];
						q.add(x);
					}
			}
		}
		void build()
		{
			int n = nn;
			vis = new boolean[n];
			dist = new int[n];
			nn = n;
			E = new int[2*n];
			L = new int[2*n];
			H = new int[n];
			vis = new boolean[n];
			Arrays.fill(H, -1);
			dfs(root,0);
			n = nn*2;
			spt = new int[n][(int)(Math.log(n)/Math.log(2)+1)];
//			System.out.println(spt.length);
			for(int i=0;i<n;i++)
				spt[i][0] = i;
			for(int j=1;(1<<j) <= n;j++)
				for(int i=0;i + (1<<j)-1<n;i++)
					if(L[spt[i][j-1]] > L[spt[i+(1<<(j-1))][j-1]])
						spt[i][j] = spt[i+(1<<(j-1))][j-1];
					else
						spt[i][j] = spt[i][j-1];
			//bfs();
			System.out.println(Arrays.toString(dist));
		}

		int query(int i , int j)
		{
			i = hash.get(i);
			j = hash.get(j);
			i = Math.min(i, j);
			j = Math.max(i, j);
//			System.out.println(i + " " + j);
			int k = (int)(Math.log(j-i+1)/Math.log(2));
			if(L[spt[i][k]] < L[spt[j-(1<<k)+1][k]])
				return spt[i][k];
			else
				return spt[j-(1<<k)+1][k];
		}
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
	static class UnionFind {                                              
		int[] p, rank, setSize;
		int numSets;

		public UnionFind(int N) 
		{
			p = new int[N];
			rank = new int[N];
			setSize = new int[N];
			numSets = N;
			for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
		}

		public int findSet(int i) 
		{ 
			if (p[i] == i) return i;
			else return p[i] = findSet(p[i]);
		}

		public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

		public void unionSet(int i, int j) 
		{ 
			if (isSameSet(i, j)) 
				return;
			numSets--; 
			int x = findSet(i), y = findSet(j);
			// rank is used to keep the tree short
			if (rank[x] > rank[y]) 
			{ 
				p[y] = x;
				setSize[x] += setSize[y]; 
			}
			else
			{	
				p[x] = y;
				setSize[y] += setSize[x];
				if(rank[x] == rank[y])
					rank[y]++; 
			} 
		}

		public int numDisjointSets() { return numSets; }

		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
	}
}
