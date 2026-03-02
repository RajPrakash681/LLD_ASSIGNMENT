import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;
import java.util.List;

public class TryIt {

    public static void main(String[] args) {

        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("created: " + t1);

        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("after assign: " + t2);
        System.out.println("after escalate: " + t3);
        System.out.println("original unchanged: " + t1);

        // try to modify the tags list from outside
        List<String> tags = t3.getTags();
        try {
            tags.add("HACKED");
        } catch (UnsupportedOperationException e) {
            System.out.println("tags are unmodifiable, got: " + t3.getTags());
        }

        // build one manually
        IncidentTicket t4 = IncidentTicket.builder("MANUAL-01", "dev@example.com", "DB timeout on login")
                .description("read replica keeps timing out")
                .priority("HIGH")
                .tag("DB")
                .tag("BACKEND")
                .source("WEBHOOK")
                .slaMinutes(60)
                .build();
        System.out.println("manual ticket: " + t4);
    }
}
