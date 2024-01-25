package com.example.demo.service;

import com.example.demo.domain.FileEntity;
import com.example.demo.domain.User;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private final FileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void saveNew(MultipartFile file, User user) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setUser(user);
        fileRepository.save(fileEntity);
    }

    public void save(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> getFile(Long id) {
        return fileRepository.findById(String.valueOf(id));
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
}
