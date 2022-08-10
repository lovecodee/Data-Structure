package seek;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/3日14:29
 * 插值查找：对关键字数量大并且分布比较均匀的查找表而言，其是比较快的
 */
public class InserValueSeek {
    public static void main(String[] args) {
        int arr[]=new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i]=i+1;
        }
        int value=insertValueinsert(arr,0,arr.length-1,56);
        System.out.println("value="+value);

    }

    public static int insertValueinsert(int []arr,int left,int right,int key){
        /*
        *  注意： key<arr[0]  、 key>arr[arr.length-1]这两个判断必须需要，不然mid有可能会产生越界
        * */
        //条件判断
        if(left>right||key<arr[0]||key>arr[arr.length-1]){
            return -1;
        }
        //找标杆
        int mid=left+(right-left)*(key-arr[left])/(arr[right]-arr[left]);
        int midVal=arr[mid];
        if(key>midVal){
            //向右边递归
            return insertValueinsert(arr,mid+1,right,key);
        }else if(key<arr[mid]){
            //向右边递归
            return insertValueinsert(arr,left,mid-1,key);
        }else{
            return mid;
        }


    }
}
