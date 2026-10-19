// ============================================
//
// ZADANIE 5
// Klasa zarządzająca zasobami
//
// ============================================

import java.util.ArrayList;
import java.util.List;


public class LibraryManager {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
    }

    public int countBooksMatchingCriteria(String titleSearchQuery, int targetStatus) {
        int matchCount = 0;

        for (Book book : books) {
            if (isMatchingBook(book, titleSearchQuery, targetStatus)) {
                matchCount++;
            }
        }

        return matchCount;
    }

    private boolean isMatchingBook(Book book, String titleSearchQuery, int targetStatus) {
        return book.titleContains(titleSearchQuery) && book.hasStatus(targetStatus);
    }
}