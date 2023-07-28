package com.sm.socialmedia.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.sm.socialmedia.entity.Post;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class PostSpecification implements Specification<Post> {
    private final UUID userId;
    private final UUID groupId;

    //create a new predicate list
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (Objects.nonNull(userId)) {
            predicates.add(builder.equal(root.get("userId"), String.valueOf(userId)));
        }
        if (Objects.nonNull(groupId)) {
            predicates.add(builder.equal(root.get("groupId"), String.valueOf(groupId)));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}