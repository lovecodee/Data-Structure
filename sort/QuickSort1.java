package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/2日20:51
 * 快排算法：
 *   （1）算法思想：冒泡排序是通过交换数组两个相邻元素来实现的，快排算法是先通过比较和交换不相邻元素是的数组更加有序，
 *               然后在通过一次冒泡排序，实现了排序的功能
 *   （2）步骤如下：
 *           ①确定数组的上下边界（通过下标指向，通过下标的移动来查看数组值并完成比较和交换）
 *           ②然后确定一个上下边界的中间值作为判断的枢纽值
 *           ③通过循环将数组中所有小于枢纽值的元素全部放到其左边，大于枢纽值的元素放到其右边（通过下标的移动来实现）
 *           ④改变上下枢纽值，然后除去已经定好位的枢纽值，从其左右两边通过递归的方式来重复①②过程
 *           ⑤循环结束，数组就已经排好序
 */
public class QuickSort1 {
    public static void main(String[] args) {
        int arr[] = new int[80000000];
        /*通过数学库中的函数来对数组arr进行初始化*/
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        /*生成随机数来进行检验*/
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr1 = simpleDateFormat1.format(date1);
        System.out.println("希尔排序起始时间为：" + datelStr1);
        //冒泡排序
        /* shellSort1(arr);*/
        quickSort(arr,0,arr.length-1);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2 = simpleDateFormat1.format(date2);
        System.out.println("希尔排序终止时间为：" + datelStr2);

    }
    public static void quickSort(int[]arr,int left,int right){
        int l=left;
        int r=right;
        int temp=0;
        int middle=arr[(left+right)/2];
        while(l<r){
            while(arr[l]<middle){
                l+=1;
            }
            while(arr[r]>middle){
                r-=1;
            }
            if(l>=r){
                break;
            }
            //交换两个值
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            //如果从左边遍历时有元素等于枢纽值，就让右下标左移（左下标不动）
            if(arr[l]==middle){
                r-=1;
            }
            //如果从右边遍历时有元素等于枢纽值，就让左下标右移移（右下标不动）
            if(arr[r]==middle){
                l+=1;
            }
        }
        //如果相遇，就要错开
        if(l==r){
            l+=1;
            r-=1;
        }
        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l) {
            quickSort(arr, l, right);
        }
    }
}
