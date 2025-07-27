import java.util.Date;
public class Notification {
    private String message;
    private Member receiver;
    private Date timestamp;

    public Notification(String message, Member receiver) {
        this.message = message;
        this.receiver = receiver;
        this.timestamp = new Date(); // 加上通知時間
    }

    public void sendNotification() {
        //System.out.println("通知發送給 " + receiver.getName() + "：" + message);
        System.out.println("● Email 已發送至 " + receiver.getEmail());
        System.out.println("收件人: " + receiver.getName());
        System.out.println("內容: " + message);
        System.out.println("發送時間: " + timestamp);
        System.out.println("─────────────────────");
    }
    // Getter（可選）
    /* 
    public String getMessage() { return message; }
    public User getReceiver() { return receiver; }
    public Date getTimestamp() { return timestamp; }
    */
}
