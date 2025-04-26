import java.util.*;

public class FlowNetwork {

    // number of nodes
    private final int n;

    // adjacency list for edges
    private final List<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public FlowNetwork(int n) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // Adds a forward and residual edge
    public void addEdge(int from, int to, int capacity) {

        Edge e1 = new Edge(from, to, capacity);

        Edge e2 = new Edge(to, from, 0); // residual edge with 0 capacity

        e1.residual = e2;
        e2.residual = e1;
        adj[from].add(e1);
        adj[to].add(e2);
    }

    // Gets the list of edges for a node
    public List<Edge> getEdges(int node) {
        return adj[node];
    }

    // Returns number of nodes
    public int size() {
        return n;
    }

    // Prints all edges (ignoring residual edges)
    public void printNetwork() {
        for (int i = 0; i < n; i++) {
            for (Edge e : adj[i]) {
                if (!e.isResidual()) {
                    System.out.println(e);
                }
            }
        }
    }
}
