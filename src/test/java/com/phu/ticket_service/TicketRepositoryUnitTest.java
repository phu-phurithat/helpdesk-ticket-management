package com.phu.ticket_service;

import com.phu.ticket_service.ticket.TicketRepository;
import com.phu.ticket_service.ticket.TicketService;
import com.phu.ticket_service.ticket.model.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketRepositoryUnitTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;  // Example service class (if applicable)

    @Test
    void testSaveTicket() {
        Ticket ticket = Ticket.builder()
                .id(1L)
                .title("Test Ticket")
                .description("This is a test ticket")
                .contact("john.doe@example.com")
                .information("Test information")
                .build();

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket savedTicket = ticketRepository.save(ticket);
        System.out.println(savedTicket);

        assertNotNull(savedTicket.getId());

        verify(ticketRepository, times(1)).save(ticket);
    }
}
