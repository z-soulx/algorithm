package com.study.soulx.algorithm4.graph.undirectedgraph;

import edu.princeton.cs.algs4.BreadthFirstPaths;

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: algorithm
 * @description: 广度优先搜索路径 - 无向图
 * 广度优先主要是隐式调用栈（递归）转化成显示调用队列
 * 搜索和搜索路径差不多就不单独写广度搜索了
 * see {@link BreadthFirstPaths}
 * @author: soulx
 * @create: 2019-07-24 17:28
 **/
public class BFSPath {
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
    public BFSPath(Graph g, int s) {
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
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        while (queue.peek()!= null){
            int vTmp = queue.poll();
            for (int w : g.adj(vTmp)) {
            if (!marks[w]) {
                edgeTo[w] = vTmp;
                marks[w] = Boolean.TRUE;
               queue.offer(w);
            }
        }
        }

        //深度优先
//        for (int w : g.adj(v)) {
//            if (!marks[w]) {
//                edgeTo[w] = v;
//                dfs(g, w);
//            }
//        }

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
        BFSPath dfs = new BFSPath(graph, s);

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
