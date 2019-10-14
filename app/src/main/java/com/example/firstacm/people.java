package com.example.firstacm;

public class people {

    private String Image;
    private String Name;
    private String Post;

    public people() {

    }

    public people(String image, String name, String post) {
        Image = image;
        Name = name;
        Post = post;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }
}
