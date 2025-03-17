package be.ucll.model;

public class Pony {

    private String name;

    private int age;

    public Pony(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void updateNameAndAge(String name, int age) {
        this.name = name;
        this.age = age;
    }

}