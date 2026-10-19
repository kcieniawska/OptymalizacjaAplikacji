// ============================================
//
// ZADANIE 5
// Klasa reprezentująca pojedynczą pozycję/książkę
//
// ============================================

public class Book {
    private final String title;
    private final int status;

    public Book(String title, int status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }


    public boolean titleContains(String query) {
        return title != null && query != null && title.contains(query);
    }

    public boolean hasStatus(int targetStatus) {
        return this.status == targetStatus;
    }
}