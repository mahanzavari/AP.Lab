public abstract class Bird extends Animal {
    private int heightOfFly;

    public Bird(String name, int age, String specialFeature, int heightOfFly) {
        super(name, age, specialFeature);
        this.heightOfFly = heightOfFly;
    }

    public int getHeightOfFly() {
        return heightOfFly;
    }

}
