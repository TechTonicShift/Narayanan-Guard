package com.mycompany.codesangam;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageOperation {

    private static final String ROBOTO_FONT = "Roboto";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 25;

    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);

            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] ^ key);
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(data);
            }

            JOptionPane.showMessageDialog(null, "Operation completed successfully");

        } catch (IOException e) {
            handleException(e);
        }
    }

    private static void handleException(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        System.out.println("This is testing");

        JFrame frame = new JFrame();
        frame.setTitle("Image Operation");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font(ROBOTO_FONT, FONT_STYLE, FONT_SIZE);

        // Creating button
        JButton button = new JButton("Open Image");
        button.setFont(font);

        // Creating text field
        JTextField textField = new JTextField(10);
        textField.setFont(font);

        button.addActionListener(e -> {
            System.out.println("Button clicked");
            String text = textField.getText();

            try {
                int key = Integer.parseInt(text);
                operate(key);
            } catch (NumberFormatException ex) {
                handleException(ex);
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(textField);
        frame.setVisible(true);
    }
}
