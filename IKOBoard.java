import grovepi.GroveDeviceFactory;
import grovepi.GrovePi;
import grovepi.PinMode;
import grovepi.sensors.ButtonSensor;
import grovepi.sensors.Led;
import java.io.PrintStream;
import grovepi.sensors.RoterySensor;

class IKOBoard {
    IKOBoard() {}

    public static void main(String[] arrstring) {
        GrovePi grovePi = new GrovePi();
        grovePi.pinMode(2, PinMode.INPUT);

        // Objects needed for setup. 
        Led led = grovePi.getDeviceFactory().createLed(2);
        RoterySensor rotery = grovePi.getDeviceFactory().createLed(3);
        ButtonSensor buttonSensor = grovePi.getDeviceFactory().createButtonSensor(4);
        led.setState(false);

        // DEBUG:
        // 1 = Button test.
        // 2 = Blick test.
        // 3 = Volume test.
        int debugTest = 1; // Change for different tests.

        if (debugTest == 1) {
            // Sense for button press.
            do {
                // Button pressed.
                if (buttonSensor.isPressed()) {
                    System.out.println("Pressed");
                    // Turn on lED.
                    led.setState(true);
                    continue;
                }
                led.setState(false);
            } while (true);
        }

        if (debugTest == 2) {
            // Make LED blink. 
            do {
                led.setState(true);
                grovePi.coomon.Delay.milliseconds(500);
                led.setState(false);
                grovePi.common.Delay.milliseconds(500);
            } while (true);
        }

        if (debugTest == 3) {
            // Sense for volume change. 
            grovePi.analogRead(Pin.ANALOG_PIN_2);
            int state = grovePi.analogRead(Pin.ANALOG_PIN_2);
            do {
                // When value changes, print it out.
                if (state = !grovePi.analogRead(Pin.ANALOG_PIN_2)) {
                    System.out.println("Volume: " + state);
                    state = grovePi.analogRead(Pin.ANALOG_PIN_2);
                    continue;
                }
                led.setState(false);
            } while (true);
        }
    }
}
