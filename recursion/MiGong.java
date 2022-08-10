package recursion;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/1日21:33
 */
public class MiGong {
    public static void main(String[] args) {
        //(1)创建一个二维数组
        int[][] map = new int[8][7];
        /*  使用1表示墙 上下左右全部置为 1  */
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 1; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板，1表示
        map[3][1] = 1;
        map[3][2] = 1;
        //输出初始地图
        System.out.println("原地图为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        //使用递归为小球找路
        setWay(map,1,1);
        System.out.println("查找后的地图为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归来给小球找路
     * 说明：
     * （1）map：表示地图
     * （2）i,j：表示从地图哪个位置开始出发(1,1)
     * （3）如果小球能够到达map[6][5],则说明通路找到
     * （4）约定：当map[i][j]为0表示没有走过
     *                      1表示墙
     *                      2表示通路可以走
     *                      3表示该点已经走过，但是走不通
     * （5）制定策略：下->右->上->左，如果该点走不同，再回溯
     */
    public static boolean setWay(int[][]map,int i,int j){
        //通路已经找到
      if(map[6][5]==2){
          return true;
      }else{
          //如果这个点还没走过，按照  下->右->上-> 左策略执行,执行过程如下
          if(map[i][j]==0){
              map[i][j]=2;
              //假定该点是可以走通的
              if(setWay(map,i+1,j)){
                  return true;
              }else if(setWay(map,i,j+1)){
                  return true;
              }else if(setWay(map,i-1,j)){
                  return true;
              }else if(setWay(map,i,j-1)){
                  return true;
              }else{
                  map[i][j]=3;
                  //经过验证走不通，然后将该点置为3
                  return false;
              }
          }else{//map[i][j]!=0   其值可能为 1 2 3
              return false;
          }
      }
    }
}

