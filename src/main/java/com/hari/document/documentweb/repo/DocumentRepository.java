package com.hari.document.documentweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.document.documentweb.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
