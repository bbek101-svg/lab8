/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dataaccess.UserDB;
import models.User;

/**
 *
 * @author admin
 */
public class UserService {
    private UserDB userDB = new UserDB();
    
    public User get(String email) throws Exception {
        User user = this.userDB.get(email);
        return user;
    }
}
