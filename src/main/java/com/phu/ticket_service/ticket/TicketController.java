package com.phu.ticket_service.ticket;

import com.phu.ticket_service.ticket.model.TicketRequest;
import com.phu.ticket_service.ticket.model.TicketResponse;
import com.phu.ticket_service.ticket.model.TicketUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody @Valid TicketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(request));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets (@RequestParam(defaultValue = "status") String sortBy,
                                                              @RequestParam(defaultValue = "asc") String direction){
        return ResponseEntity.ok(ticketService.findAllTickets(sortBy, direction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long id, @RequestBody @Valid TicketUpdateRequest request){
        return ResponseEntity.ok(ticketService.updateTicket(id, request));
    }

    @GetMapping("/status")
    public ResponseEntity<List<TicketResponse>> getTicketsByStatus(@RequestParam String status){
        return ResponseEntity.ok(ticketService.findTicketByStatus(status));
    }
}
