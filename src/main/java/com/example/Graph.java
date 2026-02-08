package com.example;

import java.util.*;
public class Graph {

    public static void main (String []args){
        Scanner input = new Scanner (System.in);
        System.out.print(" enter  number  of nodes:");
        int n = input.nextInt();
        System.out.print(" enter  number  off edges");
        int e = input.nextInt();
        Graph g = new Graph(n);
        System.out.println(" enter edges(u , v)");
        for( int i =0; i<e; i++){
            int u =input.nextInt();
            int v= input.nextInt();
            g.addEdge(u, v);
        }
        int[] in =g.calculateInDegree();
        int [] out = g.calculaterOutDegree();
        System.out.println("  \n node | In-Degree | out-Degree");
        System.out.println("---------------------------------------");
        for(int i =0; i< n; i++){
                System.out.println("   "+ i+ "  |  "+ in[i]+ "    |  "+ out[i] );
        }
        input.close();
    }

private int vertices;
private List<List<Integer>> adj;
public Graph(int v){
    this.vertices= v; 
adj = new ArrayList<>();
for(int i = 0; i<v; i++)  adj.add(new ArrayList<Integer>());

}
    public void addEdge(int u , int v){
        adj.get(u).add(v);
    }
    public int[] calculaterOutDegree(){
        int[] out = new int[vertices];
        for(int i = 0 ; i< vertices ; i++){
            out[i] = adj.get(i).size();
         
        }   return out;

    }
    public int[] calculateInDegree(){
        int[] in = new int[vertices];
        for( int i = 0 ; i< vertices; i++){
            for(int neighbor : adj.get(i)){
                in[neighbor]++;
            }
        } 
        return in;
    }

}

