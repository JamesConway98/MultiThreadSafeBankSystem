import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ThreadGUI implements ActionListener {

    JFrame threadFrame;
    JPanel threadPanel;
    JButton refreshButton, searchButton, filterButton, startThreadButton, stopThreadButton, exitButton;
    ThreadMonitor threadMonitor;
    ThreadTable threadTable;

    public ThreadGUI() {
        threadMonitor = new ThreadMonitor();
        buildGUI(threadMonitor.getThreads());
    }

    public void buildGUI(Thread[] threads) {
        threadFrame = new JFrame("Thread Monitor");
        threadPanel = new JPanel(new GridLayout(3, 2));

        threadTable = new ThreadTable(threads);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);
        searchButton = new JButton("Search by Name");
        searchButton.addActionListener(this);
        filterButton = new JButton("Filter by Thread Group");
        filterButton.addActionListener(this);
        startThreadButton = new JButton("Start Thread");
        startThreadButton.addActionListener(this);
        stopThreadButton = new JButton("Stop Thread");
        stopThreadButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        threadPanel.add(refreshButton);
        threadPanel.add(searchButton);
        threadPanel.add(filterButton);
        threadPanel.add(startThreadButton);
        threadPanel.add(stopThreadButton);
        threadPanel.add(exitButton);

        Container contentPane = threadFrame.getContentPane();
        contentPane.add(threadPanel, BorderLayout.SOUTH);
        contentPane.add(threadTable.getScrollPane(), BorderLayout.CENTER);

        threadFrame.pack();
        threadFrame.setVisible(true);
    }

    private void refreshFrame(Thread[] threads) {
        threadTable.clearTable();
        ArrayList<Thread> noNulls = threadTable.clearNulls(threads);
        for (Thread thread : noNulls) {
            threadTable.updateTable(thread);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {
            case "Refresh": {
                threadMonitor.monitorThreads();
                refreshFrame(threadMonitor.getThreads());
                break;
            }
            case "Search by Name": {
                String name = JOptionPane.showInputDialog(threadPanel, "Please enter the name of the thread you want.", null);
                if (name == null) {
                    break;
                }
                Thread thread = threadMonitor.searchThreads(name);
                if (thread != null) {
                    threadTable.clearTable();
                    threadTable.updateTable(thread);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name");
                }
                break;
            }
            case "Filter by Thread Group": {
                String name = JOptionPane.showInputDialog(threadPanel, "Please enter the name of the thread group you want.", null);
                if (name == null) {
                    JOptionPane.showMessageDialog(null, "Please enter a name");
                } else {
                    ThreadGroup threadGroup = threadMonitor.filterByThreadGroup(name);
                    if (threadGroup != null) {
                        Thread[] threads = new Thread[threadGroup.activeCount()];
                        while (threadGroup.enumerate(threads, true) == threads.length) {
                            threads = new Thread[threads.length * 2];
                        }
                        refreshFrame(threads);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid name");
                    }
                    break;
                }
            }
            case "Start Thread": {
                String name = JOptionPane.showInputDialog(threadPanel, "Please enter the name of the thread you want to add.", null);
                if (name == null) {
                    break;
                }
                if (!name.equals("")) {
                    ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                    threadRunnable tr = new threadRunnable();
                    Thread thread = new Thread(threadGroup, tr,  name);
                    thread.start();
                    JOptionPane.showMessageDialog(null, "Thread validated. Adding...");
                    threadTable.updateTable(thread);
                    threadMonitor.monitorThreads();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name");
                }
                break;
            }
            case "Stop Thread": {
                String name = JOptionPane.showInputDialog(threadPanel, "Please enter the name of the thread you want to terminate.", null);
                if (name == null) {
                    break;
                }
                Thread thread = threadMonitor.searchThreads(name);
                if (thread != null) {
                    thread.stop();
                    JOptionPane.showMessageDialog(null, "Thread validated. Stopping...");
                    threadTable.deleteRowAtThread(thread);
                    refreshFrame(threadMonitor.getThreads());
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name");
                }
                break;
            }
            case "Exit": {
                System.exit(0);
                break;


            }
        }
    }
}
