package com.foundation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class MyGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryId;
    private String title;
    @Column(length = 5000)
    private String description;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    @JsonIgnore
    private String image;
    
    
    public MyGallery() {
    }
    
    public MyGallery(Long galleryId, String title, String description, String image) {
        this.galleryId = galleryId;
        this.title = title;
        this.description = description;
        this.image = image;
    }
    
    public Long getGalleryId() {
        return galleryId;
    }
    
    public void setGalleryId(Long galleryId) {
        this.galleryId = galleryId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Blog{" +
                "galleryId=" + galleryId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
