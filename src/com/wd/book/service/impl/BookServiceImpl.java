package com.wd.book.service.impl;

import com.wd.book.dao.BookDAO;
import com.wd.book.pojo.Book;
import com.wd.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;
    @Override
    public List<Book> getBookList() {
        List<Book> bookList = bookDAO.getBookList();
        return bookList;
    }
}
