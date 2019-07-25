package com.study.soulx.algorithm4.graph.undirectedgraph;

import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.Stack;

/**
 * @program: algorithm
 * @description: 深度优先求解边（路径）-无向图
 * see {@link DepthFirstPaths}
 * 1 找出以s开始的路径所有
 * 2 是否存在s-v路径
 * 3 s到v的路径展示
 * edgeTo  unstudied see{@link QuickFindUF}
 * @author: soulx
 * @create: 2019-07-24 16:35
 **/
public class DFSPath {
    /**
     * 遍历的标记
     */
    private boolean[] marks;
    /**
     * 从起点到一个顶点的已知路径的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 起点s
     */
    private final int s;

    /**
     * 找出所有s的路径
     *
     * @param g
     * @param s
     */
    public DFSPath(Graph g, int s) {
        //默认是false
        marks = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g,s);
    }

    /**
     * 是否存在s-v的路径
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marks[v];
    }

    /**
     * s-v的路径集合
     *
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> path = new Stack<>();
        if (!hasPathTo(v)) {
            return path;
        }
        for (int start = v; start != s; start = edgeTo[start]) {
            path.push(start);
        }
        path.push(s);
        return path;
    }

    /**
     * @Description: 深度遍历该图，并记录所有边
     * @Param: [v]
     * @return: void
     * @Author: soulx
     * @Date: 2019-07-24
     */
    private void dfs(Graph g, int v) {
        marks[v] = Boolean.TRUE;
        for (int w : g.adj(v)) {
            if (!marks[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    private void validateVertex(int v) {
        int V = marks.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }





    /**
    * @Description: 测试
    * @Param:
    * @return:
    * @Author: soulx
    * @Date: 2019-07-24
    */
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
