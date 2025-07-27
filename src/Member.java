//import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDate;
//import java.time.ZoneId;

public class Member {
    private static final AtomicInteger IDCounter = new AtomicInteger(1);
    private String ID;
    private String name;
    private String email;
    private int age;
    private String gender;
    private String region;
    private int dailyInvitationCount;   // 今日已發出的邀請次數
    private LocalDate  lastInvitationDate;    // 上一次邀請的日期（用來判斷是否要歸零邀請次數）

    public Member(String name, String email, int age, String gender, String region) {
        this.ID = UniqueId();
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.region = region;
        this.dailyInvitationCount = 0;
        this.lastInvitationDate = LocalDate.now(); 
    }

    // 生成唯一 ID
    private static String UniqueId() {
        return String.format("U%03d", IDCounter.getAndIncrement());
    }

    // 發送邀請
    public boolean Invite(Member M, InvitationManager IM) {
        resetInviteCount();

        if (IM.canInvite(this)) {
            IM.createInvitation(this, M); // 已內含通知功能
            this.dailyInvitationCount++;
            this.lastInvitationDate = LocalDate.now();
            return true;
        } else {
            System.out.println("今日邀請次數已達上限");
            return false;
        }
    }

    // 檢查是否需要重置邀請次數
    private void resetInviteCount() {
        if (!LocalDate.now().isEqual(lastInvitationDate)) {
            this.dailyInvitationCount = 0;
            this.lastInvitationDate = LocalDate.now();
        }
    }

    // Getter
    public int getDailyInvitationCount() { return dailyInvitationCount;}
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getID() { return ID; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getRegion() { return region; }
}
