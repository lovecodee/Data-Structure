package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/2日15:06
 */
public class SelectSort {
    public static void main(String[] args) {
        int arr[] =new int [800000];
        /*通过数学库中的函数来对数组arr进行初始化*/
        for (int i = 0; i <800000 ; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        /*生成随机数来进行检验*/
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr1=simpleDateFormat1.format(date1);
        System.out.println("直接选择排序起始时间为："+datelStr1);
        //冒泡排序
        selectSort(arr);
        Date date2=new Date();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2=simpleDateFormat1.format(date2);
        System.out.println("直接选择排序终止时间为："+datelStr2);
    }
    /**
     * 排序方法：简单选择排序
     *     算法思想：将数组中的第一个数（arr[i]）先设定为最小值（min），然后从余下的元素中找最小值，如果找到
     *             就更新最小值（min）并记录其对应的下标当一轮循环结束后，如果最小值与原先设定值相等，则
     *             表示其就是该位置的最小值，反之，就将更新后的min与k进行互换，该位置元素已经确定
     *             然后循环上述步骤，直到最后一个元素
     *     说明：min：存储当前位置以后的最小值
     *          minIndex:存储最小值对应的下标
     * */
    public static void selectSort(int []arr){
        for (int i = 0; i <arr.length-1 ; i++) {
            //外层循环来进行遍历数组中每个位置
            int minIndex =i;
            int min =arr[i];
            //假设该位置为最小值（其前位置已经排好序）
            for (int j = i+1; j <arr.length-1 ; j++) {
                if(min>arr[j]){
                    min=arr[j];
                    minIndex=j;
                }
            }
            //通过索引值是否改变来进行判断是否需要进行交换
            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
        }
    }
}
