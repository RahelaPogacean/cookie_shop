package businessLayer.service;

import dataAccessLayer.CommentDAOImpl;
import model.Client;
import model.Comment;
import model.Cookie;

import java.util.List;

public class CommentHandler {

        private CommentDAOImpl commentDAO = new CommentDAOImpl();

        public CommentHandler(){

        }
        public void addComment(Comment comment) {
            commentDAO.addComment(comment);
        }

        public void updateAdmin(Comment comment) {
            commentDAO.updateComment(comment);
        }

        public void deleteAdmin(Comment comment) {
            commentDAO.deleteComment(comment);
        }

        public List<Comment> viewAllComments() {
            return commentDAO.viewAllComments();
        }


        public Comment searchByCookieType(Cookie cookie) {
            return commentDAO.searchByType(cookie);
        }

        public Comment searchByClient(Client client) {
            return commentDAO.searchByClient(client);
        }

        public CommentDAOImpl getCommentDAO() {
            return commentDAO;
        }

        public void setCommentDAO(CommentDAOImpl commentDAO) {
            this.commentDAO = commentDAO;
        }
    }

