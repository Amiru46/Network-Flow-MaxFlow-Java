import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Load network from the input file
            FlowNetwork network = loadFromFile("C:\\Amiru\\Bsc (hons) computer science\\GitHUB\\Network-Flow-MaxFlow-Java\\w2082097\\input\\bridge_5.txt");

            System.out.println("Flow Network Loaded:");
            network.printNetwork();

            int source = 0;
            int sink = network.size() - 1;

            // Initialize the max flow solver
            MaxFlowSolver solver = new MaxFlowSolver(network, source, sink);

            // Measure start time
            long startTime = System.nanoTime();

            // Compute maximum flow
            int result = solver.getMaxFlow();
            
            // Measure end time
            long endTime = System.nanoTime();

            System.out.println("\nFinal Flows:");
            network.printNetwork();

            System.out.println("\nMaximum Flow from " + source + " to " + sink + ": " + result);
            System.out.printf("Execution Time: %.2f ms\n", (endTime - startTime) / 1e6); // Print time in milliseconds

        } catch (IOException e) {
            System.out.println("Failed to read input file: " + e.getMessage());
        }
    }

    // Parser method to read a flow network from a file
    public static FlowNetwork loadFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        int n = Integer.parseInt(br.readLine().trim());
        FlowNetwork net = new FlowNetwork(n);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 3) {
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                int cap = Integer.parseInt(parts[2]);
                net.addEdge(from, to, cap);
            }
        }
        br.close();
        return net;
    }
}
