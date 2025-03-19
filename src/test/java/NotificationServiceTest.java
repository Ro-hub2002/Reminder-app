import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.reminder.fx.services.NotificationService;

public class NotificationServiceTest {

    @Test
    void testSendNotification() {
        NotificationService service = Mockito.mock(NotificationService.class);

        service.sendNotification("Test", "Test message");
        verify(service).sendNotification("Test", "Test message");
    }
}
