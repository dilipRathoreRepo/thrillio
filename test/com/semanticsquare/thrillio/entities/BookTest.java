package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void isKidFriendlyEligible() {
        Book book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.PHILOSOPHY,4.3);
        if (book.getGenre().equals(BookGenre.PHILOSOPHY) || book.getGenre().equals(BookGenre.SELF_HELP)){
            assertFalse(book.isKidFriendlyEligible(), "Book belongs to philosophy or self-help type, hence returning false");
        }
        book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.BIOGRAPHY,4.3);
        if (!book.getGenre().equals(BookGenre.PHILOSOPHY) && !book.getGenre().equals(BookGenre.SELF_HELP)){
            assertTrue(book.isKidFriendlyEligible(), "Since book is not philosophical or self-help, it should return true");
        }
    }
}