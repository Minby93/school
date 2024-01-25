package com.example.demo.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class FileResponse {
    private Long id;
    private String name;
    private Long size;
    private String url;
    private String contentType;
    private Long user_id;
}
