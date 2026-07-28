public class ConstructorExample {
    int id;
    String name;
    int age;
    // public ConstructorExample(){
   //   System.out.println("Hello yaawr");
   //}
    public ConstructorExample(int id,String name,int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }
    public void display(){
        System.out.println("ID: "+id);
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
    }
    public static void main(String args[]){
        ConstructorExample sc=new ConstructorExample(1,"TBSM",10);
        System.out.println(sc);
        sc.display();
    }
}
