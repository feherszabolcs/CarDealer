package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import views.MainFrame;

public class MainFrameTest {
    MainFrame mFrame = new MainFrame();

    // Test that the frame has components
    @Test
    public void testComponentsExist() {
        assertNotEquals(mFrame.getComponentCount(), 0);
    }
}
