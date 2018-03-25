package cn.edu.upc.yb.match.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    private int id;
    private String teamName;
    private String captainName;
    private String tel;
    private String qq;
    private String email;
    private String college;
    private String rank;
    private String member1Name;
    private String member1College;
    private String member1Rank;
    private String member2Name;
    private String member2College;
    private String member2Rank;
    private String ideaName;
    private String ideaInstructions;
    private int live;
    private String submit;

    public Team() {

    }

    public Team(String teamName, String captainName, String tel, String qq, String email, String college, String rank, String member1Name, String member1College, String member1Rank, String member2Name, String member2College, String member2Rank, String ideaName, String ideaInstructions) {
        this.teamName = teamName;
        this.captainName = captainName;
        this.tel = tel;
        this.qq = qq;
        this.email = email;
        this.college = college;
        this.rank = rank;
        this.member1Name = member1Name;
        this.member1College = member1College;
        this.member1Rank = member1Rank;
        this.member2Name = member2Name;
        this.member2College = member2College;
        this.member2Rank = member2Rank;
        this.ideaName = ideaName;
        this.ideaInstructions = ideaInstructions;
        this.live = 0;
        this.submit = "否";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMember1Name() {
        return member1Name;
    }

    public void setMember1Name(String member1Name) {
        this.member1Name = member1Name;
    }

    public String getMember1College() {
        return member1College;
    }

    public void setMember1College(String member1College) {
        this.member1College = member1College;
    }

    public String getMember1Rank() {
        return member1Rank;
    }

    public void setMember1Rank(String member1Rank) {
        this.member1Rank = member1Rank;
    }

    public String getMember2Name() {
        return member2Name;
    }

    public void setMember2Name(String member2Name) {
        this.member2Name = member2Name;
    }

    public String getMember2College() {
        return member2College;
    }

    public void setMember2College(String member2College) {
        this.member2College = member2College;
    }

    public String getMember2Rank() {
        return member2Rank;
    }

    public void setMember2Rank(String member2Rank) {
        this.member2Rank = member2Rank;
    }

    public String getIdeaName() {
        return ideaName;
    }

    public void setIdeaName(String ideaName) {
        this.ideaName = ideaName;
    }

    public String getIdeaInstructions() {
        return ideaInstructions;
    }

    public void setIdeaInstructions(String ideaInstructions) {
        this.ideaInstructions = ideaInstructions;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
}
