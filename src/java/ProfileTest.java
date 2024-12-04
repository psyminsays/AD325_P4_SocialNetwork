import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileTest {

    @Test
    void getName() {
        // Arrange: Set up the Profile object with a specific name
        String name = "Joyce";
        Profile profile;
        profile = new Profile(name);

        // Act: Call the getName() method
        String actualName = profile.getName();

        // Assert: Check that the name returned by getName() is what we expected
        assertEquals(name, actualName, "The name should match the one we set in the Profile object.");
    }

    // Test case for name containing special characters
    @Test
    void testNameContainsSpecialCharacters() {
        // Arrange
        String nameWithSpecialChar = "Joyce!@#";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Profile profile = new Profile(nameWithSpecialChar, "Happy", "New York", "Female", "Single", 25, 19991215, 175, "Engineer", "Sagittarius", "MIT", "CS", "Life", null);
        });

        // Assert
        assertEquals("Sorry, only alphabetical characters are allowed in names.", exception.getMessage());
    }

    @Test
    void getStatus() {
        // Test code here
    }

    @Test
    void getFriendProfiles() {
        // Test code here
    }

    @Test
    void getLocation() {
        // Test code here
    }

    @Test
    void getGender() {
        // Test code here
    }

    @Test
    void getRelationshipStatus() {
        // Test code here
    }

    @Test
    void getAge() {
        // Test code here
    }

    @Test
    void getBirthday() {
        // Test code here
    }

    @Test
    void getHeight() {
        // Test code here
    }

    @Test
    void getOccupation() {
        // Test code here
    }

    @Test
    void getAstrologicalSign() {
        // Test code here
    }

    @Test
    void getCollege() {
        // Test code here
    }

    @Test
    void getMajor() {
        // Test code here
    }

    @Test
    void getSlogan() {
        // Test code here
    }

    @Test
    void getProfilePic() {
        // Test code here
    }

    @Test
    void testNameExceedsMaxLength() {
        // Arrange
        String longName = "Mary Flyinghorse";  // Length = 18, exceeds 16

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            // Attempt to create a Profile with a name that exceeds 16 characters
            new Profile(longName, "Happy", null, "New York", "Female", "Single", 25, 19991215, 175, "Engineer", "Sagittarius", "MIT", "CS", "Life", null);
        });

        // Assert
        assertEquals("Error: Name cannot exceed 16 characters.", exception.getMessage());
    }

    /**
     * Tests that a name of 16 characters or fewer is accepted without error.
     */
    @Test
     void testNameValidLength() {
        // Arrange
        String validName = "Mary Flying";  // Length = 12, valid

        // Act
        Profile profile = new Profile(validName, "Happy", null, "New York", "Female", "Single", 25, 19991215, 175, "Engineer", "Sagittarius", "MIT", "CS", "Life", null);

        // Assert
        assertEquals(validName, profile.getName());  // No exception should be thrown, and name should match
    }

    @Test
    void setName() {
        // Test code here
    }

    @Test
    void setStatus() {
        // Test code here
    }

    @Test
    void setFriendProfiles() {
        // Test code here
    }

    @Test
    void setLocation() {
        // Test code here
    }

    @Test
    void setGender() {
        // Test code here
    }

    @Test
    void setRelationshipStatus() {
        // Test code here
    }

    @Test
    void setAge() {
        // Test code here
    }

    @Test
    void setBirthday() {
        // Test code here
    }

    @Test
    void setHeight() {
        // Test code here
    }

    @Test
    void setOccupation() {
        // Test code here
    }

    @Test
    void setAstrologicalSign() {
        // Test code here
    }

    @Test
    void setCollege() {
        // Test code here
    }

    @Test
    void setMajor() {
        // Test code here
    }

    @Test
    void setSlogan() {
        // Test code here
    }

    @Test
    void setProfilePic() {
        // Test code here
    }
}
