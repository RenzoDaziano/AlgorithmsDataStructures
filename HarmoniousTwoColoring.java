import java.util.*;

/**
 * This class solves the harmonious 2-coloring problem on an undirected, unweighted, simple, loopless graph.
 * A harmonious 2-coloring is an assignment of each vertex to one of 2 colors such that the end points of each conflict edge have different colors,
 * and the end points of each harmony edge have the same color.
 */
public class HarmoniousTwoColoring {
    static List<Integer>[] adj; // adjacency list representation of the graph
    static int[] color; // color array to keep track of the color of each vertex

    /**
     * This method reads the input, constructs the graph, and runs the DFS algorithm to determine if the graph can be harmoniously 2-colored.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of vertices
        int m = sc.nextInt(); // number of edges
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();
            if (c == 0) { // harmony edge
                adj[u].add(v);
                adj[v].add(u);
            } else { // conflict edge
                adj[u].add(v);
                adj[v].add(u);
            }
        }
        color = new int[n];
        Arrays.fill(color, -1); // -1 represents uncolored vertices
        boolean canColor = true;
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) { // not yet colored
                canColor &= dfs(i, 0); // use bitwise AND to keep track of coloring consistency
            }
        }
        System.out.println(canColor ? "1" : "0"); // output 1 if the graph can be harmoniously 2-colored, otherwise 0
    }

    /**
     * This method implements the recursive depth first search algorithm to color the vertices of the graph.
     * @param u the current vertex being colored
     * @param c the color to be assigned to vertex u
     * @return true if the graph can be harmoniously 2-colored, false otherwise
     */
    static boolean dfs(int u, int c) {
        color[u] = c;
        for (int v : adj[u]) {
            if (color[v] == -1) { // not yet colored
                if (!dfs(v, 1 - c)) { // recursively color v with the opposite color of u
                    return false; // if a conflict is detected, return false immediately
                }
            } else if (color[v] == c) { // conflict is detected
                return false;
            }
        }
        return true;
    }
}
