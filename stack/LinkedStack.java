package stack;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @author Jin
 * @ Date 2022年06月2022/6/30日20:13
 * 目标：完成链式存储结构的添加、删除、取栈顶、遍历、退出程序等基本功能
 */
public class LinkedStack {
    public static void main(String[] args) {
        LinkStack stack1=new LinkStack();
        String key="";
        boolean loop=true;
        //控制是否退出菜单
        Scanner scanner=new Scanner(System.in);
        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈（入栈）");
            System.out.println("pop:表示从栈中取出数据（出栈）");
            System.out.println("head:表示获取栈顶元素的值");
            System.out.println("请输入你的选择：");
            key=scanner.next();
            switch(key){
                case"show":
                    stack1.showStack();
                    break;
                case"push":
                    System.out.println("请输入一个数：");
                    int value =scanner.nextInt();
                    stack1.addNode(value);
                    break;
                case"pop":
                    try{
                        int res =stack1.popNode();
                        System.out.printf("出栈的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        //打印出异常信息
                    }
                    break;
                case"exit":
                    scanner.close();
                    loop=false;
                    //退出循环
                    break;
                case"head":
                    int t=stack1.getHead();
                    System.out.printf("栈顶元素的值为%d\n",t);
                    break;
                default:break;
            }
    }
}
}
class LinkStack{
   private   Stack1 head=new Stack1(0);
   public Stack1 S=head;
   public LinkStack(){
   }
    /**(1)入栈*/
    public void addNode(int key){
        Stack1 t1=new Stack1(key);
        t1.data=key;
        t1.next=S;
        S=t1;
    }
    /**(2)出栈
     * @return*/
    public int popNode(){
            if(S.next==null){
                throw new RuntimeException("栈已空，无法删除");
            }else{
                int key=S.data;
                S=S.next;
                //栈顶指针下移
                return key;
            }

   }

    /**(3)遍历栈*/
    public void showStack(){
        Stack1 cur=S;
        if(cur.next==null){
            System.out.println("栈已经空，无法取出元素");
        }
        while(cur.next!=null){
            System.out.printf("栈中的元素为：%d\n",cur.data);
            cur=cur.next;
        }
    }
    /**(4)获取栈顶元素*/
    public int getHead(){
       if(S.next==null){
           System.out.println("栈已空，不存在栈顶元素！！");
       }else{
           return S.data;
       }
        return 0;
    }
}
class Stack1{
    public int data;
    /**存储数据*/
    public Stack1 next;
    /**存储下一个节点的地址*/
    /**构造器*/
    public Stack1(int data){
        this.data=data;
        this.next=null;
    }
    @Override
    /**重写toString()方法*/
    public String toString() {
        return "Stack1{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}