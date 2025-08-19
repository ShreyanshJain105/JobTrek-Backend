package com.jobtrek.entity;


import com.jobtrek.dto.NotificationDTO;
import com.jobtrek.dto.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection ="notification" )
public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private LocalDateTime timestamp;
    private NotificationStatus status;

    public NotificationDTO toDto(){
        return new NotificationDTO(
                this.id,
                this.userId,
                this.message,
                this.action,
                this.route,
                this.timestamp,
                this.status
        );
    }
}
