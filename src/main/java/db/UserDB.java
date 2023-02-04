package db;

import jakarta.ws.rs.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class UserDB {

    private Map<String, String> users = new HashMap<>();

    private static UserDB theInstance;

    public static UserDB getTheInstance(){
        if(theInstance == null){
            theInstance = new UserDB();
        }
        return theInstance;
    }

    private UserDB(){
        users.put("user1Mail.com", "pass1");
        users.put("User2Mail.com", "pass2");
    }

    public String getPassword(String mail){
        if(!users.containsKey(mail)){
            throw new NotFoundException("user was not found");
        }
        return users.get(mail);
    }
}
