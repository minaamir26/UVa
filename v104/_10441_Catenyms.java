package v104;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class _10441_Catenyms {
	
	static ArrayList<Pair> adj[];
	static String ans;
//	static 
	static void euler(int u)
	{
		
		for(Pair v: adj[u])
		{
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			String[] arr = new String[n];
			for(int i=0;i<n;i++)
				arr[i] = sc.next();
			adj = new ArrayList[26];
			for(int i=0;i<26;i++)
				adj[i] = new ArrayList<>();
			int[] inDeg = new int[26];
			int[] outDeg = new int[26];
			
			for(int i=0;i<n;i++)
			{
				adj[arr[i].charAt(0)-'a'].add(new Pair(arr[i].charAt(arr[i].length()-1)-'a' , arr[i]));
				outDeg[arr[i].charAt(0)-'a']++;
				inDeg[arr[i].charAt(arr[i].length()-1)-'a']++;
				
			}
			
			for(int i=0;i<26;i++)
				Collections.sort(adj[i]);
			
			int odd = 0;
			System.out.println(Arrays.toString(adj));
			System.out.println(Arrays.toString(inDeg));
			System.out.println(Arrays.toString(outDeg));
			
			for(int i=0;i<26;i++)
				odd+=(adj[i].size() %2);
			System.out.println(odd);
			
			
		}
		
		
	}
	
	static class Pair implements Comparable<Pair>{
		int a;
		String s;
		Pair(int x, String y)
		{
			a = x;
			s = y;
		}
		@Override
		public int compareTo(Pair o) {
			return s.compareTo(o.s);
		}
		public String toString(){
			return s;
		}
		
	}
}
