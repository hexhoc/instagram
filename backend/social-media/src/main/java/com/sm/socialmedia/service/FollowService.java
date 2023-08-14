package com.sm.socialmedia.service;

import java.util.List;
import java.util.UUID;

import com.sm.socialmedia.dto.follow.FollowCommand;
import com.sm.socialmedia.entity.GroupFollow;
import com.sm.socialmedia.entity.UserFollow;
import com.sm.socialmedia.repository.GroupFollowRepository;
import com.sm.socialmedia.repository.UserFollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class FollowService {
    private final UserFollowRepository userFollowRepository;
    private final GroupFollowRepository groupFollowRepository;

    public List<UUID> getFollowers(UUID userId) {
        var userFollowList = userFollowRepository.findAllByFollowedId(userId);
        return userFollowList.stream()
                             .map(UserFollow::getFollowerId)
                             .toList();
    }

    public List<UUID> getFollowed(UUID userId) {
        var userFollowList = userFollowRepository.findAllByFollowerId(userId);
        return userFollowList.stream()
                             .map(UserFollow::getFollowedId)
                             .toList();
    }

    public List<UUID> getGroupFollowers(UUID userId) {
        var groupFollowList = groupFollowRepository.findAllByFollowerId(userId);
        return groupFollowList.stream()
                             .map(GroupFollow::getGroupId)
                             .toList();
    }

    public List<UUID> getFollowedGroups(UUID groupId) {
        var groupFollowList = groupFollowRepository.findAllByGroupId(groupId);
        return groupFollowList.stream()
                              .map(GroupFollow::getFollowerId)
                              .toList();

    }

    public void followUser(FollowCommand followCommand) {
        var userFollowOpt = userFollowRepository.findByFollowerIdAndFollowedId(
                followCommand.getUserId(),
                followCommand.getFollowedId());

        userFollowOpt.ifPresentOrElse(userFollow -> {
            userFollowRepository.delete(userFollow);
        }, () -> {
            var userFollow = new UserFollow();
            userFollow.setFollowerId(followCommand.getUserId());
            userFollow.setFollowedId(followCommand.getFollowedId());
            userFollowRepository.save(userFollow);
        });
    }

    public void followGroup(FollowCommand followCommand) {
        var groupFollowOpt = groupFollowRepository.findByFollowerIdAndGroupId(
                followCommand.getUserId(),
                followCommand.getFollowedId());

        groupFollowOpt.ifPresentOrElse(groupFollow -> {
            groupFollowRepository.delete(groupFollow);
        }, () -> {
            var groupFollow = new GroupFollow();
            groupFollow.setFollowerId(followCommand.getUserId());
            groupFollow.setGroupId(followCommand.getFollowedId());
            groupFollowRepository.save(groupFollow);
        });
    }

}
