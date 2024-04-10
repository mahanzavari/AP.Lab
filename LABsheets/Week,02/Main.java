import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

         Concat concat;

         PhoneBook phoneBook = new PhoneBook();
        boolean q=true;
        while(q){
            Scanner scanner=new Scanner(System.in);
            String command=scanner.nextLine();
            if(command.equals("exit")){
                q=false;
            }
            if(command.contains("concat")) {
                if (command.contains("-a")) {
                    String[] array = command.split(" ");
                    System.out.println("please enter contact's group:");
                    System.out.print("Input :");
                    String g = scanner.nextLine();
                    System.out.println("please enter contact's email");
                    System.out.print("Input :");
                    String e = scanner.nextLine();
                    System.out.println("please enter country code ");
                    System.out.println("Input:");
                    String x=scanner.nextLine();
                    System.out.println("please enter contact's phone number");
                    System.out.print("Input :");
                    String p = scanner.nextLine();
                    System.out.println("please enter contact's zip code");
                    System.out.print("Input :");
                    String z = scanner.nextLine();
                    System.out.println("please enter contact's country");
                    System.out.print("Input :");
                    String i = scanner.nextLine();
                    System.out.println("please enter contact's city");
                    System.out.print("Input :");
                    String c = scanner.nextLine();
                    System.out.println("contact saved!");
                    System.out.println(array[3]);
                     Address address = new Address(z, i, c);
                     PhoneNumber phoneNumber = new PhoneNumber(x, p);
                    concat = new Concat(g, e, array[2], array[3]);
                    HashSet<Address> v=new HashSet<>();
                    v.add(address);
                    HashSet<PhoneNumber> j=new HashSet<>();
                    j.add(phoneNumber);
                    concat.setA(j);
                    concat.setB(v);
                    phoneBook.addConcat(concat);


                }
                if (command.contains("-r")) {
                    String[] array = command.split(" ");
                    phoneBook.deleteConcat(array[2], array[3]);
                }

            }
                if(command.contains("show")){

                    if(command.contains("-g")) {
                        String[] array = command.split(" ");
                        HashSet<Concat> groupContacts = phoneBook.groupconcat(array[2]);
                        for (Concat contact : groupContacts) {
                            System.out.println("Group: " + contact.getGroup());
                            System.out.println("Email: " + contact.getEmail());
                            System.out.println("First Name: " + contact.getFirstname());
                            System.out.println("Last Name: " + contact.getLastName());
                            System.out.println("Phone Number: " + contact.getPhoneNumber());
                            System.out.println("Address:" + contact.getAddress());
                        }
                    }
                    else if(command.contains("-c")){
                        String[] array=command.split(" ");
                        Concat z=phoneBook.findConcat(array[2],array[3]);
                        System.out.println("Group: " + z.getGroup());
                                System.out.println("Email: " + z.getEmail());
                                System.out.println("First Name: " + z.getFirstname());
                                System.out.println("Last Name: " + z.getLastName());
                                System.out.println("Phone Number: " + z.getPhoneNumber());
                            System.out.println("Address:"+z.getAddress());
                    }
                    else{
                        phoneBook.printContact();
                    }
                }

        }
    }
}