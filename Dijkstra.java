import java.util.HashSet;

public class Dijkstra {
    public static void main (String[] args){
    	  
    	  Dijkstra d =new Dijkstra();
    	  
    	  int graph[][] = new int[][] {
    		     {0, 3, 1, 0, 5,0},
				 {3, 0, 3, 4, 2,1},
				 {1, 3, 0, 0, 0,0},
				 {0, 4, 0, 0, 8,3},
				 {5, 2, 0, 8, 0,0},
				 {0, 1, 0, 3, 0,0},
			}; 
      
      d.dijkstra(graph);
      }
    
    private int V;
    
    public void dijkstra(int[][] graph){
    	
    	V=graph.length;//get # of vertices
    	
    	HashSet<Integer> Q =new HashSet<Integer>();//点的集合 临时存放
    	
    	int dist[] = new int[V];// set distance
    	
    	int prev[] = new int[V];//set Previous node in optimal path from source
    	
    	for (int i =0;i<V;i++){
    		
    		dist[i]=Integer.MAX_VALUE;
    		
    		prev[i]= -1;
    		
    		Q.add(i);
    	}//initialization
    	
    	dist[0]=0;//initialize source 
    	
    	while(!Q.isEmpty()){
    		
	    	int u = mindist(Q, dist);
	    	
	    	Q.remove(u);// remove finished node
    	
	    	for(int i=0; i<V;i++){
	    		
	    		int alt=0;
	    		
	    		if(graph[u][i]>0 && Q.contains(i)){
	    			
	    			alt=dist[u]+graph[u][i];//temp distance
	    			
	    			if(alt<dist[i]){
	    				
		    			dist[i] = alt;
		    			
		    			prev[i] = u;// alt compare to min dist
		    			
	    			}
	    				
	    		}
	    	}	
    	}
    	
    	System.out.println("node distance previous");
    	
    	for(int i=0;i<V;i++){
    		
    		System.out.println(i+"      "+dist[i]+"      "+prev[i]);
    		
    	}
      }

	private int mindist(HashSet<Integer> q, int[] dist) {
		
		int u = -1;
		
		int min=Integer.MAX_VALUE;
		
		for(Integer v:q){
		if( dist[v]< min){
			
			min=dist[v];
		    u=v;
		   }
		
		}
		return u;
	}// vertex in Q with min dist[u]

}
