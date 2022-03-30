package csulb.cecs323.Books;
import javax.persistence.*;
import java.util.Set;

@Entity
public class AdHocTeams extends AuthoringEntities{


    @ManyToMany(targetEntity = IndividualAuthors.class)
    private Set adhoc;

    public AdHocTeams() {
        super();
    }


    public AdHocTeams(String email, String name, String authoring_entity_type, Set adhoc) {
        super(email,name,authoring_entity_type );
        this.adhoc = adhoc;
    }


    public Set getAdhoc() {
        return adhoc;
    }

    public void setAdhoc(Set adhoc) {
        this.adhoc = adhoc;
    }



}
