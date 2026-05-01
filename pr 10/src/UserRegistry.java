import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;

public class UserRegistry {
    private HashSet<User> users;
    private int nextId;

    public UserRegistry() {
        users = new HashSet<>();
        nextId = 1;
    }

    public void registerUser(String login, String password) {
        if (isUserRegistered(login)) {
            System.out.println("Користувач " + login + " вже є у списку");
            return;
        }
        users.add(new User(nextId++, login, password));
        System.out.println("Користувач " + login + " зареєстрований");
    }

    public void loginUser(String login, String password) {
        for (User user : users) {
            if (user.getName().equals(login) && user.getPassword().equals(password)) {
                user.setLoggedIn(true);
                user.setLastLoginDate(LocalDateTime.now());
                System.out.println("Користувач " + login + " увійшов у систему");
                return;
            }
        }
        System.out.println("Неможливо ідентифікувати або аутентифікувати користувача");
    }

    public void logoutUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                user.setLoggedIn(false);
                System.out.println("Користувач " + user.getName() + " вийшов з системи");
                return;
            }
        }
        System.out.println("Користувача з ID " + userId + " не знайдено");
    }

    public boolean isUserRegistered(String login) {
        for (User user : users) {
            if (user.getName().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void removeUser(int id) {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getId() == id) {
                System.out.println("Користувач " + user.getName() + " видалений");
                it.remove();
                return;
            }
        }
        System.out.println("Користувача з ID " + id + " не знайдено");
    }

    public void printTotalUniqueUsers() {
        System.out.println("Унікальних користувачів: " + users.size());
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Список порожній");
            return;
        }
        System.out.println("Список користувачів:");
        for (User user : users) {
            System.out.println("  " + user);
        }
    }
}
