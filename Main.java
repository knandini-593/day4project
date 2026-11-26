import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;

class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String category;
    private int availableCopies;

    public Book(int bookId, String bookName, String author, String category, int availableCopies) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.availableCopies = availableCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        if (availableCopies >= 0) {
            this.availableCopies = availableCopies;
        }
    }
}

class LibraryMember {
    private int memberId;
    private String memberName;
    private String contactNumber;

    public LibraryMember(int memberId, String memberName, String contactNumber) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.contactNumber = contactNumber;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Member Name: " + memberName);
        System.out.println("Contact: " + contactNumber);
    }
}

class Student extends LibraryMember {
    private String department;
    private int year;

    public Student(int memberId, String memberName, String contactNumber, String department, int year) {
        super(memberId, memberName, contactNumber);
        this.department = department;
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void displayMemberDetails() {
        System.out.println("--- Student ---");
        super.displayMemberDetails();
        System.out.println("Department: " + department);
        System.out.println("Year: " + year);
    }
}

class Faculty extends LibraryMember {
    private String department;
    private String designation;

    public Faculty(int memberId, String memberName, String contactNumber, String department, String designation) {
        super(memberId, memberName, contactNumber);
        this.department = department;
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public void displayMemberDetails() {
        System.out.println("--- Faculty ---");
        super.displayMemberDetails();
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
    }
}

class IssueRecord {
    private int issueId;
    private int bookId;
    private int memberId;
    private String status;

    public IssueRecord(int issueId, int bookId, int memberId, String status) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

abstract class LibraryOperations {
    abstract void addBook(Book book);
    abstract void displayBooks();
    abstract void issueBook(int bookId, int memberId);
    abstract void returnBook(int bookId, int memberId);
}

class Library extends LibraryOperations {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<LibraryMember> members = new ArrayList<>();
    private HashMap<Integer, Book> bookMap = new HashMap<>();
    private LinkedHashMap<Integer, IssueRecord> issueRecords = new LinkedHashMap<>();
    private HashSet<Integer> issuedBookIds = new HashSet<>();
    private int issueCounter = 1;

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<LibraryMember> getMembers() {
        return members;
    }

    public HashMap<Integer, Book> getBookMap() {
        return bookMap;
    }

    public LinkedHashMap<Integer, IssueRecord> getIssueRecords() {
        return issueRecords;
    }

    public HashSet<Integer> getIssuedBookIds() {
        return issuedBookIds;
    }

    public LibraryMember findMember(int memberId) {
        for (LibraryMember m : members) {
            if (m.getMemberId() == memberId) {
                return m;
            }
        }
        return null;
    }

    public void registerMember(LibraryMember member) {
        if (findMember(member.getMemberId()) != null) {
            System.out.println("Member ID already exists!");
            return;
        }
        members.add(member);
        System.out.println("Member Registered Successfully!");
        member.displayMemberDetails();
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered!");
            return;
        }
        for (LibraryMember m : members) {
            m.displayMemberDetails();
            System.out.println("--------------------");
        }
    }

    public void searchMember(int memberId) {
        LibraryMember m = findMember(memberId);
        if (m == null) {
            System.out.println("Member not found!");
        } else {
            m.displayMemberDetails();
        }
    }

    public void searchBook(int bookId) {
        Book book = bookMap.get(bookId);
        if (book == null) {
            System.out.println("Book not found!");
        } else {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Book Name: " + book.getBookName());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Category: " + book.getCategory());
            System.out.println("Available Copies: " + book.getAvailableCopies());
        }
    }

    @Override
    public void addBook(Book book) {
        if (bookMap.containsKey(book.getBookId())) {
            System.out.println("Book ID already exists!");
            return;
        }
        books.add(book);
        bookMap.put(book.getBookId(), book);
        System.out.println("Book Added Successfully!");
    }

    @Override
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        System.out.println("ID\tName\t\tAuthor\t\tCategory\tCopies");
        for (Book b : books) {
            System.out.println(b.getBookId() + "\t" + b.getBookName() + "\t\t" + b.getAuthor() + "\t\t" + b.getCategory() + "\t" + b.getAvailableCopies());
        }
        System.out.println("Total Books: " + books.size());
        System.out.println("Books with zero copies:");
        for (Book b : books) {
            if (b.getAvailableCopies() == 0) {
                System.out.println(b.getBookId() + " - " + b.getBookName());
            }
        }
    }

    @Override
    public void issueBook(int bookId, int memberId) {
        LibraryMember member = findMember(memberId);
        if (member == null) {
            System.out.println("Member not found!");
            return;
        }
        Book book = bookMap.get(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (book.getAvailableCopies() <= 0) {
            System.out.println("No copies available!");
            return;
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        IssueRecord record = new IssueRecord(issueCounter, bookId, memberId, "Issued");
        issueRecords.put(issueCounter, record);
        issueCounter++;
        issuedBookIds.add(bookId);
        System.out.println("\nBook Issued Successfully!");
        System.out.println("Member: " + member.getMemberName());
        System.out.println("Book: " + book.getBookName());
        System.out.println("Available Copies: " + book.getAvailableCopies());
    }

    @Override
    public void returnBook(int bookId, int memberId) {
        IssueRecord found = null;
        for (IssueRecord r : issueRecords.values()) {
            if (r.getBookId() == bookId && r.getMemberId() == memberId && r.getStatus().equals("Issued")) {
                found = r;
                break;
            }
        }
        if (found == null) {
            System.out.println("No active issue record found for this member and book!");
            return;
        }
        found.setStatus("Returned");
        Book book = bookMap.get(bookId);
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        boolean stillIssued = false;
        for (IssueRecord r : issueRecords.values()) {
            if (r.getBookId() == bookId && r.getStatus().equals("Issued")) {
                stillIssued = true;
                break;
            }
        }
        if (!stillIssued) {
            issuedBookIds.remove(bookId);
        }
        System.out.println("Book Returned Successfully!");
        System.out.println("Book: " + book.getBookName());
        System.out.println("Available Copies: " + book.getAvailableCopies());
    }

    public void displayIssuedBooks() {
        if (issueRecords.isEmpty()) {
            System.out.println("No issue records found!");
            return;
        }
        for (IssueRecord r : issueRecords.values()) {
            Book book = bookMap.get(r.getBookId());
            String bookName = (book != null) ? book.getBookName() : "Unknown";
            System.out.println("Issue ID: " + r.getIssueId() + " | Member ID: " + r.getMemberId() + " | Book ID: " + r.getBookId() + " | Book: " + bookName + " | Status: " + r.getStatus());
        }
    }

    public void generateReport() {
        int totalCopies = 0;
        for (Book b : books) {
            totalCopies = totalCopies + b.getAvailableCopies();
        }
        int activeIssues = 0;
        for (IssueRecord r : issueRecords.values()) {
            if (r.getStatus().equals("Issued")) {
                activeIssues++;
            }
        }
        System.out.println("---------- LIBRARY REPORT ----------");
        System.out.println("Total Books Registered: " + books.size());
        System.out.println("Total Registered Members: " + members.size());
        System.out.println("Total Active Issue Records: " + activeIssues);
        System.out.println("Total Available Book Copies: " + totalCopies);
        System.out.println("Books Currently Unavailable:");
        for (Book b : books) {
            if (b.getAvailableCopies() == 0) {
                System.out.println(b.getBookId() + " - " + b.getBookName());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        library.addBook(new Book(101, "Java Programming", "James Gosling", "Programming", 5));
        library.addBook(new Book(102, "Python Basics", "Guido van Rossum", "Programming", 3));
        library.registerMember(new Student(1001, "Rahul", "9876543210", "CSE", 2));
        library.registerMember(new Faculty(2001, "Dr. Sharma", "9123456780", "CSE", "Professor"));

        int choice = 0;
        while (choice != 12) {
            System.out.println("\n========================================");
            System.out.println("          LIBRARY MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Register Student");
            System.out.println("5. Register Faculty");
            System.out.println("6. Display All Members");
            System.out.println("7. Search Member");
            System.out.println("8. Issue Book");
            System.out.println("9. Return Book");
            System.out.println("10. Display Issued Books");
            System.out.println("11. Generate Library Report");
            System.out.println("12. Exit");
            System.out.print("\nEnter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Book ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Book Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Author Name: ");
                String author = sc.nextLine();
                System.out.print("Enter Category: ");
                String category = sc.nextLine();
                System.out.print("Enter Available Copies: ");
                int copies = sc.nextInt();
                library.addBook(new Book(id, name, author, category, copies));
            } else if (choice == 2) {
                library.displayBooks();
            } else if (choice == 3) {
                System.out.print("Enter Book ID: ");
                int id = sc.nextInt();
                library.searchBook(id);
            } else if (choice == 4) {
                System.out.print("Enter Member ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Member Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Contact Number: ");
                String contact = sc.nextLine();
                System.out.print("Enter Department: ");
                String dept = sc.nextLine();
                System.out.print("Enter Year: ");
                int year = sc.nextInt();
                library.registerMember(new Student(id, name, contact, dept, year));
            } else if (choice == 5) {
                System.out.print("Enter Member ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Member Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Contact Number: ");
                String contact = sc.nextLine();
                System.out.print("Enter Department: ");
                String dept = sc.nextLine();
                System.out.print("Enter Designation: ");
                String designation = sc.nextLine();
                library.registerMember(new Faculty(id, name, contact, dept, designation));
            } else if (choice == 6) {
                library.displayMembers();
            } else if (choice == 7) {
                System.out.print("Enter Member ID: ");
                int id = sc.nextInt();
                library.searchMember(id);
            } else if (choice == 8) {
                System.out.print("Enter Member ID: ");
                int memberId = sc.nextInt();
                System.out.print("Enter Book ID: ");
                int bookId = sc.nextInt();
                library.issueBook(bookId, memberId);
            } else if (choice == 9) {
                System.out.print("Enter Member ID: ");
                int memberId = sc.nextInt();
                System.out.print("Enter Book ID: ");
                int bookId = sc.nextInt();
                library.returnBook(bookId, memberId);
            } else if (choice == 10) {
                library.displayIssuedBooks();
            } else if (choice == 11) {
                library.generateReport();
            } else if (choice == 12) {
                System.out.println("Thank you! Exiting...");
            } else {
                System.out.println("Invalid Choice! Try again.");
            }
        }
        sc.close();
    }
}