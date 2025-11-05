package com.example.test.Util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FileUploadPathResolver {
    private String baseDirectory = "/hightech/upload";
    private final Map<String, String> extensionPaths = new HashMap<>();

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    // 확장자별 경로 설정
    public FileUploadPathResolver() {
    	//이미지
        extensionPaths.put("jpg", "images");
        extensionPaths.put("png", "images");
        extensionPaths.put("jpeg", "images");
        extensionPaths.put("svg", "images");
        //음원
        extensionPaths.put("ogg", "music");
        extensionPaths.put("mp3", "music");
        //문서
        extensionPaths.put("pdf", "documents");
        extensionPaths.put("txt", "documents");
        extensionPaths.put("hwp", "documents");
        extensionPaths.put("ppt", "documents");
        extensionPaths.put("pptx", "documents");
        extensionPaths.put("zip", "documents");
        //영상
        extensionPaths.put("mp4", "video");
        extensionPaths.put("webm", "video");
        extensionPaths.put("wmv", "video");
        extensionPaths.put("avi", "video");
        //프로그래밍 파일
        extensionPaths.put("js", "programming");
        extensionPaths.put("lua", "programming");
        extensionPaths.put("cs", "programming");
        extensionPaths.put("css", "programming");
        extensionPaths.put("sln", "programming");
        extensionPaths.put("html", "programming");
        extensionPaths.put("java", "programming");
        extensionPaths.put("c", "programming");
        extensionPaths.put("cpp", "programming");
        extensionPaths.put("h", "programming");
        extensionPaths.put("hh", "programming");
    }
    

    //저장 경로 지정
    public String getUploadPath(String fileName) {
        String extension = getFileExtension(fileName);	
        String subDirectory = extensionPaths.getOrDefault(extension, "others");
        return baseDirectory + "/" + subDirectory;
    }

    //확장자
    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1 || lastIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(lastIndex + 1).toLowerCase();
    }
}
