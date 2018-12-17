package com.mine.tasks.blog.rest;

import com.mine.tasks.blog.model.dto.CommentDto;
import com.mine.tasks.blog.model.dto.NewCommentDto;
import com.mine.tasks.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentsForPost(@PathVariable Long id) {
        return commentService.getCommentsForPost(id);
    }

    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<Long> addComment(@PathVariable Long id, @RequestBody NewCommentDto newCommentdto) {
        newCommentdto.setPostId(id);
        Long response = commentService.addComment(newCommentdto);

        if (response == -1) {
            return new ResponseEntity(response, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, null, HttpStatus.CREATED);
    }
}
