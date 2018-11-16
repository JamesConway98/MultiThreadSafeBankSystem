import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ThreadTable extends JPanel {

    JScrollPane scrollPane;

    ThreadTable(Thread[] threads) {
        buildTable(threads);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JScrollPane buildTable(Thread[] threads) {
        ArrayList<Thread> threadNoNulls = new ArrayList<>();
        
        for (Thread thread : threads) {
            if (thread != null) {
                threadNoNulls.add(thread);
            }
        }
        
        String data[][] = new String[threadNoNulls.size()][5];
        String column[] = {"Thread Name", "ID", "State", "Priority", "Daemon"};
        for (int i = 0; i < threadNoNulls.size(); i++) {
                data[i][0] = threadNoNulls.get(i).getName();
                data[i][1] = Long.toString(threadNoNulls.get(i).getId());
                data[i][2] = threadNoNulls.get(i).getState().toString();
                data[i][3] = Integer.toString(threadNoNulls.get(i).getPriority());
                data[i][4] = Boolean.toString(threadNoNulls.get(i).isDaemon());
        }

        JTable threadDisplay = new JTable(data, column);
        threadDisplay.setBounds(30, 40, 200, 300);

        scrollPane = new JScrollPane(threadDisplay);

        return scrollPane;
    }
}
