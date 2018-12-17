package com.mine.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mine.tasks.blog.model.Comment;
import com.mine.tasks.blog.model.Post;
import com.mine.tasks.blog.repository.CommentRepository;
import com.mine.tasks.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tasks.blog.model.dto.CommentDto;
import com.mine.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
    private PostRepository postRepository;

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		return commentRepository.findByPostId(postId).stream()
				.map(comment -> new CommentDto(comment.getId(), comment.getContent(),
						comment.getAuthor(), comment.getCreationDate())).collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(NewCommentDto newCommentDto) {

        Optional<Post> existingPost = postRepository.findById(newCommentDto.getPostId());
        if(!existingPost.isPresent()) {
            return -1L;
        }

	    Comment newComment = new Comment();
	    newComment.setAuthor(newCommentDto.getAuthor());
	    newComment.setContent(newCommentDto.getContent());
	    newComment.setCreationDate(LocalDateTime.now());
	    newComment.setPost(existingPost.get());
        newComment = commentRepository.save(newComment);

	    return newComment.getId();
	}
}
