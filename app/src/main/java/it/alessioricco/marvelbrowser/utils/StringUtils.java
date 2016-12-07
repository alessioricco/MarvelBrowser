package it.alessioricco.marvelbrowser.utils;

public class StringUtils {

    /**
     * check for string emptyness or nullity
     * @param string the given string to test
     * @return true if the string is null or if its length is 0
     */
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }


 }
