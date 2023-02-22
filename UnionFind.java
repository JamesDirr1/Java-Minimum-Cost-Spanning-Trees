/**James Dirr CSC-464 Program 2 */

public class UnionFind {
    private int[] parent;    //  array that holds/represents the subsets
    public UnionFind(int size) {
        parent = new int[size];
        for (var i = 0; i < size; i++) {
            parent[i] = i;    // create subsets so each element 1-(size-1) are in their own sets...
        }
    }

    public int Find(int x) {
        if (x == parent[x]) {
            return x;
        }
        // compress the paths as we seek out the root
        return parent[x] = Find(parent[x]);   // now all members point to root
    }

    public void Union(int x, int y)  {
        var px = Find(x);    // what subset is x in?
        var py = Find(y);    // what subset is y in?
        if (px != py) {          // if the sets are NOT the same
            parent[px] = py;     // join set x into set y
        }
    }

    public int Size() { // number of subsets
        int ans = 0;
        for (int i = 0; i < parent.length; ++ i) {
            if (i == parent[i]) ans ++;      // just count the roots
        }
        return ans;
    }
}


