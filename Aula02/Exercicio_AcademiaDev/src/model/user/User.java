package model.user;

import util.IdGenerator;

public abstract class User {
    private final int id;
    private String name;
    private String email;
    
    public User(String name, String email) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be blank.");

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("E-mail cannot be blank.");

        this.id = IdGenerator.generate(User.class);
        this.name = name;
        this.email = email;
    }
    
    public int getId() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public abstract String getUserRole();
}
