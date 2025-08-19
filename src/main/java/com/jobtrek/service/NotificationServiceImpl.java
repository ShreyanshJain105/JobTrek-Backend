package com.jobtrek.service;

import com.jobtrek.dto.NotificationDTO;
import com.jobtrek.dto.NotificationStatus;
import com.jobtrek.entity.Notification;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.repository.NotificationRepository;
import com.jobtrek.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements  NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException {
        notificationDTO.setId(Utilities.getNextSequence("notification"));
        notificationDTO.setStatus(NotificationStatus.UNREAD);
        notificationDTO.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notificationDTO.toEntity());
    }

    @Override
    public List<Notification> getUnreadNotification(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void readNotification(Long id) throws JobPortalException{
        Notification noti = notificationRepository.findById(id).orElseThrow(()->new JobPortalException("No Notification found"));
        noti.setStatus(NotificationStatus.READ);
        notificationRepository.save(noti);



    }


}
