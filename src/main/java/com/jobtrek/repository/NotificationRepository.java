package com.jobtrek.repository;

import com.jobtrek.dto.NotificationStatus;
import com.jobtrek.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,Long> {
    public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
