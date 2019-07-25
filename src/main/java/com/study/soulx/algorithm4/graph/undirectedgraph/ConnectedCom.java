package com.study.soulx.algorithm4.graph.undirectedgraph;

import edu.princeton.cs.algs4.CC;

/**
 * @program: algorithm
 * @description: 连通分量  检索出所有有的连通树，eg：一部分一部分的中间断开的
 * see {@link CC}
 * @author: soulx
 * @create: 2019-07-25 09:33
 **/
public class ConnectedCom {
    /**
     * 标识
     */
    private boolean[] marks;

    /**
     * 所属于的连通分量标识记录
     */
    private int[] id;

    /**
     * 连通分量的个数
     */
    private int count;

    /**
     * 连通分量的连通个数记录
     */
    private int[] size;


    public ConnectedCom(Graph g){
        marks = new boolean[g.V()];
        for(int v = 0;v < g.V();v++){
            if(!marks[v]){
                dfs(g,v);
                count++;
            }
        }

    }

    /**
     *  v和w 连通吗
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    /**
     * 连通分量数
     * @return
     */
    public int count(){
        return count;
    }

    /**
     * v所在的连通分量的标识符（0~count（）-1）
     * @param v
     * @return
     */
    public int id(int v){
        validateVertex(v);
        return id[v];
    }

    /**
     * 某个分量的连通个数
     * @param v
     * @return
     */
    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    private void validateVertex(int v) {
        int V = marks.length;
        if (v < 0 || v >= V){
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
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
        id[v] = count;
        //连通个数累加
        size[count]++;
        for (int ve : g.adj(v)) {
            if (!marks[ve]) {
                dfs(g, ve);
            }
        }
    }
}
