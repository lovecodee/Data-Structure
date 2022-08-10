package haffumancode;
import java.util.*;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/9日16:17
 * 功能：  实现了哈夫曼编码
 */
public class HaFumanCode {
    public static void main(String[] args) {
        String str="i like like like java do you like a java";
        byte[] strBytes=str.getBytes();
        System.out.println(strBytes.length);
        List<Node1> nodes=getNode(strBytes);
        System.out.println("nodes"+nodes);
        System.out.println("赫夫曼树");
        Node1 huffmanTreeRoot=creatHuffumanTree(nodes);
        System.out.println("前序遍历");
        preOrder(huffmanTreeRoot);
        //getCodes(huffmanTreeRoot,"",stringBuilder);
        Map<Byte,String> huffmanCodes=getCodes(huffmanTreeRoot);
        System.out.println("生成的哈夫曼编码表"+huffmanCodes);

    }
    /**生成哈夫曼树对应的哈夫曼编码
      *思路：
      *（1）将赫夫曼编码表以Map<Byte,String>中的形式存放
      *       32->01 97->100等等形式[]
      */
    static Map<Byte,String> huffmanCodes=new HashMap<Byte,String>();
    /**（2）在生成哈夫曼编码表示，需要去拼接路径，定义一个StringBuilder 存放某个叶子节点的路径*/
    static StringBuilder stringBuilder=new StringBuilder();
    /**重载getCodes*/
    private static Map<Byte,String> getCodes(Node1  root){
        if(root==null){
            return null;
        }
        //处理root的左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }


    /**
     * 功能：将传入的node结点的所有的叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *     node:传入结点
     *     code:路径：左子节点是 0;右子节点是 1
     *     stringBuilder:用于拼接路径
     * */
    private static void getCodes(Node1 node,String code,StringBuilder stringbuilder){
        StringBuilder stringBuilder2=new StringBuilder(stringbuilder);
        stringBuilder2.append(code);
        if(node!=null){
            //判断其是叶子结点还是非叶子节点
            if(node.data==null){
                //非叶子节点
                //递归处理（向左递归）
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else{
                //是一个叶子节点（表示找到了某个叶子结点的最后）
                huffmanCodes.put(node.data, stringbuilder.toString());
            }
        }
    }



    /**前序遍历方法*/
    private static void preOrder(Node1 root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("哈夫曼树为空！！");
        }
    }

    private static List<Node1> getNode(byte[] bytes){
        //创建一个ArrayList
        ArrayList<Node1> nodes = new ArrayList<Node1>();
        //遍历bytes，统计出每一个byte出现的次数->map[key,value]
        Map<Byte,Integer> counts=new HashMap<>();
        for(byte b:bytes){
            Integer count=counts.get(b);
            if(count==null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //把每个键值对转化成一个Node，并加入到nodes集合中
        //遍历map
        for(Map.Entry<Byte,Integer>entry:counts.entrySet()){
            nodes.add(new Node1(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    /**创建哈夫曼树*/
    private static Node1 creatHuffumanTree(List<Node1> nodes){
        while(nodes.size()>1){
            //排序
            Collections.sort(nodes);
            //取出第一个最小的二叉树
            Node1 leftNode=nodes.get(0);
            Node1 rightNode=nodes.get(1);
            //创建一棵新的二叉树（根节点没有data，只有权值）
            Node1 parent=new Node1(null,leftNode.weight+rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;
            //将已经处理过的结点处理掉
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes种
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
/**创建Node结点*/
class Node1 implements Comparable<Node1> {
    Byte data;
    /**
     * 存放数据（字符）本身，比如‘a’=97  ' '=32
     */
    int weight;
    /**
     * 权值,表示字符出现的次数
     */
    Node1 left;
    Node1 right;

    public Node1(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node1 o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node1[" + "data=" + data + ", weight=" + weight + ']';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }
}
