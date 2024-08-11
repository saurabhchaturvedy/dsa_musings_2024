package DesignPatterns.Creational.Builder.Pattern1;

public class User {


    private String firstName;
    private String lastName;
    private int age;
    private int phone;
    private String address;


    public User(UserBuilder userBuilder) {
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.age = userBuilder.age;
        this.phone = userBuilder.phone;
        this.address = userBuilder.address;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int age() {
        return age;
    }

    public int getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


    public String toString() {

        return " User : " + " " + " First Name : " + firstName + " Last name : " + lastName + " age : " + age + " phone : " + phone + " address : " + address;
    }


    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private int age;
        private int phone;
        private String address;


        UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }


        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }


        public UserBuilder secondName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }


        public UserBuilder phone(int phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            User user = new User(this);
            return user;
        }
    }
}