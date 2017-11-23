
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class glider_phase1_code {
	
	private static void Sink() {
		// TODO Auto-generated method stub
	}
	private static void StopActuator() {
		// TODO Auto-generated method stub
	}
	private static void Rise() {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Glider Sinking and Rising Starting.");
		
		// create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
     
        // provision gpio pin #01 as an output pin and turn off
        final GpioPinDigitalOutput relay1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "relay1", PinState.LOW);
        // provision gpio pin #2 as an output pin and turn off 
        final GpioPinDigitalOutput relay2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "relay2", PinState.LOW);
        
    	Sink(); {
    		relay1.low();
    	    relay2.high();
    	    System.out.println("Actuator Retracting"); //Glider taking on water
    	    Thread.sleep(5000);
    	    }
    	
    	StopActuator(); {
    		relay1.low();
    		relay2.low();
    		System.out.println("Actuator Stopped"); //Glider is bottomed out
    	}
    	
    	Rise(); {
    		relay1.high();
    		relay2.low();
    		System.out.println("Actuator Extending"); //Glider pushing out water
    		Thread.sleep(5000);
    	}
    	
        // set shutdown states for these pins
        relay1.setShutdownOptions(true, PinState.LOW);
        relay2.setShutdownOptions(true, PinState.LOW);
        System.out.println("GPIO state should be: OFF");
        Thread.sleep(5000);
        
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
        System.out.println("Exiting");
	}
}
