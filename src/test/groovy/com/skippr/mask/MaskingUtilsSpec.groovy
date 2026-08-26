package com.skippr.mask

import spock.lang.Specification
import spock.lang.Unroll

class MaskingUtilsSpec extends Specification {

    def "mask handles null, empty, and small strings with defaults"() {
        expect:
        MaskingUtils.mask(null) == null
        MaskingUtils.mask("") == ""
        MaskingUtils.mask("a") == "a"
        MaskingUtils.mask("abcd") == "abcd"
        MaskingUtils.mask("abcde") == "*bcde"
    }

    def "mask with custom char preserves behavior and nulls"() {
        expect:
        MaskingUtils.mask(null, (char) '#') == null
        MaskingUtils.mask("abcde", (char) '#') == "#bcde"
    }

    def "mask clear-length overload uses default masking char"() {
        expect:
        MaskingUtils.mask("abcdefg", 2) == "*****fg"
    }

    @Unroll
    def "mask with clear=#clear on '#input' -> '#expected'"() {
        expect:
        MaskingUtils.mask(input, (char) '*', clear) == expected

        where:
        input      | clear || expected
        "abcdefg" | 2     || "*****fg"
        "abcd"    | 4     || "abcd"      // length == clear -> unchanged
        "abc"     | 0     || "abc"       // clear < 1 -> unchanged
        "abc"     | -1    || "abc"       // negative clear -> unchanged
    }

    def "mask with custom char and explicit clear"() {
        expect:
        MaskingUtils.mask("abcdefgh", (char) '#', 3) == "#####fgh"
    }
}
