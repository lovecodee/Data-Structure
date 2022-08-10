package recursion;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/1日23:04
 */
public class Queen {
    /**定义一个max表示有几个皇后*/
    int max=8;
    static int count=0;
    /**定义数组array,保存皇后放置的位置，比如arr={0,4,7,5,2,6,1,3}*/
    int[]array=new int[max];
    public static void main(String[] args) {
        Queen queue8=new Queen();
        queue8.check(0);
        System.out.println("总共有解法: "+count+"种");
    }
    /**方法：放置第n个皇后*/
    private void check(int n){
       if(n==max){
           print();
           return;
       }
       //依次放入皇后n，并判断是否冲突
        for (int i = 0; i <max ; i++) {
            //将第n个皇后放到该行的第一列
            array[n]=i;
            //判断当放置第n个皇后位置是否冲突
            if(judge(n)){
                //如果位置不冲突，就开始放第（n+1）个皇后
                check(n+1);
            }
            //如果冲突，就将第n个皇后放到该行的下一个位置，继续判断
        }
    }

    /**当放置第n个皇后时，就去检查该皇后是否和前面已经摆放好的皇后是否冲突
     * 注意：check是每一次递归时，进入到check中都会有 for(int i=0;i<max;i++) ,因此会有回溯
     * */
    private boolean judge(int n){
        //n表示第n个皇后
        for (int i = 0; i <n ; i++) {
            /**
             * 说明：
             * （1） array[i]==array[n] :表示判断两个皇后是否在同一列
             * （2） Math.abs(n-i)==Math.abs(array[n]-array[i])):表示判断两个皇后是否在同一斜线(仔细思考)
             * （3） 无需判断是否在同一行（n一直在递增）
             * */
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
    /**写一个方法，可以将皇后的摆放位置输出*/
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}

