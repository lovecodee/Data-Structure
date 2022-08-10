package AVL.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Jin
 * @Date 2022年08月2022/8/3日18:42
 */
public class Graph {
    /**
     * vertesList:存储顶点集合
     * */
    private ArrayList<String> vertesList;
   /**
    * edges:存储图对应的邻接矩阵
    * */
    private int[][]edges;
    /**
     * numoFEdges:表示边的数目
     * */
    private int numoFEdges;
    /**
     * isVisited:记录某个节点是否被访问
     * */
    private boolean[]isVisited;
    public static void main(String[] args) {
        int n=5;
        //结点的个数
        String vertexs[]={"A","B","C","D","E"};
        //创建图对象
        Graph graph=new Graph(n);
        //循环添加顶点
        for(String vertex:vertexs){
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B  A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(2,4,1);
        //显示邻接矩阵
        graph.showGraph();
        //深度遍历（DFS）
        System.out.println("深度遍历：");
        graph.DFS();
        System.out.println();
        //广度优先（BFS）
        System.out.println("广度优先：");
        graph.BFS();
    }
    //构造器
    public Graph(int n){
        edges=new int[n][n];
        vertesList=new ArrayList<String>(n);
        numoFEdges=0;
    }
    /**
     * 深度优先遍历算法
     * i :第一次就是0
     * */
    public void DFS(boolean[]isVisited,int i){
        System.out.print(getValueByIndex(i)+"->");
        //将结点设置为已经访问
        isVisited[i]=true;
        //查找结点i的第一个邻接点
        int w=getFirstNeighbor(i);
        while(w!=-1){
            if(!isVisited[w]){
                DFS(isVisited,w);
            }
            //如果w结点已经被访问
            w=getNextNeighour(i,w);
        }
    }
    /**
     * 对DFS进行一个重载，遍历所有的节点，进行DFS
     * */
    public void DFS(){
        isVisited=new boolean[5];
        //遍历所有的节点，进行DFS
        for (int i = 0; i <getNumOfVertex() ; i++) {
            if(!isVisited[i]){
                DFS(isVisited,i);
            }
        }
    }
    /**
     * 对一个结点进行广度优先遍历
     * */
    private void BFS(boolean[]isVisited,int i){
        int u;
        //表示队列的一个头结点对应的下标
        int w;
        //表示邻接结点w
        //队列：记录结点访问顺序
        LinkedList queue=new LinkedList();
        //访问结点，输出节点信息
        System.out.print(getValueByIndex(i)+"=>");
        //标记已访问
        isVisited[i]=true;
        //将结点加入队列
        queue.addLast(i);
        while(!queue.isEmpty()){
            //取出队列的头结点下标
            u=(Integer) queue.removeFirst();
            //得到第一个邻接点的下标w
            w=getFirstNeighbor(u);
            while(w!=-1){
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"=>");
                    //标记已访问
                    isVisited[w]=true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个节点
                w=getNextNeighour(u,w);
                //体现广度优先
            }
        }
    }
    /**
     * 遍历所有的结点，都进行广度优先搜索
     * */
    public void BFS(){
        isVisited=new boolean[5];
        for (int i = 0; i <getNumOfVertex() ; i++) {
            if(!isVisited[i]){
                BFS(isVisited,i);
            }
        }
    }
    /**
     * 得到第一个邻接节点的下标
     * @return 如果存在就返回对应的下标，否则就返回-1
     * */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertesList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
            return -1;
    }
    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     * */
    public int getNextNeighour(int v1,int v2){
        for (int j = v2+1; j <vertesList.size() ; j++) {
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }
    //图中常用方法
    /**
     * 返回结点的个数
     * */
    public int getNumOfVertex(){
        return vertesList.size();
    }
    /**
     * 得到边的数目
     * */
    public int getNumoFEdges(){
        return numoFEdges;
    }
    /**
     * 返回结点i(下标)对应的数据 0->“A”  1->"B"  2->"C"
     * */
    public String getValueByIndex(int i){
        return vertesList.get(i);
    }
    /**
     * 返回两个节点的权值
     * */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    /**
     *展示邻接矩阵
     * */
    public void showGraph(){
        for (int []link:edges) {
            System.err.println(Arrays.toString(link));;
        }
    }
    //插入节点
    public void insertVertex(String vertex){
        vertesList.add(vertex);
    }
    //添加边
    /**
     * v1:表示点的下标即是第几个顶点  "A"-"B" "A"->0 "B"->1
     * v2:第二个顶点对应的下标
     * weight:表示权值
     * */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numoFEdges++;
    }
}
