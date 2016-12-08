package it.alessioricco.marvelbrowser.utils;


public class ComicBookCoverUrlHelper {

    public static String getSmallCover(final String url, final String extension) {
        return String.format("%s/portrait_medium.%s", url, extension);
    }

    public static String getBigCover(final String url, final String extension) {
        return String.format("%s/portrait_uncanny.%s", url, extension);
    }
}
