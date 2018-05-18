package rent;

import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

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
    DateFormat df = new SimpleDateFormat("MM/dd/yy");
    Date checkout = df.parse(checkoutDate);
    double dailyCharge = getDailyCharge(toolCode);
    int chargeDays = getChargeDays(toolCode, checkout, rentalDayCount);

    RentalAgreement rentalAgreement = new RentalAgreement();
    rental
    return rentalAgreement;
  }

  public static double getDailyCharge(String toolCode) throws Exception {
    switch (toolCode) {
      case "LADW":
        return 1.99;
      case "CHNS":
        return 1.49;
      case "JAKR":
      case "JAKD":
        return 2.99;
      default:
        throw new Exception("Unknown toolcode");
    }
  }

  public static int getChargeDays(String toolCode, Date checkout, int rentalDayCount
  ) throws Exception {
    int chargeDays = 0;
    Calendar calendar = Calendar.getInstance();
    for (int index = 0; index < rentalDayCount; index++) {
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
    System.out.println("hello " + chargeDays);
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
    if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER &&
        calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY &&
        calendar.get(Calendar.DAY_OF_MONTH) < 8) {
      return true;
    }
    return false;
  }

}
