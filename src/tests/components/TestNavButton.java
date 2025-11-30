package tests.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.button.NavButton;

public class TestNavButton {
    @Test
    public void testCtor1() {
        NavButton nb = new NavButton("title");
        assertEquals(nb.getText(), "title");
    }

    @Test
    public void testCtor2() {
        NavButton nb = new NavButton("title", true);
        assertEquals(nb.getText(), "title");
        assertEquals(nb.isActive(), true);
    }

    @Test
    public void testActiveSetter() {
        NavButton nb = new NavButton();
        nb.setActive(true);
        assertEquals(nb.isActive(), true);
    }

    @Test
    public void testDefaultActiveState() {
        NavButton nb = new NavButton();
        assertEquals(nb.isActive(), false);
    }

}
