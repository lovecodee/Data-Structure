package binarytree;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/3日23:39
 * 实现：（1）二叉树的前序、中序、后序遍历（递归实现）
 *      （2）二叉树的前序、中序、后序查找（递归实现）
 *      （3）二叉树节点的增加、删除
 *            规定  ：①如果删除的节点是叶子节点，则删除该节点
 *                   ②如果删除的节点不是叶子节点，则删除该子树
 *             思路
 *              （1）二叉树是单向的，在进行判断时，需要判断当前节点的子节点是否是需要删除的节点
 *                      （而不是判断当前节点是否待删除的）
 *              （2）如果左节点不为空并且是待删除的节点，就将this.left=null,然后就返回（递归结束）
 *              （3）如果右节点不为空并且是待删除的节点，就将this.right=null,然后就返回（递归结束）
 *              （4）如果第（2）（3）没有删除节点，需要向左子树递归进行删除
 *              （5）如果（4）没有删除节点，需要向右子树递归进行删除
 *              （6）如果树是空树root,如果只有一个root节点，则等价于将二叉树置空
 *      （4）实现中序线索二叉树
 */
public class BinaryTree {
    public static void main(String[] args) {
        //创建一棵二叉树
        ThreadBinaryTree threadBinaryTree=new ThreadBinaryTree();
        //创建需要的节点
        HerNode root1=new HerNode(1,"宋江");
        HerNode node2=new HerNode(3,"吴用");
        HerNode node3=new HerNode(6,"卢俊义");
        HerNode node4=new HerNode(8,"花荣");
        HerNode node5=new HerNode(10,"tom");
        HerNode node6=new HerNode(14,"公孙胜");
        root1.setLeft(node2);
        root1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试线索化
        //中序遍历结果为：{8,3,10,14,6}9
        threadBinaryTree.setRoot(root1);
        threadBinaryTree.threadedNodes();
        //测试：
        HerNode leftNode=node5.getLeft();
        System.out.println("10号节点的前驱节点是： "+leftNode);
        System.out.println("10号节点的后继节点是： "+node5.getRight());
        System.out.println("使用线索化的方式来遍历线索化二叉树：");
        threadBinaryTree.threadedList();
    }
}
/**定义二叉树*/
class ThreadBinaryTree{
    private HerNode root;
    /**为了实现线索化，需要创建一个指针，指向线索化前一个节点*/
    private HerNode pre=null;
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    public void setRoot(HerNode root) {
        this.root = root;
    }
    /**遍历线索化二叉树的一个方法*/
    public void threadedList(){
        HerNode node=root;
        while(node!=null){
            //循环找到leftType==1的节点，第一个节点就是8节点
            //后面随着遍历而变化，因为当leftType==1时候，说明该节点是按照线索化后的有效节点
            while(node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getLeftType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            node=node.getRight();
        }

    }



    /**编写对二叉树进行中序线索化的方法*/
    public void threadedNodes(HerNode node){
        if(node==null){
            return;
        }
        //（1）先线索化左子树
        threadedNodes(node.getLeft());
        //（2）线索化中间节点
        if(node.getLeft()==null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if(pre!=null&&pre.getRight()==null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setLeftType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre =node;

        //（3）最后线索化右子树
        threadedNodes(node.getRight());

    }


    //先序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    /**中序遍历*/
    public void midOrder(){
      if(this.root!=null){
          this.root.midOrder();
      }else{
          System.out.println("二叉树为空，无法遍历！");
      }
    }
    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HerNode preOrderSearch(int no){
        if(root!=null){
            return root.preOrdersearch(no);
        }else{
            return null;
        }
    }
    //中序查找
    public HerNode midOrderSearch(int no){
        if(root!=null){
            return root.midOrdersearch(no);
        }else{
            return null;
        }
    }
    //后序查找
    public HerNode postOrderSearch(int no){
        if(root!=null){
            return root.postOrdersearch(no);
        }else{
            return null;
        }
    }
    //删除节点
    public void delNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root=null;
            }else{
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("此树为空树，不能删除！！");
        }
    }

}
/**定义树节点*/
class HerNode{
    private int no;
    private String name;
    private HerNode left;
    private HerNode right;
    //说明：如果leftType==0表示的是左子树，如果是1表示指向前驱节点
    //     如果rightType==0表示的是右子树，如果是1表示指向的是后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HerNode(int no, String name){
        this.no=no;
        this.name=name;
    }
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }

    public HerNode getLeft() {
        return left;
    }

    public void setLeft(HerNode left) {
        this.left = left;
    }

    public HerNode getRight() {
        return right;
    }

    public void setRight(HerNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HerNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    /**先序遍历*/
    public void preOrder(){
        System.out.println("前序遍历~~");
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void midOrder(){
        if(this.left!=null){
            this.left.midOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.midOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
    }
    //前序查找
    public HerNode preOrdersearch(int no){
        if(this.no==no){
            return this;
        }
        HerNode cur=null;
        if(this.left!=null){
            cur=this.left.preOrdersearch(no);
        }
        if(cur!=null){
            return cur;
        }
        if(this.right!=null){
            cur=this.right.preOrdersearch(no);
        }
        return cur;
    }
    //中序查找
    public HerNode midOrdersearch(int no){
        HerNode cur=null;
        if(this.left!=null){
            cur=this.left.midOrdersearch(no);
        }
        if(cur!=null){
            return cur;
        }
        if(this.no==no){
            return this;
        }
        if(this.right!=null){
            cur=this.right.midOrdersearch(no);
        }
        return cur;
    }
    //后序遍历
    public HerNode postOrdersearch(int no) {
        HerNode cur = null;
        if (this.left != null) {
            cur = this.left.postOrdersearch(no);
        }
        if (cur != null) {
            return cur;
        }
        if (this.right != null) {
            cur = this.right.postOrdersearch(no);
        }
        if (cur != null) {
            return cur;
        }
        if (this.no == no) {
            return this;
        }
        return cur;
    }
    public void delNode(int no){
        if(this.left!=null && this.left.no==no){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }
}