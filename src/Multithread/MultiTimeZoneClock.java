import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MultiTimeZoneClock {
    private static List<ClockPanel> clocks = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Multi Time Zone Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Control Panel
        JPanel controlPanel = new JPanel();
        JTextField timeZoneField = new JTextField(20);
        JButton addButton = new JButton("Add Clock");

        controlPanel.add(new JLabel("Enter Time Zone:"));
        controlPanel.add(timeZoneField);
        controlPanel.add(addButton);

        // Clock Panel
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new BoxLayout(clockPanel, BoxLayout.Y_AXIS));

        // Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, controlPanel, clockPanel);
        frame.add(splitPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeZone = timeZoneField.getText().trim();
                if (!timeZone.isEmpty()) {
                    ClockPanel newClock = new ClockPanel(timeZone);
                    clocks.add(newClock);
                    clockPanel.add(newClock);
                    frame.revalidate();
                    timeZoneField.setText("");
                }
            }
        });

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ClockPanel clock : clocks) {
                    clock.updateTime();
                }
            }
        });
        timer.start();

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}