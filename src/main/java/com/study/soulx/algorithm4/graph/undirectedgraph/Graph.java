package com.study.soulx.algorithm4.graph.undirectedgraph;

import edu.princeton.cs.algs4.Bag;

/**
 * @program: algorithm
 * @description: 无向图数据类型实现,邻接表实现法
 * 1 顶点数和边数
 * 2 于该点相邻的顶点
 * 3 添加的操作
 * see {@link edu.princeton.cs.algs4.Graph}
 * array of {@link Bag} objects
 * @author: soulx
 * @create: 2019-07-24 11:55
 **/
public class Graph  {
    /**
     * 换行符屏蔽linux和windows的区别
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * 顶点数目
     */
    private final int V;
    /**
     * 边的数目
     */
    private int E;
    /**
     * 邻接表
     */
    private Bag<Integer>[] adj;

    /**
     * @Description: 创建一个含有V个顶点的图
     * @Param: [V]
     * @return:
     * @Author: soulx
     * @Date: 2019-07-24
     */
    @SuppressWarnings("unchecked")
    Graph(int V){
        if (V < 0){
            throw new IllegalArgumentException("Number of vertices must be non_negative");
        }
        this.V = V;
        this.E = 0;
        //创建领接表
        this.adj = (Bag<Integer>[]) new Bag[V];
        //将所有邻接表初始化为空
        for (int v = 0; v < V; v++) {
            this.adj[v] = new Bag<Integer>();
        }
    };
    //从标准输入流in 读入一幅图；

    /**
     * @Description: 顶点数
     * @Param: []
     * @return: int
     * @Author: soulx
     * @Date: 2019-07-24
     */
    public int V() {
        return this.V;
    }

    /**
     * @Description: 边数
     * @Param: []
     * @return: int
     * @Author: soulx
     * @Date: 2019-07-24
     */
    public int E() {
        return this.E;
    }

    /**
     * @Description: 向图中添加一条边V_W
     * @Param: [v, w]
     * @return: void
     * @Author: soulx
     * @Date: 2019-07-24
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;

    }

    /**
     * @Description: 和v相邻的所有顶点
     * @Param: [v]
     * @return: java.lang.Iterable<java.lang.Integer>
     * @Author: soulx
     * @Date: 2019-07-24
     */
    public Iterable<Integer> adj(int v) {
         validateVertex(v);
        return adj[v];
    }
    /**
     * @Description: 一个点的深度，有几个和他相连得边
     * @Param: [v]
     * @return: int
     * @Author: soulx
     * @Date: 2019-07-24
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * @Description: 输出格式定义
     * @Param: []
     * @return: java.lang.String
     * @Author: soulx
     * @Date: 2019-07-24
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * @Description: 校验顶点
     * @Param: [v]
     * @return: void
     * @Author: soulx
     * @Date: 2019-07-24
     */
    private void validateVertex(int v) {

        if (v < 0 || v >= V){
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }


}
