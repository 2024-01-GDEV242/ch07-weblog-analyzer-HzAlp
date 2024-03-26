/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    // New constructor that takes a log file name
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(filename);
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    // Method to calculate the total number of accesses
    public int numberOfAccesses()
    {
        int total = 0;
        for(int count : hourCounts) {
            total += count;
        }
        return total;
    }
    
    // Method to find the busiest hour
    public int busiestHour()
    {
        int busiestHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] > hourCounts[busiestHour]) {
                busiestHour = hour;
            }
        }
        return busiestHour;
    }    
    
    // Method to find the quietest hour
    public int quietestHour()
    {
        int quietestHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] < hourCounts[quietestHour]) {
                quietestHour = hour;
            }
        }
        return quietestHour;
    }     
    
}
