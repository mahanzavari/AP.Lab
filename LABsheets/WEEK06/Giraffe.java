public class Giraffe extends Mammal implements Prey {
    public Giraffe(String name, int age, String specialFeature, String speed) {
        super(name, age, specialFeature, speed);
    }

    public String detail() {
        return getName() + ", age:" + getAge() + ", speed:" + getSpeed() + ", " + getSpecialFeature();
    }
}
