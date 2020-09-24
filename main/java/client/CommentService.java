package client;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.CommentDAO;
import model.Client;
import model.Comment;

import java.io.IOException;
import java.util.ArrayList;

public class CommentService {

    public void addComment(String type, String clientName, String content) throws IOException, ClassNotFoundException {
        CommentDAO commentDAO = new CommentDAO();
        Comment comment = new Comment(type, clientName, content);
        commentDAO.addComment(comment);
    }

    public Comment findCommentByType(String cookieType) throws IOException, ClassNotFoundException {
        CommentDAO commentDAO = new CommentDAO();
        Comment comment = commentDAO.getCommentByCookieType(cookieType);
        return comment;
    }

    public Comment findCommentByClient(String clientName) throws IOException, ClassNotFoundException {
        CommentDAO commentDAO = new CommentDAO();
        Comment comment = commentDAO.getCommentByClient(clientName);
        return comment;
    }

    public ArrayList<Comment> findAllComments(){
        CommentDAO commentDAO = new CommentDAO();
        ArrayList<Comment> comments = commentDAO.getCommentsList();
        return comments;
    }

    public Comment findCommentById(int id) {
        CommentDAO commentDAO = new CommentDAO();
        Comment comment = commentDAO.getCommentById(id);

        return comment;
    }

    public void updateComment(int id, String type, String client, String content, int likes) throws IOException, ClassNotFoundException {
        Comment comment = new Comment(id, type, client, content, likes);
        CommentDAO commentDAO = new CommentDAO();

        commentDAO.updateComment(comment);
    }

}
