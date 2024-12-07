import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ProfileManagerTest {

    @Test
    void addProfile() {
        ProfileManager profileManager = new ProfileManager();
        Profile aune = new Profile("Aune");
        profileManager.addProfile(aune);

        assertThrows(IllegalArgumentException.class, () -> profileManager.addProfile(aune));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream capturedOut = new PrintStream(byteArrayOutputStream);
        System.setOut(capturedOut);

        profileManager.displayProfiles();
        assertEquals("Profiles:\n1. Aune\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile details using breadth first search traversal, starting with profile: Aune\n" +
                "1. Name: Aune, status: null, friends: []\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        Profile psy = new Profile("Psy");
        psy.addFriend(aune);
        psy.setStatus("Finals...");
        profileManager.addProfile(psy);

        profileManager.displayProfiles();
        assertEquals("Profiles:\n1. Aune\n2. Psy\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile details using breadth first search traversal, starting with profile: Aune\n" +
                "1. Name: Aune, status: null, friends: [Psy]\n" +
                "2. Name: Psy, status: Finals..., friends: [Aune]\n", byteArrayOutputStream.toString());
    }

    @Test
    void removeProfile() {
        ProfileManager profileManager = new ProfileManager();
        Profile aune = new Profile("Aune");

        assertThrows(IllegalArgumentException.class, () -> profileManager.removeProfile(aune));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream capturedOut = new PrintStream(byteArrayOutputStream);
        System.setOut(capturedOut);

        profileManager.addProfile(aune);
        Profile psy = new Profile("Psy");
        profileManager.addProfile(psy);
        Profile creep = new Profile("Creep");
        profileManager.addProfile(creep);
        profileManager.createFriendship(aune, psy);
        profileManager.createFriendship(aune, creep);
        profileManager.createFriendship(psy, creep);

        profileManager.displayProfiles();
        assertEquals("Profiles:\n" +
                "1. Aune\n" +
                "2. Psy\n" +
                "3. Creep\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile details using breadth first search traversal, starting with profile: Aune\n" +
                "1. Name: Aune, status: null, friends: [Psy, Creep]\n" +
                "2. Name: Psy, status: null, friends: [Aune, Creep]\n" +
                "3. Name: Creep, status: null, friends: [Aune, Psy]\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        profileManager.removeProfile(creep);
        profileManager.displayProfiles();
        assertEquals("Profiles:\n" +
                "1. Aune\n" +
                "2. Psy\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile details using breadth first search traversal, starting with profile: Aune\n" +
                "1. Name: Aune, status: null, friends: [Psy]\n" +
                "2. Name: Psy, status: null, friends: [Aune]\n", byteArrayOutputStream.toString());
    }

    @Test
    void createFriendship() {
        ProfileManager profileManager = new ProfileManager();
        Profile aune = new Profile("Aune");
        Profile psy = new Profile("Psy");
        profileManager.addProfile(aune);
        assertThrows(IllegalArgumentException.class, () -> profileManager.createFriendship(aune, psy));

        profileManager.addProfile(psy);
        profileManager.createFriendship(aune, psy);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream capturedOut = new PrintStream(byteArrayOutputStream);
        System.setOut(capturedOut);

        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile details using breadth first search traversal, starting with profile: Aune\n" +
                "1. Name: Aune, status: null, friends: [Psy]\n" +
                "2. Name: Psy, status: null, friends: [Aune]\n", byteArrayOutputStream.toString());

        assertTrue(aune.getFriendProfiles().contains(psy));
        assertTrue(psy.getFriendProfiles().contains(aune));
    }

    @Test
    void displayProfiles() {
        ProfileManager profileManager = new ProfileManager();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream capturedOut = new PrintStream(byteArrayOutputStream);
        System.setOut(capturedOut);

        profileManager.displayProfiles();
        assertEquals("No profiles found.\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        profileManager.addProfile(new Profile("Aune"));
        profileManager.addProfile(new Profile("Psy"));
        profileManager.displayProfiles();
        assertEquals("Profiles:\n1. Aune\n2. Psy\n", byteArrayOutputStream.toString());
    }

    @Test
    void displayProfileDetailsBFSTraversal() {
        ProfileManager profileManager = new ProfileManager();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream capturedOut = new PrintStream(byteArrayOutputStream);
        System.setOut(capturedOut);

        Profile aune = new Profile("Aune");
        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile not found in social graph.\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        Profile psy = new Profile("Psy");
        profileManager.addProfile(aune);
        profileManager.addProfile(psy);
        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile details using breadth first search traversal, starting with profile: Aune\n" +
                "1. Name: Aune, status: null, friends: []\n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        Profile luna = new Profile("Luna");
        profileManager.addProfile(luna);
        profileManager.createFriendship(aune, psy);
        profileManager.createFriendship(psy, luna);
        profileManager.displayProfileDetailsBFSTraversal(aune);
        assertEquals("Profile details using breadth first search traversal, starting with profile: Aune\n" +
                "1. Name: Aune, status: null, friends: [Psy]\n" +
                "2. Name: Psy, status: null, friends: [Aune, Luna]\n" +
                "3. Name: Luna, status: null, friends: [Psy]\n", byteArrayOutputStream.toString());
    }
}
