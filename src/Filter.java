import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    private int MinAge;
    private int MaxAge;
    private String gender;   
    private String region;   

    public Filter(int MinAge, int MaxAge, String gender, String region) {
        this.MinAge = MinAge;
        this.MaxAge = MaxAge;
        this.gender = gender;
        this.region = region;
    }

    // 驗證篩選條件
    public boolean matches(Member member) {
        int age = member.getAge();
        boolean ageMatch = age >= MinAge && age <= MaxAge;
        boolean genderMatch = gender.equalsIgnoreCase("Any") || gender.equalsIgnoreCase(member.getGender());
        boolean regionMatch = region.equalsIgnoreCase("Any") || region.equalsIgnoreCase(member.getRegion());

        return ageMatch && genderMatch && regionMatch;
    }

    public List<Member> filterMembers(List<Member> members, Filter filter) {
        return members.stream().filter(filter::matches).collect(Collectors.toList());
    }

    // Getter
    /*
    public int getMinAge() { return minAge; }
    public int getMaxAge() { return maxAge; }
    public String getGender() { return gender; }
    public String getRegion() { return region; }
     */
    
}
