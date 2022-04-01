package com.acme.todolist;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodolistApplicationTests {
	
	private static TotoItem item;

	@Test
	void contextLoads() {
	}
	
	@BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void justCreatedTodoItem() {
    	item = new TodoItem("1",Instant.now(),"first item just created");
    	assertFalse(item.finalContent().contains("[LATE!] "));
    }
    
    @Test
    void Created25hAgoTodoItem() {
    	item = new TodoItem("2",Instant.now().minus(25,ChronoUnit.HOURS),"second item created 25h ago");
    	assertTrue(item.finalContent().contains("[LATE!] ");
    }
    
    @Test
    void CreatedADayAgoTodoItem() {
    	item = new TodoItem("3",Instant.now().minus(1,ChronoUnit.DAYS),"third item created a day ago");
    	assertFalse(item.finalContent().contains("[LATE!] "));
    }
    
    @Test
    void CreatedApproxADayAgoTodoItem() {
    	item = new TodoItem("4",Instant.now().minus(1,ChronoUnit.DAYS).minus(1,ChronoUnit.SECONDS),"Fourth item created a day ago - 1s");
    	assertTrue(item.finalContent().contains("[LATE!] "));
    }

    @Test
    void justCreatedIsNotLate() {
	item = new TodoItem("5", Instant.now(), "fifth item just created");
	assertFalse(item.isLate());
   }

   @Test
   void ThirtyMinutesOldIsNotLate() {
	item = new TodoItem("6", Instant.now().minus(25,ChronoUnit.HOURS), "Sixth item created 25h ago");
	assertTrue(item.isLate());
   }

   @Test
   void TwoDaysOldIsLate() {
	item = new TodoItem("7", Instant.now().minus(1, ChronoUnit.DAYS), "seventh item created a day ago");
	assertFalse(item.isLate());
   }
    @Test
    void OneDayOneSecondOldIsLate() {
    	item = new TodoItem("8",Instant.now().minus(1,ChronoUnit.DAYS).minus(1,ChronoUnit.SECONDS),"Eighth item created a day ago - 1s");
    	assertTrue(item.finalContent().contains("[LATE!] "));
    }
}
