import java.util.*;

class Graph {
    private int numVertices; // 顶点的数量
    private LinkedList<Integer>[] adjLists; // 邻接表数组

    // 构造器
    Graph(int vertices) {
        numVertices = vertices;
        adjLists = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjLists[i] = new LinkedList<Integer>();
        }
    }

    // 添加边
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
        adjLists[dest].add(src); // 因为是无向图，所以也要添加反向边
    }

    // 打印图
    void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println("节点 " + i + " 的邻接节点: " + adjLists[i]);
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        g.printGraph();
    }
}
