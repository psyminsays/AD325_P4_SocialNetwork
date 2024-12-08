import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user's profile with personal details and preferences.
 * This class contains attributes such as name, age, gender, relationship status, and more.
 * The profile can also include a list of friends and a profile picture.
 */

public class Profile {

    // Attributes
    private String name;
    private final long profileId = (long) (Math.random() * 10000000000L);
    private String status;
    private final List<Profile> friendProfiles;
    private String location;
    private String gender;
    private String relationshipStatus;
    private int age;
    private String occupation;
    private String astrologicalSign;
    private String college;
    private String major;
    private File profilePic;

    /**
     * Constructs a new Profile with the specified details.
     *
     */

    public Profile(String name) {
        this.name = name;
        this.friendProfiles = new ArrayList<>();
        this.status = "Offline";
        this.location = "Unknown";

    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the status of the user.
     *
     * @return the status of the user
     */

    public String getStatus() {
        return status;
    }

    /**
     * Gets the list of friends' profiles.
     *
     * @return the list of friends
     */

    public List<Profile> getFriendProfiles() {
        return friendProfiles;
    }

    /**
     * Gets the location of the user.
     *
     * @return the location of the user
     */

    public String getLocation() {
        return location;
    }

    /**
     * Gets the gender of the user.
     *
     * @return the gender of the user
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the relationship status of the user.
     *
     * @return the relationship status of the user
     */

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    /**
     * Gets the age of the user.
     *
     * @return the age of the user
     */

    public int getAge() {
        return age;
    }

    /**
     * Gets the occupation of the user.
     *
     * @return the occupation of the user
     */

    public String getOccupation() {
        return occupation;
    }

    /**
     * Gets the astrological sign of the user.
     *
     * @return the astrological sign of the user
     */

    public String getAstrologicalSign() {
        return astrologicalSign;
    }


    /**
     * Gets the college name of the user.
     *
     * @return the college name of the user
     */

    public String getCollege() {
        return college;
    }


    /**
     * Gets the major of the user.
     *
     * @return the major of the user
     */

    public String getMajor() {
        return major;
    }


    /**
     * Gets the profile picture file of the user.
     *
     * @return the profile picture file
     */

    public File getProfilePic() {
        return profilePic;
    }

    /**
     * Returns a string representation of the Profile details.
     * Includes name, profile ID, status, location, gender, relationship status, age,
     * occupation, astrological sign, college, major, profile picture, and friends.
     *
     * @return a string with all profile details
     */

    public String toString() {
        // Start building the profile details string using simple concatenation
        String result = "Profile Details:\n";

        result += "Name: " + name + "\n";
        result += "Profile ID: " + profileId + "\n";
        result += "Status: " + (status != null ? status : "N/A") + "\n";
        result += "Location: " + (location != null ? location : "N/A") + "\n";
        result += "Gender: " + (gender != null ? gender : "N/A") + "\n";
        result += "Relationship Status: " + (relationshipStatus != null ? relationshipStatus : "N/A") + "\n";
        result += "Age: " + (age > 0 ? age : "N/A") + "\n";
        result += "Occupation: " + (occupation != null ? occupation : "N/A") + "\n";
        result += "Astrological Sign: " + (astrologicalSign != null ? astrologicalSign : "N/A") + "\n";
        result += "College: " + (college != null ? college : "N/A") + "\n";
        result += "Major: " + (major != null ? major : "N/A") + "\n";
        result += "Profile Picture: " + (profilePic != null ? profilePic.getName() : "No picture") + "\n";

        // Print out the list of friends, if any
        if (!friendProfiles.isEmpty()) {
            result += "Friends: \n";
            StringBuilder resultBuilder = new StringBuilder(result);
            for (Profile friend : friendProfiles) {
                resultBuilder.append(" - ").append(friend.getName()).append("\n");
            }
            result = resultBuilder.toString();
        } else {
            result += "Friends: None\n";
        }

        return result;
    }

    /**
     * Adds a friend to the friendProfiles list.
     * If the friend is not already in the list, adds the friend and returns true.
     *
     * @param friend the profile of the friend to add
     * @return true if the friend was added, false if the friend was already in the list
     * @throws IllegalArgumentException if the friend is null
     */

    public boolean addFriend(Profile friend) {
        if (friend == null) {
            throw new IllegalArgumentException("Cannot add a null profile.");
        }
        if (!friendProfiles.contains(friend)) {
            friendProfiles.add(friend);
            return true;  // Return true if the friend was added
        }
        return false;  // Return false if the friend is already in the list
    }

    /**
     * Sets the name of the user.
     * Throws an exception if the name exceeds 16 characters.
     *
     * @param name the new name for the user
     * @throws IllegalArgumentException if the name exceeds 16 characters
     */

    public void setName(String name) {
        this.name = name;
        if (name.length() > 16) throw new IllegalArgumentException("Error: Name cannot exceed 16 characters.");
    }

    /**
     * Sets the location of the user.
     *
     * @param location the new location of the user
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the new gender of the user
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the status of the user.
     *
     * @param status the new status of the user
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Sets the relationship status of the user.
     *
     * @param relationshipStatus the new relationship status of the user
     */

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    /**
     * Sets the age of the user.
     * Throws an exception if the age is less than 22.
     *
     * @param age the new age of the user
     * @throws IllegalArgumentException if the age is less than 22
     */

    public void setAge(int age) {
        if (22 > age) {
            throw new IllegalArgumentException("Error: Must be 22 and older to use this website.");
        }
        this.age = age;
    }

    /**
     * Sets the occupation of the user.
     *
     * @param occupation the new occupation of the user
     */

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Sets the astrological sign of the user.
     *
     * @param astrologicalSign the new astrological sign of the user
     */

    public void setAstrologicalSign(String astrologicalSign) {
        this.astrologicalSign = astrologicalSign;
    }

    /**
     * Sets the college name of the user.
     *
     * @param college the new college name of the user
     */

    public void setCollege(String college) {
        this.college = college;
    }

    /**
     * Sets the major of the user.
     *
     * @param major the new major of the user
     */

    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Sets the profile picture file of the user.
     *
     * @param profilePic the new profile picture file for the user
     */
    public void setProfilePic(File profilePic) {
        this.profilePic = profilePic;
    }

}
