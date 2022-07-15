package com.wd.book.dao;

import com.wd.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList();
    Book getBook(Integer id);
}
