public class Eagle extends Bird implements Hunter {
    public Eagle(String name, int age, String specialFeature, int heightOfFly) {
        super(name, heightOfFly, specialFeature, heightOfFly);
    }

    public String detail() {
        return getName() + ", age:" + getAge() + ", height of fly:" + getHeightOfFly() + ", " + getSpecialFeature();
    }

    public void hunt(Prey prey) {
        System.out.println(getName() + " hunted the " + prey.getName());
    }
}
