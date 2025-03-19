import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reminder.fx.entity.Reminder;
import com.reminder.fx.repository.ReminderRepository;
import com.reminder.fx.services.ReminderService;

@ExtendWith(MockitoExtension.class)
public class ReminderServiceTest {

    @Mock
    private ReminderRepository reminderRepository;

    @InjectMocks
    private ReminderService reminderService;

    @Test
    void testGetReminders() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        List<Reminder> fakeReminders = Arrays.asList(new Reminder(1L, "Test", now));

        when(reminderRepository.findPendingReminders(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(fakeReminders);

        List<Reminder> result = reminderService.getPendingReminders();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getReminder());

        verify(reminderRepository).findPendingReminders(any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void testGetRemindersEmptyList() {
        when(reminderRepository.findPendingReminders(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        List<Reminder> result = reminderService.getPendingReminders();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetRemindersException() {
        when(reminderRepository.findPendingReminders(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            reminderService.getPendingReminders();
        });
        assertEquals("Database error", exception.getMessage());

        verify(reminderRepository).findPendingReminders(any(LocalDateTime.class), any(LocalDateTime.class));
    }

}
