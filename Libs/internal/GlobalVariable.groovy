package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object Host
     
    /**
     * <p></p>
     */
    public static Object Port
     
    /**
     * <p></p>
     */
    public static Object User
     
    /**
     * <p></p>
     */
    public static Object Password
     
    /**
     * <p></p>
     */
    public static Object Driver
     
    /**
     * <p></p>
     */
    public static Object URL
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            Host = selectedVariables['Host']
            Port = selectedVariables['Port']
            User = selectedVariables['User']
            Password = selectedVariables['Password']
            Driver = selectedVariables['Driver']
            URL = selectedVariables['URL']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
