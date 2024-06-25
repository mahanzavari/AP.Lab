public class Cheetah extends Mammal implements Hunter {
    public Cheetah(String name, int age, String specialFeature, String speed) {
        super(name, age, specialFeature, speed);
    }

    public String detail() {
        return getName() + ", age:" + getAge() + ", speed:" + getSpeed() + ", " + getSpecialFeature();
    }

    public void hunt(Prey prey) {
        System.out.println(getName() + " hunted the " + prey.getName());
    }
}
