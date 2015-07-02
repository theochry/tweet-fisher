/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tfisher.utils;

import javax.swing.JOptionPane;

public class CredentialsException extends Exception {
  public CredentialsException() { super(); }
  public CredentialsException(String message) {
                JOptionPane.showMessageDialog(null,"Some of the passwords may be incorect, please try to login again.","Wrong Credentials",JOptionPane.INFORMATION_MESSAGE);

  }
  public CredentialsException(String message, Throwable cause) { super(message, cause); }
  public CredentialsException(Throwable cause) { super(cause); }
}