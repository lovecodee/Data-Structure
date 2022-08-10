package stack;

import java.util.Scanner;

/**
 * @author Jin
 * @ Date 2022年06月2022/6/30日19:26
 */
public class ArrayStack {
    public static void main(String[] args) {
        Stack<String> stack=new Stack<String>(4);
        String key="";
        boolean loop=true;
        //控制是否退出菜单
        Scanner scanner=new Scanner(System.in);
        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈（入栈）");
            System.out.println("pop:表示从栈中取出数据（出栈）");
            System.out.println("请输入你的选择：");
            key=scanner.next();
            switch(key){
                case"show":
                    stack.showStack();
                    break;
                case"push":
                    System.out.println("请输入一个数：");
                    int value =scanner.nextInt();
                    stack.push(value);
                    break;
                case"pop":
                    try{
                        int res =stack.pop();
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
                default:break;
            }
        }
    }
}
class Stack<S> {
    private int maxSize;
    private int[]stack;
    private int top=-1;
    /**构造器*/
    public Stack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }
    /**(1)判断栈是否满*/
    public boolean isFull(){
        return top==maxSize-1;
    }
    /**(2)判断栈空*/
    public boolean isEmpty(){
        return top==-1;
    }
    /**(3)入栈(将数据压入栈顶)*/
    public void push(int value){
        if(isFull()){
            System.out.println("栈已经满，无法进行添加新元素！！");
        }else{
            top++;
            stack[top]=value;
        }
    }
    /**(4)出栈(将栈顶的元素取出)*/
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据！");
        }else{
            int middle =stack[top];
            top--;
            return middle;
        }
    }
    /**(5)遍历栈中元素(从栈顶到栈底开始遍历)*/
    public void showStack(){
        if(isEmpty()){
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
