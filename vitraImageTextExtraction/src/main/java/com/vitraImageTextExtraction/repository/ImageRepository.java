package com.vitraImageTextExtraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitraImageTextExtraction.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
