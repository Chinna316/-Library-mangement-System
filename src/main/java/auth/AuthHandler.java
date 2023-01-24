package auth;

import com.mongodb.*;
import models.Session;
import models.User;
import org.bson.types.ObjectId;

import javax.servlet.http.Cookie;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AuthHandler {
    private static AuthHandler instance;
    private final DB db;
    private static final String SESSION = "sessions";
    public static final String DATABASE = "library";
    public static final String USER_COLLECTION = "users";
    public static Map<String, Session> sessionMap = new HashMap<>();

    private AuthHandler() {
        MongoClient client = new MongoClient();
        db = client.getDB(DATABASE);
        DBCollection collection = db.getCollection(USER_COLLECTION);
        String admin = "librarian@gmail.com";
        DBObject result = collection.findOne(new BasicDBObject("email", admin));
        if (result == null) {
            BasicDBObject user = new BasicDBObject();
            user.put("email", admin);
            user.put("name", "admin");
            user.put("password", encrypt("password"));
            user.put("role", User.LIBRARIAN);
            collection.insert(user);
        }
    }

    public static AuthHandler getInstance() throws UnknownHostException {
        if (instance == null)
            instance = new AuthHandler();
        return instance;
    }

    public boolean registerUser(String email, String password, String name, String username, String mobile, String address) {
        DBCollection collection = db.getCollection(USER_COLLECTION);
        // checking duplicate email
        DBObject result = collection.findOne(new BasicDBObject("email", email));
        if (result == null) {
            BasicDBObject user = new BasicDBObject();
            user.put("email", email);
            user.put("name", name);
            user.put("username", username);
            user.put("password", encrypt(password));
            user.put("role", User.STUDENT);
            user.put("active", false);
            user.put("mobile", mobile);
            user.put("address", address);
            collection.insert(user);
            return true;
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    public String loginUser(String email, String password) throws NoSuchAlgorithmException {
        DBCollection collection = db.getCollection(USER_COLLECTION);
        DBObject result = collection.findOne(new BasicDBObject("email", email));
        if (result == null)
            throw new RuntimeException("User not found");
        else if (result.get("password").equals(encrypt(password))) {
            return makeSession(email);
        } else
            throw new RuntimeException("Invalid password");
    }

    private String getSession(Cookie[] cookies) {
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("jwt"))
                return cookie.getValue();
        }
        return null;
    }

    public void logoutUser(Cookie[] sessions) {
        String session = getSession(sessions);
        sessionMap.remove(session);
        /*
        String session = getSession(sessions);
        DBCollection sessionsCollection = db.getCollection(SESSION);
        sessionsCollection.remove(new BasicDBObject("jwt", session));
        */
    }

    public User authenticateUser(Cookie[] sessions) {
        String session = getSession(sessions);
        Session result = sessionMap.get(session);
        if (result == null)
            return null;
        else {
            String email = result.getUserId();
            DBCollection usersCollection = db.getCollection(USER_COLLECTION);
            DBObject user = usersCollection.findOne(new BasicDBObject("email", email));
            return getUser(user);
        }
        /*
        DBCollection sessionsCollection = db.getCollection(SESSION);
        DBObject result = sessionsCollection.findOne(new BasicDBObject("jwt", session));
        if (result == null)
            return null;
        else {
            String email = (String) result.get("user_id");
            DBCollection usersCollection = db.getCollection(USER_COLLECTION);
            DBObject user = usersCollection.findOne(new BasicDBObject("email", email));
            return getUser(user);
        }
        */
    }

    private User getUser(DBObject user) {
        ObjectId id = (ObjectId) user.get("_id");
        String name = (String) user.get("name");
        String email = (String) user.get("email");
        String password = (String) user.get("password");
        String role = (String) user.get("role");
        String username = (String) user.get("username");
        String phone = (String) user.get("phone");
        String address = (String) user.get("address");
        return new User(id, name, email, password, role, username, phone, address);
    }


    private String makeSession(String email) {
        String salt = "98qw23kjk23jkl";
        String key = getKey(email + new Date().getTime() + salt);
        sessionMap.put(key, new Session(null, email, key));
        /*
        DBCollection collection = db.getCollection(SESSION);
        BasicDBObject session = new BasicDBObject();
        session.put("jwt", key);
        collection.insert(session);
        session.put("user_id", email);
        */
        return key;
    }

    public static String getKey(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
