//Interface
interface LibraryOperations {
    void borrowBook();
    void returnBook();
}

//Base Class
class LibraryMember {
    protected String memberName;
    protected int booksBorrowed;

    public LibraryMember(String memberName) {
        this.memberName = memberName;
        this.booksBorrowed = 0;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }
}

//derived
class StudentMember extends LibraryMember implements LibraryOperations {

    private Library library;

    public StudentMember(String name, Library library) {
        super(name);
        this.library = library;
    }

    @Override
    public void borrowBook() {
        library.borrowBook(this);
    }

    @Override
    public void returnBook() {
        library.returnBook(this);
    }
}

// Enum
enum TransactionType {
    BORROW,
    RETURN,
    NOT_AVAILABLE
}

// Shared Resource Class
class Library {

    private int availableBooks;

    public Library(int books) {
        this.availableBooks = books;
    }

    // Borrow method
    public synchronized void borrowBook(StudentMember member) {
        while (availableBooks == 0) {
            try {
                System.out.println("No books available. " + member.memberName + " waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        availableBooks--;
        member.booksBorrowed++;

        displayStatus(TransactionType.BORROW, member);
    }

    // Return method
    public synchronized void returnBook(StudentMember member) {

        if (member.booksBorrowed > 0) {
            availableBooks++;
            member.booksBorrowed--;

            displayStatus(TransactionType.RETURN, member);
            notifyAll();
        }
    }

    // Display after each action
    private void displayStatus(TransactionType type, StudentMember member) {
        System.out.println("\nTransaction: " + type);
        System.out.println("Available Books: " + availableBooks);
        System.out.println("Books borrowed by " + member.memberName + ": " + member.getBooksBorrowed());
    }

    // Librarian final announcement
    public synchronized void announceFinalStatus() {
        System.out.println("\nLibrarian Announcement:");
        System.out.println("Final Available Books: " + availableBooks);
    }
}

// 5️⃣ Borrowing Thread
class BorrowThread extends Thread {

    private StudentMember member;

    public BorrowThread(StudentMember member) {
        this.member = member;
    }

    public void run() {
        member.borrowBook();
    }
}

// 5️⃣ Returning Thread
class ReturnThread extends Thread {

    private StudentMember member;

    public ReturnThread(StudentMember member) {
        this.member = member;
    }

    public void run() {
        member.returnBook();
    }
}

// 5️⃣ Librarian Thread
class LibrarianThread extends Thread {

    private Library library;

    public LibrarianThread(Library library) {
        this.library = library;
    }

    public void run() {
        try {
            Thread.sleep(3000); // monitoring delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        library.announceFinalStatus();
    }
}

// Main Class
public class lib {

    public static void main(String[] args) {

        Library library = new Library(2); // initial books

        StudentMember s1 = new StudentMember("Student1", library);
        StudentMember s2 = new StudentMember("Student2", library);

        BorrowThread b1 = new BorrowThread(s1);
        BorrowThread b2 = new BorrowThread(s2);
        ReturnThread r1 = new ReturnThread(s1);

        LibrarianThread librarian = new LibrarianThread(library);

        b1.start();
        b2.start();
        r1.start();
        librarian.start();
    }
}
