package csulb.cecs323.Books;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Publishers {

    @Id
    @Column(nullable=false, length = 80)
    private String name;

    @Column(nullable=false, length = 80)
    private String email;

    @Column(nullable=false, length = 24)
    private String phone;


    public Publishers() {
    }

    public Publishers(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "Author Name: " + this.name + "Email: " + this.email + "Phone: " + this.phone;
    }

    @Override
    public boolean equals (Object o) {
        Publishers publisher = (Publishers) o;
        return this.getName() == publisher.getName();
    }
    @Override
    public int hashCode(){return Objects.hash(this.getName(), this.getEmail(),this.getPhone());}

}
