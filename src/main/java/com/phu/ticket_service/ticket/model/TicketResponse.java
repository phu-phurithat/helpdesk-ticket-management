package com.phu.ticket_service.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {

    private Long id;
    private String title;
    private String description;
    private String contact;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastTicketUpdate;

}
