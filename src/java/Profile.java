import java.io.File;
import java.util.List;

/**
 * Represents a user's profile with personal details and preferences.
 * This class contains attributes such as name, age, gender, relationship status, and more.
 * The profile can also include a list of friends and a profile picture.
 */

public class Profile {

    // Attributes
    private String name;
    private String status;
    private List<?> friendProfiles;
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
     * @param name the name of the user
     */
    public Profile(String name) {
        this.name = name;

    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        if (name.length() > 16) {
            throw new IllegalArgumentException("Error: Name cannot exceed 16 characters.");
        }
        this.name = name;
        return name;
    }

    /**
     * Sets the name of the user.
     /**
     * Sets the name of the user, with a restriction on length.
     * If the name exceeds 16 characters, an exception is thrown.
     *
     * @param name the name of the user
     * @throws IllegalArgumentException if the name exceeds 16 characters
     */
    public void setName(String name) {
        if (name.length() > 16) {
            throw new IllegalArgumentException("Error: Name cannot exceed 16 characters.");
        }
        this.name = name;
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
    public List<?> getFriendProfiles() {
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

}
