package com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.File;

@Repository
public interface FileRepository extends CrudRepository<File, String> {

	List<File> findAll();
}
