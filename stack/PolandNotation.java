package stack;

import java.util.*;
import java.util.Stack;
/**
 * @author Jin
 * @ Date 2022年07月2022/7/1日13:26
 * 功能：
 *   （1）将中缀表达式转化为后缀表达式
 *   （2）求解后缀表达式
 */
public class PolandNotation {
    public static void main(String[] args) {
        /*完成一个将中缀表达式转化为后缀表达式的功能
        * 具体实现步骤如下：
        *   （1）  1+（（2+3）*4）-5   ->  1 2 3 + 4 * + 5 -
        *   （2） 为了便于操作，将中缀表达式字符串存储在对应的list中（通过toInfixExpression()方法）
        *           即[1,+,(,(,2,+,3,),*,4,),-,5]
        *   （3） 将中缀表达式对应的list =>后缀表达式
        *   （4） 通过后缀表达式来进行运算出结果
        * */
        //①中缀表达式
        String expression ="1+((2+3)*4)-5";
        //②将中缀表达式转化为列表
        List<String> infixExpressionList=toInfixExpression(expression);
        System.out.print("中缀表达式为：");
        System.out.println(infixExpressionList);
        //③将中缀表达式转化为后缀表达式
        List<String> suffxiExpression=parsesuffiExpresionList(infixExpressionList);
        System.out.println("后缀表达式为："+suffxiExpression);
        //④求解后缀表达式
        int rbs=calculate(suffxiExpression);
        //⑤输出结果
        System.out.println("计算的结果是= "+rbs);

    }
    /**方法：将中缀表达式转化为对应的list*/
        public static List<String> toInfixExpression(String s){
            //①定义一个List,存放中缀表达式对应的内容
            List<String> ls=new ArrayList<String>();
            int i=0;
            //指针，用来遍历中缀字符串中的字符
            String str;
            //完成对多位数的拼接
            char c;
            //将遍历到的每个字符放到  c 中
            do{
                //② c是一个字符，需要加入ls
                if((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                    ls.add(""+c);
                    i++;
                }else{
                    /*③ 如果是一个数，还要考虑多位数*/
                    str="";
                    while(i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                        str+=c;
                        //拼接
                        i++;
                    }
                    ls.add(str);
                }

            }while(i<s.length());
            return ls;
        }
        /** 方法：将中缀表达式得到的list转化为 后缀表达式对应的List */
       public static List<String> parsesuffiExpresionList(List<String> ls){
           /*①定义栈
           *   Stack<String> s2=new Stack<String>();
           * 说明：s2在装转化的过程中没有pop()操作，而且到最后还要逆序输出
           * 改进：可以使用列表来代替 栈 s2
           * */
           Stack<String> s1=new Stack<String>();
           List<String>  s2=new ArrayList<String>() ;
           //遍历ls
           for(String item:ls){
               if(item.matches("\\d+")){
                   s2.add(item);
               }else if(item.equals("(")){
                   s1.push(item);
               }else if(item.equals(")")){
                   /*处理办法：依次弹出栈s1中的元素，并压入s2中，直到遇到左括号为止*/
                   while(!(s1.peek().equals("("))){
                       s2.add(s1.pop());
                       //s1中弹出并压入栈s2
                   }
                   s1.pop();
                   //将“( ”弹出
               }else{
                   /*
                   *符号优先级的比较：
                   * 规则如下：当item的优先级小于栈s1栈顶运算符的优先级时，将s1中的栈顶运算符弹出加入到s2
                   * */
                   while(s1.size()!=0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                       s2.add(s1.pop());
                   }
                   //将item压入栈中
                   s1.push(item);
               }
           }
           //将s1中剩余内容依次加入到s2中
           while(s1.size()!=0){
               s2.add(s1.pop());
           }
           return s2;
       }

    /** 方法一：将一个逆波兰表达式依次将数据和运算符放入到ArrayList中   */
    public static List<String> getListString(String suffixException) {
        //将 suffixException 分割(依据“ ”)
        String[] split=suffixException.split(" ");
        List<String>list=new ArrayList<String>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }
    /**  方法二：完成对逆波兰表达式的运算
     * 步骤如下：
     *      ①从左至右扫描，将3 和 4 压入堆栈
     *      ②遇到 + 运算符，弹出 4 和 3 ，计算 3+4，结果为 7 存入栈中
     *      ③将5入栈
     *      ④下一个是 * 运算符，弹出 5 和 7，计算 5*7，将结果 35 存入栈中
     *      ⑤将6入栈
     *      ⑥最后一个是 - 运算符，弹出 6 和 35 ，计算 35-6，将结果29 存入栈中
     * */
    public static int calculate(List<String> ls) {
        //(1)创建一个栈
        Stack<String> stack = new Stack<String>();
        //(2)遍历 ls
        for (String item : ls) {
            //(3)使用正则表达式来取出多位
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                //(4)为运算符，pop出两个数，并进行运算，然后压入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //将最终的运算结果压入栈中，然后将栈顶元素弹出并转化整型
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
/**编写一个类Operation 可以返回一个运算符对应的优先级*/
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;
    /**写一个方法，返回对应的优先级数字*/
    public static int getValue(String operation){
        int result=0;
        switch(operation){
            case"+":
                result=ADD;
                break;
            case"-":
                result=SUB;
                break;
            case"*":
                result=MUL;
                break;
            case"/":
                result=DIV;
                break;
            default:
               result=0;
                break;
        }
        return result;
    }
}