package io.jenkins.plugins.maplelabs.Events.interfaces;

public interface IBuildEvent extends IEvent {
    final float MINUTE = 60;
    final float HOUR = 3600;
    public String EVENT = "BuildEvent";

    public static enum Type {
        STARTED,
        COMPLETED
    }

    public void collectEventData(Type type);

    static String getFormattedDuration(Long duration) {
        if (duration != null) {
            String output = "(";
            String format = "%.2f";
            double d = duration.doubleValue() / 1000;
            if (d < MINUTE) {
                output = output + String.format(format, d) + " secs)";
            } else if (MINUTE <= d && d < HOUR) {
                output = output + String.format(format, d / MINUTE) + " mins)";
            } else if (HOUR <= d) {
                output = output + String.format(format, d / HOUR) + " hrs)";
            }
            return output;
        } else {
            return "";
        }
    }
}
