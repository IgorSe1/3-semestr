import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private UserIdentifier identifier;
    private String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;

    public User(UserIdentifier identifier, String password) {
        this.identifier = identifier;
        this.password = password;
        this.lastLoginDate = null;
        this.isLoggedIn = false;
    }

    public UserIdentifier getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLastLoginDate(LocalDateTime date) {
        this.lastLoginDate = date;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User other = (User) o;
        return Objects.equals(identifier, other.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return "ID: " + identifier.getId() +
                ", Логін: " + identifier.getName() +
                ", Онлайн: " + (isLoggedIn ? "так" : "ні") +
                ", Останній вхід: " + (lastLoginDate != null ? lastLoginDate : "ніколи");
    }
}