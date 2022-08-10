package sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/2日17:21
 */
public class InsertSort {
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
        System.out.println("插入排序起始时间为："+datelStr1);
        //冒泡排序
        insertSort(arr);
        Date date2=new Date();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datelStr2=simpleDateFormat1.format(date2);
        System.out.println("插入排序排序终止时间为："+datelStr2);


    }
    public static void insertSort(int []arr){
        /*步骤一：从第二个元素开始寻找（通过循环的方式）
          注意：不要遗漏了最后一个元素（即下标为<arr.length-1>的元素）
        * */
        for (int i = 1; i <arr.length ; i++) {
            int insertVal=arr[i];
            int insertIndex=i-1;
            /*
               步骤二：为insertVal找到合适的插入位置
                      insertIndex>=0:为了保证插入位置不越界
                      insertVal<arr[insertIndex]:表示待插入的数，还未找到合适的插入位置
             * */
            while(insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            /*当退出while循环后，说明插入的位置找到，insertIndex+1
             * 说明insertIndex位置的元素已经小于待插入的元素
             * */
            if((insertIndex+1)!=i){
                arr[insertIndex+1]=insertVal;
            }
        }
    }
}
