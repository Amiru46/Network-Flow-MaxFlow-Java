import java.util.*;

public class MaxFlowSolver {
    // Represents the flow network
    private final FlowNetwork network;
    // Source and sink nodes
    private final int source, sink;
    // Variable to store the final maximum flow
    private int maxFlow = 0;

    //count BFS runs
    private int bfsRuns = 0;

    // This is constructor to initialize the solver with network, source and sink
    public MaxFlowSolver(FlowNetwork network, int source, int sink) {
        this.network = network;
        this.source = source;
        this.sink = sink;
    }

    // Main method to compute maximum flow using BFS (I used Edmonds-Karp)
    public int getMaxFlow() {
        while (true) {
            // Increment BFS runs
            bfsRuns++;
            // Array to store the previous edge to reconstruct the path
            Edge[] prev = new Edge[network.size()];
            // Queue for BFS
            Queue<Integer> q = new LinkedList<>();
            q.add(source);

            // Standard BFS to find shortest augmenting path
            while (!q.isEmpty() && prev[sink] == null) {
                int u = q.poll();
                for (Edge e : network.getEdges(u)) {
                    if (prev[e.to] == null && e.remainingCapacity() > 0 && e.to != source) {
                        prev[e.to] = e;
                        q.add(e.to);
                    }
                }
            }


            // If No augmenting path found
            if (prev[sink] == null) break;

            // Find bottleneck capacity along the path
            int bottleNeck = Integer.MAX_VALUE;

            //This is for store path
            List<Integer> path = new ArrayList<>();

            for (Edge e = prev[sink]; e != null; e = prev[e.from]) {
                bottleNeck = Math.min(bottleNeck, e.remainingCapacity());
            }

            // Augment the flow along the path
            for (Edge e = prev[sink]; e != null; e = prev[e.from]) {
                e.augment(bottleNeck);
                path.add(e.to);
            }
            path.add(source);
            Collections.reverse(path);

            System.out.println("Augmenting path: " + path + " | Flow added: " + bottleNeck);
            maxFlow += bottleNeck;
        }

        System.out.println("Total BFS Runs: " + bfsRuns);
        return maxFlow;
    }
}
