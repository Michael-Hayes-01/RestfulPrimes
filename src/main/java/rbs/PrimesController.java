package rbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;
import rbs.serialization.PrimesPOD;

public class PrimesController
{
    final static Logger logger = Logger.getLogger(PrimesController.class);
    Primes cache; // todo: confirm multi threaded behaviour

	public PrimesController(int maxCacheVal)
	{
        super();
        logger.info("PrimesController: starting a new instance [" + Thread.currentThread().getId() + "] "
                + "with cache maxCacheVal: " + maxCacheVal + ", max heap: " +  Runtime.getRuntime().maxMemory());
        try {
            long start_time = System.currentTimeMillis();
            cache = new Primes(maxCacheVal);
            long cache_setup_time = System.currentTimeMillis();
            logger.info("PrimesController initialised the cache in " + (cache_setup_time-start_time) + "ms");
        }
        catch (OutOfMemoryError ex){
            logger.error("Unable to initialise the cache with " + maxCacheVal + " elements, either reduce the configured cach.max.val " +
                         "or investigate increasing the max heap variable see -X command line args");
            throw ex;
        }
    }

    /**
     * return Simple JSON return
     */
	public Object read(Request request, Response response)
	{
        //'GET' logic
        String id = request.getHeader("prime_id");
        if(id==null)
            return "Invalid GET URL: unable to parse requested prime number";


        try{
            Integer initVal = Integer.parseInt(id);
            logger.info("Request for primes up to: " + initVal + ", str: " + id);
            if(initVal==null)
                return "Invalid GET URL: unable to parse requested prime number";

            long start_time = System.currentTimeMillis();
            int primes[] = cache.getPrimesUpTo(initVal);
            long requestTime = System.currentTimeMillis();
            logger.info("Retrieved " + primes.length + " primes in " + (requestTime-start_time) + "ms");

            // convert java object to JSON format,
            // and returned as JSON formatted string
            // as noted other return types could be supported however KeepItSimple here
            PrimesPOD pod = new PrimesPOD(initVal, primes);
            Gson gson = new Gson();
            return gson.toJson(pod);
        }
        catch (NumberFormatException ex){
            return "Invalid GET URL: unable to parse Integer -- " + ex.getMessage();
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }

    public Object create(Request request, Response response)
    {
        //'POST' logic here...
        return null;
    }

	public List<Object> readAll(Request request, Response response)
	{
		//'GET collection'
        return null;
	}

	public void update(Request request, Response response)
	{
		//'PUT' logic here...
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		//'DELETE' logic here...
		response.setResponseNoContent();
	}
}
