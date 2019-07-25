package com.study.soulx.algorithm4.graph.undirectedgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: algorithm
 * @description: 无向图测试类
 * @author: soulx
 * @create: 2019-07-24 10:05
 **/
public class Test {
    public static void main(String[] args) {
        //V 默认0 开始
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(4, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        int s = 0;
        DFSPath dfs = new DFSPath(graph, s);

        for (int v = 0; v < graph.V(); v++) {
            if (dfs.hasPathTo(v)) {
                System.out.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) {
                        System.out.printf(""+x);
                    } else {
                        System.out.printf("-" + x);
                    }
                }
                System.out.println();
            }
        }
    }
}
