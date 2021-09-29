import java.util.*;
class Book
{
    String book_ID;
    String book_name;
    String author;
    String language;
    String ISBN;
    String publisher;
    boolean isborrowed;
    int time_borrowed;
    Student borrower;
    Date borrow_ddl;

    String getISBN()
    {
        return this.ISBN;
    }

    Student getBorrower()
    {
        if (this.isborrowed)
            return this.borrower;
        else
            return null;
    }


    int getTimeBorrowed()
    {
        return this.time_borrowed;
    }

    Date getBorrowDDL()
    {
        return this.borrow_ddl;
    }
}

class Student
{
    String stu_ID;
    String stu_name;
    boolean sex_ismale;
    String phone_number;
    String email;
    int max_borrow_num;
    Book book_borrowed;

    boolean isBookBorrowed(Book book)
    {
        if (this.book_borrowed == book)
            return true;
        return false;
    }

    String getPhoneNumber()
    {
        return this.phone_number;
    }

    String getEmail()
    {
        return this.email;
    }

    void UpdateMaxBorrowNum(int n)
    {
        this.max_borrow_num = n;
    }
}

class Manager
{
    String manager_ID;
    String manager_name;
    String job_num;
    int working_years;
    int cur_book_num;
    Book book_repo[] = new Book[10000];

    Manager(int cur_book_num)
    {
        this.cur_book_num = cur_book_num;
    }

    void AddBook(Book book)
    {
        book_repo[cur_book_num] = book;
        this.cur_book_num++;
    }

    void DelBook(Book book)
    {
        this.cur_book_num--;
        book_repo[cur_book_num] = null;
    }

    void borrow(Book book, Student student)
    {
        book.isborrowed = true;
        book.borrower = student;
        student.book_borrowed = book;
    }

    boolean isBookinRepo(Book book)
    {
        for (int i = 0; i < this.cur_book_num; i++)
            if (this.book_repo[i] == book)
                return true;
        return false;
    }
}