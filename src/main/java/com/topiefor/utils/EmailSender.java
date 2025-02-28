package com.topiefor.utils;

import com.topiefor.models.Address;
import com.topiefor.models.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.api.email.Email;

public class EmailSender {

    private String withSubject;
    private StringBuilder withPlainText;
    private String senderEmail;
    private String senderPassword;

    public EmailSender() {
        getSenderDetails();
    }

    private void getSenderDetails() {

        this.senderEmail = "msfttopiefor@gmail.com";
        this.senderPassword = "czhpizzngdfgrbrr";

    }

    private boolean sendEmail(String receiverEmail, String withSubject, StringBuilder withPlainText) {
        boolean isSent = false;
        System.out.println("here" + getSenderEmail());
        Email email = EmailBuilder.startingBlank()
                .from(getSenderEmail()).to(receiverEmail)
                .withSubject(withSubject)
                .withPlainText(withPlainText.toString())
                .buildEmail();
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, getSenderEmail(), getSenderPassword())
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();

        mailer.sendMail(email);

        return isSent;
    }

    public void VerificationEmail(String receiverEmail, String userName, int code) {
        withSubject = "To Pie For Account Verification Code";
        withPlainText = new StringBuilder();
        withPlainText.append("Dear ").append(userName).append("\n")
                .append("\n")
                .append("Thank you for registering with To Pie For! To ensure the security of your account and provide you with a seamless ordering experience, we require you to verify your account.\n")
                .append("\n Please use the following verification code to complete your account verification:\n")
                .append("\nVerification Code: ").append(code).append(" \n \n Once your account is verified, you'll gain access to all the features and benefits of being a To Pie For member, including easy online ordering and exclusive offers.\n")
                .append("\n \n Warm regards,\n")
                .append("\n[MSFT GROUP]\n")
                .append("To Pie For");

        sendEmail(receiverEmail, withSubject, withPlainText);
    }

    public void welcomeEmail(String receiverEmail, String userName, String telephone, Address address) {
        withSubject = " Welcome to To pie For!";
        withPlainText = new StringBuilder();
        withPlainText.append("Dear ").append(userName).append(" \n")
                .append("\n")
                .append("Welcome to To Pie For! We are thrilled to have you join our community of pie enthusiasts. This email serves as a confirmation of your successful registration on our online ordering platform.\n")
                .append("\n At To Pie For, we strive to provide you with an exceptional culinary experience, delivering mouthwatering pies right to your doorstep. With our wide variety of flavors and artisanal recipes, we are confident that you'll find something truly delicious to indulge in.\n")
                .append("Your registration details:\n")
                .append("\n Name: ").append(userName).append("\n")
                .append("Email: ").append(receiverEmail).append("\n")
                .append("Telephone Number: ").append(telephone).append("\n")
                .append("Address: ").append(address.getStreet()).append(address.getSuburb()).append(address.getCode()).append("\n")
                .append("Warm regards,\n")
                .append("\n MSFT GROUP\n")
                .append("To Pie For");
        sendEmail(receiverEmail, withSubject, withPlainText);
    }

    public void orderPlacedEmail(String receiverEmail, String userName, ArrayList<Product> cartList, Address addressFromDb) {
        LocalDate today = LocalDate.now();
        String prod = "";
        double total = 0;
        for (Product product : cartList) {
            prod += product.getName() + "   x" + product.getQuantity() + "   R " + product.getPrice() + "\n";
            total += product.getPrice();
        }
        withSubject = " Your Order Invoice from To Pie For!";
        withPlainText = new StringBuilder();
        withPlainText.append("Dear ").append(userName).append(" \n")
                .append("\n We hope this email finds you well and that you have been enjoying our delectable products from To Pie For! We are truly grateful for your recent order and would like to thank you for choosing us to satisfy your cravings.\n")
                .append("\n")
                .append("To ensure a seamless experience, we have prepared your detailed invoice for the order you placed on ").append(today).append(". Please find the breakdown below:\n")
                .append("\n Order Summary:\n")
                .append("Order Date: ").append(today).append(" \n")
                .append("Delivery Address: ").append(addressFromDb.getStreet()).append(", ").append(addressFromDb.getSuburb()).append(", ").append(addressFromDb.getStreet()).append("\n")
                .append("\n Item List:\n")
                .append(prod).append("\n")
                .append("Subtotal: R").append(total).append("\n")
                .append("\n")
                .append("Thank you once again for choosing To Pie For! We truly appreciate your business and look forward to serving you again in the future. Your satisfaction is our top priority, and we strive to provide you with the best culinary experience possible.\n")
                .append("Warm regards,\n")
                .append("\n")
                .append("MSFT GROUP\n")
                .append("To Pie For");
        sendEmail(receiverEmail, withSubject, withPlainText);
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public void passwordReseted(String userName, String receiverEmail) {
        LocalDate today = LocalDate.now();

        withSubject = "Password Reset Confirmation";
        withPlainText = new StringBuilder();
        withPlainText.append("Dear ").append(userName).append(" \n")
                .append("\n We wanted to inform you that your password has been successfully reset. You can now access your account using your new password.\n")
                .append("\n")
                .append("For security purposes, we recommend that you change your password regularly and avoid using easily guessable passwords. If you have any concerns regarding the security of your account, please contact our support team immediately.\n")
                .append("\n")
                .append(" If you did not initiate this password reset, please reach out to us immediately, as your account may have been compromised.\n")
                .append("\n")
                .append("Thank you once again for choosing To Pie For! We truly appreciate your business and look forward to serving you again in the future. Your satisfaction is our top priority, and we strive to provide you with the best culinary experience possible.\n")
                .append("Warm regards,\n")
                .append("\n")
                .append("MSFT GROUP\n")
                .append("To Pie For");
        sendEmail(receiverEmail, withSubject, withPlainText);
    }

}
