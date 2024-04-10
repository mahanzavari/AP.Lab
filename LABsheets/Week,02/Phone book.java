import java.util.HashSet;

public class PhoneBook {
    HashSet<Concat>  a;

    public PhoneBook() {
        a=new HashSet<>();
    }
    public boolean addConcat(Concat x){
         if(a.contains(x)){
             return false;
         }
         else{
             a.add(x);
             return true;
         }
    }
    public void deleteConcat(String firstName,String lastName){
        for(Concat i:a) {
            if ((i.getFirstname()).equals(firstName) && (i.getLastName()).equals(lastName)) {
                a.remove(i);
            }
        }
    }
    public Concat findConcat(String firstname,String lastName){
        for(Concat j:a){
            if((j.getFirstname()).equals(firstname) && (j.getLastName()).equals(lastName)){
                return j;
            }
        }
        return null;

    }
    public  HashSet<Concat> groupconcat(String groupname){
         HashSet<Concat> u=new HashSet<>();
        for(Concat i:a) {
            if ((i.getGroup()).equals(groupname)) {
                u.add(i);
            }
        }
    return u;


    }


    public void printContact(){
          for(Concat j:a){
              System.out.println("group:"+j.getGroup());
              System.out.println("email:"+j.getEmail());
              System.out.println("firstname:"+j.getFirstname());
              System.out.println("lastname:"+j.getLastName());
              System.out.println("phonenumber"+j.getPhoneNumber());
              System.out.println("address:"+j.getAddress());
              System.out.println();
          }
    }



}