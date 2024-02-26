package ru.gonalex.prog.lab5;

import ru.gonalex.prog.lab5.manage.OrchestratorConsole;

public class Main {
    public static void main(String[] args) {
        String fileNameJson;
        fileNameJson = (args.length == 0) ? "data.json" : args[0];
        OrchestratorConsole orchestrator = new OrchestratorConsole(fileNameJson);
        orchestrator.run();
    }
}