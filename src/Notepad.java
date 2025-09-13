import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Notepad {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Notepad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);

        // Text editing area with scroll
        JTextArea textArea = new JTextArea();
        JScrollPane  scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane,BorderLayout.CENTER);

        //creating menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu help = new JMenu("Help");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        JMenuItem cutItem = new JMenuItem("cut");
        JMenuItem copyItem = new JMenuItem("copy");
        JMenuItem pasteItem = new JMenuItem("paste");

        JMenuItem aboutItem = new JMenuItem("about");

        file.add(openItem);
        file.add(saveItem);
        file.add(exitItem);
        edit.add(cutItem);
        edit.add(copyItem);
        edit.add(pasteItem);
        help.add(aboutItem);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        frame.setJMenuBar(menuBar);

        //adding actionListener

        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                    textArea.read(reader, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error opening file!");
                }
            }
        });

        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    textArea.write(writer);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file!");
                }
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Simple Notepad\nCreated by: Sandani index-s16903"));

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);


        frame.setVisible(true);

    }
}
