package mk.ukim.finki.crosswordapi.model.enums;

public enum Difficulty {
    EASY ("Лесно"),
    MEDIUM ("Средно"),
    HARD ("Тешко");

    private final String macedonianName;

    public String prettyName(){
        return macedonianName;
    }


    Difficulty(String name) {
        this.macedonianName = name;
    }
}
