package businessLayer.generateReports;

import client.ShoppingCartService;
import com.itextpdf.text.DocumentException;
import model.ShoppingCart;
import presentationLayer.view.AdminTasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConcreteGenerator extends Generator {

    public static ArrayList<ShoppingCart> reports  = new ArrayList<ShoppingCart>();

    public ConcreteGenerator() {

        reports = generateReportsFromLasXDays(AdminTasks.noOfDays);
    }

    public Report factoryMethod(String type) throws IOException, DocumentException {

        if (type.equals("pdf")) {
            return new ConcreteReportPdf();
        }
        else if (type.equals("txt")) {
            return new ConcreteReportTxt();
        }

        return null;
    }

    public static Date subtractDay(Date date, int days) {

        days = AdminTasks.noOfDays;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -days + 1);//to also show the current day
        return cal.getTime();
    }

    public static ArrayList<java.sql.Date> getLastXDays(int days){

        days = AdminTasks.noOfDays;
        java.util.Date utilDate = new java.util.Date();
        ArrayList<java.util.Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();

        java.util.Date start = subtractDay(utilDate, days);
        calendar.setTime(start);

        java.util.Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        ArrayList<java.sql.Date> myDates = new ArrayList<java.sql.Date>();

        while (calendar.getTime().before(utilDate))
        {
            java.util.Date result = calendar.getTime();
            dates.add(result);
            java.sql.Date sqlDate = new java.sql.Date(result.getTime());
            myDates.add(sqlDate);
            calendar.add(Calendar.DATE, 1);
        }

        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
        myDates.add(sqlDate);

        return myDates;
    }
    public static ArrayList<ShoppingCart> generateReportsFromLasXDays(int days){

        ShoppingCartService shoppingCartService = new ShoppingCartService();
        ArrayList<ShoppingCart> shoppingCart = shoppingCartService.findAllShoppings();

        ArrayList<ShoppingCart> carts = new ArrayList<ShoppingCart>();
        ArrayList<java.sql.Date> myDates = getLastXDays(AdminTasks.noOfDays);//


        for (ShoppingCart shop : shoppingCart) {
            for (java.sql.Date date : myDates) {
                if (date.toString().equals(shop.getShoppingDate().toString())) {//
                    carts.add(shop);
                    System.out.println(shop);
                }
            }
        }

        return carts;
    }


}
