package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/2日19:24
 * 目标：通过代码实现希尔排序算法（直接插入算法的优化）
 * 介绍：采用分组插入的方式，先将整个待排序的记录分割成几组，减少参与直接插入排序的数据量
 *       对每个分组分别进行直接插入排序，然后增加每组的数据量，重新分组。经过几次分组排序后，
 *       记录变得“基本有序”，然后再通过一次直接插入排序
 * 步骤：
 *     （1）设置两层循环
 *               第一层循环，来设置分组的次数和每次分组后的小分组的元素个数（gap）
 *               第二层循环：将每个小分组中的元素进行比较，通过“移位”操作来完成交换（根据条件）
 */
public class ShellSort {
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
        System.out.println("希尔排序起始时间为："+datelStr1);
        //冒泡排序
       /* shellSort1(arr);*/
        shellSort2(arr);
        Date date2=new Date();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2=simpleDateFormat1.format(date2);
        System.out.println("希尔排序终止时间为："+datelStr2);
    }
    /**方式一：交换式（比较缓慢）*/
    public static void shellSort1(int []arr){
        int temp=0;
        for (int gap =arr.length/2 ; gap >0 ; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i-gap; j>=0 ; j-=gap) {
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
        }
    }
    /**方法二：移位法*/
    public static void shellSort2(int []arr){
        for (int gap =arr.length/2 ; gap >0 ; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                int j=i;
                int tem=arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0&&tem<arr[j-gap]){
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=tem;
                }

                }
            }
        }
}

