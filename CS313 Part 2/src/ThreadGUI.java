import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadGUI implements ActionListener {

    JFrame threadFrame;
    JPanel threadPanel;
    JButton refreshButton, searchButton, filterButton, startThreadButton, stopThreadButton, exitButton;
    ThreadMonitor threadMonitor;
    ThreadTable threadTable;

    public ThreadGUI() {
        threadMonitor = new ThreadMonitor();
        buildGUI();
    }

    public void buildGUI() {
        threadFrame = new JFrame("Thread Monitor");
        threadPanel = new JPanel(new GridLayout(3, 2));

        threadTable = new ThreadTable(threadMonitor.getThreads());

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {
            case "Refresh": {
                System.out.println("\n");
                threadMonitor.monitorThreads();
                threadTable.buildTable(threadMonitor.getThreads());
                threadFrame.getContentPane().removeAll();
                threadFrame.dispose();
                this.buildGUI();
                break;
            }
            case "Search by Name": {
                String name = JOptionPane.showInputDialog(threadPanel, "Please enter the name of the thread you want.", null);
                Thread thread = threadMonitor.searchThreads(name);
                if (thread != null) {
                    threadMonitor.printThread(thread);
                } else {
                    System.out.println("Could not find thread");
                }
                break;
            }
            case "Filter by Thread Group": {
                String name = JOptionPane.showInputDialog(threadPanel, "Please enter the name of the thread group you want.", null);
                ThreadGroup threadGroup = threadMonitor.filterByThreadGroup(name);
                if (threadGroup != null) {
                    Thread[] threads = new Thread[threadGroup.activeCount()];
                    while (threadGroup.enumerate(threads, true) == threads.length) {
                        threads = new Thread[threads.length * 2];
                    }
                    for (Thread thread : threads) {
                        threadMonitor.printThread(thread);
                    }
                } else {
                    System.out.println("Could not find thread");
                }
                break;
            }
            case "Start Thread": {
                break;
            }
            case "Stop Thread": {
                break;
            }
            case "Exit": {
                System.exit(0);
                break;

            }
        }
    }
}
