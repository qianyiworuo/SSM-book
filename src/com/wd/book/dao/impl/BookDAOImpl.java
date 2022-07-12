package com.wd.book.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.wd.book.dao.BookDAO;
import com.wd.book.pojo.Book;

import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        String sql = "SELECT id, bookImg, bookName, price, author, saleCount, bookCount, bookStatus FROM t_book WHERE bookStatus = 0";
        List<Book> bookList = executeQuery(sql);
        return bookList;
    }
}
