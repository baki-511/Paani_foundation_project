package com.foundation.repository;

import com.foundation.entity.Blog;
import com.foundation.entity.MyGallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyGalleryRepository extends JpaRepository<MyGallery, Long> {

}
