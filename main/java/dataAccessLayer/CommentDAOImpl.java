package dataAccessLayer;

import model.Admin;
import model.Client;
import model.Comment;
import model.Cookie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.Server;

import java.util.List;

public class CommentDAOImpl {

        private SessionFactory sessionFactory;

        public CommentDAOImpl( ) {
            this.sessionFactory = Server.sessionFactory;
        }

        public void addComment(Comment comment) {
            this.sessionFactory.getCurrentSession().save(comment);
        }

        public void updateComment(Comment comment) {
            this.sessionFactory.getCurrentSession().update(comment);
        }

        public void deleteComment(Comment comment) {
            this.sessionFactory.getCurrentSession().delete(comment);
        }

        @SuppressWarnings("unchecked")
        public List<Comment> viewAllComments() {
            return this.sessionFactory.getCurrentSession().createQuery("from Comment ").list();
        }

        public Comment searchByType(Cookie cookie) {
            try (Session session = sessionFactory.openSession()) {
                List< Comment > comments = session.createQuery("from Comment where typeCookie = '"+cookie.getType()+"'", Comment.class).list();
                if (comments.size() > 0) {
                    return comments.get(0);
                }
                else
                    return null;
            }
        }

        public Comment searchByClient(Client client) {
            try (Session session = sessionFactory.openSession()) {
                List< Comment > comments = session.createQuery("from Comment where clientName = '"+client.getClientName()+"'", Comment.class).list();
                if (comments.size() > 0) {
                    return comments.get(0);
                }
                else
                    return null;
            }
        }
}
