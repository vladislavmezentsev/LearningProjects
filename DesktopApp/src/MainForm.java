import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainForm;
    private JTextField name;
    private JTextField surname;
    private JTextField patronymic;
    private JButton collapseButton;

    public MainForm() {

        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String inputName = name.getText();
                String inputSurname = surname.getText();
                String inputPatronymic = patronymic.getText();
                if (!inputName.isEmpty() || !inputSurname.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            mainForm,
                            inputSurname + " " + inputName + " " + inputPatronymic,
                            "Ф.И.О.",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    if (collapseButton.getText().equals("Collapse")) {
                        collapseButton.setText("Expand");
                    } else {
                        collapseButton.setText("Collapse");
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            mainForm,
                            "Введите данные",
                            "Произошла ошибка",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }

            }
        });

    }

    public JPanel getMainForm() {
        return mainForm;
    }
}
