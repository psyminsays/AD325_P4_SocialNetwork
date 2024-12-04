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
    private List<String> friendProfiles;
    private String location;
    private String gender;
    private String relationshipStatus;
    private int age;
    private int birthday;
    private int height;
    private String occupation;
    private String astrologicalSign;
    private String college;
    private String major;
    private String slogan;
    private File profilePic;

    /**
     * Constructs a new Profile with the specified details.
     *
     * @param name the name of the user
     * @param status the current status of the user
     * @param friendProfiles the list of friends (can be a list of names or Profile objects)
     * @param location the location of the user
     * @param gender the gender of the user
     * @param relationshipStatus the relationship status of the user
     * @param age the age of the user
     * @param birthday the birthday of the user (as an integer YYYYMMDD)
     * @param height the height of the user in centimeters
     * @param occupation the occupation of the user
     * @param astrologicalSign the user's astrological sign
     * @param college the name of the user's college
     * @param major the user's major in college
     * @param slogan a personal slogan or motto for the user
     * @param profilePic the profile picture file of the user
     */
    public Profile(String name, String status, List<String> friendProfiles, String location,
                   String gender, String relationshipStatus, int age, int birthday, int height,
                   String occupation, String astrologicalSign, String college, String major,
                   String slogan, File profilePic) {
        this.name = name;
        this.status = status;
        this.friendProfiles = friendProfiles;
        this.location = location;
        this.gender = gender;
        this.relationshipStatus = relationshipStatus;
        this.age = age;
        this.birthday = birthday;
        this.height = height;
        this.occupation = occupation;
        this.astrologicalSign = astrologicalSign;
        this.college = college;
        this.major = major;
        this.slogan = slogan;
        this.profilePic = profilePic;
    }

    public Profile(String nameWithSpecialChar, String happy, String newYork, String female, String single, int i, int age, int birthday, String engineer, String sagittarius, String mit, String cs, String life, Object slogan) {
    }

    public Profile(String name) {
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
     * Sets the status of the user.
     *
     * @param status the status of the user
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the list of friends' profiles.
     *
     * @return the list of friends
     */
    public List<String> getFriendProfiles() {
        return friendProfiles;
    }

    /**
     * Sets the list of friends' profiles.
     *
     * @param friendProfiles the list of friends
     */
    public void setFriendProfiles(List<String> friendProfiles) {
        this.friendProfiles = friendProfiles;
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
     * Sets the location of the user.
     *
     * @param location the location of the user
     */
    public void setLocation(String location) {
        this.location = location;
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
     * Sets the gender of the user.
     *
     * @param gender the gender of the user
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     * Sets the relationship status of the user.
     *
     * @param relationshipStatus the relationship status of the user
     */
    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
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
     * Sets the age of the user.
     *
     * @param age the age of the user
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the birthday of the user in the format YYYYMMDD.
     *
     * @return the birthday of the user
     */
    public int getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday of the user in the format YYYYMMDD.
     *
     * @param birthday the birthday of the user
     */
    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets the height of the user in centimeters.
     *
     * @return the height of the user
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the user in centimeters.
     *
     * @param height the height of the user
     */
    public void setHeight(int height) {
        this.height = height;
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
     * Sets the occupation of the user.
     *
     * @param occupation the occupation of the user
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
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
     * Sets the astrological sign of the user.
     *
     * @param astrologicalSign the astrological sign of the user
     */
    public void setAstrologicalSign(String astrologicalSign) {
        this.astrologicalSign = astrologicalSign;
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
     * Sets the college name of the user.
     *
     * @param college the college name of the user
     */
    public void setCollege(String college) {
        this.college = college;
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
     * Sets the major of the user.
     *
     * @param major the major of the user
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Gets the personal slogan or motto of the user.
     *
     * @return the personal slogan of the user
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * Sets the personal slogan or motto of the user.
     *
     * @param slogan the personal slogan of the user
     */
    public void setSlogan(String slogan) {
        this.slogan = slogan;
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
     * Sets the profile picture file for the user.
     *
     * @param profilePic the profile picture
     */
    public void setProfilePic(File profilePic) {
        this.profilePic = profilePic;
    }
}
