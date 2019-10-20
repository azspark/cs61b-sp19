public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> ad = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            ad.addLast(word.charAt(i));
        }
        return ad;
    }

    public boolean isPalindrome(String word) {
        Deque wd = wordToDeque(word);
        return isPalidromeHelper(wd);
    }

    private boolean isPalidromeHelper(Deque d) {
        if (d.size() < 2) {
            return true;
        }
        if (d.removeFirst() == d.removeLast()) {
            return isPalidromeHelper(d);
        } else {
            return false;
        }
    }
}