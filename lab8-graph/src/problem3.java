import java.util.*;
public class problem3 {
    public int number;
    public ArrayList<Integer>[] adj;
    public boolean[] isProtected;
    problem3(int number)
    {
        this.number=number;
        adj=new ArrayList[number];
        isProtected=new boolean[number];
        for(int i=0;i<number;i++)
        {
            adj[i]=new ArrayList<>();
        }
    }
    void addEdge(int src,int dest)
    {
        adj[src].add(dest);
        adj[dest].add(src);
    }
    public ArrayList<Integer> buildTowers() {
        ArrayList<Integer> towers = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            if (adj[i].size() == 1 && !isProtected[i]) {
                int neighbor = adj[i].get(0);
                if (!isProtected[neighbor]) {
                    towers.add(neighbor + 1);
                    isProtected[neighbor] = true;
                    isProtected[i] = true;
                    for (int adjNode : adj[neighbor]) {
                        isProtected[adjNode] = true;
                    }
                }
            }
        }

        for (int i = 0; i < number; i++) {
            if (!isProtected[i]) {
                towers.add(i + 1);
                isProtected[i] = true;
                for (int adjNode : adj[i]) {
                    isProtected[adjNode] = true;
                }
            }
        }
        return towers;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for(int i=0;i<T;i++)
        {
            int n=input.nextInt();
            int m=input.nextInt();
            problem3 g=new problem3(n);
            for(int j=0;j<m;j++)
            {
                int a=input.nextInt()-1;
                int b=input.nextInt()-1;
                g.addEdge(a,b);
            }
            ArrayList<Integer> towers=g.buildTowers();
            System.out.println(towers.size());
            for(int tower:towers)
            {
                System.out.print(tower+" ");
            }
            System.out.println();
        }
    }
}
