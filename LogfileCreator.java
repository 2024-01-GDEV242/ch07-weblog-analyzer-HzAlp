import java.io.*;
import java.util.*;

/**
 * A class for creating log files of random data.
 * 
 * @author David J. Barnes and Michael Kölling
 * @editted by Alper Hiz 3/25/2024
 * @version    2016.02.29
 * 
 */
public class LogfileCreator
{
    private Random rand;

    /**
     * Create log files.
     */
    public LogfileCreator()
    {
        rand = new Random();
    }
    
    /**
     * Create a log file with entries for each year from startYear to endYear.
     * @param filename The file to write the log entries to.
     * @param numEntries The number of entries to generate for each year.
     * @param startYear The starting year for the log entries.
     * @param endYear The ending year for the log entries.
     * @return true if the log file was successfully created, false otherwise.
     */
    public boolean createFile(String filename, int numEntries, int startYear, int endYear)
    {
        boolean success = false;

        if(numEntries > 0 && startYear <= endYear) {
            try (FileWriter writer = new FileWriter(filename)) {
                for (int year = startYear; year <= endYear; year++) {
                    for (int i = 0; i < numEntries; i++) {
                        LogEntry entry = createEntry(year);
                        writer.write(entry.toString());
                        writer.write('\n');
                    }
                }

                success = true;
            }
            catch(IOException e) {
                System.err.println("There was a problem writing to " + filename);
            }

        }
        return success;
    }
    
    /**
     * Create a single (random) log entry for the given year.
     * @param year The year for the log entry.
     * @return A log entry containing random data for the specified year.
     */
    public LogEntry createEntry(int year)
    {
        int month = 1 + rand.nextInt(12);
        int day = 1 + rand.nextInt(28);
        int hour = rand.nextInt(24);
        int minute = rand.nextInt(60);
        return new LogEntry(year, month, day, hour, minute);
    }


}
