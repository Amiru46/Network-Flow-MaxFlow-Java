public class Edge {

    // The starting node of the edge
    int from;

    // The ending node of the edge
    int to;

    // The total capacity of the edge
    int capacity;

    // The current flow through the edge
    int flow;

    //In flow network algorithms (like Ford-Fulkerson or Edmonds-Karp),
    // a residual edge represents the reverse direction of a flow and is used to potentially "undo" or "adjust" flow.
    Edge residual;

    // Constructor to initialize the edge with from-node, to-node, and capacity
    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    // Returns the remaining capacity on this edge
    public int remainingCapacity() {
        return capacity - flow;
    }

    // Augments flow along this edge and updates the residual edge
    //The bottleNeck is the maximum amount of flow that can be pushed through a path
    // from source to sink in a single iteration of a flow algorithm.
    public void augment(int bottleNeck) {
        this.flow += bottleNeck;
        this.residual.flow -= bottleNeck;
    }

    // Checks if this edge is a residual edge (used in flow algorithms)
    public boolean isResidual() {
        return capacity == 0;
    }

    // Returns a string representation of the edge for debugging
    @Override
    public String toString() {
        return String.format("Edge %d -> %d | flow = %d / %d", from, to, flow, capacity);
    }
}
