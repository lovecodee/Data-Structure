package arrayqueue;

import java.util.Scanner;

/**
 * @author Jin
 * @ Date 2022年06月2022/6/29日0:08
 * 通过数组来实现队列
 */
public class ArrayQueueRealize {
    public static void main(String[] args) {
        //创建一个对象
        ArrayQueue t1=new ArrayQueue(3);
        char key=' ';
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        while(loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("请输入具体操作：");
            key =scanner.next().charAt(0);
            //接收一个字符
            switch(key){
                case's':t1.show();break;
                case'a':
                    System.out.println("输出一个数");
                    int value =scanner.nextInt();
                    t1.addQueue(value);break;
                case'g':try{
                    int res=t1.getQueue();
                    System.out.printf("取出的数据是%d\n ",res);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
                case'h':
                    try{
                        int res=t1.headQueue();
                        System.out.printf("队列头的数据是%d",res);

                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':scanner.close();
                       loop=false;
                     break;
                default:break;
            }
        }
        System.out.println("程序退出！！！");
    }
}
/**使用数组模拟队列---编写一个ArrayQueue类*/
class ArrayQueue{
    public int maxSize;
    private int front;
    /**队列头*/
    private int rear;
    /**队列尾*/
    private int[]arr;
    /*该数组用于存放数据，模拟队列*/
    /**创建队列的构造器（数据进行初始化）*/
    public ArrayQueue(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
        /*表示数组的最大容量*/
        front = -1;
        //指向队列头部，分析出front是指向队列头的前一个位置
        rear  = -1;
        //指向队列尾，指向队列尾的数据（即就是队列的最后一个数据）
    }
    /**（1）判断队列是否满*/
    public boolean isFull(){
            return rear==maxSize-1;
    }
    /**（2）判断队列是否为空*/
    public boolean isEmpty(){
        return rear==front;
    }
    /**（3）添加数据到队列*/
    public  void addQueue(int n){
        //判断队列是否已经满
        if(isFull()){
            System.out.println("队列满，不能加入数据！！");
        }
        rear++;//rear后移
        arr[rear]=n;
    }
    /**（4）获取队列的数据(出队列)*/
    public int getQueue() {
        //判断队列是否是空的
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
            //通过抛出异常
        }else{
            front++;//front后移
            int middle=arr[front];
            arr[front]=0;
            //取出后空间值置为0
            return middle;

        }

    }
    /**（5）显示队列的数据*/
    public  void show(){
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d  ",i,arr[i]);
        }
    }
    /**（6）显示队列的头数据（并不取数据）*/
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front+1];
    }
}