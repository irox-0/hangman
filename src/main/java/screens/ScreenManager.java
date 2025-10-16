package screens;

public class ScreenManager {
    private Screen currentScreen;

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public void displayCurrentScreen() {
        if (currentScreen != null)
            currentScreen.display();
    }

}
