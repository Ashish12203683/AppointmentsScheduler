import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;
        import java.util.List;

class Appointment {
    private String title;
    private String date;
    private String time;
    private String description;

    public Appointment(String title, String date, String time, String description) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}

class AppointmentScheduler {
    private List<Appointment> appointments;

    public AppointmentScheduler() {
        appointments = new ArrayList<>();
    }

    public void createAppointment(String title, String date, String time, String description) {
        Appointment appointment = new Appointment(title, date, time, description);
        appointments.add(appointment);
        JOptionPane.showMessageDialog(null, "Appointment created successfully!");
    }

    public void viewAppointments() {
        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No appointments found.");
        } else {
            StringBuilder appointmentsText = new StringBuilder();
            for (Appointment appointment : appointments) {
                appointmentsText.append("Title: ").append(appointment.getTitle()).append("");
                appointmentsText.append("Date: ").append(appointment.getDate()).append("");
                appointmentsText.append("Time: ").append(appointment.getTime()).append("");
                appointmentsText.append("Description: ").append(appointment.getDescription()).append("");
                appointmentsText.append("--------------------");
            }
            JTextArea appointmentsArea = new JTextArea(appointmentsText.toString());
            appointmentsArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(appointmentsArea);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Appointments", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void editAppointment(String title) {
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getTitle().equalsIgnoreCase(title)) {
                JTextField dateField = new JTextField(appointment.getDate());
                JTextField timeField = new JTextField(appointment.getTime());
                JTextField descriptionField = new JTextField(appointment.getDescription());

                JPanel editPanel = new JPanel(new GridLayout(0, 1));
                editPanel.add(new JLabel("New Date:"));
                editPanel.add(dateField);
                editPanel.add(new JLabel("New Time:"));
                editPanel.add(timeField);
                editPanel.add(new JLabel("New Description:"));
                editPanel.add(descriptionField);

                int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Appointment",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String newDate = dateField.getText();
                    String newTime = timeField.getText();
                    String newDescription = descriptionField.getText();

                    appointment = new Appointment(title, newDate, newTime, newDescription);
                    appointments.remove(appointment);
                    appointments.add(appointment);

                    JOptionPane.showMessageDialog(null, "Appointment edited successfully!");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Appointment not found.");
        }
    }

    public void deleteAppointment(String title) {
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getTitle().equalsIgnoreCase(title)) {
                appointments.remove(appointment);
                JOptionPane.showMessageDialog(null, "Appointment deleted successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Appointment not found.");
        }
    }

    public void setReminder(String title, String reminderTime) {
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getTitle().equalsIgnoreCase(title)) {
                JOptionPane.showMessageDialog(null, "Reminder set for appointment: " + appointment.getTitle() +
                        " at " + reminderTime);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Appointment not found.");
        }
    }

    public void searchAppointments(String keyword) {
        boolean found = false;
        StringBuilder searchResults = new StringBuilder();
        for (Appointment appointment : appointments) {
            if (appointment.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    appointment.getDate().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.append("Title: ").append(appointment.getTitle()).append("");
                searchResults.append("Date: ").append(appointment.getDate()).append("");
                searchResults.append("Time: ").append(appointment.getTime()).append("");
                searchResults.append("Description: ").append(appointment.getDescription()).append("");
                searchResults.append("");
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No appointments found matching the keyword: " + keyword);
        } else {
            JTextArea searchArea = new JTextArea(searchResults.toString());
            searchArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(searchArea);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Search Results", JOptionPane.PLAIN_MESSAGE);
        }
    }
}

public class AppointmentsScheduler_GUI_FinalProject {
    public static void main(String[] args) {
        AppointmentScheduler scheduler = new AppointmentScheduler();

        JFrame frame = new JFrame("Appointment Scheduler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        JButton createButton = new JButton("Create Appointment");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField titleField = new JTextField();
                JTextField dateField = new JTextField();
                JTextField timeField = new JTextField();
                JTextField descriptionField = new JTextField();

                JPanel createPanel = new JPanel(new GridLayout(0, 1));
                createPanel.add(new JLabel("Title:"));
                createPanel.add(titleField);
                createPanel.add(new JLabel("Date:"));
                createPanel.add(dateField);
                createPanel.add(new JLabel("Time:"));
                createPanel.add(timeField);
                createPanel.add(new JLabel("Description: createPanel.add(descriptionField"));

                int result = JOptionPane.showConfirmDialog(null, createPanel, "Create Appointment",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String title = titleField.getText();
                    String date = dateField.getText();
                    String time = timeField.getText();
                    String description = descriptionField.getText();

                    scheduler.createAppointment(title, date, time, description);
                }
            }
        });

        JButton viewButton = new JButton("View Appointments");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scheduler.viewAppointments();
            }
        });

        JButton editButton = new JButton("Edit Appointment");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter the title of the appointment to edit:");
                if (title != null && !title.isEmpty()) {
                    scheduler.editAppointment(title);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Appointment");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter the title of the appointment to delete:");
                if (title != null && !title.isEmpty()) {
                    scheduler.deleteAppointment(title);
                }
            }
        });

        JButton reminderButton = new JButton("Set Reminder");
        reminderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter the title of the appointment to set a reminder:");
                if (title != null && !title.isEmpty()) {
                    String reminderTime = JOptionPane.showInputDialog("Enter the reminder time:");
                    if (reminderTime != null && !reminderTime.isEmpty()) {
                        scheduler.setReminder(title, reminderTime);
                    }
                }
            }
        });

        JButton searchButton = new JButton("Search Appointments");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = JOptionPane.showInputDialog("Enter a keyword to search appointments:");
                if (keyword != null && !keyword.isEmpty()) {
                    scheduler.searchAppointments(keyword);
                }
            }
        });

        frame.add(createButton);
        frame.add(viewButton);
        frame.add(editButton);
        frame.add(deleteButton);
        frame.add(reminderButton);
        frame.add(searchButton);

        frame.setVisible(true);
    }
}