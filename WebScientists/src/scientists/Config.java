package scientists;

//import com.ibm.personafusion.db.CloudantClient;
import scientists.db.CloudantDatabase;

public class Config 
{
    
    /** Cloudant **/
    public static final int CLOUDANT_PORT = 443;
    public static final String CLOUDANT_NAME = "scientistweb";
    
    /** Watson User Modeling **/
    
    public static final String WATSON_PROF_API = "/api/v2/profile";
    public static final String WATSON_VIZ_API = "/api/v2/visualize";
    
    public static CloudantDatabase cc = new CloudantDatabase();

    /* Mobile Data Config */
    public static final String MOBILE_DATA_APP_ID = "";
    public static final String MOBILE_DATA_APP_SECRET = "";
}
