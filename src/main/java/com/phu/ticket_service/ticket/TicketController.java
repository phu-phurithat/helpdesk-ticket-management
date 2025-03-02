package com.phu.ticket_service.ticket;

import com.phu.ticket_service.ticket.model.TicketRequest;
import com.phu.ticket_service.ticket.model.TicketResponse;
import com.phu.ticket_service.ticket.model.TicketUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody @Valid TicketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(request));
    }

    @GetMapping
    public ResponseEntity<Page<TicketResponse>> getAllTickets (@RequestParam(defaultValue = "lastUpdate" ) String sortBy,
                                                               @RequestParam (defaultValue = "desc" ) String direction,
                                                               @RequestParam(defaultValue = "10") int pageSize,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(required = false) String status){
        System.out.println(ticketService.findTicketsPageSortFilter(sortBy, direction,status, page, pageSize));
        return ResponseEntity.ok(ticketService.findTicketsPageSortFilter(sortBy, direction,status, page, pageSize));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long id, @RequestBody @Valid TicketUpdateRequest request){
        return ResponseEntity.ok(ticketService.updateTicket(id, request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TicketResponse> updateTicketStatus(@PathVariable Long id, @RequestParam String status){
        System.out.println(status);
        return ResponseEntity.ok(ticketService.updateTicketStatus(id, status));
    }
}
