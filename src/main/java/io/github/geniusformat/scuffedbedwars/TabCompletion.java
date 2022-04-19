package io.github.geniusformat.scuffedbedwars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {
    List<String> listArgs = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            listArgs.clear();
            listArgs.add("generator");
            listArgs.add("shop");
            listArgs.add("reload");
            for (String a : listArgs) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
                return result;
            }
            listArgs.clear();
        }
        else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("generator")) {
                listArgs.clear();
                listArgs.add("generator");
                listArgs.add("shop");
                listArgs.add("reload");
                for (String a : listArgs) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                    return result;
                }
                listArgs.clear();
            }
        }

        return null;
    }
}
