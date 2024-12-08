import ADTPackage.QueueInterface;
import GraphPackage.UndirectedGraph;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a ProfileManager to manage profiles using an undirected graph.
 *
 * @author Aune Mitchell
 */
public class ProfileManager {
    private final UndirectedGraph<Profile> socialGraph;
    private final ArrayList<Profile> profiles;

    /** Constructor. */
    public ProfileManager() {
        socialGraph = new UndirectedGraph<>();
        profiles = new ArrayList<>();
    }

    /**
     * Adds a profile to the graph.
     *
     * @param profile The profile to add.
     * @throws IllegalArgumentException if the profile already exists in the graph.
     */
    public void addProfile(Profile profile) {
        // Checks if the profile already exists in the graph
        // If so, throws an IllegalArgumentException
        if (profiles.contains(profile)) {
            throw new IllegalArgumentException("Unable to add as profile already exists.");
        }

        // Adds the profile to the graph
        socialGraph.addVertex(profile);

        // Adds the profile to the list of profiles
        profiles.add(profile);

        // If the profile already has friends in its friends list,
        // adds the friendship (connection) to the graph
        List<Profile> friendsCopy = profile.getFriendProfiles();
        for (Profile friend : friendsCopy) {
            if (!socialGraph.hasEdge(profile, friend)) {
                createFriendship(profile, friend);
            }
        }
    }

    /**
     * Removes the given profile from the graph.
     *
     * @param profile The profile to remove.
     * @throws IllegalArgumentException if the profile doesn't exist in the graph.
     */
    public void removeProfile(Profile profile) {
        // Checks if the profiles list doesn't contain the profile
        // If so, throws an IllegalArgumentException
        if (!profiles.contains(profile)) {
            throw new IllegalArgumentException("Unable to remove profile as it does not exist.");
        }

        // Removes the profile from the list
        profiles.remove(profile);

        // Removes the profile from the graph
        socialGraph.removeVertex(profile);

        // Removes the profile from other profile's friend lists
        for (Profile otherProfile: profiles) {
            otherProfile.getFriendProfiles().remove(profile);
        }
    }

    /**
     * Creates a friendship (undirected connection) between two profiles.
     *
     * @param profile The first profile.
     * @param friend  The second profile.
     * @throws IllegalArgumentException if either or both of the profiles provided don't exist on the graph.
     */
    public void createFriendship(Profile profile, Profile friend) {
        // Checks if the profile list doesn't contain either of the profiles
        // If so, throws an IllegalArgumentException
        if (!profiles.contains(profile) || !profiles.contains(friend)) {
            throw new IllegalArgumentException("Profiles must first be added to the social graph before creating a friendship.");
        }

        // Adds an edge between the profile and the friend
        socialGraph.addEdge(profile, friend);

        // If the friend isn't already in the friend list for the profile, add it
        if (!profile.getFriendProfiles().contains(friend)) {
            profile.addFriend(friend);
        }

        // If the profile isn't already in the friend list for the friend, add it
        if (!friend.getFriendProfiles().contains(profile)) {
            friend.addFriend(profile);
        }
    }

    /** Displays all profiles. */
    public void displayProfiles() {
        // StringBuilder object for displaying the profiles
        StringBuilder profilesStringBuilder = new StringBuilder();

        // If the list isn't empty, build the string
        if (!profiles.isEmpty()) {
            // Add a header
            profilesStringBuilder.append("Profiles:\n");

            // For each profile in the list
            for (int i = 0; i < profiles.size(); i++) {
                // Number the profile
                profilesStringBuilder.append(i + 1);
                profilesStringBuilder.append(". ");

                // Add the profile name
                profilesStringBuilder.append(profiles.get(i).getName());
                profilesStringBuilder.append("\n");
            }

            // Print the string
            System.out.print(profilesStringBuilder.toString());
        } else {
            // Otherwise print "No profiles found."
            System.out.println("No profiles found.");
        }
    }

    /**
     * Display profile details starting from the given profile
     * using depth first search.
     *
     * @param profile The profile to start from for depth first search.
     */
    public void displayProfileDetailsBFSTraversal(Profile profile) {

        // Get the breadth first traversal results as a queue
        QueueInterface<Profile> BFSQueue = socialGraph.getBreadthFirstTraversal(profile);

        // If the queue isn't empty
        if (!BFSQueue.isEmpty()) {

            // Print the header
            System.out.println("Profile details using breadth first search traversal, starting with profile: " + profile.getName());

            // Keep track of the profile number
            int i = 0;

            // While the queue is empty
            while (!BFSQueue.isEmpty()) {
                // Increment and print the profile number
                i++;
                System.out.print(i + ". ");

                // Dequeue the next profile
                Profile dequeuedProfile = BFSQueue.dequeue();

                // Print the profile details
                System.out.print(dequeuedProfile);
            }
        } else {
            // Otherwise, queue is empty so the profile doesn't exist in the social graph
            System.out.println("Profile not found in social graph.");
        }
    }
}
