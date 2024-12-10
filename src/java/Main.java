import java.io.File;
import java.util.*;

/**
 * A menu where the user can interact with the social media system with admin rights.
 *
 * @author Psy Cisneros
 * @author Aune Mitchell
 */
public class Main {

    private static Profile currentProfile;
    private static final ProfileManager profileManager = new ProfileManager();

    /**
     * Main method for running the program.
     *
     * @param args Arguments for the program, none expected.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the social media system manager!\n");
        System.out.println("You must first create your profile.");

        // Create a profile for the initial user
        Profile newUser = createProfile();

        // Add the initial user to the profile manager
        profileManager.addProfile(newUser);

        // Set the current user to the initial user
        currentProfile = newUser;

        // Display the instructions
        getMainInstructions();

        // Continually prompt for user choice for options until program exits
        boolean exit = false;
        while (!exit) {
            System.out.println("\nEnter 1 to repeat options for the main menu.");
            int userIntChoice = getUserIntInput(1, 11);

            // Call methods for corresponding user choice
            switch (userIntChoice) {
                case 1:
                    getMainInstructions();
                    break;
                case 2:
                    modifyProfile();
                    break;
                case 3:
                    viewCurrentProfile();
                    break;
                case 4:
                    viewAllProfiles();
                    break;
                case 5:
                    addAFriend();
                    break;
                case 6:
                    viewFriendList();
                    break;
                case 7:
                    viewFriendOfFriendList();
                    break;
                case 8:
                    deleteProfile();
                    break;
                case 9:
                    addNewProfile();
                    break;
                case 10:
                    switchCurrentUser();
                    break;
                case 11:
                    logout();
            }
        }
    }

    /**
     * Creates a profile.
     *
     * @return The created profile.
     */
    private static Profile createProfile() {
        System.out.println("Creating profile...");

        // Gets the name for the profile and creates the profile using that name
        String name = getNameInput();
        Profile profile = new Profile(name);
        System.out.println("Profile created. Welcome, " + name + "!");

        // Returning a new profile with the given name
        return profile;
    }

    /**
     * Displays the instructions for the main program.
     */
    private static void getMainInstructions() {
        System.out.println("\nMain program instructions:");
        System.out.println("Enter 1 to repeat these instructions.");
        System.out.println("Enter 2 to modify the current profile (" + currentProfile.getName() + ").");
        System.out.println("Enter 3 to view the current profile details.");
        System.out.println("Enter 4 to view all profiles.");
        System.out.println("Enter 5 to add a new friend for the current profile.");
        System.out.println("Enter 6 to view the current profile's friend list.");
        System.out.println("Enter 7 to view one of the current profile's friend's friend list.");
        System.out.println("Enter 8 to delete a profile.");
        System.out.println("Enter 9 to add another profile.");
        System.out.println("Enter 10 to switch the current user.");
        System.out.println("Enter 11 to logout (end program).");
    }

    /**
     * Gets a user integer choice from the provided inclusive range.
     *
     * @param rangeStart The start of the range.
     * @param rangeEnd   The end of the range.
     * @return The user's choice.
     */
    private static int getUserIntInput(int rangeStart, int rangeEnd) {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);

        // Initializes valid input as false and user choice to min int value
        boolean validInput = false;
        int userIntChoice = Integer.MIN_VALUE;

        // While the input is invalid
        while (!validInput) {
            System.out.println("Please enter your choice between "
                    + rangeStart + " and " + rangeEnd + ":");
            // Ensures input is an integer
            while (!scanner.hasNextInt()) {
                System.out.println(
                        "\nInput is not an integer. Please enter a number between "
                                + rangeStart + " and " + rangeEnd + " inclusive:");
                scanner.next();
            }
            // Ensures integer input is in the range
            userIntChoice = scanner.nextInt();
            if (userIntChoice >= rangeStart && userIntChoice <= rangeEnd) {
                validInput = true;
            } else {
                System.out.println(
                        "\nSelection is out of bounds. Please enter a number between "
                                + rangeStart + " and " + rangeEnd + "inclusive:");
            }
        }
        return userIntChoice;
    }

    /**
     * Displays the instructions for modifying the profile.
     */
    private static void getModifyProfileInstructions() {
        System.out.println("\nModifying profile options for current profile (" + currentProfile.getName() + "):");
        System.out.println("Enter 1 to repeat these instructions.");
        System.out.println("Enter 2 to set the name (must be at least 1 character).");
        System.out.println("Enter 3 to set the status.");
        System.out.println("Enter 4 to set the location.");
        System.out.println("Enter 5 to set the gender.");
        System.out.println("Enter 6 to set the relationship status.");
        System.out.println("Enter 7 to set the age (must be 22 or older).");
        System.out.println("Enter 8 to set the occupation.");
        System.out.println("Enter 9 to set the astrological sign.");
        System.out.println("Enter 10 to set the college.");
        System.out.println("Enter 11 to set the major.");
        System.out.println("Enter 12 to set the profile picture.");
        System.out.println("Enter 13 to return to the main menu.");
    }

    /**
     * Modifies the profile based on the user selection.
     */
    private static void modifyProfile() {
        // Display the instructions for modifying the profile
        getModifyProfileInstructions();

        // Continually prompt for user choice for options until user chooses to return to main menu
        boolean exit = false;
        while (!exit) {
            System.out.println("\nEnter 1 to repeat options for modifying the profile.");
            int userIntChoice = getUserIntInput(1, 13);

            // Actions for corresponding user choice
            switch (userIntChoice) {
                case 1:
                    getModifyProfileInstructions();
                    break;
                case 2:
                    String newName = getNameInput();
                    currentProfile.setName(newName);
                    System.out.println("Successfully set name to: " + newName);
                    break;
                case 3:
                    System.out.println("\nPlease enter the status:");
                    String newStatus = getStringInput();
                    currentProfile.setStatus(newStatus);
                    System.out.println("Successfully set status to: " + newStatus);
                    break;
                case 4:
                    System.out.println("\nPlease enter the location:");
                    String newLocation = getStringInput();
                    currentProfile.setLocation(newLocation);
                    System.out.println("Successfully set location to: " + newLocation);
                    break;
                case 5:
                    System.out.println("\nPlease enter the gender:");
                    String newGender = getStringInput();
                    currentProfile.setGender(newGender);
                    System.out.println("Successfully set gender to: " + newGender);
                    break;
                case 6:
                    System.out.println("\nPlease enter the relationship status:");
                    String newRelationshipStatus = getStringInput();
                    currentProfile.setRelationshipStatus(newRelationshipStatus);
                    System.out.println("Successfully set relationship status to: " + newRelationshipStatus);
                    break;
                case 7:
                    System.out.println("\nPlease enter the age.");
                    int newAge = getUserIntInput(22, 122);
                    currentProfile.setAge(newAge);
                    System.out.println("Successfully set age to: " + newAge);
                    break;
                case 8:
                    System.out.println("\nPlease enter the occupation:");
                    String newOccupation = getStringInput();
                    currentProfile.setOccupation(newOccupation);
                    System.out.println("Successfully set occupation to: " + newOccupation);
                    break;
                case 9:
                    String newAstrologicalSign = getAstrologicalSignChoice();
                    currentProfile.setAstrologicalSign(newAstrologicalSign);
                    System.out.println("Successfully set astrological sign to: " +
                            newAstrologicalSign);
                    break;
                case 10:
                    System.out.println("\nPlease enter the college:");
                    String newCollege = getStringInput();
                    currentProfile.setCollege(newCollege);
                    System.out.println("Successfully set college to: " + newCollege);
                    break;
                case 11:
                    System.out.println("\nPlease enter the major:");
                    String newMajor = getStringInput();
                    currentProfile.setMajor(newMajor);
                    System.out.println("Successfully set major to: " + newMajor);
                    break;
                case 12:
                    File profilePic = getProfilePicInput();
                    currentProfile.setProfilePic(profilePic);
                    System.out.println("Successfully set profile pic to: " + profilePic.getPath());
                    break;
                case 13:
                    System.out.println("Returning to main program.");
                    exit = true;
            }
        }
    }

    /**
     * Gets a string input from the user.
     *
     * @return The string input from the user.
     */
    public static String getStringInput() {
        String output = "";
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            if (scanner.hasNextLine()) {
                output = scanner.nextLine();
                validInput = true;
            }
        }
        return output;
    }

    /**
     * Gets the name input from the user.
     *
     * @return The name.
     */
    public static String getNameInput() {
        String name = "";
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        // Prompting until name provided for profile is valid
        while (!validInput) {
            System.out.println("\nPlease enter the name (must be 1 or more characters):");
            if (scanner.hasNextLine()) {
                name = scanner.nextLine();
                // Ensuring it's not a name of just whitespace
                if (!name.trim().isEmpty()) {
                    validInput = true;
                }
            }
        }
        return name;
    }

    /**
     * Gets the profile picture file input from the user.
     *
     * @return The profile picture file.
     */
    public static File getProfilePicInput() {
        String path = "";
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        File file = new File("");

        // Prompting until file provided for profile pic is valid
        while (!validInput) {
            System.out.println("\nPlease enter the path of the profile picture file " +
                    "(file must exist and have the extension " +
                    ".png, .jpeg, or .jpg):");
            if (scanner.hasNextLine()) {
                path = scanner.nextLine();
                // Ensuring it has a valid extension
                if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png")) {
                    file = new File(path);
                    // Ensuring the file exists
                    if (file.exists()) {
                        validInput = true;
                    } else {
                        System.out.println("File not found. Please enter a valid path.");
                    }
                } else {
                    System.out.println("Incorrect extension. " +
                            "File must have the extension .png, .jpeg, or .jpg.");
                }
            }
        }
        return file;
    }

    /**
     * Gets the astrological sign input from the user
     *
     * @return The astrological sign.
     */
    public static String getAstrologicalSignChoice() {
        // Create ArrayList of valid signs
        ArrayList<String> signs = new ArrayList<>(
                Arrays.asList("Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio",
                        "Sagittarius", "Capricorn", "Aquarius",
                        "Pisces")
        );

        // Prompts to select a sign from the list
        System.out.println("\nPlease select an astrological sign:");
        for (int i = 0; i < signs.size(); i++) {
            System.out.println(i + 1 + ". " + signs.get(i));
        }

        // Gets the user choice in the range of 1 - the size of the signs array
        int signChoice = getUserIntInput(1, signs.size());

        // Returns the sign
        return signs.get(signChoice - 1);
    }

    /**
     * Display the current profile details.
     */
    public static void viewCurrentProfile() {
        System.out.println("\n" + currentProfile);
    }

    /**
     * Displays all profiles.
     */
    public static void viewAllProfiles() {
        System.out.println("\n");
        profileManager.displayProfiles();
    }

    /**
     * Adds a new friend to the current profile.
     */
    public static void addAFriend() {
        // Gets the list of profiles
        ArrayList<Profile> profiles = profileManager.getListOfProfiles();
        int profilesSize = profiles.size();

        // If there's more than 1 profile
        if (profilesSize > 1) {
            // Create a HashMap for mapping the display choice to the profiles ArrayList index
            HashMap<Integer, Integer> choiceToProfilesIndexMap = new HashMap<>();

            // Get the current profile's friends list
            List<Profile> currentFriends = currentProfile.getFriendProfiles();

            // Initialize display index to 0
            int displayIndex = 0;

            // Keep track of choices to display if there are valid choices
            StringBuilder choicesStringBuilder = new StringBuilder();
            choicesStringBuilder.append("\nPlease select a new friend to add for the current user.\n");

            // For each profile in the profiles ArrayList
            for (int i = 0; i < profilesSize; i++) {
                Profile profile = profiles.get(i);

                // If the profile isn't the current profile and not already friends with the current profile
                if (profile != currentProfile && !currentFriends.contains(profile)) {
                    // Increment the display index
                    displayIndex++;

                    // Add the display index and profiles index to the HashMap
                    choiceToProfilesIndexMap.put(displayIndex, i);

                    // Add the display index and name to the choices string
                    choicesStringBuilder.append(displayIndex);
                    choicesStringBuilder.append(". ");
                    choicesStringBuilder.append(profiles.get(i).getName());
                    choicesStringBuilder.append("\n");
                }
            }

            // If there's at least 1 valid choice to choose from
            if (displayIndex > 0) {
                // Print the choices string
                System.out.println(choicesStringBuilder.toString());

                // Get the user choice
                int userChoice = getUserIntInput(1, displayIndex);

                // Get the profiles ArrayList index for the corresponding user choice
                int profilesIndex = choiceToProfilesIndexMap.get(userChoice);

                // Create the friendship
                Profile friendChoice = profiles.get(profilesIndex);
                profileManager.createFriendship(currentProfile, friendChoice);
                System.out.println("Added friend: " + friendChoice.getName());
            }
        } else {
            // Otherwise, there is no valid choice for the user
            System.out.println("\nCurrent user " + currentProfile.getName() +
                    " is the only profile in the network or is already " +
                    "friends with the other existing profiles. No friends to add.");
        }
    }

    /**
     * View the friend list of the current user.
     */
    public static void viewFriendList() {
        // If it's not empty, display the name of the current profile's friend(s)
        if (!currentProfile.getFriendProfiles().isEmpty()) {
            System.out.println(currentProfile.getName() + "'s friends:");
            List<Profile> friendProfiles = currentProfile.getFriendProfiles();

            // Display for each friend's name with an index number
            for (int i = 0; i < friendProfiles.size(); i++) {
                System.out.println(i + 1 + ". " + friendProfiles.get(i).getName());
            }
        } else {
            // Otherwise, the current profile has no friends... WOMP WOMP
            System.out.println("\n" + currentProfile.getName() + " has no friends :(");
        }
    }

    /**
     * View the friend list of on of your friends.
     */
    private static void viewFriendOfFriendList() {
        // Get list of a current user's friend
        List<Profile> friends = currentProfile.getFriendProfiles();

        if (friends.isEmpty()) {
            System.out.println(currentProfile.getName() + " has no friends to view,");
            return;
        }

        // Display friends so the user can pick one
        System.out.println("\nSelect a friend to view their friend's list");
        for (int i = 0; i < friends.size(); i++) {
            System.out.println(i + 1 + ". " + friends.get(i).getName());
        }

        int choice = getUserIntInput(1, friends.size());
        Profile selectedFriend = friends.get(choice - 1);

        // show that friend's friend list
        List<Profile> friendOfFriendList = selectedFriend.getFriendProfiles();

        if (friendOfFriendList.isEmpty()) {
            System.out.println(currentProfile.getName() + " has no friends to view,");
        } else {
            System.out.println("\n" + selectedFriend.getName() + "'s friends:");
            for (int i = 0; i < friendOfFriendList.size(); i++) {
                System.out.println(i + 1 + ". " + friendOfFriendList.get(i).getName());
            }
        }
    }

    /**
     * Method to delete another profile.
     */
    private static void deleteProfile() {
        System.out.println("Are you sure you want to delete the profile " + currentProfile.getName() + "?");
        System.out.println("Enter 'Yes' to confirm or 'No' to cancel.");

        String confirmation = getStringInput(); // Scanner to get user input

        if (confirmation.equals("Yes")) {
            profileManager.removeProfile(currentProfile);
            System.out.println("Profile " + currentProfile.getName() + " has been deleted.");
            currentProfile = null; // Reset current profile
        } else {
            System.out.println("\nProfile deletion cancelled.");
        }

    }

    /**
     * Add a new profile.
     */
    private static void addNewProfile() {
        Profile newProfile = createProfile();
        profileManager.addProfile(newProfile);
        System.out.println("Profile " + newProfile.getName() + " has been added.");
    }

    /**
     * Switch the current user to a different user.
     */
    private static void switchCurrentUser() {
        List<Profile> allProfiles = profileManager.getListOfProfiles();

        if (allProfiles.size() <= 1) {
            System.out.println("You cannot switch users. There's only one profile");
            return;
        }

        System.out.println("Select a profile to switch to: ");
        for (int i = 0; i < allProfiles.size(); i++) {
            System.out.println(i + 1 + ". " + allProfiles.get(i).getName());
        }

        int choice = getUserIntInput(1, allProfiles.size());
        currentProfile = allProfiles.get(choice - 1);
        System.out.println("You are now logged in as " + currentProfile.getName());
    }

    /**
     * Log out of the program.
     */
    private static void logout() {
        System.out.println("Logging out...");
        System.exit(0);  // This will end the program
    }
}