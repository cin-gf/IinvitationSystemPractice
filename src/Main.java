import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 模擬從資料庫中取得使用者列表
        List<Member> allMembers = loadMembersFromDatabase();

        // 指定主角（例如 UserA）
        Member mainMember = new Member("測試號", "test@email.com", 21, "女", "Taipei");

        // 從 allUsers 移除主角自己
        allMembers.removeIf(user -> user.getEmail().equalsIgnoreCase(mainMember.getEmail()));

        // 建立篩選器，顯示「篩選條件表單」
        Filter filter = new Filter(20, 25, "Any", "Taipei");

        // 篩選符合條件的使用者
        List<Member> filteredMembers = filter.filterMembers(allMembers, filter);
        System.out.println("符合條件的使用者：");
        int index = 1;
        for (Member u : filteredMembers) {
            System.out.println(index + ". " + u.getName());
            index++;
        }

        // 建立邀請系統
        InvitationManager manager = new InvitationManager();

        // 發送邀請
        for (Member u : filteredMembers) {
            mainMember.Invite(u, manager);
        }
    }

    // 模擬從資料庫讀取使用者資料的方法
    public static List<Member> loadMembersFromDatabase() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("Cin", "cin@email.com", 22, "女", "Taipei"));
        members.add(new Member("Tom", "tom@email.com", 24, "男", "Kaohsiung"));
        members.add(new Member("Amy", "amy@email.com", 20, "女", "Taipei"));
        members.add(new Member("John", "john@email.com", 30, "男", "Tainan"));
        members.add(new Member("Gisoo", "gisoo@email.com", 21, "女", "Taipei")); // 注意大小寫一致
        return members;
    }
}
