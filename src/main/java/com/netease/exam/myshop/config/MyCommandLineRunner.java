package com.netease.exam.myshop.config;

import com.netease.exam.myshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    StorageService storageService;

    @Override
    public void run(String... strings) throws Exception {
        storageService.init();
    }
}
