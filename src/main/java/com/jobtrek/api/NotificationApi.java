package com.jobtrek.api;

import com.jobtrek.dto.ResponseDTO;
import com.jobtrek.entity.Notification;
import com.jobtrek.exception.JobPortalException;
import com.jobtrek.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/notification")
public class NotificationApi {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Notification>> getNotification(@PathVariable Long userId){
        return new ResponseEntity<>(notificationService.getUnreadNotification(userId), HttpStatus.OK);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<ResponseDTO> readNotification(@PathVariable Long id) throws JobPortalException {
        notificationService.readNotification(id);
        return new ResponseEntity<>(new ResponseDTO("Success"),HttpStatus.OK);
    }

}
