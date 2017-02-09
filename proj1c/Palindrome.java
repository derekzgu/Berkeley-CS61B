/**
 * @author Wending Peng
 */
public class Palindrome {
    /**
     * Builds a deque where the characters in the deque appear
     * in the deque appear in the same order as in the word.
     *
     * @param word the word to build a deque
     * @return the deque
     */
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDequeSolution<>();
        for (int i = 0; i < word.length(); ++i) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /**
     * Checks if the word is a palindrome.
     *
     * @param word the word to check
     * @return true if the word is a palindrome; false if not
     */
    public static boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }

    private static boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        if (wordDeque.removeLast() == wordDeque.removeFirst()) {
            return isPalindromeHelper(wordDeque);
        }
        return false;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }

    private static boolean isPalindromeHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        if (cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
            return isPalindromeHelper(wordDeque, cc);
        }
        return false;
    }
}
