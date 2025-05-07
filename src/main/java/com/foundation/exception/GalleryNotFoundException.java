package com.foundation.exception;

public class GalleryNotFoundException extends RuntimeException{
    public GalleryNotFoundException(Long galleryId) {
        super("Gallery NOT Found With ID : " + galleryId);
    }
}
