import java.util.HashSet;

public   class  Concat {
    private String group;
    private String email;
    private String firstname;
    private String lastName;
    private HashSet<PhoneNumber> a;
    private  HashSet<Address> b;

    public Concat(String group, String email, String firstname, String lastName) {
        this.group = group;
        this.email = email;
        this.firstname = firstname;
        this.lastName = lastName;
        a=new HashSet<>();
        b=new HashSet<>();
    }



    public String getGroup() {
        return group;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public HashSet<PhoneNumber> getPhoneNumber() {
        return a;
    }

    public HashSet<Address> getAddress() {
        return b;
    }

    public void setA(HashSet<PhoneNumber> a) {
        this.a = a;
    }

    public void setB(HashSet<Address> b) {
        this.b = b;
    }
}