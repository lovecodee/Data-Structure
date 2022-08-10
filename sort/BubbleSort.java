package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/2日11:15
 *         冒泡排序的实现及优化
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] =new int [8000000];
        /*通过数学库中的函数来对数组arr进行初始化*/
        for (int i = 0; i <8000000 ; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        /*生成随机数来进行检验*/
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr1=simpleDateFormat1.format(date1);
        System.out.println("冒泡排序起始时间为："+datelStr1);
        //冒泡排序
        bubbleSort(arr);
        Date date2=new Date();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2=simpleDateFormat1.format(date2);
        System.out.println("冒泡排序排序终止时间为："+datelStr2);


    }
    /**
     * 排序方法一：冒泡排序
     * 变量功能说明：
     *       temp:作为交换相邻两个数组元素的一个中间变量
     *       flag:实现冒泡排序的优化，如果在经历一趟后，没有进行交换，表示已经排好序，无需再进行后几趟
     *           当flag=true ,表示在该趟遍历中存在交换过程
     *            flag=false,表示在该趟遍历中不存在交换过程
     * 通过两层循环来实现对数组的排序：
     *       ①外层循环来进行控制比较趟数
     *       ②内层循环来进行控制每趟中元素比较的的次数
     *
     * Arrays.toString(arr):可以将数组以列表的形式输出
     * */

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        //标志位flag,来表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag=true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

}


