public class Main {
    public static void main(String[] args) {
        Student1 student1=new Student1("James","Gosling","0987654");
        Student1 student2=new Student1("Dennis","Ritchie","1234567");
        student1.setgrade(18);
        student2.setgrade(17.5);
        student1.printStudentInfo();
        System.out.println();
        student2.printStudentInfo();
        System.out.println();
        Lab lab=new Lab("Mr.Smith","Monday",30);
        lab.addstudent(student1);
        lab.addstudent(student2);
        lab.printLabInfo();


    }
}