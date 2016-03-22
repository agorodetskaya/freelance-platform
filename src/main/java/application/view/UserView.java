package application.view;

import application.model.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


public class UserView {
    private long id;
    @NotEmpty(message = "* заполните поле")
    @Length(min = 4, max = 20, message = "* от 4 до 20 символов")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9-_.\\s]*", message = "* логин не верный")
    protected String login;
    @NotEmpty(message = "* заполните поле")
    @Email
    protected String email;
    @NotEmpty(message = "* заполните поле")
    @Length(min = 2, max = 100, message = "* от 2 до 100 символов")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9-\\s]*", message = "* имя не верный")
    protected String firstName;
    @NotEmpty(message = "* заполните поле")
    @Length(min = 2, max = 100, message = "* от 2 до 100 символов")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9-\\s]*", message = "* фамилия не верный")
    protected String lastName;
    @Min(value = 1, message = "* возраст не менее 1")
    @Max(value = 150, message = "* возраст не более 150")
    protected int age;
    protected String roleName;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
