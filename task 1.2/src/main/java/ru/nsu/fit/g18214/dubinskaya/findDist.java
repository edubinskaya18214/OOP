package ru.nsu.fit.g18214.dubinskaya;

import java.util.Scanner;

public class findDist {
    algoritm fw = new algoritm();

    public findDist() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] graph = new int[n][n];
        int v1 = scanner.nextInt();
        int v2 = scanner.nextInt();

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                graph[i][j] = scanner.nextInt();

        fw.floydWarshall(graph, n);
        if (fw.takeDist(v1 - 1 , v2 -1 )!= -1)
            System.out.println(fw.takeDist(v1, v2 ));
        else System.out.println("impossible to get from v1 to v2");
    }

    public static void main(String[] args) {
        findDist w = new findDist();
    }
}
