import java.util.Scanner;
import java.util.ArrayList;
public class Tree {
    public class Node{
        int key;
        boolean visited;
        int currentSum;
        ArrayList<Node> children;
        ArrayList<Integer> weights;
        Node(int key) {
            this.key = key;
            this.visited = false;
            this.currentSum = 0;
            this.children = new ArrayList<>();
            this.weights = new ArrayList<>();
        }
    }

    Node nodes[];
    int pathNum=0;

    int targetSum;
    Tree(int size){
        nodes=new Node[size+1];
        for(int i=1;i<=size;i++){
            nodes[i] = new Node(i);
        }
    }
    void dfs(Node node, int currentSum) {
        if (node == null || node.visited) return;

        node.visited = true;
        boolean isLeaf = true;

        for (int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            if (!child.visited) {
                isLeaf = false;
                int weight = node.weights.get(i);
                dfs(child, currentSum + weight);
            }
        }

        if (isLeaf && currentSum == targetSum) {
            pathNum++;
        }
        node.visited = false;
    }



    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);

        int n=input.nextInt();
        int sum=input.nextInt();

        Tree tree=new Tree(n);
        tree.targetSum=sum;
        for(int i=0;i<n-1;i++){
            int u=input.nextInt();
            int v=input.nextInt();
            int weight=input.nextInt();
            tree.nodes[u].children.add(tree.nodes[v]);
            tree.nodes[v].children.add(tree.nodes[u]);
            tree.nodes[u].weights.add(weight);
            tree.nodes[v].weights.add(weight);
        }
        tree.dfs(tree.nodes[1], 0);
        System.out.println(tree.pathNum);

    }
}
