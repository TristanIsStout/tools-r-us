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

  public String toString() {
    return "dueDate: " + dueDate + "\n" +
           "dailyCharge: " + dailyCharge + "\n" +
           "chargeDays: " + chargeDays + "\n" +
           "preDiscountCharge: " + preDiscountCharge + "\n" +
           "discountPercent: " + discountPercent + "\n" +
           "discountAmount: " + discountAmount + "\n" +
           "finalCharge: " + finalCharge + "\n";
  }

  public static boolean equal(RentalAgreement rentalAgreement1,
                              RentalAgreement rentalAgreement2) {
    // if both are null, they are equal
    if (rentalAgreement1 == null && rentalAgreement2 == null) {
      return true;
    // if just one is null, they are not equal
    } else if (rentalAgreement1 == null || rentalAgreement2 == null) {
      return false;
    }
    // if all properties are the same, they are equal
    return (rentalAgreement1.dueDate.equals(rentalAgreement2.dueDate) &&
            rentalAgreement1.dailyCharge.equals(rentalAgreement2.dailyCharge) &&
            rentalAgreement1.chargeDays == rentalAgreement2.chargeDays &&
            rentalAgreement1.preDiscountCharge.equals(rentalAgreement2.preDiscountCharge) &&
            rentalAgreement1.discountPercent.equals(rentalAgreement2.discountPercent) &&
            rentalAgreement1.discountAmount.equals(rentalAgreement2.discountAmount) &&
            rentalAgreement1.finalCharge.equals(rentalAgreement2.finalCharge));
  }

}
