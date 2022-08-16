import java.util.*;
public class Main
{
    public static int[][] shortestpath(int[][] adj, int[][] path) 
    {
        int n = adj.length;
        int[][] ans = new int[n][n];
        copy(ans, adj);
    	for (int k=0; k<n;k++) 
            for (int i=0; i<n; i++) 
        	for (int j=0; j<n;j++) 
                    if (ans[i][k]+ans[k][j] < ans[i][j]) {
                        ans[i][j] = ans[i][k]+ans[k][j];
          		        path[i][j] = path[k][j];
                    }
    	return ans;
    }
    public static void copy(int[][] a, int[][] b) 
    {
        for (int i=0;i<a.length;i++)
            for (int j=0;j<a[0].length;j++)
                a[i][j] = b[i][j];
    }
    public static void main(String[] args) 
    {
        Scanner stdin = new Scanner(System.in);
	    long timeS, timeE, timeR;
        // Tests out algorithm with graph shown in class.
    	int[][] m = new int[8][8];
    	m[0][0] = 10000; m[0][1] = 278;  m[0][2] = 0; m[0][3] = 10000; 
	        m[0][4] = 10000;m[0][5] = 10000;m[0][6] = 10000;m[0][7] = 10000;
	        
	        m[1][0] = 10000; m[1][1] = 10000;  m[1][2] = 10000; m[1][3] = 10000; 
	        m[1][4] = 1440;m[1][5] = 10000;m[1][6] = 10000;m[1][7] = 10000;
	        
	        m[2][0] = 10000; m[2][1] = 10000;  m[2][2] = 10000; m[2][3] = 1920; 
	        m[2][4] = 10000;m[2][5] = 10000;m[2][6] = 10000;m[2][7] = 10000;
	        
	        m[3][0] = 10000; m[3][1] = 10000;  m[3][2] = 10000; m[3][3] = 10000; 
	        m[3][4] = 10000;m[3][5] = 10000;m[3][6] = 748;m[3][7] = 10000;
	        
	        m[4][0] = 10000; m[4][1] = 10000;  m[4][2] = 10000; m[4][3] = 10000; 
	        m[4][4] = 10000;m[4][5] = 1700;m[4][6] = 1150;m[4][7] = 10000;
	        
	        m[5][0] = 10000; m[5][1] = 10000;  m[5][2] = 10000; m[5][3] = 10000; 
	        m[5][4] = 10000;m[0][5] = 10000;m[5][6] = 10000;m[5][7] = 361;
	        
	        m[6][0] = 10000; m[6][1] = 10000;  m[6][2] = 10000; m[6][3] = 10000; 
	        m[6][4] = 10000;m[6][5] = 10000;m[6][6] = 10000;m[6][7] = 101;
	        
	        m[7][0] = 10000; m[7][1] = 10000;  m[7][2] = 10000; m[7][3] = 10000; 
	        m[7][4] = 10000;m[7][5] = 10000;m[7][6] = 10000;m[7][7] = 10000;
        
        int[][] shortpath;
        int[][] path = new int[8][8];

        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                if (m[i][j] == 10000)
                    path[i][j] = -1;
    		else
                    path[i][j] = i;
    	for (int i=0; i<8; i++)
            path[i][i] = i;

    	timeS = System.nanoTime();
		shortpath = shortestpath(m, path);
		
        timeE = System.nanoTime();
        timeR = timeE - timeS;
	    
        // Prints out shortest distances.
        System.out.println("  0  1  2  3  4  5  6  7 ");
        System.out.println("  ---------");
    	for (int i=0; i<8;i++) {
            System.out.print(i + "|");
            for (int j=0; j<8;j++)
                System.out.print(shortpath[i][j]+" ");
            System.out.println();
    	}
        System.out.println("Ruta más corta de un vértice a otro (0 a 4)");
        System.out.print("Vértice inicial: ");
    	int start = stdin.nextInt();
        System.out.print("Vértice final: ");
    	int end = stdin.nextInt();
         String myPath = end + "";
         System.out.println();
        System.out.println("  0  1  2  3  4  5  6  7");
        System.out.println("  ---------");
         for (int i=0; i<8;i++) {
            System.out.print(i + "|");
            for (int j=0; j<8;j++)
                System.out.print(path[i][j]+" ");
            System.out.println();
    	}
    	while (path[start][end] != start) {
            myPath = path[start][end] + " -> " + myPath;
            end = path[start][end];
    	}
    	myPath = start + " -> " + myPath;
    	System.out.println("Esta es la ruta: " + myPath);
    }
}
