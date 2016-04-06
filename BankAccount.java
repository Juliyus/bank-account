package bankaccount;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.*;
import java.util.Calendar;

public class BankAccount extends Account { // inherit from Account class

    static int optionReturns; // variable for selected button option

    public static void main(String[] args) {
        mainScreen(); // call mainscreen method
    }

    private static void inputform() { // input form method
        BankAccount ba = new BankAccount("", "", "", "", "", "", "");
        
        JTextField Tname = new JTextField(5); // text field variable for full name
        JTextField Taddress = new JTextField(5); // text field  variable for full address
        JTextField Tphone = new JTextField(5); // text field  variable for phone number
        JTextField Temail = new JTextField(5); // text field  variable for email address
        JTextField Tbalance = new JTextField(5); // text field  variable for balance
        JDateChooser dateCh = new JDateChooser(); // variable for date chooser
        String Tdob; // variable for storing chosen date
        Calendar dob; // variable for getting day, month and year value from chosen date
        
        JPanel myPanel = new JPanel(new GridLayout(0, 1)); // create new object from JPanel
        myPanel.add(new JLabel("Full name:")); // add Full name label into panel
        myPanel.add(Tname); // add panel with Tname textfield

        myPanel.add(new JLabel("Date of birth:")); // add Date of birth label into panel
        myPanel.add(dateCh); // add Date of birth dateChooser into panel

        myPanel.add(new JLabel("Full address:")); // add Full address label into panel
        myPanel.add(Taddress); // add Full address textfield into panel

        myPanel.add(new JLabel("Phone number:")); // add Phone number label into panel
        myPanel.add(Tphone); // add Phone number textfield into panel

        myPanel.add(new JLabel("Email address:")); // add Email address label into panel
        myPanel.add(Temail); // add Email address textfield into panel

        myPanel.add(new JLabel("Opening balance:")); // add Opening balance label into panel
        myPanel.add(Tbalance); // add Opening balance textfield into panel

        myPanel.add(new JLabel("Account type:")); // add Account type label into panel

        DefaultComboBoxModel acctType = new DefaultComboBoxModel(); // create new object from DefaultComboBoxModel
        acctType.addElement("Saving"); // add Saving into element
        acctType.addElement("Checking"); // add Checking into element
        JComboBox comboBox = new JComboBox(acctType);  // create new object from JComboBox for popup menu selection
        myPanel.add(comboBox);  // add Account type comboBox into panel

        boolean status = false; // initialize status variable
        while (status == false) { // if status equals false, continue the loop
            // show confirmation dialog to input information details
            int result = JOptionPane.showConfirmDialog(null, myPanel, "Information details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) { // if OK button pressed, check all input value validity
                if (true == alphabetOnly(Tname.getText()) && true == checkDate(dateCh.getCalendar()) && true == ba.checkEmpty(Taddress.getText(), "Full address") && true == numberOnly(Tphone.getText()) && true == ba.checkEmpty(Temail.getText(), "Email address") && true == validBalance(Tbalance.getText())) {
                    status = true; // update status into true to end the loop
                }
            } else { // if Cancel button pressed, back to main screen
                mainScreen(); // call mainscreen method
            }
        }

        dob = dateCh.getCalendar(); // insert chosen calendar date to dob variable
        // extract day, moth and year from dob variable
        Tdob = dob.get(Calendar.DATE) + "/" + (dob.get(Calendar.MONTH) + 1) + "/" + dob.get(Calendar.YEAR);
        // insert all Account object value into database arrayList
        Double bal = Double.parseDouble(Tbalance.getText()); // initialize bal variable to store initial balance with double format
        database.add(new Account(Tname.getText(), Tdob, Taddress.getText(), Tphone.getText(), Temail.getText(), bal.toString(), comboBox.getSelectedItem().toString()));

        JOptionPane.showMessageDialog(null, "Information has been saved"); // show information that data has been saved

        // reset all variable for the next input
        Tname.setText("");
        dateCh.setCalendar(null);
        Taddress.setText("");
        Tphone.setText("");
        Temail.setText("");
        Tbalance.setText("");

        mainScreen(); // call mainscreen method
    }

    // Bank account constructor refer to Account class
    protected BankAccount(String name, String dob, String address, String phone, String email, String balance, String acctType) {
        super(name, dob, address, phone, email, balance, acctType);
    }

    // mainscreen method
    protected static void mainScreen() {
        // initialize buttons array for main screen menu
        String buttons[] = {"New account", "Deposit", "Withdrawal", "View account", "Exit"};
        // show main screen option dialog
        optionReturns = JOptionPane.showOptionDialog(null, "Please choose menu", "VJ Bank", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        // switch case for selected button
        switch (optionReturns) {
            case 0:
                inputform(); // if buttons index 0 (New Account) chosen, call inputform method
                break;
            case 1:
                deposit(viewAccount());  // if buttons index 1 (Deposit) chosen, call deposit method
                mainScreen(); // back to mainscreen when deposit done
                break;
            case 2:
                withdrawal(viewAccount()); // if buttons index 2 (Withdrawal) chosen, call withdrawal method
                mainScreen(); // back to mainscreen when deposit done
                break;
            case 3:
                accountDetail(viewAccount()); // if buttons index 3 (View account) chosen, call accountDetail method
                mainScreen(); // back to mainscreen when deposit done
                break;
            case 4: // if buttons index 4 (Exit) chosen, exit and terminate
                System.exit(0);
            default: // default, exit and terminate
                System.exit(0);
        }
    }

    // viewAccount method
    protected static int viewAccount() {
        int option = 0; // initialize option variable
        JPanel myPanel = new JPanel(new GridLayout(0, 1)); // create new object from JPanel with grid layout setting 
        myPanel.add(new JLabel("Please choose account:")); // add "Please choose account" label into panel

        DefaultComboBoxModel acctList = new DefaultComboBoxModel(); // create new object from DefaultComboBoxModel

        for (int i = 0; i < database.size(); i++) {
            acctList.addElement(i + 1 + ". " + database.get(i).acct_dtl[0]); // add each account into element 
        }

        JComboBox comboBox = new JComboBox(acctList); // create new object from JComboBox for popup menu selection
        myPanel.add(comboBox); // add account list comboBox into panel
        // show confirmation dialog to choose account
        int result = JOptionPane.showConfirmDialog(null, myPanel, "View Accounts", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) { // if OK button pressed, extract database arrayList index from selected item
            try { // split string value by "." and get the first value for database index reference into option variable
                option = Integer.parseInt(comboBox.getSelectedItem().toString().split("\\.")[0]) - 1;
            } catch (NullPointerException e) { // if OK button pressed and no account available, show information dialog and back to main screen
                JOptionPane.showMessageDialog(null, "No account available.", "Information", JOptionPane.WARNING_MESSAGE);
                mainScreen(); // call mainscreen method
            }
        } else { // if cancel button pressed, back to mainscreen
            mainScreen();
        }
        return option; // return option value
    }

// Override is not used because non static method cannot referenced from static context (JTextField) since only non static method can be overriden
    @Override 
    protected boolean checkEmpty(String str, String str2) {
        if (super.checkEmpty(str, str2) == false) {
            JOptionPane.showMessageDialog(null, "Please input " + str2.toLowerCase() + ".", str2, JOptionPane.WARNING_MESSAGE);
        }
        return super.checkEmpty(str, str2);
    }
}
