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

    public List<UUID> getFollowersForUser(UUID userId) {
        var userFollowList = userFollowRepository.findAllByFollowingId(userId);
        return userFollowList.stream()
                             .map(UserFollow::getUserId)
                             .toList();
    }

    public List<UUID> getUsersFollowedByUser(UUID userId) {
        var userFollowList = userFollowRepository.findAllByUserId(userId);
        return userFollowList.stream()
                             .map(UserFollow::getUserId)
                             .toList();
    }

    public List<UUID> getUsersFollowingGroup(UUID userId) {
        var groupFollowList = groupFollowRepository.findAllByFollowerId(userId);
        return groupFollowList.stream()
                             .map(GroupFollow::getGroupId)
                             .toList();
    }

    public List<UUID> getFollowersForGroup(UUID groupId) {
        var groupFollowList = groupFollowRepository.findAllByGroupId(groupId);
        return groupFollowList.stream()
                              .map(GroupFollow::getFollowerId)
                              .toList();

    }

    public void followUser(FollowCommand followCommand) {
        var userFollowOpt = userFollowRepository.findByUserIdAndFollowingId(
                followCommand.getUserId(),
                followCommand.getFollowingId());

        userFollowOpt.ifPresentOrElse(userFollow -> {
            userFollowRepository.delete(userFollow);
        }, () -> {
            var userFollow = new UserFollow();
            userFollow.setUserId(followCommand.getUserId());
            userFollow.setFollowingId(followCommand.getFollowingId());
            userFollowRepository.save(userFollow);
        });
    }

    public void followGroup(FollowCommand followCommand) {
        var groupFollowOpt = groupFollowRepository.findByFollowerIdAndGroupId(
                followCommand.getUserId(),
                followCommand.getFollowingId());

        groupFollowOpt.ifPresentOrElse(groupFollow -> {
            groupFollowRepository.delete(groupFollow);
        }, () -> {
            var groupFollow = new GroupFollow();
            groupFollow.setFollowerId(followCommand.getUserId());
            groupFollow.setGroupId(followCommand.getFollowingId());
            groupFollowRepository.save(groupFollow);
        });
    }

}
