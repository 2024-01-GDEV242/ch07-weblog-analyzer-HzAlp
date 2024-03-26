/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Alper Hiz
 * @version    3/25/2024
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
    
    // Method to find the busiest two-hour period
    public int busiestTwoHour()
    {
        int busiestTwo = 0;
        int maxCount = 0;
        
        // Loop through each two-hour period
        for (int hour = 0; hour < 24; hour++) {
            int count = hourCounts[hour % 24] + 
            hourCounts[(hour + 1) % 24];
            if (count > maxCount) {
                maxCount = count;
                busiestTwo = hour % 24;
            }
        }
        
        return busiestTwo;
    }
    
    /**
     * Find the quietest day.
     * @return The quietest day.
     */
    public int quietestDay()
    {
        int[] dayCounts = new int[32]; // Array to store counts for each day (index 0 not used)
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }

        int quietestDay = 1; // Start with the assumption that the first day is the quietest
        for (int day = 2; day < dayCounts.length; day++) {
            if (dayCounts[day] < dayCounts[quietestDay]) {
                quietestDay = day;
            }
        }

        return quietestDay;
    }
    
    /**
     * Find the busiest day.
     * @return The busiest day.
     */
    public int busiestDay()
    {
        int[] dayCounts = new int[32]; // Array to store counts for each day (index 0 not used)
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }

        int busiestDay = 1; // Start with the assumption that the first day is the busiest
        for (int day = 2; day < dayCounts.length; day++) {
            if (dayCounts[day] > dayCounts[busiestDay]) {
                busiestDay = day;
            }
        }

        return busiestDay;
    }

    
    /**
     * Calculate the total accesses per month.
     * @return An array containing the total accesses for each month.
     */
    public int[] totalAccessesPerMonth()
    {
        int[] monthCounts = new int[13]; // Array to store counts for each month (index 0 not used)
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }

        return monthCounts;
    }

    /**
     * Find the quietest month.
     * @return The quietest month (1-12).
     */
    public int quietestMonth()
    {
        int[] monthCounts = new int[13]; // Array to store counts for each month (index 0 not used)
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }

        int quietestMonth = 1; // Start with the assumption that January is the quietest
        for (int month = 2; month < monthCounts.length; month++) {
            if (monthCounts[month] < monthCounts[quietestMonth]) {
                quietestMonth = month;
            }
        }

        return quietestMonth;
    }
    
    
}
