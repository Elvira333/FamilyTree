package people;

public class DataOfHuman {
    public int id;
    public String name;
    public int parentId;

    public DataOfHuman(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "id№" + id+ " " + name;
    }
}
