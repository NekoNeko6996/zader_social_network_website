package com.group02.zader.common.dto;

import com.group02.zader.common.enums.PostVisibility;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostCreateDTO {
    private String content;
    private MultipartFile imageFile;
    private PostVisibility visibility = PostVisibility.PUBLIC;
}