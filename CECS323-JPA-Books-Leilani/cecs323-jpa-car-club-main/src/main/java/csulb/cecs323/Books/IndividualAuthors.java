package csulb.cecs323.Books;
import java.util.Set;
import javax.persistence.*;

@Entity
public class IndividualAuthors extends AuthoringEntities {

    @ManyToMany (mappedBy = "author")
    Set<AdHocTeams> team;

    public IndividualAuthors() {
        super();
    }

    public IndividualAuthors(String email, String name, String ae) {
        super(email, name, ae);
        //this.adhocTeam = adhocTeam;
    }

}
