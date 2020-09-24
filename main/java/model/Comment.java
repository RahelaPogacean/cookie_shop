package model;

import javax.persistence.*;

@Table
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column
    private String clientName;

    @Column
    private String typeCookie;

    @Column
    private String content;

    @Column
    private int likeCount;

    public Comment(){

    }

    public Comment(String typeCookie, String clientName, String content){
        this.clientName = clientName;
        this.typeCookie = typeCookie;
        this.content = content;
    }

    public Comment(String typeCookie, String clientName, String content, int likeCount){
        this.typeCookie = typeCookie;
        this.clientName = clientName;
        this.content = content;
        this.likeCount = likeCount;
    }

    public Comment(int id, String type, String client, String content, int likes) {
        this.commentId = id;
        this.typeCookie = type;
        this.clientName = client;
        this.content = content;
        this.likeCount = likes;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTypeCookie() {
        return typeCookie;
    }

    public void setTypeCookie(String typeCookie) {
        this.typeCookie = typeCookie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", clientName='" + clientName + '\'' +
                ", typeCookie='" + typeCookie + '\'' +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                '}';
    }
}
