package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.managers.BookmarkManager;

import static org.junit.jupiter.api.Assertions.*;

class WeblinkTest {

    @org.junit.jupiter.api.Test
    void isKidFriendlyEligible() {
        // Test1 : 'porn' in url - false
        Weblink weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html", "http://www.javaworld.com");
        boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For porn in URL, it must return false");

        // Test2 : 'porn' in title - false
        weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming porn, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = weblink.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For porn in title, it must return false");

        // Test3 : adult in host - false
        weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adultworld.com");
        isKidFriendlyEligible = weblink.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For adult in host, it must return false");

        // Test4 : adult in url but not in host - true
        weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = weblink.isKidFriendlyEligible();
        assertTrue(isKidFriendlyEligible, "For adult in URL but not in host, it must return true");

        // Test5 : adult in title only - true
        weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming adult, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = weblink.isKidFriendlyEligible();
        assertTrue(isKidFriendlyEligible, "For adult in title but not in host, it must return true");

    }
}