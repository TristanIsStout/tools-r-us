package test;

import java.util.List;
import java.util.ArrayList;
import junit.framework.TestCase;

import rent.RentalAgreement;
import rent.RentService;

public class RentServiceTest extends TestCase {

  public void testRentTool() {
    RentalAgreement actual;
    String actualException;
    for (RentToolTestCase testCase : getRentToolTestSet()) {
      actual = null;
      actualException = null;
      try {
        actual = RentService.rentTool(testCase.toolCode, testCase.checkoutDate,
                                      testCase.rentalDays, testCase.discount);
      } catch (Exception ex) {
        actualException = ex.getMessage();
      }
      assertTrue(testCase.message, RentalAgreement.equal(testCase.expected, actual));
      assertEquals(testCase.message, testCase.exceptionMessage, actualException);
    }
  }

  private List<RentToolTestCase> getRentToolTestSet() {
    List<RentToolTestCase> testSet = new ArrayList();
    RentToolTestCase testCase;
    // test 1
    testCase = new RentToolTestCase();
    testCase.message = "Exception thrown for bad percentage";
    testCase.toolCode = "JAKR";
    testCase.checkoutDate = "09/03/15";
    testCase.rentalDays = 5;
    testCase.discount = 101;
    testCase.expected = null; // because an exception is thrown
    testCase.exceptionMessage = "Invalid Discount: must be a whole percent between 0 and 100.";
    testSet.add(testCase);
    // custom: test exception for bad number of days
    testCase = new RentToolTestCase();
    testCase.message = "Exception thrown for bad rental day count";
    testCase.toolCode = "JAKR";
    testCase.checkoutDate = "09/03/15";
    testCase.rentalDays = 0;
    testCase.discount = 0;
    testCase.expected = null; // because an exception is thrown
    testCase.exceptionMessage = "Invalid Rental Day Count: must be a postive whole number.";
    testSet.add(testCase);
    // custom: exception for bad date
    testCase = new RentToolTestCase();
    testCase.message = "Exception thrown for bad date";
    testCase.toolCode = "JAKR";
    testCase.checkoutDate = "200-200-200";
    testCase.rentalDays = 1;
    testCase.discount = 0;
    testCase.expected = null; // because an exception is thrown
    testCase.exceptionMessage = "Unparseable date: \"200-200-200\"";
    testSet.add(testCase);
    // custom: exception for bad tool code
    testCase = new RentToolTestCase();
    testCase.message = "Exception thrown for bad date";
    testCase.toolCode = "JACKR";
    testCase.checkoutDate = "09/03/15";
    testCase.rentalDays = 1;
    testCase.discount = 0;
    testCase.expected = null; // because an exception is thrown
    testCase.exceptionMessage = "Unknown tool code";
    testSet.add(testCase);
    // test 2
    testCase = new RentToolTestCase();
    testCase.message = "Labor Day, Ladder";
    testCase.toolCode = "LADW";
    testCase.checkoutDate = "09/03/15";
    testCase.rentalDays = 5;
    testCase.discount = 10;
    testCase.expected = new RentalAgreement();
    testCase.expected.toolCode = "LADW";
    testCase.expected.toolType = "Ladder";
    testCase.expected.toolBrand = "Werner";
    testCase.expected.dueDate = "09/08/15";
    testCase.expected.dailyCharge = "$1.99";
    testCase.expected.chargeDays = 5;
    testCase.expected.preDiscountCharge = "$9.95";
    testCase.expected.discountPercent = "10%";
    testCase.expected.discountAmount = "$1.00";
    testCase.expected.finalCharge = "$8.95";
    testCase.exceptionMessage = null;
    testSet.add(testCase);
    // test 3
    testCase = new RentToolTestCase();
    testCase.message = "Fourth of July on Saturday, Chainsaw";
    testCase.toolCode = "CHNS";
    testCase.checkoutDate = "07/02/15";
    testCase.rentalDays = 5;
    testCase.discount = 25;
    testCase.expected = new RentalAgreement();
    testCase.expected.toolCode = "CHNS";
    testCase.expected.toolType = "Chainsaw";
    testCase.expected.toolBrand = "Stihl";
    testCase.expected.dueDate = "07/07/15";
    testCase.expected.dailyCharge = "$1.49";
    testCase.expected.chargeDays = 3;
    testCase.expected.preDiscountCharge = "$4.47";
    testCase.expected.discountPercent = "25%";
    testCase.expected.discountAmount = "$1.12";
    testCase.expected.finalCharge = "$3.35";
    testCase.exceptionMessage = null;
    testSet.add(testCase);
    // test 4
    testCase = new RentToolTestCase();
    testCase.message = "Labor Day, DeWalt Jackhammer, no discount";
    testCase.toolCode = "JAKD";
    testCase.checkoutDate = "09/03/15";
    testCase.rentalDays = 6;
    testCase.discount = 0;
    testCase.expected = new RentalAgreement();
    testCase.expected.toolCode = "JAKD";
    testCase.expected.toolType = "Jackhammer";
    testCase.expected.toolBrand = "DeWalt";
    testCase.expected.dueDate = "09/09/15";
    testCase.expected.dailyCharge = "$2.99";
    testCase.expected.chargeDays = 3;
    testCase.expected.preDiscountCharge = "$8.97";
    testCase.expected.discountPercent = "0%";
    testCase.expected.discountAmount = "$0.00";
    testCase.expected.finalCharge = "$8.97";
    testCase.exceptionMessage = null;
    testSet.add(testCase);
    // test 5
    testCase = new RentToolTestCase();
    testCase.message = "Fourth of July, Rigid Jackhammer, no discount";
    testCase.toolCode = "JAKR";
    testCase.checkoutDate = "07/02/15";
    testCase.rentalDays = 9;
    testCase.discount = 0;
    testCase.expected = new RentalAgreement();
    testCase.expected.toolCode = "JAKR";
    testCase.expected.toolType = "Jackhammer";
    testCase.expected.toolBrand = "Ridgid";
    testCase.expected.dueDate = "07/11/15";
    testCase.expected.dailyCharge = "$2.99";
    testCase.expected.chargeDays = 5;
    testCase.expected.preDiscountCharge = "$14.95";
    testCase.expected.discountPercent = "0%";
    testCase.expected.discountAmount = "$0.00";
    testCase.expected.finalCharge = "$14.95";
    testCase.exceptionMessage = null;
    testSet.add(testCase);
    // test 6
    testCase = new RentToolTestCase();
    testCase.message = "Fourth of July, future";
    testCase.toolCode = "JAKR";
    testCase.checkoutDate = "07/02/20";
    testCase.rentalDays = 4;
    testCase.discount = 50;
    testCase.expected = new RentalAgreement();
    testCase.expected.toolCode = "JAKR";
    testCase.expected.toolType = "Jackhammer";
    testCase.expected.toolBrand = "Ridgid";
    testCase.expected.dueDate = "07/06/20";
    testCase.expected.dailyCharge = "$2.99";
    testCase.expected.chargeDays = 1;
    testCase.expected.preDiscountCharge = "$2.99";
    testCase.expected.discountPercent = "50%";
    testCase.expected.discountAmount = "$1.50";
    testCase.expected.finalCharge = "$1.49";
    testCase.exceptionMessage = null;
    testSet.add(testCase);
    return testSet;
  }

  private class RentToolTestCase {
    public String message;
    public String toolCode;
    public String checkoutDate;
    public int rentalDays;
    public int discount;
    public RentalAgreement expected;
    public String exceptionMessage;
  }

}
