/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.Books;

// Import all of the entity classes that we have written for this application.
//import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */

public class MainMenu {
    /**
     * You will likely need the entityManager in a great many functions throughout your application.
     * Rather than make this a global variable, we will make it an instance variable within the CarClub
     * class, and create an instance of CarClub in the main.
     */
    private EntityManager entityManager;

    /**
     * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
     * We use it because it is easy to control how much or how little logging gets done without having to
     * go through the application and comment out/uncomment code and run the risk of introducing a bug.
     * Here also, we want to make sure that the one Logger instance is readily available throughout the
     * application, without resorting to creating a global variable.
     */
    private static final Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    /**
     * The constructor for the CarClub class.  All that it does is stash the provided EntityManager
     * for use later in the application.
     * @param manager    The EntityManager that we will use.
     */
    public MainMenu(EntityManager manager) {
        this.entityManager = manager;
    }

    public static void main(String[] args) {
        LOGGER.fine("Creating EntityManagerFactory and EntityManager");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MainMenu");
        EntityManager manager = factory.createEntityManager();
        // Create an instance of CarClub and store our new EntityManager as an instance variable.
        MainMenu books = new MainMenu(manager);
        Scanner scnr = new Scanner(System.in);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        // List of owners that I want to persist.  I could just as easily done this with the seed-data.sql
//      List <Owners> owners = new ArrayList<Owners>();
//      owners.add(new Owners("Reese", "Mike", "714-892-5544"));
//      owners.add(new Owners("Leck", "Carl", "714-321-3729"));
//      owners.add(new Owners("Guitierez", "Luis", "562-982-2899"));
//      // Create the list of owners in the database.
//      carclub.createEntity (owners);
        //what happened to git
        //System.out.println("Enter a new publisher: ");
        //List<WritingGroups>

        //AuthoringEntities ae = new AuthoringEntities("janedoe@gmail.com", "Jane Doe", "Literature");  //cannot instantiate base class
//        ae = new IndividualAuthors();

        WritingGroups wg1 = new WritingGroups("shakespearefans@wg.com", "Shakespeare Fans", "Writing Group", "Shakespeare",2022);
        IndividualAuthors ia1 = new IndividualAuthors("georgeorwell@company.com", "George Orwell", "Individual Author");
        AdHocTeams adt1 = new AdHocTeams("companyname@company.com", "Pearsons Teams", "Ad Hoc Team");
        System.out.println(ia1);
        System.out.println(adt1);
        System.out.println(wg1);


        AdHocTeamMembers adtm1 = new AdHocTeamMembers(adt1, ia1);
        IndividualAuthors ia2 = new IndividualAuthors("mehrsabar@company.com", "Mehrsa Baradaran", "Individual Author");

        AdHocTeamMembers adtm2 = new AdHocTeamMembers(adt1, ia2);
        List <Books> booklist = new ArrayList<>();
        List<AdHocTeamMembers> totalmems = new ArrayList<AdHocTeamMembers>();
        totalmems.add(adtm1);
        totalmems.add(adtm2);
        System.out.println(totalmems);

//        System.out.println(adtm1);
//        System.out.println(adtm2);

        Publishers p1 = new Publishers("Oxford Publishers", "800-855-1234", "oxfordpublishers@oxford.com");
        Books b1 = new Books("123456", "Animal Farm", p1, wg1, 1999);
        System.out.println(p1);
        System.out.println(b1);


        Scanner scan = new Scanner(System.in);
        int menuOption = -1;
        boolean menuDone = false;
        boolean validMenuOption = false;
        List<Publishers> totalPublishers = new ArrayList<Publishers>();
        List<Books> totalBooks = new ArrayList<Books>();

        while (!menuDone) {
            String menu = "-- Main Menu --" + "\nEnter an option: "+ "\n1. Add an Authoring Entity"
                    + "\n2. Add Publisher" + "\n3. Add Book" + "\n4. Update a book" + "\n5. Delete a book" + "\n6. Quit";
            System.out.println(menu);
            while (!validMenuOption) {
                try {
                    menuOption = scnr.nextInt();
                    validMenuOption = true;

                } catch (Exception e) {
                    System.out.println("Re-enter a valid option.");
                    System.out.println(menu);
                    scnr.nextLine();
                }
            }

            switch (menuOption) {
                case 1:
                    //Authoring Entity
                    validMenuOption = false;
                    scnr.nextLine();
                    break;
                case 2:
                    // Adding Publisher
                    System.out.println("Enter Publisher name:");
                    String pubName = scnr.nextLine();

                    //check for existing publisher

                    System.out.println("Enter Publisher phone:");
                    String pubPhone = scnr.nextLine();

                    System.out.println("Enter Publisher email:");
                    String pubEmail = scnr.nextLine();

                    Publishers p = new Publishers(pubName, pubPhone, pubEmail);
                    totalPublishers.add(p);


                    validMenuOption = false;
                    scnr.nextLine(); //clearing buffer
                    break;
                case 3:
                    // Adding Book
                    System.out.println("Enter Book's ISBN:");
                    String ISBN = scnr.nextLine();
                    int returnISBN = getInt();//check for validity

                    System.out.println("Enter Book's title: ");
                    String bookTitle = scnr.nextLine();

                    System.out.println("Enter Book's publisher name: ");
                    String bookPub = scnr.nextLine();
                    for (int i = 0; i < totalPublishers.size(); i++) {
                        if(totalPublishers.get(i).getName().equals(bookPub)){
                            //publisher exists, can put it into constructor
                        }
                        else {
                            //publisher doesnt exist, need to make it and prompt user for pub info
                            System.out.println("");

                        }
                    }

                    System.out.println("Enter Book's authoring entity name: ");
                    String bookAuth = scnr.nextLine();

                    System.out.println("Enter Book's year published: ");
                    String bookYear = scnr.nextLine();
                    int returnYear = getInt();//check for validity

//                    Books b = new Books(returnISBN, bookTitle, , ,returnYear); // FIX ME
//                    totalBooks.add(b);

                    validMenuOption = false;
                    scnr.nextLine();
                    break;
                case 4:
                    //update a book


                    break;
                case 5:
                    //delete a book

                    break;
                case 6:
                    //quitting
                    System.out.println("Quitting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Re-enter a valid option.");
                    validMenuOption = false;
                    scnr.nextLine();
                    break;
            }
        }


        // Commit the changes so that the new data persists and is visible to other users.
        tx.commit();
        LOGGER.fine("End of Transaction");

    } // End of the main method

    /**
     * Create and persist a list of objects to the database.
     * @param entities   The list of entities to persist.  These can be any object that has been
     *                   properly annotated in JPA and marked as "persistable."  I specifically
     *                   used a Java generic so that I did not have to write this over and over.
     */
    public <E> void createEntity(List <E> entities) {
        for (E next : entities) {
            LOGGER.info("Persisting: " + next);
            // Use the CarClub entityManager instance variable to get our EntityManager.
            this.entityManager.persist(next);
        }

        // The auto generated ID (if present) is not passed in to the constructor since JPA will
        // generate a value.  So the previous for loop will not show a value for the ID.  But
        // now that the Entity has been persisted, JPA has generated the ID and filled that in.
        for (E next : entities) {
            LOGGER.info("Persisted object after flush (non-null id): " + next);
        }
    } // End of createEntity member method

    /**
     * Think of this as a simple map from a String to an instance of auto_body_styles that has the
     * same name, as the string that you pass in.  To create a new Cars instance, you need to pass
     * in an instance of auto_body_styles to satisfy the foreign key constraint, not just a string
     * representing the name of the style.
     * @param
     * @return           The auto_body_styles instance corresponding to that style name.
     */
//   public auto_body_styles getStyle (String name) {
//      // Run the native query that we defined in the auto_body_styles entity to find the right style.
//      List<auto_body_styles> styles = this.entityManager.createNamedQuery("ReturnAutoBodyStyle",
//              auto_body_styles.class).setParameter(1, name).getResultList();
//      if (styles.size() == 0) {
//         // Invalid style name passed in.
//         return null;
//      } else {
//         // Return the style object that they asked for.
//         return styles.get(0);
//      }
//   }// End of the getStyle method
} // End of CarClub class


