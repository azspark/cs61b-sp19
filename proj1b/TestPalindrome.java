import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);

        Deque d2 = palindrome.wordToDeque(" ");
        String actual2 = "";
        for (int i = 0; i < " ".length(); i++) {
            actual2 += d2.removeFirst();
        }
        assertEquals(" ", actual2);
    }

    @Test
    public void testPalindrome() {
        String t1 = "";
        String t2 = "q";
        String t3 = "Aa";
        String t4 = "abba";
        String t5 = "123321";
        String t6 = "12a210";
        String t7 = "flake";
        String t8 = "ad";
        String t9 = "bcfe";
        String t10 = "asdz";
        CharacterComparator cc = new OffByOne();
        CharacterComparator cc3 = new OffByN(3);

        assertTrue(palindrome.isPalindrome(t1));
        assertTrue(palindrome.isPalindrome(t2));
        assertFalse(palindrome.isPalindrome(t3));
        assertTrue(palindrome.isPalindrome(t4));
        assertTrue(palindrome.isPalindrome(t5));
        assertFalse(palindrome.isPalindrome(t6));

        assertTrue(palindrome.isPalindrome(t7, cc));
        assertTrue(palindrome.isPalindrome(t8, cc3));
        assertTrue(palindrome.isPalindrome(t9, cc3));
        assertFalse(palindrome.isPalindrome(t10, cc3));
    }
}
