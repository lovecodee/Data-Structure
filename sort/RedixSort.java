package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/3日1:15
 * 基数排序：
 *      算法思想：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序
 *              这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 */
public class RedixSort {
    public static void main(String[] args){
        int arr[] = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        /*生成随机数来进行检验*/
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr1 = simpleDateFormat1.format(date1);
        System.out.println("基数排序起始时间为：" + datelStr1);
        //冒泡排序
        /* shellSort1(arr);*/
        radixSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2 = simpleDateFormat1.format(date2);
        System.out.println("基数排序终止时间为：" + datelStr2);





    }
    public static void radixSort(int []arr){
        int max=arr[0];
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        //获取当前数字的最大位数
        int maxLength=(max+"").length();
        int[][] bucket=new int[10][arr.length];
        int[] bucketElementCounts=new int[10];
        for (int i = 0,n=1; i <maxLength ; i++,n*=10) {
            for (int j = 0; j <arr.length ; j++) {
                int digieOFElement=arr[j]/n%10;
                bucket[digieOFElement][bucketElementCounts[digieOFElement]]=arr[j];
                bucketElementCounts[digieOFElement]++;
            }
            int index=0;
            for (int k = 0; k <bucketElementCounts.length ; k++) {
                if(bucketElementCounts[k]!=0){
                    for (int j = 0; j <bucketElementCounts[k] ; j++) {
                        arr[index++]=bucket[k][j];
                    }
                }
                bucketElementCounts[k]=0;
            }







        }

    }
}
