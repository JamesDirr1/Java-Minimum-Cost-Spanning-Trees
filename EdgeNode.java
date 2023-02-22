/**James Dirr CSC-464 Program 2 */

public class EdgeNode implements Comparable<EdgeNode>  {

    private int vertex1, vertex2, weight;
    private EdgeNode next;

    public  EdgeNode(  int vertex1,  int vertex2, int  weight )
    {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight  = weight;
        this.next = null;
    }

    public int getVertex1() {
        return vertex1;
    }

    public void setVertex1(int vertex1) {
        this.vertex1 = vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public void setVertex2(int vertex2) {
        this.vertex2 = vertex2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void  setNext(  EdgeNode  n  )
    {
        this.next = n ;
    }
    public  EdgeNode   getNext( )
    {
        return  this.next;
    }

    public int compareTo(EdgeNode n) {
        if(this.weight < n.weight)
            return -1;
        else if(this.weight  > n.weight)
            return 1;
        else
            return 0;
    }

}
