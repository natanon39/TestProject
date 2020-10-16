package com.alonedev.testproject.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    private int albumId,id;
    private String title,url,thumbnailUrl;
    public Photo(int albumId,int id,String title,String url,String thumbnailUrl)
    {
        this.albumId=albumId;
        this.id=id;
        this.title=title;
        this.url=url;
        this.thumbnailUrl=thumbnailUrl;
    }
    public  int getAlbumId(){
        return this.albumId;
    }
    public  int getId(){
        return this.id;
    }
    public  String getTitle(){
        return this.title;
    }
    public  String getUrl(){
        return this.url;
    }
    public  String getThumbnailUrl(){
        return this.thumbnailUrl;
    }
}