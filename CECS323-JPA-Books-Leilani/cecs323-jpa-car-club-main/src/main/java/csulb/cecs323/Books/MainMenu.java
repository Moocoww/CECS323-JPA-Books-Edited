
package csulb.cecs323.Books;

// Import all of the entity classes that we have written for this application.
//import csulb.cecs323.model.*;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
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
     *
     * @param manager The EntityManager that we will use.
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

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
//      // Create the list of owners in the database.
//      carclub.createEntity (owners);

        WritingGroups wg1 = new WritingGroups("shakespearefans@wg.com", "Shakespeare Fans", "Writing Group", "Shakespeare", 2022);
        WritingGroups wg2 = new WritingGroups("candyland@wg.com", "Candy Land", "Writing Group", "Candy", 2019);

        //individual author objects
        IndividualAuthors ia1 = new IndividualAuthors("georgeorwell@company.com", "George Orwell", "Individual Author");
        IndividualAuthors ia2 = new IndividualAuthors("mehrsabar@company.com", "Mehrsa Baradaran", "Individual Author");

        //add hoc teams objects
        AdHocTeams adt1 = new AdHocTeams("companyname@company.com", "Pearsons Teams", "Ad Hoc Team");
        AdHocTeams adt2 = new AdHocTeams("anotherco@company.com", "Some Other Team", "Ad Hoc Team");

        //ad hoc team members objects
        AdHocTeamMembers adtm1 = new AdHocTeamMembers(adt1, ia1);
        AdHocTeamMembers adtm2 = new AdHocTeamMembers(adt1, ia2);

        //publisher objects
        Publishers p1 = new Publishers("Oxford Publishers", "800-855-1234", "oxfordpublishers@oxford.com");
        Publishers p2 = new Publishers("Monkeys", "800-900-9999", "monkey@company.com");

        //book objects
        Books b1 = new Books("123456", "Animal Farm", p1, wg1, 1999);
        Books b2 = new Books("123450", "Animals Run", p1, ia1, 1990);

//        List<WritingGroups> wgs = new ArrayList<>();
//        wgs.add(wg1);
//        wgs.add(wg2);

        Scanner scnr = new Scanner(System.in);
        int menuOption = -1;
        boolean menuDone = false;
        boolean validMenuOption = false;
        List<Publishers> totalPublishers = new ArrayList<Publishers>();
        totalPublishers.add(p1);
        totalPublishers.add(p2);
        List<Books> totalBooks = new ArrayList<Books>();
        totalBooks.add(b1);
        totalBooks.add(b2);
        List<AuthoringEntities> totalAuthoringEntities = new ArrayList<AuthoringEntities>();
        List<IndividualAuthors> totalIndividualAuthors = new ArrayList<IndividualAuthors>();
        totalIndividualAuthors.add(ia1);
        totalIndividualAuthors.add(ia2);
        List<AdHocTeams> totalAdHocTeams = new ArrayList<AdHocTeams>();
        totalAdHocTeams.add(adt1);
        totalAdHocTeams.add(adt2);
        List<WritingGroups> totalWritingGroups = new ArrayList<WritingGroups>();
        totalWritingGroups.add(wg1);
        totalWritingGroups.add(wg2);
        List<AdHocTeamMembers> totalMembers = new ArrayList<AdHocTeamMembers>();
        totalMembers.add(adtm1);
        totalMembers.add(adtm2);

        //add all the author groups into Authoring Entities
        totalAuthoringEntities.add(ia1); //add individual author
        totalAuthoringEntities.add(ia2); //add individual author

        totalAuthoringEntities.add(wg1); //add writing group
        totalAuthoringEntities.add(wg2); //add writing group

        totalAuthoringEntities.add(adt1); //add adhocteam
        totalAuthoringEntities.add(adt2); //add adhocteam


        //create entity for database
//        books.createEntity(totalPublishers); // create the list of publishers in the database
//        books.createEntity(totalBooks);
//        books.createEntity(totalAuthoringEntities);
//        books.createEntity(totalIndividualAuthors);
//        books.createEntity(totalAdHocTeams);
//        books.createEntity(totalWritingGroups);
//        books.createEntity(totalMembers);


        while (!menuDone) {
            String menu = "-- Main Menu --" + "\nEnter an option: " + "\n1. Add an Authoring Entity"
                    + "\n2. Add Publisher" + "\n3. Add Book" + "\n4. List specific publisher information" +
                    "\n5. List specific book information" + "\n6. List specific writing group information" +
                    "\n7. Update a book" + "\n8. Delete a book" + "\n9. List primary key information"+ "\n10. Quit";
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
//                    String aeType = "";
//                    String aeName = "";
//                    String aeEmail = "";
//                    String headWriterName= "";
//                    int yearFormed;
//                    String addToTeam = "";
//
//                    scnr.nextLine();
//                    System.out.println("Enter an Authoring Entity Type (Writing Group, Individual Author or Ad Hoc Team): ");
//
//                    //check for valid authoring entity type
//                    boolean valid = false;
//                    while (!valid) {
//                        aeType = scnr.nextLine();
//                        if (aeType.equalsIgnoreCase("Individual Author")) {
//                            System.out.println("Enter an Individual Author name: ");
//                            aeName = scnr.nextLine();
//                            System.out.println("Enter an Individual Author email: ");
//                            aeEmail = scnr.nextLine();
//
//                            //check for new valid author email
//                            IndividualAuthors someAuthor = new IndividualAuthors();
//                            if (getValidAuthorEmail(aeEmail, totalAuthoringEntities) == true) {
//                                System.out.println("Email is good");
//                                someAuthor.setEmail(aeEmail);
//                                someAuthor.setName(aeName);
//                                someAuthor.setAuthoring_entity_type(aeType);
//                                totalAuthoringEntities.add(someAuthor);
//                                totalIndividualAuthors.add(someAuthor);
//                            }
//
//                            // iv. Add an Individual Author to an existing Ad Hoc Team
//                            boolean doneAdding = false;
//                            while (!doneAdding) {
//                                System.out.println("Would you like to add an Individual Author to an existing Ad Hoc Team? (y/n): ");
//                                addToTeam = scnr.nextLine();
//                                if (addToTeam.equalsIgnoreCase("y")) {
//                                    AdHocTeamMembers newMember = new AdHocTeamMembers();
//
//                                    //display all existing ad hoc teams
//                                    MainMenu.showAllAdHocTeam(totalAdHocTeams);
//
//                                    System.out.println("Enter Ad Hoc Team email: ");
//                                    String teamEmail = "";
//
//                                    checkAdHocEmail(newMember,totalAdHocTeams);
//                                    newMember.setIndividualAuthor(someAuthor);
//                                    totalMembers.add(newMember); //adding to junction table list
//                                    doneAdding = true;
//
//                                    //check if ad hoc team email is valid
////                                    boolean endList = false;
////                                    while(!endList){
////                                        teamEmail = scnr.nextLine();
////                                        for (int i = 0; i < totalAdHocTeams.size(); i++) {
////                                            if (totalAdHocTeams.get(i).getEmail().equals(teamEmail)) {
////                                                //System.out.println("Does it enter here?");
////
////                                                AdHocTeams existingAdHoc = totalAdHocTeams.get(i);
////                                                newMember.setAdhocteam(existingAdHoc);
////                                                newMember.setIndividualAuthor(someAuthor);
////                                                totalMembers.add(newMember); //adding to junction table list
////                                                endList = true;
////                                                doneAdding = true;
////                                            }
////                                        }
////                                        if (endList == false) {
////                                            System.out.println("Try again. Enter a valid email above.");
////
////                                        }
////                                    }
//                                }
//                                else if (addToTeam.equalsIgnoreCase("n")){
//                                    doneAdding = true;
//                                }
//                                else{
//                                    System.out.println("Invalid option. Please enter y/n. ");//whatever
//                                }
//                            }
//                            valid = true; //
//                        }
//                        else if (aeType.equalsIgnoreCase("Ad Hoc Team")) {
//                            System.out.println("Enter an Ad Hoc Team name: ");
//                            aeName = scnr.nextLine();
//                            System.out.println("Enter an Ad Hoc Team email: ");
//                            aeEmail = scnr.nextLine();
//
//                            AdHocTeams someTeam = new AdHocTeams();
//                            //check if email already exists
//                            if (getValidAuthorEmail(aeEmail, totalAuthoringEntities) == true) {
//                                System.out.println("Email is good");
//                                someTeam.setEmail(aeEmail);
//                                someTeam.setName(aeName);
//                                someTeam.setAuthoring_entity_type(aeType);
//                                totalAuthoringEntities.add(someTeam);
//                                totalAdHocTeams.add(someTeam);
//                            }
//                            valid = true;
//
//                        } else if (aeType.equalsIgnoreCase("Writing Group")) {
//                            System.out.println("Enter Writing Group name: ");
//                            aeName = scnr.nextLine();
//                            System.out.println("Enter Writing Group email: ");
//                            aeEmail = scnr.nextLine();
//                            //check if email already exists
//                            WritingGroups someTeam = new WritingGroups();
//                            if (getValidAuthorEmail(aeEmail, totalAuthoringEntities) == true) {
//                                System.out.println("Email is good");
//                                someTeam.setEmail(aeEmail);
//                                someTeam.setName(aeName);
//                                someTeam.setAuthoring_entity_type(aeType);
////                                totalAuthoringEntities.add(someTeam);
////                                totalWritingGroups.add(someTeam);
//                            }
//                            System.out.println("Enter Head writer name: ");
//                            headWriterName = scnr.nextLine();
//                            System.out.println("Enter year formed: ");
//                            yearFormed = scnr.nextInt();
//                            //WritingGroups wg = new WritingGroups(aeEmail, aeName, aeType, headWriterName, yearFormed);
//                            someTeam.setHead_writer(headWriterName);
//                            someTeam.setYear_formed(yearFormed);
//                            totalAuthoringEntities.add(someTeam);
//                            totalWritingGroups.add(someTeam);
//                            valid = true;
//                        }
//                        //all inputs are invalid. re-enter the correct Authoring Entity type.
//                        else {
//                            System.out.println("Invalid input. Re-enter (Writing Group, Individual Author or Ad Hoc Team):");
//                            valid = false;
//                        }
//                    }

                    //new function call test...
                    addAuthoringEntity(totalIndividualAuthors, totalAdHocTeams, totalWritingGroups, totalMembers, totalAuthoringEntities);
                    validMenuOption = false;
                    break;

                //GOOD
                case 2:
                    // Adding new Publisher
                    String pubName = checkPublisherName(totalPublishers);
                    System.out.println("pubName: " + pubName);
//                    boolean validPubName = false;
//                    String pubName = "";
//                    System.out.println("Enter Publisher name:");
//                    scnr.nextLine();
//                    boolean done = false;
//                    while (!validPubName) {
//                        pubName = scnr.nextLine();
//
//                        //checking if name already exists
//                        //boolean done = false;
//                        while (!done) {
//                            for (int i = 0; i < totalPublishers.size(); i++) {
//                                if (totalPublishers.get(i).getName().equalsIgnoreCase(pubName)) {
//                                    System.out.println("That Publisher already exists! Re-enter a new Publisher name.");
//                                    validPubName = false;
//                                    done = false;
//                                }
//
//                            }
//
//                            if (done == true){
//                                validPubName = true;
//                            }
//                        }
//                    }
                    scnr.nextLine();
                    System.out.println("Enter Publisher phone:");
                    String pubPhone = scnr.nextLine();


                    System.out.println("Enter Publisher email:");
                    String pubEmail = scnr.nextLine();

                    Publishers p = new Publishers(pubName, pubPhone, pubEmail);
                    totalPublishers.add(p);

                    validMenuOption = false;
                    break;
                case 3:
//                    // Adding new Book
                    Publishers bookPublisher = new Publishers();
                    String ISBN = checkISBN(totalBooks);
//                    boolean validBookISBN = false;
//                    String ISBN = "";
//
//                    System.out.println("Enter Book's ISBN:");
//                    scnr.nextLine(); //clear buffer
//
//                    while (!validBookISBN) {
//                        ISBN = scnr.nextLine();
//
//                        //checking if book already exists
//                        boolean donedone = false;
//                        while (!donedone) {
//                            for (int i = 0; i < totalBooks.size(); i++) {
//                                if (totalBooks.get(i).getISBN().equals(ISBN)) {
//                                    System.out.println("That Book already exists! Re-enter a new Book ISBN.");
//                                    validBookISBN= false;
//                                    donedone = true;
//                                }
//                                else{
//                                    donedone = true;
//                                    validBookISBN = true;
//                                }
//                            }
//                        }
//                    }
                    scnr.nextLine();
                    System.out.println("Enter Book's title: ");
                    String bookTitle = scnr.nextLine();

//                    System.out.println("Enter Book's publisher name: ");
//                    String bookPub = scnr.nextLine();

                    //check for existing book publisher name
                    //call case 2
                    String publisherName = checkPublisherName(totalPublishers);

                    System.out.println("Enter Publisher phone:");
                    pubPhone = scnr.nextLine();
                    System.out.println("Enter Publisher email:");
                    pubEmail = scnr.nextLine();

                    bookPublisher = new Publishers(publisherName, pubPhone, pubEmail);
                    totalPublishers.add(bookPublisher);


//                    Publishers bookPublisher = new Publishers();
//
//                    boolean pubFound = false;
//                    for (int i = 0; i < totalPublishers.size(); i++) {
//                        // publisher already exists
//                        if(totalPublishers.get(i).getName().equalsIgnoreCase(bookPub)){
//                            bookPublisher = totalPublishers.get(i);
//                            pubFound = true;
//                        }
//                        //publisher doesnt exist yet
//                        else {
//                            pubFound = false;
//                        }
//                    }
                    // publisher doesn't already exist, needs to be created first
//                    if (!pubFound) {
//                        System.out.println("Publisher doesn't already exist, publisher created!");
//
//                        System.out.println("Enter Publisher phone:");
//                        pubPhone = scnr.nextLine();
//
//                        System.out.println("Enter Publisher email:");
//                        pubEmail = scnr.nextLine();
//
//                        bookPublisher = new Publishers(bookPub, pubPhone, pubEmail);
//                        totalPublishers.add(bookPublisher);
//                    }
                    System.out.println("Enter Book's authoring entity name: ");
                    String bookAuth = scnr.nextLine();
                    AuthoringEntities bookAuthEntity = new AuthoringEntities();

                    boolean aeFound = false;


                    for (int i = 0; i < totalAuthoringEntities.size(); i++) {
                        // Authoring Entity already exists
                        if (totalAuthoringEntities.get(i).getName().equalsIgnoreCase(bookAuth)) {
                            bookAuthEntity = totalAuthoringEntities.get(i);
                            aeFound = true;
                        }
                        // Authoring Entity doesn't exist yet
                        else {
                            aeFound = false;
                        }

                    }
                    // FIXME: Authoring Entity doesn't already exist, needs to be created first
                    //create function from case 1
                    if (!aeFound) {
                        System.out.println("Authoring Entity doesn't already exist, creating Authoring Entity first");
                        // copy & paste code in case 1
                        // make sure to add auth entity to book object declared on line 430

                    }

                    System.out.println("Enter Book's year published: ");
                    int bookYear = getInt();

                    // FIXME: need to add book to db
                    //Books b = new Books(ISBN, bookTitle, bookPublisher, bookAuthEntity, bookYear);
                    //totalBooks.add(b);

                    validMenuOption = false;
                    //scnr.nextLine(); // this might be an extra line, possibly delete?
                    break;
                case 4:
                    //List specific publisher information
                    list_publisher_info(totalPublishers);
                    validMenuOption = false;

                    break;
                case 5:
                    // List specific book information
                    display_books(totalBooks);
                    validMenuOption = false;

                    break;
                case 6:
                    // List specific writing group information
                    list_writing_group_info(totalWritingGroups);
                    validMenuOption = false;

                    break;
                case 7:
                    // Update a Book – Change the authoring entity for an existing book.
                    MainMenu.update_book(totalBooks);
                    validMenuOption = false;
                    break;

               //GOOD
                case 8:
                    //delete a book
                    MainMenu.delete_book(totalBooks);
                    validMenuOption = false;
                    break;
                case 9:
                    //List primary key information for publishers, books, and Authoring Entities
                    String menuselect = ("Select a number below: "+
                            "1.\tList primary key for Publishers"+
                            "2.\tList primary for Books"+
                            "3.\tList primary key for Authoring Entities"

                    );
                    System.out.println(menuselect);

                    int pk = scnr.nextInt();
                    if (pk == 1){

                    }
                    else if (pk == 2){

                    }
                    else if (pk == 3){

                    }
                    else{
                        System.out.println("Invalid input. " + menuselect);
                    }

                case 10:
                    //quitting
                    System.out.println("Quitting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Re-enter a valid option.");
                    validMenuOption = false;
                    break;
            }
        }


        // Commit the changes so that the new data persists and is visible to other users.
        tx.commit();
        LOGGER.fine("End of Transaction");

    } // End of the main method

    /**
     * Create and persist a list of objects to the database.
     *
     * @param entities The list of entities to persist.  These can be any object that has been
     *                 properly annotated in JPA and marked as "persistable."  I specifically
     *                 used a Java generic so that I did not have to write this over and over.
     */
    public <E> void createEntity(List<E> entities) {
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

//    /**
//     * Think of this as a simple map from a String to an instance of auto_body_styles that has the
//     * same name, as the string that you pass in.  To create a new Cars instance, you need to pass
//     * in an instance of auto_body_styles to satisfy the foreign key constraint, not just a string
//     * representing the name of the style.
//     * @param
//     * @return The auto_body_styles instance corresponding to that style name.
//     */
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
    
    public static void addAuthoringEntity(List<IndividualAuthors> totalIA, List<AdHocTeams> totalAHT, List<WritingGroups> totalWG, List<AdHocTeamMembers> totalMems, List<AuthoringEntities> totalAE) {
        //copy and paste all of code in case 1 to reuse in update_book
        Scanner scnr = new Scanner(System.in);
        String aeType = "";
        String aeName = "";
        String aeEmail = "";
        String headWriterName= "";
        int yearFormed;
        String addToTeam = "";

        //scnr.nextLine();
        System.out.println("Enter an Authoring Entity Type (Writing Group, Individual Author or Ad Hoc Team): ");

        //check for valid authoring entity type
        boolean valid = false;
        while (!valid) {
            aeType = scnr.nextLine();
            if (aeType.equalsIgnoreCase("Individual Author")) {
                System.out.println("Enter an Individual Author name: ");
                aeName = scnr.nextLine();
                System.out.println("Enter an Individual Author email: ");
                aeEmail = scnr.nextLine();

                //check for new valid author email
                IndividualAuthors someAuthor = new IndividualAuthors();
                if (getValidAuthorEmail(aeEmail, totalAE) == true) {
                    System.out.println("Email is good");
                    someAuthor.setEmail(aeEmail);
                    someAuthor.setName(aeName);
                    someAuthor.setAuthoring_entity_type(aeType);
                    totalAE.add(someAuthor);
                    totalIA.add(someAuthor);
                }

                // iv. Add an Individual Author to an existing Ad Hoc Team
                boolean doneAdding = false;
                while (!doneAdding) {
                    System.out.println("Would you like to add an Individual Author to an existing Ad Hoc Team? (y/n): ");
                    addToTeam = scnr.nextLine();
                    if (addToTeam.equalsIgnoreCase("y")) {
                        AdHocTeamMembers newMember = new AdHocTeamMembers();

                        //display all existing ad hoc teams
                        MainMenu.showAllAdHocTeam(totalAHT);


                        String teamEmail = "";

                        checkAdHocEmail(newMember,totalAHT);
                        newMember.setIndividualAuthor(someAuthor);
                        totalMems.add(newMember); //adding to junction table list
                        doneAdding = true;

                        //check if ad hoc team email is valid
//                                    boolean endList = false;
//                                    while(!endList){
//                                        teamEmail = scnr.nextLine();
//                                        for (int i = 0; i < totalAdHocTeams.size(); i++) {
//                                            if (totalAdHocTeams.get(i).getEmail().equals(teamEmail)) {
//                                                //System.out.println("Does it enter here?");
//
//                                                AdHocTeams existingAdHoc = totalAdHocTeams.get(i);
//                                                newMember.setAdhocteam(existingAdHoc);
//                                                newMember.setIndividualAuthor(someAuthor);
//                                                totalMembers.add(newMember); //adding to junction table list
//                                                endList = true;
//                                                doneAdding = true;
//                                            }
//                                        }
//                                        if (endList == false) {
//                                            System.out.println("Try again. Enter a valid email above.");
//
//                                        }
//                                    }
                    }
                    else if (addToTeam.equalsIgnoreCase("n")){
                        doneAdding = true;
                    }
                    else{
                        System.out.println("Invalid option. Please enter y/n. ");//whatever
                    }
                }
                valid = true; //
            }
            else if (aeType.equalsIgnoreCase("Ad Hoc Team")) {
                System.out.println("Enter an Ad Hoc Team name: ");
                aeName = scnr.nextLine();
                System.out.println("Enter an Ad Hoc Team email: ");
                aeEmail = scnr.nextLine();

                AdHocTeams someTeam = new AdHocTeams();
                //check if email already exists
                if (getValidAuthorEmail(aeEmail, totalAE) == true) {
                    System.out.println("Email is good");
                    someTeam.setEmail(aeEmail);
                    someTeam.setName(aeName);
                    someTeam.setAuthoring_entity_type(aeType);
                    totalAE.add(someTeam);
                    totalAHT.add(someTeam);
                }
                valid = true;

            } else if (aeType.equalsIgnoreCase("Writing Group")) {
                System.out.println("Enter Writing Group name: ");
                aeName = scnr.nextLine();
                System.out.println("Enter Writing Group email: ");
                aeEmail = scnr.nextLine();
                //check if email already exists
                WritingGroups someTeam = new WritingGroups();
                if (getValidAuthorEmail(aeEmail, totalAE) == true) {
                    System.out.println("Email is good");
                    someTeam.setEmail(aeEmail);
                    someTeam.setName(aeName);
                    someTeam.setAuthoring_entity_type(aeType);
//                                totalAuthoringEntities.add(someTeam);
//                                totalWritingGroups.add(someTeam);
                }
                System.out.println("Enter Head writer name: ");
                headWriterName = scnr.nextLine();
                System.out.println("Enter year formed: ");
                yearFormed = scnr.nextInt();
                //WritingGroups wg = new WritingGroups(aeEmail, aeName, aeType, headWriterName, yearFormed);
                someTeam.setHead_writer(headWriterName);
                someTeam.setYear_formed(yearFormed);
                totalAE.add(someTeam);
                totalWG.add(someTeam);
                valid = true;
            }
            //all inputs are invalid. re-enter the correct Authoring Entity type.
            else {
                System.out.println("Invalid input. Re-enter (Writing Group, Individual Author or Ad Hoc Team):");
                valid = false;
            }
        }
    }// end of addAuthoringEntity method


    public static String checkPublisherName(List<Publishers> totalPublishers){
        Scanner scnr = new Scanner(System.in);
        boolean validPubName = false;
        String pubName = "";
        System.out.println("Enter Publisher name:");

        boolean done = false;
        while (!validPubName) {
            pubName = scnr.nextLine();
            done = false;

            //checking if name already exists
            while (!done) {
                for (int i = 0; i < totalPublishers.size(); i++) {
                    if (totalPublishers.get(i).getName().equalsIgnoreCase(pubName)) {
                        System.out.println("That Publisher already exists! Re-enter a new Publisher name.");
                        validPubName = false;
                        done = true;
                    }

                }

                if (done == false){
                    done = true;
                    validPubName = true;

                }
            }
        }
        return pubName;
    } //end of checkPublisherName method


    public static String checkISBN(List<Books> totalBooks){

        Scanner scnr = new Scanner(System.in);
        boolean validBookISBN = false;
        String ISBN = "";
        boolean donedone = false;
        System.out.println("Enter Book's ISBN:");

        while (!validBookISBN) {
            ISBN = scnr.nextLine();
            donedone = false;
            //checking if book already exists

            while (!donedone) {
                for (int i = 0; i < totalBooks.size(); i++) {
                    if (totalBooks.get(i).getISBN().equals(ISBN)) {
                        System.out.println("That Book already exists! Re-enter a new Book ISBN.");
                        validBookISBN= false;
                        donedone = true;
                    }
//                    else{
//                        donedone = true;
//                        validBookISBN = true;
//                    }
                }
                if (donedone == false){
                    donedone = true;
                    validBookISBN = true;
                }
            }
        }
        return ISBN;
    }//end of checkISBN method


    public static void checkAdHocEmail(AdHocTeamMembers newMember, List<AdHocTeams> totalAdHocTeams){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Ad Hoc Team email: ");
        String teamEmail = "";

        //check if ad hoc team email is valid
        boolean endList = false;
        while(!endList){
            teamEmail = scnr.nextLine();
            for (int i = 0; i < totalAdHocTeams.size(); i++) {
                if (totalAdHocTeams.get(i).getEmail().equalsIgnoreCase(teamEmail)) {
                    AdHocTeams existingAdHoc = totalAdHocTeams.get(i);
                    newMember.setAdhocteam(existingAdHoc);
//                    newMember.setIndividualAuthor(someAuthor);
//                    totalMembers.add(newMember); //adding to junction table list
                    endList = true;
                   // doneAdding = true;
                }
            }
            if (endList == false) {
                System.out.println("Try again. Enter a valid email above.");

            }
        }
    }// end of checkAdHocEmail method

    public static void showAllAdHocTeam(List<AdHocTeams> allTeams){
        for (int i = 0; i < allTeams.size(); i++) {
            if (allTeams.size() == 0){
                System.out.println("No ad hoc teams available");
            }
            else{
                System.out.println(allTeams.get(i));
            }
        }
    } // End of showAllAdHocTeam method

    /**
     *
     */
    public static void display_books(List <Books> books) {

        System.out.println("-----Books Information----");
        if (books.size() == 0){
            System.out.println("No books available");
        }
        else{
            for (int i = 0; i < books.size(); i++){
                System.out.println((i+1) + ". \t" + books.get(i).getISBN()) ;
            }
        }
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Book ISBN:");
        String bookName = "";
        boolean done = false;
        boolean found = false;
        while (!done) {
            bookName = scnr.nextLine();
            for (int i = 0; i < books.size(); i++) {

                if (books.get(i).getISBN().equalsIgnoreCase(bookName)) {
                    System.out.println(books.get(i).toString());
                    found = true;
                    done = true;
                }
            }
            if (!found) {
                System.out.println("Invalid ISBN. Please Re-enter ISBN: ");
            }
        }

    }//end of display_books method


    /** This function shows the user a list of all available books so they can choose
     * which to delete from the database.
     *
     * @param books This is a list of books in the database.
     */
    public static void delete_book(List<Books> books) {
        Scanner scnr = new Scanner(System.in);
        String deleteISBN = "";

        //prints out all available books for user to see
        System.out.println("-----Books Information----");
        if (books.size() == 0){
            System.out.println("No books available.");
        }
        else{
            for (int i = 0; i < books.size(); i++){
                System.out.println((i+1) + ". \t" + books.get(i).getISBN()) ;

            }
            System.out.println("Enter the ISBN of the book you want to delete: ");
            deleteISBN = scnr.nextLine();

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getISBN().equals(deleteISBN)) {
                    books.remove(i);
                    System.out.println("Book successfully deleted.");
                }
            }
        }
    }// end of delete_book method

    // FIXME: use addAuthoringEntity() function
    public static void update_book(List<Books> books) {
        Scanner scnr = new Scanner(System.in);
        boolean validEntity = false;
        boolean validISBN = false;
        boolean flag = false;
        String findISBN = "";
        String updatedType = "";

        //prints out all available books for user to see
        System.out.println("-----Books Information----");
        if (books.size() == 0){
            System.out.println("No books available.");
        }
        else{
            for (int i = 0; i < books.size(); i++){
                System.out.println((i+1) + ". \t" + books.get(i).getISBN()) ;
            }
        }

        //making sure user enters valid isbn
        while(!validISBN) {
            System.out.println("Enter the ISBN of the book you want to update: ");
            findISBN = scnr.nextLine();

            for (int i = 0;  i < books.size(); i++) {
                if (books.get(i).getISBN().equals(findISBN)) {
                    //valid input!
                    flag = true;
                    validISBN = true;
                }
            }
            //invalid input! if not found in booklist, prompts user to reenter an isbn
            if (flag == false) {
                System.out.println("Invalid ISBN.");
            }
        }

        while (!validEntity) {
            System.out.println("Enter the Authoring Entity Type (Writing Group, Individual Author, Ad Hoc Team): ");
            updatedType = scnr.nextLine();
            // can reuse all the code in case 1, aka just call the function here.
            //addAuthoringEntity(totalIndividualAuthors, totalAdHocTeams, totalWritingGroups, totalMembers, totalAuthoringEntities);
        }
    }// end of update_book method

    /**
     * @return
     */
    public static int getInt() {
        Scanner in = new Scanner(System.in);
        int input = 0;
        boolean valid = false;
        while (!valid) {
            if (in.hasNextInt()) {
                input = in.nextInt();
                valid = true;
            } else {
                in.next(); //clear invalid string
                System.out.println("Invalid Input.");
            }
        }
        return input;
    }// end of getInt method

    public static void list_publisher_info(List<Publishers> publishers) {
        if (publishers.size() == 0) {
            System.out.println("No Publishers available.");
        }
        else {
            System.out.println("-----Publisher Information----");
            for (int i = 0; i < publishers.size(); i++) {
                System.out.println((i+1) + ". \t" + publishers.get(i).getName());
            }
        }

        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a Publisher Name:");
        String pubName = "";
        boolean validInput = false;

        while (!validInput) {
            pubName = scnr.nextLine();
            boolean found = false;
            for (int i = 0; i < publishers.size(); i++) {
                if (publishers.get(i).getName().equalsIgnoreCase(pubName)) {
                    System.out.println(publishers.get(i).toString());
                    found = true;
                    validInput = true;
                }
            }
            if (!found) {
                System.out.println("Publisher not found. Re-enter Publisher name: ");
            }
        }
    } //end of list_publisher_info method

    /**
     *
     * @param wg
     */
    public static void list_writing_group_info(List<WritingGroups> wg) {
        if (wg.size() == 0) {
            System.out.println("No Writing Group available.");
        }
        else {
            System.out.println("-----Writing Group Information----");
            for (int i = 0; i < wg.size(); i++) {
                System.out.println((i+1) + ". \t" + wg.get(i).getName());
            }
        }

        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a Writing Group Name:");
        String pubName = "";
        boolean validInput = false;

        while (!validInput) {
            pubName = scnr.nextLine();
            boolean found = false;
            for (int i = 0; i < wg.size(); i++) {
                if (wg.get(i).getName().equalsIgnoreCase(pubName)) {
                    System.out.println(wg.get(i).toString());
                    found = true;
                    validInput = true;
                }
            }

            if (!found) {
                System.out.println("Writing Group not found. Re-enter Writing Group name: ");
            }
        }
    }//end of list_writing_group_info method

    /**
     * Checks if user's input for author's email is valid.
     * @param email if email exists in database
     * @param author if author email already exists within the database
     * @return true if the email is valid
     */
    public static boolean getValidAuthorEmail(String email, List<AuthoringEntities> author){
        Scanner scnr = new Scanner(System.in);
        String email2 = email;
        boolean validInput = false;
        boolean done = true;
        boolean found =false;
        while (!found){
            while (!validInput) {
                for (int i = 0; i < author.size(); i++) {
                    if (author.get(i).getEmail().equalsIgnoreCase(email2)) {
                        System.out.println("Email already exist. Re-enter another email.");
                        found = false;
                        done = false;
                    }

                }
                if (done == true){
                    validInput = true;
                    found = true;
                }
                else {
                    validInput = false;
                    done = true;
                    email2 = scnr.nextLine();
                }
            }
        }
        if (found){
            return true;
        }
        return false;

    } //end of checkEmail method

} //end of main

