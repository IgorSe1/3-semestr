import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lastLoginDate = null;
        this.isLoggedIn = false;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public LocalDateTime getLastLoginDate() { return lastLoginDate; }
    public boolean isLoggedIn() { return isLoggedIn; }

    public void setLastLoginDate(LocalDateTime date) { this.lastLoginDate = date; }
    public void setLoggedIn(boolean loggedIn) { this.isLoggedIn = loggedIn; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Логін: " + name + ", Онлайн: " + (isLoggedIn ? "так" : "ні") +
               ", Останній вхід: " + (lastLoginDate != null ? lastLoginDate : "ніколи");
    }
}
