package linklist;

/**
 * @author Jin
 * @ Date 2022年06月2022/6/30日14:05
 *
 * 通过使用循环链表来实现约瑟夫环问题
 *
 */
public class CircleLinkList {
    public static void main(String[] args) {
                CircleLinkedList circleLinkList=new CircleLinkedList();
                circleLinkList.addPerson(41);
                circleLinkList.showPerson();
                System.out.println("约瑟夫环的运行结果为：");
                circleLinkList.countPerson(1,3,41);

    }
}
/**创建一个环形的单向链表*/
class CircleLinkedList{
    /**创建一个头指针*/
    private Person first=null;
    /**（1）添加节点，形成环形链表（没有头节点）*/
    public void addPerson(int nums){
        if(nums<1){
            System.out.println("nums的值不正确！！");
            return;
        }
        Person cur=null;
        //辅助指针，帮助构建链表
        for(int i=1;i<=nums;i++){
            Person person =new Person(i);
            if(i==1){
                //第一节点特别考虑
                first=person;
                first.setNext(first);
                cur=first;
                //让cur指向第一个小孩
            }else{
                //非第一个节点
                cur.setNext(person);
                person.setNext(first);
                cur=person;
            }
        }
    }














    /**（2）遍历当前环形链表*/
    public void showPerson(){
        if(first==null){
            System.out.println("没有任何人~~~");
            return;
        }
        Person cur=first;
        while(true){
            System.out.printf("该犹太人编号为%d\n",cur.getNo());
            if(cur.getNext()==first){
                //说明已经遍历完
                break;
            }
            cur=cur.getNext();
        }
    }
    /*
    *  根据用户的输入计算出出圈的顺序
    *  startNo: 表示从第几个人开始数
    *  countNum: 表示数几下
    *  nums: 表示最初有几个人在圈中
    * */
    /**(3)约瑟夫环问题的解决*/
    public void countPerson(int startNo,int countNum,int nums){
        /** (1)对数据进行校验，检验其合理性*/
        if(first==null||startNo<1||startNo>nums){
            System.out.println("输入数据有误！！！");
            return;
        }
        //（2）创建辅助指针，帮助出圈
        Person helper=first;
        //需要创建一个辅助变量helper,事先应该指向环形链表的最后一个节点
        while(true){
            if(helper.getNext()==first){
                //说明helper指向了最后一个结点
                break;
            }
            helper=helper.getNext();
        }
        //（3）人报数时，先让first和helper移动（k-1）次
        for(int i=0;i<startNo-1;i++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //(4)人开始报数，让first和helper指针同时移动（m-1）次，然后出圈(循环操作，直到圈中只有一个节点)
        while(true){
            if(helper==first) {
                //圈中只有一个节点
                break;
            }
            for(int j=0;j<countNum-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //输出出圈的人编号
            System.out.printf("编号%d出圈\n",first.getNo());
            //将first指向的人出圈
            first=first.getNext();
            //改变指向（方向）
            helper.setNext(first);
            //改变内容
        }
        System.out.printf("最后留在圈中人的编号为%d\n",first.getNo());
    }

}











/**创建一个Person类，表示结点*/
class Person{
    private int no;
    /**表示的是节点中的编号*/
    private Person next;
    /**指向下一个节点，默认值为null*/
    public Person(int no) {
        this.no = no;
    }
    /**方法，设置或获取属性*/
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Person getNext() {
        return next;
    }
    public void setNext(Person next) {
        this.next = next;
    }
}