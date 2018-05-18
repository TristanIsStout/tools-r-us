package rent;

import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.NumberFormat;

public class RentService {
  
  public static RentalAgreement rentTool(
      String toolCode, String checkoutDate, int rentalDayCount, int discountPercent
  ) throws Exception {
    if (discountPercent < 0 || discountPercent > 100) {
      throw new Exception("Invalid Discount: must be a whole percent between 0 and 100.");
    }
    if (rentalDayCount < 1) {
      throw new Exception("Invalid Rental Day Count: must be a postive whole number.");
    }
    RentalAgreement rentalAgreement = new RentalAgreement();
    // Tool code, type, and brand, with param validation
    rentalAgreement.setToolProperties(toolCode);
    // formatters
    DateFormat df = new SimpleDateFormat("MM/dd/yy");
    Locale locale = new Locale("en", "US");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    // due date
    Date checkout = df.parse(checkoutDate);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(checkout);
    calendar.add(Calendar.DATE, rentalDayCount);
    rentalAgreement.dueDate = df.format(calendar.getTime());
    // daily charge
    double dailyCharge = getDailyCharge(toolCode);
    rentalAgreement.dailyCharge = currencyFormatter.format(dailyCharge);
    // charge days
    int chargeDays = getChargeDays(toolCode, checkout, rentalDayCount);
    rentalAgreement.chargeDays = chargeDays;
    // prediscount charge
    double preDiscountCharge = Math.round(dailyCharge * chargeDays * 100) / 100.0;
    rentalAgreement.preDiscountCharge = currencyFormatter.format(preDiscountCharge);
    rentalAgreement.discountPercent = "" + discountPercent + "%";
    // discount amount
    double discountAmount = Math.round(dailyCharge * chargeDays * discountPercent) / 100.0;
    rentalAgreement.discountAmount = currencyFormatter.format(discountAmount);
    // final charge
    rentalAgreement.finalCharge  = currencyFormatter.format(preDiscountCharge - discountAmount);
    return rentalAgreement;
  }

  public static double getDailyCharge(String toolCode) throws Exception {
    if (toolCode ==  "LADW") {
      return 1.99;
    } else if (toolCode == "CHNS") {
      return 1.49;
    } else if (toolCode == "JAKD" || toolCode == "JAKR") {
      return 2.99;
    } else {
      throw new Exception("Unkown tool code");
    }
  }

  public static int getChargeDays(String toolCode, Date checkout, int rentalDayCount) {
    int chargeDays = 0;
    Calendar calendar = Calendar.getInstance();
    // checkout day not included, rent period starts one day after
    for (int index = 1; index <= rentalDayCount; index++) {
      calendar.setTime(checkout);
      calendar.add(Calendar.DATE, index);
      // always charge for ladders
      if (toolCode == "LADW" ||
          // charge on weekdays for chainsaws
          (toolCode == "CHNS" && !isWeekend(calendar)) ||
          // charge for non holiday weekdays for jack hammers
          ((toolCode == "JAKD" || toolCode == "JAKR") && !isWeekend(calendar) && !isHoliday(calendar))
      ) {
        chargeDays++;
      }
    }
    return chargeDays;
  }

  public static boolean isWeekend(Calendar calendar) {
    return (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
            calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
  }

  public static boolean isHoliday(Calendar calendar) {
    // fourth of july
    if (calendar.get(Calendar.MONTH) == Calendar.JULY) {
      if (calendar.get(Calendar.DAY_OF_MONTH) == 4) {
        return true;
      }
      // friday before, if 4th is on a saturday
      if (calendar.get(Calendar.DAY_OF_MONTH) == 3 &&
          calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
        return true;
      }
      // monday after, if 4th is on a sunday
      if (calendar.get(Calendar.DAY_OF_MONTH) == 5 &&
          calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
        return true;
      }
    }
    // labor day is the first monday of september
    if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER &&
        calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY &&
        calendar.get(Calendar.DAY_OF_MONTH) < 8) {
      return true;
    }
    return false;
  }

}
