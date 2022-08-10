package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/4日16:38
 * 堆排序
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] =new int [80000000];
        /*通过数学库中的函数来对数组arr进行初始化*/
        for (int i = 0; i <80000000 ; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        /*生成随机数来进行检验*/
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr1=simpleDateFormat1.format(date1);
        System.out.println("插入排序起始时间为："+datelStr1);
        //冒泡排序
        heapSort(arr);
        Date date2=new Date();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2=simpleDateFormat1.format(date2);
        System.out.println("插入排序排序终止时间为："+datelStr2);
    }
    /**编写一个堆排序的方法*/
    public static void heapSort(int[]arr){
        int temp=0;
        //i指向的是从左到右、从下到上的一个非叶子节点
        for (int i = (arr.length/2)-1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        for (int j = arr.length-1; j >0 ; j--) {
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }

    }
    /**讲一个数组（二叉树）调整成一个大顶堆
     *  功能：完成以 “ i ”对应的非叶子节点的树调整成大顶堆
     *   arr:表示的是待调整的数组
     *   i:表示的是非叶子节点在数组中的索引
     *   length:表示对多个元素继续调整，length是在逐渐的减少
     * */
    public static void adjustHeap(int[]arr,int i,int length){
        //保存数组元素到一个临时变量
        int temp=arr[i];
        //k表示的是i结点的左子节点
        for(int k=i*2+1;k<length;k=2*i+1){
            //左子节点的值小于右子节点的值
            if((k+1)<length&&arr[k]<arr[k+1]){
                k++;
            }
            //如果子节点的较大值大于父节点，就进行交换
            if(arr[k]>temp){
                arr[i]=arr[k];
                i=k;
            }else{
                break;
            }
        }
        //将左右子树中的较大值放在父节点的位置
        arr[i]=temp;
    }
}
