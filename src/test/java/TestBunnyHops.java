import org.testng.annotations.Test;
import pages.Documentation;
import utils.ReportUtils;

public class TestBunnyHops extends ReportUtils {

    @Documentation(
            coverage = "Validates that the expected number of bunny hop combinations are returned.",
            createdDate = "2/12/2022"
    )
    @Test
    public void testBunnyHops() {
        Bunny bunny = new Bunny();
        assertEquals("1 step", bunny.bunnyHops(1), 1);
        assertEquals("2 steps", bunny.bunnyHops(2), 2);
        assertEquals("3 steps", bunny.bunnyHops(3), 4);
        assertEquals("4 steps", bunny.bunnyHops(4), 7);
        assertEquals("5 steps", bunny.bunnyHops(5), 13);
    }
}
