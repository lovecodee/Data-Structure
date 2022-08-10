package seek;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/3日12:21
 */
public class BinarySeek {
    public static void main(String[] args) {
        int arr[]={12,23,45,4,2,78,78,78,90};
        /*int to=binarySeek(arr,0,arr.length-1,78);
        System.out.println("to="+to);*/
        List resIndexList=binarySeek2(arr,0,arr.length-1,78);
        System.out.println("resIndexlist"+resIndexList);
        System.out.println("你好" );
    }
    /**通过递归的方式来实现二分查找
     *    arr:表示待查找数组
     *    left:表示查找的左边界下标
     *    right:表示查找的右边界下标
     *    key:表示的是待查找的关键字
     * 前提：数组arr必须是已经有序的数组
     * */
    /**查找数组中第一个与关键字相同的元素*/
    public static int binarySeek(int[]arr,int left,int right,int key){
        if(left>right){
            return -1;
        }
        int mid=(left+right)/2;
        if(arr[mid]>key){
            return binarySeek(arr,left,mid-1,key);
        }else if(arr[mid]<key){
            return binarySeek(arr,mid+1,right,key);
        }else{
            return mid;
        }
    }
    /**查找数组中与关键字相同的所有元素，并通过数组来记录对应的下标*/
    public static ArrayList<Integer> binarySeek2(int[]arr, int left, int right, int key){
        if(left>right){
            return new ArrayList<Integer>();
        }
        int mid=(left+right)/2;
        if(arr[mid]>key){
            return binarySeek2(arr,left,mid-1,key);
        }else if(arr[mid]<key){
            return binarySeek2(arr,mid+1,right,key);
        }else{
            ArrayList<Integer>resIndexlist=new ArrayList<Integer>();
            //向左查找
            int tem=mid-1;
            while(true){
                if(tem<0||arr[tem]!=key){
                    break;
                }
                resIndexlist.add(tem);
                tem-=1;
            }
            //将第一个查找的添加进去（只添加一次）
            resIndexlist.add(mid);
            //向右查找
            tem=mid+1;
            while(true){
                if(tem>arr.length-1||arr[tem]!=key){
                    break;
                }
                resIndexlist.add(tem);
                tem+=1;
            }
            return  resIndexlist;
        }
    }
}
