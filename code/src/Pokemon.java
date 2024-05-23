public class Pokemon {

    private final String name;

    private final String type;

    private final String desc;

    public Pokemon(String[] data) {
        name = data[0];
        type = data[1];
        desc = data[2];
    }

    public String toString() { return name + " [" + type + "]"; }

    public String getName() { return name; }

    public String getDesc() { return desc; }
}
