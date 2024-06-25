public class Parrot extends Bird implements Prey {
    public Parrot(String name, int age, String specialFeature, int heightOfFly) {
        super(name, heightOfFly, specialFeature, heightOfFly);
    }

    public String detail() {
        return getName() + ", age:" + getAge() + ", height of fly:" + getHeightOfFly() + ", " + getSpecialFeature();
    }
}
