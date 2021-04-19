package com.example.finalyearproject.model;

public class PictureModel {
    private String imageName, imageUrl;

    public PictureModel() {
    }

    public PictureModel(String imageName, String imageUrl) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUri(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
