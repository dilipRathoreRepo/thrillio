package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.partner.Shareable;

public class View {

    public static void browse(User user, Bookmark[][] bookmarks){
        System.out.println("\n" + user.getEmail() + " is browsing");
        int bookmarkCount = 0;

        for(Bookmark[] bookmarkList: bookmarks){
            for(Bookmark bookmark: bookmarkList){
                if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT){
                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if (isBookmarked){
                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                        System.out.println("New item bookmarked -> " + bookmark);
                        bookmarkCount++;
                    }
                }

                if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)){
                    // Mark bookmark as kid friendly
                    if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN) && bookmark.isKidFriendlyEligible()){
                        String kidFriendlyStatus = getKidFriendlyStatusDecision();
                        if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)){
                            BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
                        }
                    }
                    // Sharing (only if a bookmark is kid friendly approved)
                    if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && (bookmark instanceof Shareable)){
                        boolean isShared = getShareDecision();
                        if(isShared){
                            BookmarkController.getInstance().share(user, bookmark);
                        }
                    }
                }
            }
        }
    }

    // TODO : Below method simulate user input. After IO section, needs to be updated via console
    private static boolean getShareDecision() {
        return Math.random() < 0.5;
    }

    private static String getKidFriendlyStatusDecision() {
        double randomNumber = Math.random();
        return randomNumber < 0.4 ? KidFriendlyStatus.APPROVED:
                (randomNumber >= 0.4 && randomNumber <0.8) ? KidFriendlyStatus.REJECTED: KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return Math.random() < 0.5;
    }

//    public static void bookmark(User user, Bookmark[][] bookmarks){
//        System.out.println("\n" + user.getEmail() + " is bookmarking");
//        for (int i=0; i < DataStore.USER_BOOKMARK_LIMIT; i++){
//            int typeOffset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
//            int bookmarkOffset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
//
//            Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
//            BookmarkController.getInstance().saveUserBookmark(user, bookmark);
//
//            System.out.println(bookmark);
//        }
//    }
}
