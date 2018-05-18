package rent;

import java.util.Date;
import java.util.Objects;

public class RentalAgreement {

  public String dueDate;
  public String dailyCharge;
  public int chargeDays;
  public String preDiscountCharge;
  public String discountPercent;
  public String discountAmount;
  public String finalCharge;
  public String toolCode;
  public String toolType;
  public String toolBrand;

  public void setToolProperties(String toolCode) throws Exception {
    if (toolCode == "LADW") {
      toolType = "Ladder";
      toolBrand = "Werner";
    } else if (toolCode == "CHNS") {
      toolType = "Chainsaw";
      toolBrand = "Stihl";
    } else if (toolCode == "JAKR") {
      toolType = "Jackhammer";
      toolBrand = "Ridgid";
    } else if (toolCode == "JAKD") {
      toolType = "Jackhammer";
      toolBrand = "DeWalt";
    } else {
      throw new Exception("Unknown tool code");
    }
    this.toolCode = toolCode;
  }

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
    // if all properties are the same, they are equal. Objects.equals handles null
    return (Objects.equals(rentalAgreement1.dueDate, rentalAgreement2.dueDate) &&
            Objects.equals(rentalAgreement1.dailyCharge, rentalAgreement2.dailyCharge) &&
            rentalAgreement1.chargeDays == rentalAgreement2.chargeDays &&
            Objects.equals(rentalAgreement1.preDiscountCharge, rentalAgreement2.preDiscountCharge) &&
            Objects.equals(rentalAgreement1.discountPercent, rentalAgreement2.discountPercent) &&
            Objects.equals(rentalAgreement1.discountAmount, rentalAgreement2.discountAmount) &&
            Objects.equals(rentalAgreement1.finalCharge, rentalAgreement2.finalCharge) &&
            Objects.equals(rentalAgreement1.toolCode, rentalAgreement2.toolCode) &&
            Objects.equals(rentalAgreement1.toolType, rentalAgreement2.toolType) &&
            Objects.equals(rentalAgreement1.toolBrand, rentalAgreement2.toolBrand));
  }

}
