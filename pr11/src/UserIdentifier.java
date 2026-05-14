import java.util.Objects;

public class UserIdentifier {
    private int id;
    private String name;

    public UserIdentifier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentifier other = (UserIdentifier) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UserIdentifier{id=" + id + ", name='" + name + "'}";
    }
}
