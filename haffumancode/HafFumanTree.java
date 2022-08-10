package haffumancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author Jin
 * @ Date 2022年07月2022/7/9日14:21
 *  实现：哈夫曼树的创建（带权路径长最短WPL）
 */
public class HafFumanTree {
    public static void main(String[] args) {
        int arr[]={13,7,8,3,29,6,1};
        Node root=haHumanTree(arr);
        System.out.println("前序遍历的结果为：");
        preOrder(root);
    }
    /**变写一个前序遍历的方法*/
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("是空树，不能遍历！！");
        }
    }
    /**
     * 赫夫曼树的实现：
     *    （1）遍历原数组arr
     *    （2）将arr的每一个元素构成一个Node
     *    （3）将Node放入到ArrayList中
     * */
    public static Node haHumanTree(int[]arr){
        List<Node> nodes=new ArrayList<Node>();
        for(int value:arr){
            nodes.add(new Node(value));
        }
        while(nodes.size()>1) {
            Collections.sort(nodes);
            //取出根节点权值最小的两棵二叉树
            //（1）取出权值最小的结点
            Node leftNode = nodes.get(0);
            //（2）取出权值第二小的结点
            Node rightNode = nodes.get(1);
            //（3）构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //（4）从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //（5）将parent结点接入到nodes
            nodes.add(parent);
        }
        //返回赫夫曼树的头结点
        return nodes.get(0);
    }
}
/**创建结点类
 * 为了让Node对象持续排序Collections集合排序
 * 让Node实现了Comparable接口
 * */
class Node implements Comparable<Node>{
    int value;
    /**表示结点的权值*/
    Node left;
    /** 表示的是左节点 */
    Node right;
    /** 表示的是右节点 */
    public Node(int value) {
        this.value = value;
    }
    /**重写 toString() 方法*/
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    @Override
    public int compareTo(Node o) {
        //表示的是从小到大排序
        return this.value-o.value;
    }
    /**写一个前序遍历*/
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }

    }
}