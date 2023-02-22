/**James Dirr CSC-464 Program 2 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Prog2 {

    static int  KruskalCost = 0;
    static int PrimCost = 0;
    static HashSet<EdgeNode> PrimMCST = new HashSet<EdgeNode>();
    static HashSet<EdgeNode> KruskalMCST= new HashSet<EdgeNode>();

    public static void main(String[] args) throws FileNotFoundException {




        System.out.println("Enter File Location: ");
        Scanner input = new Scanner(System.in);
        String fileloc = input.nextLine();
        File graph = new File(fileloc);
        long startTime = System.nanoTime();
        primAlg(graph);
        long endTime = System.nanoTime();
        double duration = (double) (endTime - startTime) / 1000000.0;

        System.out.println("Prim's Algorithm Cost : " + PrimCost + "\n");
        for (EdgeNode edge: PrimMCST
             ) {
            System.out.println(edge.getVertex1() + " " + edge.getVertex2() + " Weight: " + edge.getWeight() + "\n");
        }
        System.out.println("Total Time Elapsed : " + duration + " milliseconds.\n");

        startTime = System.nanoTime();
        kruskalAlg(graph);
        endTime = System.nanoTime();
        duration = (double) (endTime - startTime) / 1000000.0;

        System.out.println("Kruskal Algorithm Cost : " + KruskalCost + "\n");
        for (EdgeNode edge: KruskalMCST
        ) {
            System.out.println(edge.getVertex1() + " " + edge.getVertex2() + " Weight: " + edge.getWeight() + "\n");
        }
        System.out.println("Total Time Elapsed : " + duration + " milliseconds." + "\n");
    }

    public static void primAlg(File graph) throws FileNotFoundException {
        int numVertex = 0;
        int numEdge;
        Scanner read = new Scanner(graph);
        numVertex = read.nextInt();
        numEdge = read.nextInt();
        //System.out.println("numvertex = " + numVertex + ", numedge = " + numEdge);
        EdgeNode AdjList[] = new EdgeNode[numVertex];
        HashSet<Integer> S = new HashSet<Integer>();
        HashSet<Integer> VminusS = new HashSet<Integer>();

        S.add(0);
        for (int i = 1;  i <= numVertex-1; i++){
            VminusS.add(i);
        }
        while (read.hasNextLine()){
            int v = read.nextInt();
            int u = read.nextInt();
            int w = read.nextInt();
            EdgeNode temp1 = new EdgeNode(v, u ,w);
            EdgeNode temp2 = new EdgeNode(u, v ,w);
            //not setting whole edge to next??
            if (AdjList[u] != null){
                temp1.setNext(AdjList[u]);
            }
            AdjList[u] = temp1;
            if (AdjList[v] != null){
                temp2.setNext(AdjList[v]);
            }
            AdjList[v] = temp2;
        }
        PriorityQueue<EdgeNode> PQ = new PriorityQueue<>();
        int  Curvertex = 0;
        EdgeNode temp = AdjList[Curvertex];
        while (temp != null)
        {
            PQ.add(temp);
            temp = temp.getNext();
        }
        EdgeNode min;
        int x;
        while ( (PQ.size() > 0)  &&  (PrimMCST.size() < numVertex-1 ))
        {
            min = PQ.poll();
            x = min.getVertex1();
            if (! S.contains(x))
            {
                S.add(x);
                PrimMCST.add(min);
                PrimCost += min.getWeight();
                VminusS.remove(x);
                temp = AdjList[x];
                while (temp != null)
                {
                    if ( ! S.contains(temp.getVertex1()))
                    {
                        PQ.add( temp );
                    }
                    temp = temp.getNext( );
                }
            }
            }
    }

    public static void kruskalAlg(File graph) throws FileNotFoundException {
        int numVertex = 0;
        int numEdge;
        Scanner read = new Scanner(graph);
        numVertex = read.nextInt();
        numEdge = read.nextInt();
        //System.out.println("numvertex = " + numVertex + ", numedge = " + numEdge);
        UnionFind UF = new UnionFind(numVertex);
        PriorityQueue<EdgeNode> PQ = new PriorityQueue<>();
        while (read.hasNextLine()) {
            int v = read.nextInt();
            int u = read.nextInt();
            int w = read.nextInt();
            EdgeNode temp1 = new EdgeNode(v, u, w);
            PQ.add(temp1);
        }
        EdgeNode min;
        int x,y;
        while ( (PQ.size() > 0)  &&  (KruskalMCST.size() < numVertex-1 )){
            min= PQ.poll();
            x = min.getVertex1();
            y = min.getVertex2();
            if (UF.Find(x) != UF.Find(y)){
                KruskalMCST.add(min);
                KruskalCost += min.getWeight();
                UF.Union(x,y);
            }
        }

    }

}