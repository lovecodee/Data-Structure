package linklist;

import java.util.Stack;

/**
 * @author Jin
 * @ Date 2022年06月2022/6/29日11:12
 */
public class SingleLinkList {
    public static  void main(String[] args) {
        //进行测试
        //（1）创建几个节点
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");
        HeroNode hero5=new HeroNode(5,"小明","闪电豹");
        HeroNode hero6=new HeroNode(6,"小明","闪");
        //创建要给链表
        //添加节点
        SingleLinkedListDemo singleLinkList=new SingleLinkedListDemo();
        singleLinkList.add(hero1);
        singleLinkList.add(hero2);
        singleLinkList.add(hero4);
        singleLinkList.add(hero3);
        singleLinkList.add(hero5);
        singleLinkList.add(hero6);
        hero6.next=hero4;
       // singleLinkList.showList();
       // singleLinkList.update(hero5);
      //  System.out.println("修改后的数据为：");
//        //显示
//        singleLinkList.showList();
//        //删除
//        singleLinkList.del(1);
//        System.out.println("删除后的情况为：");
//        //显示
//        singleLinkList.showList();
//        //获得节点个数
//        int total = singleLinkList.getLength(singleLinkList.getHead());
//        System.out.printf("节点的总个数为%d",total);
//        //找倒数第几个节点
//        singleLinkList.showList();
//        System.out.println("倒数第二个节点为：");
//        HeroNode res = singleLinkList.findLastIndexNode(singleLinkList.getHead(),2);
//        System.out.println(res);
//        //单链表的反转
//        System.out.println("单链表反转前的结果为：");
//        singleLinkList.showList();
//        singleLinkList.reverseList(singleLinkList.getHead());
//        System.out.println("单链表反转后的结果为：");
//        singleLinkList.showList();
//        //测试逆序打印单链表
//        singleLinkList.reverseList(singleLinkList.getHead());
//        System.out.println("逆序后的单链表为：(原链表并没有逆序)");
//        singleLinkList.showList();
        HeroNode tu=singleLinkList.judgeCircle();
        if(tu==null){
            System.out.println("单链表中存在环！");
        }else{
            System.out.println("相遇节点为： "+ tu);
        }
        int length= singleLinkList.calSingle(tu);
        System.out.println("环的长度为： "+length);
    }
}
/**定义SingleLinkedList来管理我们的英雄*/
class SingleLinkedListDemo{
    //先初始化一个头结点，头结点不要动
    private HeroNode head=new HeroNode(0,"","");
    /**添加节点到单向链表
     *思路：当不考虑编号的顺序时（1）找到当前链表的最后节点   （2）将最后的这个节点的next指向新的节点
     */
    public void add(HeroNode heroNode){//不考虑编号的顺序
        //通过temp(辅助节点)来找到链表的最后一个节点
        HeroNode temp=head;
        //遍历链表，找到最后
        while(true){
            if(temp.next==null){
                break;
            }else {
                temp = temp.next;
            }
        }
        //当退出循环时，temp就指向了链表的最后一个节点
        temp.next=heroNode;
        //将新结点加入到最后
    }
    public HeroNode getHead(){
        return head;
    }
    /**第二种方式来进行添加（考虑编号）*/
    public void addByOrder(HeroNode heroNode){
        /**
         * 头结点不能动，可以通过辅助指针（变量）找到添加的位置前一个节点
         * */
        HeroNode tem=head;
        boolean flag=false;
        //flag标志添加的编号已经存在，默认为false
        while(true) {
            if (tem.next == null) {
                //temp已经在链表的最后
                break;
            }
            if (tem.next.no > heroNode.no) {
                //说明temp已经在链表的最后
                break;
            } else if (tem.next.no == heroNode.no) {
                //说明希望添加的heroNode的编号已然存在
                flag = true;
                //说明编号存在
                break;
            }
            tem = tem.next;
            //后移,遍历当前链表
        }
        //判断flag的值
        if(flag){
            //不能添加，说明编号存在
            System.out.printf("准备插入英雄的编号为%d已经存在，不能加入！！",heroNode.no);
        }else{
            //新节点插入链表
            heroNode.next=tem.next;
            tem.next=heroNode;
        }
    }
    /**获得单链表的结点个数*/
    public static int getLength(HeroNode head){
        HeroNode tep=head;
        if(tep.next==null){
            return 0;
        }
        int length=0;
        while(true){
            if(tep.next!=null){
                length++;
                tep=tep.next;
            }else{
                break;
            }
        }
        return length;
    }
    //单链表的反转
    public static void reverseList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur=head.next;
        HeroNode next;
        HeroNode reversehead=new HeroNode(0,"","");
        while(cur!=null){
            next=cur.next;
            //头插法创建单链表
            cur.next=reversehead.next;
            reversehead.next=cur;
            cur=next;
        }
        head.next=reversehead.next;
    }
    //逆序输出单链表
    public static void reversePrint(HeroNode head){
        //单链表为空
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack=new Stack<HeroNode>();
        HeroNode cur=head.next;
        //cur用来遍历单链表的所有结点
        while(cur!=null){
            stack.push(cur);
            //结点入栈
            cur=cur.next;
        }
        //将栈中的结点进行遍历输出
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //修改结点的信息，根据no的编号来进行修改，即no编号不能变
   //删除结点
    public void del(int no){
        HeroNode pt=head;
        boolean flag=false;
        while(true){
            if(pt.next==null){
                break;
            }
            if(pt.next.no==no){
                //找到待删除结点的前一个结点
                flag=true;
                break;
            }
            pt=pt.next;
        }
        if(flag){
            pt.next=pt.next.next;
        }else{
            System.out.printf("要删除的%d节点不存在",no);
        }
    }
    /**遍历数组来进行执操作*/
    public void showList(){
        //第一步：判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能够移动，需要一个辅助变量来遍历
        HeroNode temd=head.next;
        while(true){
            //判断是否到链表最后
            if(temd==null){
                break;
            }
            //输出节点信息(调用toString()方法)
            System.out.println(temd);
            //将temp后移
            temd=temd.next;
        }
    }
    /**查找单链表中倒数第index个元素*/
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //如果链表为空，返回null
        if(head.next==null){
            return null;
        }
        int size=getLength(head);
        //索引位置不合理
        if(index<=0||index>size){
           return null;
        }
        //定义辅助变量,for循环定位到倒数的index
        HeroNode cur=head.next;
        //指向的是第二个节点（非头结点的第一个节点）
        for(int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;
    }

    /**修改节点数据*/
    public void update(HeroNode heroNode){
        HeroNode te=head.next;
        boolean flag=false;
        //如果找到目标节点，就设置为true
        while(true){
            if(te==null){
                break;
            }
            if(te.no== heroNode.no){
                flag=true;
                break;
            }
            te=te.next;
        }
        if(flag){
            te.name= heroNode.name;
        } else{
            System.out.printf("对应英雄的节点没有在原链表中找到！！");
        }
    }
    /**判断单链表是否有环，如果有环，返回第一次相遇的节点*/
    public HeroNode judgeCircle(){
        HeroNode cur1=head;
        HeroNode cur2=head;
        HeroNode target=null;
        boolean flag=false;
        while(true){
            if(cur1.next==null||cur1.next.next==null){
               return null;
            }else{
                cur1=cur1.next;
                cur2=cur2.next.next;
                if(cur1==cur2){
                    target=cur1;
                    break;
                }
            }
        }
        return target;
    }
    /**链表有环的情况下，计算环的长度*/
    public int calSingle(HeroNode herNode){
        HeroNode cur=herNode;
        HeroNode cur1=herNode;
        int length=0;
        while(true){
            length++;
            cur=cur.next;
            cur1=cur1.next.next;
            if(cur==cur1){
                break;
            }
        }
        return length;
    }


}
    /**定义HeroNode,每个HeroNode就是一个节点*/
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    //指向下一个节点
    /**构造器（初始化）*/
    public HeroNode(int no,String name,String nickname) {
        this.no=no;
        this.name=name;
        this.nickname=nickname;
        this.next=null;
    }
    /**为了显示方法，我们重新定义toString*/
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}