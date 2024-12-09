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

    }

    /** Add another profile */
    public static void addAnotherProfile() {
        Profile newProfile = createProfile(); // Reuses the createProfile method from earlier
        profileManager.addProfile(newProfile);
        System.out.println("New profile " + newProfile.getName() + " added.");
    }

    /** Switch the current user */
    public static void switchUser() {
        List<Profile> allProfiles = profileManager.getListOfProfiles();
        if (allProfiles.size() <= 1) {
            System.out.println("There are no other profiles to switch to.");
            return;
        }

        // Display all profiles and allow the user to choose a new one
        System.out.println("Select a profile to switch to:");
        for (int i = 0; i < allProfiles.size(); i++) {
            if (!allProfiles.get(i).equals(currentProfile)) {
                System.out.println((i + 1) + ". " + allProfiles.get(i).getName());
            }
        }

        int choice = getUserIntInput(1, allProfiles.size()) - 1;
        Profile newUser = allProfiles.get(choice);

        currentProfile = newUser;
        System.out.println("Switched to " + newUser.getName() + " as the current user.");
    }

    /** Logout and end the program */
    public static void logout() {
        System.out.println("Logging out...");
        System.exit(0); // Terminates the program
    }
}