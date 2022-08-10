package hashtable;

import java.util.Scanner;

/**
 * @author Jin
 * @ Date 2022年07月2022/7/3日17:35
 *           实现哈希表
 */
public class HashTab {
    public static void main(String[] args) {
        Hashtable hashtable=new Hashtable(7);
        String key="";
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("  ****************");
            System.out.println("  *     菜单     *");
            System.out.println("  * add:添加雇员  *");
            System.out.println("  * list:显示雇员 *");
            System.out.println("  * find:查找雇员 *");
            System.out.println("  * del:删除雇员  *");
            System.out.println("  * exit:退出系统 *");
            System.out.println("  ****************");
            System.out.println("请对应输入选择：");
            key=scanner.next();
            switch(key){
                case"add":
                    System.out.println("请输入ID：");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名：");
                    String name=scanner.next();
                    Emp emp=new Emp(id,name);
                    hashtable.add(emp);
                    break;
                case"list":
                    hashtable.list();
                    break;
                case"find":
                    System.out.println("请输如要查找的id:");
                    id=scanner.nextInt();
                    hashtable.findEmpById(id);
                    break;
                case"del":
                    System.out.println("请输入待删除雇员的id: ");
                    id = scanner.nextInt();
                    hashtable.delEmpById(id);
                    break;
                case"exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入选择不存在，请重新输入");
                    break;
            }
        }
    }
}

/**创建哈希链表*/
class Hashtable{
    private EmplinkedList[]emplinkedLists;
    public int size;
    //构造器
    public Hashtable(int size) {
        this.size=size;
     emplinkedLists=new EmplinkedList[size];
     //初始化链表
        for (int i = 0; i < size; i++) {
            emplinkedLists[i]=new EmplinkedList();
        }
    }
    /*添加*/
    public void add(Emp emp){
        int empLinkedListNO=hashFun(emp.id);
        emplinkedLists[empLinkedListNO].add(emp);
    }
    /**根据输入的id来进行查找雇员*/
    public void findEmpById(int id){
        int empLinkedList=hashFun(id);
        Emp emp=emplinkedLists[empLinkedList].findEmpByld(id);
        if(emp!=null){
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n",(empLinkedList+1),id);
        }else{
            System.out.println("在哈希表中没有找到到该雇员！！");
        }
    }
    public void delEmpById(int id){
        int empLinkedList=hashFun(id);
        boolean tu=emplinkedLists[empLinkedList].del(id);
        if(tu){
            System.out.printf("在第%d条链表中 id = %d 的雇员已经被删除\n",(empLinkedList+1),id);
        }else{
            System.out.println("待删除雇员在哈希表中不存在！！");
        }
    }
    /**遍历所有的链表*/
    public void list(){
        for (int i = 0; i <size ; i++) {
            emplinkedLists[i].list(i);
        }
    }
    /**编写一个哈希（散列）函数*/
    public int hashFun(int id){
       return id%size;
    }
}


class Emp{
    public int id;
    public String name;
    public Emp next;
    //默认为空
    public Emp(int id,String name) {
        super();
        this.id = id;
        this.name=name;
    }
}
class EmplinkedList{
    private Emp head;
    /**默认为空
     * 使用尾插法来进行添加新员工
     * */
    public void add(Emp emp){
        //添加第一个员工
        if(head==null){
            head=emp;
            return;
        }
        //辅助指针
        Emp cur=head;
        while(true){
            if(cur.next==null){
                break;
            }
            cur=cur.next;
        }
        cur.next=emp;
    }
    /**遍历链表的雇员信息*/
    public void list(int no){
        if(head==null){
            System.out.println("第"+(no+1)+" 链表为空");
            return;
        }
        System.out.print("第"+(no+1)+" 链表的信息为：");
        Emp cur=head;
        while(true){
            System.out.printf("=> id =%d, name=%s\t",cur.id,cur.name);
            if(cur.next==null){
                    break;
            }
            cur=cur.next;
        }
        System.out.println();
    }
    /** 删除雇员信息*/
    public boolean del(int no){
        boolean flag=false;
        if(head==null){
            return false;
        }
        Emp cur=head;
        Emp cur1=head;
       while(true){
           if(cur==head&&head.id==no){
               flag=true;
               head=head.next;
               break;
           }
           if(cur.next!=null){
               cur1=cur;
               cur=cur.next;
               if(cur.id==no){
                   flag=true;
                   cur1.next=cur1.next.next;
                   break;
               }
           }else{
               break;
           }

       }
        return flag;
    }
    /**根据id查找雇员，若查找成功，就返回Emp,反之，就返回null*/
    public Emp findEmpByld(int id){
        if(head==null){
            System.out.println("链表为空！");
            return null;
        }
        Emp cur=head;
        while(true){
            if(cur.id==id){
                break;
            }
            if(cur.next==null){
                cur=null;
                break;
            }
            cur=cur.next;
        }
        return cur;
    }

}
