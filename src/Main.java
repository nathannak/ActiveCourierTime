import java.util.AbstractMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    /**
     * Company: Postmates.
     *
     * The “active time” of a courier is the time between the pickup and dropoff of a delivery. Given a set of data formatted like the following:
     *
     * (delivery id, timestamp, pickup/dropoff)
     *
     * Calculate the total active time in seconds. A courier can pick up multiple orders before dropping them off. The timestamp is in unix epoch
     * seconds.
     *
     * For example, if the input is the following:
     *
     * (1, 1573280047, 'pickup')
     * (1, 1570320725, 'dropoff')
     * (2, 1570321092, 'pickup')
     * (3, 1570321212, 'pickup')
     * (3, 1570322352, 'dropoff')
     * (2, 1570323012, 'dropoff')
     *
     * The total active time would be 1260 seconds.
     **/

    public static void main(String[] args) {

        System.out.println( calcAT(new String[][]{
        {"1", "1573280047", "pickup"},
        {"1", "1570320725", "dropoff"},
        {"2", "1570321092", "pickup"},
        {"3", "1570321212", "pickup"},
        {"3", "1570322352", "dropoff"},
        {"2", "1570323012", "dropoff"}}));

    }
    
    private static int calcAT(String[][] arr){
        
        int res=0;

        PriorityQueue<Map.Entry<Integer,String>> pq = new PriorityQueue<>( (a,b) -> (a.getKey()-b.getKey()));

        for(String[] strArr : arr){

            Integer ts = Integer.parseInt(strArr[1]);
            Map.Entry<Integer,String> e = new AbstractMap.SimpleEntry<>(ts,strArr[2]);
            pq.offer(e);
        }

        int earliestPickup=-1;

        while(pq.size()>0) {

            Map.Entry<Integer,String> e = pq.poll();

            if(e.getValue().equals("pickup")){
                //st.push(e.getKey());
                if(earliestPickup==-1) earliestPickup=e.getKey();
                else earliestPickup=Math.min(earliestPickup,e.getKey());
            }

            if(e.getValue().equals("dropoff")) {

                int dropOffts = e.getKey();

                if(earliestPickup!=-1) res+= (dropOffts-earliestPickup);
                earliestPickup=-1;
            }

        }

        return res;

    }

}
