package com.easybookstore.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easybookstore.backend.model.Books;

public interface BookDao extends JpaRepository<Books, Long> {

}
