package binarytree;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/4日13:24
 * 顺序存储二叉树遍历：
 */
public class ArrayBinaryDemo {
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree=new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder1();
    }
}
class ArrayBinaryTree{
    private int[]arr;
    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }
    public void preOrder1(){
        preOrder1(0);
    }
    //存储数据
    /**编写一个方法，完成顺序存储二叉树的前序遍历*/
    public void preOrder1(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.print(arr[index]+"  ");
        if((2*index+1)<arr.length) {
            preOrder1(2 * index + 1);
        }
        if((2*index+2)<arr.length){
            preOrder1(2*index+2);
        }
    }
}