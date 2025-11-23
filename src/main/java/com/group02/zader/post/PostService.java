package com.group02.zader.post;

import com.group02.zader.common.dto.PostCreateDTO;
import com.group02.zader.common.entity.Member;
import com.group02.zader.common.entity.Post;
import com.group02.zader.common.entity.PostMedia;
import com.group02.zader.common.enums.MediaType;
import com.group02.zader.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;
    private final UserRepository userRepository;
    
    // root
    private final String rootUploadDir;

    public PostService(PostRepository postRepository, 
                       PostMediaRepository postMediaRepository, 
                       UserRepository userRepository,
                       @Value("${app.upload.dir}") String rootUploadDir) {
        this.postRepository = postRepository;
        this.postMediaRepository = postMediaRepository;
        this.userRepository = userRepository;
        this.rootUploadDir = rootUploadDir;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public void createPost(PostCreateDTO dto, String username) throws IOException {
        Member author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // create post
        Post post = Post.builder()
                .member(author)
                .content(dto.getContent())
                .visibility(dto.getVisibility())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Post savedPost = postRepository.save(post);

        // upload
        if (dto.getImageFile() != null && !dto.getImageFile().isEmpty()) {
            MultipartFile file = dto.getImageFile();
            
            // file type
            String typeFolder = "image";
            MediaType mediaType = MediaType.IMAGE;

            String contentType = file.getContentType();
            if (contentType != null && contentType.startsWith("video")) {
                typeFolder = "video";
                mediaType = MediaType.VIDEO;
            }
            
            // call service
            String relativePath = saveFile(file, author.getMemberId(), typeFolder);

            PostMedia media = PostMedia.builder()
                    .post(savedPost)
                    .mediaUrl(relativePath)
                    .mediaType(mediaType)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .isDeleted(false)
                    .build();

            postMediaRepository.save(media);
        }
    }

    private String saveFile(MultipartFile file, Long userId, String typeFolder) throws IOException {
        // random file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        
        // file structure default: uploads / userId / post / [image|video] / filename
        // example: uploads/1/post/image/
        Path userPath = Paths.get(rootUploadDir, String.valueOf(userId), "post", typeFolder);

        if (!Files.exists(userPath)) {
            Files.createDirectories(userPath);
        }

        // save file
        Files.copy(file.getInputStream(), userPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        
        // return relative path to file
        // result: 1/post/image/filename.jpg
        return String.valueOf(userId) + "/post/" + typeFolder + "/" + fileName;
    }
}