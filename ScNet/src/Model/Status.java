package Model;

import java.util.HashMap;
import java.util.Map;
/**
 * There are 4 Status defined
 * 0 - Register, for Future use, status before getting email confirmation upon registration
 * 1 - Active, used upon registration and upon reActivation
 * -1 - Disable, used upon deActivation
 * -2 - Terminated, for Future use, to terminate user if user perform illegal activities
 */
public enum Status {
	Register(0), Active(1), Disable(-1), Terminated(-2);
	
	private int value;
	
	Status(int status)
    {
        this.value = status;
    }
	/**
	 * To get integer value of Status for storing in database
	 * @return int - return integer value
	 */
    public int getCode()
    {
        return this.value;
    }
    
    private static final Map<Integer, Status> intToTypeMap = new HashMap<Integer, Status>();
	static {
	    for (Status type : Status.values()) {
	        intToTypeMap.put(type.value, type);
	    }
	}

	/**
	 * To convert integer to Status, used upon retrieval from database
	 * @param i - integer value stored in the database
	 * @return Status map to the integer parameter
	 */
	public static Status fromInt(int i) {
		Status type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return Status.Register;
	    return type;
	}
}
