package com.ynov.netflix.posters.controllers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.netflix.posters.daos.IPosterDao;
import com.ynov.netflix.posters.models.Poster;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class PosterController {
    

    static OkHttpClient client = new OkHttpClient();

    public static Map<String, Object> run(String url) throws IOException {
        Map<String, Object> responseData = new HashMap<String, Object>();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            responseData.put("result", response.body().string());
            responseData.put("code", response.code());
            return responseData;
        }
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    Map<String, Object> post(String url, String json) throws IOException {
        Map<String, Object> responseData = new HashMap<String, Object>();
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            responseData.put("result", response.body().string());
            responseData.put("code", response.code());
            return responseData;
        }
    }

    @Autowired
    IPosterDao posterDao;
     LocalTime now;

    @GetMapping("/poster")
    public List<Poster> getAllPoster() {

        return posterDao.findAll();
    }

    @GetMapping("/poster/{idPoster}")
    public Optional<Poster> getOnePoster(@PathVariable String idPoster) {

        //Date d = new Date();
        
       // System.out.println(d.getHours());
       // System.out.println("bien vu");
        
        
        return posterDao.findById(idPoster);
    }

    @GetMapping("/posterByIdContent/{idContent}")
    public Poster getOnePosterById(@PathVariable String idContent) {

        
        return posterDao.findByName(idContent);
    }



    @PostMapping("/poster")
    public boolean addPoster(@RequestBody Poster poster) {
        posterDao.save(poster);
        return true;
    }

    public PosterController(LocalTime now) {
        this.now = now;
      }
    

    
}
