package AVL;

/**
 * @author Jin
 * @Date 2022年08月2022/8/3日16:59
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[]arr={10,12,8,9,7,6};
        AVLTree avlTree=new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历：");
        avlTree.midSearch();
        //测试高度
        System.out.println("在没有平衡处理前：");
        System.out.println("树的高度="+avlTree.getRoot().height());
        System.out.println("树的左子树高度="+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度="+avlTree.getRoot().rightHeight());
        System.out.println("当前的根节点为："+avlTree.getRoot());
        System.out.println("根节点的左子节点为："+avlTree.getRoot().left);
    }
}
/**
 * 创建AVL树
 * */
class AVLTree{
    private Node root;
    public Node getRoot() {
        return root;
    }
    //查找要删除的结点
    public Node search(int value){
        if(root==null){
            return null;
        }else{
            return root.search(value);
        }
    }
    //查找待删结点的父节点
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }
    /**
     * 查找最小结点
     * node: 传入的结点（当前二叉树的根结点）
     * return:返回以node为根结点的二叉排序树的最小节点的值
     * */
    public int delRightTreeMin(Node node){
        Node target=node;
        //循环的查找左子节点
        while(target.left!=null){
            target=target.left;
        }
        delNode(target.value);
        return target.value;
    }
    //删除结点
    public void delNode(int value){
        if(root==null){
            return;
        }else{
            //（1）先去找到待删除的目标结点targerNode
            Node targetNode =search(value);
            //（2）如果找到待删除的结点
            if(targetNode==null){
                return;
            }
            //（3）如果发现当前这棵二叉排序树只有一个结点
            if(root.left==null&&root.right==null){
                root=null;
                return;
            }
            //（4）寻找targetNode的父节点
            Node parent =searchParent(value);
            //如果要删除的结点是叶子结点
            if(targetNode.left==null && targetNode.right==null){
                //判断targetNode是父节点的左子节点，还是右子节点
                if(parent.left!=null&&parent.left.value== targetNode.value){
                    //是左子节点
                    parent.left=null;
                }else if(parent.right!=null&&parent.right.value== targetNode.value){
                    //是右子节点
                    parent.right=null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){
                //删除有两颗子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value=minVal;
            }else{//删除只有一棵子树的结点
                //如果删除的结点有左子节点
                if(targetNode.left!=null){
                    if(parent!=null){
                        //如果targetNode是parent的左子节点
                        if(parent.left.value==value){
                            parent.left=targetNode.left;
                        }else{ //如果targetNode是parent的右子节点
                            parent.right=targetNode.left;
                        }
                    }else{
                        root=targetNode.left;
                    }

                }else{//删除的结点有有右子节点
                    if(parent!=null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }else{
                        root=targetNode.left;
                    }
                }

            }


        }
    }
    //添加结点
    public void add(Node node){
        if(root==null){
            root=node;
        }else{
            root. add(node);
        }
    }
    //中序遍历
    public void midSearch(){
        if(root!=null){
            root.midSearch();
        }else{
            System.out.println("二叉树为空树！");
        }
    }

}
/**
 * 创建结点
 * */
class Node{
    int value;
    Node left;
    Node right;
    /**构造器*/
    public Node(int value) {
        this.value = value;
    }
    /**返回当前结点的高度，以该结点为根结点的树的高度*/
    public int height(){
        return Math.max(left==null?0:left.height(),right==null? 0:right.height())+1;
    }
    /**返回左子树的高度*/
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }
    /**返回右子树的高度*/
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }
    /**左旋转方法*/
    private void leftRotate(){
        //创建新结点,以当前根节点的值
        Node newNode =new Node(value);
        //把新结点的左子树设置成当前结点的左子树
        newNode.left=left;
        //把新的结点右子树设置成为当前结点的右子树的左子树
        newNode.right=right.left;
        //把当前结点的值替换成右子节点的值
        value = right.value;
        //把当前结点的右子树设置成为当前结点右子树的右子树
        right=right.right;
        //把当前结点的左子树（左子结点）设置成为新的结点
        left=newNode;
    }
    /**右旋转*/
    public void rightRotate(){
        Node newNode=new Node(value);
        newNode.right=right;
        newNode.left=left.right;
        value=left.value;
        left=left.left;
        right=newNode;
    }
    /**查找要删除的结点*/
    public Node search(int value){
        if(value==this.value){
            //找到的就是该结点
            return this;
        }else if(value<this.value){
            //如果查找的值小于当前结点，向左子树递归查找
            //如果左子树为空
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else{
            //如果查找的值不小于当前节点，向右子树递归查找
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }
    /**
     * 查找待删除节点的父节点*/
    public Node searchParent(int value){
        //如果当前节点就是待删除结点的父节点
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else{
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if(value<this.value&&this.left!=null){
                return this.left.searchParent(value);
                //向左子树递归查找
            }else if(value>=this.value&&this.right!=null){
                return this.right.searchParent(value);
                //向右子树递归
            }else{
                return null;
                //没有父结点
            }
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    //添加元素
    public void add(Node node){
        if(node==null){
            return;
        }
        if(node.value<this.value){
            if(this.left==null){
                //左子树为空
                this.left=node;
            }else{
                //向左子树进行递归
                this.left.add(node);
            }
        }else{
            if(this.right==null){
                //右子树为空
                this.right=node;
            }else{
                //向右子树进行递归
                this.right.add(node);
            }
        }
        //当添加一个结点后，如果（右子树的高度-左子树的高度）>1 ,左旋转
        if(rightHeight()-leftHeight()>1){
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right!=null&&right.rightHeight()>right.rightHeight()){
                //先对当前结点的右结点（右子树）->右旋转
                right.rightRotate();
                //在对当前结点进行左旋转
                leftRotate();
            }else {
                leftRotate();//左旋转
            }
            return;
        }
        //当添加完一个结点后，如果（左子树的高度-右子树的高度）>1,右旋转
        if(leftHeight()-rightHeight()>1){
            if(left!=null&&left.rightHeight()>left.leftHeight()) {
             //先对当前结点的左结点（左子树）->左旋转
                left.leftRotate();
                //在对当前结点进行右旋转
                rightRotate();//右旋转
            }else{
                //直接进行右旋转
                rightRotate();
            }
        }
    }
    //中序遍历
    public void midSearch(){
        if(this.left!=null){
            this.left.midSearch();
        }
        System.out.print(this+" ");
        if(this.right!=null){
            this.right.midSearch();
        }
    }
}