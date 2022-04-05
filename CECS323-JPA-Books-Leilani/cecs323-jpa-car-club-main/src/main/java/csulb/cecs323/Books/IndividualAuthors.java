package csulb.cecs323.Books;
import java.util.Set;
import javax.persistence.*;

@Entity
public class IndividualAuthors extends AuthoringEntities {

//    @ManyToMany(targetEntity = AdHocTeams.class)
//    private Set adhocTeam;

    @ManyToMany (mappedBy = "author")
    Set<AdHocTeams> team;

    public IndividualAuthors() {
        super();
    }

    public IndividualAuthors(String email, String name, String ae) {
        super(email, name, ae);
        //this.adhocTeam = adhocTeam;
    }

//    public Set getAdhocTeam() {
//        return adhocTeam;
//    }
//
//    public void setAdhocTeam(Set adhocTeam) {
//        this.adhocTeam = adhocTeam;
//    }
}
