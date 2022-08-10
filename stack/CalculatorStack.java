package stack;

import javax.xml.transform.stream.StreamSource;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/1日0:59
 * 通过栈来实现简单的运算器（可以进行加减乘除等基本运算）
 */
public class CalculatorStack {
    public static void main(String[] args) {
        String expression ="3+4*8-20";
        ArrayStack2 numStack=new ArrayStack2(10);
        //数栈，用来存储运算数
        ArrayStack2 operStack=new ArrayStack2(10);
        //符栈，用来存储运算符
        int index=0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';
        String KeepNum="";
        //将每次扫描得到的字符保存到ch
        //开始while循环的扫描expression
        while(true){
            //依次得到expression的每一个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后再进行相应操作
            if(operStack.isOper(ch)){
                //ch是运算符，判断当前栈是否为空
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }else{
                    //符号栈不为空
                    /*比较待插入栈中运算符的优先级<①>和符栈顶的运算符之间的优先级关系<②>
                    * （1）如果①的优先级小于等于②
                    *            从数栈中弹出两个运算数，从符号栈中弹出栈顶运算符②，然后进行运算
                    *            将运算结果压入数栈，将符号①压入符栈
                    * （2）如果①的优先级大于②
                    *            将符号①压入符号栈
                    * */
                    if(operStack.priority(ch)<= operStack.priority(operStack.getHead())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=operStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                        //将计算结果和符号分别压入数栈和符号栈
                    }else{
                        operStack.push(ch);
                    }
                }
            }else{
               /* numStack.push(ch-48);  ---->  没有考虑到多位数   */
                /**
                 * 多位数分析思路：
                 * （1）当处理多位数时，不能发现是一位数字就入栈
                 * （2）如果扫描的第一位是数字，再向后扫描一位，若是符号，扫描的第一位就入数字栈
                 * （3）如果第二位也是数字，使用变量字符串进行拼接，依次如此
                 * */
                //处理多位数
                 KeepNum += ch;
                 /*判断ch是不是最后一个字符*/
                 if(index==expression.length()-1){
                     numStack.push(Integer.parseInt(KeepNum));
                 }else{
                        //判断下一个字符是不是数字
                     if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                            numStack.push(Integer.parseInt(KeepNum));
                            KeepNum="";
                            //注意：一定要将KeepNum置空，否则会出现字符串越界问题
                    }
                 }
            }
            //让 index+1，然后判断是否扫描到expression最后
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        //表达式扫描完毕，开始从两个栈中开始取符号和数字进行运算
        while(true){
            //符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if(operStack.isEmpty()){
                break;
            }else{
                num1=numStack.pop();
                num2=numStack.pop();
                oper=operStack.pop();
                res=operStack.cal(num1,num2,oper);
                numStack.push(res);
            }
        }
        int last=numStack.pop();
        System.out.printf("表达式%s =%d",expression,last);
    }
}
/**创建一个栈*/
class ArrayStack2{
    private int maxSize;
    private int[]stack;
    private int top=-1;
    /**构造器*/
    public ArrayStack2(int maxSize){
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
    /**(6)查看栈顶元素*/
    public int getHead(){
        return stack[top];
    }
    /**返回运算符的优先级，优先级使用数字表示，数字越大，其优先级就越高*/
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1;
        }
    }
    /**判断是不是运算符*/
    public boolean isOper(char val){
        return val=='*'||val=='/'||val=='+'||val=='-';
    }
    /**计算方法*/
    public int cal(int num1,int nums2,int oper){
       int res=0;
       //res用于存储计算的结果
        switch(oper){
            case'+': res=num1+nums2 ;break;
            case'-': res=nums2-num1 ;break;
            case'*':res=num1*nums2;break;
            case'/':res=nums2/num1;break;
            default:break;
        }
        return res;
    }
}
