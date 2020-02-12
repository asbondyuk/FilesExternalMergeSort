package cft.focusstart.bondyuk.commandLineParser;

import cft.focusstart.bondyuk.settings.Settings;

public interface CommandLineParser {
    Settings parseCommandLine(String[] commandLine);
}