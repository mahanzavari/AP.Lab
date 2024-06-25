public abstract class Person {
    protected Hospital hospital;
    protected String username;
    protected String password;
    protected int money;

    public Person(Hospital hospital, String username, String password, int money) {
        this.hospital = hospital;
        this.username = username;
        this.password = password;
        this.money = money;
    }

    public abstract void menu();
}
