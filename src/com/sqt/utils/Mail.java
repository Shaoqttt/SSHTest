package com.sqt.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Mail {
	String to = ""; // �ռ���
    String from = ""; // ������
    String host = ""; // smtp����
    String username = ""; // �û���
    String password = ""; // ����
    String subject = ""; // �ʼ�����
    String content = ""; // �ʼ�����
    public Mail() {
    }
    public Mail(String to, String from, String host, String username,
            String password, String subject, String content) {
        this.to = to;
        this.from = from;
        this.host = host;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }
    /**
     * ������ת��Ϊ����
     * 
     * @param strText
     * @return
     */
    public String transferChinese(String strText) {

        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(),
                    "GB2312"), "GB2312", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strText;
    }
    /**
     * �����ʼ�
     * 
     * @return �ɹ�����true��ʧ�ܷ���false
     */
    public boolean sendMail() {
        // ����mail session
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // ����MimeMessage���趨������ֵ��������Ϣ����
            MimeMessage msg = new MimeMessage(session);
            // ������Ϣ����
            msg.setFrom(new InternetAddress(from));
            System.out.println(from);
            // ���ʼ���ַӳ�䵽Internet��ַ��
            InternetAddress[] address = { new InternetAddress(to) };
            /**
             * setRecipient��Message.RecipientType type, Address
             * address�������������ʼ��Ľ����ߡ�<br>
             * ��������������һ�������ǽ����ߵ����ͣ��ڶ��������ǽ����ߡ�<br>
             * ���������Ϳ�����Message.RecipientType .TO��Message
             * .RecipientType.CC��Message.RecipientType.BCC��TO��ʾ��Ҫ�����ˣ�CC��ʾ������
             * ��BCC��ʾ���ܳ����ˡ��������뷢����һ����ͨ��ʹ��InternetAddress�Ķ���
             */
            msg.setRecipients(Message.RecipientType.TO, address);
            // �����ʼ��ı���
            subject = transferChinese(subject);
            msg.setSubject(subject);
            // ����Multipart
            Multipart mp = new MimeMultipart();

            // ��Multipart�������
            MimeBodyPart mbpContent = new MimeBodyPart();
            // �����ʼ�����(���ı���ʽ)
            // mbpContent.setText(content);
            // �����ʼ�����(HTML��ʽ)
            mbpContent.setContent(content, "text/html;charset=utf-8");
            // ��MimeMessage��ӣ�Multipart�������ģ�
            mp.addBodyPart(mbpContent);
            // ��Multipart���MimeMessage
            msg.setContent(mp);
            // �����ʼ����͵�ʱ�䡣
            msg.setSentDate(new Date());
            // �����ʼ�
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
