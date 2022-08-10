package seek;

import java.util.Arrays;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/3日15:05
 * 斐波那契查找法
 */
public class FibonacciSeek {
    public static int maxSize=100;
    public static void main(String[] args) {
        int f[];
        f=fib();
        int arr[]={12,34,45,6,778,89};
        int location= fibonacciSeek(arr,6);
        System.out.println("location:  "+location);
    }
    public static int fibonacciSeek(int []a,int key){
        int low=0;
        int high=a.length-1;
        int k=0;
        int mid=0;
        int f[]=fib();
        //使用该数组来进行存储斐波那契数列
        while(high>f[k]-1){
            k++;
        }
        //复制，根据斐波那契的大小来进行对元素组进行扩充，如果扩充，扩充不分使用原数组的最后一个元素来进行填充
        int []tem= Arrays.copyOf(a,f[k]);
        for (int i = high+1; i <tem.length ; i++) {
            tem[i]=a[high];
        }
        while(low<=high){
            mid= low+f[k-1]+1;

            //向数组左边查找，改变其右边界（k-1）  ---->可以使用递归来进行理解
            if(key<tem[mid]){
                high=mid-1;
                k--;
            }else if(key>tem[mid]){
                //向数组右边查找，改变其左边界(k-2)
                low=mid+1;
                k-=2;
            }else{
                if(mid<=high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return 0;
    }
    /**构建斐波那契数列*/
    public static int[] fib(){
        int[]f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i = 2; i <maxSize ; i++) {
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }
}
