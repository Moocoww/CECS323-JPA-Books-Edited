package csulb.cecs323.Books;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AuthoringEntities {
    @Id
    @Column(nullable=false, length=80)
    protected String email;

    @Column(nullable=false, length=80)
    protected String name;

    @Column(nullable=false, length=80)
    private String authoring_entity_type;

    public AuthoringEntities() {
    }

    public AuthoringEntities(String email, String name, String authoring_entity_type) {
        this.email = email;
        this.name = name;
        this.authoring_entity_type = authoring_entity_type;
    }

    @Override
    public String toString(){
        return "Email: " + this.email + "\t| Name: " + this.name + "\t| Authoring Entity Type: " + this.authoring_entity_type;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthoring_entity_type() {
        return authoring_entity_type;
    }

    public void setAuthoring_entity_type(String authoring_entity_type) {
        this.authoring_entity_type = authoring_entity_type;
    }

}
