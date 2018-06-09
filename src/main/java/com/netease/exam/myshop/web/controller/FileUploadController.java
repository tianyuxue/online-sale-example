package com.netease.exam.myshop.web.controller;

import com.netease.exam.myshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
public class FileUploadController {
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }

    @RequestMapping(path="/api/upload", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadHandler(@RequestParam("file") MultipartFile file)
    {
        Map<String,Object> model = new HashMap<>();
        String filename = storageService.store(file);
        String url = "/files/" + filename;
        model.put("code", 200);
        model.put("message", "上传成功");
        model.put("result", url);
        System.out.println(model);
        return model;
    }

}
