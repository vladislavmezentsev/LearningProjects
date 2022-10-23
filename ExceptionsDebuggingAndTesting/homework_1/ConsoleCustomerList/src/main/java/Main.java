import org.apache.logging.log4j.*;

import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Marker INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY");
    private static final Marker INVALID_INPUT_MARKER = MarkerManager.getMarker("INVALID_INPUT");
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String HELP_TEXT = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        String command = "";

        while (true) {
            try {
                command = scanner.nextLine();
                LOGGER.info(INPUT_HISTORY_MARKER,"Request: " + command);
                String[] tokens = command.split("\\s+", 2);
                if (tokens[0].equals("add") && tokens.length == 1) {
                    throw new IllegalArgumentException("Wrong format. \nCorrect format: " + "add Василий Петров " +
                            "vasily.petrov@gmail.com +79215637722");
                } else if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(HELP_TEXT);
                } else {
                    System.out.println(COMMAND_ERROR);
                    LOGGER.warn(INVALID_INPUT_MARKER,"Request: " + command + ". Error: " + "Wrong command");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                LOGGER.warn(INVALID_INPUT_MARKER,"Request: " + command + ". Error: " + exception);
            }
        }
    }
}
