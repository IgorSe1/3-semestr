import java.io.*;
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
        var key = new UserIdentifier(nextId, login);
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
            var name = toRemove.getName();
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
        var list = new LinkedList<User>();
        for (var user : users.values()) {
            list.add(user);
        }
        return list;
    }

    public LinkedList<User> getInOrder(Comparator<User> comparator) {
        var list = getUserList();
        list.sort(comparator);
        return list;
    }

    public LinkedList<User> getFiltered(Predicate<User> predicate) {
        var list = new LinkedList<User>();
        for (var user : users.values()) {
            if (predicate.test(user)) {
                list.add(user);
            }
        }
        return list;
    }

    public void saveToFile(String path) {
        try (
            var fos = new FileOutputStream(path);
            var oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(users);
            oos.writeInt(nextId);
            System.out.println("Збережено " + users.size() + " користувачів у файл " + path);
        } catch (Exception e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }

    public void loadFromFile(String path) {
        try (
            var fis = new FileInputStream(path);
            var ois = new ObjectInputStream(fis)
        ) {
            users = (HashMap<UserIdentifier, User>) ois.readObject();
            nextId = ois.readInt();

            for (var user : users.values()) {
                user.setLoggedIn(false);
            }

            System.out.println("Відновлено " + users.size() + " користувачів з файлу " + path);
        } catch (Exception e) {
            System.out.println("Помилка відновлення: " + e.getMessage());
        }
    }
}
