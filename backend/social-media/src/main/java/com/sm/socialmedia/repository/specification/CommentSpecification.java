package com.sm.socialmedia.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sm.socialmedia.entity.Comment;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class CommentSpecification implements Specification<Comment> {
    private final Integer userId;
    private final Integer postId;

    //create a new predicate list
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (Objects.nonNull(userId)) {
            predicates.add(builder.equal(root.get("userId"), String.valueOf(userId)));
        }
        if (Objects.nonNull(postId)) {
            predicates.add(builder.equal(root.get("postId"), String.valueOf(postId)));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}