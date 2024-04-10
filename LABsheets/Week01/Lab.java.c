public class Lab {
    private static Student1[] students;
    private String teacherName;
    private String dayOfWeek;
    private int maxSize;
    private static  int currentSize;
    private double avgGrade;

    public Lab(String teacherName,String dayOfWeek,int maxSize){
        this.teacherName=teacherName;
        this.dayOfWeek=dayOfWeek;
        this.maxSize=maxSize;
        students=new Student1[maxSize];


    }
   public void printLabInfo(){
       System.out.println("TeacherName: "+teacherName+"\n"+"DayOfWeek: " +dayOfWeek+"MaxSize: "+maxSize+"\n"+"CurrentSize :" +currentSize+"\n"+"AvgGrade: " +avverage()+"\n");
       System.out.println("NameStudent: ");
       for(int i =0;i<currentSize;++i){

           System.out.print(students[i].getfirstname()+" "+students[i].getlastname()+" "+students[i].getid()+" "+students[i].getgrade());
           System.out.println();
       }
   }
  public  void addstudent(Student1 x){
        if(maxSize>=currentSize){
        students[currentSize]=x;
      currentSize++;
         }
   }
   public double avverage(){
        double nomre=0;
        for(int i=0;i<currentSize;++i){
            nomre+=students[i].getgrade();
        }
        double miangin=nomre/currentSize;
        return miangin;
    }


}