package com.angel.dao;

import com.angel.po.Book;

import java.util.List;

public interface BookDao
{
	List<Book> queryBooks();
}
