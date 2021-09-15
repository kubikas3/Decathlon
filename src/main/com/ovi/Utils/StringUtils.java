package main.com.ovi.Utils;

public class StringUtils {
    public static long countChars(CharSequence text, char ch) {
        return text.codePoints().filter((c) -> c == ch).count();
    }
}
