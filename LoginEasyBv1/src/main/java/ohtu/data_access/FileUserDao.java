package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;


public class FileUserDao implements UserDao {

    private File file;
    private String url;

    public FileUserDao(String urli) {
        file = new File(urli);
        url = urli;
    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<User>();
        try {
            Scanner lukija = new Scanner(file);
            while (lukija.hasNextLine()) {
                String username = lukija.nextLine();
                String salasana = lukija.nextLine();

                User user = new User(username, salasana);
                users.add(user);
            }

            lukija.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    @Override
    public User findByName(String name) {
        try {
            Scanner lukija = new Scanner(file);
            while (lukija.hasNextLine()) {
                String username = lukija.nextLine();
                String salasana = lukija.nextLine();

                if (username.equals(name)) {
                    return new User(username, salasana);
                }
            }

            lukija.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void add(User user) {
        try {
            FileWriter writer = new FileWriter(url, true);
            writer.append(user.getUsername() + "\n");
            writer.append(user.getPassword() + "\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUsers(List<User> users) {
        try {
            FileWriter writer = new FileWriter(url, true);
            int i = 0;
            while (i < users.size()) {
                User user = users.get(i);
                writer.append(user.getUsername() + "\n");
                writer.append(user.getPassword() + "\n");
            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Scanner lukija = new Scanner(file);
            while (lukija.hasNextLine()) {
                String username = lukija.nextLine();
                String salasana = lukija.nextLine();

                User user = new User(username, salasana);
                users.add(user);
            }

            lukija.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
}
