import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.swing.*;

class Prj_581_Main extends JFrame {

    public static void main(String[] args) {
        runSwingGUI(); //run the encoderRing GUI
//        logicTest();
//        zoneTest();


    }
    public static void runSwingGUI(){//run the encoderRing GUI
        Prj_581_swingGUI simpleGUI = new Prj_581_swingGUI();
        simpleGUI.setVisible(true);
    }
    public static void logicTest (){
        String designation = "new";
        String str = "testin";
        int desLen = 3;
        int projectLen = 3;
        int vars = 3;
        int mode = 0;

        Prj_581_encoderRing test = new Prj_581_encoderRing();
        ArrayList<String> result = test.encodeWithVars(str,projectLen,vars,mode);
        for (String s : result){
            System.out.println(s);
        }
    }
    public static void zoneTest(){
        System.out.println();
        long now = System.currentTimeMillis();
        //get minutes with a leading zero
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        TimeZone tz = TimeZone.getDefault();
        String zone1 = tz.getOffset(now)+"";
        String zone2 = tz.getDisplayName();
        String zone3 = tz.getRawOffset()+"";
        String zone4 = tz.getID();
        String zone5 = tz.toString();
//        ZoneId offset = ZoneOffset.of(tz.getID());

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String tZone2 = TimeZone.getTimeZone(offset).toString();
//        String tZone = offset.getId();
//        offset.getOffset();

        int zone = (tz.getOffset(now))/3600000;
        time = time.truncatedTo(ChronoUnit.SECONDS);
        date.format(dateFormat);
        time.format(timeFormat);
        ZoneId zoneId = ZoneId.systemDefault();
        Set<String> zoneId1 = ZoneId.getAvailableZoneIds();
//        String zone6 = zoneId.getDisplayName(zoneId);
        System.out.println(date+" "+time);
        System.out.println(zone1);
        System.out.println(zone2);
        System.out.println(zone3);
        System.out.println(zone4);
        System.out.println(zone5);
//        System.out.println(zone6);
//        String [] tk = getAvailableZoneIds
        tz.getDisplayName();

    }

}
