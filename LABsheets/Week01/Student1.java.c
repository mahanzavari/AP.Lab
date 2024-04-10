public   class  Student1 {
    private String firstname;
    private String lastname;
    private String id;
    private double grade;

    public Student1(String firstname, String lastname, String id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        grade = 0;

    }
    public Student1(){

    }

    public void printStudentInfo() {
        System.out.println(firstname + " " + lastname + " " + "\nID: " + id + "\nGrade: " + grade);

    }

    public String getfirstname() {
        return firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public String getid() {
        return id;
    }

    public double getgrade() {
        return grade;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setlastname(String lastname) {
        this.lastname= lastname;
    }

    public void setid(String id) {
        this.id = id;
    }

    public void setgrade(double grade) {
        this.grade = grade;
    }
}