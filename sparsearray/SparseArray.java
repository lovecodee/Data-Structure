package sparsearray;

/**
 * @author Jin
 * @ Date 2022年06月2022/6/28日10:48
 */
public class SparseArray {
    public static void main(String[] args) {
        //（1）创建一个原始的二维数组11*11      0：表示没有棋子   1：表示黑子   2：表示蓝子
        int[][] chessArr1 =new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        //（2）输出原始的二维数组
        System.out.println("原始的二维数组：" );
        for(int[]row:chessArr1){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //（3）将  二维数组 转  稀疏数组
        //①遍历数组，得到非零元素的总个数
        int sum=0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        //②创建对应的稀疏数组
        int[][] sparseArr =new int[sum+1][3];
        //②给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //③遍历二维数组，给稀疏数组赋值
        int count=0;
        //用来记录有几个非零数据
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }

            }
        }
        //④输出稀疏数组的形式
        System.out.println("稀疏数组为：");
        for (int i = 0; i <sum+1 ; i++) {
            for (int j = 0; j <3 ; j++) {
                System.out.printf("%d\t",sparseArr[i][j]);
            }
            System.out.println();
        }
        //(4)将稀疏数组转变为原二维数组
        /**
         * 步骤如下：
         *    ①通过稀疏数组的第一行创建原数组
         *    ②再通过循环来进行遍历稀疏数组（从第二行开始）对数组进行赋值
         * */
        System.out.println("原始数组为：");
        int[][]arr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <sparseArr.length ; i++) {
                arr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%d\t",arr2[i][j]);
            }
            System.out.println();
        }
    }
}
