package csulb.cecs323.Books;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class AdHocTeamMembers {

    @Id
    @ManyToOne
    private AdHocTeams adhocteam;

    @Id
    @ManyToOne
    private IndividualAuthors individualAuthor;

    public AdHocTeamMembers() {
    }

    public AdHocTeamMembers(AdHocTeams adhocteam, IndividualAuthors individualAuthor) {
        setAdhocteam(adhocteam);
        setIndividualAuthor(individualAuthor);

    }



    public AdHocTeams getAdhocteam() {
        return adhocteam;
    }

    public void setAdhocteam(AdHocTeams adhocteam) {
        this.adhocteam = adhocteam;
    }

    public IndividualAuthors getIndividualAuthor() {
        return individualAuthor;
    }

    public void setIndividualAuthor(IndividualAuthors individualAuthor) {
        this.individualAuthor = individualAuthor;
    }

    @Override
    public String toString() {
        return "AdHocTeamMembers: " +
                "AdHoc Team {" + adhocteam.getName() + "}" +
                " \t| Individual Author {" + individualAuthor.getName() + "}";
    }

}
