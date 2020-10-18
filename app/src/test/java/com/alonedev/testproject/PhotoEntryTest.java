package com.alonedev.testproject;

import com.alonedev.testproject.model.PhotoModel;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PhotoEntryTest {
    private String json;
    private PhotoModel photo;

    @Before
    public void setUp() throws Exception {
        json = getStringFromFile("Photo.json");
        photo = new Gson().fromJson(json, PhotoModel.class);
    }

    @Test
    public void parseJsonDataResult_getAlbumId() throws Exception {
        Assert.assertEquals(1, photo.getAlbumId(), 1);
    }

    @Test
    public void parseJsonDataResult_getId() throws Exception {
        Assert.assertEquals(1, photo.getId(), 1);
    }

    @Test
    public void parseJsonDataResult_getTitle() throws Exception {
        Assert.assertEquals("accusamus beatae ad facilis cum similique qui sunt", photo.getTitle(), "accusamus beatae ad facilis cum similique qui sunt");
    }

    @Test
    public void parseJsonDataResult_getUrl() throws Exception {
        Assert.assertEquals("https://via.placeholder.com/600/92c952", photo.getUrl(), "https://via.placeholder.com/600/92c952");
    }

    @Test
    public void parseJsonDataResult_getThumbnailUrl() throws Exception {
        Assert.assertEquals("https://via.placeholder.com/150/92c952", photo.getThumbnailUrl(), "https://via.placeholder.com/150/92c952");
    }

    @After
    public void tearDown() {
        json = null;
        photo = null;
    }


    //filereader
    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public String getStringFromFile(String filePath) throws Exception {
        String fileName = System.getProperty("user.dir") + "/src/test/java/com/alonedev/testproject/data/";
        final InputStream stream = new FileInputStream(fileName + filePath);
        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }
}