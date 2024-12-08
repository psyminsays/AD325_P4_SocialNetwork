import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    @Test
    void testGetName() {
        Profile profile = new Profile("Sarah");
        assertEquals("Sarah", profile.getName());
    }

    @Test
    void testGetStatus() {
        // Given: a Profile with a status
        Profile profile = new Profile("Sarah");
        profile.setStatus("Online");

        // When: we call getStatus
        String status = profile.getStatus();

        // Then -- The status should output "Online"
        assertEquals("Online", status);
    }

    @Test
    void testGetFriendProfiles() {
        // Given: Create three Profile objects
        Profile profile1 = new Profile("Alice");
        Profile profile2 = new Profile("Bob");
        Profile profile3 = new Profile("Charlie");

        // Add friends to profile1
        profile1.addFriend(profile2);
        profile1.addFriend(profile3);

        // When: we call getFriendProfiles
        List<Profile> friends = profile1.getFriendProfiles();

        // Then: the friendProfiles list should contain profile2 and profile3
        assertEquals(2, friends.size(), "Profile1 should have 2 friends.");
        assertTrue(friends.contains(profile2), "Profile1 should have Bob as a friend.");
        assertTrue(friends.contains(profile3), "Profile1 should have Charlie as a friend.");
    }

    @Test
    void testGetLocation() {
        // Given: A Profile object with a location
        Profile profile = new Profile("Alice");
        profile.setLocation("New York, USA");

        // When: we call getLocation
        String location = profile.getLocation();

        // Then: The location should be "New York, USA"
        assertEquals("New York, USA", location, "The location should be 'New York, USA'.");
    }

    @Test
    void testGetGender() {
        // Given: A Profile object with gender set
        Profile profile = new Profile("Alice");
        profile.setGender("Nonbinary");

        // When: we call getGender
        String gender = profile.getGender();

        // Then: The gender should be "Nonbinary"
        assertEquals("Nonbinary", gender, "The gender should be 'Nonbinary'.");
    }

    @Test
    void testGetRelationshipStatus() {
        // Given: A Profile object with relationship status set
        Profile profile = new Profile("Alice");
        profile.setRelationshipStatus("In a relationship");

        // When: we call getRelationshipStatus
        String relationshipStatus = profile.getRelationshipStatus();

        // Then: The relationship status should be "In a relationship"
        assertEquals("In a relationship", relationshipStatus, "The relationship status should be 'In a relationship'.");
    }

    @Test
    void testGetAge() {
        // Given: A Profile object with an age set
        Profile profile = new Profile("Alice");
        profile.setAge(25);

        // When: we call getAge
        int age = profile.getAge();

        // Then: The age should be 25
        assertEquals(25, age, "The age should be 25.");
    }

    @Test
    void testGetOccupation() {
        // Given: A Profile object with an occupation set
        Profile profile = new Profile("Alice");
        profile.setOccupation("Software Engineer");

        // When: we call getOccupation
        String occupation = profile.getOccupation();

        // Then: The occupation should be "Software Engineer"
        assertEquals("Software Engineer", occupation, "The occupation should be 'Software Engineer'.");
    }

    @Test
    void testGetAstrologicalSign() {
        // Given: A Profile object with an astrological sign set
        Profile profile = new Profile("Alice");
        profile.setAstrologicalSign("Capricorn");

        // When: we call getAstrologicalSign
        String astrologicalSign = profile.getAstrologicalSign();

        // Then: The astrological sign should be "Capricorn"
        assertEquals("Capricorn", astrologicalSign, "The astrological sign should be 'Capricorn'.");
    }

    @Test
    void testGetCollege() {
        // Given: A Profile object with a college set
        Profile profile = new Profile("Alice");
        profile.setCollege("MIT");

        // When: we call getCollege
        String college = profile.getCollege();

        // Then: The college should be "MIT"
        assertEquals("MIT", college, "The college should be 'MIT'.");
    }

    @Test
    void testGetMajor() {
        // Given: A Profile object with a major set
        Profile profile = new Profile("Alice");
        profile.setMajor("Computer Science");

        // When: we call getMajor
        String major = profile.getMajor();

        // Then: The major should be "Computer Science"
        assertEquals("Computer Science", major, "The major should be 'Computer Science'.");
    }

    @Test
    void testGetProfilePic() {
        // Given: A Profile object with a profile picture set
        Profile profile = new Profile("Alice");
        File profilePic = new File("path/to/profilePic.jpg");
        profile.setProfilePic(profilePic);

        // When: we call getProfilePic
        File result = profile.getProfilePic();

        // Then: The result should be the profile picture we set
        assertEquals(profilePic, result, "The profile picture should match the one set.");
    }

    @Test
    void testSetName() {
        Profile profile = new Profile("Sarah");
        profile.setName("Rhyan");
        assertEquals("Rhyan", profile.getName());
    }

    @Test
    void testSetStatus() {
        Profile profile = new Profile("Sarah");
        profile.setStatus("Offline");
        assertEquals("Offline", profile.getStatus());
    }

    @Test
    void testAddFriend() {
        Profile profile1 = new Profile("Alice");
        Profile profile2 = new Profile("Bob");

        // Add friend
        boolean isFriendAdded = profile1.addFriend(profile2);
        assertTrue(isFriendAdded, "Friend should be added successfully.");

        // Try adding the same friend again
        isFriendAdded = profile1.addFriend(profile2);
        assertFalse(isFriendAdded, "Friend should not be added again.");
    }

    @Test
    void testSetLocation() {
        Profile profile = new Profile("Alice");
        profile.setLocation("New York, USA");
        assertEquals("New York, USA", profile.getLocation());
    }

    @Test
    void testSetGender() {
        Profile profile = new Profile("Alice");
        profile.setGender("Nonbinary");
        assertEquals("Nonbinary", profile.getGender());
    }

    @Test
    void testSetRelationshipStatus() {
        Profile profile = new Profile("Alice");
        profile.setRelationshipStatus("In a relationship");
        assertEquals("In a relationship", profile.getRelationshipStatus());
    }

    @Test
    void testSetAge() {
        Profile profile = new Profile("Alice");
        profile.setAge(25);
        assertEquals(25, profile.getAge());
    }
}
