package com.ontrack.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ontrack.model.Task;

public class TaskInboxServiceTest {

    @Test
    public void testGetTaskInboxWithValidStudentId() {
        TaskInboxService service = new TaskInboxService();
        List<Task> tasks = service.getTaskInbox("12345");
        
        assertEquals(2, tasks.size());
        
        Task task1 = tasks.get(0);
        assertEquals("task1", task1.getTaskId());
        assertEquals("Math Homework", task1.getTitle());
        assertEquals("Pending", task1.getFeedbackStatus());
        assertEquals(2, task1.getChatMessages().size());
        
        Task task2 = tasks.get(1);
        assertEquals("task2", task2.getTaskId());
        assertEquals("Science Project", task2.getTitle());
        assertEquals("Reviewed", task2.getFeedbackStatus());
        assertEquals(2, task2.getChatMessages().size());
    }
    
    @Test
    public void testGetTaskInboxWithInvalidStudentId() {
        TaskInboxService service = new TaskInboxService();
        List<Task> tasks = service.getTaskInbox("invalid");
        
        assertTrue(tasks.isEmpty());
    }
    
    @Test
    public void testGetTaskInboxWithNoTasks() {
        TaskInboxService service = new TaskInboxService();
        List<Task> tasks = service.getTaskInbox("empty");
        
        assertTrue(tasks.isEmpty());
    }
    
    @Test
    public void testGetTaskInboxWithDifferentStudentId() {
        TaskInboxService service = new TaskInboxService();
        List<Task> tasks = service.getTaskInbox("54321");
        
        assertTrue(tasks.isEmpty());
    }
    
    @Test
    public void testGetTaskInboxWithMockData() {
        TaskInboxService service = new TaskInboxService() {
            @Override
            public List<Task> getTaskInbox(String studentId) {
                // Mock data for student ID "12345"
                if ("12345".equals(studentId)) {
                    List<Task> mockTasks = new ArrayList<>();
                    
                    Task task1 = new Task();
                    task1.setTaskId("task1");
                    task1.setTitle("Math Homework");
                    task1.setSubmissionDate(new Date());
                    task1.setFeedbackStatus("Pending");
                    task1.setChatMessages(List.of("Please complete the exercise", "Done"));
                    
                    Task task2 = new Task();
                    task2.setTaskId("task2");
                    task2.setTitle("Science Project");
                    task2.setSubmissionDate(new Date());
                    task2.setFeedbackStatus("Reviewed");
                    task2.setChatMessages(List.of("Good work", "Thank you"));
                    
                    mockTasks.add(task1);
                    mockTasks.add(task2);
                    
                    return mockTasks;
                } else {
                    return super.getTaskInbox(studentId);
                }
            }
        };
        
        List<Task> tasks = service.getTaskInbox("12345");
        
        assertEquals(2, tasks.size());
        // Add more assertions if needed
    }
}


