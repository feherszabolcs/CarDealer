package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import views.AddFrame;

public class AddFrameTest {
    AddFrame addFrame = new AddFrame();

    // Test if the frame has components rendered
    @Test
    public void testComponentsExist() {
        assertNotEquals(addFrame.getComponentCount(), 0);
    }
}
