package com.study.soulx.algorithm4.graph.undirectedgraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.NonrecursiveDFS;

/**
 * @program: algorithm
 * @description: 深度优先遍历算法-无向图的处理
 * 1 能够直接得出谋点是否连通
 * 2 连同个数
 * See {@link DepthFirstSearch} for a official version.
 * See {@link NonrecursiveDFS} for a non-recursive version.  无递归版本
 * @author: soulx
 * @create: 2019-07-24 15:18
 **/
public class DFSearch {
    private boolean[] marks;
    private int count;

    /**
     * @Description: 找到和起点s连同的所有顶点
     * @Param: [G, s]
     * @return:
     * @Author: soulx
     * @Date: 2019-07-24
     */
    DFSearch(Graph g, int s) {
        //默认是false
        marks = new boolean[g.V()];
        validateVertex(s);
        dfs(g, s);
    }

    private void validateVertex(int v) {
        int V = marks.length;
        if (v < 0 || v >= V){
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }
    /**
     * @Description: v和s是连通的吗
     * @Param: [v]
     * @return: boolean
     *@Author: soulx
     * @Date: 2019-07-24
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marks[v];
    }

    /**
     * @Description: 与s连通的顶点总数
     * @Param: [v]
     * @return: boolean
     * @Author: soulx
     * @Date: 2019-07-24
     */
    public int count() {

        return count;
    }

    /**
     * @Description: 深度遍历该图，标记所有连通
     * @Param: [v]
     * @return: void
     * @Author: soulx
     * @Date: 2019-07-24
     */
    private void dfs(Graph g, int v) {
        marks[v] = Boolean.TRUE;
        count++;
        for (int ve : g.adj(v)) {
            if (!marks[ve]) {
                dfs(g, ve);
            }
        }
    }
}
