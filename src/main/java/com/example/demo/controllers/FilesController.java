package com.example.demo.controllers;

import com.example.demo.config.MyUserDetails;
import com.example.demo.domain.FileEntity;
import com.example.demo.domain.FileResponse;
import com.example.demo.domain.User;
import com.example.demo.service.FileService;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")// везде где endpoint = files будет падать в этот контроллер
public class FilesController {
    @Autowired
    private UserRepository userRepository;
    private final FileService fileService;

    @Autowired
    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof MyUserDetails) {
                username = ((MyUserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            User user =  userRepository.findByUsername(username).orElse(null);

            fileService.saveNew(file, user);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("/list")
    public List<FileResponse> list() {
        return fileService.getAllFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}/courses")
    public List<FileResponse> getMyCourses(@PathVariable Long id){
        List<Long> coursesId = userRepository.findCoursesByUserId(id);
        List<FileEntity> courses = new ArrayList<>();
        Optional<FileEntity> checkFile;
        for (int i = 0; i < coursesId.size(); i++){
            checkFile = fileService.getFile(coursesId.get(i));
            if (checkFile.isPresent()) {
                FileEntity file = checkFile.get();
                courses.add(file);
            }
        }
        return courses.stream().map(this::mapToFileResponse).collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(FileEntity fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(String.valueOf(fileEntity.getId()))
                .toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(fileEntity.getId());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<FileEntity> fileEntityOptional = fileService.getFile(Long.valueOf(id));

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        FileEntity fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }
}
