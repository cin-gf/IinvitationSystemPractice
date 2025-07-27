import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// 將 Invitation 與 InvitationManager 職責整合為 InvitationSystem（邀請紀錄與管理功能一體）
public class InvitationManager {
    private static final int MaxInvites = 10;
    private static final AtomicInteger counter = new AtomicInteger(1);
    private final List<Invitation> invitationRecords = new ArrayList<>();

    // 內部類別 Invitation（邀請實體）nested、contains 
    public static class Invitation {
        private String invitationId;
        private Member sender;
        private Member receiver;
        private InvitationStatus status;
        private Date dateSent;

        public Invitation(Member sender, Member receiver) {
            this.invitationId = "INV" + counter.getAndIncrement();
            this.sender = sender;
            this.receiver = receiver;
            this.status = InvitationStatus.Pending;
            this.dateSent = new Date();
        }

        public void accept() { this.status = InvitationStatus.Accepted; }
        public void decline() { this.status = InvitationStatus.Declined; }
        public String getInvitationId() { return invitationId; }
        public Member getSender() { return sender; }
        public Member getReceiver() { return receiver; }
        public InvitationStatus getStatus() { return status; }
        public Date getDateSent() { return dateSent; }
    }

    // 可否發送邀請
    public boolean canInvite(Member member) {
        return member.getDailyInvitationCount() < MaxInvites;
    }

    // 建立邀請 + 發送通知 + 紀錄邀請
    public Invitation createInvitation(Member sender, Member receiver) {
        Invitation invitation = new Invitation(sender, receiver);
        invitationRecords.add(invitation);
        System.out.println("邀請已送出給 " + receiver.getName() + "，邀請ID: " + invitation.getInvitationId());

        notifyInvitation(invitation);
        return invitation;
    }

    // 發送通知
    public void notifyInvitation(Invitation invitation) {
        String msg = "您收到來自 " + invitation.getSender().getName() + " 的邀請。";
        Notification notification = new Notification(msg, invitation.getReceiver());
        notification.sendNotification();
    }

    // Getter for invitation records (optional)
    public List<Invitation> getInvitationRecords() {
        return invitationRecords;
    }
}
