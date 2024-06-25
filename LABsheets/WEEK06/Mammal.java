public abstract class Mammal extends Animal {
    private String speed;

    public Mammal(String name, int age, String specialFeature, String speed) {
        super(name, age, specialFeature);
        this.speed = speed;
    }

    public String getSpeed() {
        return speed;
    }

}
