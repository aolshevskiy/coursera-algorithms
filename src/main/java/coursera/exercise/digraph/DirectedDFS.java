package coursera.exercise.digraph;

import com.coursera.algorithms.algs4.Bag;
import com.coursera.algorithms.algs4.Digraph;
import com.coursera.algorithms.stdlib.In;
import com.coursera.algorithms.stdlib.StdOut;

/*************************************************************************
 *  Compilation:  javac DirectedDFS.java
 *  Execution:    java DirectedDFS V E
 *  Dependencies: Digraph.java Bag.java com.coursera.algorithms.stdlib.In.java com.coursera.algorithms.stdlib.StdOut.java
 *  Data files:   http://www.cs.princeton.edu/algs4/42directed/tinyDG.txt
 *
 *  Determine single-source or multiple-source reachability in a digraph
 *  using depth first search.
 *  Runs in O(E + V) time.
 *
 *  % java DirectedDFS tinyDG.txt 1
 *  1
 *
 *  % java DirectedDFS tinyDG.txt 2
 *  0 1 2 3 4 5
 *
 *  % java DirectedDFS tinyDG.txt 1 2 6
 *  0 1 2 3 4 5 6 9 10 11 12 
 *
 *************************************************************************/

public class DirectedDFS {
    private boolean[] marked;  // marked[v] = true if v is reachable
                               // from source (or sources)

    // single-source reachability
    public DirectedDFS(ExactDigraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // multiple-source reachability
    public DirectedDFS(ExactDigraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources)
            dfs(G, v);
    }

    private void dfs(ExactDigraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    // is there a directed path from the source (or sources) to v?
    public boolean marked(int v) {
        return marked[v];
    }

    // test client
    public static void main(String[] args) {

        // read in ExactDigraph from command-line argument
        In in = new In(args[0]);
        ExactDigraph G = new ExactDigraph(in);

        // read in sources from command-line arguments
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i < args.length; i++) {
            int s = Integer.parseInt(args[i]);
            sources.add(s);
        }

        // multiple-source reachability
        DirectedDFS dfs = new DirectedDFS(G, sources);

        // print out vertices reachable from sources
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) StdOut.print(v + " ");
        }
        StdOut.println();
    }

}
