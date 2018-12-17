package com.mine.tasks.blog.repository;

import com.mine.tasks.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
