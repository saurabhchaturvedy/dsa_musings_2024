package LLD.LLD002.SplitWise;

class User {
    private String userId;
    private String name;
    private String email;

    // Constructor
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "userId='" + userId + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
