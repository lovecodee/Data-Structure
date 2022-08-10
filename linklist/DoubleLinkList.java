package linklist;

/**
 * @author Jin
 * @ Date 2022年06月2022/6/30日9:45
 */
public class DoubleLinkList {
    public static void main(String[] args) {
        //创建结点
        System.out.println("**************************");
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero3=new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero4=new HeroNode2(4,"林冲","豹子头");
        HeroNode2 hero5=new HeroNode2(4,"公孙胜","入云龙");

        //创建头结点
        DoubleLinkListDemo doubleLinkListDemo=new DoubleLinkListDemo();
        //添加节点
        doubleLinkListDemo.addByOrder(hero1);
        doubleLinkListDemo.addByOrder(hero2);
        doubleLinkListDemo.addByOrder(hero3);
        doubleLinkListDemo.addByOrder(hero4);
        //展示双链表
        System.out.println("创建后的双链表为：");
        doubleLinkListDemo.showList();
        //删除结点
        System.out.println("删除后的双链表为：");
        doubleLinkListDemo.del(2);
        doubleLinkListDemo.showList();
        //更新节点（添加节点）
        System.out.println("修改结点后的双链表为：");
        doubleLinkListDemo.update(hero5);
        doubleLinkListDemo.showList();
        //展示双链表
        System.out.println("最终的双链表为：");
        doubleLinkListDemo.showList();
    }
}

class DoubleLinkListDemo{
    /**创建头结点*/
    HeroNode2 head= new HeroNode2(0,"","");

    //相关操作如下
    /**（1）返回头节点
     * @return*/
    public HeroNode2 getHead(){
        return head;
    }

    /**（2）遍历双向链表*/

    public void showList(){
        //第一步：判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能够移动，需要一个辅助变量来遍历
        HeroNode2 temd=head.next;
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
    /**（3）添加节点到双链表(方式一：尾插法，不考虑编号)*/
    public void add(HeroNode2 heroNode){//不考虑编号的顺序
        //通过temp(辅助节点)来找到链表的最后一个节点
        HeroNode2 temp=head;
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
        heroNode.pre=temp;
        //将新结点加入到最后
    }
    /**方式二：添加节点（根据编号进行添加）*/
    public void addByOrder(HeroNode2 heroNode){
        /**
         * 头结点不能动，可以通过辅助指针（变量）找到添加的位置前一个节点
         * */
        HeroNode2 tem=head;
        boolean flag=false;
        //flag标志添加的编号已经存在，默认为false
        while(true) {
            if (null == tem.next) {
                //tem已经在链表的最后
                break;
            }
            if (tem.next.no > heroNode.no) {
                //说明tem已经在链表的最后
                break;
            } else if (tem.next.no == heroNode.no) {
                //说明希望添加的heroNode的编号已然存在
                flag = true;
                //说明编号存在
                break;
            }
            tem=tem.next;
        }
        //判断flag的值
        if(flag){
            //不能添加，说明编号存在
            System.out.printf("准备插入英雄的编号为%d已经存在，不能加入！！",heroNode.no);
        }else{
            //新节点插入链表
           heroNode.next=tem.next;
           heroNode.pre=tem;
           if(tem.next==null){
               //tem为双链表的最后一个节点
               tem.next=heroNode;
           }else{
               //tem非双链表的最后一个节点
               tem.next.pre=heroNode;
               tem.next=heroNode;
           }
        }
    }


    /**（4）修改链表的内容*/
    public void update(HeroNode2 heroNode){
        HeroNode2 te=head.next;
        boolean flag=false;
        //如果找到目标节点，就设置为true
        while(true){
            if(te==null){
                break;}
            if(te.no== heroNode.no){
                flag=true;
                break;}
            te=te.next;
        }
        if(flag){
            te.name= heroNode.name;
            te.nickname= heroNode.nickname;
        } else{
            System.out.printf("对应英雄的节点没有在原链表中找到！！");
        }
    }





    /**（5）从双向链表中删除一个节点*/
    public void del(int no){
        if(head.next==null){
            System.out.println("链表为空，无法删除！！！");
            return;
        }
        HeroNode2 pt=head.next;
        boolean flag=false;
        while(true){
            if(pt==null){
                //已经到了单链表的最后一个结点的next
                break;
            }
            if(pt.no==no){
                //找到待删除结点的前一个结点
                flag=true;
                break;
            }
            pt=pt.next;
        }
        if(flag){
            pt.pre.next=pt.next;
            if(pt.next!=null) {
                //待删除的结点不是最后一个结点，如果是最后一个节点的话，若执行会出现空指针异常
                pt.next.pre = pt.pre;
            }
        }else{
            System.out.printf("要删除的%d节点不存在",no);
        }
    }

}










/**定义HeroNode2,每个HeroNode2就是一个节点*/
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    /**指向下一个节点*/
    public HeroNode2 pre;
    //指向上一个节点
    /**构造器（初始化）*/
    public HeroNode2(int no,String name,String nickname) {
        this.no=no;
        this.name=name;
        this.nickname=nickname;
        this.next=null;
        this.pre=null;
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