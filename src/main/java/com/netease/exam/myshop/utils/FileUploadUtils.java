package com.netease.exam.myshop.utils;

import com.netease.exam.myshop.meta.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class FileUploadUtils {

    /**
     * 将字节流转化为字符串
     * @param bts
     * @return
     */
    private static String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;

        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 构建上传文件的文件名
     * 构造方式为: md5(用户名+系统时间)
     * @param file
     * @return
     */
    public static String buildUploadFileName(MultipartFile file)
    {
        Long timestamp = System.currentTimeMillis();

        Person user = SecurityUtils.getCurrentUser();

        String filename = user.getUserName() + timestamp.toString();

        String type = file.getContentType();

        String extension = type.substring(type.lastIndexOf("/") + 1);

        String realFilename;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(filename.getBytes());
            realFilename = FileUploadUtils.bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("创建上传图片，构造文件名失败");
        }

        return realFilename + "." + extension;
    }
}
