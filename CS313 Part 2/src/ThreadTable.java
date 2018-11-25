import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class ThreadTable extends JTable {

    private JScrollPane scrollPane;
    private DefaultTableModel dm;


    ThreadTable(Thread[] threads) {
            buildTable(threads);
        }

        public JScrollPane getScrollPane() {
            return scrollPane;
        }

        public void buildTable(Thread[] threads) {


            ArrayList<Thread> threadNoNulls = clearNulls(threads);


            dm = new DefaultTableModel(0,0);
            String header[] = new String[] { "Thread Name", "ID", "State",
                    "Priority", "Daemon", "Thread Group" };
            dm.setColumnIdentifiers(header);
            this.setModel(dm);

            for (int i = 0; i < threadNoNulls.size(); i++) {
                Vector<Object> data = new Vector<>();
                data.add(threadNoNulls.get(i).getName());
                data.add(Long.toString(threadNoNulls.get(i).getId()));
                data.add(threadNoNulls.get(i).getState().toString());
                data.add(Integer.toString(threadNoNulls.get(i).getPriority()));
                data.add(Boolean.toString(threadNoNulls.get(i).isDaemon()));
                data.add(threadNoNulls.get(i).getThreadGroup().getName());
                dm.addRow(data);
            }

            scrollPane = new JScrollPane(this);
        }

        public void updateTable(Thread thread) {


            Vector<Object> row = new Vector<>();
            row.add(thread.getName());
            row.add(Long.toString(thread.getId()));
            row.add(thread.getState().toString());
            row.add(thread.getPriority());
            row.add(Boolean.toString(thread.isDaemon()));
            row.add(thread.getThreadGroup().getName());
            dm.addRow(row);
        }

        public void clearTable() {
            dm.setRowCount(0);
        }

        public ArrayList<Thread> clearNulls(Thread[] threads) {
            ArrayList<Thread> threadNoNulls = new ArrayList<>();
                for (Thread thread : threads) {
                    if (thread != null) {
                        threadNoNulls.add(thread);
                    }
                }
                return threadNoNulls;
        }

        public void deleteRowAtThread(Thread thread) {
            int rowNum = 0;
            for (int i = dm.getRowCount() - 1; i >= 0; --i) {
                    if (dm.getValueAt(i, 1).equals(thread.getName())) {
                        // what if value is not unique?
                        rowNum = i;
                        break;
                    }
            }

            if (rowNum != 0) {
                dm.fireTableRowsDeleted(rowNum, rowNum);
            }

        }

}
