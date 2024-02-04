package com.skippr.mask;

import java.util.Arrays;

public class MaskingUtils {

    /**
     * A String for a mask character.
     *
     * @since 1.0
     */
    public static final char MASKING_CHAR = '*';

    /**
     * The empty String {@code ""}.
     * @since 1.0
     */
    public static final String EMPTY = "";

    /**
     * Default clear characters length to mask.
     * @since 1.0
     */
    public static final int CLEAR_LENGTH = 4;

    /**
     * <p>Mask a String with {@code *} leaving {@code 4} clear character at end to form a
     * new String.</p>
     *
     * <pre>
     * MaskingUtils.mask(null) = null
     * MaskingUtils.mask("")   = ""
     * MaskingUtils.mask("a")  = "a"
     * MaskingUtils.mask("abcd") = "abcd"
     * MaskingUtils.mask("abcde") = "*bcde"
     * </pre>
     *
     * @param str  the String to mask, may be null
     * @return a new String consisting masked string leaving last 4 char clear,
     *  {@code null} if null String input
     */
    public static String mask(final String str){
        return mask(str, MASKING_CHAR, CLEAR_LENGTH);
    }

    /**
     * <p>Mask a String with input ch leaving {@code 4} clear character at end to form a
     * new String.</p>
     *
     * <pre>
     * MaskingUtils.mask(null, '*') = null
     * MaskingUtils.mask("", '*')   = ""
     * MaskingUtils.mask("a", '*)  = "a"
     * MaskingUtils.mask("abcd", '*) = "abcd"
     * MaskingUtils.mask("abcde", '*) = "*bcde"
     * </pre>
     *
     * @param str  the String to mask, may be null
     * @param ch  the char to fill masking places
     * @return a new String consisting masked string leaving last 4 char clear,
     *  {@code null} if null String input
     */
    public static String mask(final String str, final char ch){
        return mask(str, ch, CLEAR_LENGTH);
    }

    public static String mask(final String str, final int clear){
        return mask(str, MASKING_CHAR, clear);
    }

    public static String mask(final String str, final char ch, final int clear){
        if (str == null) {
            return null;
        }
        if (clear < 1 || str.length() <= clear) {
            return str;
        }
        final char[] buf = new char[str.length() - clear];
        Arrays.fill(buf, ch);
        new String(buf);
        return new String(buf).concat(str.substring(str.length() - clear));
    }
}
