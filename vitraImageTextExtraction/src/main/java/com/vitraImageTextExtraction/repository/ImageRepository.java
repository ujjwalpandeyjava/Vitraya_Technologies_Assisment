package com.vitraImageTextExtraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitraImageTextExtraction.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
