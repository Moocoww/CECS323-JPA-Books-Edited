package csulb.cecs323.Books;
import java.util.Set;
import javax.persistence.*;

@Entity
public class IndividualAuthors extends AuthoringEntities {



    @ManyToMany(targetEntity = AdHocTeams.class)
    private Set adhocTeam;


    public IndividualAuthors() {
        super();
    }

    public IndividualAuthors(String email, String name, String authoring_entity_type, Set adhocTeam) {
        super(email, name, authoring_entity_type);
        this.adhocTeam = adhocTeam;
    }

//    public Set getAdhocTeam() {
//        return adhocTeam;
//    }
//
//    public void setAdhocTeam(Set adhocTeam) {
//        this.adhocTeam = adhocTeam;
//    }
}
