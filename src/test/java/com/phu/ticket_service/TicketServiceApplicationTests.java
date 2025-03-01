package com.phu.ticket_service;

import com.phu.ticket_service.ticket.TicketRepository;
import com.phu.ticket_service.ticket.model.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
class TicketServiceApplicationTests {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.32")
			.withDatabaseName("ticket")
			.withUsername("root")
			.withPassword("");

	static {
		mysql.start();
		System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
		System.setProperty("spring.datasource.username", mysql.getUsername());
		System.setProperty("spring.datasource.password", mysql.getPassword());
	}

	@Autowired
	private TicketRepository ticketRepository;

	@Test
	void testTicketCreation() {
		Ticket ticket = Ticket.builder()
				.title("Test Ticket")
				.description("This is a test ticket")
				.contact("john.doe@example.com")
				.information("Test information")
				.build();
		Ticket savedTicket = ticketRepository.save(ticket);

		assertNotNull(savedTicket.getId());
	}
}
