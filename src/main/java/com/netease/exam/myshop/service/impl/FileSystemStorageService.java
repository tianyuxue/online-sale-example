package com.netease.exam.myshop.service.impl;

import com.netease.exam.myshop.service.StorageService;
import com.netease.exam.myshop.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService{

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties)
    {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    /**
     * 如果上传目录不存在，则创建目录
     */
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    /**
     * 执行上传文件的存储
     * 返回存储在服务器上的文件名
     */
    @Override
    public String store(MultipartFile file) {
        String orgianlName= StringUtils.cleanPath(file.getOriginalFilename());
        String filename = FileUploadUtils.buildUploadFileName(file);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + orgianlName);
            }
            Files.copy(file.getInputStream(),
                       this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + orgianlName, e);
        }
        return filename;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    /**
     * 根据文件名，读取已经上传了的文件
     * @param filename
     * @return
     */
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {

    }
}
