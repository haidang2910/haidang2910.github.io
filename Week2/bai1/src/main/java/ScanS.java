import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanS implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(jsonReader.class);

    @Override
    public void run() {

        int timeCheck = 0;
        boolean firstCheck = false;

        while (true){
            timeCheck +=2;
            String log = Integer.toString(jsonReader.getS());
            if (jsonReader.getS() > jsonReader.getOldS()*1.0015){
                logger.info(log);
                timeCheck = 0;
            }
            else if ( timeCheck > 12){
                logger.debug(log);
                timeCheck = 0;
            }

            else if ( jsonReader.getS()>0 && firstCheck == false){
                logger.info(log);
                firstCheck = true;
                timeCheck = 0;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
