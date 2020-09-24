package dataAccessLayer;

import model.FavouriteCookie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.Server;

import java.util.List;

public class FavouriteDAO {

        private SessionFactory sessionFactory;

        public  FavouriteDAO() {
            this.sessionFactory = Server.sessionFactory;
        }

        public boolean createFavourite(FavouriteCookie favorite) {
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<FavouriteCookie> list = session.createQuery("from FavouriteCookie where favouriteType = '"+favorite.getFavouriteType()+"' and clientName = '"+favorite.getClientName()+"'", FavouriteCookie.class).list();
                if(list.size() == 0 ){
                    session.save(favorite);
                    session.getTransaction().commit();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }

        public boolean deleteFavourite(FavouriteCookie favorite) {
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<FavouriteCookie> list = session.createQuery("from FavouriteCookie where favouriteType = '"+favorite.getFavouriteType()+"' and clientName = '"+favorite.getClientName()+"'", FavouriteCookie.class).list();
                if(list.size() > 0 ){
                    session.delete(list.get(0));
                    session.getTransaction().commit();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }

        public void createFavouriteCookie(FavouriteCookie favorite) {
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<FavouriteCookie> list = session.createQuery("from FavouriteCookie where favouriteType = '"+favorite.getFavouriteType()+"' and clientName = '"+favorite.getClientName()+"'", FavouriteCookie.class).list();
                if(list.size() == 0 ){
                    session.save(favorite);
                    session.getTransaction().commit();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        public void deleteFavouriteCookie(FavouriteCookie favorite) {
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<FavouriteCookie> list = session.createQuery("from FavouriteCookie where favouriteType = '"+favorite.getFavouriteType()+"' and clientName = '"+favorite.getClientName()+"'", FavouriteCookie.class).list();
                if(list.size() > 0 ){
                    session.delete(list.get(0));
                    session.getTransaction().commit();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        public List<FavouriteCookie> viewAll(String username) {
            List<FavouriteCookie> list;
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                list = session.createQuery("from FavouriteCookie where clientName = '"+username+"'", FavouriteCookie.class).list();
                return list;
            }
        }

    }


