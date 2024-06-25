import java.util.HashSet;

public abstract class Animal {
    private String name;
    private int age;
    private String specialFeature;

    private static HashSet<Animal> animalSet = new HashSet<>();

    public Animal(String name, int age, String specialFeature) {
        this.name = name;
        this.age = age;
        this.specialFeature = specialFeature;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecialFeature() {
        return specialFeature;
    }

    public abstract String detail();

    public static void addAnimal(Animal animal) {
        if (animal != null) {
            animalSet.add(animal);
        }
    }

    public static void show() {
        int index = 1;

        for (Animal animal : animalSet) {
            System.out.println(index++ + "-" + animal.detail());
        }
    }

    public static void main(String[] args) {
        Parrot parrot = new Parrot("parrot", 12, "This parrot can talk", 10);
        Giraffe giraffe = new Giraffe("giraffe", 34, "This animal is very tall", "67");
        Cheetah cheetah = new Cheetah("cheetah", 45, "This animal is very fast", "120");
        Eagle eagle = new Eagle("eagle", 34, "This animal can fly high", 30);

        Animal.addAnimal(eagle);
        Animal.addAnimal(cheetah);
        Animal.addAnimal(parrot);
        Animal.addAnimal(giraffe);

        Animal.show();

        Hunter hunter = (Hunter) eagle;
        Prey prey = (Prey) parrot;

        hunter.hunt(prey);

    }

}