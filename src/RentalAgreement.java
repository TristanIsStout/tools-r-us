package rent;

import java.util.Date;

public class RentalAgreement {

  public String dueDate;
  public String dailyCharge;
  public int chargeDays;
  public String preDiscountCharge;
  public String discountPercent;
  public String discountAmount;
  public String finalCharge;

  public static boolean equal(RentalAgreement rentalAgreement1,
                              RentalAgreement rentalAgreement2) {
    // if both are null, they are equal
    if (rentalAgreement1 == null && rentalAgreement2 == null) {
      return true;
    } else if (rentalAgreement1 == null || rentalAgreement2 == null) {
      return false;
    }
    // if all properties are the same, they are equal
    return (rentalAgreement1.dueDate ==rentalAgreement2.dueDate &&
            rentalAgreement1.dailyCharge ==rentalAgreement2.dailyCharge &&
            rentalAgreement1.chargeDays ==rentalAgreement2.chargeDays &&
            rentalAgreement1.preDiscountCharge ==rentalAgreement2.preDiscountCharge &&
            rentalAgreement1.discountPercent ==rentalAgreement2.discountPercent &&
            rentalAgreement1.discountAmount ==rentalAgreement2.discountAmount &&
            rentalAgreement1.finalCharge ==rentalAgreement2.finalCharge);
  }

}
