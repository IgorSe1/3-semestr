import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Predicate;

public class UserRegistry {
    private HashMap<UserIdentifier, User> users;
    private int nextId;

    public UserRegistry() {
        users = new HashMap<>();
        nextId = 1;
    }

    public void registerUser(String login, String password) {
        if (isUserRegistered(login)) {
            System.out.println("Користувач " + login + " вже є у списку");
            return;
        }
        UserIdentifier key = new UserIdentifier(nextId, login);
        users.put(key, new User(key, password));
        nextId++;
        System.out.println("Користувач " + login + " зареєстрований");
    }

    public void loginUser(String login, String password) {
        for (var user : users.values()) {
            if (user.getIdentifier().getName().equals(login) && user.getPassword().equals(password)) {
                user.setLoggedIn(true);
                user.setLastLoginDate(LocalDateTime.now());
                System.out.println("Користувач " + login + " увійшов у систему");
                return;
            }
        }
        System.out.println("Неможливо ідентифікувати або аутентифікувати користувача");
    }

    public void logoutUser(int userId) {
        for (var user : users.values()) {
            if (user.getIdentifier().getId() == userId) {
                user.setLoggedIn(false);
                System.out.println("Користувач " + user.getIdentifier().getName() + " вийшов з системи");
                return;
            }
        }
        System.out.println("Користувача з ID " + userId + " не знайдено");
    }

    public boolean isUserRegistered(String login) {
        for (var key : users.keySet()) {
            if (key.getName().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void removeUser(int id) {
        UserIdentifier toRemove = null;
        for (var key : users.keySet()) {
            if (key.getId() == id) {
                toRemove = key;
                break;
            }
        }
        if (toRemove != null) {
            String name = toRemove.getName();
            users.remove(toRemove);
            System.out.println("Користувач " + name + " видалений");
        } else {
            System.out.println("Користувача з ID " + id + " не знайдено");
        }
    }

    public void printTotalUniqueUsers() {
        System.out.println("Унікальних користувачів: " + users.size());
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Список порожній");
            return;
        }
        for (var user : users.values()) {
            System.out.println("  " + user);
        }
    }

    public LinkedList<User> getUserList() {
        LinkedList<User> list = new LinkedList<>();
        for (var user : users.values()) {
            list.add(user);
        }
        return list;
    }

    public LinkedList<User> getInOrder(Comparator<User> comparator) {
        LinkedList<User> list = getUserList();
        list.sort(comparator);
        return list;
    }

    public LinkedList<User> getFiltered(Predicate<User> predicate) {
        LinkedList<User> list = new LinkedList<>();
        for (var user : users.values()) {
            if (predicate.test(user)) {
                list.add(user);
            }
        }
        return list;
    }
}
