package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/2日23:41
 * 归并算法： 采用了经典的分治策略（分治法将问题分成了一些个小问题然后递归求解），采用“分策略”先将数组分成
 *         最小（即单个元素），然后通过“治策略”逐渐合并，并在合并中进行排序，先使分块有序，最后整体有序
 */
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = new int[80000000];
        int temp[]=new int [arr.length];
        /*通过数学库中的函数来对数组arr进行初始化*/
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        //生成随机数来进行检验
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr1 = simpleDateFormat1.format(date1);
        System.out.println("归并排序起始时间为：" + datelStr1);
        //冒泡排序
        /* shellSort1(arr);*/
        mergeSort(arr,0,arr.length-1,temp);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2 = simpleDateFormat1.format(date2);
        System.out.println("归并排序终止时间为：" + datelStr2);
    }
    /**拆分的方法*/
    public static void mergeSort(int []arr,int left,int right,int[]temp){
        if(left<right){
            int mid=(left+right)/2;
            //拆分左边
            mergeSort(arr,left,mid,temp);
            //拆分右边
            mergeSort(arr,mid+1,right,temp);
            //等拆分到最小后开始合并
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /**
     *  arr:排序的原始数组
     *  left:左边有序序列的初始索引
     *  mid:中间索引
     *  right:右边索引
     *  temp:中转的数组
     * */
    public static void merge(int[]arr,int left,int mid,int right,int[]temp){
        int i=left;
        int j=mid+1;
        int t=0;
        //（一）将分组合并填充到数组temp中
        while(i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t++]=arr[i++];
            }else{
                temp[t++]=arr[j++];
            }
        }
        //（二）将分组剩余数据填入到数组temp中
        while(i<=mid){
            temp[t++]=arr[i++];
        }
        while(j<=right){
            temp[t++]=arr[j++];
        }
        //（三）复制拷贝到元素组
        t=0;
        int templeft=left;
        while(templeft<=right){
            arr[templeft++]=temp[t++];
        }
    }
}
