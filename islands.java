//ALGO
/*
1) Initialize result (count of islands) as 0 
2) Traverse each index of the 2D matrix. 
3) If the value at that index is 1, check all its 8 neighbours. If a neighbour is also equal to 1, take the union of the index and its neighbour. 
4) Now define an array of size row*column to store frequencies of all sets. 
5) Now traverse the matrix again. 
6) If the value at index is 1, find its set. 
7) If the frequency of the set in the above array is 0, increment the result be 1.

*/
import java.io.*;
import java.util.*;
  
public class Main
{
    public static void main(String[] args)throws IOException
    {
        int[][] a = new int[][] {{1, 1, 0, 0, 0},
                                 {0, 1, 0, 0, 1},
                                 {1, 0, 0, 1, 1},
                                 {0, 0, 0, 0, 0},
                                 {1, 0, 1, 0, 1}
                                };
        System.out.println("Number of Islands is: " +countIslands(a));
     }
  
  
     static int countIslands(int a[][])
     {
        int n = a.length;
        int m = a[0].length;
  
        DisjointUnionSets dus = new DisjointUnionSets(n*m);
  

        for (int j=0; j<n; j++)
        {
            for (int k=0; k<m; k++)
            {
        
                if (a[j][k] == 1)
                {
  
                    if (j+1 < n && a[j+1][k]==1)
                        dus.union(j*(m)+k, (j+1)*(m)+k);
                    if (j-1 >= 0 && a[j-1][k]==1)
                        dus.union(j*(m)+k, (j-1)*(m)+k);
                    if (k+1 < m && a[j][k+1]==1)
                        dus.union(j*(m)+k, (j)*(m)+k+1);
                    if (k-1 >= 0 && a[j][k-1]==1)
                        dus.union(j*(m)+k, (j)*(m)+k-1);
                    if (j+1<n && k+1<m && a[j+1][k+1]==1)
                        dus.union(j*(m)+k, (j+1)*(m)+k+1);
                    if (j+1<n && k-1>=0 && a[j+1][k-1]==1)
                        dus.union(j*m+k, (j+1)*(m)+k-1);
                    if (j-1>=0 && k+1<m && a[j-1][k+1]==1)
                        dus.union(j*m+k, (j-1)*m+k+1);
                    if (j-1>=0 && k-1>=0 && a[j-1][k-1]==1)
                        dus.union(j*m+k, (j-1)*m+k-1);
                }
            }
        }
  

        int[] c = new int[n*m]; //frequency
        int numberOfIslands = 0;
        for (int j=0; j<n; j++)
        {
            for (int k=0; k<m; k++)
            {
                if (a[j][k]==1)
                {
  
                    int x = dus.find(j*m+k);
  
                    if (c[x]==0)
                    {
                        numberOfIslands++;
                        c[x]++;
                    }
  
                    else
                        c[x]++;
                }
            }
        }
        return numberOfIslands;
    }
}
  

class DisjointUnionSets
{
    int[] weight, parent;
    int n;
  
    public DisjointUnionSets(int n)
    {
        weight = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }
  
    void makeSet()
    {
    
        for (int i=0; i<n; i++)
            parent[i] = i;
    }

    int find(int x)
    {
        if (parent[x] != x)
        {
           
            parent[x]=find(parent[x]);
        }
  
        return parent[x];
    }
  
    
    void union(int x, int y)
    {
        
        
        int xParent = find(x);
        int yParent = find(y);
  
      
        if (xParent == yParent)
            return;
  
   
        if (weight[xParent] < weight[yParent])
            parent[xParent] = yParent;
  
    
        else if(weight[yParent]<weight[xParent])
            parent[yParent] = xParent;
  
        else  
        {
            
            parent[yParent] = xParent;
  
            weight[xParent] = weight[xParent] + 1;
        }
    }
}
