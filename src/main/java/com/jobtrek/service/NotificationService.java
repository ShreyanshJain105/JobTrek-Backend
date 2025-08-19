package com.jobtrek.service;

import com.jobtrek.dto.NotificationDTO;
import com.jobtrek.entity.Notification;
import com.jobtrek.exception.JobPortalException;

import java.util.List;

public interface NotificationService {

    public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException;
    public List<Notification> getUnreadNotification(Long userId);

   public  void readNotification(Long id) throws JobPortalException;
}
