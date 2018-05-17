package test;

import java.util.*;
import junit.framework.TestCase;
import rent.*;

public class RentServiceTest extends TestCase {

  public void testRentTool() {
    RentalAgreement actual;
    String actualException;
    for (RentToolTestCase testCase : makeRentToolTestSet()) {
      actual = null;
      actualException = null;
      try {
        actual = RentService.rentTool(testCase.toolCode, testCase.checkoutDate,
                                      testCase.rentalDays, testCase.discount);
      } catch (Exception ex) {
        actualException = ex.getMessage();
      }
      if (testCase.exceptionMessage == null) {
        assertTrue(testCase.message, RentalAgreement.equal(testCase.expected, actual));
      } else {
        assertEquals(testCase.message, testCase.exceptionMessage, actualException);
      }
    }
  }

  private List<RentToolTestCase> makeRentToolTestSet() {
    List<RentToolTestCase> testSet = new ArrayList();
    RentToolTestCase testCase;
    // test 1
    testCase = new RentToolTestCase();
    testCase.message = "Exception thrown for bad percentage";
    testCase.toolCode = "JAKR";
    testCase.checkoutDate = "9/3/15";
    testCase.rentalDays = 5;
    testCase.discount = 101;
    testCase.expected = null; // because an exception is thrown
    testCase.exceptionMessage = "Invalid Discount: must be a whole percent between 0 and 100.";
    testSet.add(testCase);
    // test 2
    testCase = new RentToolTestCase();
    testCase.message = "Labor Day, Rigid Jackhammer";
    testCase.toolCode = "LADW";
    testCase.checkoutDate = "9/3/15";
    testCase.rentalDays = 5;
    testCase.discount = 10;
    testCase.expected = new RentalAgreement();
    testCase.expected.dueDate = "9/8/15";
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
    testCase.checkoutDate = "7/2/15";
    testCase.rentalDays = 5;
    testCase.discount = 25;
    testCase.expected = new RentalAgreement();
    testCase.expected.dueDate = "7/15/15";
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
    testCase.toolCode = "JACKD";
    testCase.checkoutDate = "9/3/15";
    testCase.rentalDays = 6;
    testCase.discount = 0;
    testCase.expected = new RentalAgreement();
    testCase.expected.dueDate = "9/9/15";
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
    testCase.toolCode = "JACKR";
    testCase.checkoutDate = "7/2/15";
    testCase.rentalDays = 9;
    testCase.discount = 0;
    testCase.expected = new RentalAgreement();
    testCase.expected.dueDate = "7/11/15";
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
    testCase.toolCode = "JACKR";
    testCase.checkoutDate = "7/2/20";
    testCase.rentalDays = 4;
    testCase.discount = 50;
    testCase.expected = new RentalAgreement();
    testCase.expected.dueDate = "7/6/15";
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
