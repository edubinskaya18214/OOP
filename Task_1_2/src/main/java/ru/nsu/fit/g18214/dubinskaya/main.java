package ru.nsu.fit.g18214.dubinskaya;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();
        graph g = new graph();
        g.setNumv(n);
        System.out.print("v1: ");
        int v1 = scanner.nextInt();
        System.out.print("v2: ");
        int v2 = scanner.nextInt();
        System.out.print("numEdges: ");
        int numEdges = scanner.nextInt();
        for (int i = 0; i < numEdges; ++i) {
            System.out.print(i+": ");
            g.addEdge(scanner.nextInt()-1,scanner.nextInt()-1,scanner.nextInt());
        }
        int dist = getDist.get(g,v1-1,v2-1);
        System.out.println(dist);
    }
}
