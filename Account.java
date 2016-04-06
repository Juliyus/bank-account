package bankaccount;
//comment
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Account extends ValidCharacter{ // inherit from ValidCharacter class

    protected static ArrayList<Account> database = new ArrayList<>(); // initialize database arrayList
    protected String[] acct_dtl = new String[7]; // initialize account detail array

    // Account constructor
    protected Account(String name, String dob, String address, String phone, String email, String balance, String acctType) {
        this.acct_dtl[0] = name;
        this.acct_dtl[1] = dob;
        this.acct_dtl[2] = address;
        this.acct_dtl[3] = phone;
        this.acct_dtl[4] = email;
        this.acct_dtl[5] = balance;
        this.acct_dtl[6] = acctType;
    }

    // deposit method
    protected static void deposit(int selectedAccount) {
        byte counter = 0; // variable for loop
        String input; // variable for input value
        do {
            try { // input deposit amount and show current balance dialog box
                input = JOptionPane.showInputDialog(null, "Current balance: $" + database.get(selectedAccount).acct_dtl[5] + "\n\nEnter deposit amount:", "Deposit", JOptionPane.PLAIN_MESSAGE);
                if (input == null) { // If Cancel button selected, show confirmation dialog box
                    int conf = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (conf == 0) { //If yes confirmation button selected, back to main screen
                        BankAccount.mainScreen();
                    }
                }
                // If OK button selected without any value input or value less than 0, notify the user and return to input dialog box
                if ((input.length() == 0 || Double.parseDouble(input) <= 0)) {
                    JOptionPane.showMessageDialog(null, "Value must be greater than zero.", "Invalid value", JOptionPane.WARNING_MESSAGE);
                } else { // if there is any value input
                    double deposit; // initialize deposit variable
                    // add deposit to current balance
                    deposit = Double.parseDouble(database.get(selectedAccount).acct_dtl[5]) + Double.parseDouble(input);
                    // update current balance with new balance
                    database.get(selectedAccount).acct_dtl[5] = Double.toString(deposit);
                    // show message that deposit has been succeed
                    JOptionPane.showMessageDialog(null, "Deposit succeed.\nCurrent balance: $" + database.get(selectedAccount).acct_dtl[5]);
                    counter = 1; // update counter to 1 to end the loop
                }
            } catch (NumberFormatException e) { // to catch NumberFormatException error
                JOptionPane.showMessageDialog(null, "Please input a valid value", "Invalid value", JOptionPane.WARNING_MESSAGE);
            }
        } while (counter < 1); // loop if no valid value
    }

    // withdrawal method
    protected static void withdrawal(int selectedAccount) {
        byte counter = 0; // variable for loop
        String input; // variable for input value
        do {
            try { // input withdrawal amount and show current balance dialog box
                input = JOptionPane.showInputDialog(null, "Current " + database.get(selectedAccount).acct_dtl[6].toLowerCase() + " balance: $" + database.get(selectedAccount).acct_dtl[5] + "\n\nEnter withdrawal amount: $", "Withdrawal", JOptionPane.PLAIN_MESSAGE);
                if (input == null) { // If Cancel button selected, show confirmation dialog box
                    int conf = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (conf == 0) { //If yes confirmation button selected, back to main screen
                        BankAccount.mainScreen();
                    }
                }
                // If OK button selected without any value input or value less than 0, notify the user and return to input dialog box
                if ((input.length() == 0 || Double.parseDouble(input) <= 0)) {
                    JOptionPane.showMessageDialog(null, "Value must be greater than zero.", "Invalid value", JOptionPane.WARNING_MESSAGE);
                } else { // if there is any value input
                    double withdrawal; // initialize withdrawal variable
                    if (database.get(selectedAccount).acct_dtl[6].equals("Saving")) { // if account type is saving
                        // if balance more or equal than 1000, proceed withdrawal
                        if (Double.parseDouble(database.get(selectedAccount).acct_dtl[5]) - Double.parseDouble(input) >= 1000.0) {
                            // calculate withdrawal
                            withdrawal = Double.parseDouble(database.get(selectedAccount).acct_dtl[5]) - Double.parseDouble(input);
                            // update balance after withdrawal
                            database.get(selectedAccount).acct_dtl[5] = Double.toString(withdrawal);
                            // show message that withdrawal has been succeed
                            JOptionPane.showMessageDialog(null, "Withdrawal succeed.\nPrevious " + database.get(selectedAccount).acct_dtl[6].toLowerCase() + " balance: $" + (Double.parseDouble(database.get(selectedAccount).acct_dtl[5]) + Double.parseDouble(input)) + "\nWithdrawal amount: $" + Double.parseDouble(input) + "\nCurrent balance: $" + database.get(selectedAccount).acct_dtl[5], "Withdrawal", JOptionPane.INFORMATION_MESSAGE);
                            counter = 1; // update counter to 1 to end the loop
                        } else { // if balance less than 1000, show current balance, withdrawal amount, minimum balance and ask user to input smaller amount
                            JOptionPane.showMessageDialog(null, "Current " + database.get(selectedAccount).acct_dtl[6].toLowerCase() + " balance: $" + database.get(selectedAccount).acct_dtl[5] + "\nWithdrawal amount: $" + Double.parseDouble(input) + "\nMinimum balance is $1000 \nPlease input a smaller amount.", "Insuficient balance", JOptionPane.WARNING_MESSAGE);
                        }
                        // if account type is checking and balance less than 0 after withdrawal
                    } else if (Double.parseDouble(database.get(selectedAccount).acct_dtl[5]) - Double.parseDouble(input) < 0.0) {
                        // show current balance, withdrawal amount and ask user to input smaller amount
                        JOptionPane.showMessageDialog(null, "Current " + database.get(selectedAccount).acct_dtl[6].toLowerCase() + " balance: $" + database.get(selectedAccount).acct_dtl[5] + "\nWithdrawal amount: $" + Double.parseDouble(input) + "\nYou don't have enough balance, please input a smaller amount.", "Insuficient balance", JOptionPane.WARNING_MESSAGE);
                    } else { // if checking balance more than 0 after withdrawal, proceed withdrawal
                        // calculate withdrawal
                        withdrawal = Double.parseDouble(database.get(selectedAccount).acct_dtl[5]) - Double.parseDouble(input);
                        // update balance after withdrawal
                        database.get(selectedAccount).acct_dtl[5] = Double.toString(withdrawal);
                        // show message that withdrawal has been succeed
                        JOptionPane.showMessageDialog(null, "Withdrawal succeed.\nPrevious " + database.get(selectedAccount).acct_dtl[6].toLowerCase() + " balance: $" + (Double.parseDouble(database.get(selectedAccount).acct_dtl[5]) + Double.parseDouble(input)) + "\nWithdrawal amount: $" + Double.parseDouble(input) + "\nCurrent balance: $" + database.get(selectedAccount).acct_dtl[5], "Withdrawal", JOptionPane.INFORMATION_MESSAGE);
                        counter = 1; // update counter to 1 to end the loop
                    }
                }
            } catch (NumberFormatException e) { // to catch NumberFormatException error
                JOptionPane.showMessageDialog(null, "Please input a valid value", "Invalid value", JOptionPane.WARNING_MESSAGE);
            }
        } while (counter < 1); // loop if no valid value
    }

    // accountDetail method
    protected static void accountDetail(int selectedAccount) {
        // initialize labeln varible for labelname
        String[] labeln = {"Name: ", "Date of birth: ", "Address: ", "Phone number: ", "Email address: ", "Opening balance: ", "Account type: "};
        int i = 0; // initialize variable i for labeln index reference in the loop
        String detail = ""; // initialize detail variable to put all database extraction string
        for (String acct_dtls : database.get(selectedAccount).acct_dtl) {
            // insert account detail and labelname into detail string
            detail = detail + labeln[i++] + acct_dtls + "\n";
        }
        // show account information detail
        JOptionPane.showMessageDialog(null, detail, "Account information", JOptionPane.INFORMATION_MESSAGE);
    }
}
