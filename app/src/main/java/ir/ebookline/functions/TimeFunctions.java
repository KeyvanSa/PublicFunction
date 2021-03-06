package ir.ebookline.functions;

public class TimeFunctions {



    public long getCurrentTimeMillis()
    {
        return java.lang.System.currentTimeMillis();
    }


    public String getDisplayableTime(long delta) {
        long difference = 0;
        long mDate = getCurrentTimeMillis();
        if (mDate > delta) {
            difference = mDate - delta;
            final long seconds = difference / 1000;
            final long minutes = seconds / 60;
            final long hours = minutes / 60;
            final long days = hours / 24;
            final long months = days / 31;
            final long years = days / 365;

            if (seconds < 60) {
                return "a few seconds ago";
            } else if (seconds < 120) {
                return "a minute ago";
            } else if (seconds < 2700) // 45 * 60
            {
                return minutes + " minutes ago";
            } else if (seconds < 5400) // 90 * 60
            {
                return "an hour ago";
            } else if (seconds < 86400) // 24 * 60 * 60
            {
                return hours + " hours ago";
            } else if (seconds < 172800) // 48 * 60 * 60
            {
                return "yesterday";
            } else if (seconds < 2592000) // 30 * 24 * 60 * 60
            {
                return days + " days ago";
            } else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
            {
                return months <= 1 ? "a month ago" : months + " months ago";
            } else {
                return years <= 1 ? "a year ago" : years + " years ago";
            }
        }
        return null;
    }

}
