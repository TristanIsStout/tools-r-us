package test;

import junit.framework.TestCase;
import rent.*;

public class RentServiceTest extends TestCase {

  public void testTrue() {
    RentalAgreement ra = RentService.rentTool();
    assertTrue(true);
  }
}
