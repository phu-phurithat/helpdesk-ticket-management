package com.phu.ticket_service.ticket;

import com.phu.ticket_service.handler.TicketNotFoundException;
import com.phu.ticket_service.ticket.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketResponse createTicket(TicketRequest request){
        Ticket ticket = ticketMapper.toTicket(request);
        ticket.setStatus(Status.PENDING);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }

    public List<TicketResponse> findAllTickets(String sortBy, String sortDirection) {
        return ticketRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection), sortBy)).stream()
                .map(ticketMapper::toTicketResponse).toList();
    }

    public TicketResponse updateTicket(Long id, TicketUpdateRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setContact(request.getContact());
        ticket.setStatus(Status.valueOf(request.getStatus()));
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }

    public List<TicketResponse> findTicketByStatus(String status) {
        status = status.toUpperCase();
        if (!status.equals("PENDING") && !status.equals("ACCEPTED")
                && !status.equals("RESOLVED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Status must be PENDING, ACCEPTED, RESOLVED, REJECTED");
        }
        return ticketRepository.findByStatus(Status.valueOf(status)).stream()
                .map(ticketMapper::toTicketResponse).toList();
    }
}
