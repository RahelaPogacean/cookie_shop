package dataAccessLayer;

import model.Cookie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.Server;

import java.util.List;

public class CookieDAOImpl {

        static SessionFactory sessionFactory;

        public CookieDAOImpl( ) {
            sessionFactory = Server.sessionFactory;
        }

        public boolean createCookie(Cookie cookie) {
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<Cookie> cookies = session.createQuery("from Cookie where type = '"+cookie.getType()+"'", Cookie.class).list();

                session.save(cookie);
//                for(Cookie cookie1 : cookies){
//                    cookie1.setStock(stock);
//                    session.update(cookie1);
//                }
                session.getTransaction().commit();
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }


        public boolean updateCookie(Cookie cookie) {
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<Cookie> cookies = session.createQuery("from Cookie where cookieId = '"+cookie.getCookieId()+"'", Cookie.class).list();
//                if(cookies.size() == 0){
//                    cookie.cookieBack();
//                }
               // int stock = cookies.size() + 1;
               // cookie.setStock(stock);
                for(Cookie cookie1: cookies){
                    cookie1.setType(cookie.getType());
                    cookie1.setPrice(cookie.getPrice());
                    cookie1.setQuantityOfSweeteners(cookie.getQuantityOfSweeteners());
                   // cookie1.setStock(stock);
                    cookie1.setRating(cookie.getRating());
                    session.update(cookie1);
                }
                session.getTransaction().commit();
                return true;
            }catch (Exception e){
                return false;
            }
        }

    public void modifyCookie(Cookie cookie) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Cookie> cookies = session.createQuery("from Cookie where cookieId = '"+cookie.getCookieId()+"'", Cookie.class).list();

            if(cookies.size() == 0){
                cookie.cookieBack();
            }

            for(Cookie cookie1: cookies){
                cookie1.setType(cookie.getType());
                cookie1.setPrice(cookie.getPrice());
                cookie1.setQuantityOfSweeteners(cookie.getQuantityOfSweeteners());
                cookie1.setStock(cookie.getStock());
                cookie1.setRating(cookie.getRating());
                session.update(cookie1);
            }
            session.getTransaction().commit();
        }catch (Exception e){

        }
    }

        public boolean deleteCookie(Cookie cookie) {
            try(Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<Cookie> cookies = session.createQuery("from Cookie where cookieId='"+cookie.getCookieId()+"'", Cookie.class).list();
                session.delete(cookies.get(0));
                session.getTransaction().commit();
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

//        public void deleteAllCookie(String type) {
//            try(Session session = sessionFactory.openSession()) {
//                session.beginTransaction();
//                List<Cookie> cookies = session.createQuery("from Cookie where type='"+type+"'", Cookie.class).list();
//                for(Cookie cookie1: cookies){
//                    session.delete(cookie1);
//                }
//                session.getTransaction().commit();
//            }
//        }

        public List<Cookie> viewAllCookies() {
            try(Session session = sessionFactory.openSession()) {
                List<Cookie> cookies = session.createQuery("from Cookie ", Cookie.class).list();

                for (Cookie cookie : cookies) {
                    System.out.println(">> Cookie: " + cookie.getCookieId());
                }
                return cookies;
            }
        }

//        public List<Cookie> viewAllDistinctCookies() {
//            try(Session session = sessionFactory.openSession()) {
//                return session.createQuery("select c from Cookie c where c.cookieId in (select distinct min(c.cookieId) from Cookie c group by c.type)").list();
//
//            }
//        }


        public Cookie searchByType(String type) {
            try (Session session = sessionFactory.openSession()) {
                List<Cookie> cookies = session.createQuery("from Cookie where type = '" + type + "'", Cookie.class).list();
                if (cookies.size() > 0) {
                    return cookies.get(0);
                }
                else
                    return null;
            }
        }

        public Cookie searchByPrice(Double price) {
            try (Session session = sessionFactory.openSession()) {
                List<Cookie> cookies = session.createQuery("from Cookie where price = '" + price + "'", Cookie.class).list();
                if (cookies.size() > 0) {
                    return cookies.get(0);
                }
                else
                    return null;
            }
        }

        public Cookie searchBySweets(Double quantityOfSweeteners) {
            try (Session session = sessionFactory.openSession()) {
                List<Cookie> cookies = session.createQuery("from Cookie where quantityOfSweeteners = '" + quantityOfSweeteners + "'", Cookie.class).list();
                if (cookies.size() > 0) {
                    return cookies.get(0);
                }
                else
                    return null;
            }
        }

        public Cookie searchByRating(Double rating) {
            try (Session session = sessionFactory.openSession()) {
                List<Cookie> cookies = session.createQuery("from Cookie where rating = '" + rating + "'", Cookie.class).list();
                if (cookies.size() > 0) {
                    return cookies.get(0);
                }
                else
                    return null;
            }
        }

//        public List<String> searchTypes() {
//            try(Session session = sessionFactory.openSession()) {
//                return session.createQuery("SELECT DISTINCT c1.type FROM Cookie c1").list();
//            }
//        }

//        public Double setRating(String type, Double rating){
//            Double newRating = (getRating(type) + rating)/2;
//            try(Session session = sessionFactory.openSession()) {
//                session.beginTransaction();
//                List<Cookie> cookies = session.createQuery("from Cookie where type = '"+type+"'", Cookie.class).list();
//                for(Cookie cookie1 : cookies){
//                    cookie1.setRating(newRating);
//                    session.update(cookie1);
//                }
//                session.getTransaction().commit();
//            }
//            return newRating;
//        }
//
//        public Double getRating(String type){
//            try(Session session = sessionFactory.openSession()) {
//                List<Double> ratingList = session.createQuery("SELECT c1.rating FROM Cookie c1 WHERE c1.type = '" + type + "'").list();
//                return ratingList.get(0);
//            }
//        }
//
//        public boolean updateRating(String type, Double rating){
//            try(Session session = sessionFactory.openSession()) {
//                session.beginTransaction();
//                List<Cookie> list = session.createQuery("from Cookie where type = '"+type+"'", Cookie.class).list();
//                if(list.size()>0){
//                    for(Cookie c : list){
//                        c.setRating(rating);
//                        session.update(c);
//                    }
//                    session.getTransaction().commit();
//                    return true;
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return false;
//        }
//
//        public List<Integer> searchStocks(List<String> types) {
//            try(Session session = sessionFactory.openSession()) {
//                List<Integer> stocks = null;
//                for(String type : types){
//                    List<Integer> stock = session.createQuery("FROM Cookie WHERE type = '"+type+"'").list();
//                    stock.add(stock.size());
//                }
//
//                return stocks;
//            }
//        }
//
//        public int searchStock(String type) {
//            try(Session session = sessionFactory.openSession()) {
//                List<Integer> stock = session.createQuery("FROM Cookie WHERE type = '"+type+"'").list();
//                return stock.size();
//            }
//        }
//
//        public void setSessionFactory(SessionFactory sessionFactory) {
//            this.sessionFactory = sessionFactory;
//        }
    }
