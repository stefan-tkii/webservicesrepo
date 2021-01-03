package webproject.filmreview.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import webproject.filmreview.PasswordHasher;
import webproject.filmreview.Models.User;
import webproject.filmreview.Models.WatchesMovie;
import webproject.filmreview.Models.WatchesSeries;

public class UserService 
{
    private Map<Long, User> users = Database.getAllUsers();

    public UserService()
    {

    }

    public List<User> getAllUsers()
    {
        return new ArrayList<User>(users.values());
    }

    public User getUserById(long userId)
    {
        return users.get(userId);
    }

    public User addUser(String username, String password)
    {
        List<WatchesMovie> moviesList = new ArrayList<>();
        List<WatchesSeries> seriesList = new ArrayList<>();
        String hashedPassword = PasswordHasher.generateSecurePassword(password, username);
        User user = new User(users.size()+1, username, hashedPassword, moviesList, seriesList);
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(String password, long userId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        String hashedPassword = PasswordHasher.generateSecurePassword(password, user.getUsername());
        user.setPassword(hashedPassword);
        users.put(userId, user);
        return user;
    }

    public void deleteUser(long userId)
    {
        users.remove(userId);
    }
    
}