package bankaccount;

import java.util.Calendar;
import javax.swing.JOptionPane;

public class ValidCharacter {

    // alphabetOnly method to check only alphabet input
    protected static boolean alphabetOnly(String str) {
        boolean status = false; // initialize status variable return value
        if ("".equals(str)) { // if no character input, show notification
            JOptionPane.showMessageDialog(null, "Please input full name.", "Full name", JOptionPane.WARNING_MESSAGE);
        } else { // if character has been input, check each character to make sure only alphabet input
            int wrong = 0; // initialize wrong variable to count wrong character (not alphabet)
            for (int i = 0; i < str.length(); i++) {
                int zz = (int) str.charAt(i);
                if ((zz >= 65 && zz <= 90) || (zz >= 97 && zz <= 122) || zz == 32) {
                } else {
                    wrong++;
                }
            }
            if (wrong == 0) { // if all input are alphabet, update status into true
                status = true;
            } else { // if there is any wrong character, show notification
                JOptionPane.showMessageDialog(null, "Please input alphabet only.", "Full name", JOptionPane.WARNING_MESSAGE);
            }
        }
        return status; // return status value
    }

    // numberOnly method to check only number input
    protected static boolean numberOnly(String str) {
        boolean status = false; // initialize status variable return value
        if ("".equals(str)) { // if no character input, show notification
            JOptionPane.showMessageDialog(null, "Please input phone number.", "Phone number", JOptionPane.WARNING_MESSAGE);
        } else { // if character has been input, check each character to make sure only number input
            int wrong = 0; // initialize wrong variable to count wrong character (not number)
            for (int c = 0; c < str.length(); c++) {
                int zz = (int) str.charAt(c);
                if (zz >= 48 && zz <= 57) {
                } else {
                    wrong++;
                }
            }
            if (wrong == 0) {  // if all input are number, update status into true
                status = true;
            } else { // if there is any wrong character, show notification
                JOptionPane.showMessageDialog(null, "Please input number only.", "Phone number", JOptionPane.WARNING_MESSAGE);
            }
        }
        return status; // return status value
    }

    // validBalance method to check valid balance input
    protected static boolean validBalance(String str) {
        boolean status = false; // initialize status variable return value
        int wrong = 0; // initialize wrong variable to count invalid balance
        for (int c = 0; c < str.length(); c++) {
            int zz = (int) str.charAt(c);
            if ((zz >= 48 && zz <= 57) || zz == 46) {
            } else {
                wrong++;
            }
        }
        if (wrong == 0) { // if balance valid, parse to double to check any execption and update status into true
            try {
                Double.parseDouble(str);
                status = true;
            } catch (Exception e) { // if there is any exception, show notification
                JOptionPane.showMessageDialog(null, "Please input a valid value.", "Opening Balance", JOptionPane.WARNING_MESSAGE);
            }
        } else { // if balance value is not valid, show notification
            JOptionPane.showMessageDialog(null, "Please input numeric only.", "Opening Balance", JOptionPane.WARNING_MESSAGE);
        }
        return status; // return status value
    }

    // checkDate method to check make sure calendar is chosen and age must more than 17 years old
    protected static boolean checkDate(Calendar dateCh) {
        boolean status = false; // initialize status variable return value
        try {
            dateCh.get(Calendar.DATE); // try to get date to check if there is any value
            // if age less than 17 years old, show notification
            if (Calendar.getInstance().get(Calendar.YEAR) - dateCh.get(Calendar.YEAR) < 17) {
                JOptionPane.showMessageDialog(null, "You have to be older than 17 years to open a bank account.", "Date of birth", JOptionPane.WARNING_MESSAGE);
            } else { // if age more than 17 years old, update status into true
                status = true;
            }
        } catch (NullPointerException e) { // show notification if date is null
            JOptionPane.showMessageDialog(null, "Please input date of birth.", "Date of birth", JOptionPane.WARNING_MESSAGE);
        }
        return status; // return status value
    }

    // checkEmpty method to check if there is no input from user
    protected boolean checkEmpty(String str, String str2) {
        boolean status = false; // initialize status variable return value
        if ("".equals(str)) { // if no character input, show notification
        } else {  // if there is any input, update status into true
            status = true;
        }
        return status; // return status value
    }
}